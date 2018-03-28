package com.duoker.watch.network.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by chengang on 4/25/2017.
 */

public class NoSSLv3SocketFactory extends SSLSocketFactory
{
    private final SSLSocketFactory delegate;

    public NoSSLv3SocketFactory()
    {
        this.delegate = HttpsURLConnection.getDefaultSSLSocketFactory();
    }

    public NoSSLv3SocketFactory(SSLSocketFactory paramSSLSocketFactory)
    {
        this.delegate = paramSSLSocketFactory;
    }

    private Socket makeSocketSafe(Socket paramSocket)
    {
//        if ((paramSocket instanceof SSLSocket))
//            paramSocket = new NoSSLv3SSLSocket((SSLSocket)paramSocket, null);
//        return paramSocket;
        return null;
    }

    public Socket createSocket(String paramString, int paramInt)
            throws IOException
    {
        return makeSocketSafe(this.delegate.createSocket(paramString, paramInt));
    }

    public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
            throws IOException
    {
        return makeSocketSafe(this.delegate.createSocket(paramString, paramInt1, paramInetAddress, paramInt2));
    }

    public Socket createSocket(InetAddress paramInetAddress, int paramInt)
            throws IOException
    {
        return makeSocketSafe(this.delegate.createSocket(paramInetAddress, paramInt));
    }

