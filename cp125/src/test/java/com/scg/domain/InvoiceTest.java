package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;

class InvoiceTest {
    private Invoice invoice;
    
    @Test
    void test_Invoice() {
        // ARRANGE
        String name = "Small Business";
        PersonalName contact = new PersonalName("last", "first", "middle");
        Address address = new Address("123 Street", "City", StateCode.AL, "12345");
        ClientAccount client = new ClientAccount(name, contact, address);
        Month invoiceMonth = Month.OCTOBER;
        int invoiceYear = 1968;

        // ACT
        invoice = new Invoice(client, invoiceMonth, invoiceYear);
        
        // ASSERT
        assertEquals("The Small Consulting Group", invoice.getBizName());
        assertEquals("Renton", invoice.getBizAddress().getCity());
        assertEquals("98058", invoice.getBizAddress().getPostalCode());
        assertEquals(StateCode.WA, invoice.getBizAddress().getState());
        assertEquals("1616 Index Ct.", invoice.getBizAddress().getStreetNumber());
    }

    @Test
    void test_AddLineItem() {
        // ARRANGE
        String name = "Small Business";
        PersonalName contact = new PersonalName("last", "first", "middle");
        Address address = new Address("123 Street", "City", StateCode.AL, "12345");
        ClientAccount client = new ClientAccount(name, contact, address);
        Month invoiceMonth = Month.OCTOBER;
        int invoiceYear = 1968;
        invoice = new Invoice(client, invoiceMonth, invoiceYear);
        Consultant consultant = new Consultant(contact);
        InvoiceLineItem lineItem = new InvoiceLineItem(LocalDate.of(1968, 10, 8), consultant, Skill.SOFTWARE_ENGINEER, 2);
        String expected = String.format("The Small Consulting Group1616 Index Ct.Renton, WA 98058 Invoice for:"
                + "Small Business123 StreetCity, AL 12345 last, first middleInvoice For Month of: October 1968"
                + "Invoice Date: %1$tB %1$td, %1$tYDate        Consultant                   Skill                Hours  Charge"
                + "----------  ---------------------------  -------------------  -----  ----------"
                + "10/08/1968  last, first middle           Software Engineer        2      300.00"
                + "Total:                                                            2      300.00"
                + "The Small Consulting Group                                            Page:   1"
                + "===============================================================================", LocalDate.now());

        // ACT
        invoice.addLineItem(lineItem);
        
        // ASSERT
        String result = invoice.toReportString().replaceAll("\r", "").replaceAll("\n", "");
        assertEquals(expected, result);
    }

    @Test
    void test_AddLineItem_SixItems() {
        // ARRANGE
        String name = "Small Business";
        PersonalName contact = new PersonalName("last", "first", "middle");
        Address address = new Address("123 Street", "City", StateCode.AL, "12345");
        ClientAccount client = new ClientAccount(name, contact, address);
        Month invoiceMonth = Month.OCTOBER;
        int invoiceYear = 1968;
        invoice = new Invoice(client, invoiceMonth, invoiceYear);
        Consultant consultant = new Consultant(contact);
        InvoiceLineItem lineItem = new InvoiceLineItem(LocalDate.of(1968, 10, 8), consultant, Skill.SOFTWARE_ENGINEER, 2);
        String expected = String.format("The Small Consulting Group1616 Index Ct.Renton, WA 98058 Invoice for:Small Business123 StreetCity, "
                + "AL 12345 last, first middleInvoice For Month of: October 1968Invoice Date: %1$tB %1$td, %1$tY"
                + "Date        Consultant                   Skill                Hours  Charge"
                + "----------  ---------------------------  -------------------  -----  ----------"
                + "10/08/1968  last, first middle           Software Engineer        2      300.00"
                + "10/08/1968  last, first middle           Software Engineer        2      300.00"
                + "10/08/1968  last, first middle           Software Engineer        2      300.00"
                + "10/08/1968  last, first middle           Software Engineer        2      300.00"
                + "10/08/1968  last, first middle           Software Engineer        2      300.00"
                + "The Small Consulting Group                                            Page:   1"
                + "==============================================================================="
                + "The Small Consulting Group1616 Index Ct.Renton, WA 98058 Invoice for:Small Business"
                + "123 StreetCity, AL 12345 last, first middleInvoice For Month of: October 1968"
                + "Invoice Date: %1$tB %1$td, %1$tYDate        Consultant                   Skill                Hours  Charge"
                + "----------  ---------------------------  -------------------  -----  ----------"
                + "10/08/1968  last, first middle           Software Engineer        2      300.00"
                + "Total:                                                           12    1,800.00"
                + "The Small Consulting Group                                            Page:   2"
                + "===============================================================================", LocalDate.now());

        // ACT
        invoice.addLineItem(lineItem);
        invoice.addLineItem(lineItem);
        invoice.addLineItem(lineItem);
        invoice.addLineItem(lineItem);
        invoice.addLineItem(lineItem);
        invoice.addLineItem(lineItem);
        
        // ASSERT
        String result = invoice.toReportString().replaceAll("\r", "").replaceAll("\n", "");
        assertEquals(expected, result);
    }

