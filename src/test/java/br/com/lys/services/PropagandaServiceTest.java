package br.com.lys.services;
import br.com.lys.models.propaganda.Propaganda;
import br.com.lys.repositories.PropagandaRepository;
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
public class PropagandaServiceTest {

    @Mock
    private PropagandaRepository propagandaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PropagandaService propagandaService;
    private Propaganda propaganda;

    @BeforeEach
    void setUp() {
        propaganda = new Propaganda();
    }

    @Test
    void testCreate() {
        Propaganda propaganda = new Propaganda();
        when(propagandaRepository.save(any(Propaganda.class))).thenReturn(propaganda);
        Propaganda created = propagandaService.create(propaganda);
        assertNotNull(created);
        verify(propagandaRepository).save(any(Propaganda.class));
    }

    @Test
    void testRead() {
        Long id = 1L;
        Optional<Propaganda> optionalPropaganda = Optional.of(new Propaganda());
        when(propagandaRepository.findById(id)).thenReturn(optionalPropaganda);
        Propaganda found = propagandaService.read(id);
        assertNotNull(found);
        verify(propagandaRepository).findById(id);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(propagandaRepository).deleteById(id);
        propagandaService.delete(id);
        verify(propagandaRepository).deleteById(id);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Propaganda> page = new PageImpl<>(Collections.singletonList(propaganda));
        when(propagandaRepository.findAll(pageable)).thenReturn(page);
        Page<Propaganda> result = propagandaService.findAll(pageable);
        assertNotNull(result);
        verify(propagandaRepository).findAll(pageable);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        Propaganda propaganda = new Propaganda();
        Optional<Propaganda> optionalPropaganda = Optional.of(new Propaganda());
        when(propagandaRepository.findById(id)).thenReturn(optionalPropaganda);
        when(propagandaRepository.save(any(Propaganda.class))).thenReturn(propaganda);
        Propaganda updated = propagandaService.update(id, propaganda);
        assertNotNull(updated);
        verify(propagandaRepository).findById(id);
        verify(propagandaRepository).save(any(Propaganda.class));
    }

    @Test
    void testUpdateNotFound() {
        Long id = 1L;
        Propaganda propaganda = new Propaganda();
        when(propagandaRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> propagandaService.update(id, propaganda));
        verify(propagandaRepository).findById(id);
    }
}
