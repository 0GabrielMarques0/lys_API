package br.com.lys.services;
import br.com.lys.models.parceiro.Parceiro;
import br.com.lys.repositories.ParceiroRepository;
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
public class ParceiroServiceTest {

    @Mock
    private ParceiroRepository parceiroRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ParceiroService parceiroService;

    private Parceiro parceiro;

    @BeforeEach
    void setUp() {
        parceiro = new Parceiro();
    }

    @Test
    void createTest() {
        when(parceiroRepository.save(any(Parceiro.class))).thenReturn(parceiro);
        Parceiro result = parceiroService.create(parceiro);
        assertNotNull(result);
        verify(parceiroRepository).save(parceiro);
    }

    @Test
    void readTest() {
        Long id = 1L;
        when(parceiroRepository.findById(id)).thenReturn(Optional.of(parceiro));
        Parceiro result = parceiroService.read(id);
        assertNotNull(result);
        verify(parceiroRepository).findById(id);
    }

    @Test
    void deleteTest() {
        Long id = 1L;
        doNothing().when(parceiroRepository).deleteById(id);
        parceiroService.delete(id);
        verify(parceiroRepository).deleteById(id);
    }

    @Test
    void findAllTest() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Parceiro> page = new PageImpl<>(Collections.singletonList(parceiro));
        when(parceiroRepository.findAll(pageRequest)).thenReturn(page);
        Page<Parceiro> result = parceiroService.findAll(pageRequest);
        assertNotNull(result);
        verify(parceiroRepository).findAll(pageRequest);
    }

    @Test
    void updateTest() {
        Long id = 1L;
        when(parceiroRepository.findById(id)).thenReturn(Optional.of(parceiro));
        when(parceiroRepository.save(any(Parceiro.class))).thenReturn(parceiro);
        Parceiro updatedParceiro = new Parceiro();
        Parceiro result = parceiroService.update(id, updatedParceiro);
        assertNotNull(result);
        verify(parceiroRepository).findById(id);
        verify(parceiroRepository).save(parceiro);
    }
}