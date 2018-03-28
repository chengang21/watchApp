package com.duoker.watch.network;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;


import com.duoker.watch.ui.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PushService extends Service {

    private String TAG = "===Client===";
    private String TAG1 = "===Send===";
    String secret = "123456";
    Handler sockRecvHandler;
    SocketThread socketThread;
    NotificationManager manager;
    private Callback callback = null;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onData(JSONObject data) throws JSONException;
    }

    private socketBinder mBinder = new socketBinder();

    public class socketBinder extends Binder {

        public PushService getService() {
            return PushService.this;
        }

        public int register(String appid, String name, String password){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "register");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("name", name);
                sendjsonObject.put("password", password);
                sign = md5(md5("action=register"+"&appid="+appid+"&name="+name+"&password="
                        +password)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            return socketThread.Send(sendjsonObject.toString());
        }

        public int login(String appid, String name, String password){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "login");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("name", name);
                sendjsonObject.put("password", password);
                sign = md5(md5("action=login"+"&appid="+appid+"&name="+name+"&password="+password)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            return socketThread.Send(sendjsonObject.toString());
        }


        public void addWatch(String appid, String watchId, String pwd){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "addWatch");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("watchid", watchId);
                sendjsonObject.put("pwd", pwd);
                sign = md5(md5("action=addWatch"+"&appid="+appid+"&pwd="+pwd+"&secret="
                        +secret+"&watchId="+watchId)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

        public void delWatch(String appid, String watchId){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "delWatch");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("watchid", watchId);
                sign = md5(md5("action=delWatch"+"&appid="+appid+"&secret="
                        +secret+"&watchId="+watchId)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

        public void getWatchList(String appid){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "getWatchList");
                sendjsonObject.put("appid", appid);
                sign = md5(md5("action=getWatchList"+"&appid="+appid+"&secret="
                        +secret)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

        public void editWatch(String appid, String watchId, String nickname,
                              String sex, String birthyear, String height, String weight){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "editWatch");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("watchid", watchId);
                sendjsonObject.put("nickname", nickname);
                sendjsonObject.put("sex", sex);
                sendjsonObject.put("birthyear", birthyear);
                sendjsonObject.put("height", height);
                sendjsonObject.put("weight", weight);
                sign = md5(md5("action=editWatch"+"&appid="+appid+"&birthyear="+birthyear
                        +"&height="+height+"&nickname="+nickname+"&secret="+secret
                        +"&sex="+sex+"&watchId="+watchId+"&weight="+weight)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

        public void setAlertMode(String appid, String watchId, String alertmode){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "setAlertMode");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("watchid", watchId);
                sendjsonObject.put("alertmode", alertmode);
                sign = md5(md5("action=setAlertMode"+"&alertmode="+alertmode+
                        "&appid="+appid+"&secret="+secret+"&watchId="+watchId)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

        public void setWatchVolume(String appid, String watchId, String level){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "setWatchVolume");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("watchid", watchId);
                sendjsonObject.put("level", level);
                sign = md5(md5("action=setWatchVolume"+"&appid="+appid+
                        "&level="+level+"&secret="+secret+"&watchId="+watchId)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

        public void getLastGpsData(String appid, String watchId){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "getLastGpsData");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("watchid", watchId);
                sign = md5(md5("action=getLastGpsData"+"&appid="+appid+
                        "&secret="+secret+"&watchId="+watchId)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

        public void getGpsData(String appid, String watchId, String begintime,
                               String endtime, String start, String num){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "getGpsData");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("watchid", watchId);
                sendjsonObject.put("begintime", "begintime");
                sendjsonObject.put("endtime", endtime);
                sendjsonObject.put("start", start);
                sendjsonObject.put("num", num);
                sign = md5(md5("action=getGpsData"+"&appid="+appid+
                        "&begintime="+begintime+"&endtime="+endtime+
                        "&num="+num+"&secret="+secret+"&start="+start+"&watchId="+watchId)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

        public void getWalkCount(String appid, String watchId, String begintime, String endtime){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "getWalkCount");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("watchid", watchId);
                sendjsonObject.put("begintime", "begintime");
                sendjsonObject.put("endtime", endtime);
                sign = md5(md5("action=getWalkCount"+"&appid="+appid+
                        "&begintime="+begintime+"&endtime="+endtime+
                        "&secret="+secret+"&watchId="+watchId)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

        public void getHeartRate(String appid, String watchId, String begintime,
                                 String endtime){
            JSONObject sendjsonObject = new JSONObject();
            String sign;
            String curtime;
            curtime = String.valueOf(System.currentTimeMillis()/1000);
            try {
                sendjsonObject.put("action", "getHeartRate");
                sendjsonObject.put("appid", appid);
                sendjsonObject.put("watchid", watchId);
                sendjsonObject.put("begintime", "begintime");
                sendjsonObject.put("endtime", endtime);
                sign = md5(md5("action=getHeartRate"+"&appid="+appid+
                        "&begintime="+begintime+"&endtime="+endtime+
                        "&secret="+secret+"&watchId="+watchId)+"&t="+curtime);
                sendjsonObject.put("sign", sign);
                sendjsonObject.put("t", curtime);
            }catch (Exception ee) {
                Log.i(TAG, "JSONObject出现异常");
                ee.printStackTrace();
            }
            socketThread.Send(sendjsonObject.toString());
        }

    }

    public void HandleRecvMsg(String msg ) throws JSONException {
        String mtype = null;
        String watchId = null;
        int type = 0;
        JSONObject jsonObject = new JSONObject(msg);
        mtype = jsonObject.getString("mtype");
        switch (mtype)
        {
            case "login":
                if(jsonObject.getInt("retcode") == 0)
                    secret = jsonObject.getString("secret");
                break;
            case "2":
                watchId = jsonObject.getString("watchid");
                ShowNotify("手表"+watchId+"刚刚上线");
            break;
            case "100":
                watchId = jsonObject.getString("watchid");
                showBigTextNotify("手表"+watchId+"发送短信："+jsonObject.getString("msg"));
                break;
            case "101":
                watchId = jsonObject.getString("watchid");
                showBigTextNotify("手表"+watchId+"有紧急电话！\n号码是："+jsonObject.getString("phone"));
                break;
            case "200":
                watchId = jsonObject.getString("watchid");
                showBigTextNotify("手表"+watchId+"电量低！剩余电量："+jsonObject.getString("msg"));
                break;
            case "400":
                watchId = jsonObject.getString("watchid");
                type = jsonObject.getInt("type");
                if(type == 1)
                    showBigTextNotify("手表"+watchId+"进入"+jsonObject.getString("fname")+"!");
                if(type == 2)
                    showBigTextNotify("手表"+watchId+"离开"+jsonObject.getString("fname")+"!");
                break;
        }
        if(callback != null)callback.onData(jsonObject);
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public PushService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind( Intent intent )
    {
        boolean res = super.onUnbind( intent );
        Log.i(TAG, "PushService::onUnbind"  );

        return res;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sockRecvHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                try {
                    Log.i(TAG, "mhandler接收到msg=" + msg.what);
                    if (msg.obj != null) {
                        String s = msg.obj.toString();
                        HandleRecvMsg( s );
                        if (s.trim().length() > 0) {
                            Log.i(TAG, "mhandler接收到obj=" + s);
                        } else {
                            Log.i(TAG, "没有数据返回不更新");
                        }
                    }
                } catch (Exception ee) {
                    Log.i(TAG, "加载过程出现异常");
                    ee.printStackTrace();
                }
            }
        };

        socketThread = new SocketThread(sockRecvHandler);
        socketThread.start();

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void ShowNotify(String str)
    {
        Intent intent = new Intent(PushService.this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(PushService.this, 1, intent, 0);
        Notification builder = new Notification.Builder(PushService.this)
                .setAutoCancel(true)
                .setContentTitle("多格健康")
                .setContentText(str)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setLights(Color.GREEN,3000,3000)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        manager.notify(10, builder);
    }

    private void showBigTextNotify(String str) {
        Intent intent = new Intent(PushService.this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(PushService.this, 1, intent, 0);
        NotificationCompat.BigTextStyle textStyle = new NotificationCompat.BigTextStyle();
        textStyle.setBigContentTitle("多格健康")
                .setSummaryText("多格健康")
                .bigText(str);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.arrow_up_float))
                .setTicker(str).setContentInfo("contentInfo")
                .setContentTitle("多格健康")
                .setContentText(str)
                .setStyle(textStyle)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        manager.notify(3, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "PushService::onDestroy" );
        socketThread.isRun = false;
    }

}
