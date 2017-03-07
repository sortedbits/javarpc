package com.sortedbits.javarpc.test.rpc;

import com.sortedbits.javarpc.Channel;
import com.sortedbits.javarpc.RPCRequest;
import com.sortedbits.javarpc.RPCResponse;
import com.sortedbits.javarpc.server.ServerConfig;
import com.sortedbits.javarpc.channels.SerializedSocketChannel;
import com.sortedbits.javarpc.server.AbstractRPCServer;
import com.sortedbits.javarpc.server.RPCServerController;

import java.io.IOException;
import java.net.Socket;

public class RPCServer extends AbstractRPCServer {

    protected RPCServer(ServerConfig config) {
        super(config);
    }

    @Override
    protected Channel<RPCRequest, RPCResponse> createSocketChannel(Socket socket) throws IOException {
        return new SerializedSocketChannel<>(socket);
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