    @Test
    void test_GetInvoiceMonth() {
        // ARRANGE
        String name = "Small Business";
        PersonalName contact = new PersonalName("last", "first", "middle");
        Address address = new Address("123 Street", "City", StateCode.AL, "12345");
        ClientAccount client = new ClientAccount(name, contact, address);
        Month invoiceMonth = Month.OCTOBER;
        int invoiceYear = 1968;
        invoice = new Invoice(client, invoiceMonth, invoiceYear);
        
        // ACT
        Month result = invoice.getInvoiceMonth();
        
        // ASSERT
        assertEquals(Month.OCTOBER, result);
    }

    @Test
    void test_ExtractLineItems() {
        // ARRANGE
        String name = "Small Business";
        PersonalName contact = new PersonalName("last", "first", "middle");
        Address address = new Address("123 Street", "City", StateCode.AL, "12345");
        ClientAccount client = new ClientAccount(name, contact, address);
        Month invoiceMonth = Month.OCTOBER;
        int invoiceYear = 1968;
        invoice = new Invoice(client, invoiceMonth, invoiceYear);
        Consultant consultant = new Consultant(contact);
        LocalDate weekStartDay = LocalDate.of(1968, 10, 8);
        TimeCard timeCard = new TimeCard(consultant, weekStartDay);
        LocalDate date = LocalDate.of(1968, 10, 8);
        Account account = new Account() {
            private static final long serialVersionUID = -483015861398344904L;
            @Override public boolean isBillable() { return true; }
            @Override public String getName() { return name; }
        };
        ConsultantTime consultantTime = new ConsultantTime(date, account, Skill.SOFTWARE_ENGINEER, 2);
        timeCard.addConsultantTime(consultantTime );
        String expected = "Date        Consultant                   Skill                Hours      Charge" + 
                "----------  ---------------------------  ------------------   -----  ----------" +
                "10/08/1968  last, first middle           Software Engineer        2      300.00";

        // ACT
        invoice.extractLineItems(timeCard);
        
        // ASSERT
        String result = invoice.toString().replaceAll("\r", "").replaceAll("\n", "");
        assertEquals(expected, result);
    }

