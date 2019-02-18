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
    public int compare(TimeCard firstTimeCard, TimeCard secondTimeCard) {
        int diff = 0;
        if (firstTimeCard != secondTimeCard) {
            if ((diff = firstTimeCard.getConsultant().getName().getLastName()
                    .compareTo(secondTimeCard.getConsultant().getName().getLastName())) == 0)
                if ((diff = firstTimeCard.getConsultant().getName().getFirstName()
                        .compareTo(secondTimeCard.getConsultant().getName().getFirstName())) == 0)
                    if ((diff = firstTimeCard.getConsultant().getName().getMiddleName()
                            .compareTo(secondTimeCard.getConsultant().getName().getMiddleName())) == 0)
                        if ((diff = firstTimeCard.getWeekStartingDay().compareTo(secondTimeCard.getWeekStartingDay())) == 0)
                            if ((diff = Integer.compare(firstTimeCard.getTotalBillableHours(), secondTimeCard.getTotalBillableHours())) == 0)
                                diff = Integer.compare(firstTimeCard.getTotalNonBillableHours(), secondTimeCard.getTotalNonBillableHours());
        }
        return diff;
    }
}
