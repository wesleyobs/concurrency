package br.com.cuncurrency.atomicity;

import br.com.concurrency.atomicity.AtomicityThread;
import br.com.concurrency.atomicity.SuccessCuncurrencyAtomicity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuccessCuncurrencyAtomicityTest {

    @Test
    public void testCuncurrencyAtomicityWhenSuccess() throws InterruptedException {
        final AtomicityThread successCuncurrencyAtomicity = new SuccessCuncurrencyAtomicity();
        boolean execute = successCuncurrencyAtomicity.execute();
        Assertions.assertTrue(execute);
    }
}
