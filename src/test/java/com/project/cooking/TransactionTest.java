package com.project.cooking;

import static org.junit.Assert.*;
import org.junit.Test;

import com.project.cooking.finance.Transaction;

public class TransactionTest {

    @Test
    public void testConstructorAndGetters() {
        Transaction t = new Transaction("2025-05-17", 123.45);
        assertEquals("2025-05-17", t.getDate());
        assertEquals(123.45, t.getAmount(), 0.0001);
    }

    @Test
    public void testDefaultConstructor() {
        Transaction t = new Transaction();
        assertNull(t.getDate());
        assertEquals(0.0, t.getAmount(), 0.0001);
    }
}
