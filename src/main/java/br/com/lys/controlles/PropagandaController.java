package br.com.lys.controlles;

import br.com.lys.models.propaganda.PropagandaDetails;
import br.com.lys.models.propaganda.PropagandaRecord;
import br.com.lys.services.PropagandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/propagandas")

public class PropagandaController {

    @Autowired
    private PropagandaService propagandaService;

    @PostMapping
    public ResponseEntity<PropagandaDetails> save(@RequestBody PropagandaRecord propaganda, UriComponentsBuilder builder){
        var propagandaAux = propagandaService.create(propaganda.toPropaganda());
        var uri = builder.path("/propagandas/{id}").buildAndExpand(propagandaAux.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropagandaDetails(propagandaAux));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropagandaDetails> read(@PathVariable Long id){
        var propaganda = propagandaService.read(id);
        if(propaganda == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PropagandaDetails(propaganda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        propagandaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault Pageable page){
        var propagandas = propagandaService.findAll(page);
        if(propagandas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(propagandas.stream().map(PropagandaDetails::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropagandaDetails> update(@PathVariable Long id, @RequestBody PropagandaRecord propaganda){
        var propagandaAux = propagandaService.update(id, propaganda.toPropaganda());
        if(propagandaAux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PropagandaDetails(propagandaAux));
    }
}
