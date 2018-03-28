package com.duoker.watch.repository;

import android.text.TextUtils;

import com.duoker.watch.db.model.CardNumberBean;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.utils.GsonTools;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by chengang on 4/30/2017.
 */

// 手表通讯录相关设置
public class CardNumberRepositoryImpl implements CardNumberRepository {
    @Override
    public List<CardNumberBean> getCardNumber(String userid, String watchid) throws IOException {

        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return null;

        FormBody body = new FormBody.Builder().add("action", "getWatchContacts").add("userid", userid).add("watchid", watchid).add("token", "").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").addHeader("Connection", "close").post(body).build();

        OkHttpClient httpClient = new OkHttpClient();
        ResponseBody responseBody = httpClient.newCall(request).execute().body();
        // ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String jsonstr = responseBody.string();
        responseBody.close();

        //String jsonstr = "[{ \"cardname\":\"cg\", \"cardnum\":\"13911111111\", \"cardid\": \"1\", \"cardIsChecked\": 1, \"cardtype\":1, \"cardshortnum\": \"1\" }, " +
        //        "{ \"cardname\":\"cg2\", \"cardnum\":\"13611112222\", \"cardid\": \"2\", \"cardIsChecked\": 1, \"cardtype\":2, \"cardshortnum\": \"2\" }," +
        //       "{ \"cardname\":\"cg3\", \"cardnum\":\"13611113333\", \"cardid\": \"3\", \"cardIsChecked\": 1, \"cardtype\":2, \"cardshortnum\": \"3\" }," +
        //       "{ \"cardname\":\"cg4\", \"cardnum\":\"13611114444\", \"cardid\": \"4\", \"cardIsChecked\": 1, \"cardtype\":3, \"cardshortnum\": \"4\" }]";

        List<CardNumberBean> list = GsonTools.changeGsonToList(jsonstr, CardNumberBean.class);

        return list;
    }

    @Override
    public void saveAll(String userid, String watchid, List<CardNumberBean> cardNumberList) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)) || (cardNumberList == null))
            return;

        String jsonstr = "[{ \"cardname\":\"cg\", \"cardnum\":\"13911111111\", \"cardid\": \"1\", \"cardIsChecked\": 1, \"cardtype\":1, \"cardshortnum\": \"1\" }, " +
                "{ \"cardname\":\"cg2\", \"cardnum\":\"13611112222\", \"cardid\": \"2\", \"cardIsChecked\": 1, \"cardtype\":2, \"cardshortnum\": \"2\" }," +
               "{ \"cardname\":\"cg3\", \"cardnum\":\"13611113333\", \"cardid\": \"3\", \"cardIsChecked\": 1, \"cardtype\":2, \"cardshortnum\": \"3\" }," +
               "{ \"cardname\":\"cg4\", \"cardnum\":\"13611114444\", \"cardid\": \"4\", \"cardIsChecked\": 1, \"cardtype\":3, \"cardshortnum\": \"4\" }]";

        cardNumberList = GsonTools.changeGsonToList(jsonstr, CardNumberBean.class);

        int size = cardNumberList.size();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("action", "saveWatchContacts").
                add("userid", userid).
                add("watchid", watchid).
                add("totalnum", Integer.toString(size)).
                add("token", "");
        String json = GsonTools.createGsonString(cardNumberList);
        builder.add("data", json);
        FormBody requestBody = builder.build();
        Request request = new Request.Builder().addHeader("Connection", "Close").url(L25Constants.SERVER_ADDRESS + "/app.php").post(requestBody).build();

        ResponseBody responseBody =  new OkHttpClient().newCall(request).execute().body();
        responseBody.close();
    }
}
