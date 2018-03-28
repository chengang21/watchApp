package com.duoker.watch.network.utils;

/**
 * Created by chengang on 4/25/2017.
 */


import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class HttpsUtils
{
    private static X509TrustManager chooseTrustManager(TrustManager[] paramArrayOfTrustManager)
    {
        int i = paramArrayOfTrustManager.length;
        for (int j = 0; j < i; j++)
        {
            TrustManager localTrustManager = paramArrayOfTrustManager[j];
            if ((localTrustManager instanceof X509TrustManager))
                return (X509TrustManager)localTrustManager;
        }
        return null;
    }

    public static SSLParams getSslSocketFactory(InputStream[] paramArrayOfInputStream, InputStream paramInputStream, String paramString)
    {
        return  null;
//        SSLParams localSSLParams = new SSLParams();
//        try
//        {
//            TrustManager[] arrayOfTrustManager = prepareTrustManager(paramArrayOfInputStream);
//            KeyManager[] arrayOfKeyManager = prepareKeyManager(paramInputStream, paramString);
//            SSLContext localSSLContext = SSLContext.getInstance("TLS");
//            if (arrayOfTrustManager != null);
//            for (Object localObject = new MyTrustManager(chooseTrustManager(arrayOfTrustManager)); ; localObject = new UnSafeTrustManager(null))
//            {
//                localSSLContext.init(arrayOfKeyManager, new TrustManager[] { localObject }, null);
//                localSSLParams.sSLSocketFactory = localSSLContext.getSocketFactory();
//                localSSLParams.trustManager = ((X509TrustManager)localObject);
//                return localSSLParams;
//            }
//        }
//        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
//        {
//            throw new AssertionError(localNoSuchAlgorithmException);
//        }
//        catch (KeyManagementException localKeyManagementException)
//        {
//            throw new AssertionError(localKeyManagementException);
//        }
//        catch (KeyStoreException localKeyStoreException)
//        {
//            throw new AssertionError(localKeyStoreException);
//        }
    }

    private static KeyManager[] prepareKeyManager(InputStream paramInputStream, String paramString)
    {
        if ((paramInputStream == null) || (paramString == null))
            return null;
        try
        {
            KeyStore localKeyStore = KeyStore.getInstance("BKS");
            localKeyStore.load(paramInputStream, paramString.toCharArray());
            KeyManagerFactory localKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            localKeyManagerFactory.init(localKeyStore, paramString.toCharArray());
            KeyManager[] arrayOfKeyManager = localKeyManagerFactory.getKeyManagers();
            return arrayOfKeyManager;
        }
        catch (KeyStoreException localKeyStoreException)
        {
            localKeyStoreException.printStackTrace();
            return null;
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
            localNoSuchAlgorithmException.printStackTrace();
            return null;
        }
        catch (UnrecoverableKeyException localUnrecoverableKeyException)
        {
            localUnrecoverableKeyException.printStackTrace();
            return null;
        }
        catch (CertificateException localCertificateException)
        {
            localCertificateException.printStackTrace();
            return null;
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
            return null;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }

    // ERROR //
    private static TrustManager[] prepareTrustManager(InputStream[] paramArrayOfInputStream)
    {
        return null;
    }

    private static class MyTrustManager
            implements X509TrustManager
    {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager localTrustManager;

        public MyTrustManager(X509TrustManager paramX509TrustManager)
                throws NoSuchAlgorithmException, KeyStoreException
        {
            TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            localTrustManagerFactory.init((KeyStore)null);
            this.defaultTrustManager = HttpsUtils.chooseTrustManager(localTrustManagerFactory.getTrustManagers());
            this.localTrustManager = paramX509TrustManager;
        }

        public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                throws CertificateException
        {
        }

        public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                throws CertificateException
        {
            try
            {
                this.defaultTrustManager.checkServerTrusted(paramArrayOfX509Certificate, paramString);
                return;
            }
            catch (CertificateException localCertificateException)
            {
                this.localTrustManager.checkServerTrusted(paramArrayOfX509Certificate, paramString);
            }
        }

        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[0];
        }
    }

    public static class SSLParams
    {
        public SSLSocketFactory sSLSocketFactory;
        public X509TrustManager trustManager;
    }

    private class UnSafeHostnameVerifier
            implements HostnameVerifier
    {
        private UnSafeHostnameVerifier()
        {
        }

        public boolean verify(String paramString, SSLSession paramSSLSession)
        {
            return true;
        }
    }

    private static class UnSafeTrustManager
            implements X509TrustManager
    {
        public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                throws CertificateException
        {
        }

        public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                throws CertificateException
        {
        }

        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[0];
        }
    }
}