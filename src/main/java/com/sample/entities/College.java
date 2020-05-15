package com.sample.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "college")
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private int id;

    @Column(name = "college_name")
    private String name;

    @Column(name = "college_address")
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
