package com.sortedbits.remoting.simple;

import com.sortedbits.remoting.*;

import java.io.IOException;
import java.net.Socket;

public class SimpleClient extends AbstractSocketClient<SimpleResponse, SimpleRequest> {

    @Override
    protected Channel<SimpleResponse, SimpleRequest> createSocketChannel(Socket socket) throws IOException {
        return new SocketSerializationChannel<>(socket);
    }
}
