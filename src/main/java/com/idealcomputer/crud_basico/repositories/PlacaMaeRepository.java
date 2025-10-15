package com.idealcomputer.crud_basico.repositories;

import com.idealcomputer.crud_basico.models.PlacaMaeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacaMaeRepository extends JpaRepository<PlacaMaeModel, Long> {
}
