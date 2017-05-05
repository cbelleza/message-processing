package com.jpmorgan.support.vo;

public class MessageType3 extends MessageType2 {

    private AdjustmentOperationValue adjustmentOperationValue;

    public AdjustmentOperationValue getAdjustmentOperationValue() {
        return adjustmentOperationValue;
    }

    public void setAdjustmentOperationValue(final AdjustmentOperationValue adjustmentOperationValue) {
        this.adjustmentOperationValue = adjustmentOperationValue;
    }
}