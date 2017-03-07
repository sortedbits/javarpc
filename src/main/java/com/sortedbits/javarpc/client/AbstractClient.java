package com.sortedbits.javarpc.client;

import com.sortedbits.javarpc.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AbstractClient<I, O> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private ClientConfig config;
    private Channel<I, O> channel;

    public AbstractClient() {
        this("client.conf");
    }

    public AbstractClient(String configName) {
        this.config = ClientConfig.load(configName);
    }

    public synchronized I submit(O req) throws IOException {
        try {
            Channel<I, O> channel = getChannel(config);
            channel.write(req);
            return channel.read();
        } catch (IOException e) {
            closeChannel();
            throw e;
        }
    }

    protected abstract Channel<I, O> getChannel(ClientConfig config) throws IOException;

    protected abstract  void closeChannel();

}
