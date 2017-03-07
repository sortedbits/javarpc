package com.sortedbits.javarpc.test.socket;

import com.sortedbits.javarpc.Channel;
import com.sortedbits.javarpc.channels.SerializedSocketChannel;
import com.sortedbits.javarpc.server.AbstractSocketServer;
import com.sortedbits.javarpc.server.ServerConfig;

import java.io.IOException;
import java.net.Socket;

public class SocketServer extends AbstractSocketServer<SimpleRequest, SimpleResponse, SocketServerController> {

    protected SocketServer(ServerConfig serverConfig) {
        super(serverConfig);
    }

    @Override
    protected Channel<SimpleRequest, SimpleResponse> createSocketChannel(Socket socket) throws IOException {
        return new SerializedSocketChannel<>(socket);
    }

    @Override
    protected SocketServerController createController(ServerConfig config) {
        return new SocketServerController();
    }

    public static void main(String[] args) {
        ServerConfig config = ServerConfig.load();
        SocketServer server = new SocketServer(config);
        Thread serverThread = new Thread(server);
        serverThread.setDaemon(false);
        serverThread.start();
    }
}
