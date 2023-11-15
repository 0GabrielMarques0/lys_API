package br.com.lys.services;

import br.com.lys.models.usuario.Usuario;
import br.com.lys.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UsuarioService usuarioService;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
    }

    @Test
    void testCreate() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario created = usuarioService.create(usuario);
        assertNotNull(created);
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void testRead() {
        Long id = 1L;
        Optional<Usuario> optionalUsuario = Optional.of(new Usuario());
        when(usuarioRepository.findById(id)).thenReturn(optionalUsuario);
        Usuario found = usuarioService.read(id);
        assertNotNull(found);
        verify(usuarioRepository).findById(id);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(usuarioRepository).deleteById(id);
        usuarioService.delete(id);
        verify(usuarioRepository).deleteById(id);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Usuario> page = new PageImpl<>(Collections.singletonList(usuario));
        when(usuarioRepository.findAll(pageable)).thenReturn(page);
        Page<Usuario> result = usuarioService.findAll(pageable);
        assertNotNull(result);
        verify(usuarioRepository).findAll(pageable);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        Optional<Usuario> optionalUsuario = Optional.of(new Usuario());
        when(usuarioRepository.findById(id)).thenReturn(optionalUsuario);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario updated = usuarioService.update(id, usuario);
        assertNotNull(updated);
        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void testUpdateNotFound() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> usuarioService.update(id, usuario));
        verify(usuarioRepository).findById(id);
    }
}
