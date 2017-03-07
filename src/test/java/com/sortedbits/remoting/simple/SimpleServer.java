package com.sortedbits.remoting.simple;

import com.sortedbits.remoting.*;
import com.sun.corba.se.spi.activation.Server;

import java.io.IOException;
import java.net.Socket;

public class SimpleServer extends AbstractSocketServer<SimpleRequest, SimpleResponse, SimpleServerController> {

    protected SimpleServer(ServerConfig serverConfig) {
        super(serverConfig);
    }

    @Override
    protected Channel<SimpleRequest, SimpleResponse> createSocketChannel(Socket socket) throws IOException {
        return new SocketSerializationChannel<>(socket);
    }

    @Override
    protected SimpleServerController createController(ServerConfig config) {
        return new SimpleServerController();
    }

    public static void main(String[] args) {
        ServerConfig config = ServerConfig.load();
        SimpleServer server = new SimpleServer(config);
        Thread serverThread = new Thread(server);
        serverThread.setDaemon(false);
        serverThread.start();
    }
}
