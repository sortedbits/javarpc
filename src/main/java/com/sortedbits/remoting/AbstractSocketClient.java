package com.sortedbits.remoting;


import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;

import static org.apache.commons.io.IOUtils.closeQuietly;

public abstract class AbstractSocketClient<I, O> extends AbstractClient<I, O> {

    protected Channel<I, O> channel;

    public AbstractSocketClient() {
        super();
    }

    public AbstractSocketClient(String configName) {
        super(configName);
    }

    @Override
    protected Channel<I, O> getChannel(ClientConfig config) throws IOException {
        if (channel == null) {
            channel = createChannel(config);
        }
        return channel;
    }

    protected Channel<I, O> createChannel(ClientConfig config) throws IOException {
        Socket socket = null;
        if (!config.getSocketSSL()) {
            socket = new Socket(config.getServerAddr(), config.getPort());
        } else {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            socket = factory.createSocket(config.getServerAddr(), config.getPort());
        }
        socket.setSoTimeout(config.getSocketTimeout());
        return createSocketChannel(socket);
    }

    protected abstract Channel<I,O> createSocketChannel(Socket socket) throws IOException;

    @Override
    protected void closeChannel() {
        closeQuietly(channel);
        channel = null;
    }
}


/*
    protected abstract Channel<I, O> createChannel(Socket socket) throws IOException;

    private Channel<I, O> getChannel(ClientConfig config) throws IOException {
        if (channel == null) {
            Socket socket = new Socket(config.getServerAddr(), config.getPort());
            socket.setSoTimeout(config.getSocketTimeout());
            channel = createChannel(socket);
        }
        return channel;
    }
 */