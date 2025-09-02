package com.allandc.reservas.service;

import com.allandc.reservas.dto.CreateDiningTableDTO;
import com.allandc.reservas.dto.UpdateDiningTableDTO;
import com.allandc.reservas.entity.DiningTable;
import com.allandc.reservas.enums.DiningTableStatus;
import com.allandc.reservas.repository.DiningTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiningTableService {

    private final DiningTableRepository repository;

    @Autowired
    public DiningTableService(DiningTableRepository diningTableRepository) {
        this.repository = diningTableRepository;
    }

    public DiningTable createDiningTable(CreateDiningTableDTO dto) {
        DiningTable newDiningTable = new DiningTable();

        newDiningTable.setNumber(dto.number());
        newDiningTable.setCapacity(dto.capacity());
        newDiningTable.setStatus(DiningTableStatus.AVAILABLE);

        return repository.save(newDiningTable);
    }

    public List<DiningTable> getAllDiningTables() {
        return repository.findAll();
    }

    public DiningTable updateDiningTable(int id, UpdateDiningTableDTO dto) {
        DiningTable tempTable = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dining Table not Found - id " + id));

        // null -> campo vazio/não alterar
        if (dto.capacity() != null) tempTable.setCapacity(dto.capacity());
        if (dto.status() != null) tempTable.setStatus(dto.status());

        return repository.save(tempTable);
    }

    public void deleteDiningTable(int id) {
        repository.deleteById(id);
    }
}
