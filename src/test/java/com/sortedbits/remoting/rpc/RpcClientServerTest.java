package com.sortedbits.remoting.rpc;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RpcClientServerTest {

    @BeforeClass
    public static void setup() throws Exception {
        RpcServer.main(new String[] {});
        Thread.sleep(1000);
    }

    @Test
    public void testSum() throws IOException {
        RpcClient client = new RpcClient();
        CalcService service = client.getService(CalcService.class);
        int x = service.add(3, 4);
        assertEquals(7, x);
    }

    @Test
    public void testDiv() throws IOException {
        RpcClient client = new RpcClient();
        CalcService service = client.getService(CalcService.class);
        int x = service.div(8, 4);
        assertEquals(2, x);
    }

    @Test(expected=java.lang.ArithmeticException.class)
    public void testDivByZero() throws IOException {
        RpcClient client = new RpcClient();
        CalcService service = client.getService(CalcService.class);
        int x = service.div(8, 0);
    }
}
