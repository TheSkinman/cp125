package com.scg.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * JUnit test for TimeCardListUtil class.
 */
public final class TimeCardListUtil_ExtraTest {
    /** The test year. */
    private static final int TEST_YEAR = 2007;
    
    /** Constant for the 4th. */
    private static final int DAY_4 = 4;

    /** Constant for the 11th. */
    private static final int DAY_11 = 11;

    /** Constant for the 18th. */
    private static final int DAY_18 = 18;

    /** Test programmer. */
    private Consultant programmer;
    /** Test time card 1. */

    private TimeCard timeCard1;

    /** Test time card 2. */
    private TimeCard timeCard2;

    /** Test time card 3. */
    private TimeCard timeCard3;

    /** Test time cards. */
    private ArrayList<TimeCard> timeCards;
    private ArrayList<TimeCard> timeCardsSame;

    /** Set up the test fixture. */
    @BeforeEach
    public void setUp() {
        LocalDate startDate = LocalDate.of(TEST_YEAR, Month.FEBRUARY, DAY_4);

        programmer = new Consultant(new PersonalName("Coder", "Carl"));

        timeCard1 = new TimeCard(programmer, startDate);
        programmer = new Consultant(new PersonalName("Coder", "Carly"));
        timeCard2 = new TimeCard(programmer, startDate);

        timeCards = new ArrayList<TimeCard>();
        timeCards.add(timeCard1);
        timeCards.add(timeCard2);
        
        timeCardsSame = new ArrayList<TimeCard>();
        timeCardsSame.add(timeCard1);
        timeCardsSame.add(timeCard1);
    }

    /** Test for the sortByStartDate method. */
    @Test
    public void test_SortByStartDate() {
        // ACT
        TimeCardListUtil.sortByStartDate(timeCardsSame);
        
        // ASSERT
        assertEquals(timeCard1, timeCardsSame.get(0));
        assertEquals(timeCard1, timeCardsSame.get(1));
    }

    /** Test for the sortByConsultantName method. */
    @Test
    public void testSortByConsultantName() {
        // ACT
        TimeCardListUtil.sortByConsultantName(timeCards);

        // ASSERT
        assertEquals(timeCard1, timeCards.get(0));
        assertEquals(timeCard2, timeCards.get(1));
    }

    /** Test for the sortByConsultantName method with same time cards. */
    @Test
    public void testSortByConsultantName_sameTimeCards() {
        // ACT
        TimeCardListUtil.sortByConsultantName(timeCardsSame);

        // ASSERT
        assertEquals(timeCard1, timeCardsSame.get(0));
        assertEquals(timeCard1, timeCardsSame.get(1));
    }

//    /** Test for the getTimeCardsForDateRange method. */
//    @Test
//    public void testGetTimeCardsForDateRange() {
//        final DateRange dateRange = new DateRange("2007-02-11", "2007-02-17");
//
//        final List<TimeCard> selected = TimeCardListUtil.getTimeCardsForDateRange(timeCards, dateRange);
//
//        assertEquals(1, selected.size());
//    }

//    /** Test for the getTimeCardsForConsultant method. */
//    @Test
//    public void testGetTimeCardsForConsultant() {
//        final List<TimeCard> selected = TimeCardListUtil.getTimeCardsForConsultant(timeCards, programmer);
//        assertEquals(2, selected.size());
//        assertEquals(timeCard1, selected.get(0));
//        assertEquals(timeCard3, selected.get(1));
//    }
}
