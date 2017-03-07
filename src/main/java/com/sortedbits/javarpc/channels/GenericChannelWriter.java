package com.sortedbits.javarpc.channels;

import java.io.Closeable;
import java.io.IOException;

public interface GenericChannelWriter<O> extends Closeable {

    void write(O msg) throws IOException;
}
