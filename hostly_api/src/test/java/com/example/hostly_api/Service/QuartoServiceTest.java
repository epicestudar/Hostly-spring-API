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
        String id = "1";
        Quarto quartoExistente = new Quarto();
        quartoExistente.setId(id);
        quartoExistente.setCodigoQuarto("101");

        when(quartoRepository.findById(id)).thenReturn(Optional.of(quartoExistente));

        Optional<Quarto> resultado = quartoService.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals("101", resultado.get().getCodigoQuarto());
        verify(quartoRepository, times(1)).findById(id);
    }

    @Test
    public void testBuscarQuartoPorCodigo() {
        String codigoQuarto = "101";
        Quarto quartoExistente = new Quarto();
        quartoExistente.setCodigoQuarto(codigoQuarto);
        quartoExistente.setTipoQuarto(TipoQuarto.SUITE);

        when(quartoRepository.findByCodigoQuarto(codigoQuarto)).thenReturn(Optional.of(quartoExistente));

        Optional<Quarto> resultado = quartoService.buscarPorCodigo(codigoQuarto);

        assertTrue(resultado.isPresent());
        assertEquals(TipoQuarto.SUITE, resultado.get().getTipoQuarto());
        verify(quartoRepository, times(1)).findByCodigoQuarto(codigoQuarto);
    }

    @Test
    public void testSalvarQuarto() {
        Quarto novoQuarto = new Quarto();
        novoQuarto.setCodigoQuarto("101");
        novoQuarto.setTipoQuarto(TipoQuarto.SUITE);
        novoQuarto.setCapacidadeQuarto(2);
        novoQuarto.setValorQuarto(200.00);
        novoQuarto.setStatus(StatusQuarto.DISPONIVEL);

        when(quartoRepository.save(novoQuarto)).thenReturn(novoQuarto);

        Quarto resultado = quartoService.salvar(novoQuarto);

        assertEquals("101", resultado.getCodigoQuarto());
        assertEquals(TipoQuarto.SUITE, resultado.getTipoQuarto());
        verify(quartoRepository, times(1)).save(novoQuarto);
    }

    @Test
    public void testAtualizarQuartoExistente() {
        String id = "1";
        Quarto quartoExistente = new Quarto();
        quartoExistente.setId(id);
        quartoExistente.setCodigoQuarto("101");
        quartoExistente.setTipoQuarto(TipoQuarto.SUITE);
        quartoExistente.setCapacidadeQuarto(2);
        quartoExistente.setValorQuarto(200.00);
        quartoExistente.setStatus(StatusQuarto.DISPONIVEL);

        Quarto dadosAtualizados = new Quarto();
        dadosAtualizados.setCodigoQuarto("102");
        dadosAtualizados.setTipoQuarto(TipoQuarto.DUPLEX);
        dadosAtualizados.setCapacidadeQuarto(4);
        dadosAtualizados.setValorQuarto(400.00);
        dadosAtualizados.setStatus(StatusQuarto.RESERVADO);

        when(quartoRepository.findById(id)).thenReturn(Optional.of(quartoExistente));
        when(quartoRepository.save(any(Quarto.class))).thenReturn(quartoExistente);

        Optional<Quarto> resultado = quartoService.atualizar(id, dadosAtualizados);

        assertTrue(resultado.isPresent());
        assertEquals(TipoQuarto.DUPLEX, resultado.get().getTipoQuarto());  // Verifica se o tipo do quarto não mudou
        verify(quartoRepository, times(1)).findById(id);
        verify(quartoRepository, times(1)).save(quartoExistente);
    }

     @Test
    public void testDeletarQuarto() {
        String id = "1";
        doNothing().when(quartoRepository).deleteById(id);

        // Chama o método de deletar
        quartoService.deletar(id);

        // Verifica se o método deleteById foi chamado corretamente
        verify(quartoRepository, times(1)).deleteById(id);
    }


}
