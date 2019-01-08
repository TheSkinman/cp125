/**
 * 
 */
package com.scg.domain;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.scg.util.PersonalName;

/**
 * @author Norman Skinner
 *
 */
public class TimeCardTest {
	private TimeCard timeCard;
	private Account account1;
	private Account account2;
	private Consultant consultant;
	private LocalDate weekStartDay;
	private PersonalName name;
	private ConsultantTime consultantTime1;
	private ConsultantTime consultantTime2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		account1 = new Account() {
			@Override public boolean isBillable() { return true; }
			@Override public String getName() { return "Company One"; }
		};
		account2 = new Account() {
			@Override public boolean isBillable() { return false; }
			@Override public String getName() { return "Company Two"; }
		};
		name = new PersonalName("Smith", "Agent", "Digital");
		consultant = new Consultant(name);
		weekStartDay = LocalDate.of(1968, 10, 8);
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#TimeCard(com.scg.domain.Consultant, java.time.LocalDate)}.
	 */
	@Test
	public void test_TimeCard_Constructor() {
		// ARRANGE
		// ACT
		timeCard = new TimeCard(consultant, weekStartDay);
		
		// ASSERT
		assertNotNull(timeCard);
		assertEquals("Smith", timeCard.getConsultant().getName().getLastName());
		assertEquals("Agent", timeCard.getConsultant().getName().getFirstName());
		assertEquals("Digital", timeCard.getConsultant().getName().getMiddleName());
		assertEquals("1968-10-08", timeCard.getWeekStartingDay().toString());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#addConsultantTime(com.scg.domain.ConsultantTime)}.
	 */
	@Test
	public void test_AddConsultantTime() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		consultantTime1 = new ConsultantTime(weekStartDay, account1, Skill.SOFTWARE_ENGINEER, 1);
		consultantTime2 = new ConsultantTime(weekStartDay, account2, Skill.SOFTWARE_TESTER, 2);
		
		// ACT
		timeCard.addConsultantTime(consultantTime1);
		timeCard.addConsultantTime(consultantTime2);
		
		// ASSERT
		assertEquals(3, timeCard.getTotalHours());
		assertEquals(1, timeCard.getTotalBillableHours());
		assertEquals(2, timeCard.getTotalNonBillableHours());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getBillableHoursForClient(java.lang.String)}.
	 */
	@Test
	public void test_GetBillableHoursForClient() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		consultantTime1 = new ConsultantTime(weekStartDay, account1, Skill.SOFTWARE_ENGINEER, 1);
		consultantTime2 = new ConsultantTime(weekStartDay, account2, Skill.SOFTWARE_TESTER, 2);
		timeCard.addConsultantTime(consultantTime1);
		timeCard.addConsultantTime(consultantTime2);
		
		// ACT
		List<ConsultantTime> results = timeCard.getBillableHoursForClient("Company One");
		
