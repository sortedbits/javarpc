package com.sortedbits.remoting;

import java.io.Closeable;
import java.io.IOException;

public interface ChannelWriter<O> extends Closeable {

    void write(O msg) throws IOException;
}
