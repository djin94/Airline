package com.foxminded.airline.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sit")
public class Sit {

    @Id
    @SequenceGenerator(name = "sit_id_seq", sequenceName = "sit_sit_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "sit_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "sitId", unique = true)
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "plane_id")
    private Plane plane;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "levelticket_id")
    private LevelTicket levelTicket;

    private String place;

    @OneToMany(mappedBy = "sit", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonIgnore
    private List<Ticket> tickets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public LevelTicket getLevelTicket() {
        return levelTicket;
    }

    public void setLevelTicket(LevelTicket levelTicket) {
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

        return id == sit.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Sit{" +
                "place='" + place + '\'' +
                '}';
    }
}
