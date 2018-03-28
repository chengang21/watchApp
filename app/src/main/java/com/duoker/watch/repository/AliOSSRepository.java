package com.duoker.watch.repository;

/**
 * Created by chengang on 5/9/2017.
 */

public abstract interface AliOSSRepository {
    public abstract String downloadAudio(String url) throws Exception;

    public abstract String downloadAvatar(String url) throws Exception;

    public abstract String downloadFile(String url, String url2) throws Exception;

    public abstract String uploadAudio(String url)  throws Exception;

    public abstract String uploadAvatar(String url)  throws Exception;

    public abstract String uploadFile(String url, String url2) throws Exception;
}