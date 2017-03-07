package com.sortedbits.remoting;


import java.io.*;
import java.net.Socket;

import static org.apache.commons.io.IOUtils.closeQuietly;

public class SocketSerializationChannel<I,O> extends SocketChannel<I, O> {

    public SocketSerializationChannel(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected ChannelReader<I> createReader(InputStream is) throws IOException {
        final ObjectInputStream ois = new ObjectInputStream(is);

        return new ChannelReader<I>() {
            @Override
            public I read() throws IOException {
                try {
                    return (I)ois.readObject();
                } catch (ClassNotFoundException e) {
                    throw new IOException("Read error: ", e);
                }
            }

            @Override
            public void close() throws IOException {
                closeQuietly(ois);
            }
        };
    }

    @Override
    protected ChannelWriter<O> createWriter(OutputStream os) throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(os);

        return new ChannelWriter<O>() {
            @Override
            public void write(O req) throws IOException {
                oos.writeObject(req);
                oos.flush();
            }

            @Override
            public void close() throws IOException {
                closeQuietly(oos);
            }
        };
    }
}
