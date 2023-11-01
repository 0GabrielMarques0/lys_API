package br.com.lys.services;

import br.com.lys.models.propaganda.Propaganda;
import br.com.lys.repositories.PropagandaRepository;
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

class PropagandaServiceTest {

    private PropagandaService propagandaService;
    private PropagandaRepository mockedPropagandaRepository;

    @BeforeEach
    void setUp() {
        mockedPropagandaRepository = mock(PropagandaRepository.class);
        propagandaService = new PropagandaService(mockedPropagandaRepository);
    }

    @Test
    void createShouldSaveAndReturnPropaganda() {
        Propaganda propaganda = new Propaganda();
        when(mockedPropagandaRepository.save(propaganda)).thenReturn(propaganda);

        Propaganda result = propagandaService.create(propaganda);

        assertEquals(propaganda, result);
    }

    @Test
    void readShouldReturnPropagandaIfExists() {
        Propaganda propaganda = new Propaganda();
        when(mockedPropagandaRepository.findById(1L)).thenReturn(Optional.of(propaganda));

        Propaganda result = propagandaService.read(1L);

        assertEquals(propaganda, result);
    }

    @Test
    void readShouldReturnNullIfNotExists() {
        when(mockedPropagandaRepository.findById(1L)).thenReturn(Optional.empty());

        Propaganda result = propagandaService.read(1L);

        assertNull(result);
    }

    @Test
    void deleteShouldCallDeleteById() {
        propagandaService.delete(1L);
        verify(mockedPropagandaRepository, times(1)).deleteById(1L);
    }

    @Test
    void findAllShouldReturnPageOfPropagandas() {
        Page<Propaganda> page = new PageImpl<>(List.of(new Propaganda()));
        when(mockedPropagandaRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Propaganda> result = propagandaService.findAll(PageRequest.of(0, 10));

        assertEquals(page, result);
    }

    @Test
    void updateShouldUpdateAndReturnPropaganda() {
        Propaganda existingPropaganda = new Propaganda();
        Propaganda newPropaganda = new Propaganda();
        newPropaganda.setNome("New Name");
        newPropaganda.setDescricao("New Description");
        newPropaganda.setAnexo("New Anexo");
        newPropaganda.setLink("New Link");
        when(mockedPropagandaRepository.findById(1L)).thenReturn(Optional.of(existingPropaganda));
        when(mockedPropagandaRepository.save(existingPropaganda)).thenReturn(existingPropaganda);

        Propaganda result = propagandaService.update(1L, newPropaganda);

        assertEquals("New Name", result.getNome());
        assertEquals("New Description", result.getDescricao());
        assertEquals("New Anexo", result.getAnexo());
        assertEquals("New Link", result.getLink());
    }

    @Test
    void updateShouldReturnNullIfPropagandaDoesNotExist() {
        Propaganda newPropaganda = new Propaganda();
        when(mockedPropagandaRepository.findById(1L)).thenReturn(Optional.empty());

        Propaganda result = propagandaService.update(1L, newPropaganda);

        assertNull(result);
    }
}
