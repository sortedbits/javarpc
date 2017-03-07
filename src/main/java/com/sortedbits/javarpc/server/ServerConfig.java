package com.sortedbits.javarpc.server;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.InetAddress;

public class ServerConfig {

    public static final String LISTEN_ADDR = "listen.addr";
    public static final String LISTEN_PORT = "listen.port";
    public static final String SOCKET_TIMEOUT = "socket.timeout";
    public static final String THREAD_POOL_SIZE = "threadPool.size";
    private static final String SOCKET_SSL = "socket.ssl";
    public static final int DEFAULT_SOCKET_TIMEOUT = 0;

    private int listenPort;
    private InetAddress listenAddr;
    private int threadPoolSize;
    private int socketTimeout;
    private boolean socketSSL;

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

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public boolean getSocketSSL() {
        return socketSSL;
    }

    public static ServerConfig load() {
        return load("server.conf");
    }

    public static ServerConfig load(String configName) {
        try {
            Config config = ConfigFactory.load(configName);

            ServerConfig serverConfig = new ServerConfig();
            serverConfig.listenAddr = InetAddress.getByName(config.getString(LISTEN_ADDR));
            serverConfig.listenPort = config.getInt(LISTEN_PORT);
            serverConfig.threadPoolSize = config.getInt(THREAD_POOL_SIZE);
            serverConfig.socketTimeout = config.hasPath(SOCKET_TIMEOUT) ? config.getInt(SOCKET_TIMEOUT) : DEFAULT_SOCKET_TIMEOUT;
            serverConfig.socketSSL = config.getBoolean(SOCKET_SSL);
            
            return serverConfig;
        } catch (Exception e) {
            throw new RuntimeException("Error loading server config: ", e);
        }
    }
}
