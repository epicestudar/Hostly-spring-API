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

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.hostly_api.Enum.MetodoPagamento;
import com.example.hostly_api.Model.Pagamento;
import com.example.hostly_api.Repository.PagamentoRepository;


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
        String id = "1";
        Pagamento mockPagamento = new Pagamento();
        mockPagamento.setId(id);

        when(pagamentoRepository.findById(id)).thenReturn(Optional.of(mockPagamento));

        Optional<Pagamento> resultado = pagamentoService.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        verify(pagamentoRepository, times(1)).findById(id);
    }

    @Test
    public void testBuscarPorIdPagamentoNaoExistente() {
        String id = "1";

        when(pagamentoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Pagamento> resultado = pagamentoService.buscarPorId(id);

        assertFalse(resultado.isPresent());
        verify(pagamentoRepository, times(1)).findById(id);
    }

    // Teste para buscar pagamento por ID da reserva
    @Test
    public void testBuscarPorReservaExistente() {
        String id = "1";
        Pagamento mockPagamento = new Pagamento();
        mockPagamento.setId("1");

        when(pagamentoRepository.findByReserva_Id(id)).thenReturn(Optional.of(mockPagamento));

        Optional<Pagamento> resultado = pagamentoService.buscarPorReserva(id);

        assertTrue(resultado.isPresent());
        assertEquals("1", resultado.get().getId());
        verify(pagamentoRepository, times(1)).findByReserva_Id(id);
    }

    @Test
    public void testBuscarPorReservaNaoExistente() {
        String id = "1";

        when(pagamentoRepository.findByReserva_Id(id)).thenReturn(Optional.empty());

        Optional<Pagamento> resultado = pagamentoService.buscarPorReserva(id);

        assertFalse(resultado.isPresent());
        verify(pagamentoRepository, times(1)).findByReserva_Id(id);
    }

    // Teste para salvar um novo pagamento
    @Test
    public void testSalvarPagamento() {
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setMetodoPagamento(MetodoPagamento.CREDITO);
    
        when(pagamentoRepository.save(novoPagamento)).thenReturn(novoPagamento);
    
        Pagamento resultado = pagamentoService.salvar(novoPagamento);
    
        assertEquals(MetodoPagamento.CREDITO, resultado.getMetodoPagamento()); // Comparando com o enum
        verify(pagamentoRepository, times(1)).save(novoPagamento);
    }
    

    // Teste para atualizar um pagamento existente
    @Test
public void testAtualizarPagamentoExistente() {
    String id = "1";
    Pagamento pagamentoExistente = new Pagamento();
    pagamentoExistente.setId(id);
    pagamentoExistente.setMetodoPagamento(MetodoPagamento.BOLETO);  // Valor original

    Pagamento dadosAtualizados = new Pagamento();
    dadosAtualizados.setMetodoPagamento(MetodoPagamento.DEBITO);  // Valor atualizado

    // Mockando as chamadas do repositório
    when(pagamentoRepository.findById(id)).thenReturn(Optional.of(pagamentoExistente));
    when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamentoExistente);

    Optional<Pagamento> resultado = pagamentoService.atualizar(id, dadosAtualizados);

    assertTrue(resultado.isPresent());
    assertEquals(MetodoPagamento.DEBITO, resultado.get().getMetodoPagamento());  // Comparar com o enum correto
    verify(pagamentoRepository, times(1)).findById(id);
    verify(pagamentoRepository, times(1)).save(pagamentoExistente);
}


    // Teste para tentar atualizar um pagamento que não existe
    @Test
    public void testAtualizarPagamentoNaoExistente() {
        String id = "99";
        Pagamento dadosAtualizados = new Pagamento();
        dadosAtualizados.setMetodoPagamento(MetodoPagamento.valueOf("PIX"));

        when(pagamentoRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            pagamentoService.atualizar(id, dadosAtualizados);
        });

        assertEquals("Pagamento não encontrado com o ID: " + id, exception.getMessage());
        verify(pagamentoRepository, times(1)).findById(id);
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }

    // Teste para deletar um pagamento
    @Test
    public void testDeletarPagamento() {
        String id = "1";

        doNothing().when(pagamentoRepository).deleteById(id);

        pagamentoService.deletar(id);

        verify(pagamentoRepository, times(1)).deleteById(id);
    }
}
