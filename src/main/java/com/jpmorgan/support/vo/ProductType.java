package com.jpmorgan.support.vo;

import java.math.BigDecimal;

public class ProductType {

    private String name;
    private BigDecimal value;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(final BigDecimal value) {
        this.value = value;
    }
}