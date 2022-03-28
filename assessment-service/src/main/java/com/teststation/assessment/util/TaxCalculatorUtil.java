package com.teststation.assessment.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TaxCalculatorUtil {

    private double configuredTax;

    public TaxCalculatorUtil(@Value(value = "${client.tax.percent}") double configuredTax) {
        this.configuredTax = configuredTax;
    }

    /**
     * Allows to calculate a total wage with tax amount.
     * @param baseWage base amount to be calculated the tax against
     * @return total amount of wage
     */
    public double addTax(double baseWage) {
        double adjustedTax = configuredTax / 100;
        return baseWage + (baseWage * adjustedTax);
    }
}
