package com.sortedbits.remoting;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class AbstractSocketServer<I, O, C extends ServerController<I, O>> extends AbstractServer<I, O, C> {
    private ServerSocket listener;

    protected AbstractSocketServer(ServerConfig config) {
        super(config);
    }

    protected Channel<I, O> createChannel(ServerConfig config) throws IOException {
        Socket socket = listener.accept();
        socket.setSoTimeout(config.getSocketTimout());
        return createSocketChannel(socket);
    }


    protected abstract Channel<I,O> createSocketChannel(Socket socket) throws IOException;

    @Override
    protected void startListener(ServerConfig config) throws IOException {
        int port = config.getListenPort();
        InetAddress addr = config.getListenAddr();
        listener = new ServerSocket(port, 0, addr);
        logger.info("Server listening at " + addr.getHostAddress() + ":" + port + "...");
    }
}
