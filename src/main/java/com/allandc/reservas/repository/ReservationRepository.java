package com.allandc.reservas.repository;

import com.allandc.reservas.entity.Reservation;
import com.allandc.reservas.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByUserId(UUID userId);
    List<Reservation> findByDiningTableNumberAndStatusAndDateBetween(
            int tableNumber,
            ReservationStatus status,
            LocalDateTime start,
            LocalDateTime end
    );
}
