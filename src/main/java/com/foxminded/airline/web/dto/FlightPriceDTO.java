package com.foxminded.airline.web.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightPriceDTO that = (FlightPriceDTO) o;
        return Objects.equals(levelTicket, that.levelTicket) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(levelTicket, price);
    }
}
