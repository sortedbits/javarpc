package com.sortedbits.javarpc.test.socket;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SocketTest {

    @BeforeClass
    public static void setup() throws Exception {
        SocketServer.main(new String[] {});
        Thread.sleep(1000);
    }

    @Test
    public void test() throws Exception {

        Thread.sleep(1000);

        SocketClient client = new SocketClient();
        SimpleResponse res = client.submit(new SimpleRequest("hello"));
        assertEquals("ok", res.message);
    }
}
