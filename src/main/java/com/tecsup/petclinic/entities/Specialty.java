package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String office;
    private Integer h_open;
    private Integer h_close;
}
