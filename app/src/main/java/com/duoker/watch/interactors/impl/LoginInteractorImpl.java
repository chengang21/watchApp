package com.duoker.watch.interactors.impl;

import android.content.Context;
import android.text.TextUtils;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.LoginInfoBean;
import com.duoker.watch.model.LoginModel;
import com.duoker.watch.model.UserBean;
import com.duoker.watch.model.WatchsBean;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.storage.LoginUserStorage;
import com.duoker.watch.storage.SetupInfoHelper;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.utils.GsonTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by cheng on 2017/9/3.
 */
public class LoginInteractorImpl extends SimpleInteractor<LoginInfoBean>
{
    private final LoginModel mLoginUser;
    private final Context mContext;

    public LoginInteractorImpl(LoginModel loginModel, Context ctx )
    {
        this.mLoginUser = loginModel;
        this.mContext = ctx;
    }

    public void run()
    {
        try
        {
            postObject2UiThread( login(this.mLoginUser) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }


    public LoginInfoBean login(LoginModel loginModel)  throws IOException
    {
        LoginInfoBean loginInfoBean;
        if (loginModel == null){
            DuokerWatchApp.getInstance().setWatches(null);
            DuokerWatchApp.getInstance().setDefaultWatch(null);
            DuokerWatchApp.getInstance().setDefaultWatchId(loginModel.getLoginUserName(), "");
            return null;
        }

        String userName = loginModel.getLoginUserName();
        String password = loginModel.getPassword();
        String token = "duoker"; // MD5Utils.getMD5(userName + password + "!QZZ12ewqrtdda8&097agf%$#");
        FormBody formBody = new FormBody.Builder().
                add("action", "login").
                add("phone", userName).
                add("pwd", password).
                add("token", token).
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String responseString = responseBody.string();
        responseBody.close();
        loginInfoBean = (LoginInfoBean) GsonTools.changeGsonToBean(responseString, LoginInfoBean.class);
        if ( loginInfoBean == null || loginInfoBean.getUser() == null ) {
            return null;
        }

        LoginUserStorage loginUserStorage = userBean2LoginUserStorage(loginInfoBean.getUser());
        loginUserStorage.setLoginUserName(loginModel.getLoginUserName());
        loginUserStorage.setPassword(loginModel.getPassword());
        DuokerWatchApp.getInstance().setLoginUser(loginUserStorage);

        List watchsStorageList = watchsBean2WatchesStorage(loginInfoBean.getWatchs());

        if ((watchsStorageList != null) && (!watchsStorageList.isEmpty()))
        {
            WatchsStorage[] arrayOfWatchsStorage = (WatchsStorage[])watchsStorageList.toArray(new WatchsStorage[watchsStorageList.size()]);
            DuokerWatchApp.getInstance().setWatches(arrayOfWatchsStorage);

            String defaultWatchId = DuokerWatchApp.getInstance().getDefaultWatchId(loginModel.getLoginUserName());
            Iterator iterator = watchsStorageList.iterator();

            boolean findDefault=false;
            for ( WatchsStorage watchs=(WatchsStorage)iterator.next(); iterator.hasNext(); watchs=(WatchsStorage)iterator.next() ) {
                if( TextUtils.equals(watchs.getWatchId(), defaultWatchId))
                {
                    DuokerWatchApp.getInstance().setDefaultWatch(watchs);
                    findDefault = true;
                    break;
                }
            }
            if (findDefault == false)
            {
                WatchsStorage defaultWatch = (WatchsStorage)watchsStorageList.get(0);
                DuokerWatchApp.getInstance().setDefaultWatch(defaultWatch);
                DuokerWatchApp.getInstance().setDefaultWatchId(loginUserStorage.getLoginUserName(), defaultWatch.getWatchId());
            }
        }

        SetupInfoHelper setupInfoHelper = new SetupInfoHelper(mContext);
        setupInfoHelper.saveLoginUserName(loginModel.getLoginUserName());
        setupInfoHelper.savePassword(loginModel.getPassword());
        return loginInfoBean;
    }


    public static LoginUserStorage userBean2LoginUserStorage(UserBean userBean)
    {
        if (userBean == null)
            return null;
        LoginUserStorage loginUserStorage = new LoginUserStorage();
        loginUserStorage.setUserid(userBean.getUserid());
        loginUserStorage.setSession(userBean.getSession());
        loginUserStorage.setUserName(userBean.getUserName());
        loginUserStorage.setUserSex(userBean.getUserSex());
        loginUserStorage.setBirthYear(userBean.getBirthYear());
        loginUserStorage.setHeadImage(userBean.getHeadImage());
        return loginUserStorage;
    }

    public static WatchsStorage watchsBean2WatchesStorage(WatchsBean watchsBean)
    {
        if (watchsBean == null)
            return null;
        WatchsStorage watchsStorage = new WatchsStorage();
        watchsStorage.setWatchId(watchsBean.getWatchId());
        watchsStorage.setOwner(watchsBean.getOwner());
        watchsStorage.setDevname(watchsBean.getDevname());
        watchsStorage.setDevdistance(watchsBean.getDevdistance());
        watchsStorage.setElectricity(watchsBean.getElectricity());
        watchsStorage.setHeadImage(watchsBean.getHeadImage());
        watchsStorage.setOnline(watchsBean.getOnline());
        watchsStorage.setPhoneIMS(watchsBean.getPhoneIMS());
        watchsStorage.setDevsex(watchsBean.getDevsex());
        watchsStorage.setRss(watchsBean.getRss());
        watchsStorage.setMcc(watchsBean.getMcc());
        watchsStorage.setMnc(watchsBean.getMnc());
        watchsStorage.setLac(watchsBean.getLac());
        watchsStorage.setCellID(watchsBean.getCellID());
        watchsStorage.setGpsQuality(watchsBean.getGpsQuality());
        watchsStorage.setCalcTime(watchsBean.getCalcTime());
        watchsStorage.setLongitude(watchsBean.getLongitude());
        watchsStorage.setLatitude(watchsBean.getLatitude());
        watchsStorage.setAddr(watchsBean.getAddr());
        watchsStorage.setAltitude(watchsBean.getAltitude());
        watchsStorage.setDirectionOffSet(watchsBean.getDirectionOffSet());
        watchsStorage.setDirectionSpeed(watchsBean.getDirectionSpeed());
        watchsStorage.setGroupId(watchsBean.getGroupId());
        watchsStorage.setProjectCate(watchsBean.getProjectCate());
        return watchsStorage;
    }

    public static List<WatchsStorage> watchsBean2WatchesStorage(List<WatchsBean> watchsBeanList)
    {
        List<WatchsStorage> list = null;
        if (watchsBeanList == null)
            return list;

        list = new ArrayList();
        Iterator iterator = watchsBeanList.iterator();
        while (iterator.hasNext())
            ((List)list).add(watchsBean2WatchesStorage((WatchsBean)iterator.next()));

        return list;
    }

    public static WatchsBean watchesStorage2WatchsBean(WatchsStorage watchsStorage)
    {
        if (watchsStorage == null)
            return null;

        WatchsBean watchsBean = new WatchsBean();
        watchsBean.setWatchId(watchsStorage.getWatchId());
        watchsBean.setOwner(watchsStorage.getOwner());
        watchsBean.setDevname(watchsStorage.getDevname());
        watchsBean.setDevdistance(watchsStorage.getDevdistance());
        watchsBean.setElectricity(watchsStorage.getElectricity());
        watchsBean.setHeadImage(watchsStorage.getHeadImage());
        watchsBean.setOnline(watchsStorage.getOnline());
        watchsBean.setPhoneIMS(watchsStorage.getPhoneIMS());
        watchsBean.setDevsex(watchsStorage.getDevsex());
        watchsBean.setRss(watchsStorage.getRss());
        watchsBean.setMcc(watchsStorage.getMcc());
        watchsBean.setMnc(watchsStorage.getMnc());
        watchsBean.setLac(watchsStorage.getLac());
        watchsBean.setCellID(watchsStorage.getCellID());
        watchsBean.setGpsQuality(watchsStorage.getGpsQuality());
        watchsBean.setCalcTime(watchsStorage.getCalcTime());
        watchsBean.setLongitude(watchsStorage.getLongitude());
        watchsBean.setLatitude(watchsStorage.getLatitude());
        watchsBean.setAddr(watchsStorage.getAddr());
        watchsBean.setAltitude(watchsStorage.getAltitude());
        watchsBean.setDirectionOffSet(watchsStorage.getDirectionOffSet());
        watchsBean.setDirectionSpeed(watchsStorage.getDirectionSpeed());
        watchsBean.setGroupId(watchsStorage.getGroupId());
        watchsBean.setProjectCate(watchsStorage.getProjectCate());
        return watchsBean;
    }

}