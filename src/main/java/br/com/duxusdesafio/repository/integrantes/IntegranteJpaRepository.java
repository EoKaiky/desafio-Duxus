package br.com.duxusdesafio.repository.integrantes;

import br.com.duxusdesafio.model.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IntegranteJpaRepository extends JpaRepository <Integrante, UUID> {
    Optional<Integrante> findById(Long id);
}
