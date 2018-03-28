package com.duoker.watch.interactors.base;

/**
 * Created by chengang on 4/30/2017.
 */

public abstract interface ErrorBundle
{
    public abstract String getErrorMessage();

    public abstract Exception getException();
}