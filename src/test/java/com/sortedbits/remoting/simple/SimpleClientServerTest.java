package com.sortedbits.remoting.simple;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SimpleClientServerTest {

    @BeforeClass
    public static void setup() {
        SimpleServer.main(new String[] {});
    }

    @Test
    public void test() throws IOException {
        SimpleClient client = new SimpleClient();
        SimpleResponse res = client.submit(new SimpleRequest("hello"));
        assertEquals("ok", res.message);
    }
}
