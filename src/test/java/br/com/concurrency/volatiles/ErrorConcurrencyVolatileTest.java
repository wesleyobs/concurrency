package br.com.concurrency.volatiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ErrorConcurrencyVolatileTest {

    @Test
    public void testConcurrencyVolatileError(){
        final VolatileThread errorConcurrencyVolatile = new ErrorConcurrencyVolatile();
        Assertions.assertFalse(errorConcurrencyVolatile.execute());
    }
}
