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
}
