package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvoiceFooterTest {
    private InvoiceFooter invoiceFooter;

    @Test
    void testInvoiceFooter() {
        // ARRANGE
        String businessName = "Big Business";

        // ACT
        invoiceFooter = new InvoiceFooter(businessName);

        // ASSERT
        assertNotNull(invoiceFooter);
    }

    @Test
    void testIncrementPageNumber() {
        // ARRANGE
        String businessName = "Big Business";
        invoiceFooter = new InvoiceFooter(businessName);
        String expected = "Big Business                                                          Page:   4"
                + "===============================================================================";

        // ACT
        invoiceFooter.incrementPageNumber();
        invoiceFooter.incrementPageNumber();
        invoiceFooter.incrementPageNumber();
        String result = invoiceFooter.toString().replaceAll("\r", "").replaceAll("\n", "");

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void testToString() {
        // ARRANGE
        String businessName = "Big Business";
        invoiceFooter = new InvoiceFooter(businessName);
        String expected = "Big Business                                                          Page:   1"
                + "===============================================================================";

        // ACT
        String result = invoiceFooter.toString().replaceAll("\r", "").replaceAll("\n", "");

        // ASSERT
        assertEquals(expected, result);
    }

}
