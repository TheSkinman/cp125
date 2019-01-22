package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;

class InvoiceHeaderTest {
    private InvoiceHeader invoiceHeader;

    @Test
    void testInvoiceHeader() {
        // ARRANGE
        String businessName = "Small Business";
        Address businessAddress = new Address("123 Street", "City", StateCode.AK, "11111");
        PersonalName contact = new PersonalName("last", "first", "middle");
        ClientAccount client = new ClientAccount("Big Business", contact, businessAddress);
        LocalDate invoiceDate = LocalDate.of(1968, 10, 8);
        LocalDate invoiceForMonth = LocalDate.of(2008, 3, 18);
        
        // ACT
        invoiceHeader = new InvoiceHeader(businessName, businessAddress, client, invoiceDate, invoiceForMonth);
        
        // ASSERT
        assertNotNull(invoiceHeader);
    }

    @Test
    void testToString() {
        // ARRANGE
        String businessName = "Small Business";
        Address businessAddress = new Address("123 Street", "City", StateCode.AK, "11111");
        PersonalName contact = new PersonalName("last", "first", "middle");
        ClientAccount client = new ClientAccount("Big Business", contact, businessAddress);
        LocalDate invoiceDate = LocalDate.of(1968, 10, 8);
        LocalDate invoiceForMonth = LocalDate.of(2008, 3, 18);
        invoiceHeader = new InvoiceHeader(businessName, businessAddress, client, invoiceDate, invoiceForMonth);
        String expected = "Small Business123 StreetCity, AK 11111 Invoice for:Big Business123 Street" + 
                "City, AK 11111 last, first middleInvoice For Month of: March 2008" + 
                "Invoice Date: October 08, 1968" + 
                "Date        Consultant                   Skill                Hours  Charge" + 
                "----------  ---------------------------  ------------------   -----  ----------";
        
        // ACT
        String result = invoiceHeader.toString().replaceAll("\r", "").replaceAll("\n", "");
        
        // ASSERT
        assertEquals(expected, result);
    }

}
