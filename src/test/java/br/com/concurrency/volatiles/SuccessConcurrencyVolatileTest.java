package br.com.concurrency.volatiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuccessConcurrencyVolatileTest {

    @Test
    public void testConcurrencyVolatileSuccess(){
        final VolatileThread successConcurrencyVolatile = new SuccessConcurrencyVolatile();
        Assertions.assertTrue(successConcurrencyVolatile.execute());
    }
}
