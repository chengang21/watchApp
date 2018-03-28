package com.duoker.watch.ui.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.QueryContactEvent;
import com.duoker.watch.ui.view.sortlistview.CharacterParser;
import com.duoker.watch.ui.view.sortlistview.ClearEditText;
import com.duoker.watch.ui.view.sortlistview.PinyinComparator;
import com.duoker.watch.ui.view.sortlistview.SideBar;
import com.duoker.watch.ui.view.sortlistview.SortAdapter;
import com.duoker.watch.ui.view.sortlistview.SortModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chengang on 5/1/2017.
 * 从手机通讯录中选取电话号码
 */

public class SelectContactActivity extends BaseActivity {

    private static String TAG = SelectContactActivity.class.getSimpleName();
    private TextView mCenterToastView;
    private CharacterParser mCharacterParser;
    private ClearEditText mClearEditText;
    private SortAdapter mDataAdapter;
    private PinyinComparator mPinyinComparator;
    private SideBar mSideBar;
    private ListView mSortListView;
    private List<SortModel> mSourceDateList;

    private List<SortModel> filledData(List<ContactBean> paramList) {
        ArrayList list = new ArrayList();
        if (paramList == null) ;
        SortModel sortModel;
        ContactBean contact;
        String name;

        for (int i = 0; i < paramList.size(); i++) {
            sortModel = new SortModel();
            contact = (ContactBean) paramList.get(i);
            name = contact.getName();
            if (name != null) {
                sortModel.setName(name);
                sortModel.setCode(contact.getPhoneNumber());
                String str2 = this.mCharacterParser.getSelling(name).substring(0, 1).toUpperCase();
                if (str2.matches("[A-Z]"))
                    sortModel.setSortLetters(str2.toUpperCase());
                else
                    sortModel.setSortLetters("#");

                list.add(sortModel);

            }
        }
        return list;
    }


    private void filterData(String paramString) {
        List list = new ArrayList();
        if (TextUtils.isEmpty(paramString))
            list = this.mSourceDateList;

        if (list != null) {
            Collections.sort((List) list, this.mPinyinComparator);
            this.mDataAdapter.updateListView((List) list);
        } else {
            list.clear();
            Iterator localIterator = this.mSourceDateList.iterator();
            while (localIterator.hasNext()) {
                SortModel localSortModel = (SortModel) localIterator.next();
                String str = localSortModel.getName();
                if ((str.contains(paramString)) || (this.mCharacterParser.getSelling(str).startsWith(paramString)))
                    ((List) list).add(localSortModel);
            }
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_contact);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.search_bar_icon_normal);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                SelectContactActivity.this.finish();
            }
        });
    }

    private void initViews() {
        mCharacterParser = CharacterParser.getInstance();
        mPinyinComparator = new PinyinComparator();
        mSideBar = ((SideBar) findViewById(R.id.sidebar));
        mCenterToastView = ((TextView) findViewById(R.id.toast_view));
        mSideBar.setTextView(this.mCenterToastView);
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int i = SelectContactActivity.this.mDataAdapter.getPositionForSection(s.charAt(0));
                if (i != -1)
                    SelectContactActivity.this.mSortListView.setSelection(i);
            }
        });
        mSortListView = ((ListView) findViewById(R.id.select_contact_listview));
        mSortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                QueryContactEvent queryContactEvent = new QueryContactEvent();
                SortModel sortModel = (SortModel) SelectContactActivity.this.mDataAdapter.getItem(pos);
                queryContactEvent.name = sortModel.getName();
                queryContactEvent.phone = sortModel.getCode().replaceAll(" ", "");
                EventBus.getDefault().post(queryContactEvent);
                SelectContactActivity.this.finish();
            }
        });
        mClearEditText = ((ClearEditText) findViewById(R.id.filter_et));
        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable paramAnonymousEditable) {
            }

            @Override
            public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
            }

            @Override
            public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
                SelectContactActivity.this.filterData(paramAnonymousCharSequence.toString());
            }
        });
        new QueryPhoneContactsAsync().execute(new Void[0]);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_select_contact);
        initToolbar();
        initViews();
    }

class ContactBean {
    private String name;
    private String phoneNumber;

    ContactBean() {
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public void setPhoneNumber(String paramString) {
        this.phoneNumber = paramString;
    }
}

class QueryPhoneContactsAsync extends AsyncTask<Void, Void, List<SelectContactActivity.ContactBean>> {
    QueryPhoneContactsAsync() {
    }

    protected List<SelectContactActivity.ContactBean> doInBackground(Void[] paramArrayOfVoid) {
        ArrayList list = new ArrayList();
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        ContentResolver contentResolver = SelectContactActivity.this.getContentResolver();
        Cursor cursor1 = contentResolver.query(uri, new String[]{"_id"}, null, null, null);
        if (cursor1 != null) {
            while (cursor1.moveToNext()) {
                int i = cursor1.getInt(0);
                StringBuilder sb = new StringBuilder("contractID=");
                sb.append(i);
                Cursor cursor2 = contentResolver.query(Uri.parse("content://com.android.contacts/contacts/" + i + "/data"), new String[]{"mimetype", "data1", "data2"}, null, null, null);
                if (cursor2 != null) {
                    ContactBean contact = new ContactBean();
                    while (cursor2.moveToNext()) {
                        String str1 = cursor2.getString(cursor2.getColumnIndex("data1"));
                        String str2 = cursor2.getString(cursor2.getColumnIndex("mimetype"));
                        if ("vnd.android.cursor.item/name".equals(str2)) {
                            sb.append(",name=").append(str1);
                            contact.setName(str1);
                        } else if ("vnd.android.cursor.item/phone_v2".equals(str2)) {
                            sb.append(",phone=").append(str1);
                            contact.setPhoneNumber(str1);
                        }
                    }
                    list.add(contact);
                    cursor2.close();
                    Log.i(SelectContactActivity.TAG, sb.toString());
                }
            }
            cursor1.close();
        }
        return list;
    }

    protected void onPostExecute(List<SelectContactActivity.ContactBean> paramList) {
        super.onPostExecute(paramList);

        mSourceDateList = filledData(paramList);
        Collections.sort(mSourceDateList, mPinyinComparator);
        mDataAdapter = new SortAdapter(SelectContactActivity.this, mSourceDateList);
        mSortListView.setAdapter(mDataAdapter);
    }
}
}
