package com.allandc.reservas.controller;

import com.allandc.reservas.dto.CreateDiningTableDTO;
import com.allandc.reservas.dto.DiningTableResponseDTO;
import com.allandc.reservas.dto.UpdateDiningTableDTO;
import com.allandc.reservas.entity.DiningTable;
import com.allandc.reservas.service.DiningTableService;
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

    @GetMapping
    public List<DiningTableResponseDTO> getDiningTables() {
        List<DiningTable> result = diningTableService.getAllDiningTables();

        return result.stream()
                .map(r -> new DiningTableResponseDTO(r.getNumber(), r.getCapacity(), r.getStatus()))
                .toList();
    }

    @PostMapping
    public DiningTableResponseDTO addDiningTable(@Valid @RequestBody CreateDiningTableDTO tableDTO) {
        DiningTable tempTable = diningTableService.createDiningTable(tableDTO);

        return new DiningTableResponseDTO(tempTable.getNumber(), tempTable.getCapacity(), tempTable.getStatus());
    }

    @PatchMapping("/{id}")
    public DiningTableResponseDTO updateDiningTable(@PathVariable int id,
                                                    @Valid @RequestBody UpdateDiningTableDTO tableDTO) {
        DiningTable tempTable = diningTableService.updateDiningTable(id, tableDTO);

        return new DiningTableResponseDTO(tempTable.getNumber(), tempTable.getCapacity(), tempTable.getStatus());
    }

    @DeleteMapping("/{id}")
    public void removeDiningTable(@PathVariable int id) {
        diningTableService.deleteDiningTable(id);
    }
}
