package com.allandc.reservas.service;

import com.allandc.reservas.dto.CreateDiningTableDTO;
import com.allandc.reservas.entity.DiningTable;
import com.allandc.reservas.repository.DiningTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        newDiningTable.setStatus("disponível"); // verificar quem vai settar isso: BD ou Service

        return repository.save(newDiningTable);
    }
}
