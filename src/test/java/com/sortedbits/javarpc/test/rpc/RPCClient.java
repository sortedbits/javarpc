package com.sortedbits.javarpc.test.rpc;

import com.sortedbits.javarpc.Channel;
import com.sortedbits.javarpc.RPCRequest;
import com.sortedbits.javarpc.RPCResponse;
import com.sortedbits.javarpc.channels.SerializedSocketChannel;
import com.sortedbits.javarpc.client.AbstractRPCClient;

import java.io.IOException;
import java.net.Socket;

public class RPCClient extends AbstractRPCClient {

    @Override
    protected Channel<RPCResponse, RPCRequest> createSocketChannel(Socket socket) throws IOException {
        return new SerializedSocketChannel<>(socket);
    }
}
