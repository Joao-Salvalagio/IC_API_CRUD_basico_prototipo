package com.idealcomputer.crud_basico.repositories;

import com.idealcomputer.crud_basico.models.ArmazenamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmazenamentoRepository extends JpaRepository<ArmazenamentoModel, Long> {
}