		// ASSERT
		assertEquals(1, results.size());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getConsultant()}.
	 */
	@Test
	public void test_GetConsultant() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		
		// ACT
		Consultant result = timeCard.getConsultant();
		
		// ASSERT
		assertEquals(consultant, result);
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getConsultantHours()}.
	 */
	@Test
	public void test_GetConsultantHours() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		consultantTime1 = new ConsultantTime(weekStartDay, account1, Skill.SOFTWARE_ENGINEER, 1);
		consultantTime2 = new ConsultantTime(weekStartDay, account2, Skill.SOFTWARE_TESTER, 2);
		timeCard.addConsultantTime(consultantTime1);
		timeCard.addConsultantTime(consultantTime2);
		
		// ACT
		List<ConsultantTime> results = timeCard.getConsultantHours();
		
		// ASSERT
		assertEquals(2, results.size());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getTotalBillableHours()}.
	 */
	@Test
	public void test_GetTotalBillableHours() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		consultantTime1 = new ConsultantTime(weekStartDay, account1, Skill.SOFTWARE_ENGINEER, 1);
		consultantTime2 = new ConsultantTime(weekStartDay, account2, Skill.SOFTWARE_TESTER, 2);
		
		// ACT
		timeCard.addConsultantTime(consultantTime1);
		timeCard.addConsultantTime(consultantTime2);
		
		// ASSERT
		assertEquals(3, timeCard.getTotalHours());
		assertEquals(1, timeCard.getTotalBillableHours());
		assertEquals(2, timeCard.getTotalNonBillableHours());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getTotalHours()}.
	 */
	@Test
	public void test_GetTotalHours() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		consultantTime1 = new ConsultantTime(weekStartDay, account1, Skill.SOFTWARE_ENGINEER, 1);
		consultantTime2 = new ConsultantTime(weekStartDay, account2, Skill.SOFTWARE_TESTER, 2);
		
		// ACT
		timeCard.addConsultantTime(consultantTime1);
		timeCard.addConsultantTime(consultantTime2);
		
		// ASSERT
		assertEquals(3, timeCard.getTotalHours());
		assertEquals(1, timeCard.getTotalBillableHours());
		assertEquals(2, timeCard.getTotalNonBillableHours());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getTotalNonBillableHours()}.
	 */
	@Test
	public void test_GetTotalNonBillableHours() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		consultantTime1 = new ConsultantTime(weekStartDay, account1, Skill.SOFTWARE_ENGINEER, 1);
		consultantTime2 = new ConsultantTime(weekStartDay, account2, Skill.SOFTWARE_TESTER, 2);
		timeCard.addConsultantTime(consultantTime1);
		timeCard.addConsultantTime(consultantTime2);
		
		// ACT
		int result = timeCard.getTotalNonBillableHours();
		
		// ASSERT
		assertEquals(2, result);
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getWeekStartingDay()}.
	 */
	@Test
	public void test_GetWeekStartingDay() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		
		// ACT
		String result =  timeCard.getWeekStartingDay().toString();
		
		// ASSERT
		assertEquals("1968-10-08", result);
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#toReportString()}.
	 */
	@Test
	public void test_ToReportString() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		consultantTime1 = new ConsultantTime(weekStartDay, account1, Skill.SOFTWARE_ENGINEER, 1);
		consultantTime2 = new ConsultantTime(weekStartDay, account2, Skill.SOFTWARE_TESTER, 2);
		timeCard.addConsultantTime(consultantTime1);
		timeCard.addConsultantTime(consultantTime2);
		
		// ACT
		String result = timeCard.toReportString();
		
		// ASSERT
		assertTrue(result.contains("====================================================================")); 
		assertTrue(result.contains("Consultant: Smith, Agent Digital         Week Starting: Oct 08, 1968"));
		assertTrue(result.contains("Billable Time:"));
		assertTrue(result.contains("Account                      Date        Hours  Skill"));
		assertTrue(result.contains("---------------------------  ----------  -----  --------------------"));
		assertTrue(result.contains("Company One                  10/08/1968      1  Software Engineer"));
		assertTrue(result.contains("Non-billable Time:"));
		assertTrue(result.contains("Account                      Date        Hours  Skill"));
		assertTrue(result.contains("---------------------------  ----------  -----  --------------------"));
		assertTrue(result.contains("Company Two                  10/08/1968      2  Software Tester"));
		assertTrue(result.contains("Summary:"));
		assertTrue(result.contains("Total Billable:                              1"));
		assertTrue(result.contains("Total Non-Billable:                          2"));
		assertTrue(result.contains("Total Hours:                                 3"));
		assertTrue(result.contains("===================================================================="));
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#toString()}.
	 */
	@Test
	public void test_ToString() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		
		// ACT
		String result = timeCard.toString();
		
		// ASSERT
		assertEquals("TimeCard [getConsultantHours()=[], getTotalBillableHours()=0, getTotalHours()=0, getNonTotalBilableHours()=0, getWeekStartingDay()=1968-10-08]", result);
	}
}
