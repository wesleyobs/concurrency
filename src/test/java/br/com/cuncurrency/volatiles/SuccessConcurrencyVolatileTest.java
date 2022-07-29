package br.com.cuncurrency.volatiles;

import br.com.concurrency.volatiles.SuccessConcurrencyVolatile;
import org.junit.jupiter.api.Test;

public class SuccessConcurrencyVolatileTest {

    @Test
    public void testConcurrencyVolatileSuccess(){
        final SuccessConcurrencyVolatile successConcurrencyVolatile = new SuccessConcurrencyVolatile();
        successConcurrencyVolatile.execute();
    }
}
