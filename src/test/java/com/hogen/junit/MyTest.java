package com.hogen.junit;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("test class name")
public class MyTest {

    @Test
    @BeforeEach
    static void myFirstTest() {
        assertEquals(2, 1+1);
        assertEquals(4, 4, "wrong");
        assertNotNull("notnull");
        assumeTrue(1==1);
    }

    @Nested
    class WhenNew {
        @Test
        void isEmpty() {
            assertTrue(true);
        }
        @ParameterizedTest
        @CsvSource({ "foo, 1", "bar, 2", "baz, 3" })
        void palindromes(String param1, int param2) {
            assertTrue(param2 == 1);
        }
    }
}