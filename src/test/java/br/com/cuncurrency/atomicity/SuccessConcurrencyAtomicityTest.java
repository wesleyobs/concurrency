package br.com.cuncurrency.atomicity;

import br.com.concurrency.atomicity.AtomicityThread;
import br.com.concurrency.atomicity.SuccessConcurrencyAtomicity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuccessConcurrencyAtomicityTest {

    @Test
    public void testCuncurrencyAtomicityWhenSuccess() throws InterruptedException {
        final AtomicityThread successCuncurrencyAtomicity = new SuccessConcurrencyAtomicity();
        boolean execute = successCuncurrencyAtomicity.execute();
        Assertions.assertTrue(execute);
    }
}
