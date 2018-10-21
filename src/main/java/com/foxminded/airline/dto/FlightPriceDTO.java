package com.foxminded.airline.dto;

/**
 * Created by Кабатов on 04.10.2018.
 */
public class FlightPriceDTO {
    private String levelTicket;
    private String price;

    public String getLevelTicket() {
        return levelTicket;
    }

    public void setLevelTicket(String levelTicket) {
        this.levelTicket = levelTicket;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
