package com.sortedbits.remoting.simple;

import com.sortedbits.remoting.Channel;
import com.sortedbits.remoting.AbstractServer;
import com.sortedbits.remoting.SocketChannel;
import com.sortedbits.remoting.SocketSerializationChannel;

import java.io.IOException;
import java.net.Socket;

public class SimpleServer extends AbstractServer<SimpleRequest, SimpleResponse, SimpleServerController> {

    protected SimpleServer() {
        super(new SimpleServerController());
    }

    @Override
    protected Channel<SimpleRequest, SimpleResponse> createChannel(Socket socket) throws IOException {
        return new SocketSerializationChannel<>(socket);
    }

    public static void main(String[] args) {
        SimpleServer server = new SimpleServer();
        Thread serverThread = new Thread(server);
        serverThread.setDaemon(false);
        serverThread.start();
    }
}
