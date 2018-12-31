package com.scg.domain;

import java.time.LocalDate;
import java.util.ArrayList;
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

		List<ConsultantTime> theReturn = (List<ConsultantTime>) consultantHours.stream().filter(p -> p.isBillable())
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
		StringBuilder sb = new StringBuilder();

		// Start the block
		sb.append(LINE_DOUBLE);

		// Consultant and Week Starting Date
		sb.append(String.format("%1$-40s Week Starting: %2$tb %2$td, %2$tY%n", getConsultant(), getWeekStartingDay()));

		// Billable Headers
		sb.append(String.format("%nBillable Time:%n"));
		sb.append(String.format("%-27s  %-10s  Hours  Skill%n", "Account", "Date"));
		sb.append(StringUtils.repeat("-", 27) + "  ");
		sb.append(StringUtils.repeat("-", 10) + "  ");
		sb.append(StringUtils.repeat("-", 5) + "  ");
		sb.append(StringUtils.repeat("-", 20) + "\n");

		// Print only consultant hours that are billable
	    getConsultantHours().forEach(p -> {
	    	if (p.isBillable())
	    		sb.append(p.toString());
	    	});

		// Non-Billable Headers
		sb.append(String.format("%nNon-billable Time:%n"));
		sb.append(String.format("%-27s  %-10s  Hours  Skill%n", "Account", "Date"));
		sb.append(StringUtils.repeat("-", 27) + "  ");
		sb.append(StringUtils.repeat("-", 10) + "  ");
		sb.append(StringUtils.repeat("-", 5) + "  ");
		sb.append(StringUtils.repeat("-", 20) + "\n");

		// Print only consultant hours that are NOT billable
	    getConsultantHours().forEach(p -> {
	    	if (!p.isBillable())
	    		sb.append(p.toString());
	    	});
		
		// Summary
		sb.append(String.format("%nSummary:%n"));
		sb.append(String.format("%-39s  %5d%n", "Total Billable:", getTotalBillableHours()));
		sb.append(String.format("%-39s  %5d%n", "Total Non-Billable:", getTotalNonBillableHours()));
		sb.append(String.format("%-39s  %5d%n", "Total Hours:", getTotalHours()));
		
		// End this block
		sb.append(LINE_DOUBLE);
		String finalString = sb.toString(); 
		return finalString;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TimeCard [getConsultantHours()=" + getConsultantHours() + ", getTotalBillableHours()="
				+ getTotalBillableHours() + ", getTotalHours()=" + getTotalHours() + ", getNonTotalBilableHours()="
				+ getTotalNonBillableHours() + ", getWeekStartingDay()=" + getWeekStartingDay() + ", toString()="
				+ super.toString() + "]";
	}
}
