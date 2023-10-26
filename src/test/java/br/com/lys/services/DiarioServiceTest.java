package br.com.lys.services;

import br.com.lys.models.diario.Diario;
import br.com.lys.repositories.DiarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DiarioServiceTest {

    @InjectMocks
    private DiarioService diarioService;

    @Mock
    private DiarioRepository diarioRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        Diario diario = new Diario();
        when(diarioRepository.save(any(Diario.class))).thenReturn(diario);
        diarioService.create(diario);
        verify(diarioRepository).save(diario);
    }

    @Test
    public void testRead() {
        Diario diario = new Diario();
        when(diarioRepository.findById(any(Long.class))).thenReturn(Optional.of(diario));
        diarioService.read(1L);
        verify(diarioRepository).findById(1L);
    }

    @Test
    public void testDelete() {
        diarioService.delete(1L);
        verify(diarioRepository).deleteById(1L);
    }

    @Test
    public void testFindAll() {
        Page<Diario> page = new PageImpl<>(List.of(new Diario()));
        when(diarioRepository.findAll(any(Pageable.class))).thenReturn(page);
        diarioService.findAll(Pageable.unpaged());
        verify(diarioRepository).findAll(Pageable.unpaged());
    }

    @Test
    public void testUpdate() {
        Diario oldDiario = new Diario();
        oldDiario.setTexto("Old Text");

        Diario newDiario = new Diario();
        newDiario.setTexto("New Text");

        when(diarioRepository.findById(any(Long.class))).thenReturn(Optional.of(oldDiario));
        when(diarioRepository.save(any(Diario.class))).thenReturn(newDiario);

        diarioService.update(1L, newDiario);
        verify(diarioRepository).save(oldDiario);
        assert oldDiario.getTexto().equals("New Text");
    }
}
