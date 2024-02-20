package com.example.demo.util;

public enum OperationType {
    EQUAL("="),
    LESS_THAN("<"),
    GREATER_THAN(">");

    private final String symbol;

    OperationType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
