package com.sortedbits.remoting;

import java.io.Closeable;
import java.io.IOException;

public interface ChannelReader<I> extends Closeable {

    I read() throws IOException;
}
