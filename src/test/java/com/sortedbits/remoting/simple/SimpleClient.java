package com.sortedbits.remoting.simple;

import com.sortedbits.remoting.Channel;
import com.sortedbits.remoting.AbstractClient;
import com.sortedbits.remoting.SocketChannel;
import com.sortedbits.remoting.SocketSerializationChannel;

import java.io.IOException;
import java.net.Socket;

public class SimpleClient extends AbstractClient<SimpleResponse, SimpleRequest> {

    @Override
    protected Channel<SimpleResponse, SimpleRequest> createChannel(Socket socket) throws IOException {
        return new SocketSerializationChannel<>(socket);
    }
}
