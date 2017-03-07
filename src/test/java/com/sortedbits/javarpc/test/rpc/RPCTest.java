package com.sortedbits.javarpc.test.rpc;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RPCTest {

    @BeforeClass
    public static void setup() throws Exception {
        RPCServer.main(new String[] {});
        Thread.sleep(1000);
    }

    @Test
    public void testSum() throws IOException {
        RPCClient client = new RPCClient();
        CalcService service = client.getService(CalcService.class);
        int x = service.add(3, 4);
        assertEquals(7, x);
    }

    @Test
    public void testDiv() throws IOException {
        RPCClient client = new RPCClient();
        CalcService service = client.getService(CalcService.class);
        int x = service.div(8, 4);
        assertEquals(2, x);
    }

    @Test(expected=java.lang.ArithmeticException.class)
    public void testDivByZero() throws IOException {
        RPCClient client = new RPCClient();
        CalcService service = client.getService(CalcService.class);
        int x = service.div(8, 0);
    }
}
