package com.scg.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * Footer for Small Consulting Group invoices. The printed footer has the
 * billing business' name and page number, formatted in a readable fashion.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class InvoiceFooter {
	private String businessName;
	private int pageNumber;

	/**
	 * Construct an InvoiceFooter.
	 * 
	 * @param businessName
	 *            name of business to include in footer
	 */
	public InvoiceFooter(String businessName) {
		super();
		this.businessName = businessName;
		pageNumber = 1;
	}

	/**
	 * Increment the current page number by one.
	 */
	public void incrementPageNumber() {
		pageNumber++;
	}

	/**
	 * Print the formatted footer.
	 */
	@Override
	public String toString() {
		final String LINE_DOUBLE = StringUtils.repeat("=", 79) + "\n";
		return String.format("%n%n%-70sPage:%4s%n%s", businessName, pageNumber, LINE_DOUBLE);
	}
}
