package com.scg.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
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
	 * 
	 * @param consultant
	 * @param weekStartDay
	 */
	public TimeCard(Consultant consultant, LocalDate weekStartDay) {
		super();
		accountNames = new HashSet<>();
		consultantHours = new ArrayList<>();
		this.consultant = consultant;
		this.weekStartingDay = weekStartDay;
	}

	/**
	 * 
	 * @param consultantTime
	 */
	public void addConsultantTime(ConsultantTime consultantTime) {
		accountNames.add(consultantTime.getName());
		totalHours += consultantTime.getHours();
		if (consultantTime.isBillable()) {
			totalBillableHours += consultantTime.getHours();
		}
		consultantHours.add(consultantTime);
	}

	/**
	 * @return the consultantHours
	 */
	public List<ConsultantTime> getBillableHoursForClient(String clientName) {

		List<ConsultantTime> theReturn = (List<ConsultantTime>) consultantHours.stream().filter(p -> p.isBillable() && p.getName() == clientName)
				.collect(Collectors.toList());

		return theReturn;
	}
	
	/**
	 * @return the consultant
	 */
	public Consultant getConsultant() {
		return consultant;
	}

	/**
	 * @return the consultantHours
	 */
	public List<ConsultantTime> getConsultantHours() {
	    // TODO: make unchangeable
		return consultantHours;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalBillableHours() {
		return totalBillableHours;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTotalHours() {
		return totalHours;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTotalNonBillableHours() {
		return totalHours - totalBillableHours;
	}
	
	/**
	 * @return the weekStartingDay
	 */
	public LocalDate getWeekStartingDay() {
		return weekStartingDay;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toReportString() {
		final String LINE_DOUBLE = StringUtils.repeat("=", 68) + "\n";
		String finalString = null;
		try (Formatter fmtr = new Formatter();) {

			// Start the block
			fmtr.format(LINE_DOUBLE)
	
			// Consultant and Week Starting Date
			
			    .format("Consultant: %1$-28s Week Starting: %2$tb %2$td, %2$tY%n", getConsultant(), getWeekStartingDay())
	
			// Billable Headers
			    .format("%nBillable Time:%n")
			    .format("%-27s  %-10s  Hours  Skill%n", ACCOUNT, DATE)
			    .format(StringUtils.repeat("-", 27) + "  ")
			    .format(StringUtils.repeat("-", 10) + "  ")
			    .format(StringUtils.repeat("-", 5) + "  ")
			    .format(StringUtils.repeat("-", 20) + "\n");
	
			// Print only consultant hours that are billable
		    getConsultantHours().forEach(p -> {
		    	if (p.isBillable())
		    		fmtr.format(p.toString());
		    	});
	
			// Non-Billable Headers
			fmtr.format("%nNon-billable Time:%n")
			    .format("%-27s  %-10s  Hours  Skill%n", ACCOUNT, DATE)
			    .format(StringUtils.repeat("-", 27) + "  ")
			    .format(StringUtils.repeat("-", 10) + "  ")
			    .format(StringUtils.repeat("-", 5) + "  ")
			    .format(StringUtils.repeat("-", 20) + "\n");
	
			// Print only consultant hours that are NOT billable
		    getConsultantHours().forEach(p -> {
		    	if (!p.isBillable())
		    		fmtr.format(p.toString());
		    	});
			
			// Summary
			fmtr.format("%nSummary:%n")
			    .format("%-39s  %5d%n", TOTAL_BILLABLE, getTotalBillableHours())
			    .format("%-39s  %5d%n", TOTAL_NON_BILLABLE, getTotalNonBillableHours())
			    .format("%-39s  %5d%n", TOTAL_HOURS, getTotalHours())
			
			// End this block
			    .format(LINE_DOUBLE);
			finalString = fmtr.toString();
		}
		return finalString;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() { // TimeCar for <consultant> day of week
		return "TimeCard [getConsultantHours()=" + getConsultantHours() + ", getTotalBillableHours()="
				+ getTotalBillableHours() + ", getTotalHours()=" + getTotalHours() + ", getNonTotalBilableHours()="
				+ getTotalNonBillableHours() + ", getWeekStartingDay()=" + getWeekStartingDay() + "]";
	}
}
