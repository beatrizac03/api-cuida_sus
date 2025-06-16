package com.api.cuida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.cuida.DTOs.FuncionarioLoginDto;
import com.api.cuida.DTOs.FuncionarioRegistroDto;
import com.api.cuida.DTOs.FuncionarioResponseDto;
import com.api.cuida.DTOs.PacienteLoginDto;
import com.api.cuida.models.CargoFuncionario;
import com.api.cuida.models.Funcionario;
import com.api.cuida.models.Paciente;
import com.api.cuida.repositories.CargoFuncionarioRepository;
import com.api.cuida.repositories.FuncionarioRepository;
import com.api.cuida.repositories.PacienteRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoFuncionarioRepository cargoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Paciente login(PacienteLoginDto paciente) {
        return pacienteRepository
                .findByCpfAndNomeMaeAndCidadeNatal(paciente.getCpf(), paciente.getNomeMae(), paciente.getCidadeNatal())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public Paciente getUserByCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));
    }

    public FuncionarioResponseDto loginFuncionario(FuncionarioLoginDto dto) {
        Funcionario funcionario = funcionarioRepository
                .findByMatricula(dto.getMatricula())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        if (!BCrypt.checkpw(dto.getSenha(), funcionario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return mapToResponseDto(funcionario);
    }

    public FuncionarioResponseDto registerFuncionario(FuncionarioRegistroDto dto) {
        if (funcionarioRepository.findByMatricula(dto.getMatricula()).isPresent()) {
            throw new RuntimeException("Matrícula já cadastrada");
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setCpf(dto.getCpf());
        funcionario.setMatricula(dto.getMatricula());
        funcionario.setEmail(dto.getEmail());
        funcionario.setTipoAtendimento(dto.getTipoAtendimento());
        funcionario.setSenha(passwordEncoder.encode(dto.getSenha()));

        CargoFuncionario cargo = cargoRepository.findById(dto.getCargoId())
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));
        funcionario.setCargo(cargo);

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        // converter para response DTO
        return mapToResponseDto(funcionarioSalvo);
    }

    public FuncionarioResponseDto mapToResponseDto(Funcionario funcionario) {
        FuncionarioResponseDto dto = new FuncionarioResponseDto();
        dto.setNome(funcionario.getNome());
        dto.setCpf(funcionario.getCpf());
        dto.setMatricula(funcionario.getMatricula());
        dto.setEmail(funcionario.getEmail());
        dto.setTipoAtendimento(funcionario.getTipoAtendimento());
        dto.setCargo(funcionario.getCargo().getNome());
        // outros campos conforme necessário
        return dto;
    }
}
