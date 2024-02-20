package com.example.demo.dto;

import com.example.demo.util.OperationType;

public class CompletedSearchCriteria {
    private  OperationType operationType;
    private String filedName;

    public CompletedSearchCriteria() {
    }

    public CompletedSearchCriteria(OperationType operationType, String filedName) {
        this.operationType = operationType;
        this.filedName = filedName;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }
}
