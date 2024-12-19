package com.airbnb.dto.wrapper;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TotalAmountWrapper {
    private BigDecimal totalAmount;

    public TotalAmountWrapper() {
        this.totalAmount = BigDecimal.ZERO;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void add(BigDecimal value) {
        this.totalAmount = this.totalAmount.add(value);
    }
}