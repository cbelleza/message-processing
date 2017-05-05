package com.jpmorgan.support.vo;

import java.math.BigDecimal;

import com.jpmorgan.support.AdjustmentOperation;

public class AdjustmentOperationValue {

    private AdjustmentOperation adjustmentOperation;

    private BigDecimal value;

    public AdjustmentOperation getAdjustmentOperation() {
        return adjustmentOperation;
    }

    public void setAdjustmentOperation(final AdjustmentOperation adjustmentOperation) {
        this.adjustmentOperation = adjustmentOperation;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(final BigDecimal value) {
        this.value = value;
    }
}
