package com.sortedbits.remoting.rpc;

import com.sortedbits.remoting.Channel;
import com.sortedbits.remoting.SocketChannel;
import com.sortedbits.remoting.SocketSerializationChannel;

import java.io.IOException;
import java.net.Socket;

public class RpcServer extends RpcAbstractServer {

    @Override
    protected Channel createChannel(Socket socket) throws IOException {
        return new SocketSerializationChannel<>(socket);
    }

    @Override
    protected void init(RpcServerController controller) {
        controller.register(CalcService.class, new CalcServiceImpl());
    }

    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        Thread serverThread = new Thread(server);
        serverThread.setDaemon(false);
        serverThread.start();
    }
}
