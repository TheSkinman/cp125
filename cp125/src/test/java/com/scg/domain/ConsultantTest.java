/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import com.scg.util.PersonalName;

/**
 * @author Norman Skinner
 *
 */
public class ConsultantTest {
	private Consultant consultant;
	private PersonalName personalName;
	
	/**
	 * Test method for {@link com.scg.domain.Consultant#Consultant(com.scg.util.PersonalName)}.
	 */
	@Test
	public void test_ConsultantConstructor() {
		// ARRANGE
		personalName = new PersonalName("Name-Last", "Name-First", "Name-Middle");
		
		// ACT
		consultant = new Consultant(personalName);
		
		// ASSERT
		assertNotNull(consultant);
		assertNotNull(consultant.getName());
		assertEquals("Name-Last",consultant.getName().getLastName());
		assertEquals("Name-First",consultant.getName().getFirstName());
		assertEquals("Name-Middle",consultant.getName().getMiddleName());
	}

	/**
	 * Test method for {@link com.scg.domain.Consultant#toString()}.
	 */
	@Test
	public void test_ToString() {
		// ARRANGE
		personalName = new PersonalName("Name-Last", "Name-First", "Name-Middle");
		consultant = new Consultant(personalName);
		
		// ACT
		String result = consultant.toString();
		
		// ASSERT
		assertEquals("Name-Last, Name-First Name-Middle", result);
	}

	/**
	 * Test method for {@link com.scg.domain.Consultant#getName()}.
	 */
	@Test
	public void test_GetName() {
		// ARRANGE
		personalName = new PersonalName("Name-Last", "Name-First", "Name-Middle");
		consultant = new Consultant(personalName);
		
		// ACT
		PersonalName result = consultant.getName(); 
		
		// ASSERT
		assertTrue(Objects.equals(personalName, result));
	}

    @Test
    public void test_Consultant_CompareTo() {
        // ARRANGE
        PersonalName personalName1 = new PersonalName("a", "a", "a");
        Consultant consultant1 = new Consultant(personalName1); 
        PersonalName personalName2 = new PersonalName("b", "b", "b");
        Consultant consultant2 = new Consultant(personalName2); 
        PersonalName personalName3 = new PersonalName("a", "a", "a");
        Consultant consultant3 = new Consultant(personalName3); 

        // ACT
        int resultLess = consultant1.compareTo(consultant2);
        int resultEqual = consultant1.compareTo(consultant3);
        int resultEqualOtherWay = consultant3.compareTo(consultant1);
        int resultEqualSame = consultant1.compareTo(consultant1);
        int resultGreater = consultant2.compareTo(consultant1);

        // ASSERT
        assertTrue(resultLess < 0);
        assertTrue(resultEqual == 0);
        assertTrue(resultEqualOtherWay == 0);
        assertTrue(resultEqualSame == 0);
        assertTrue(resultGreater > 0);
    }
}
