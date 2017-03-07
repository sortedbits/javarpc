package com.sortedbits.remoting;

import java.io.IOException;
import java.net.Socket;

import static org.apache.commons.io.IOUtils.closeQuietly;

public abstract class AbstractClient<I, O> {

    private ClientConfig config;
    private Channel<I, O> channel;

    public AbstractClient() {
        this("client.conf");
    }

    public AbstractClient(String configName) {
        this.config = ClientConfig.load(configName);
    }

    protected abstract Channel<I, O> createChannel(Socket socket) throws IOException;

    public synchronized I submit(O req) throws IOException {
        try {
            channel = getChannel();
            channel.write(req);
            return channel.read();
        } catch (IOException e) {
            closeQuietly(channel);
            channel = null;
            throw e;
        }
    }

    private Channel<I, O> getChannel() throws IOException {
        if (channel == null) {
            Socket socket = new Socket(config.getServerAddr(), config.getPort());
            socket.setSoTimeout(config.getSocketTimeout());
            channel = createChannel(socket);
        }
        return channel;
    }
}
