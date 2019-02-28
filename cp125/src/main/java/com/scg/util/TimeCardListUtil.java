package com.scg.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * Utility class for processing TimeCard lists.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class TimeCardListUtil {
    private static final int DAYS_PER_WEEK = 7;
    
    /**
     * Get a list of TimeCards for the specified consultant.
     * 
     * @param timeCards
     *            The list of time cards to extract the sub set from.
     * @param consultant
     *            The consultant whose TimeCards will be obtained.
     * @return A list of TimeCards for the specified consultant.
     */
    public static List<TimeCard> getTimeCardsForConsultant(List<TimeCard> timeCards, Consultant consultant) {
        List<TimeCard> returnList = timeCards.stream().filter(i -> i.getConsultant().equals(consultant))
                .collect(Collectors.toList());
        return returnList;
    }

    /**
     * Get a list of TimeCards that cover dates that fall within a date range. Each
     * time card *may* have time entries through out one week beginning with the
     * time card start date.
     * 
     * @param timeCards
     *            The list of time cards to extract the sub set from
     * @param dateRange
     *            The DateRange within which the dates of the returned TimeCards
     *            must fall.
     * @return A list of TimeCards having dates fall within the date range.
     */
    public static List<TimeCard> getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange) {
        // Stream<TimeCard> strm = Stream.of(timeCards);
        // List<TimeCard> returnList;
        // returnList = strm.filter(i ->
        // dateRange.isInRange(i.getWeekStartingDay())).collect(Collectors.toList());

        List<TimeCard> returnList = timeCards.stream().filter(i -> dateRange.isInRange(i.getWeekStartingDay()))
                .collect(Collectors.toList());
        return returnList;
    }

    /**
     * Sorts this list into ascending order by consultant name.
     * 
     * @param timeCards
     *            the list of time cards to sort
     */
    public static void sortByConsultantName(List<TimeCard> timeCards) {
        Collections.sort(timeCards, new Comparator<TimeCard>() {
            public int compare(TimeCard t1, TimeCard t2) {
                int diff = 0;
                if (t1 != t2) {
                    if ((diff = t1.getConsultant().getName().getLastName()
                            .compareTo(t2.getConsultant().getName().getLastName())) == 0)
                        if ((diff = t1.getConsultant().getName().getFirstName()
                                .compareTo(t2.getConsultant().getName().getFirstName())) == 0)
                            diff = t1.getConsultant().getName().getMiddleName()
                                    .compareTo(t2.getConsultant().getName().getMiddleName());
                }
                return diff;
            }
        });
    }

    /**
     * Sorts this list into ascending order, by the start date.
     * 
     * @param timeCards
     *            the list of time cards to sort
     */
    public static void sortByStartDate(List<TimeCard> timeCards) {
        Collections.sort(timeCards, new Comparator<TimeCard>() {
            public int compare(TimeCard t1, TimeCard t2) {
                int diff = 0;
                if (t1 != t2) {
                    diff = t1.getWeekStartingDay().compareTo(t2.getWeekStartingDay());
                }
                return diff;
            }
        });
    }
}
