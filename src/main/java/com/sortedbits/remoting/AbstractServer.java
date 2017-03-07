package com.sortedbits.remoting;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.apache.commons.io.IOUtils.closeQuietly;

public abstract class AbstractServer<I, O, C extends ServerController<I, O>> implements Runnable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ServerConfig config;
    private C controller;
    private ServerSocket listener;
    private StopWatch timer;
    private ExecutorService executor;

    protected AbstractServer(C controller) {
        this.controller = controller;
        init(controller);
        this.timer = StopWatch.createStarted();
    }

    @Override
    public void run() {
        startup();

        while (true) {
            final Socket socket;
            try {
                socket = listener.accept();
                socket.setSoTimeout(config.getSocketTimout());
                Channel<I, O>  channel = createChannel(socket);
                executor.submit(() -> process(channel));
            } catch (IOException e) {
                logger.error("Server error: ", e);
            }
        }
    }

    protected void init(C controller) {
    }

    protected abstract Channel<I, O> createChannel(Socket socket) throws IOException;

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
            config = ServerConfig.load("server.conf");
            executor = Executors.newFixedThreadPool(config.getThreadPoolSize());
            int port = config.getListenPort();
            InetAddress addr = config.getListenAddr();
            listener = new ServerSocket(port, 0, addr);
            logger.info("Startup completed in " +  timer.getTime() + "ms: ");
            logger.info("AbstractServer listening at " + addr.getHostAddress() + ":" + port + "...");
        } catch (Exception e) {
            logger.error("AbstractServer startup error: ", e);
        }
    }
}
