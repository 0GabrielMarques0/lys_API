package br.com.lys.services;
import br.com.lys.models.voluntario.Voluntario;
import br.com.lys.repositories.VoluntarioRepository;
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
public class VoluntarioServiceTest {

    @Mock
    private VoluntarioRepository voluntarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private VoluntarioService voluntarioService;
    private Voluntario voluntario;

    @BeforeEach
    void setUp() {
        voluntario = new Voluntario();
    }

    @Test
    void testCreate() {
        Voluntario voluntario = new Voluntario();
        when(voluntarioRepository.save(any(Voluntario.class))).thenReturn(voluntario);
        Voluntario created = voluntarioService.create(voluntario);
        assertNotNull(created);
        verify(voluntarioRepository).save(any(Voluntario.class));
    }

    @Test
    void testRead() {
        Long id = 1L;
        Optional<Voluntario> optionalVoluntario = Optional.of(new Voluntario());
        when(voluntarioRepository.findById(id)).thenReturn(optionalVoluntario);
        Voluntario found = voluntarioService.read(id);
        assertNotNull(found);
        verify(voluntarioRepository).findById(id);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(voluntarioRepository).deleteById(id);
        voluntarioService.delete(id);
        verify(voluntarioRepository).deleteById(id);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Voluntario> page = new PageImpl<>(Collections.singletonList(voluntario));
        when(voluntarioRepository.findAll(pageable)).thenReturn(page);
        Page<Voluntario> result = voluntarioService.findAll(pageable);
        assertNotNull(result);
        verify(voluntarioRepository).findAll(pageable);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        Voluntario voluntario = new Voluntario();
        Optional<Voluntario> optionalVoluntario = Optional.of(new Voluntario());
        when(voluntarioRepository.findById(id)).thenReturn(optionalVoluntario);
        when(voluntarioRepository.save(any(Voluntario.class))).thenReturn(voluntario);
        Voluntario updated = voluntarioService.update(id, voluntario);
        assertNotNull(updated);
        verify(voluntarioRepository).findById(id);
        verify(voluntarioRepository).save(any(Voluntario.class));
    }

    @Test
    void testUpdateNotFound() {
        Long id = 1L;
        Voluntario voluntario = new Voluntario();
        when(voluntarioRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> voluntarioService.update(id, voluntario));
        verify(voluntarioRepository).findById(id);
    }
}
