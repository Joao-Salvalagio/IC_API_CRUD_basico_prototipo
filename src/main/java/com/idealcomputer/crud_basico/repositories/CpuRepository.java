package com.idealcomputer.crud_basico.repositories;

import com.idealcomputer.crud_basico.models.CpuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepository extends JpaRepository<CpuModel, Long> {
}
