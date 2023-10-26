package br.com.lys.controlles;

import br.com.lys.models.parceiro.ParceiroDetails;
import br.com.lys.models.parceiro.ParceiroRecord;
import br.com.lys.services.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/parceiros")
public class ParceiroController {
    @Autowired
    private ParceiroService parceiroService;
    @PostMapping
    public ResponseEntity<ParceiroDetails> save(@RequestBody ParceiroRecord parceiro, UriComponentsBuilder builder){
        var parceiroAux = parceiroService.create(parceiro.toParceiro());
        var uri = builder.path("/parceiros/{id}").buildAndExpand(parceiroAux.getId()).toUri();
        return ResponseEntity.created(uri).body(new ParceiroDetails(parceiroAux));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ParceiroDetails> read(@PathVariable Long id){
        var parceiro = parceiroService.read(id);
        if(parceiro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ParceiroDetails(parceiro));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        parceiroService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity findAll(@PageableDefault Pageable page){
        var parceiros = parceiroService.findAll(page);
        if(parceiros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(parceiros.stream().map(ParceiroDetails::new));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ParceiroDetails> update(@PathVariable Long id, @RequestBody ParceiroRecord parceiro){
        var parceiroAux = parceiroService.update(id, parceiro.toParceiro());
        if(parceiroAux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ParceiroDetails(parceiroAux));
    }
}
