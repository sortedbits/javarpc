package com.sortedbits.remoting;

import javax.net.ssl.SSLServerSocketFactory;
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
        socket.setSoTimeout(config.getSocketTimeout());
        return createSocketChannel(socket);
    }

    protected abstract Channel<I,O> createSocketChannel(Socket socket) throws IOException;

    @Override
    protected void startListener(ServerConfig config) throws IOException {
        int port = config.getListenPort();
        InetAddress addr = config.getListenAddr();
        boolean socketSSL = config.getSocketSSL();
        listener = getServerSocket(port, addr, socketSSL);
        logger.info("Server listening at " + addr.getHostAddress() + ":" + port + "...");
    }

    private ServerSocket getServerSocket(int port, InetAddress addr, boolean socketSSL) throws IOException {
        if (!socketSSL) {
            return new ServerSocket(port, 0, addr);
        } else {
            SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            return factory.createServerSocket(port, 0, addr);
        }
    }
}
