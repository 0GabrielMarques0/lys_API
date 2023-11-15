package br.com.lys.services;

import br.com.lys.models.diario.Diario;
import br.com.lys.repositories.DiarioRepository;
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
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DiarioServiceTest {

    @Mock
    private DiarioRepository diarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DiarioService diarioService;

    private Diario diario;

    @BeforeEach
    void setUp() {
        diario = new Diario();
    }

    @Test
    void createTest() {
        when(diarioRepository.save(any(Diario.class))).thenReturn(diario);
        Diario result = diarioService.create(diario);
        assertNotNull(result);
        verify(diarioRepository).save(diario);
    }

    @Test
    void readTest() {
        Long id = 1L;
        when(diarioRepository.findById(id)).thenReturn(Optional.of(diario));
        Diario result = diarioService.read(id);
        assertNotNull(result);
        verify(diarioRepository).findById(id);
    }

    @Test
    void deleteTest() {
        Long id = 1L;
        doNothing().when(diarioRepository).deleteById(id);
        diarioService.delete(id);
        verify(diarioRepository).deleteById(id);
    }

    @Test
    void findAllTest() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Diario> page = new PageImpl<>(Collections.singletonList(diario));
        when(diarioRepository.findAll(pageRequest)).thenReturn(page);
        Page<Diario> result = diarioService.findAll(pageRequest);
        assertNotNull(result);
        verify(diarioRepository).findAll(pageRequest);
    }

    @Test
    void updateTest() {
        Long id = 1L;
        when(diarioRepository.findById(id)).thenReturn(Optional.of(diario));
        when(diarioRepository.save(any(Diario.class))).thenReturn(diario);
        Diario updatedDiario = new Diario();
        Diario result = diarioService.update(id, updatedDiario);
        assertNotNull(result);
        verify(diarioRepository).findById(id);
        verify(diarioRepository).save(diario);
    }
}