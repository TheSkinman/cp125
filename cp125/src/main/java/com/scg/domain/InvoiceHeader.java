package com.scg.domain;

import java.time.LocalDate;

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
		return "InvoiceHeader [businessName=" + businessName + ", businessAddress=" + businessAddress + ", client="
				+ client + ", invoiceDate=" + invoiceDate + ", invoiceForMonth=" + invoiceForMonth + "]";
	}
}
