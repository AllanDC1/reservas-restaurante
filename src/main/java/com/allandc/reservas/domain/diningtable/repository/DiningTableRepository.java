package com.allandc.reservas.domain.diningtable.repository;

import com.allandc.reservas.domain.diningtable.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiningTableRepository extends JpaRepository<DiningTable, Integer> {
    Optional<DiningTable> findByNumber(int i);
}
