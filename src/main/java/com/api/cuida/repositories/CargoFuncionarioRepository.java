package com.api.cuida.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cuida.models.CargoFuncionario;

public interface CargoFuncionarioRepository extends JpaRepository<CargoFuncionario, Long> {
    // Aqui você pode adicionar métodos específicos para manipulação de cargos, se necessário
    Optional<CargoFuncionario> findByNome(String nome);
}
