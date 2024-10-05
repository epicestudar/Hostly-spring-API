package com.example.hostly_api.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.hostly_api.Enum.StatusReserva;
import com.example.hostly_api.Model.Reserva;
import com.example.hostly_api.Repository.ReservaRepository;

public class ReservaServiceTest {
    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
@Test
    public void testBuscarReservaPorId() {
        String idReserva = "1";
        Reserva reservaExistente = new Reserva();
        reservaExistente.setId_reserva(idReserva);

        when(reservaRepository.findById(idReserva)).thenReturn(Optional.of(reservaExistente));

        Optional<Reserva> resultado = reservaService.buscarPorId(idReserva);

        assertTrue(resultado.isPresent());
        assertEquals(idReserva, resultado.get().getId_reserva());
        verify(reservaRepository, times(1)).findById(idReserva);
    }

    @Test
    public void testSalvarReserva() {
        Reserva novaReserva = new Reserva();
        novaReserva.setQuantidade_diarias(3);
        novaReserva.setStatus(StatusReserva.PENDENTE); 

        when(reservaRepository.save(novaReserva)).thenReturn(novaReserva);

        Reserva resultado = reservaService.salvar(novaReserva);

        assertEquals(3, resultado.getQuantidade_diarias());
        assertEquals(StatusReserva.PENDENTE, resultado.getStatus());
        verify(reservaRepository, times(1)).save(novaReserva);
    }

    @Test
    public void testAtualizarReservaExistente() {
        String idReserva = "1";
        Reserva reservaExistente = new Reserva();
        reservaExistente.setId_reserva(idReserva);
        reservaExistente.setQuantidade_diarias(2);
        reservaExistente.setStatus(StatusReserva.PENDENTE); 

        Reserva dadosAtualizados = new Reserva();
        dadosAtualizados.setQuantidade_diarias(4);
        dadosAtualizados.setStatus(StatusReserva.CONFIRMADO); 

        when(reservaRepository.findById(idReserva)).thenReturn(Optional.of(reservaExistente));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaExistente);

        Optional<Reserva> resultado = reservaService.atualizar(idReserva, dadosAtualizados);

        assertTrue(resultado.isPresent());
        assertEquals(4, resultado.get().getQuantidade_diarias()); // Verifica se a quantidade de diárias não mudou
        assertEquals(StatusReserva.CONFIRMADO, resultado.get().getStatus()); // Verifica se o status não mudou
        verify(reservaRepository, times(1)).findById(idReserva);
        verify(reservaRepository, times(1)).save(reservaExistente);
    }

     @Test
    public void testDeletarReserva() {
        String idReserva = "1";
        doNothing().when(reservaRepository).deleteById(idReserva);

        // Chama o método de deletar
        reservaService.deletar(idReserva);

        // Verifica se o método deleteById foi chamado corretamente
        verify(reservaRepository, times(1)).deleteById(idReserva);
    }

}
