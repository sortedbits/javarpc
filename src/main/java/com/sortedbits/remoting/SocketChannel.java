package com.sortedbits.remoting;

import java.io.*;
import java.net.Socket;

import static org.apache.commons.io.IOUtils.closeQuietly;

public abstract class SocketChannel<I, O> implements Channel<I, O> {

    private final Socket socket;
    private final ChannelReader<I> reader;
    private final ChannelWriter<O> writer;

    public SocketChannel(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = createWriter(socket.getOutputStream());
        this.reader = createReader(socket.getInputStream());
    }

    protected abstract ChannelReader<I> createReader(InputStream is) throws IOException;

    protected abstract ChannelWriter<O> createWriter(OutputStream os) throws IOException;

    @Override
    public void write(O req) throws IOException {
        writer.write(req);
    }

    @Override
    public I read() throws IOException {
        return reader.read();
    }

    @Override
    public void close() {
        closeQuietly(writer);
        closeQuietly(reader);
        closeQuietly(socket);
    }
}
