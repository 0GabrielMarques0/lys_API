package br.com.lys.controlles;
import br.com.lys.models.diario.DiarioDetails;
import br.com.lys.models.diario.DiarioRecord;
import br.com.lys.services.DiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/diarios")
public class DiarioController {

    @Autowired
    private DiarioService diarioService;

    @PostMapping
    public ResponseEntity<DiarioDetails> save(@RequestBody DiarioRecord diario, UriComponentsBuilder builder){
        var diarioAux = diarioService.create(diario.toDiario());
        var uri = builder.path("/diarios/{id}").buildAndExpand(diarioAux.getId()).toUri();
        return ResponseEntity.created(uri).body(new DiarioDetails(diarioAux));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiarioDetails> read(@PathVariable Long id){
        var diario = diarioService.read(id);
        if(diario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DiarioDetails(diario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        diarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault Pageable page){
        var diarios = diarioService.findAll(page);
        if(diarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(diarios.stream().map(DiarioDetails::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiarioDetails> update(@PathVariable Long id, @RequestBody DiarioRecord diario){
        var diarioAux = diarioService.update(id, diario.toDiario());
        if(diarioAux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DiarioDetails(diarioAux));
    }


}
