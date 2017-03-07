package com.sortedbits.remoting.rpc;

import com.sortedbits.remoting.Channel;
import com.sortedbits.remoting.ServerConfig;
import com.sortedbits.remoting.SocketChannel;
import com.sortedbits.remoting.SocketSerializationChannel;

import java.io.IOException;
import java.net.Socket;

public class RpcServer extends RpcAbstractServer {

    protected RpcServer(ServerConfig config) {
        super(config);
    }

    @Override
    protected Channel<RpcRequest, RpcResponse> createSocketChannel(Socket socket) throws IOException {
        return new SocketSerializationChannel<>(socket);
    }

    @Override
    protected RpcServerController createController(ServerConfig config) {
        RpcServerController controller = new RpcServerController();
        controller.register(CalcService.class, new CalcServiceImpl());
        return controller;
    }

    public static void main(String[] args) {
        ServerConfig config = ServerConfig.load();
        RpcServer server = new RpcServer(config);
        Thread serverThread = new Thread(server);
        serverThread.setDaemon(false);
        serverThread.start();
    }
}