    @Test
    void test_ExtractLineItems_MonthNotEqual() {
        // ARRANGE
        String name = "Small Business";
        PersonalName contact = new PersonalName("last", "first", "middle");
        Address address = new Address("123 Street", "City", StateCode.AL, "12345");
        ClientAccount client = new ClientAccount(name, contact, address);
        Month invoiceMonth = Month.OCTOBER;
        int invoiceYear = 1968;
        invoice = new Invoice(client, invoiceMonth, invoiceYear);
        Consultant consultant = new Consultant(contact);
        LocalDate weekStartDay = LocalDate.of(1968, 10, 8);
        TimeCard timeCard = new TimeCard(consultant, weekStartDay);
        LocalDate date = LocalDate.of(1968, 8, 8);
        Account account = new Account() {
            private static final long serialVersionUID = 1318290521636344179L;
            @Override public boolean isBillable() { return true; }
            @Override public String getName() { return name; }
        };
        ConsultantTime consultantTime = new ConsultantTime(date, account, Skill.SOFTWARE_ENGINEER, 2);
        timeCard.addConsultantTime(consultantTime );
        String expected = "Date        Consultant                   Skill                Hours      Charge" + 
                "----------  ---------------------------  ------------------   -----  ----------";

        // ACT
        invoice.extractLineItems(timeCard);
        
        // ASSERT
        String result = invoice.toString().replaceAll("\r", "").replaceAll("\n", "");
        assertEquals(expected, result);

    }

    @Test
    void test_ExtractLineItems_YearNotEqual() {
        // ARRANGE
        String name = "Small Business";
        PersonalName contact = new PersonalName("last", "first", "middle");
        Address address = new Address("123 Street", "City", StateCode.AL, "12345");
        ClientAccount client = new ClientAccount(name, contact, address);
        Month invoiceMonth = Month.OCTOBER;
        int invoiceYear = 1968;
        invoice = new Invoice(client, invoiceMonth, invoiceYear);
        Consultant consultant = new Consultant(contact);
        LocalDate weekStartDay = LocalDate.of(1968, 10, 8);
        TimeCard timeCard = new TimeCard(consultant, weekStartDay);
        LocalDate date = LocalDate.of(1969, 10, 8);
        Account account = new Account() {
            private static final long serialVersionUID = -6221789367148721152L;
            @Override public boolean isBillable() { return true; }
            @Override public String getName() { return name; }
        };
        ConsultantTime consultantTime = new ConsultantTime(date, account, Skill.SOFTWARE_ENGINEER, 2);
        timeCard.addConsultantTime(consultantTime );
        String expected = "Date        Consultant                   Skill                Hours      Charge" + 
                "----------  ---------------------------  ------------------   -----  ----------";

        // ACT
        invoice.extractLineItems(timeCard);
        
        // ASSERT
        String result = invoice.toString().replaceAll("\r", "").replaceAll("\n", "");
        assertEquals(expected, result);

    }

    @Test
    void test_ToReportString() {
        // ARRANGE
        String name = "Small Business";
        PersonalName contact = new PersonalName("last", "first", "middle");
        Address address = new Address("123 Street", "City", StateCode.AL, "12345");
        ClientAccount client = new ClientAccount(name, contact, address);
        Month invoiceMonth = Month.OCTOBER;
        int invoiceYear = 1968;
        invoice = new Invoice(client, invoiceMonth, invoiceYear);
        String expected = "Total:                                                            0        0.00"
                + "The Small Consulting Group                                            Page:   1"
                + "===============================================================================";
        
        // ACT
        String result = invoice.toReportString().replaceAll("\r", "").replaceAll("\n", "");
        
        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void test_ToString() {
        // ARRANGE
        String name = "Small Business";
        PersonalName contact = new PersonalName("last", "first", "middle");
        Address address = new Address("123 Street", "City", StateCode.AL, "12345");
        ClientAccount client = new ClientAccount(name, contact, address);
        Month invoiceMonth = Month.OCTOBER;
        int invoiceYear = 1968;
        invoice = new Invoice(client, invoiceMonth, invoiceYear);
        Consultant consultant = new Consultant(contact);
        InvoiceLineItem lineItem = new InvoiceLineItem(LocalDate.of(1968, 10, 8), consultant, Skill.SOFTWARE_ENGINEER, 2);
        invoice.addLineItem(lineItem);
        String expected = "Date        Consultant                   Skill                Hours      Charge" + 
                "----------  ---------------------------  ------------------   -----  ----------" +
                "10/08/1968  last, first middle           Software Engineer        2      300.00";
        
        // ACT
        String result = invoice.toString().replaceAll("\r", "").replaceAll("\n", "");
        
        // ASSERT
        assertEquals(expected, result);
    }
}


