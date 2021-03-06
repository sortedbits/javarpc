package com.sortedbits.javarpc.client;


import com.sortedbits.javarpc.channels.GenericChannel;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;

import static org.apache.commons.io.IOUtils.closeQuietly;

public abstract class AbstractSocketClient<I, O> extends AbstractClient<I, O> {

    protected GenericChannel<I, O> channel;

    public AbstractSocketClient() {
        super();
    }

    public AbstractSocketClient(String configName) {
        super(configName);
    }

    @Override
    protected GenericChannel<I, O> getChannel(ClientConfig config) throws IOException {
        if (channel == null) {
            channel = createChannel(config);
        }
        return channel;
    }

    protected GenericChannel<I, O> createChannel(ClientConfig config) throws IOException {
        Socket socket = null;
        if (!config.getSocketSSL()) {
            socket = new Socket(config.getServerAddr(), config.getPort());
        } else {
            logger.info("SSL socket enabled");
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            socket = factory.createSocket(config.getServerAddr(), config.getPort());
        }
        socket.setSoTimeout(config.getSocketTimeout());
        return createSocketChannel(socket);
    }

    protected abstract GenericChannel<I,O> createSocketChannel(Socket socket) throws IOException;

    @Override
    protected void closeChannel() {
        closeQuietly(channel);
        channel = null;
    }
}


/*
    protected abstract GenericChannel<I, O> createChannel(Socket socket) throws IOException;

    private GenericChannel<I, O> getChannel(ClientConfig config) throws IOException {
        if (channel == null) {
            Socket socket = new Socket(config.getServerAddr(), config.getPort());
            socket.setSoTimeout(config.getSocketTimeout());
            channel = createChannel(socket);
        }
        return channel;
    }
 */