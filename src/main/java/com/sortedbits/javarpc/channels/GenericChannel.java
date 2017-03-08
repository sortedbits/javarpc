package com.sortedbits.javarpc.channels;

import java.io.Closeable;
import java.io.IOException;

public interface GenericChannel<I, O> extends Closeable {

    void write(O req) throws IOException;

    I read() throws IOException;
}
