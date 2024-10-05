package com.example.hostly_api.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.hostly_api.Model.Hospede;
import com.example.hostly_api.Repository.HospedeRepository;

import jakarta.persistence.EntityNotFoundException;

public class HospedeServiceTest {
@InjectMocks
    private HospedeService hospedeService;

    @Mock
    private HospedeRepository hospedeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Teste para buscar hóspede por CPF
    @Test
    public void testBuscarPorCpfHospedeExistente() {
        String cpf = "12345678900";
        Hospede mockHospede = new Hospede();
        mockHospede.setCpf(cpf);
        mockHospede.setNome("João");

        when(hospedeRepository.findByCpf(cpf)).thenReturn(Optional.of(mockHospede));

        Optional<Hospede> resultado = hospedeService.buscarPorCpf(cpf);

        assertTrue(resultado.isPresent());
        assertEquals("João", resultado.get().getNome());
        verify(hospedeRepository, times(1)).findByCpf(cpf);
    }

    @Test
    public void testBuscarPorCpfHospedeNaoExistente() {
        String cpf = "00000000000";

        when(hospedeRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Optional<Hospede> resultado = hospedeService.buscarPorCpf(cpf);

        assertFalse(resultado.isPresent());
        verify(hospedeRepository, times(1)).findByCpf(cpf);
    }

    // Teste para buscar hóspede por email
    @Test
    public void testBuscarPorEmailHospedeExistente() {
        String email = "hospede@example.com";
        Hospede mockHospede = new Hospede();
        mockHospede.setEmail(email);
        mockHospede.setNome("Maria");

        when(hospedeRepository.findByEmail(email)).thenReturn(Optional.of(mockHospede));

        Optional<Hospede> resultado = hospedeService.buscarPorEmail(email);

        assertTrue(resultado.isPresent());
        assertEquals("Maria", resultado.get().getNome());
        verify(hospedeRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testBuscarPorEmailHospedeNaoExistente() {
        String email = "naoexiste@example.com";

        when(hospedeRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<Hospede> resultado = hospedeService.buscarPorEmail(email);

        assertFalse(resultado.isPresent());
        verify(hospedeRepository, times(1)).findByEmail(email);
    }

    // Teste para salvar um novo hóspede
    @Test
    public void testSalvarHospede() {
        Hospede novoHospede = new Hospede();
        novoHospede.setNome("Carlos");
        novoHospede.setCpf("98765432100");

        when(hospedeRepository.save(novoHospede)).thenReturn(novoHospede);

        Hospede resultado = hospedeService.salvar(novoHospede);

        assertEquals("Carlos", resultado.getNome());
        assertEquals("98765432100", resultado.getCpf());
        verify(hospedeRepository, times(1)).save(novoHospede);
    }

    // Teste para atualizar um hóspede existente
    @Test
    public void testAtualizarHospedeExistente() {
        String idHospede = "1";
        Hospede hospedeExistente = new Hospede();
        hospedeExistente.setId_hospede(idHospede);
        hospedeExistente.setNome("Ana");

        Hospede dadosAtualizados = new Hospede();
        dadosAtualizados.setNome("Ana Maria");

        when(hospedeRepository.findById(idHospede)).thenReturn(Optional.of(hospedeExistente));
        when(hospedeRepository.save(any(Hospede.class))).thenReturn(hospedeExistente);

        Optional<Hospede> resultado = hospedeService.atualizar(idHospede, dadosAtualizados);

        assertTrue(resultado.isPresent());
        assertEquals("Ana Maria", resultado.get().getNome());
        verify(hospedeRepository, times(1)).findById(idHospede);
        verify(hospedeRepository, times(1)).save(hospedeExistente);
    }

    // Teste para tentar atualizar um hóspede que não existe
    @Test
    public void testAtualizarHospedeNaoExistente() {
        String idHospede = "99";
        Hospede dadosAtualizados = new Hospede();
        dadosAtualizados.setNome("João Atualizado");

        when(hospedeRepository.findById(idHospede)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            hospedeService.atualizar(idHospede, dadosAtualizados);
        });

        assertEquals("Hóspede não encontrado com o ID: " + idHospede, exception.getMessage());
        verify(hospedeRepository, times(1)).findById(idHospede);
        verify(hospedeRepository, never()).save(any(Hospede.class));
    }

    // Teste para deletar um hóspede
    @Test
    public void testDeletarHospede() {
        String idHospede = "1";

        doNothing().when(hospedeRepository).deleteById(idHospede);

        hospedeService.deletar(idHospede);

        verify(hospedeRepository, times(1)).deleteById(idHospede);
    }
}
