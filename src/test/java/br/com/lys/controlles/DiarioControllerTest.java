package br.com.lys.controlles;

import br.com.lys.models.diario.DiarioDetails;
import br.com.lys.models.diario.DiarioRecord;
import br.com.lys.models.diario.Diario;
import br.com.lys.models.usuario.Usuario;
import br.com.lys.services.DiarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DiarioControllerTest {

    @Mock
    private DiarioService diarioService;

    @InjectMocks
    private DiarioController diarioController;

    @BeforeEach
    void setUp() {}

    @Test
    void testSave() {

        DiarioRecord diarioRecord = new DiarioRecord(1L, "texto", "26/01/2022", new Usuario());

        Diario diario = new Diario(1L, "texto", "26/01/2022", new Usuario());

        when(diarioService.create(any(Diario.class))).thenReturn(diario);

        ResponseEntity<?> response = diarioController.save(diarioRecord, UriComponentsBuilder.newInstance());

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
    @Test
    void testReadDiarioFound() {

        Diario diario = new Diario(1L, "texto", "26/01/2022", new Usuario());

        when(diarioService.read(anyLong())).thenReturn(diario);

        ResponseEntity<DiarioDetails> response = diarioController.read(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testReadDiarioNotFound() {

        when(diarioService.read(anyLong())).thenReturn(null);

        ResponseEntity<DiarioDetails> response = diarioController.read(1L);

        assertEquals(404, response.getStatusCodeValue());
    }
}

