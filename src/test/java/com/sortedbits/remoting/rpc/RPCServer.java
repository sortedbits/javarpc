package com.sortedbits.remoting.rpc;

import com.sortedbits.remoting.Channel;
import com.sortedbits.remoting.ServerConfig;
import com.sortedbits.remoting.SocketSerializationChannel;

import java.io.IOException;
import java.net.Socket;

public class RPCServer extends AbstractRPCServer {

    protected RPCServer(ServerConfig config) {
        super(config);
    }

    @Override
    protected Channel<RPCRequest, RPCResponse> createSocketChannel(Socket socket) throws IOException {
        return new SocketSerializationChannel<>(socket);
    }

    @Override
    protected RPCServerController createController(ServerConfig config) {
        RPCServerController controller = new RPCServerController();
        controller.register(CalcService.class, new CalcServiceImpl());
        return controller;
    }

    public static void main(String[] args) {
        ServerConfig config = ServerConfig.load();
        RPCServer server = new RPCServer(config);
        Thread serverThread = new Thread(server);
        serverThread.setDaemon(false);
        serverThread.start();
    }
}
