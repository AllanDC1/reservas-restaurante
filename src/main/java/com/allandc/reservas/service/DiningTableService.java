package com.allandc.reservas.service;

import com.allandc.reservas.dto.CreateDiningTableDTO;
import com.allandc.reservas.dto.UpdateDiningTableDTO;
import com.allandc.reservas.entity.DiningTable;
import com.allandc.reservas.repository.DiningTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiningTableService {

    private DiningTableRepository repository;

    @Autowired
    public DiningTableService(DiningTableRepository diningTableRepository) {
        this.repository = diningTableRepository;
    }

    public DiningTable createDiningTable(CreateDiningTableDTO data) {
        DiningTable newDiningTable = new DiningTable();

        newDiningTable.setNumber(data.number());
        newDiningTable.setCapacity(data.capacity());
        newDiningTable.setStatus("disponível");

        return repository.save(newDiningTable);
    }

    public List<DiningTable> getAllDiningTables() {
        return repository.findAll();
    }

    public void updateDiningTable(int id, UpdateDiningTableDTO data) {
        DiningTable tempTable = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dining Table not Found - id " + id));

        // null -> campo vazio/não alterar
        if (data.capacity() != null) tempTable.setCapacity(data.capacity());
        if (data.status() != null) tempTable.setStatus(data.status());

        repository.save(tempTable);
    }

    public void deleteDiningTable(int id) {
        repository.deleteById(id);
    }
}
