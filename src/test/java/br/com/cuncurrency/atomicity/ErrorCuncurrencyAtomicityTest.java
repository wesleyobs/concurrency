package br.com.cuncurrency.atomicity;

import br.com.concurrency.atomicity.AtomicityThread;
import br.com.concurrency.atomicity.ErrorCuncurrencyAtomicity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ErrorCuncurrencyAtomicityTest {
    @Test
    public void testCuncurrencyAtomicityWhenError() throws InterruptedException {
        final AtomicityThread errorCuncurrencyAtomicity = new ErrorCuncurrencyAtomicity();
        boolean execute = errorCuncurrencyAtomicity.execute();
        Assertions.assertFalse(execute);
    }
}
