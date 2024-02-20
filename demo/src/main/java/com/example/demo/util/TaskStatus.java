package com.example.demo.util;

public enum TaskStatus {
    COMPLETED(1),
    NOT_COMPLETED(0);

    private final Integer symbol;

    TaskStatus(Integer symbol) {
        this.symbol = symbol;
    }

    public Integer getSymbol() {
        return symbol;
    }
}
