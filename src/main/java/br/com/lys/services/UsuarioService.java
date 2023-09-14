package br.com.lys.services;

import br.com.lys.models.usuario.Usuario;
import br.com.lys.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
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
    public List <Usuario> findAll (){
        return usuarioRepository.findAll();
    }
}
