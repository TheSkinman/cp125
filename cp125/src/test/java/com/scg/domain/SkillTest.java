/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Norman Skinner
 *
 */
public class SkillTest {
	private Skill skill;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.scg.domain.Skill#getRate()}.
	 */
	@Test
	public void test_GetRate() {
		// ARRANGE
		skill = Skill.SOFTWARE_ENGINEER;
		
		// ACT
		int result = skill.getRate();
		
		// ASSERT
		assertEquals(150, result);
	}
	

	/**
	 * Test method for {@link com.scg.domain.Skill#getRate()}.
	 */
	@Test
	public void test_ToString() {
		// ARRANGE
		StringBuilder sb = new StringBuilder();
		String expected = "Project ManagerSoftware EngineerSoftware TesterSystem ArchitectUnknown Skill";
		
		// ACT
		sb.append(Skill.PROJECT_MANGER.toString());
		sb.append(Skill.SOFTWARE_ENGINEER.toString());
		sb.append(Skill.SOFTWARE_TESTER.toString());
		sb.append(Skill.SYSTEM_ARCHITECT.toString());
		sb.append(Skill.UNKNOWN_SKILL.toString());
		String result = sb.toString();
		
		// ASSERT
		assertEquals(expected, result);
	}
}
