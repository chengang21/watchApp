package com.duoker.watch.db;

/**
 * Created by chengang on 5/9/2017.
 */

public class MyDbException extends Exception
{
    private static final long serialVersionUID = 1L;

    public MyDbException()
    {
    }

    public MyDbException(String msg)
    {
        super(msg);
    }

    public MyDbException(String paramString, Throwable throwable)
    {
        super(paramString, throwable);
    }

    public MyDbException(Throwable throwable)
    {
        super(throwable);
    }
}