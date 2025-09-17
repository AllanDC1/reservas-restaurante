package com.allandc.reservas.domain.diningtable.controller;

import com.allandc.reservas.domain.diningtable.dto.CreateDiningTableDTO;
import com.allandc.reservas.domain.diningtable.dto.DiningTableResponseDTO;
import com.allandc.reservas.domain.diningtable.dto.UpdateDiningTableDTO;
import com.allandc.reservas.domain.diningtable.DiningTable;
import com.allandc.reservas.domain.diningtable.service.DiningTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class DiningTableController {

    private final DiningTableService diningTableService;

    @Autowired
    public DiningTableController(DiningTableService diningTableService) {
        this.diningTableService = diningTableService;
    }

    @Operation(summary = "Lista todas as mesas do restaurante e suas informações",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping
    public List<DiningTableResponseDTO> getDiningTables() {
        List<DiningTable> result = diningTableService.getAllDiningTables();

        return result.stream()
                .map(r -> new DiningTableResponseDTO(r.getNumber(), r.getCapacity(), r.getStatus()))
                .toList();
    }

    @Operation(summary = "Adiciona uma nova mesa ao restaurante",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping
    public DiningTableResponseDTO addDiningTable(@Valid @RequestBody CreateDiningTableDTO tableDTO) {
        DiningTable tempTable = diningTableService.createDiningTable(tableDTO);

        return new DiningTableResponseDTO(tempTable.getNumber(), tempTable.getCapacity(), tempTable.getStatus());
    }

    @Operation(summary = "Atualiza informações de uma mesa do restaurante",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @PatchMapping("/{id}")
    public DiningTableResponseDTO updateDiningTable(@PathVariable int id,
                                                    @Valid @RequestBody UpdateDiningTableDTO tableDTO) {
        DiningTable tempTable = diningTableService.updateDiningTable(id, tableDTO);

        return new DiningTableResponseDTO(tempTable.getNumber(), tempTable.getCapacity(), tempTable.getStatus());
    }

    @Operation(summary = "Remove uma mesa do restaurante",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @DeleteMapping("/{id}")
    public void removeDiningTable(@PathVariable int id) {
        diningTableService.deleteDiningTable(id);
    }
}
