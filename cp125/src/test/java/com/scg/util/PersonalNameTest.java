/**
 * 
 */
package com.scg.util;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * So much testing goodness!
 * 
 * @author Norman Skinner
 *
 */
public class PersonalNameTest {
    private PersonalName personalName;

    @Test
    public void test_HashCode_Nulls() {
        // ARRANGE
        personalName = new PersonalName(null, null, null);

        // ACT
        int result = personalName.hashCode();

        // ASSERT
        assertEquals(29791, result);
    }

    @Test
    public void test_HashCode_Values() {
        // ARRANGE
        PersonalName personalName1 = new PersonalName("lastName", "firstName", "middleName");
        PersonalName personalName2 = new PersonalName("lastName", "firstName", "middleName");

        // ACT
        int result1 = personalName1.hashCode();
        int result2 = personalName2.hashCode();

        // ASSERT
        assertEquals(result1, result2);
    }

    @Test
    public void test_PersonalNameString() {
        // ARRANGE
        personalName = new PersonalName();

        // ACT
        String result = personalName.toString();

        // ASSERT
        assertEquals(",", result);
    }

    @Test
    public void test_PersonalNameStringString() {
        // ARRANGE
        personalName = new PersonalName("lastName", "firstName");

        // ACT
        String result = personalName.toString();

        // ASSERT
        assertEquals("lastName, firstName", result);
    }

    @Test
    public void test_PersonalNameStringStringString() {
        // ARRANGE
        personalName = new PersonalName("lastName", "firstName", "middleName");

        // ACT
        String result = personalName.toString();

        // ASSERT
        assertEquals("lastName, firstName middleName", result);
    }

    @Test
    public void test_SetFirstName() {
        // ARRANGE
        personalName = new PersonalName();

        // ACT
        personalName.setFirstName("TEST");

        // ASSERT
        String result = personalName.toString();
        assertEquals(", TEST", result);
    }

    @Test
    public void test_SetLastName() {
        // ARRANGE
        personalName = new PersonalName();

        // ACT
        personalName.setLastName("TEST");

        // ASSERT
        String result = personalName.toString();
        assertEquals("TEST,", result);
    }

    @Test
    public void test_SetMiddleName() {
        // ARRANGE
        personalName = new PersonalName();

        // ACT
        personalName.setMiddleName("TEST");

        // ASSERT
        String result = personalName.toString();
        assertEquals(",  TEST", result);
    }

    @Test
    public void test_ToString() {
        // ARRANGE
        personalName = new PersonalName("lastName", "firstName", "middleName");

        // ACT
        String result = personalName.toString();

        // ASSERT
        assertEquals("lastName, firstName middleName", result);
    }

    @Test
    public void test_CompareTo() {
        // ARRANGE
        PersonalName personalName1 = new PersonalName("a", "a", "a");
        PersonalName personalName2 = new PersonalName("b", "b", "b");
        PersonalName personalName3 = new PersonalName("a", "a", "a");

        // ACT
        int resultLess = personalName1.compareTo(personalName2);
        int resultEqual = personalName1.compareTo(personalName3);
        int resultEqualOtherWay = personalName3.compareTo(personalName1);
        int resultEqualSame = personalName1.compareTo(personalName1);
        int resultGreater = personalName2.compareTo(personalName1);

        // ASSERT
        assertTrue(resultLess < 0);
        assertTrue(resultEqual == 0);
        assertTrue(resultEqualOtherWay == 0);
        assertTrue(resultEqualSame == 0);
        assertTrue(resultGreater > 0);
    }

    @Test
    public void test_EqualsObject() {
        // ARRANGE
        personalName = new PersonalName(null, null, null);
        PersonalName personalName2 = new PersonalName(null, null, null);

        // ACT
        boolean result01 = personalName.equals(personalName);
        boolean result02 = personalName.equals(null);
        boolean result03 = personalName.equals(personalName2);
        @SuppressWarnings("unlikely-arg-type")
        boolean result04 = personalName.equals(LocalDate.now());
        personalName2 = new PersonalName("a", "b", "c");
        boolean result05 = personalName.equals(personalName2);
        personalName2 = new PersonalName("a", null, "c");
        boolean result06 = personalName.equals(personalName2);
        personalName2 = new PersonalName(null, null, "c");
        boolean result07 = personalName.equals(personalName2);
        personalName2 = new PersonalName(null, null, "c");
        boolean result08 = personalName.equals(personalName2);
        personalName = new PersonalName("a", "b", "c");
        personalName2 = new PersonalName("a", "-", "c");
        boolean result09 = personalName.equals(personalName2);
        personalName2 = new PersonalName("-", "b", "c");
        boolean result10 = personalName.equals(personalName2);
        personalName2 = new PersonalName("a", "b", "-");
        boolean result11 = personalName.equals(personalName2);
        personalName2 = new PersonalName("a", "b", "c");
        boolean result12 = personalName.equals(personalName2);

        // ASSERT
        assertTrue(result01);
        assertFalse(result02);
        assertTrue(result03);
        assertFalse(result04);
        assertFalse(result05);
        assertFalse(result06);
        assertFalse(result07);
        assertFalse(result08);
        assertFalse(result09);
        assertFalse(result10);
        assertFalse(result11);
        assertTrue(result12);
    }

}
