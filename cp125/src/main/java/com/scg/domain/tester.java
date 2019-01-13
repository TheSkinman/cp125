package com.scg.domain;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;

public class tester {

	private static final Logger log = LoggerFactory.getLogger(Invoice.class);

	
	public tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		String businessName = "The Small Consulting Group";
		Address businessAddress = new Address("1616 Index Ct.", "Renton", StateCode.WA, "98058");
		PersonalName contact = new PersonalName("Coyote", "Wiley", "NMN");
		Address clientAddress = new Address("2121 Xedni Tc.", "Austin", StateCode.TX, "12354");
		ClientAccount client = new ClientAccount("Acme Industries", contact, clientAddress );
		LocalDate invoiceDate = LocalDate.of(2018, 04, 01);
		LocalDate invoiceForMonth = LocalDate.of(2017, 03, 01);
		
		InvoiceHeader iheader = new InvoiceHeader(businessName, businessAddress, client, invoiceDate, invoiceForMonth);
		
		InvoiceFooter ifooter = new InvoiceFooter("The Small Consulting Group");
		
		Invoice testInvoice = new Invoice(client, Month.DECEMBER, 1978);
		
		
		System.out.println(iheader.toString());
		System.out.println("\n\n");
		System.out.println(ifooter.toString());
		ifooter.incrementPageNumber();
		System.out.println(iheader.toString());
		System.out.println("\n\n");
		System.out.println(ifooter.toString());
		ifooter.incrementPageNumber();
		System.out.println(iheader.toString());
		System.out.println("\n\n");
		System.out.println(ifooter.toString());
		ifooter.incrementPageNumber();
		System.out.println(iheader.toString());
		System.out.println("\n\n");
		System.out.println(ifooter.toString());
		
	}

}
