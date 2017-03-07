package com.sortedbits.remoting.simple;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleClientServerTest {

    @BeforeClass
    public static void setup() throws Exception {
        SimpleServer.main(new String[] {});
        Thread.sleep(1000);
    }

    @Test
    public void test() throws Exception {

        Thread.sleep(1000);

        SimpleClient client = new SimpleClient();
        SimpleResponse res = client.submit(new SimpleRequest("hello"));
        assertEquals("ok", res.message);
    }
}
