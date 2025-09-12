package com.allandc.reservas.controller;

import com.allandc.reservas.dto.CreateReservationDTO;
import com.allandc.reservas.dto.ReservationResponseDTO;
import com.allandc.reservas.entity.Reservation;
import com.allandc.reservas.entity.User;
import com.allandc.reservas.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Operation(summary = "Lista as reservas do usuário logado",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping
    public List<ReservationResponseDTO> getReservations(@AuthenticationPrincipal User user) {

        List<Reservation> result = reservationService.getReservationsByUser(user.getId());

        return result.stream()
                .map(r -> new ReservationResponseDTO(
                        r.getDiningTable().getNumber(),
                        r.getDate(),
                        r.getStatus()))
                .toList();
    }

    @Operation(summary = "Cria uma reserva para o usuário logado",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping
    public ReservationResponseDTO createReservation(@AuthenticationPrincipal User user,
                                                    @Valid @RequestBody CreateReservationDTO reservationDTO) {

        Reservation tempReservation = reservationService.createReservation(user.getId(), reservationDTO);

        return new ReservationResponseDTO(
                tempReservation.getDiningTable().getNumber(),
                tempReservation.getDate(),
                tempReservation.getStatus());
    }

    @Operation(summary = "Cancela uma reserva do usuário logado",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @PatchMapping("/{id}/cancel")
    public void cancelReservation(@PathVariable UUID id) {
        reservationService.cancelReservation(id);
    }
}
