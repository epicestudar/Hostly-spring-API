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
        String id = "1";
        Reserva reservaExistente = new Reserva();
        reservaExistente.setId(id);

        when(reservaRepository.findById(id)).thenReturn(Optional.of(reservaExistente));

        Optional<Reserva> resultado = reservaService.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        verify(reservaRepository, times(1)).findById(id);
    }

    @Test
    public void testSalvarReserva() {
        Reserva novaReserva = new Reserva();
        novaReserva.setQuantidadeDiarias(3);
        novaReserva.setStatus(StatusReserva.PENDENTE); 

        when(reservaRepository.save(novaReserva)).thenReturn(novaReserva);

        Reserva resultado = reservaService.salvar(novaReserva);

        assertEquals(3, resultado.getQuantidadeDiarias());
        assertEquals(StatusReserva.PENDENTE, resultado.getStatus());
        verify(reservaRepository, times(1)).save(novaReserva);
    }

    @Test
    public void testAtualizarReservaExistente() {
        String id = "1";
        Reserva reservaExistente = new Reserva();
        reservaExistente.setId(id);
        reservaExistente.setQuantidadeDiarias(2);
        reservaExistente.setStatus(StatusReserva.PENDENTE); 

        Reserva dadosAtualizados = new Reserva();
        dadosAtualizados.setQuantidadeDiarias(4);
        dadosAtualizados.setStatus(StatusReserva.CONFIRMADO); 

        when(reservaRepository.findById(id)).thenReturn(Optional.of(reservaExistente));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaExistente);

        Optional<Reserva> resultado = reservaService.atualizar(id, dadosAtualizados);

        assertTrue(resultado.isPresent());
        assertEquals(4, resultado.get().getQuantidadeDiarias()); // Verifica se a quantidade de diárias não mudou
        assertEquals(StatusReserva.CONFIRMADO, resultado.get().getStatus()); // Verifica se o status não mudou
        verify(reservaRepository, times(1)).findById(id);
        verify(reservaRepository, times(1)).save(reservaExistente);
    }

     @Test
    public void testDeletarReserva() {
        String id = "1";
        doNothing().when(reservaRepository).deleteById(id);

        // Chama o método de deletar
        reservaService.deletar(id);

        // Verifica se o método deleteById foi chamado corretamente
        verify(reservaRepository, times(1)).deleteById(id);
    }

}
