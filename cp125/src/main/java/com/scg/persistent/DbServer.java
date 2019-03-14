package com.scg.persistent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;
import com.scg.util.PersonalName;

/**
 * Provides a programmatic interface to store and access objects in the
 * database.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class DbServer {
    private static final String INSERT_CONSULTANT = "INSERT INTO CONSULTANTS (LAST_NAME, FIRST_NAME, MIDDLE_NAME) VALUES (?, ?, ?)";
    private static final String SELECT_CONSULTANTS = "SELECT last_name, first_name, middle_name FROM consultants";
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
        // List<ClientAccount> returnClients = new ArrayList<>();
        // try (Connection conn = DriverManager.getConnection(dbUrl, username,
        // password);) {
        // Statement stmt = conn.createStatement();
        // ResultSet rs = stmt.executeQuery(SELECT_CONSULTANTS);
        //
        // while (rs.next()) {
        // returnClients.add(new Clie)
        // System.out.print(rs.getString("NAME") + "\n");
        // }
        //
        // System.out.println("DONE");
        // return null;
        // }
        //
        return null;
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
    public Invoice getInvoice(ClientAccount client, java.time.Month month, int year) throws SQLException {
        return null;
    }

}
