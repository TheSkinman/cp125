package com.scg.util;

import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class DateRange {
    private LocalDate endDate;
    private LocalDate startDate;

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @return the startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param endDate
     * @param startDate
     */
    public DateRange(LocalDate startDate, LocalDate endDate) {
        super();
        this.endDate = endDate;
        this.startDate = startDate;
    }

    /**
     * @param endDate
     * @param startDate
     */
    public DateRange(Month month, int year) {
        super();
        this.startDate = LocalDate.of(year, month, 1);
        this.endDate = startDate.with(lastDayOfMonth());
    }

    /**
     * @param endDate
     * @param startDate
     */
    public DateRange(String start, String end) {
        super();
        this.endDate = endDate;
        this.startDate = startDate;
    }
    
    public boolean isInRange(LocalDate date) {
        return false;
    }
    
}
