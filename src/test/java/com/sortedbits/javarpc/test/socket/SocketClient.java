package com.sortedbits.javarpc.test.socket;

import com.sortedbits.javarpc.Channel;
import com.sortedbits.javarpc.channels.SerializedSocketChannel;
import com.sortedbits.javarpc.client.AbstractSocketClient;

import java.io.IOException;
import java.net.Socket;

public class SocketClient extends AbstractSocketClient<SimpleResponse, SimpleRequest> {

    @Override
    protected Channel<SimpleResponse, SimpleRequest> createSocketChannel(Socket socket) throws IOException {
        return new SerializedSocketChannel<>(socket);
    }
}
