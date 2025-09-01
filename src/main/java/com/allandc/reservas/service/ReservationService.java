package com.allandc.reservas.service;

import com.allandc.reservas.dto.CreateReservationDTO;
import com.allandc.reservas.entity.DiningTable;
import com.allandc.reservas.entity.Reservation;
import com.allandc.reservas.entity.User;
import com.allandc.reservas.repository.DiningTableRepository;
import com.allandc.reservas.repository.ReservationRepository;
import com.allandc.reservas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private DiningTableRepository diningTableRepositoryRepository;
    private UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              DiningTableRepository diningTableRepositoryRepository,
                              UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.diningTableRepositoryRepository = diningTableRepositoryRepository;
        this.userRepository = userRepository;
    }

    public Reservation createReservation(int userId, CreateReservationDTO data) {

        // alterar quando implementar autenticação
        User tempUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not Found - id " + userId));

        DiningTable tempTable = diningTableRepositoryRepository.findById(data.tableId())
                .orElseThrow(() -> new RuntimeException("Dining Table not Found - id " + data.tableId()));

        Reservation newReservation = new Reservation();

        newReservation.setUser(tempUser);
        newReservation.setDiningTable(tempTable);
        newReservation.setDate(data.date());
        newReservation.setStatus("ativa");

        return reservationRepository.save(newReservation);
    }

    public List<Reservation> getReservationsByUser(int userId) {
        // alterar quando implementar autenticação
        return reservationRepository.findByUserId(userId);
    }

    public void cancelReservation(int id) {
        Reservation tempReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not Found - id " + id));

        if (tempReservation.getStatus().equals("cancelada")) {
            throw new RuntimeException("Reservation already cancelled");
        }

        tempReservation.setStatus("cancelada");
        reservationRepository.save(tempReservation);
    }
}
