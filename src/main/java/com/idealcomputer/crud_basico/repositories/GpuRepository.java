package com.idealcomputer.crud_basico.repositories;

import com.idealcomputer.crud_basico.models.GpuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpuRepository extends JpaRepository<GpuModel, Long> {
}