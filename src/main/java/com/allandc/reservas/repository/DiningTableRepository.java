package com.allandc.reservas.repository;

import com.allandc.reservas.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiningTableRepository extends JpaRepository<DiningTable, Integer> {
}
