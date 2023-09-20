package br.com.lys.controlles;

import br.com.lys.models.usuario.UsuarioDetails;
import br.com.lys.models.usuario.UsuarioRecord;
import br.com.lys.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;

        @PostMapping
        public ResponseEntity<UsuarioDetails> save(@RequestBody UsuarioRecord usuario, UriComponentsBuilder builder){
            var usuarioAux = usuarioService.create(usuario.toUsuario());
            var uri = builder.path("/usuarios/{id}").buildAndExpand(usuarioAux.getId()).toUri();
            return ResponseEntity.created(uri).body(new UsuarioDetails(usuarioAux));
        }

        @GetMapping("/{id}")
        public ResponseEntity<UsuarioDetails> read(@PathVariable Long id){
            var usuario = usuarioService.read(id);
            if(usuario == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new UsuarioDetails(usuario));
        }

        @GetMapping
        public ResponseEntity<?> findAll(){
            var usuarios = usuarioService.findAll();
            if(usuarios.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(usuarios.stream().map(UsuarioDetails::new));
        }

        @PutMapping("/{id}")
        public ResponseEntity<UsuarioDetails> update(@PathVariable Long id, @RequestBody UsuarioRecord usuario){
            var usuarioAux = usuarioService.update(id, usuario.toUsuario());
            if(usuarioAux == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new UsuarioDetails(usuarioAux));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id){
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }
}
