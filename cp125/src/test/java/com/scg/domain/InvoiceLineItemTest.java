package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scg.util.PersonalName;

class InvoiceLineItemTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testInvoiceLineItem() {
        // ARRANGE
        LocalDate date = LocalDate.of(1968, 10, 8);
        PersonalName name = new PersonalName("last", "first", "middle");
        Consultant consultant = new Consultant(name );
        Skill skill = Skill.SOFTWARE_ENGINEER;
        int hours = 8;
        
        // ACT
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem(date, consultant, skill, hours);
        
        // ASSERT
        assertNotNull(invoiceLineItem);
    }

    @Test
    void test_GetCharge_CalculatesCorrect() {
        // ARRANGE
        LocalDate date = LocalDate.of(1968, 10, 8);
        PersonalName name = new PersonalName("last", "first", "middle");
        Consultant consultant = new Consultant(name );
        Skill skill = Skill.SOFTWARE_ENGINEER;
        int hours = 8;
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem(date, consultant, skill, hours);
        
        // ACT
        int result = invoiceLineItem.getCharge();
        
        // ASSERT
        assertEquals(1200, result);
    }

    @Test
    void testToString() {
        // ARRANGE
        LocalDate date = LocalDate.of(1968, 10, 8);
        PersonalName name = new PersonalName("last", "first", "middle");
        Consultant consultant = new Consultant(name );
        Skill skill = Skill.SOFTWARE_ENGINEER;
        int hours = 8;
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem(date, consultant, skill, hours);
        String expected = "10/08/1968  last, first middle           Software Engineer        8    1,200.00\r\n";
        
        // ACT
        String result = invoiceLineItem.toString();
        
        // ASSERT
        assertEquals(expected, result);
    }

}
