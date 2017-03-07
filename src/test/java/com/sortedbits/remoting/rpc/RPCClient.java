package com.sortedbits.remoting.rpc;

import com.sortedbits.remoting.Channel;
import com.sortedbits.remoting.SocketSerializationChannel;

import java.io.IOException;
import java.net.Socket;

public class RPCClient extends AbstractRPCClient {

    @Override
    protected Channel<RPCResponse, RPCRequest> createSocketChannel(Socket socket) throws IOException {
        return new SocketSerializationChannel<>(socket);
    }
}
