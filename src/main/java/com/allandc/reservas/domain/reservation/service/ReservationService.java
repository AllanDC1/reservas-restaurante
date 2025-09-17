package com.allandc.reservas.domain.reservation.service;

import com.allandc.reservas.domain.reservation.dto.CreateReservationDTO;
import com.allandc.reservas.domain.diningtable.DiningTable;
import com.allandc.reservas.domain.reservation.Reservation;
import com.allandc.reservas.domain.user.User;
import com.allandc.reservas.domain.diningtable.DiningTableStatus;
import com.allandc.reservas.domain.reservation.ReservationStatus;
import com.allandc.reservas.domain.user.UserRoles;
import com.allandc.reservas.domain.diningtable.repository.DiningTableRepository;
import com.allandc.reservas.domain.reservation.repository.ReservationRepository;
import com.allandc.reservas.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Reservation createReservation(UUID userId, CreateReservationDTO dto) {

        User tempUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado - id " + userId));

        DiningTable tempTable = diningTableRepository.findByNumber(dto.tableNumber())
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada - id " + dto.tableNumber()));

        if (tempTable.getStatus().equals(DiningTableStatus.RESERVED)) throw new RuntimeException("Mesa já reservada.");
        if (tempTable.getStatus().equals(DiningTableStatus.INACTIVE)) throw new RuntimeException("Mesa temporariamente desativada.");

        if (dto.numberOfGuests() > tempTable.getCapacity()) throw new RuntimeException("Capacidade da mesa excedida.");

        // possível implementação de verificação da data/horário de funcionamento do restaurante

        List<Reservation> conflicts = reservationRepository.findByDiningTableNumberAndStatusAndDateBetween(
                dto.tableNumber(),
                ReservationStatus.ACTIVE,
                dto.date().minusHours(2),
                dto.date().plusHours(2)
        );

        boolean hasConflict = conflicts.stream().anyMatch(r -> {
            LocalDateTime existingStart = r.getDate();
            LocalDateTime existingEnd = existingStart.plusHours(2);
            return dto.date().isBefore(existingEnd) && dto.date().plusHours(2).isAfter(existingStart);
        });

        if (hasConflict) throw new RuntimeException("A mesa já foi reservada nesse horário.");

        Reservation newReservation = new Reservation();

        newReservation.setUser(tempUser);
        newReservation.setDiningTable(tempTable);
        newReservation.setDate(dto.date());
        newReservation.setStatus(ReservationStatus.ACTIVE);

        return reservationRepository.save(newReservation);
    }

    public List<Reservation> getReservationsByUser(UUID userId) {
        return reservationRepository.findByUserId(userId);
    }

    public void cancelReservation(UUID id) {
        Reservation tempReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada - id " + id));

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedUser.getRole() != UserRoles.ADMIN && !tempReservation.getUser().getEmail().equals(loggedUser.getEmail())) {
            throw new RuntimeException("Você só pode cancelar suas próprias reservas.");
        }

        if (tempReservation.getStatus().equals(ReservationStatus.CANCELLED)) {
            throw new RuntimeException("Reserva já cancelada.");
        }

        tempReservation.setStatus(ReservationStatus.CANCELLED);

        DiningTable tempTable = tempReservation.getDiningTable();
        tempTable.setStatus(DiningTableStatus.AVAILABLE);

        reservationRepository.save(tempReservation); // a tempTable será salva em cascata
    }
}
