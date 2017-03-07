package com.sortedbits.javarpc.server;

import com.sortedbits.javarpc.Channel;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.apache.commons.io.IOUtils.closeQuietly;

public abstract class AbstractServer<I, O, C extends ServerController<I, O>> implements Runnable {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private ServerConfig config;
    private C controller;
    private StopWatch timer;
    private ExecutorService executor;

    protected AbstractServer(ServerConfig config) {
        this.config = config;
        this.timer = StopWatch.createStarted();
    }

    @Override
    public void run() {
        startup();

        while (true) {
            try {
                Channel<I, O> channel = createChannel(config);
                executor.submit(() -> process(channel));
            } catch (IOException e) {
                logger.error("Server error: ", e);
            }
        }
    }

    protected abstract C createController(ServerConfig config);

    protected abstract Channel<I,O> createChannel(ServerConfig config) throws IOException;

    private void process(Channel<I, O> channel) {
        try {
            while (true) {
                I req = channel.read();
                O res = controller.handle(req);
                channel.write(res);
            }
        } catch (IOException e) {
            logger.error("Channel communication error: ", e);
        } finally {
            logger.debug("Closing channel...");
            closeQuietly(channel);
        }
    }

    private void startup() {
        try {
            logger.info("Startup in progress...");
            ServerConfig config = ServerConfig.load("server.conf");

            executor = Executors.newFixedThreadPool(config.getThreadPoolSize());
            controller = createController(config);

            startListener(config);

            logger.info("Startup completed in " +  timer.getTime() + "ms: ");
        } catch (Exception e) {
            logger.error("Server startup error: ", e);
        }
    }

    protected abstract void startListener(ServerConfig config) throws IOException;
}
