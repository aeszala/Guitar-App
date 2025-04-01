package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class UserListTest {

    @Test
    public void testing() {
        assertTrue(true);
    }

    @Test
    public void testing2() {
        assertFalse(false);
    }

    @Test
    public void testing3() {
        String val1 = "hello";
        String val2 = "hi";
        assertEquals(val1, val2);
    }
}