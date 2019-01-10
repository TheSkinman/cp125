package com.scg.domain;

public class tester {

	public tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		InvoiceFooter ifooter = new InvoiceFooter("The Small Consulting Group");
		
		System.out.println(ifooter.toString());
		ifooter.incrementPageNumber();
		System.out.println(ifooter.toString());
		ifooter.incrementPageNumber();
		System.out.println(ifooter.toString());
		ifooter.incrementPageNumber();
		System.out.println(ifooter.toString());
		
	}

}
