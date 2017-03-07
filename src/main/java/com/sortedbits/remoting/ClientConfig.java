package com.sortedbits.remoting;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.InetAddress;

public class ClientConfig {

    private InetAddress serverAddr;
    private int serverPort;
    private int socketTimeout;

    ClientConfig(InetAddress serverAddr, int serverPort, int socketTimeout) {
        this.serverAddr = serverAddr;
        this.serverPort = serverPort;
        this.socketTimeout = socketTimeout;
    }

    int getPort() {
        return serverPort;
    }

    InetAddress getServerAddr() {
        return serverAddr;
    }

    static ClientConfig load(String configName) {
        try {
            Config config = ConfigFactory.load(configName);

            InetAddress serverAddr = InetAddress.getByName(config.getString("server.addr"));
            int serverPort = config.getInt("server.port");

            int socketTimeout = config.hasPath("socket.timeout") ? config.getInt("socket.timeout") : 0;

            return new ClientConfig(serverAddr, serverPort, socketTimeout);
        } catch (Exception e) {
            throw new RuntimeException("Error loading client config: ", e);
        }
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }
}
