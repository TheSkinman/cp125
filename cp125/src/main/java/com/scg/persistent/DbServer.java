package com.scg.persistent;

import java.time.Month;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.Invoice;
import com.scg.domain.InvoiceLineItem;
import com.scg.domain.NonBillableAccount;
import com.scg.domain.Skill;
import com.scg.domain.TimeCard;
import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;

/**
 * Provides a programmatic interface to store and access objects in the
 * database.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class DbServer {
    private static final Logger log = LoggerFactory.getLogger(DbServer.class);
    private static final String INSERT_CLIENTS = "INSERT INTO clients (name, street, city, state, postal_code,contact_last_name, contact_first_name, contact_middle_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CLIENTS = "SELECT name, street, city, state, postal_code,contact_last_name, contact_first_name, contact_middle_name FROM clients";
    private static final String INSERT_CONSULTANT = "INSERT INTO CONSULTANTS (LAST_NAME, FIRST_NAME, MIDDLE_NAME) VALUES (?, ?, ?)";
    private static final String SELECT_CONSULTANTS = "SELECT last_name, first_name, middle_name FROM consultants";
    private static final String SELECT_CONSULTANT_ID = "SELECT id FROM consultants WHERE last_name = ? AND first_name = ? AND middle_name = ?";
    private static final String INSERT_TIMECARD = "INSERT INTO timecards (consultant_id, start_date) VALUES (?, ?)";
    private static final String INSERT_NON_BILLABLE_HOURS = "INSERT INTO non_billable_hours (account_name, timecard_id, date, hours) VALUES (?, ?, ?, ?)";
    private static final String INSERT_BILLABLE_HOURS = "INSERT INTO billable_hours (client_id, timecard_id, date, skill, hours) VALUES ((SELECT DISTINCT id FROM clients WHERE name = ?), ?, ?, ?, ?)";
    private static final String SELECT_INVOICE = "SELECT b.date, c.last_name, c.first_name, c.middle_name, b.skill, s.rate, b.hours FROM billable_hours b, consultants c, skills s, timecards t WHERE b.client_id = (SELECT DISTINCT id FROM clients WHERE name = ?) AND b.skill = s.name AND b.timecard_id = t.id AND c.id = t.consultant_id AND b.date >= ? AND b.date <= ?";

    private final String dbUrl;
    private final String username;
    private final String password;

    @SuppressWarnings("unused")
    private DbServer() {
        this.dbUrl = null;
        this.username = null;
        this.password = null;
    }

    /**
     * Constructor.
     * 
     * @param dbUrl
     *            the database URL
     * @param username
     *            the database username
     * @param password
     *            the database password
     */
    public DbServer(final String dbUrl, final String username, final String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }

    /**
     * Add a client to the database, inserts one row in the clients table.
     * 
     * @param client
     *            the client to add
     * @throws SQLException
     *             if any database operations fail
     */
    public void addClient(ClientAccount client) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);) {
            try (PreparedStatement ps = conn.prepareStatement(INSERT_CLIENTS);) {
                ps.setString(1, client.getName());
                ps.setString(2, client.getAddress().getStreetNumber());
                ps.setString(3, client.getAddress().getCity());
                ps.setString(4, client.getAddress().getState().toString());
                ps.setString(5, client.getAddress().getPostalCode());
                ps.setString(6, client.getContact().getLastName());
                ps.setString(7, client.getContact().getFirstName());
                ps.setString(8, client.getContact().getMiddleName());
                ps.execute();
                conn.commit();
            }
        }
    }

    /**
     * Get all of the clients in the database, selects all rows from the clients
     * table.
     * 
     * @return a list of all of the clients
     * @throws SQLException
     *             if any database operations fail
     */
    public List<ClientAccount> getClients() throws SQLException {
        List<ClientAccount> returnClientAccount = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);) {
            try (Statement stmt = conn.createStatement();) {
                try (ResultSet rs = stmt.executeQuery(SELECT_CLIENTS);) {
                    while (rs.next()) {
                        String name = rs.getString("name");
                        String streetNumber = rs.getString("street");
                        String city = rs.getString("city");
                        String state = rs.getString("state");
                        String postalCode = rs.getString("postal_code");
                        String lastName = rs.getString("contact_last_name");
                        String firstName = rs.getString("contact_first_name");
                        String middleName = rs.getString("contact_middle_name");
                        PersonalName contact = new PersonalName(lastName, firstName, middleName);
                        Address address = new Address(streetNumber, city, StateCode.valueOf(state), postalCode);
                        returnClientAccount.add(new ClientAccount(name, contact, address));
                    }
                }
            }
        }
        return returnClientAccount;
    }

    /**
     * Add a consultant to the database, inserts one row in the consultants table.
     * 
     * @param consultant
     *            the consultant to add
     * @throws SQLException
     *             if any database operations fail
     */
    public void addConsultant(Consultant consultant) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);) {
            try (PreparedStatement ps = conn.prepareStatement(INSERT_CONSULTANT);) {
                ps.setString(1, consultant.getName().getLastName());
                ps.setString(2, consultant.getName().getFirstName());
                ps.setString(3, consultant.getName().getMiddleName());
                ps.execute();
                conn.commit();
            }
        }
    }

    /**
     * Get all of the consultant in the database, selects all rows from the
     * consultants table.
     * 
     * @return a list of all of the consultants
     * @throws SQLException
     *             if any database operations fail
     */
    public List<Consultant> getConsultants() throws SQLException {
        List<Consultant> returnConsultant = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);) {
            try (Statement stmt = conn.createStatement();) {
                try (ResultSet rs = stmt.executeQuery(SELECT_CONSULTANTS);) {
                    while (rs.next()) {
                        String lastName = rs.getString("last_name");
                        String firstName = rs.getString("first_name");
                        String middleName = rs.getString("middle_name");
                        returnConsultant.add(new Consultant(new PersonalName(lastName, firstName, middleName)));
                    }
                }
            }
        }
        return returnConsultant;
    }

    /**
     * Add a timecard to the database, inserts one row in the timecards table as
     * well as zero or more rows in the billable_hours and non_billable_hours
     * tables. The process is as follows: obtain the consultant id of the consultant
     * the time card is for from the consultants table insert a new record in the
     * timecards table, and capture the generated key for this record for each time
     * entry on the time card insert a record into the billable_hours or
     * non_billable_hours as appropriate including the timecard_id of the newly
     * created timecards record
     * 
     * @param timeCard
     *            the timecard to add
     * @throws SQLException
     *             if any database operations fail
     */
    public void addTimeCard(TimeCard timeCard) throws SQLException {
        // Get the consultants ID number from the consultant table.
        int consultant_id = 0;
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);) {
            try (PreparedStatement ps = conn.prepareStatement(SELECT_CONSULTANT_ID);) {
                ps.setString(1, timeCard.getConsultant().getName().getLastName());
                ps.setString(2, timeCard.getConsultant().getName().getFirstName());
                ps.setString(3, timeCard.getConsultant().getName().getMiddleName());
                try (ResultSet rs = ps.executeQuery();) {
                    if (rs.next())
                        consultant_id = rs.getInt("id");
                }
            }
        }

        // Insert new record into the timecard table and get the ID for the new record.
        int timecard_id = 0;
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);) {
            try (PreparedStatement ps = conn.prepareStatement(INSERT_TIMECARD, Statement.RETURN_GENERATED_KEYS);) {
                ps.setInt(1, consultant_id);
                ps.setString(2, timeCard.getWeekStartingDay().toString());
                ps.execute();
                conn.commit();
                try (ResultSet rs = ps.getGeneratedKeys();) {
                    if (rs.next())
                        timecard_id = rs.getInt(1);
                }
            }
        }

        // Insert the non billable hours
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);) {
            try (PreparedStatement ps = conn.prepareStatement(INSERT_NON_BILLABLE_HOURS);) {
                for (ConsultantTime ct : timeCard.getConsultantHours()) {
                    if (null != ct && !ct.isBillable()) {
                        ps.setString(1, ((NonBillableAccount)ct.getAccount()).name());
                        ps.setInt(2, timecard_id);
                        ps.setString(3, ct.getDate().toString());
                        ps.setInt(4, ct.getHours());
                        ps.execute();
                    }
                }
                conn.commit();
            }
        }

        // Insert the billable hours
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);) {
            try (PreparedStatement ps = conn.prepareStatement(INSERT_BILLABLE_HOURS);) {
                for (ConsultantTime ct : timeCard.getConsultantHours()) {
                    if (null != ct && ct.isBillable() ) {
                        ps.setString(1, ct.getAccount().getName());
                        ps.setInt(2, timecard_id);
                        ps.setString(3, ct.getDate().toString());
                        ps.setString(4, ct.getSkill().name());
                        ps.setInt(5, ct.getHours());
                        ps.execute();
                    }
                }
                conn.commit();
            }
        }
    }

    /**
     * Get clients monthly invoice. Select all of the data from the database needed
     * to construct the invoice (billable_hours, consultants, skills, timecards),
     * create the invoice and return it.
     * 
     * @param client
     *            the client to obtain the invoice line items for
     * @param month
     *            the month of the invoice
     * @param year
     *            the year of the invoice
     * @return the clients invoice for the month
     * @throws SQLException
     *             if any database operations fail
     */
    public Invoice getInvoice(ClientAccount client, Month month, int year) throws SQLException {
        Invoice returnInvoice = new Invoice(client, month, year);
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);) {
            try (PreparedStatement ps = conn.prepareStatement(SELECT_INVOICE);) {
                LocalDate monthStart = LocalDate.of(year, month, 1);
                LocalDate monthEnd = monthStart.plusMonths(1).minusDays(1);
                ps.setString(1, client.getName());
                ps.setString(2, monthStart.toString());
                ps.setString(3, monthEnd.toString());
                try (ResultSet rs = ps.executeQuery();) {
                    while (rs.next()) {
                        
                        
// SELECT b.date, c.last_name, c.first_name, c.middle_name, b.skill, s.rate, b.hours 

                        String lastName = rs.getString("last_name");
                        String firstName = rs.getString("first_name");
                        String middleName = rs.getString("middle_name");
                        LocalDate date = LocalDate.parse(rs.getString("date"));
                        Consultant consultant = new Consultant(new PersonalName(lastName, firstName, middleName));
                        Skill skill = Skill.valueOf(rs.getString("skill"));
                        int hours = rs.getInt("hours");
                        InvoiceLineItem lineItem = new InvoiceLineItem(date, consultant, skill, hours);
                        returnInvoice.addLineItem(lineItem );
                    }
                }
            }
        }
        return returnInvoice;
    }

}
