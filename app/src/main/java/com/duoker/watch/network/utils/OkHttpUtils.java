package com.duoker.watch.network.utils;

/**
 * Created by chengang on 4/25/2017.
 */


import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class OkHttpUtils
{
    private static final String TAG = OkHttpUtils.class.getSimpleName();
    private static String certificates = "-----BEGIN CERTIFICATE-----\nMIICpzCCAhACCQC6jK7qA6AB2zANBgkqhkiG9w0BAQUFADCBlzELMAkGA1UEBhMC\nY24xCzAJBgNVBAgMAmdkMQswCQYDVQQHDAJzejEQMA4GA1UECgwHbG9uZ2NvczEQ\nMA4GA1UECwwHbG9uZ2NvczEdMBsGA1UEAwwUdjJodHRwcy5odWJhb3hpbmcuY24x\nKzApBgkqhkiG9w0BCQEWHGxvbmdjb3NAdjJodHRwcy5odWJhb3hpbmcuY24wHhcN\nMTYwODE5MDg1NjI1WhcNMjYwODE3MDg1NjI1WjCBlzELMAkGA1UEBhMCY24xCzAJ\nBgNVBAgMAmdkMQswCQYDVQQHDAJzejEQMA4GA1UECgwHbG9uZ2NvczEQMA4GA1UE\nCwwHbG9uZ2NvczEdMBsGA1UEAwwUdjJodHRwcy5odWJhb3hpbmcuY24xKzApBgkq\nhkiG9w0BCQEWHGxvbmdjb3NAdjJodHRwcy5odWJhb3hpbmcuY24wgZ8wDQYJKoZI\nhvcNAQEBBQADgY0AMIGJAoGBANWxmokuK2yPORSBBfE2XUskpCbD55/O4raZafiH\nNukTKymuBWQix+w6a0EodtkWolOgDBXHQkO+h1gpPfs70KnMCA1JUpn0qUQNvU3d\nNCYGPdv2mWAooVcicE4mY7mHnbgAzcKmWCO5uUFAkZ+N241Whn6i/7GToa3lHfXA\nm01lAgMBAAEwDQYJKoZIhvcNAQEFBQADgYEAsq4Jak+K2paVA9NjPAaMJQpDGZcO\ndrxDGVeOHddVwxQkqd1NCUvkEWRzQHSA6pa8qdJJX0PRRGpv3z7rJZTs0MxRcUDa\nSw7AsiL1epfQmSzPMwdfsF+yqb8n5tN1thcD9gsApvwlSicJE45PYO6RjxrLp+iP\nVFpnKj9z4AMVl5E=\n-----END CERTIFICATE-----";
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;

    private OkHttpUtils()
    {
        if (this.mOkHttpClient == null)
        {
            InputStream[] arrayOfInputStream = new InputStream[1];
            arrayOfInputStream[0] = new Buffer().writeUtf8(certificates).inputStream();
            // HttpsUtils.SSLParams localSSLParams = HttpsUtils.getSslSocketFactory(arrayOfInputStream, null, null);
            // new NoSSLv3SocketFactory(localSSLParams.sSLSocketFactory);
            this.mOkHttpClient = new OkHttpClient(); //  new OkHttpClient.Builder().sslSocketFactory(localSSLParams.sSLSocketFactory, localSSLParams.trustManager).build();
        }
    }

    public static OkHttpUtils getInstance()
    {
        if (mInstance == null);
        try
        {
            if (mInstance == null)
                mInstance = new OkHttpUtils();
            return mInstance;
        }
        finally
        {
        }
    }

    public ResponseBody enqueue(Request request) throws IOException
    {
        Response response;
        try
        {
            response = mOkHttpClient.newCall(request).execute();
            if (response == null)
                return null;
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
            throw localIOException;
        }
        if (response.isSuccessful())
            return response.body();

        response.body().close();
        throw new IOException("Unexpected code " + response);
    }

    public Response enqueueReturnResp(Request paramRequest)
            throws IOException
    {
        try
        {
            Response localResponse = this.mOkHttpClient.newCall(paramRequest).execute();
            return localResponse;
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
            throw localIOException;
        }
    }

    public OkHttpClient getOkHttpClient()
    {
        return this.mOkHttpClient;
    }
}