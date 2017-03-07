package com.sortedbits.remoting;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.InetAddress;

public class ServerConfig {

    private int listenPort;
    private InetAddress listenAddr;
    private int threadPoolSize;
    private int socketTimeout;

    ServerConfig(InetAddress listenAddr, int listenPort, int threadPoolSize, int socketTimeout) {
        this.listenAddr = listenAddr;
        this.listenPort = listenPort;
        this.threadPoolSize = threadPoolSize;
        this.socketTimeout = socketTimeout;
    }

    int getListenPort() {
        return listenPort;
    }

    InetAddress getListenAddr() {
        return listenAddr;
    }

    int getThreadPoolSize() {
        return threadPoolSize;
    }

    int getSocketTimout() {
        return socketTimeout;
    }

    static ServerConfig load(String configName) throws Exception {
        try {
            Config config = ConfigFactory.load(configName);

            InetAddress listenAddr = InetAddress.getByName(config.getString("listen.addr"));
            int listenPort = config.getInt("listen.port");
            int threadPoolSize = config.getInt("threadPool.size");
            int socketTimeout = config.hasPath("socket.timeout") ? config.getInt("socket.timeout") : 0;

            return new ServerConfig(listenAddr, listenPort, threadPoolSize, socketTimeout);
        } catch (Exception e) {
            throw new RuntimeException("Error loading server config: ", e);
        }
    }
}
