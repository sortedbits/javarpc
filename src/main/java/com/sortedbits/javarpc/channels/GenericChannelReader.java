package com.sortedbits.javarpc.channels;

import java.io.Closeable;
import java.io.IOException;

public interface GenericChannelReader<I> extends Closeable {

    I read() throws IOException;
}
