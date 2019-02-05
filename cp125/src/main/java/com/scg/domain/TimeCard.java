package com.scg.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents a time card capable of storing a collection of a consultant's
 * billable and non-billable hours for a week. The TimeCard maintains a
 * collection of ConsultantTime, and provides access to number of hours and time
 * billed to a particular client.
 * 
 * @author Norman Skinner
 *
 */
public class TimeCard {

    private static final String DATE = "Date";
    private static final String ACCOUNT = "Account";
    private static final String TOTAL_HOURS = "Total Hours:";
    private static final String TOTAL_NON_BILLABLE = "Total Non-Billable:";
    private static final String TOTAL_BILLABLE = "Total Billable:";
    private Set<String> accountNames;
    private List<ConsultantTime> consultantHours;
    private Consultant consultant;
    private LocalDate weekStartingDay;
    private int totalBillableHours;
    private int totalHours;

    /**
     * Creates a new instance of TimeCard
     * 
     * @param consultant
     *            The Consultant whose information this TimeCard records.
     * @param weekStartDay
     *            The date of the first work day of the week this TimeCard records
     *            information for.
     */
    public TimeCard(Consultant consultant, LocalDate weekStartDay) {
        super();
        accountNames = new HashSet<>();
        consultantHours = new ArrayList<>();
        this.consultant = consultant;
        this.weekStartingDay = weekStartDay;
    }

    /**
     * Getter for consultant property.
     * 
     * @return value of consultant property.
     */
    public Consultant getConsultant() {
        return consultant;
    }

    /**
     * Getter for billableHours property.
     * 
     * @return value of billableHours property
     */
    public int getTotalBillableHours() {
        return totalBillableHours;
    }

    /**
     * Getter for totalNonBillableHours property.
     * 
     * @return Value of totalNonBillableHours property
     */
    public int getTotalNonBillableHours() {
        return totalHours - totalBillableHours;
    }

    /**
     * Getter for consultingHours property.
     * 
     * @return Value of consultingHours property
     */
    public List<ConsultantTime> getConsultantHours() {
        // TODO: make unchangeable
        return consultantHours;
    }

    /**
     * Add a ConsultantTime object to the collection maintained by this TimeCard.
     * 
     * @param consultantTime
     *            The ConsultantTime to add
     */
    public void addConsultantTime(ConsultantTime consultantTime) {
        accountNames.add(consultantTime.getAccount().getName());
        totalHours += consultantTime.getHours();
        if (consultantTime.isBillable()) {
            totalBillableHours += consultantTime.getHours();
        }
        consultantHours.add(consultantTime);
    }

    /**
     * Getter for totalHours property.
     * 
     * @return Value of totalHours property
     */
    public int getTotalHours() {
        return totalHours;
    }

    /**
     * Getter for weekStartingDay property.
     * 
     * @return Value of weekStartingDay property.
     */
    public LocalDate getWeekStartingDay() {
        return weekStartingDay;
    }

    /**
     * Returns the billable hours (if any) in this TimeCard for the specified
     * Client. Finds all the billable hours in the collection of ConsultantTime
     * objects where the client matched the provided client and returns a new list
     * in containing only these ConsultantTime objects.
     * 
     * @param clientName
     *            of the client to extract hours for.
     * @return list of billable hours for the client.
     */
    public List<ConsultantTime> getBillableHoursForClient(String clientName) {

        List<ConsultantTime> theReturn = (List<ConsultantTime>) consultantHours.stream()
                .filter(p -> p.isBillable() && p.getAccount().getName() == clientName).collect(Collectors.toList());

        return theReturn;
    }

    /**
     * Create a string representation of this object, consisting of the consultant
     * name and the time card week starting day.
     * 
     * @return a string containing the consultant name and the time card week
     *         starting day
     */
    @Override
    public String toString() { // TimeCar for <consultant> day of week
        return "TimeCard [getConsultantHours()=" + getConsultantHours() + ", getTotalBillableHours()="
                + getTotalBillableHours() + ", getTotalHours()=" + getTotalHours() + ", getNonTotalBilableHours()="
                + getTotalNonBillableHours() + ", getWeekStartingDay()=" + getWeekStartingDay() + "]";
    }

    /**
     * Create a string representation of this object, suitable for printing the
     * entire time card.
     * 
     * @return this TimeCard as a formatted String.
     */
    public String toReportString() {
        final String LINE_DOUBLE = StringUtils.repeat("=", 68) + "\n";
        StringBuilder sb = new StringBuilder();
        try (Formatter fmtr = new Formatter(sb, Locale.US);) {

            // Start the block
            fmtr.format(LINE_DOUBLE)

                    // Consultant and Week Starting Date

                    .format("Consultant: %1$-28s Week Starting: %2$tb %2$td, %2$tY%n", getConsultant(),
                            getWeekStartingDay())

                    // Billable Headers
                    .format("%nBillable Time:%n").format("%-27s  %-10s  Hours  Skill%n", ACCOUNT, DATE)
                    .format(StringUtils.repeat("-", 27) + "  ").format(StringUtils.repeat("-", 10) + "  ")
                    .format(StringUtils.repeat("-", 5) + "  ").format(StringUtils.repeat("-", 20) + "\n");

            // Print only consultant hours that are billable
            getConsultantHours().forEach(p -> {
                if (p.isBillable())
                    fmtr.format(p.toString());
            });

            // Non-Billable Headers
            fmtr.format("%nNon-billable Time:%n").format("%-27s  %-10s  Hours  Skill%n", ACCOUNT, DATE)
                    .format(StringUtils.repeat("-", 27) + "  ").format(StringUtils.repeat("-", 10) + "  ")
                    .format(StringUtils.repeat("-", 5) + "  ").format(StringUtils.repeat("-", 20) + "\n");

            // Print only consultant hours that are NOT billable
            getConsultantHours().forEach(p -> {
                if (!p.isBillable())
                    fmtr.format(p.toString());
            });

            // Summary
            fmtr.format("%nSummary:%n").format("%-39s  %5d%n", TOTAL_BILLABLE, getTotalBillableHours())
                    .format("%-39s  %5d%n", TOTAL_NON_BILLABLE, getTotalNonBillableHours())
                    .format("%-39s  %5d%n", TOTAL_HOURS, getTotalHours())

                    // End this block
                    .format(LINE_DOUBLE);
        }
        return sb.toString();
    }
}
