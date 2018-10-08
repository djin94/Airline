package com.foxminded.airline.dto;

public class TicketDTO {
    private String sit;
    private PassengerDTO passengerDTO;

    public String getSit() {
        return sit;
    }

    public void setSit(String sit) {
        this.sit = sit;
    }

    public PassengerDTO getPassengerDTO() {
        return passengerDTO;
    }

    public void setPassengerDTO(PassengerDTO passengerDTO) {
        this.passengerDTO = passengerDTO;
    }
}
