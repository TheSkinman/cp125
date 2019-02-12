package com.scg.domain;

import java.time.LocalDate;
import java.util.Formatter;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.scg.util.Address;

/**
 * Header for Small Consulting Group Invoices. The printed header has the
 * following:
 * <ul>
 * <li>billing business' name</li>
 * <li>billing business' address</li>
 * <li>name of the client being billed</li>
 * <li>address of the client being billed</li>
 * <li>month being billed for</li>
 * <li>date the invoice was generated</li>
 * </ul>
 * All of this formatted in a readable fashion.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class InvoiceHeader {
    private static final String INVOICE_HEADER = 
            "%s%n%s%nInvoice for:%n%s%n%nInvoice For Month of: %4$tB %4$tY%nInvoice Date: %5$tB %5$td, %5$tY%n%n";
    private static final String LINE_HEADER =
            "Date        Consultant                   Skill                Hours  Charge%n" + 
            "----------  ---------------------------  -------------------  -----  ----------%n";

	private String businessName;
	private Address businessAddress;
	private ClientAccount client;
	private LocalDate invoiceDate;
	private LocalDate invoiceForMonth;

	/**
	 * Construct an InvoiceHeader.
	 * 
	 * @param businessName
	 *            name of business issuing invoice
	 * @param businessAddress
	 *            address of business issuing invoice
	 * @param client
	 *            client for the invoice with this header.
	 * @param invoiceDate
	 *            date of the invoice with this header.
	 * @param invoiceForMonth
	 *            month of billable charges for invoice with this header.
	 */
	public InvoiceHeader(String businessName, Address businessAddress, ClientAccount client, LocalDate invoiceDate,
			LocalDate invoiceForMonth) {
		super();
		this.businessName = businessName;
		this.businessAddress = businessAddress;
		this.client = client;
		this.invoiceDate = invoiceDate;
		this.invoiceForMonth = invoiceForMonth;
	}

	/**
	 * Print this InvoiceHeader.
	 * 
	 * @return Formatted string of the information in this header.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		try (Formatter fmrt = new Formatter(sb, Locale.US);) {
    		// Company Address
    		fmrt.format(INVOICE_HEADER,
    		        businessName,
    		        businessAddress.toString(),
    		        client.toString(), 
    		        invoiceForMonth, 
    		        invoiceDate)
            .format(LINE_HEADER);
		}
		return sb.toString();
	}
	
}
