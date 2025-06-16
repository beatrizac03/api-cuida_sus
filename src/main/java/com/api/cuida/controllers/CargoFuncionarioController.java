package com.api.cuida.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.DTOs.CargoResponseDto;
import com.api.cuida.repositories.CargoFuncionarioRepository;

@RestController
public class CargoFuncionarioController {
    @Autowired
    private CargoFuncionarioRepository repository;

    @GetMapping("/cargos")
    public List<CargoResponseDto> listarCargos() {
        return repository.findAll().stream()
                .map(cargo -> new CargoResponseDto(cargo.getId(), cargo.getNome()))
                .collect(Collectors.toList());
    }
}
