package com.sortedbits.remoting;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.InetAddress;

public class ClientConfig {

    public static final String SERVER_ADDR = "server.addr";
    public static final String SERVER_PORT = "server.port";
    public static final String SOCKET_TIMEOUT = "socket.timeout";
    public static final String SOCKET_SSL = "socket.ssl";
    public static final int DEFAULT_SOCKET_TIMEOUT = 0;

    private InetAddress serverAddr;
    private int serverPort;
    private int socketTimeout;
    private boolean socketSSL;

    private ClientConfig() {
    }

    public int getPort() {
        return serverPort;
    }

    public  InetAddress getServerAddr() {
        return serverAddr;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public boolean getSocketSSL() {
        return socketSSL;
    }

    public static ClientConfig load() {
        return load("client.conf");
    }

    public static ClientConfig load(String configName) {
        try {
            Config config = ConfigFactory.load(configName);

            ClientConfig clientConfig = new ClientConfig();
            clientConfig.serverAddr = InetAddress.getByName(config.getString(SERVER_ADDR));
            clientConfig.serverPort = config.getInt(SERVER_PORT);
            clientConfig.socketTimeout = config.hasPath(SOCKET_TIMEOUT) ? config.getInt(SOCKET_TIMEOUT) : DEFAULT_SOCKET_TIMEOUT;
            clientConfig.socketSSL = config.getBoolean(SOCKET_SSL);
            return clientConfig;
        } catch (Exception e) {
            throw new RuntimeException("Error loading client config: ", e);
        }
    }

}
