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

import com.example.hostly_api.Enum.MetodoPagamento;
import com.example.hostly_api.Model.Pagamento;
import com.example.hostly_api.Repository.PagamentoRepository;

import jakarta.persistence.EntityNotFoundException;

public class PagamentoServiceTest {
    @InjectMocks
    private PagamentoService pagamentoService;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Teste para buscar pagamento por ID
    @Test
    public void testBuscarPorIdPagamentoExistente() {
        String idPagamento = "1";
        Pagamento mockPagamento = new Pagamento();
        mockPagamento.setId_pagamento(idPagamento);

        when(pagamentoRepository.findById(idPagamento)).thenReturn(Optional.of(mockPagamento));

        Optional<Pagamento> resultado = pagamentoService.buscarPorId(idPagamento);

        assertTrue(resultado.isPresent());
        assertEquals(idPagamento, resultado.get().getId_pagamento());
        verify(pagamentoRepository, times(1)).findById(idPagamento);
    }

    @Test
    public void testBuscarPorIdPagamentoNaoExistente() {
        String idPagamento = "1";

        when(pagamentoRepository.findById(idPagamento)).thenReturn(Optional.empty());

        Optional<Pagamento> resultado = pagamentoService.buscarPorId(idPagamento);

        assertFalse(resultado.isPresent());
        verify(pagamentoRepository, times(1)).findById(idPagamento);
    }

    // Teste para buscar pagamento por ID da reserva
    @Test
    public void testBuscarPorReservaExistente() {
        String idReserva = "1";
        Pagamento mockPagamento = new Pagamento();
        mockPagamento.setId_pagamento("1");

        when(pagamentoRepository.findByReserva_IdReserva(idReserva)).thenReturn(Optional.of(mockPagamento));

        Optional<Pagamento> resultado = pagamentoService.buscarPorReserva(idReserva);

        assertTrue(resultado.isPresent());
        assertEquals("1", resultado.get().getId_pagamento());
        verify(pagamentoRepository, times(1)).findByReserva_IdReserva(idReserva);
    }

    @Test
    public void testBuscarPorReservaNaoExistente() {
        String idReserva = "1";

        when(pagamentoRepository.findByReserva_IdReserva(idReserva)).thenReturn(Optional.empty());

        Optional<Pagamento> resultado = pagamentoService.buscarPorReserva(idReserva);

        assertFalse(resultado.isPresent());
        verify(pagamentoRepository, times(1)).findByReserva_IdReserva(idReserva);
    }

    // Teste para salvar um novo pagamento
    @Test
    public void testSalvarPagamento() {
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setMetodo_pagamento(MetodoPagamento.CREDITO);
    
        when(pagamentoRepository.save(novoPagamento)).thenReturn(novoPagamento);
    
        Pagamento resultado = pagamentoService.salvar(novoPagamento);
    
        assertEquals(MetodoPagamento.CREDITO, resultado.getMetodo_pagamento()); // Comparando com o enum
        verify(pagamentoRepository, times(1)).save(novoPagamento);
    }
    

    // Teste para atualizar um pagamento existente
    @Test
public void testAtualizarPagamentoExistente() {
    String idPagamento = "1";
    Pagamento pagamentoExistente = new Pagamento();
    pagamentoExistente.setId_pagamento(idPagamento);
    pagamentoExistente.setMetodo_pagamento(MetodoPagamento.BOLETO);  // Valor original

    Pagamento dadosAtualizados = new Pagamento();
    dadosAtualizados.setMetodo_pagamento(MetodoPagamento.DEBITO);  // Valor atualizado

    // Mockando as chamadas do repositório
    when(pagamentoRepository.findById(idPagamento)).thenReturn(Optional.of(pagamentoExistente));
    when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamentoExistente);

    Optional<Pagamento> resultado = pagamentoService.atualizar(idPagamento, dadosAtualizados);

    assertTrue(resultado.isPresent());
    assertEquals(MetodoPagamento.DEBITO, resultado.get().getMetodo_pagamento());  // Comparar com o enum correto
    verify(pagamentoRepository, times(1)).findById(idPagamento);
    verify(pagamentoRepository, times(1)).save(pagamentoExistente);
}


    // Teste para tentar atualizar um pagamento que não existe
    @Test
    public void testAtualizarPagamentoNaoExistente() {
        String idPagamento = "99";
        Pagamento dadosAtualizados = new Pagamento();
        dadosAtualizados.setMetodo_pagamento(MetodoPagamento.valueOf("PIX"));

        when(pagamentoRepository.findById(idPagamento)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            pagamentoService.atualizar(idPagamento, dadosAtualizados);
        });

        assertEquals("Pagamento não encontrado com o ID: " + idPagamento, exception.getMessage());
        verify(pagamentoRepository, times(1)).findById(idPagamento);
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }

    // Teste para deletar um pagamento
    @Test
    public void testDeletarPagamento() {
        String idPagamento = "1";

        doNothing().when(pagamentoRepository).deleteById(idPagamento);

        pagamentoService.deletar(idPagamento);

        verify(pagamentoRepository, times(1)).deleteById(idPagamento);
    }
}
