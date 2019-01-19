/**
 * 
 */
package com.scg.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scg.domain.ConsultantTime;
import com.scg.domain.Skill;

/**
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
class AddressTest {
    private Address address;
    private Address addres2;
    private String streetNumber;
    private String city;
    private StateCode state;
    private String postalCode;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        streetNumber = "123 Street";
        city = "Big City";
        state = StateCode.CA;
        postalCode = "91111";
        address = new Address(streetNumber, city, state, postalCode);
    }

    /**
     * Test method for {@link com.scg.util.Address#hashCode()}.
     */
    @Test
    void test_HashCode() {
        // ARRANGE
        address = new Address("123 Street", "Big City", StateCode.CA, "91111");
        addres2 = new Address("123 Street", "Big City", StateCode.CA, "91111");
        
        // ACT
        int hash1 = address.hashCode();
        int hash2 = addres2.hashCode();
        
        // ASSERT
        assertEquals(hash1, hash2);
    }

    @Test
    void test_HashCode_NULL() {
        // ARRANGE
        address = new Address(null,null,null,null);
        
        // ACT
        int hash = address.hashCode();
        
        // ASSERT
        assertEquals(923521, hash);
    }

    /**
     * Test method for {@link com.scg.util.Address#equals(java.lang.Object)}.
     */
    @Test
    void test_EqualsObject() {
        // ARRANGE
        address = new Address(streetNumber, city, state, postalCode);
        addres2 = new Address(streetNumber, city, state, postalCode);
        
        // ACT
        boolean result01 = address.equals(addres2);
        boolean result02 = address.equals(address);
        boolean result03 = address.equals(null);
        @SuppressWarnings("unlikely-arg-type")
        boolean result04 = address.equals(city);

        address = new Address(streetNumber, city, state, postalCode);
        addres2 = new Address(null,null,null,null);

        boolean result05 = addres2.equals(address);
        address = new Address(streetNumber, null, state, postalCode);
        boolean result06 = addres2.equals(address);
        address = new Address(streetNumber, null, state, null);
        boolean result07 = addres2.equals(address);
        address = new Address(streetNumber, null, null, null);
        boolean result08 = addres2.equals(address);
        address = new Address(null, null, null, null);
        boolean result09 = addres2.equals(address);

        
        
        address   = new Address(streetNumber, city, state, postalCode);
        boolean result10 = address.equals(addres2);
        addres2 = new Address(streetNumber, "Small City", state, postalCode);
        boolean result11 = address.equals(addres2);
        addres2 = new Address("9999 NoStreet", city, state, postalCode);
        boolean result12 = address.equals(addres2);
        addres2 = new Address(streetNumber, city, state, "12345");
        boolean result13 = address.equals(addres2);
        addres2 = new Address(streetNumber, city, StateCode.FL, postalCode);
        boolean result14 = address.equals(addres2);
        
        // ASSERT
        assertTrue(result01);
        assertTrue(result02);
        assertFalse(result03);
        assertFalse(result04);
        assertFalse(result05);
        assertFalse(result06);
        assertFalse(result07);
        assertFalse(result08);
        assertTrue(result09);
        assertFalse(result10);
        assertFalse(result11);
        assertFalse(result12);
        assertFalse(result13);
        assertFalse(result14);
    }

    /**
     * Test method for {@link com.scg.util.Address#toString()}.
     */
    @Test
    void test_ToString() {
        // ARRANGE
        address = new Address("123 Street", "Big City", StateCode.CA, "91111");
        
        // ACT
        String result = address.toString();
        
        // ASSERT
        assertEquals("123 Street\r\nBig City, CA 91111 \r\n", result);
    }

}
