package com.allandc.reservas.service;

import com.allandc.reservas.dto.CreateReservationDTO;
import com.allandc.reservas.entity.DiningTable;
import com.allandc.reservas.entity.Reservation;
import com.allandc.reservas.entity.User;
import com.allandc.reservas.enums.DiningTableStatus;
import com.allandc.reservas.enums.ReservationStatus;
import com.allandc.reservas.repository.DiningTableRepository;
import com.allandc.reservas.repository.ReservationRepository;
import com.allandc.reservas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final DiningTableRepository diningTableRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              DiningTableRepository diningTableRepository,
                              UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.diningTableRepository = diningTableRepository;
        this.userRepository = userRepository;
    }

    public Reservation createReservation(UUID userId, CreateReservationDTO data) {

        // alterar quando implementar autenticação
        User tempUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not Found - id " + userId));

        DiningTable tempTable = diningTableRepository.findById(data.tableId())
                .orElseThrow(() -> new RuntimeException("Dining Table not Found - id " + data.tableId()));

        // alterar para verificar se a mesa estará reservada no horário desejado
        if (tempTable.getStatus().equals(DiningTableStatus.RESERVED)) throw new RuntimeException("Dining table already reserved");
        if (tempTable.getStatus().equals(DiningTableStatus.INACTIVE)) throw new RuntimeException("Dining table temporarily inactive");

        if (data.numberOfGuests() > tempTable.getCapacity()) throw new RuntimeException("Dining table capacity exceeded");

        Reservation newReservation = new Reservation();

        newReservation.setUser(tempUser);
        newReservation.setDiningTable(tempTable);
        newReservation.setDate(data.date());
        newReservation.setStatus(ReservationStatus.ACTIVE);

        return reservationRepository.save(newReservation);
    }

    public List<Reservation> getReservationsByUser(UUID userId) {
        // alterar quando implementar autenticação
        return reservationRepository.findByUserId(userId);
    }

    public void cancelReservation(UUID id) {
        Reservation tempReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not Found - id " + id));

        if (tempReservation.getStatus().equals(ReservationStatus.CANCELLED)) {
            throw new RuntimeException("Reservation already cancelled");
        }

        tempReservation.setStatus(ReservationStatus.CANCELLED);

        DiningTable tempTable = tempReservation.getDiningTable();
        tempTable.setStatus(DiningTableStatus.AVAILABLE);

        reservationRepository.save(tempReservation); // a tempTable será salva em cascata
    }
}
