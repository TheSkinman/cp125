/**
 * 
 */
package com.scg.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
class StateCodeTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * Test method for {@link com.scg.util.StateCode#getFullName()}.
     */
    @Test
    void test_GetFullName() {
        // ARRANGE
        StateCode sc = StateCode.WA;
        
        // ACT 
        String result = sc.getFullName();
        
        // ASSERT
        assertEquals("Washington", result);
    }

}
