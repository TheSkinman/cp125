/**
 * 
 */
package com.scg.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
class ListFactoryTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    void cleanUp() {
        System.setOut(originalOut);
    }

    @Test
    void test_PopulateLists() {
        // ARRANGE
        final List<ClientAccount> accounts = new ArrayList<ClientAccount>();
        final List<Consultant> consultants = new ArrayList<Consultant>();
        final List<TimeCard> timeCards = new ArrayList<TimeCard>();
        
        // ACT
        ListFactory.populateLists(accounts, consultants, timeCards);
        
        // ASSERT
        assertEquals(2, accounts.size());
    }

    @Test
    void test_PrintTimeCards() {
        // ARRANGE
        final List<ClientAccount> accounts = new ArrayList<ClientAccount>();
        final List<Consultant> consultants = new ArrayList<Consultant>();
        final List<TimeCard> timeCards = new ArrayList<TimeCard>();
        ListFactory.populateLists(accounts, consultants, timeCards);
        
        // ACT
        ListFactory.printTimeCards(timeCards);
        String result = outContent.toString().replaceAll("\n", "").replaceAll("\r", "");
        
        // ASSERT
        assertEquals(getExpectedTimeCard(), result);
        
    }
    
    private String getExpectedTimeCard() {
        String returnTimeCard = "====================================================================" + 
                "Consultant: Coder, Carl                  Week Starting: Feb 27, 2017" + 
                "" + 
                "Billable Time:" + 
                "Account                      Date        Hours  Skill" + 
                "---------------------------  ----------  -----  --------------------" + 
                "Acme Industries              02/27/2017      8  Software Engineer" + 
                "Acme Industries              02/28/2017      8  Software Engineer" + 
                "FooBar Enterprises           03/01/2017      8  Software Engineer" + 
                "FooBar Enterprises           03/02/2017      8  Software Engineer" + 
                "FooBar Enterprises           03/03/2017      8  Software Engineer" + 
                "" + 
                "Non-billable Time:" + 
                "Account                      Date        Hours  Skill" + 
                "---------------------------  ----------  -----  --------------------" + 
                "" + 
                "Summary:" + 
                "Total Billable:                             40" + 
                "Total Non-Billable:                          0" + 
                "Total Hours:                                40" + 
                "====================================================================" + 
                "" + 
                "====================================================================" + 
                "Consultant: Coder, Carl                  Week Starting: Mar 06, 2017" + 
                "" + 
                "Billable Time:" + 
                "Account                      Date        Hours  Skill" + 
                "---------------------------  ----------  -----  --------------------" + 
                "Acme Industries              03/06/2017      8  Software Engineer" + 
                "Acme Industries              03/07/2017      8  Software Engineer" + 
                "FooBar Enterprises           03/08/2017      8  Software Engineer" + 
                "FooBar Enterprises           03/09/2017     12  Software Engineer" + 
                "" + 
                "Non-billable Time:" + 
                "Account                      Date        Hours  Skill" + 
                "---------------------------  ----------  -----  --------------------" + 
                "Vacation                     03/10/2017      8  Software Engineer" + 
                "" + 
                "Summary:" + 
                "Total Billable:                             36" + 
                "Total Non-Billable:                          8" + 
                "Total Hours:                                44" + 
                "====================================================================" + 
                "" + 
                "====================================================================" + 
                "Consultant: Architect, Ann S.            Week Starting: Mar 06, 2017" + 
                "" + 
                "Billable Time:" + 
                "Account                      Date        Hours  Skill" + 
                "---------------------------  ----------  -----  --------------------" + 
                "FooBar Enterprises           03/06/2017      8  System Architect" + 
                "FooBar Enterprises           03/08/2017      8  System Architect" + 
                "FooBar Enterprises           03/09/2017      8  System Architect" + 
                "FooBar Enterprises           03/10/2017      8  System Architect" + 
                "FooBar Enterprises           03/11/2017      8  System Architect" + 
                "" + 
                "Non-billable Time:" + 
                "Account                      Date        Hours  Skill" + 
                "---------------------------  ----------  -----  --------------------" + 
                "Sick Leave                   03/07/2017      8  System Architect" + 
                "" + 
                "Summary:" + 
                "Total Billable:                             40" + 
                "Total Non-Billable:                          8" + 
                "Total Hours:                                48" + 
                "====================================================================" + 
                "" + 
                "====================================================================" + 
                "Consultant: Architect, Ann S.            Week Starting: Mar 13, 2017" + 
                "" + 
                "Billable Time:" + 
                "Account                      Date        Hours  Skill" + 
                "---------------------------  ----------  -----  --------------------" + 
                "FooBar Enterprises           03/13/2017      8  System Architect" + 
                "FooBar Enterprises           03/14/2017      8  System Architect" + 
                "FooBar Enterprises           03/17/2017      8  System Architect" + 
                "" + 
                "Non-billable Time:" + 
                "Account                      Date        Hours  Skill" + 
                "---------------------------  ----------  -----  --------------------" + 
                "Business Development         03/15/2017      8  System Architect" + 
                "Business Development         03/16/2017      8  System Architect" + 
                "" + 
                "Summary:" + 
                "Total Billable:                             24" + 
                "Total Non-Billable:                         16" + 
                "Total Hours:                                40" + 
                "====================================================================";
        return returnTimeCard;
    }

}
