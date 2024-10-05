package com.example.hostly_api.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import com.example.hostly_api.Model.Administrador;
import com.example.hostly_api.Repository.AdministradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;

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
        String idAdministrador = "1";
        Administrador mockAdministrador = new Administrador();
        mockAdministrador.setId_administrador(idAdministrador);
        mockAdministrador.setEmail("admin@example.com");

        when(administradorRepository.findById(idAdministrador)).thenReturn(Optional.of(mockAdministrador));

        Optional<Administrador> resultado = administradorService.buscarPorId(idAdministrador);

        assertTrue(resultado.isPresent());
        assertEquals("admin@example.com", resultado.get().getEmail());
        verify(administradorRepository, times(1)).findById(idAdministrador);
    }

    @Test
    public void testBuscarPorIdAdministradorNaoExistente() {
        String idAdministrador = "99";

        when(administradorRepository.findById(idAdministrador)).thenReturn(Optional.empty());

        Optional<Administrador> resultado = administradorService.buscarPorId(idAdministrador);

        assertFalse(resultado.isPresent());
        verify(administradorRepository, times(1)).findById(idAdministrador);
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
        String idAdministrador = "1";
        Administrador administradorExistente = new Administrador();
        administradorExistente.setId_administrador(idAdministrador);
        administradorExistente.setEmail("admin@example.com");

        Administrador dadosAtualizados = new Administrador();
        dadosAtualizados.setEmail("adminatualizado@example.com");

        when(administradorRepository.findById(idAdministrador)).thenReturn(Optional.of(administradorExistente));
        when(administradorRepository.save(any(Administrador.class))).thenReturn(administradorExistente);

        Optional<Administrador> resultado = administradorService.atualizar(idAdministrador, dadosAtualizados);

        assertTrue(resultado.isPresent());
        assertEquals("adminatualizado@example.com", resultado.get().getEmail());
        verify(administradorRepository, times(1)).findById(idAdministrador);
        verify(administradorRepository, times(1)).save(administradorExistente);
    }

    @Test
    public void testAtualizarAdministradorNaoExistente() {
        String idAdministrador = "99";
        Administrador dadosAtualizados = new Administrador();
        dadosAtualizados.setEmail("adminatualizado@example.com");

        when(administradorRepository.findById(idAdministrador)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            administradorService.atualizar(idAdministrador, dadosAtualizados);
        });

        assertEquals("Administrador não encontrado com o ID: " + idAdministrador, exception.getMessage());
        verify(administradorRepository, times(1)).findById(idAdministrador);
        verify(administradorRepository, never()).save(any(Administrador.class));
    }

    @Test
    public void testDeletarAdministrador() {
        String idAdministrador = "1";
        doNothing().when(administradorRepository).deleteById(idAdministrador);

        // Chama o método de deletar
        administradorService.deletar(idAdministrador);

        // Verifica se o método deleteById foi chamado corretamente
        verify(administradorRepository, times(1)).deleteById(idAdministrador);
    }
}
