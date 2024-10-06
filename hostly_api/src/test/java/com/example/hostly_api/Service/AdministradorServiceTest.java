package com.example.hostly_api.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.hostly_api.Model.Administrador;
import com.example.hostly_api.Repository.AdministradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class AdministradorServiceTest {
    @InjectMocks
    private AdministradorService administradorService;

    @Mock
    private AdministradorRepository administradorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarPorIdAdministradorExistente() {
        String id = "1";
        Administrador mockAdministrador = new Administrador();
        mockAdministrador.setId(id);
        mockAdministrador.setEmail("admin@example.com");

        when(administradorRepository.findById(id)).thenReturn(Optional.of(mockAdministrador));

        Optional<Administrador> resultado = administradorService.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals("admin@example.com", resultado.get().getEmail());
        verify(administradorRepository, times(1)).findById(id);
    }

    @Test
    public void testBuscarPorIdAdministradorNaoExistente() {
        String id = "99";

        when(administradorRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Administrador> resultado = administradorService.buscarPorId(id);

        assertFalse(resultado.isPresent());
        verify(administradorRepository, times(1)).findById(id);
    }

    @Test
    public void testSalvarAdministrador() {
        Administrador novoAdministrador = new Administrador();
        novoAdministrador.setEmail("novoadmin@example.com");

        when(administradorRepository.save(novoAdministrador)).thenReturn(novoAdministrador);

        Administrador resultado = administradorService.salvar(novoAdministrador);

        assertEquals("novoadmin@example.com", resultado.getEmail());
        verify(administradorRepository, times(1)).save(novoAdministrador);
    }

    @Test
    public void testAtualizarAdministradorExistente() {
        String id = "1";
        Administrador administradorExistente = new Administrador();
        administradorExistente.setId(id);
        administradorExistente.setEmail("admin@example.com");

        Administrador dadosAtualizados = new Administrador();
        dadosAtualizados.setEmail("adminatualizado@example.com");

        when(administradorRepository.findById(id)).thenReturn(Optional.of(administradorExistente));
        when(administradorRepository.save(any(Administrador.class))).thenReturn(administradorExistente);

        Optional<Administrador> resultado = administradorService.atualizar(id, dadosAtualizados);

        assertTrue(resultado.isPresent());
        assertEquals("adminatualizado@example.com", resultado.get().getEmail());
        verify(administradorRepository, times(1)).findById(id);
        verify(administradorRepository, times(1)).save(administradorExistente);
    }

    @Test
    public void testAtualizarAdministradorNaoExistente() {
        String id = "99";
        Administrador dadosAtualizados = new Administrador();
        dadosAtualizados.setEmail("adminatualizado@example.com");

        when(administradorRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            administradorService.atualizar(id, dadosAtualizados);
        });

        assertEquals("Administrador não encontrado com o ID: " + id, exception.getMessage());
        verify(administradorRepository, times(1)).findById(id);
        verify(administradorRepository, never()).save(any(Administrador.class));
    }

    @Test
    public void testDeletarAdministrador() {
        String id = "1";
        doNothing().when(administradorRepository).deleteById(id);

        // Chama o método de deletar
        administradorService.deletar(id);

        // Verifica se o método deleteById foi chamado corretamente
        verify(administradorRepository, times(1)).deleteById(id);
    }
}
