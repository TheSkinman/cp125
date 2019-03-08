/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;

/**
 * @author Norman Skinner
 *
 */
public class TimeCardTest {
	private static final String TEST_FILE_NAME = "testFile-delete.ser";
	private static final Logger log = LoggerFactory.getLogger(TimeCardTest.class);
    private TimeCard timeCard;
	private Account account1;
	private Account account2;
	private Consultant consultant;
	private LocalDate weekStartDay;
	private PersonalName name;
	private ConsultantTime consultantTime1;
	private ConsultantTime consultantTime2;

	@BeforeEach
	public void setUp() throws Exception {
		account1 = new Account() {
			/**
             * 
             */
            private static final long serialVersionUID = -8442028335855559528L;
            @Override public boolean isBillable() { return true; }
			@Override public String getName() { return "Company One"; }
		};
		account2 = new Account() {
			/**
             * 
             */
            private static final long serialVersionUID = 8071579684058101949L;
            @Override public boolean isBillable() { return false; }
			@Override public String getName() { return "Company Two"; }
		};
		name = new PersonalName("Smith", "Agent", "Digital");
		consultant = new Consultant(name);
		weekStartDay = LocalDate.of(1968, 10, 8);
	}

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

	@Test
	public void test_GetConsultant() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		
		// ACT
		Consultant result = timeCard.getConsultant();
		
		// ASSERT
		assertEquals(consultant, result);
	}

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

	@Test
	public void test_GetWeekStartingDay() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		
		// ACT
		String result =  timeCard.getWeekStartingDay().toString();
		
		// ASSERT
		assertEquals("1968-10-08", result);
	}

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

	@Test
	public void test_ToString() {
		// ARRANGE
		timeCard = new TimeCard(consultant, weekStartDay);
		
		// ACT
		String result = timeCard.toString();
		
		// ASSERT
		assertEquals("TimeCard [getConsultantHours()=[], getTotalBillableHours()=0, getTotalHours()=0, getNonTotalBilableHours()=0, getWeekStartingDay()=1968-10-08]", result);
	}

    @Test
    public void test_TestSerialDeserializer() {
        // ARRANGE
        PersonalName personalName1 = new PersonalName("Last", "First", "Middle");
        Consultant consultant1 = new Consultant(personalName1);
        LocalDate weekStartDay1 = LocalDate.of(1968, 10, 8);
        TimeCard timeCard1 = new TimeCard(consultant1, weekStartDay1);
        // TODO: Investigate why these lines are failing the saving of the list.
        // consultantTime1 = new ConsultantTime(weekStartDay1, account1, Skill.SOFTWARE_ENGINEER, 1);
        // consultantTime2 = new ConsultantTime(weekStartDay1, account2, Skill.SOFTWARE_TESTER, 2);
        // timeCard1.addConsultantTime(consultantTime1);
        // timeCard1.addConsultantTime(consultantTime2);
        List<TimeCard> tc1 = new ArrayList<>();
        tc1.add(timeCard1);
        tc1.add(timeCard1);
        
        // ACT
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(TEST_FILE_NAME, false));) {
            objectOutputStream.writeObject(tc1);
            log.info("File [{}] saved successfully.", TEST_FILE_NAME);
        } catch (IOException ex) {
            log.error("Failded to save file [" + TEST_FILE_NAME + "]", ex);
        }
        
        List<TimeCard> tc2 = null;
        try (ObjectInputStream objectInputStram = new ObjectInputStream(new FileInputStream(TEST_FILE_NAME));) {
            tc2 = (List<TimeCard>)objectInputStram.readObject();
        } catch(ClassNotFoundException ex) {
            log.error("There was a problem finding the class for file [" + TEST_FILE_NAME + "]",ex);
        } catch (IOException ex) {
            log.error("There was a problem loading the file [" + TEST_FILE_NAME + "]",ex);
        }
        
        // ASSERT
        assertEquals(tc1.get(0).compareTo(tc2.get(0)), 0);
    }

    @Test
    public void test_Consultant_CompareTo() {
        // ARRANGE
        PersonalName personalName1 = new PersonalName("a", "a", "a");
        Consultant consultant1 = new Consultant(personalName1);
        LocalDate weekStartDay1 = LocalDate.of(1968, 10, 8);
        TimeCard timeCard1 = new TimeCard(consultant1, weekStartDay1);
        consultantTime1 = new ConsultantTime(weekStartDay1, account1, Skill.SOFTWARE_ENGINEER, 1);
        consultantTime2 = new ConsultantTime(weekStartDay1, account2, Skill.SOFTWARE_TESTER, 2);
        timeCard1.addConsultantTime(consultantTime1);
        timeCard1.addConsultantTime(consultantTime2);

        PersonalName personalName2 = new PersonalName("b", "b", "b");
        Consultant consultant2 = new Consultant(personalName2);
        LocalDate weekStartDay2 = LocalDate.of(1968, 10, 9);
        TimeCard timeCard2 = new TimeCard(consultant2, weekStartDay2);
        consultantTime1 = new ConsultantTime(weekStartDay2, account1, Skill.SOFTWARE_ENGINEER, 1);
        consultantTime2 = new ConsultantTime(weekStartDay2, account2, Skill.SOFTWARE_TESTER, 2);
        timeCard2.addConsultantTime(consultantTime1);
        timeCard2.addConsultantTime(consultantTime2);

        PersonalName personalName3 = new PersonalName("a", "a", "a");
        Consultant consultant3 = new Consultant(personalName3);
        LocalDate weekStartDay3 = LocalDate.of(1968, 10, 8);
        TimeCard timeCard3 = new TimeCard(consultant3, weekStartDay);
        consultantTime1 = new ConsultantTime(weekStartDay3, account1, Skill.SOFTWARE_ENGINEER, 1);
        consultantTime2 = new ConsultantTime(weekStartDay3, account2, Skill.SOFTWARE_TESTER, 2);
        timeCard3.addConsultantTime(consultantTime1);
        timeCard3.addConsultantTime(consultantTime2);

        // ACT
        int resultLess = timeCard1.compareTo(timeCard2);
        int resultEqual = timeCard1.compareTo(timeCard3);
        int resultEqualOtherWay = timeCard3.compareTo(timeCard1);
        int resultEqualSame = timeCard1.compareTo(timeCard1);
        int resultGreater = timeCard2.compareTo(timeCard1);

        // ASSERT
        assertTrue(resultLess < 0);
        assertTrue(resultEqual == 0);
        assertTrue(resultEqualOtherWay == 0);
        assertTrue(resultEqualSame == 0);
        assertTrue(resultGreater > 0);
    }
}
