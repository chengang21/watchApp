package com.duoker.watch.ui.activity;

/**
 * Created by cheng on 2017/10/2.
 */


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.SetCountryCodeEvent;
import com.duoker.watch.ui.view.sortlistview.CharacterParser;
import com.duoker.watch.ui.view.sortlistview.ClearEditText;
import com.duoker.watch.ui.view.sortlistview.PinyinComparator;
import com.duoker.watch.ui.view.sortlistview.SideBar;
import com.duoker.watch.ui.view.sortlistview.SortAdapter;
import com.duoker.watch.ui.view.sortlistview.SortModel;
import com.duoker.watch.utils.GsonTools;
import com.duoker.watch.utils.IOUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CountryCodeActivity extends BaseActivity {
    private static String TAG = CountryCodeActivity.class.getSimpleName();
    private TextView mCenterToastView;
    private CharacterParser mCharacterParser;
    private ClearEditText mClearEditText;
    private SortAdapter mDataAdapter;
    private PinyinComparator mPinyinComparator;
    private SideBar mSideBar;
    private ListView mSortListView;
    private List<SortModel> mSourceDateList;


    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_country_code);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                finish();
            }
        });

        initViews();
    }

    private void initViews() {
        mCharacterParser = CharacterParser.getInstance();
        mPinyinComparator = new PinyinComparator();
        mSideBar = ((SideBar) findViewById(R.id.sidebar));
        mCenterToastView = ((TextView) findViewById(R.id.toast_view));
        mSideBar.setTextView(this.mCenterToastView);
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            public void onTouchingLetterChanged(String str) {
                int i = mDataAdapter.getPositionForSection(str.charAt(0));
                if (i != -1)
                    mSortListView.setSelection(i);
            }
        });

        mSortListView = ((ListView) findViewById(R.id.lv1));

        mSortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                SetCountryCodeEvent event = new SetCountryCodeEvent();
                SortModel sortModel = (SortModel) mDataAdapter.getItem(position);
                event.name = sortModel.getName();
                event.phoneCode = sortModel.getCode().replaceAll(" ", "");
                EventBus.getDefault().post(event);
                finish();
            }
        });
        this.mClearEditText = ((ClearEditText) findViewById(R.id.filter_et));
        this.mClearEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable paramAnonymousEditable) {
            }

            public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
            }

            public void onTextChanged(CharSequence str, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
                filterData(str.toString());
            }
        });

        new QueryPhoneContactsAsync().execute( new Void[0] );
    }

    private boolean isZh() {
        return getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }


    private List<SortModel> filledData(List<CountryCodeBean> countryCodeBeanList) {
        ArrayList arrayList = new ArrayList();
        if (countryCodeBeanList == null)
            return arrayList;

        for (int ii = 0; ii < countryCodeBeanList.size(); ii++) {
            CountryCodeBean countryCodeBean = (CountryCodeBean) countryCodeBeanList.get(ii);
            String name = countryCodeBean.getName();
            if (name != null) {
                SortModel sortModel = new SortModel();
                ;
                sortModel.setName(name);
                sortModel.setCode(countryCodeBean.getPhoneCode());
                String first = this.mCharacterParser.getSelling(name).substring(0, 1).toUpperCase();

                if (first.matches("[A-Z]"))
                    sortModel.setSortLetters(first.toUpperCase());
                else
                    sortModel.setSortLetters("#");

                arrayList.add(sortModel);
            }
        }
        return arrayList;
    }

    private void filterData(String filter) {
        List<SortModel> arrayList = new ArrayList<SortModel>();
        if (TextUtils.isEmpty(filter))
            arrayList = this.mSourceDateList;

        Iterator iterator = this.mSourceDateList.iterator();
        while (iterator.hasNext()) {
            SortModel sortModel = (SortModel) iterator.next();
            String name = sortModel.getName();
            if ((name.contains(filter)) || (this.mCharacterParser.getSelling(name).startsWith(filter)))
                arrayList.add(sortModel);
        }

        if (arrayList != null) {
            Collections.sort(arrayList, this.mPinyinComparator);
            this.mDataAdapter.updateListView(arrayList);
        } else
            arrayList.clear();
    }

    class CountryCodeBean {
        private String name;
        private String phoneCode;

        CountryCodeBean() {
        }

        public String getName() {
            return this.name;
        }

        public String getPhoneCode() {
            return this.phoneCode;
        }

        public void setName(String paramString) {
            this.name = paramString;
        }

        public void setPhoneCode(String paramString) {
            this.phoneCode = paramString;
        }
    }

    class CountryCodeJsonBean {
        private String cn;
        private String en;
        private String phone_code;

        CountryCodeJsonBean() {
        }

        public String getCn() {
            return this.cn;
        }

        public String getEn() {
            return this.en;
        }

        public String getPhone_code() {
            return this.phone_code;
        }

        public void setCn(String paramString) {
            this.cn = paramString;
        }

        public void setEn(String paramString) {
            this.en = paramString;
        }

        public void setPhone_code(String paramString) {
            this.phone_code = paramString;
        }
    }

    class QueryPhoneContactsAsync extends AsyncTask<Void, Void, List<CountryCodeBean>> {
        QueryPhoneContactsAsync() {
        }

        @Override
        protected List<CountryCodeBean> doInBackground(Void[] p) {
            ArrayList arrayList = new ArrayList();
            InputStream is = getResources().openRawResource(R.raw.country_code);

            CountryCodeJsonBean countryCodeJsonBean;
            try {
                List countryCodeList = GsonTools.changeGsonToList(IOUtils.readStreamAsString(is, "UTF-8"), CountryCodeJsonBean.class);
                if (countryCodeList != null) {
                    boolean isChinese = isZh();
                    Iterator iterator = countryCodeList.iterator();
                    while (iterator.hasNext()) {
                        String name;
                        countryCodeJsonBean = ( CountryCodeJsonBean) iterator.next();
                        CountryCodeBean countryCodeBean = new CountryCodeBean();
                        String code = countryCodeJsonBean.getPhone_code();
                        if (!isChinese)
                            name = countryCodeJsonBean.getEn();
                        else
                            name = countryCodeJsonBean.getCn();

                        countryCodeBean.setName(name);
                        countryCodeBean.setPhoneCode(code);

                        arrayList.add(countryCodeBean);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrayList;
        }

        @Override
        protected void onPostExecute( List<CountryCodeBean> countryCodeBeanList ) {
            mSourceDateList = filledData(countryCodeBeanList );
            Collections.sort( mSourceDateList, mPinyinComparator);
            mDataAdapter = new SortAdapter(CountryCodeActivity.this, mSourceDateList );
            mSortListView.setAdapter(mDataAdapter);
        }
    }

}
