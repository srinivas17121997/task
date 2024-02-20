package com.example.demo.dto;

import com.example.demo.util.OperationType;

import java.time.LocalDate;

public class DateSearchCriteria {
    private  OperationType operationType;
    private LocalDate value;

    public DateSearchCriteria() {
    }

    public DateSearchCriteria(OperationType operationType, LocalDate value) {
        this.operationType = operationType;
        this.value = value;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public LocalDate getValue() {
        return value;
    }

    public void setValue(LocalDate value) {
        this.value = value;
    }
}
