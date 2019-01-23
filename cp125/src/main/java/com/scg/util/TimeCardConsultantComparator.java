package com.scg.util;

import java.util.Comparator;

import com.scg.domain.TimeCard;

/**
 * Compares two TimeCard objects ascending order by (in precedence order)
 * consultant, time card starting date, total billable hours and non-billable
 * hours.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class TimeCardConsultantComparator implements Comparator<TimeCard> {

    /**
     * Compares its two arguments, in ascending order by (in precedence order)
     * consultant, the starting date, total billable hours and lastly total
     * non-billable hours
     * 
     * @param firstTimeCard
     *            the first object to be compared.
     * @param secondTimeCard
     *            the second object to be compared.
     * @return A negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(TimeCard o1, TimeCard o2) {
        int diff = 0;
        if (o1 != o2) {
            if ((diff = o1.getConsultant().getName().getLastName().compareTo(o2.getConsultant().getName().getLastName())) == 0)
            if ((diff = o1.getConsultant().getName().getFirstName().compareTo(o2.getConsultant().getName().getFirstName())) == 0)
            if ((diff = o1.getConsultant().getName().getMiddleName().compareTo(o2.getConsultant().getName().getMiddleName())) == 0)
            if ((diff = o1.getWeekStartingDay().compareTo(o2.getWeekStartingDay())) == 0)
            if ((diff = Integer.compare(o1.getTotalBillableHours(), o2.getTotalBillableHours())) == 0)
                diff = Integer.compare(o1.getTotalNonBillableHours(), o2.getTotalNonBillableHours());
        }
        return diff;
    }
}
