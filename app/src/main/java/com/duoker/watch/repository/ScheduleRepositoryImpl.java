package com.duoker.watch.repository;

import android.content.Context;
import android.text.TextUtils;

import com.duoker.watch.model.ScheduleBean;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.storage.UserSetupInfoHelper;
import com.duoker.watch.utils.GsonTools;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by cheng on 2017/10/6.
 */
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private static final String TAG = ScheduleRepositoryImpl.class.getSimpleName();
    private final Context mContext;
    private final UserSetupInfoHelper mSetupInfoHelper;

    public ScheduleRepositoryImpl(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
        this.mSetupInfoHelper = new UserSetupInfoHelper(this.mContext);
    }

    public ScheduleModel addSchedule(ScheduleModel scheduleModel) throws IOException {
        if (scheduleModel == null)
            return null;
        String userId = scheduleModel.getUserId();
        String watchId = scheduleModel.getWatchId();
        if ((TextUtils.isEmpty(userId)) || (TextUtils.isEmpty(watchId))) 
            return null;
        
        int repeat = scheduleModel.getSchedulerepeat();
        String content = scheduleModel.getSchedulecontent();
        String scheduletime = scheduleModel.getScheduletime();
        int scheduleweek = scheduleModel.getScheduleweek();
        
        FormBody formBody = new FormBody.Builder().
                add("action", "addchedule").
                add("userid", userId).
                add("watchid", watchId).
                add("schedulerepeat", Integer.toString(repeat)).
                add("schedulecontent", content).
                add("scheduletime", scheduletime).
                add("scheduleweek", Integer.toString(scheduleweek)).
                add("token", "token").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String resp = responseBody.string();
        responseBody.close();

        return (ScheduleModel) GsonTools.changeGsonToBean(resp, ScheduleModel.class);
    }

    public void delBatch(List<ScheduleModel> scheduleModelList) throws IOException {
        if ((scheduleModelList == null) || (scheduleModelList.isEmpty()))
            return;

        JsonArray jsonArray = new JsonArray();

        String userID = null;
        String watchID = null;
        String scheduleIdList = null;

        Iterator iterator = scheduleModelList.iterator();
        while (iterator.hasNext()) {
            ScheduleModel localScheduleModel = (ScheduleModel) iterator.next();
            userID = localScheduleModel.getUserId();
            watchID = localScheduleModel.getWatchId();
            String scheduleid = localScheduleModel.getScheduleid();

            try {
                jsonArray.add(new JsonPrimitive(Long.parseLong(scheduleid)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        scheduleIdList = jsonArray.toString();

        if ((TextUtils.isEmpty(userID)) || (TextUtils.isEmpty(watchID)) || (TextUtils.isEmpty(scheduleIdList)))
            return;

        FormBody formBody = new FormBody.Builder().
                add("action", "delschedule").
                add("userid", userID).
                add("watchid", watchID).
                add("scheduleid", scheduleIdList).
                add("token", "").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void delSchedule(ScheduleModel scheduleModel) throws IOException {
        if (scheduleModel == null)
            return;

        String userId = scheduleModel.getUserId();
        String watchId = scheduleModel.getWatchId();

        if ((TextUtils.isEmpty(userId)) || (TextUtils.isEmpty(watchId)))
            return;

        String scheduleid = scheduleModel.getScheduleid();
        FormBody formBody = new FormBody.Builder().
                add("action", "delschedule").
                add("userid", userId).
                add("watchid", watchId).
                add("scheduleid", scheduleid).
                add("token", "token").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();

        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void editSchedule(ScheduleModel scheduleModel) throws IOException {
        if (scheduleModel == null)
            return;

        String userId = scheduleModel.getUserId();
        String watchId = scheduleModel.getWatchId();
        String scheduleid = scheduleModel.getScheduleid();

        if ((TextUtils.isEmpty(userId)) || (TextUtils.isEmpty(watchId)) || (TextUtils.isEmpty(scheduleid)))
            return;

        int schedulerepeat = scheduleModel.getSchedulerepeat();
        String schedulecontent = scheduleModel.getSchedulecontent();
        String scheduletime = scheduleModel.getScheduletime();
        int j = scheduleModel.getScheduleweek();
        int k = scheduleModel.getSchedulestatus();
        FormBody formBody = new FormBody.Builder().
                add("action", "editchedule").
                add("userid", userId).
                add("watchid", watchId).
                add("scheduleid", scheduleid).
                add("schedulerepeat", Integer.toString(schedulerepeat)).
                add("schedulecontent", schedulecontent).
                add("scheduletime", scheduletime).
                add("scheduleweek", Integer.toString(j)).
                add("schedulestatus", Integer.toString(k)).add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    /**
     * @param userID
     * @param watchID
     * @return
     * @throws IOException [{
     *                     "schedulecontent" : "早晨8点上班",
     *                     "scheduleid" : "1",
     *                     "schedulerepeat" : "1",
     *                     "schedulesetnikname" : "cg",
     *                     "schedulesetphone" : "13916101696",
     *                     "schedulestatus" : "1",
     *                     "scheduletime" : "2017-09-30 12:12:12",
     *                     "scheduleweek" : "1"
     *                     },
     *                     {
     *                     "schedulecontent" : "早晨11点去银行",
     *                     "scheduleid" : "2",
     *                     "schedulerepeat" : "1",
     *                     "schedulesetnikname" : "cg",
     *                     "schedulesetphone" : "13916101696",
     *                     "schedulestatus" : "1",
     *                     "scheduletime" : "2017-09-30 12:12:12",
     *                     "scheduleweek" : "1"
     *                     }]
     */
    public List<ScheduleModel> getSchedules(String userID, String watchID) throws IOException {
        if ((TextUtils.isEmpty(userID)) || (TextUtils.isEmpty(watchID)))
            return null;

        FormBody localFormBody = new FormBody.Builder().
                add("action", "getschedule").
                add("userid", userID).
                add("watchid", watchID).
                add("token", "token").
                build();
        Request localRequest = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(localFormBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(localRequest);
        String resp = responseBody.string();
        responseBody.close();
        return scheduleBean2ScheduleStorage(userID, watchID, GsonTools.changeGsonToList(resp, ScheduleBean.class));
    }

    public boolean isAutoDelSchedule(String paramString) {
        return mSetupInfoHelper.isDelOutTimeSchedule(paramString);
    }

    public void operateSchedule(ScheduleModel scheduleModel)
            throws IOException {
        if (scheduleModel == null)
            return;

        String userId = scheduleModel.getUserId();
        String watchId = scheduleModel.getWatchId();
        String scheduleid = scheduleModel.getScheduleid();

        if ((TextUtils.isEmpty(userId)) || (TextUtils.isEmpty(watchId)) || (TextUtils.isEmpty(scheduleid)))
            return;

        int i = scheduleModel.getSchedulestatus();
        FormBody localFormBody = new FormBody.Builder().
                add("action", "operatechedule").
                add("userid", userId).
                add("watchid", watchId).
                add("scheduleid", scheduleid).
                add("schedulestatus", Integer.toString(i)).
                add("token", "").build();
        Request localRequest = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(localFormBody).build();
        OkHttpUtils.getInstance().enqueue(localRequest).close();
    }

    public static ScheduleModel scheduleBean2ScheduleStorage(String userID, String watchID, ScheduleBean scheduleBean) {
        if (scheduleBean == null)
            return null;

        ScheduleModel scheduleModel = new ScheduleModel();
        scheduleModel.setUserId(userID);
        scheduleModel.setWatchId(watchID);
        scheduleModel.setScheduleid(scheduleBean.getScheduleid());
        scheduleModel.setSchedulerepeat(scheduleBean.getSchedulerepeat());
        scheduleModel.setSchedulecontent(scheduleBean.getSchedulecontent());
        scheduleModel.setScheduletime(scheduleBean.getScheduletime());
        scheduleModel.setScheduleweek(scheduleBean.getScheduleweek());
        scheduleModel.setSchedulestatus(scheduleBean.getSchedulestatus());
        scheduleModel.setSchedulesetphone(scheduleBean.getSchedulesetphone());
        scheduleModel.setSchedulesetnikname(scheduleBean.getSchedulesetnikname());
        return scheduleModel;
    }

    public static List<ScheduleModel> scheduleBean2ScheduleStorage(String paramString1, String paramString2, List<ScheduleBean> beanList) {
        List list = null;
        if (beanList == null)
            return list;

        list = new ArrayList();
        for (int i = 0; i < beanList.size(); i++)
            ((List) list).add(scheduleBean2ScheduleStorage(paramString1, paramString2, (ScheduleBean) beanList.get(i)));

        return list;
    }

}
