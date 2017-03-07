package com.sortedbits.javarpc;

import java.io.Closeable;
import java.io.IOException;

public interface Channel<I, O> extends Closeable {

    void write(O req) throws IOException;

    I read() throws IOException;
}
