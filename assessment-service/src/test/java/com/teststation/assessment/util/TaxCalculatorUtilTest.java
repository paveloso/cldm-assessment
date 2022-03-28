package com.teststation.assessment.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaxCalculatorUtilTest {

    private TaxCalculatorUtil taxCalculatorUtil;

    @BeforeEach
    void init() {
        taxCalculatorUtil = new TaxCalculatorUtil(50.0);
    }

    @Test
    void addTax() {
        double result = taxCalculatorUtil.addTax(100.0);
        assertEquals(150.0, result);
    }
}
