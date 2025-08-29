package com.allandc.reservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiningTable {

    @Id
    @GeneratedValue
    private int id;

    private int number;

    private int capacity;

    private String status;
}
