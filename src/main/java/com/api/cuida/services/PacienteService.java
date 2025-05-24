package com.api.cuida.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.cuida.models.Paciente;
import com.api.cuida.repositories.PacienteRepository;

@Service
public class PacienteService implements UserDetailsService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buscarPorCpf(username)
                .orElseThrow(() -> new UsernameNotFoundException("Paciente com CPF " + username + " não encontrado"));
    }

    public Optional<Paciente> listarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente cadastrarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizarPaciente(Long id, Paciente paciente) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado!");
        }

        paciente.setId(id);

        return pacienteRepository.save(paciente);
    }

    public void deletarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado!");
        }

        pacienteRepository.deleteById(id);
    }
}
