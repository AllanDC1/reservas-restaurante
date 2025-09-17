package com.allandc.reservas.domain.diningtable;

import com.allandc.reservas.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    private int capacity;

    @Enumerated(EnumType.STRING)
    private DiningTableStatus status;

    @OneToMany(mappedBy = "diningTable", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;
}
