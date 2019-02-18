package com.scg.util;

import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * Encapsulates a range of two dates, inclusive of the start date and end date.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class DateRange {
    private LocalDate endDate;
    private LocalDate startDate;

    /**
     * Returns the start date for this DateRange.
     * 
     * @return the startDate the start date for this DateRange.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date for this DateRange.
     * 
     * @return the end date for this DateRange.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Construct a DateRange given two dates.
     * 
     * @param startDate
     *            the start date for this DateRange.
     * @param endDate
     *            the end date for this DateRange.
     */
    public DateRange(LocalDate startDate, LocalDate endDate) {
        super();
        this.endDate = endDate;
        this.startDate = startDate;
    }

    /**
     * Construct a DateRange for the given month, the date range shall span the
     * entire month, from the first day of the month through the last day of the
     * month.
     * 
     * @param month
     *            the month for which this DateRange should be constructed.
     * @param year
     *            the calendar year
     */
    public DateRange(Month month, int year) {
        super();
        this.startDate = LocalDate.of(year, month, 1);
        this.endDate = startDate.with(lastDayOfMonth());
    }

    /**
     * Construct a DateRange given two date strings in the correct format.
     * 
     * @param start
     *            String representing the start date, of the form yyyy-MM-dd.
     * @param end
     *            String representing the end date, of the form yyyy-MM-dd.
     */
    public DateRange(String start, String end) {
        super();
        this.startDate = LocalDate.parse(start);
        this.endDate = LocalDate.parse(end);
    }

    /**
     * Returns true if the specified date is within the range start date &lt;= date &lt;=
     * end date.
     * 
     * @param date
     *            the date to check for being within this DateRange.
     * @return true if the specified date is within this DateRange.
     */
    public boolean isInRange(LocalDate date) {
        if (startDate.compareTo(date) <= 0 && endDate.compareTo(date) >= 0) {
            return true;
        }
        return false;
    }

}
