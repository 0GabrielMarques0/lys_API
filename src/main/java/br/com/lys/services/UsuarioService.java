package br.com.lys.services;

import br.com.lys.models.usuario.Usuario;
import br.com.lys.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public Usuario create (Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public Usuario read (Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public void delete (Long id){
        usuarioRepository.deleteById(id);
    }
    public Page<Usuario> findAll (Pageable page){
        return usuarioRepository.findAll(page);
    }
    public Usuario update (Long id, Usuario usuario){
        var usuarioAux = usuarioRepository.findById(id).orElse(null);
        if(usuarioAux == null){
            return null;
        }
        if ( usuario.getNome() != null){
            usuarioAux.setNome(usuario.getNome());
        }
        if ( usuario.getEmail() != null){
            usuarioAux.setEmail(usuario.getEmail());
        }
        if ( usuario.getSenha() != null){
            usuarioAux.setSenha(usuario.getSenha());
        }
        return usuarioRepository.save(usuarioAux);
    }
}
