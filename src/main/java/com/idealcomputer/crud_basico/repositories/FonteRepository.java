package com.idealcomputer.crud_basico.repositories;

import com.idealcomputer.crud_basico.models.FonteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FonteRepository extends JpaRepository<FonteModel, Long> {
}
