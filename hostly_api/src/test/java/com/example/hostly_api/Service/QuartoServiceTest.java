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

import com.example.hostly_api.Enum.StatusQuarto;
import com.example.hostly_api.Enum.TipoQuarto;
import com.example.hostly_api.Model.Quarto;
import com.example.hostly_api.Repository.QuartoRepository;

public class QuartoServiceTest {
    @InjectMocks
    private QuartoService quartoService;

    @Mock
    private QuartoRepository quartoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarQuartoPorId() {
        String idQuarto = "1";
        Quarto quartoExistente = new Quarto();
        quartoExistente.setId_quarto(idQuarto);
        quartoExistente.setCodigo_quarto("101");

        when(quartoRepository.findById(idQuarto)).thenReturn(Optional.of(quartoExistente));

        Optional<Quarto> resultado = quartoService.buscarPorId(idQuarto);

        assertTrue(resultado.isPresent());
        assertEquals("101", resultado.get().getCodigo_quarto());
        verify(quartoRepository, times(1)).findById(idQuarto);
    }

    @Test
    public void testBuscarQuartoPorCodigo() {
        String codigoQuarto = "101";
        Quarto quartoExistente = new Quarto();
        quartoExistente.setCodigo_quarto(codigoQuarto);
        quartoExistente.setTipo_quarto(TipoQuarto.SUITE);

        when(quartoRepository.findByCodigoQuarto(codigoQuarto)).thenReturn(Optional.of(quartoExistente));

        Optional<Quarto> resultado = quartoService.buscarPorCodigo(codigoQuarto);

        assertTrue(resultado.isPresent());
        assertEquals(TipoQuarto.SUITE, resultado.get().getTipo_quarto());
        verify(quartoRepository, times(1)).findByCodigoQuarto(codigoQuarto);
    }

    @Test
    public void testSalvarQuarto() {
        Quarto novoQuarto = new Quarto();
        novoQuarto.setCodigo_quarto("101");
        novoQuarto.setTipo_quarto(TipoQuarto.SUITE);
        novoQuarto.setCapacidade_quarto(2);
        novoQuarto.setValor_quarto(200.00);
        novoQuarto.setStatus(StatusQuarto.DISPONIVEL);

        when(quartoRepository.save(novoQuarto)).thenReturn(novoQuarto);

        Quarto resultado = quartoService.salvar(novoQuarto);

        assertEquals("101", resultado.getCodigo_quarto());
        assertEquals(TipoQuarto.SUITE, resultado.getTipo_quarto());
        verify(quartoRepository, times(1)).save(novoQuarto);
    }

    @Test
    public void testAtualizarQuartoExistente() {
        String idQuarto = "1";
        Quarto quartoExistente = new Quarto();
        quartoExistente.setId_quarto(idQuarto);
        quartoExistente.setCodigo_quarto("101");
        quartoExistente.setTipo_quarto(TipoQuarto.SUITE);
        quartoExistente.setCapacidade_quarto(2);
        quartoExistente.setValor_quarto(200.00);
        quartoExistente.setStatus(StatusQuarto.DISPONIVEL);

        Quarto dadosAtualizados = new Quarto();
        dadosAtualizados.setCodigo_quarto("102");
        dadosAtualizados.setTipo_quarto(TipoQuarto.DUPLEX);
        dadosAtualizados.setCapacidade_quarto(4);
        dadosAtualizados.setValor_quarto(400.00);
        dadosAtualizados.setStatus(StatusQuarto.RESERVADO);

        when(quartoRepository.findById(idQuarto)).thenReturn(Optional.of(quartoExistente));
        when(quartoRepository.save(any(Quarto.class))).thenReturn(quartoExistente);

        Optional<Quarto> resultado = quartoService.atualizar(idQuarto, dadosAtualizados);

        assertTrue(resultado.isPresent());
        assertEquals(TipoQuarto.DUPLEX, resultado.get().getTipo_quarto());  // Verifica se o tipo do quarto não mudou
        verify(quartoRepository, times(1)).findById(idQuarto);
        verify(quartoRepository, times(1)).save(quartoExistente);
    }

     @Test
    public void testDeletarQuarto() {
        String idQuarto = "1";
        doNothing().when(quartoRepository).deleteById(idQuarto);

        // Chama o método de deletar
        quartoService.deletar(idQuarto);

        // Verifica se o método deleteById foi chamado corretamente
        verify(quartoRepository, times(1)).deleteById(idQuarto);
    }


}
