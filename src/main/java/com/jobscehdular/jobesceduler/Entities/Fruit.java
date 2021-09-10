package com.jobscehdular.jobesceduler.Entities;

import javax.persistence.*;

@Entity
@Table(name = "fruits",schema = "public")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  int id;
    private String name;

    public long getId() {
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
}
