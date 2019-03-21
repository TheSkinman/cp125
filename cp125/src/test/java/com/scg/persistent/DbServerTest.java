package com.scg.persistent;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.Invoice;
import com.scg.domain.NonBillableAccount;
import com.scg.domain.Skill;
import com.scg.domain.TimeCard;
import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;

/**
 * Unit tests (maybe more intergration) for DbServer.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
class DbServerTest {
    private static final String COMPANY_ZIPCODE = "98034";
    private static final StateCode COMPANY_STATE = StateCode.WA;
    private static final String COMPANY_CITY = "Kirkland";
    private static final String COMPANY_STREET = "123 Street";
    private static final String COMPANY_MIDDLE_NAME = "Company_Middle";
    private static final String COMPANY_FIRST_NAME = "Company_First";
    private static final String COMPANY_LAST_NAME = "Company_Last";
    private static final String TEST_COMPANY = "Test_Company";
    private static final String DB_URL = "jdbc:derby://localhost:1527/memory:scgDb";
    private static final String USERNAME = "student";
    private static final String PASSWORD = "student";
    private static DbServer db = null;

    @Mock
    private Connection mockConn;

    @Mock
    private PreparedStatement mockPs;

    @Mock
    private ResultSet mockRs;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        clearTables();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        clearTables();
    }

    @Test
    void test_Add_Get_Client() throws SQLException {
        // ARRANGE - regular
        db = new DbServer(DB_URL, USERNAME, PASSWORD);

        ClientAccount client = new ClientAccount(TEST_COMPANY, new PersonalName("last", "first", "middle"),
                new Address(COMPANY_STREET, COMPANY_CITY, COMPANY_STATE, "98034"));

        // ACT
        db.addClient(client);
        List<ClientAccount> result = db.getClients();

        // ASSERT
        assertEquals(1, result.size());
        assertEquals(client, result.get(0));
    }

    @Test
    void test_Add_Get_Consultant() throws SQLException {
        // ARRANGE - regular
        db = new DbServer(DB_URL, USERNAME, PASSWORD);

        Consultant consultant = new Consultant(new PersonalName("Gates", "Bill", "Mortimur"));

        // ACT
        db.addConsultant(consultant);
        List<Consultant> result = db.getConsultants();

        // ASSERT
        assertEquals(1, result.size());
        assertEquals(consultant, result.get(0));
    }

    @Test
    void test_AddTimeCard_GetInvoice() throws SQLException {
        // ARRANGE
        db = new DbServer(DB_URL, USERNAME, PASSWORD);

        /* save Client */
        ClientAccount client = new ClientAccount(TEST_COMPANY,
                new PersonalName(COMPANY_LAST_NAME, COMPANY_FIRST_NAME, COMPANY_MIDDLE_NAME),
                new Address(COMPANY_STREET, COMPANY_CITY, COMPANY_STATE, COMPANY_ZIPCODE));
        db.addClient(client);

        /* save Consultant */
        PersonalName consultantName = new PersonalName("Gates", "Bill", "Mortimur");
        Consultant consultantObj = new Consultant(consultantName);
        db.addConsultant(consultantObj);

        /* setup TimeCard */
        LocalDate timeCardStartDay = LocalDate.of(1968, 10, 8);
        ConsultantTime consultantTimeBillable = new ConsultantTime(timeCardStartDay, client, Skill.SOFTWARE_ENGINEER,
                8);
        ConsultantTime consultantTimeNonBillable = new ConsultantTime(timeCardStartDay, NonBillableAccount.VACATION,
                Skill.SOFTWARE_TESTER, 7);
        TimeCard timeCard = new TimeCard(consultantObj, timeCardStartDay);
        timeCard.addConsultantTime(consultantTimeBillable);
        timeCard.addConsultantTime(consultantTimeNonBillable);
        int calculatedCharges = Skill.SOFTWARE_ENGINEER.getRate() * consultantTimeBillable.getHours();
        LocalDate calculatedFirstDate = LocalDate.of(timeCardStartDay.getYear(), timeCardStartDay.getMonth(), 1);

        // ACT
        db.addTimeCard(timeCard);
        Invoice resultingInvoice = db.getInvoice(client, timeCardStartDay.getMonth(), timeCardStartDay.getYear());

        // ASSERT
        assertEquals(consultantTimeBillable.getHours(), resultingInvoice.getTotalHours());
        assertEquals(calculatedCharges, resultingInvoice.getTotalCharges());
        assertEquals(client, resultingInvoice.getClientAccount());
        assertEquals(timeCardStartDay.getMonth(), resultingInvoice.getInvoiceMonth());
        assertEquals(calculatedFirstDate, resultingInvoice.getStartDate());
    }

    private void clearTables() throws SQLException {
        String DROP_TABLE = "DELETE FROM %s";
        List<String> tablesToDrop = new ArrayList<>();
        tablesToDrop.add("non_billable_hours");
        tablesToDrop.add("billable_hours");
        tablesToDrop.add("timecards");
        tablesToDrop.add("consultants");
        tablesToDrop.add("clients");
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);) {
            for (String table : tablesToDrop) {
                String sql_command = String.format(DROP_TABLE, table);
                try (Statement st = conn.createStatement();) {
                    st.executeUpdate(sql_command);
                }
                conn.commit();
            }
        }

    }

}
