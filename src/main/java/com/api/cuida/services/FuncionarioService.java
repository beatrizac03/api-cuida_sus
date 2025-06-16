package com.api.cuida.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.cuida.DTOs.FuncionarioRegistroDto;
import com.api.cuida.models.Funcionario;
import com.api.cuida.repositories.FuncionarioRepository;

@Service
public class FuncionarioService implements UserDetailsService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorMatricula(String matricula) {
        return funcionarioRepository.findByMatricula(matricula);
    }

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        return funcionarioRepository.findByMatricula(matricula)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado"));
    }

    // public Funcionario cadastrarFuncionario(FuncionarioRegistroDto funcionario) {
    //     Funcionario novoFuncionario = new Funcionario();
    //     novoFuncionario.setMatricula(funcionario.getMatricula());
    //     novoFuncionario.setNome(funcionario.getNome());
    //     novoFuncionario.setCpf(funcionario.getCpf());
    //     novoFuncionario.setSenha(funcionario.getSenha());
    //     novoFuncionario.setEmail(funcionario.getEmail());
    //     novoFuncionario.setCargo(funcionario.getCargo());
    //     novoFuncionario.setTipoAtendimento(funcionario.getTipoAtendimento());

    //     return funcionarioRepository.save(novoFuncionario);
    // }

    // public Funcionario atualizarFuncionario(String matricula, FuncionarioRegistroDto funcionario) {
    //     Funcionario funcionarioExistente = funcionarioRepository.findByMatricula(matricula)
    //             .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

    //     funcionarioExistente.setMatricula(funcionario.getMatricula());
    //     funcionarioExistente.setNome(funcionario.getNome());
    //     funcionarioExistente.setCpf(funcionario.getCpf());
    //     funcionarioExistente.setSenha(funcionario.getSenha());
    //     funcionarioExistente.setEmail(funcionario.getEmail());
    //     funcionarioExistente.setCargo(funcionario.getCargo());
    //     funcionarioExistente.setTipoAtendimento(funcionario.getTipoAtendimento());

    //     return funcionarioRepository.save(funcionarioExistente);
    // }

    // public void deletarFuncionario(String matricula) {
    //     Funcionario funcionarioExistente = funcionarioRepository.findByMatricula(matricula)
    //             .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

    //     funcionarioRepository.delete(funcionarioExistente);
    // }
}
