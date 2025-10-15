package com.idealcomputer.crud_basico.repositories;

import com.idealcomputer.crud_basico.models.GabineteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GabineteRepository extends JpaRepository<GabineteModel, Long> {
}
