package com.example.management.model.audit;

public enum Action {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String name;

    private Action(String value) {
        this.name = value;
    }

    public String value() {
        return this.name;
    }
    @Override
    public String toString() {
        return name;
    }
}
