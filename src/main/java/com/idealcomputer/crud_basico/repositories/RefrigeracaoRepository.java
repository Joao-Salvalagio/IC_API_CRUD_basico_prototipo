package com.idealcomputer.crud_basico.repositories;

import com.idealcomputer.crud_basico.models.RefrigeracaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefrigeracaoRepository extends JpaRepository<RefrigeracaoModel, Long> {
}
