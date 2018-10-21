package com.foxminded.airline.domain.entity;

public enum LevelTicket {
    ECONOM("Econom"),
    BUSINESS("Business");

    private String levelTicket;

    LevelTicket(String levelTicket) {
        this.levelTicket = levelTicket;
    }

    public String getLevelTicket() {
        return levelTicket;
    }
}
