package com.foxminded.airline.dto;

import java.util.Objects;

public class TicketDTO {
    private String sit;

    private UserDTO userDTO;

    private FlightDTO flightDTO;

    public String getSit() {
        return sit;
    }

    public void setSit(String sit) {
        this.sit = sit;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public FlightDTO getFlightDTO() {
        return flightDTO;
    }

    public void setFlightDTO(FlightDTO flightDTO) {
        this.flightDTO = flightDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDTO ticketDTO = (TicketDTO) o;
        return Objects.equals(sit, ticketDTO.sit) &&
                Objects.equals(userDTO, ticketDTO.userDTO) &&
                Objects.equals(flightDTO, ticketDTO.flightDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sit, userDTO, flightDTO);
    }
}
