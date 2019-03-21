package com.scg.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
	private static final String COMPANY = "Company";
    private ClientAccount clientAccount; 
    private ClientAccount clientAccoun2; 
	private PersonalName personalName;
	private PersonalName badPersonName;
	private Address address;
    private Address badAddress;
	
	@BeforeEach
	public void setUp() throws Exception {
		personalName = new PersonalName("last", "first", "middle");
		badPersonName = new PersonalName("AAAA", "BBBB", "CCCC");
		address = new Address("1325 4th Ave #400", "Seattle", StateCode.WA, "98101");
        badAddress = new Address("1 street", "Boise", StateCode.ID, "89022");
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
    void test_EqualsObject() {
        // ARRANGE
        clientAccount = new ClientAccount(COMPANY, personalName, address);
        clientAccoun2 = new ClientAccount(COMPANY, personalName, address);

        // ACT
        boolean result01 = clientAccount.equals(clientAccoun2);
        boolean result02 = clientAccount.equals(clientAccount);
        boolean result03 = clientAccount.equals(null);
        @SuppressWarnings("unlikely-arg-type")
        boolean result04 = clientAccount.equals(address);

        clientAccount = new ClientAccount(COMPANY, personalName, address);
        clientAccoun2 = new ClientAccount(null, null, null);

        boolean result05 = clientAccoun2.equals(clientAccount);
        clientAccount = new ClientAccount(COMPANY, null, address);
        boolean result06 = clientAccoun2.equals(clientAccount);
        clientAccount = new ClientAccount(COMPANY, null, address);
        boolean result07 = clientAccoun2.equals(clientAccount);
        clientAccount = new ClientAccount(COMPANY, null, null);
        boolean result08 = clientAccoun2.equals(clientAccount);
        clientAccount = new ClientAccount(null, null, null);
        boolean result09 = clientAccoun2.equals(clientAccount);

        clientAccount = new ClientAccount(COMPANY, personalName, address);
        boolean result10 = clientAccount.equals(clientAccoun2);
        clientAccoun2 = new ClientAccount(COMPANY, badPersonName, address);
        boolean result11 = clientAccount.equals(clientAccoun2);
        clientAccoun2 = new ClientAccount("Bad Company", personalName, address);
        boolean result12 = clientAccount.equals(clientAccoun2);
        clientAccoun2 = new ClientAccount(COMPANY, null, address);
        boolean result13 = clientAccount.equals(clientAccoun2);
        clientAccoun2 = new ClientAccount(COMPANY, personalName, badAddress);
        boolean result14 = clientAccount.equals(clientAccoun2);

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

    @Test
    public void test_Consultant_CompareTo() {
        // ARRANGE
        PersonalName personalName1 = new PersonalName("a", "a", "a");
        Address address1 = new Address("1 a", "a", StateCode.AK, "11111");
        ClientAccount clientAccount1 = new ClientAccount("a", personalName1, address1 ); 
        PersonalName personalName2 = new PersonalName("b", "b", "b");
        Address address2 = new Address("2 b", "b", StateCode.CA, "22222");
        ClientAccount clientAccount2 = new ClientAccount("b", personalName2, address2 ); 
        PersonalName personalName3 = new PersonalName("a", "a", "a");
        Address address3 = new Address("1 a", "a", StateCode.AK, "11111");
        ClientAccount clientAccount3 = new ClientAccount("a", personalName3, address3 ); 

        // ACT
        int resultLess = clientAccount1.compareTo(clientAccount2);
        int resultEqual = clientAccount1.compareTo(clientAccount3);
        int resultEqualOtherWay = clientAccount3.compareTo(clientAccount1);
        int resultEqualSame = clientAccount1.compareTo(clientAccount1);
        int resultGreater = clientAccount2.compareTo(clientAccount1);

        // ASSERT
        assertTrue(resultLess < 0);
        assertTrue(resultEqual == 0);
        assertTrue(resultEqualOtherWay == 0);
        assertTrue(resultEqualSame == 0);
        assertTrue(resultGreater > 0);
    }
}
