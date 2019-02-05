/**
 * 
 */
package com.scg.domain;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests, now with more units! 
 * 
 * @author Norman Skinner
 *
 */
public class ConsultantTimeTest {
	private ConsultantTime consultantTime;
	private Account account;
	private Account accountOther;
	private LocalDate date;
	private Skill skillType;
	private int hours;
	
	@BeforeEach
	public void setUp() throws Exception {
		account = new Account() {
			private String name = "Company Name";

			@Override
			public boolean isBillable() {
				return false;
			}

			@Override
			public String getName() {
				return name;
			}
		};
		date = LocalDate.of(1968, 10, 8);
		skillType = Skill.SOFTWARE_ENGINEER;
		hours = 8;
		accountOther = new Account() {
			private String name = "Another Company";

			@Override
			public boolean isBillable() {
				return true;
			}

			@Override
			public String getName() {
				return name;
			}
		};
	}

	@Test
	public void test_HashCode_WithNulls() {
		// ARRANGE
		consultantTime = new ConsultantTime(null, null, null, 1);
		
		// ACT
		int result = consultantTime.hashCode();
		
		// ASSERT
		assertEquals(923552, result);
	}

	@Test
	public void test_HashCode_WithValues() { 
		// ARRANGE
		ConsultantTime consultantTime1 = new ConsultantTime(date, account, skillType, 1);
		ConsultantTime consultantTime2 = new ConsultantTime(date, account, skillType, 1);
		
		// ACT
		int result1 = consultantTime1.hashCode();
		int result2 = consultantTime2.hashCode();
		
		// ASSERT
		assertEquals(result1, result2);
	}

	@Test
	public void test_ConsultantTimeConstructor() {
		// ARRANGE
		date = LocalDate.of(1111, 11, 11);
		skillType = Skill.PROJECT_MANGER;
		hours = 42;
		
		// ACT
		consultantTime = new ConsultantTime(date, account, skillType, hours);

		// ASSERT
		assertNotNull(consultantTime);
		assertNotNull(consultantTime.getAccount());
		assertEquals(date, consultantTime.getDate());
		assertEquals(skillType, consultantTime.getSkill());
		assertEquals(hours, consultantTime.getHours());
		assertEquals(account.getName(), consultantTime.getAccount().getName());
		assertEquals(false, consultantTime.isBillable());
	}

	@Test
	public void test_ConsultantTimeConstructor_BadHour() {
		// ARRANGE
		hours = 0;
		
		// ACT & ASSERT
		assertThrows(IllegalArgumentException.class, () -> {
			consultantTime = new ConsultantTime(date, account, skillType, hours);
		});
	}

	@Test
	public void test_EqualsObject() {
		// ARRANGE
		consultantTime = new ConsultantTime(null, null, null, 1);
		ConsultantTime consultantTime02 = new ConsultantTime(null, null, null, 1);

		// ACT
		boolean result01 = consultantTime.equals(consultantTime02);
		boolean result02 = consultantTime.equals(consultantTime);
		boolean result03 = consultantTime.equals(null);
		@SuppressWarnings("unlikely-arg-type")
		boolean result04 = consultantTime.equals(account);
		consultantTime02 = new ConsultantTime(date, account, skillType, 1);
		boolean result05 = consultantTime.equals(consultantTime02);
		consultantTime02 = new ConsultantTime(date, null, skillType, 1);
		boolean result06 = consultantTime.equals(consultantTime02);
		consultantTime   = new ConsultantTime(date, account, skillType, 1);
		boolean result07 = consultantTime.equals(consultantTime02);
		consultantTime02 = new ConsultantTime(date, accountOther, skillType, 1);
		boolean result08 = consultantTime.equals(consultantTime02);
		consultantTime02 = new ConsultantTime(LocalDate.of(1212, 12, 12), account, skillType, 1);
		boolean result09 = consultantTime.equals(consultantTime02);
		consultantTime02 = new ConsultantTime(date, account, skillType, 2);
		boolean result10 = consultantTime.equals(consultantTime02);
		consultantTime02 = new ConsultantTime(date, account, Skill.UNKNOWN_SKILL, 1);
		boolean result11 = consultantTime.equals(consultantTime02);
		
		// ASSERT
		assertTrue(result01);
		assertTrue(result02);
		assertFalse(result03);
		assertFalse(result04);
		assertFalse(result05);
		assertFalse(result06);
		assertFalse(result07);
		assertFalse(result08);
		assertFalse(result09);
		assertFalse(result10);
		assertFalse(result11);
	}

	@Test
	public void test_SetAccount() {
		// ARRANGE
		consultantTime = new ConsultantTime(date, null, skillType, hours);

		// ACT
		consultantTime.setAccount(account);
		
		// ASSERT
		assertTrue(Objects.equals(account, consultantTime.getAccount()));
	}

	@Test
	public void test_SetDate() {
		// ARRANGE
		consultantTime = new ConsultantTime(date, account, skillType, hours);
		LocalDate testDate = LocalDate.of(1212, 12, 12);

		// ACT
		consultantTime.setDate(testDate);
		
		// ASSERT
		assertEquals(testDate, consultantTime.getDate());
	}

	@Test
	public void test_SetHours() {
		// ARRANGE
		consultantTime = new ConsultantTime(date, account, skillType, hours);

		// ACT
		consultantTime.setHours(123);

		// ASSERT
		assertEquals(123, consultantTime.getHours());
	}

	@Test
	public void test_SetHours_BadHours() {
		// ARRANGE
		consultantTime = new ConsultantTime(date, account, skillType, hours);

		// ACT & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			consultantTime.setHours(0);
		});
	}

	@Test
	public void test_ToString() {
		// ARRANGE
		consultantTime = new ConsultantTime(date, account, skillType, hours);

		// ACT
		String result = consultantTime.toString();

		// ASSERT
		String expected = "Company Name                 10/08/1968      8  Software Engineer\r\n";
		assertEquals(expected, result);
	}
}
