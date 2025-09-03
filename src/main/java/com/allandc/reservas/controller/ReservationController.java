package com.allandc.reservas.controller;

import com.allandc.reservas.dto.CreateReservationDTO;
import com.allandc.reservas.dto.ReservationResponseDTO;
import com.allandc.reservas.entity.Reservation;
import com.allandc.reservas.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponseDTO> getReservations() {

        UUID userId = UUID.randomUUID(); // alterar para usuário autenticado

        List<Reservation> result = reservationService.getReservationsByUser(userId);

        return result.stream()
                .map(r -> new ReservationResponseDTO(
                        r.getDiningTable().getNumber(),
                        r.getDate(),
                        r.getStatus()))
                .toList();
    }

    @PostMapping
    public ReservationResponseDTO createReservation(@RequestBody CreateReservationDTO reservationDTO) {

        UUID userId = UUID.randomUUID(); // alterar para usuário autenticado

        Reservation tempReservation = reservationService.createReservation(userId, reservationDTO);

        return new ReservationResponseDTO(
                tempReservation.getDiningTable().getNumber(),
                tempReservation.getDate(),
                tempReservation.getStatus());
    }

    @PatchMapping("/{id}/cancel")
    public void cancelReservation(@PathVariable UUID id) {
        reservationService.cancelReservation(id);
    }
}
