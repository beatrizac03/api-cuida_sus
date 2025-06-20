package com.api.cuida.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.cuida.models.SalaAtendimento;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.models.TipoAtendimento;

public interface SalaRepository extends JpaRepository<SalaAtendimento, Long> {
    Optional<SalaAtendimento> findByNomeSala(String nomeSala);

    @Query("""
                SELECT sa FROM SalaAtendimento sa
                WHERE sa.tipoAtendimento = :tipo
                AND sa.id NOT IN (
                    SELECT a.salaAtendimento.id FROM Atendimento a
                    WHERE a.salaAtendimento IS NOT NULL
                      AND a.statusAtendimento = 'AGUARDANDO_NA_FILA'
                )
            """)
    List<SalaAtendimento> findSalasDisponiveisPorTipo(@Param("tipo") TipoAtendimento tipo);

}
