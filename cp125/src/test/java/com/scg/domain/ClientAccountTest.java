/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import com.scg.util.PersonalName;

/**
 * @author Norman Skinner
 *
 */
public class ClientAccountTest {
	private ClientAccount clientAccount; 
	private PersonalName personalName;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		personalName = new PersonalName("last", "first", "middle");
	}

	/**
	 * Test method for {@link com.scg.domain.ClientAccount#ClientAccount(java.lang.String, com.scg.util.PersonalName)}.
	 */
	@Test
	public void test_ClientAccountConstructor_String_PersonalName() {
		// ARRANGE
		String name = "Company Test";
		PersonalName contact = personalName;

		// ACT
		ClientAccount result = new ClientAccount(name, contact);
		
		// ASSERT
		assertEquals(name, result.getName());
		assertTrue(Objects.equals(personalName, result.getContact()));
		assertEquals(true, result.isBillable());
	}

	/**
	 * Test method for {@link com.scg.domain.ClientAccount#getName()}.
	 */
	@Test
	public void test_GetName() {
		// ARRANGE
		clientAccount = new ClientAccount("test", null);
		
		// ACT
		String result = clientAccount.getName();
		
		// ASSERT
		assertEquals("test", result);
	}

	/**
	 * Test method for {@link com.scg.domain.ClientAccount#isBillable()}.
	 */
	@Test
	public void test_IsBillable() {
		// ARRANGE
		clientAccount = new ClientAccount("test", null);
		
		// ACT
		boolean result = clientAccount.isBillable();
		
		// ASSERT
		assertTrue(result);
	}

	/**
	 * Test method for {@link com.scg.domain.ClientAccount#getContact()}.
	 */
	@Test
	public void test_GetContact() {
		// ARRANGE
		clientAccount = new ClientAccount("test", personalName);
		
		// ACT
		PersonalName result = clientAccount.getContact();
		
		// ASSERT
		assertTrue(Objects.equals(personalName, result));
	}

	/**
	 * Test method for {@link com.scg.domain.ClientAccount#setContact(com.scg.util.PersonalName)}.
	 */
	@Test
	public void test_SetContact() {
		// ARRANGE
		clientAccount = new ClientAccount("test", null);
		
		// ACT
		clientAccount.setContact(personalName);
		
		// ASSERT
		assertNotNull(clientAccount.getContact());
	}
}
