package com.duoker.watch.network;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class SocketThread extends Thread {
    private String ip = "139.196.226.71";
    private int port = 8282;
    private int timeout = 0;
    private String TAG = "socket thread";
    private String TAG1 = "===Send===";

    public Socket socket = null;
    PrintWriter out;
    BufferedReader in;
    Handler recvHandler;
    SharedPreferences sp;
    public boolean isRun = true;

    public SocketThread(Handler recvHandler ) {
        this.recvHandler = recvHandler;
        Log.i(TAG, "创建socket线程");
    }

    /**
     * 连接socket服务器
     */
    public int connect()
    {
        Log.i(TAG, "连接中……");
        SocketAddress socketAddress = new InetSocketAddress(ip,port); //获取sockaddress对象

        try {
            socket = new Socket();
            socket.setKeepAlive(true);
            socket.connect(socketAddress, 3000);
            socket.setSoTimeout(timeout);// 设置阻塞时间
            in = new BufferedReader(new InputStreamReader( socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter( socket.getOutputStream())), true);
            Log.i(TAG, "连接成功");
            Log.i(TAG, "输入输出流获取成功");
        } catch (UnknownHostException e) {
            Log.i(TAG, "连接错误!UnknownHostException");
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            Log.i(TAG, "连接服务器失败");
            return 0;
        } catch (Exception e) {
            Log.i(TAG, "连接服务器失败!Exception" + e.getMessage());
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public void TryConnect() {
        while(connect() == 0)
        {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实时接受数据
     */
    @Override
    public void run() {
        Log.i(TAG, "线程socket开始运行");
        TryConnect();
        String line = "";
        while (isRun) {
            try {
                if (socket != null) {
                    while ((line = in.readLine()) != null) {
                        Log.i(TAG, "readLine: " + line + ", len=" + line.length());

                        Message msg = recvHandler.obtainMessage();
                        msg.obj = line;
                        recvHandler.sendMessage(msg);// 结果返回给UI处理
                        Log.i(TAG1, " send to handler");
                    }
                    if(line == null)
                    {
                        Log.i(TAG, "连接断开！正在重新连接...");
                        sleep(1000);
                        TryConnect();
                    }
                } else {
                    Log.i(TAG, "没有可用连接，正在重新连接...");
                    sleep(1000);
                    TryConnect();
                }
            } catch (Exception e) {
                Log.i(TAG, "数据接收错误" + e.getMessage());
               // e.printStackTrace();
            }
        }
    }

    /**
     * 发送数据
     *
     * @param message
     */
    public int Send(String message) {
        try {
            if (socket != null) {
                Log.i(TAG1, "发送" + message + "至" + socket.getInetAddress().getHostAddress() + ":"  + String.valueOf(socket.getPort()));
                out.println(message);
                out.flush();
                Log.i(TAG1, "发送成功");
                return 1;
              //  Message msg = outHandler.obtainMessage();
              //  msg.obj = message;
               // msg.what = 1;
               // outHandler.sendMessage(msg);// 结果返回给UI处理
            } else {
                Log.i(TAG, "socket 不存在,发送失败");
                return 0;
            }
        } catch (Exception e) {
            Log.i(TAG1, "发送失败Exception");
            return 0;
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            if (socket != null) {
                Log.i(TAG, "close in");
                in.close();
                Log.i(TAG, "close out");
                out.close();
                Log.i(TAG, "close socket");
                socket.close();
            }
        } catch (Exception e) {
            Log.i(TAG, "close err");
            e.printStackTrace();
        }

    }
}