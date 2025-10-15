package com.idealcomputer.crud_basico.repositories;

import com.idealcomputer.crud_basico.models.MemoriaRamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoriaRamRepository extends JpaRepository<MemoriaRamModel, Long> {
}
