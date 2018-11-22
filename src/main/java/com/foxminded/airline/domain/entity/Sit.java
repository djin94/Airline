package com.foxminded.airline.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sit")
public class Sit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sitId", unique = true)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "plane_id")
    private Plane plane;

    @Column(name = "levelticket")
    private String levelTicket;

    private String place;

    @OneToMany(mappedBy = "sit", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonIgnore
    private List<Ticket> tickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public String getLevelTicket() {
        return levelTicket;
    }

    public void setLevelTicket(String levelTicket) {
        this.levelTicket = levelTicket;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sit sit = (Sit) o;

        return id.equals(sit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sit{" +
                "id=" + id +
                ", plane=" + plane +
                '}';
    }
}
