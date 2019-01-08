/**
 * 
 */
package com.scg.util;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Norman Skinner
 *
 */
public class PersonalNameTest {
	private PersonalName personalName;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.scg.util.PersonalName#hashCode()}.
	 */
	@Test
	public void test_HashCode_Nulls() {
		// ARRANGE
		personalName = new PersonalName(null, null, null);
		
		// ACT
		int result = personalName.hashCode();
		
		// ASSERT
		assertEquals(29791, result);
	}

	/**
	 * Test method for {@link com.scg.util.PersonalName#hashCode()}.
	 */
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

	/**
	 * Test method for {@link com.scg.util.PersonalName#PersonalName(java.lang.String)}.
	 */
	@Test
	public void test_PersonalNameString() {
		// ARRANGE
		personalName = new PersonalName("lastName");
		
		// ACT
		String result = personalName.toString();
		
		// ASSERT
		assertEquals("lastName,", result);
	}

	/**
	 * Test method for {@link com.scg.util.PersonalName#PersonalName(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void test_PersonalNameStringString() {
		// ARRANGE
		personalName = new PersonalName("lastName", "firstName");
		
		// ACT
		String result = personalName.toString();
		
		// ASSERT
		assertEquals("lastName, firstName", result);
	}

	/**
	 * Test method for {@link com.scg.util.PersonalName#PersonalName(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void test_PersonalNameStringStringString() {
		// ARRANGE
		personalName = new PersonalName("lastName", "firstName", "middleName");
		
		// ACT
		String result = personalName.toString();
		
		// ASSERT
		assertEquals("lastName, firstName middleName", result);
	}

//	/**
//	 * Test method for {@link com.scg.util.PersonalName#getFirstName()}.
//	 */
//	@Test
//	public void test_GetFirstName() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.scg.util.PersonalName#setFirstName(java.lang.String)}.
//	 */
//	@Test
//	public void test_SetFirstName() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.scg.util.PersonalName#getLastName()}.
//	 */
//	@Test
//	public void test_GetLastName() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.scg.util.PersonalName#setLastName(java.lang.String)}.
//	 */
//	@Test
//	public void test_SetLastName() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.scg.util.PersonalName#getMiddleName()}.
//	 */
//	@Test
//	public void test_GetMiddleName() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.scg.util.PersonalName#setMiddleName(java.lang.String)}.
//	 */
//	@Test
//	public void test_SetMiddleName() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link com.scg.util.PersonalName#toString()}.
	 */
	@Test
	public void test_ToString() {
		// ARRANGE
		personalName = new PersonalName("lastName", "firstName", "middleName");
		
		// ACT
		String result = personalName.toString();
		
		// ASSERT
		assertEquals("lastName, firstName middleName", result);
	}

	/**
	 * Test method for {@link com.scg.util.PersonalName#equals(java.lang.Object)}.
	 */
	@Test
	public void test_EqualsObject() {
		// ARRANGE
		personalName = new PersonalName(null, null, null);
		PersonalName personalName2 = new PersonalName(null, null, null);
		
		// ACT
		boolean result01 = personalName.equals(personalName);
		boolean result02 = personalName.equals(null);
		boolean result03 = personalName.equals(personalName2);
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
