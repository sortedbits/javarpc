package com.sortedbits.remoting.rpc;

import com.sortedbits.remoting.Channel;
import com.sortedbits.remoting.SocketChannel;
import com.sortedbits.remoting.SocketSerializationChannel;

import java.io.IOException;
import java.net.Socket;

public class RpcClient extends RpcAbstractClient {

    @Override
    protected Channel<RpcResponse, RpcRequest> createChannel(Socket socket) throws IOException {
        return new SocketSerializationChannel<>(socket);
    }
}
