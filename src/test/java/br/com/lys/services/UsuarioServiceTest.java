package br.com.lys.services;

import br.com.lys.models.usuario.Usuario;
import br.com.lys.repositories.UsuarioRepository;
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

class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private UsuarioRepository mockedUsuarioRepository;

    @BeforeEach
    void configurar() {
        mockedUsuarioRepository = mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(mockedUsuarioRepository);
    }

    @Test
    void criarDeveSalvarERetornarUsuario() {
        Usuario usuario = new Usuario();
        when(mockedUsuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.create(usuario);

        assertEquals(usuario, resultado);
    }

    @Test
    void lerDeveRetornarUsuarioSeExistir() {
        Usuario usuario = new Usuario();
        when(mockedUsuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.read(1L);

        assertEquals(usuario, resultado);
    }

    @Test
    void lerDeveRetornarNuloSeNaoExistir() {
        when(mockedUsuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Usuario resultado = usuarioService.read(1L);

        assertNull(resultado);
    }

    @Test
    void deletarDeveChamarDeleteById() {
        usuarioService.delete(1L);
        verify(mockedUsuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void listarTodosDeveRetornarPaginaDeUsuarios() {
        Page<Usuario> pagina = new PageImpl<>(List.of(new Usuario()));
        when(mockedUsuarioRepository.findAll(any(Pageable.class))).thenReturn(pagina);

        Page<Usuario> resultado = usuarioService.findAll(PageRequest.of(0, 10));

        assertEquals(pagina, resultado);
    }

    @Test
    void atualizarDeveAtualizarERetornarUsuario() {
        Usuario usuarioExistente = new Usuario();
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Nome Novo");
        novoUsuario.setEmail("novo@email.com");
        novoUsuario.setSenha("senha123");
        when(mockedUsuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(mockedUsuarioRepository.save(usuarioExistente)).thenReturn(usuarioExistente);

        Usuario resultado = usuarioService.update(1L, novoUsuario);

        assertEquals("Nome Novo", resultado.getNome());
        assertEquals("novo@email.com", resultado.getEmail());
        assertEquals("senha123", resultado.getSenha());
    }

    @Test
    void atualizarDeveRetornarNuloSeUsuarioNaoExistir() {
        Usuario novoUsuario = new Usuario();
        when(mockedUsuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Usuario resultado = usuarioService.update(1L, novoUsuario);

        assertNull(resultado);
    }
}
