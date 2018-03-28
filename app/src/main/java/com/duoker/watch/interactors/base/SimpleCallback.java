package com.duoker.watch.interactors.base;

/**
 * Created by chengang on 4/30/2017.
 */

public interface SimpleCallback<T> {
    void onSuccess(T paramT);
    void onError(SimpleErrorBundle errorBundle);
}