package com.allandc.reservas.domain.reservation;

import com.allandc.reservas.domain.diningtable.DiningTable;
import com.allandc.reservas.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "table_id")
    private DiningTable diningTable;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
