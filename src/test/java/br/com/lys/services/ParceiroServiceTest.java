package br.com.lys.services;

import br.com.lys.models.parceiro.Parceiro;
import br.com.lys.repositories.ParceiroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ParceiroServiceTest {

    private ParceiroService parceiroService;
    private ParceiroRepository mockedParceiroRepository;
    private ModelMapper mockedModelMapper;

    @BeforeEach
    void setUp() {
        mockedParceiroRepository = mock(ParceiroRepository.class);
        mockedModelMapper = mock(ModelMapper.class);
        parceiroService = new ParceiroService(mockedParceiroRepository, mockedModelMapper);
    }

    @Test
    void createShouldSaveAndReturnParceiro() {
        Parceiro parceiro = new Parceiro();
        when(mockedParceiroRepository.save(parceiro)).thenReturn(parceiro);

        Parceiro result = parceiroService.create(parceiro);

        assertEquals(parceiro, result);
    }

    @Test
    void readShouldReturnParceiroIfExists() {
        Parceiro parceiro = new Parceiro();
        when(mockedParceiroRepository.findById(1L)).thenReturn(Optional.of(parceiro));

        Parceiro result = parceiroService.read(1L);

        assertEquals(parceiro, result);
    }

    @Test
    void readShouldReturnNullIfNotExists() {
        when(mockedParceiroRepository.findById(1L)).thenReturn(Optional.empty());

        Parceiro result = parceiroService.read(1L);

        assertNull(result);
    }

    @Test
    void deleteShouldCallDeleteById() {
        parceiroService.delete(1L);
        verify(mockedParceiroRepository, times(1)).deleteById(1L);
    }

    @Test
    void findAllShouldReturnPageOfParceiros() {
        Page<Parceiro> page = new PageImpl<>(List.of(new Parceiro()));
        when(mockedParceiroRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Parceiro> result = parceiroService.findAll(PageRequest.of(0, 10));

        assertEquals(page, result);
    }

    @Test
    void updateShouldUpdateAndReturnParceiro() {
        Parceiro existingParceiro = new Parceiro();
        Parceiro newParceiro = new Parceiro();
        when(mockedParceiroRepository.findById(1L)).thenReturn(Optional.of(existingParceiro));
        when(mockedParceiroRepository.save(existingParceiro)).thenReturn(existingParceiro);

        Parceiro result = parceiroService.update(1L, newParceiro);

        verify(mockedModelMapper).map(newParceiro, existingParceiro);
        assertEquals(existingParceiro, result);
    }

    @Test
    void updateShouldThrowRuntimeExceptionIfNotExists() {
        Parceiro newParceiro = new Parceiro();
        when(mockedParceiroRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> parceiroService.update(1L, newParceiro));
    }
}
