package com.allandc.reservas.service;

import com.allandc.reservas.dto.CreateReservationDTO;
import com.allandc.reservas.entity.Reservation;
import com.allandc.reservas.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationRepository repository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.repository = reservationRepository;
    }

    public Reservation createReservation(CreateReservationDTO data) {

        Reservation newReservation = new Reservation();

        // newReservation.setDiningTable(data.tableId()); // verificar como sera associado
        newReservation.setDate(data.date());

        return repository.save(newReservation);
    }
}
