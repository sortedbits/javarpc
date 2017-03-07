package com.sortedbits.remoting;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.InetAddress;

public class ServerConfig {

    public static final String LISTEN_ADDR = "listen.addr";
    public static final String LISTEN_PORT = "listen.port";
    public static final String SOCKET_TIMEOUT = "socket.timeout";
    public static final String THREAD_POOL_SIZE = "threadPool.size";
    public static final int DEFAULT_SOCKET_TIMEOUT = 0;

    private int listenPort;
    private InetAddress listenAddr;
    private int threadPoolSize;
    private int socketTimeout;

    private ServerConfig() {
    }

    public int getListenPort() {
        return listenPort;
    }

    public InetAddress getListenAddr() {
        return listenAddr;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public int getSocketTimout() {
        return socketTimeout;
    }

    public static ServerConfig load(String configName) throws Exception {
        try {
            Config config = ConfigFactory.load(configName);

            ServerConfig serverConfig = new ServerConfig();
            serverConfig.listenAddr = InetAddress.getByName(config.getString(LISTEN_ADDR));
            serverConfig.listenPort = config.getInt(LISTEN_PORT);
            serverConfig.threadPoolSize = config.getInt(THREAD_POOL_SIZE);
            serverConfig.socketTimeout = config.hasPath(SOCKET_TIMEOUT) ? config.getInt(SOCKET_TIMEOUT) : DEFAULT_SOCKET_TIMEOUT;

            return serverConfig;
        } catch (Exception e) {
            throw new RuntimeException("Error loading server config: ", e);
        }
    }
}
