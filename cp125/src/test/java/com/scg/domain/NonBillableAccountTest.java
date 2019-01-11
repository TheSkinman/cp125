/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


/**
 * @author Norman Skinner
 *
 */
public class NonBillableAccountTest {

	/**
	 * Test method for {@link com.scg.domain.NonBillableAccount#getName()}.
	 */
	@Test
	public void test_GetName_forAll() {
		// ARRANGE
		NonBillableAccount businessDevelopment = NonBillableAccount.BUSINESS_DEVELOPMENT;
		NonBillableAccount sickLeave = NonBillableAccount.SICK_LEAVE;
		NonBillableAccount vacation = NonBillableAccount.VACATION;
		
		
		// ACT
		String businessDevelopmentResult = businessDevelopment.getName();
		String sickLeaveResult = sickLeave.getName();
		String vacationResult = vacation.getName();
		String directResult = NonBillableAccount.VACATION.getName();
		
		// ASSERT
		assertEquals("Business Development", businessDevelopmentResult);
		assertEquals("Sick Leave", sickLeaveResult);
		assertEquals("Vacation", vacationResult);
		assertEquals("Vacation", directResult);
	}

	/**
	 * Test method for {@link com.scg.domain.NonBillableAccount#isBillable()}.
	 */
	@Test
	public void test_IsBillable_returnsFalseForAll() {
		// ARRANGE
		NonBillableAccount businessDevelopment = NonBillableAccount.BUSINESS_DEVELOPMENT;
		NonBillableAccount sickLeave = NonBillableAccount.SICK_LEAVE;
		NonBillableAccount vacation = NonBillableAccount.VACATION;
		
		// ACT
		Boolean businessDevelopmentResult = businessDevelopment.isBillable();
		Boolean sickLeaveResult = sickLeave.isBillable();
		Boolean vacationResult = vacation.isBillable();
		
		// ASSERT
		assertFalse(businessDevelopmentResult);
		assertFalse(sickLeaveResult);
		assertFalse(vacationResult);
	}

	/**
	 * Test method for {@link com.scg.domain.NonBillableAccount#isBillable()}.
	 */
	@Test
	public void test_ToString_ForAll() {
		// ARRANGE
		StringBuilder sb = new StringBuilder();
		sb.append(NonBillableAccount.BUSINESS_DEVELOPMENT.toString());
		sb.append(", ");
		sb.append(NonBillableAccount.SICK_LEAVE.toString());
		sb.append(", ");
		sb.append(NonBillableAccount.VACATION.toString());
		
		// ACT
		String result = sb.toString();
		
		// ASSERT
		assertEquals("Business Development, Sick Leave, Vacation", result);
	}
}
