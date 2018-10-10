package com.foxminded.airline.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="role")
public class Role {

    @Id
    @SequenceGenerator(name = "role_id_seq", sequenceName = "role_role_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "role_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "roleId", unique = true, nullable = true)
    private int id;

    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonIgnore
    private List<User> user = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return id == role.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
