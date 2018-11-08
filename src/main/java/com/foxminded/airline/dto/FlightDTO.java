package com.foxminded.airline.dto;

import java.util.Objects;

public class FlightDTO {
    private String number;
    private String dateString;
    private String timeString;
    private String planeName;
    private String departureAirport;
    private String arrivalAirport;
    private boolean enabled;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDTO flightDTO = (FlightDTO) o;
        return enabled == flightDTO.enabled &&
                Objects.equals(number, flightDTO.number) &&
                Objects.equals(dateString, flightDTO.dateString) &&
                Objects.equals(timeString, flightDTO.timeString) &&
                Objects.equals(planeName, flightDTO.planeName) &&
                Objects.equals(departureAirport, flightDTO.departureAirport) &&
                Objects.equals(arrivalAirport, flightDTO.arrivalAirport);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, dateString, timeString, planeName, departureAirport, arrivalAirport, enabled);
    }
}
