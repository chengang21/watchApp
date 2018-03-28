package com.duoker.watch.interactors.base;

/**
 * Created by chengang on 4/30/2017.
 */


public class SimpleErrorBundle
        implements ErrorBundle
{
    private static final String DEFAULT_ERROR_MSG = "Unknown error";
    private final Exception exception;

    public SimpleErrorBundle(Exception paramException)
    {
        this.exception = paramException;
    }

    public String getErrorMessage()
    {
        if (this.exception != null)
            return this.exception.getMessage();
        return "Unknown error";
    }

    public Exception getException()
    {
        return this.exception;
    }
}
