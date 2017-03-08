package com.sortedbits.javarpc.test.rpc;

import com.sortedbits.javarpc.channels.GenericChannel;
import com.sortedbits.javarpc.rpc.RPCRequest;
import com.sortedbits.javarpc.rpc.RPCResponse;
import com.sortedbits.javarpc.channels.SerializedSocketChannel;
import com.sortedbits.javarpc.rpc.AbstractRPCClient;

import java.io.IOException;
import java.net.Socket;

public class RPCClient extends AbstractRPCClient {

    @Override
    protected GenericChannel<RPCResponse, RPCRequest> createSocketChannel(Socket socket) throws IOException {
        return new SerializedSocketChannel<>(socket);
    }
}
