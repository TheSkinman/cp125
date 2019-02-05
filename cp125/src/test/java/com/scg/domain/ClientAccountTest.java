package com.scg.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;

/**
 * @author Norman Skinner
 *
 */
public class ClientAccountTest {
	private ClientAccount clientAccount; 
	private PersonalName personalName;
	private Address address;
	
	@BeforeEach
	public void setUp() throws Exception {
		personalName = new PersonalName("last", "first", "middle");
		address = new Address("1325 4th Ave #400", "Seattle", StateCode.WA, "98101");
	}

	@Test
	public void test_ClientAccountConstructor_String_PersonalName() {
		// ARRANGE
		String name = "Company Test";
		PersonalName contact = personalName;

		// ACT
		ClientAccount result = new ClientAccount(name, contact, address);
		
		// ASSERT
		assertEquals(name, result.getName());
		assertTrue(Objects.equals(personalName, result.getContact()));
		assertEquals(true, result.isBillable());
	}

	@Test
	public void test_GetName() {
		// ARRANGE
		clientAccount = new ClientAccount("test", null, null);
		
		// ACT
		String result = clientAccount.getName();
		
		// ASSERT
		assertEquals("test", result);
	}

    @Test
    public void test_SetAddress() {
        // ARRANGE
        clientAccount = new ClientAccount(null, null, null);
        
        // ACT
        clientAccount.setAddress(address);
        Address result = clientAccount.getAddress();
        
        // ASSERT
        assertEquals(address, result);
    }

	@Test
	public void test_IsBillable() {
		// ARRANGE
		clientAccount = new ClientAccount("test", null, null);
		
		// ACT
		boolean result = clientAccount.isBillable();
		
		// ASSERT
		assertTrue(result);
	}

	@Test
	public void test_GetContact() {
		// ARRANGE
		clientAccount = new ClientAccount("test", personalName, address);
		
		// ACT
		PersonalName result = clientAccount.getContact();
		
		// ASSERT
		assertTrue(Objects.equals(personalName, result));
	}

	@Test
	public void test_SetContact() {
		// ARRANGE
		clientAccount = new ClientAccount("test", null,null);
		
		// ACT
		clientAccount.setContact(personalName);
		
		// ASSERT
		assertNotNull(clientAccount.getContact());
	}
}