    public Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
            throws IOException
    {
        return makeSocketSafe(this.delegate.createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2));
    }

    public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
            throws IOException
    {
        return makeSocketSafe(this.delegate.createSocket(paramSocket, paramString, paramInt, paramBoolean));
    }

    public String[] getDefaultCipherSuites()
    {
        return this.delegate.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites()
    {
        return this.delegate.getSupportedCipherSuites();
    }

    public class DelegateSSLSocket extends SSLSocket
    {
        protected final SSLSocket delegate = null; //CG;

        DelegateSSLSocket(SSLSocket arg2)
        {

        }

        public void addHandshakeCompletedListener(HandshakeCompletedListener paramHandshakeCompletedListener)
        {
            this.delegate.addHandshakeCompletedListener(paramHandshakeCompletedListener);
        }

        public void bind(SocketAddress paramSocketAddress)
                throws IOException
        {
            this.delegate.bind(paramSocketAddress);
        }

        public void close()
                throws IOException
        {
//            try
//            {
//                this.delegate.close();
//                return;
//            }
//            finally
//            {
//                localObject = finally;
//                throw localObject;
//            }
        }

        public void connect(SocketAddress paramSocketAddress)
                throws IOException
        {
            this.delegate.connect(paramSocketAddress);
        }

        public void connect(SocketAddress paramSocketAddress, int paramInt)
                throws IOException
        {
            this.delegate.connect(paramSocketAddress, paramInt);
        }

        public boolean equals(Object paramObject)
        {
            return this.delegate.equals(paramObject);
        }

        public SocketChannel getChannel()
        {
            return this.delegate.getChannel();
        }

        public boolean getEnableSessionCreation()
        {
            return this.delegate.getEnableSessionCreation();
        }

        public String[] getEnabledCipherSuites()
        {
            return this.delegate.getEnabledCipherSuites();
        }

        public String[] getEnabledProtocols()
        {
            return this.delegate.getEnabledProtocols();
        }

        public InetAddress getInetAddress()
        {
            return this.delegate.getInetAddress();
        }

        public InputStream getInputStream()
                throws IOException
        {
            return this.delegate.getInputStream();
        }

        public boolean getKeepAlive()
                throws SocketException
        {
            return this.delegate.getKeepAlive();
        }

        public InetAddress getLocalAddress()
        {
            return this.delegate.getLocalAddress();
        }

        public int getLocalPort()
        {
            return this.delegate.getLocalPort();
        }

        public SocketAddress getLocalSocketAddress()
        {
            return this.delegate.getLocalSocketAddress();
        }

        public boolean getNeedClientAuth()
        {
            return this.delegate.getNeedClientAuth();
        }

        public boolean getOOBInline()
                throws SocketException
        {
            return this.delegate.getOOBInline();
        }

        public OutputStream getOutputStream()
                throws IOException
        {
            return this.delegate.getOutputStream();
        }

        public int getPort()
        {
            return this.delegate.getPort();
        }

        public int getReceiveBufferSize()
                throws SocketException
        {


                int i = this.delegate.getReceiveBufferSize();
                return i;

        }

        public SocketAddress getRemoteSocketAddress()
        {
            return this.delegate.getRemoteSocketAddress();
        }

        public boolean getReuseAddress()
                throws SocketException
        {
            return this.delegate.getReuseAddress();
        }

        public int getSendBufferSize()
                throws SocketException
        {
            int i = this.delegate.getSendBufferSize();
            return i;
        }

        public SSLSession getSession()
        {
            return this.delegate.getSession();
        }

        public int getSoLinger()
                throws SocketException
        {
            return this.delegate.getSoLinger();
        }

        public int getSoTimeout()
                throws SocketException
        {
            int i = this.delegate.getSoTimeout();
            return i;
        }

        public String[] getSupportedCipherSuites()
        {
            return this.delegate.getSupportedCipherSuites();
        }

        public String[] getSupportedProtocols()
        {
            return this.delegate.getSupportedProtocols();
        }

        public boolean getTcpNoDelay()
                throws SocketException
        {
            return this.delegate.getTcpNoDelay();
        }

        public int getTrafficClass()
                throws SocketException
        {
            return this.delegate.getTrafficClass();
        }

        public boolean getUseClientMode()
        {
            return this.delegate.getUseClientMode();
        }

        public boolean getWantClientAuth()
        {
            return this.delegate.getWantClientAuth();
        }

        public boolean isBound()
        {
            return this.delegate.isBound();
        }

        public boolean isClosed()
        {
            return this.delegate.isClosed();
        }

        public boolean isConnected()
        {
            return this.delegate.isConnected();
        }

        public boolean isInputShutdown()
        {
            return this.delegate.isInputShutdown();
        }

        public boolean isOutputShutdown()
        {
            return this.delegate.isOutputShutdown();
        }

        public void removeHandshakeCompletedListener(HandshakeCompletedListener paramHandshakeCompletedListener)
        {
            this.delegate.removeHandshakeCompletedListener(paramHandshakeCompletedListener);
        }

        public void sendUrgentData(int paramInt)
                throws IOException
        {
            this.delegate.sendUrgentData(paramInt);
        }

        public void setEnableSessionCreation(boolean paramBoolean)
        {
            this.delegate.setEnableSessionCreation(paramBoolean);
        }

        public void setEnabledCipherSuites(String[] paramArrayOfString)
        {
            this.delegate.setEnabledCipherSuites(paramArrayOfString);
        }

        public void setEnabledProtocols(String[] paramArrayOfString)
        {
            this.delegate.setEnabledProtocols(paramArrayOfString);
        }

        public void setKeepAlive(boolean paramBoolean)
                throws SocketException
        {
            this.delegate.setKeepAlive(paramBoolean);
        }

        public void setNeedClientAuth(boolean paramBoolean)
        {
            this.delegate.setNeedClientAuth(paramBoolean);
        }

        public void setOOBInline(boolean paramBoolean)
                throws SocketException
        {
            this.delegate.setOOBInline(paramBoolean);
        }

        public void setPerformancePreferences(int paramInt1, int paramInt2, int paramInt3)
        {
            this.delegate.setPerformancePreferences(paramInt1, paramInt2, paramInt3);
        }

        public void setReceiveBufferSize(int paramInt)
                throws SocketException
        {

                this.delegate.setReceiveBufferSize(paramInt);

        }

        public void setReuseAddress(boolean paramBoolean)
                throws SocketException
        {
            this.delegate.setReuseAddress(paramBoolean);
        }

        public void setSendBufferSize(int paramInt)
                throws SocketException
        {

                this.delegate.setSendBufferSize(paramInt);
                return;

        }

        public void setSoLinger(boolean paramBoolean, int paramInt)
                throws SocketException
        {
            this.delegate.setSoLinger(paramBoolean, paramInt);
        }

        public void setSoTimeout(int paramInt)
                throws SocketException
        {
            this.delegate.setSoTimeout(paramInt);
            return;
        }

        public void setTcpNoDelay(boolean paramBoolean)
                throws SocketException
        {
            this.delegate.setTcpNoDelay(paramBoolean);
        }

        public void setTrafficClass(int paramInt)
                throws SocketException
        {
            this.delegate.setTrafficClass(paramInt);
        }

        public void setUseClientMode(boolean paramBoolean)
        {
            this.delegate.setUseClientMode(paramBoolean);
        }

        public void setWantClientAuth(boolean paramBoolean)
        {
            this.delegate.setWantClientAuth(paramBoolean);
        }

        public void shutdownInput()
                throws IOException
        {
            this.delegate.shutdownInput();
        }

        public void shutdownOutput()
                throws IOException
        {
            this.delegate.shutdownOutput();
        }

        public void startHandshake()
                throws IOException
        {
            this.delegate.startHandshake();
        }

        public String toString()
        {
            return this.delegate.toString();
        }
    }

    private class NoSSLv3SSLSocket extends NoSSLv3SocketFactory.DelegateSSLSocket
    {
        private NoSSLv3SSLSocket(SSLSocket arg2)
        {
            super(arg2);
        }

        public void setEnabledProtocols(String[] paramArrayOfString)
        {
//            ArrayList localArrayList;
//            if ((paramArrayOfString != null) && (paramArrayOfString.length == 1) && ("SSLv3".equals(paramArrayOfString[0])))
//            {
//                localArrayList = new ArrayList(Arrays.asList(this.delegate.getEnabledProtocols()));
//                if (localArrayList.size() <= 1)
//                    break label91;
//                localArrayList.remove("SSLv3");
//                System.out.println("Removed SSLv3 from enabled protocols");
//            }
//            while (true)
//            {
//                paramArrayOfString = (String[])localArrayList.toArray(new String[localArrayList.size()]);
//                super.setEnabledProtocols(paramArrayOfString);
//                return;
//                label91: System.out.println("SSL stuck with protocol available for " + String.valueOf(localArrayList));
//            }
        }
    }
}
