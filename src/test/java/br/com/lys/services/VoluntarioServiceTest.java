package br.com.lys.services;

import br.com.lys.models.voluntario.Voluntario;
import br.com.lys.repositories.VoluntarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VoluntarioServiceTest {

    private VoluntarioService voluntarioService;
    private VoluntarioRepository mockedVoluntarioRepository;

    @BeforeEach
    void configurar() {
        mockedVoluntarioRepository = mock(VoluntarioRepository.class);
        voluntarioService = new VoluntarioService(mockedVoluntarioRepository);
    }

    @Test
    void criarDeveSalvarERetornarVoluntario() {
        Voluntario voluntario = new Voluntario();
        when(mockedVoluntarioRepository.save(voluntario)).thenReturn(voluntario);

        Voluntario resultado = voluntarioService.create(voluntario);

        assertEquals(voluntario, resultado);
    }

    @Test
    void lerDeveRetornarVoluntarioSeExistir() {
        Voluntario voluntario = new Voluntario();
        when(mockedVoluntarioRepository.findById(1L)).thenReturn(Optional.of(voluntario));

        Voluntario resultado = voluntarioService.read(1L);

        assertEquals(voluntario, resultado);
    }

    @Test
    void lerDeveRetornarNuloSeNaoExistir() {
        when(mockedVoluntarioRepository.findById(1L)).thenReturn(Optional.empty());

        Voluntario resultado = voluntarioService.read(1L);

        assertNull(resultado);
    }

    @Test
    void deletarDeveChamarDeleteById() {
        voluntarioService.delete(1L);
        verify(mockedVoluntarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void listarTodosDeveRetornarPaginaDeVoluntarios() {
        Page<Voluntario> pagina = new PageImpl<>(List.of(new Voluntario()));
        when(mockedVoluntarioRepository.findAll(any(Pageable.class))).thenReturn(pagina);

        Page<Voluntario> resultado = voluntarioService.findAll(PageRequest.of(0, 10));

        assertEquals(pagina, resultado);
    }

    @Test
    void atualizarDeveAtualizarERetornarVoluntario() {
        Voluntario voluntarioExistente = new Voluntario();
        Voluntario novoVoluntario = new Voluntario();
        novoVoluntario.setNome("Nome Novo");
        novoVoluntario.setEmail("novo@email.com");
        novoVoluntario.setSenha("senha123");
        novoVoluntario.setTelefone("11999999999");
        when(mockedVoluntarioRepository.findById(1L)).thenReturn(Optional.of(voluntarioExistente));
        when(mockedVoluntarioRepository.save(voluntarioExistente)).thenReturn(voluntarioExistente);

        Voluntario resultado = voluntarioService.update(1L, novoVoluntario);

        assertEquals("Nome Novo", resultado.getNome());
        assertEquals("novo@email.com", resultado.getEmail());
        assertEquals("senha123", resultado.getSenha());
        assertEquals("11999999999", resultado.getTelefone());
    }

    @Test
    void atualizarDeveRetornarNuloSeVoluntarioNaoExistir() {
        Voluntario novoVoluntario = new Voluntario();
        when(mockedVoluntarioRepository.findById(1L)).thenReturn(Optional.empty());

        Voluntario resultado = voluntarioService.update(1L, novoVoluntario);

        assertNull(resultado);
    }
}
