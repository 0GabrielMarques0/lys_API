package br.com.lys.controlles;

import br.com.lys.models.voluntario.VoluntarioDetails;
import br.com.lys.models.voluntario.VoluntarioRecord;
import br.com.lys.services.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/voluntarios")
public class VoluntarioController {

    @Autowired
private VoluntarioService voluntarioService;

    @PostMapping
    public ResponseEntity<VoluntarioDetails> save(@RequestBody VoluntarioRecord voluntario, UriComponentsBuilder builder){
        var voluntarioAux = voluntarioService.create(voluntario.toVoluntario());
        var uri = builder.path("/voluntarios/{id}").buildAndExpand(voluntarioAux.getId()).toUri();
        return ResponseEntity.created(uri).body(new VoluntarioDetails(voluntarioAux));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoluntarioDetails> read(@PathVariable Long id){
        var voluntario = voluntarioService.read(id);
        if(voluntario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new VoluntarioDetails(voluntario));
    }

    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault Pageable page){
        var voluntarios = voluntarioService.findAll(page);
        if(voluntarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntarios.stream().map(VoluntarioDetails::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoluntarioDetails> update(@PathVariable Long id, @RequestBody VoluntarioRecord voluntario){
        var voluntarioAux = voluntarioService.update(id, voluntario.toVoluntario());
        if(voluntarioAux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new VoluntarioDetails(voluntarioAux));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        voluntarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
