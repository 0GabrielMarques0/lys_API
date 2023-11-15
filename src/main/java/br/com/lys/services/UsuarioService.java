package br.com.lys.services;

import br.com.lys.models.usuario.Usuario;
import br.com.lys.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;
    @Cacheable(value = "usuario", key = "#usuario.id")
    public Usuario create (Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    @Cacheable(value = "usuario", key = "#id")
    public Usuario read (Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    @CacheEvict(value = "usuario", key = "#id")
    public void delete (Long id){
        usuarioRepository.deleteById(id);
    }
    @Cacheable(value = "usuario", key = "#page.pageNumber")
    public Page<Usuario> findAll (Pageable page){
        return usuarioRepository.findAll(page);
    }
    @CachePut(value = "usuario", key = "#id")
    public Usuario update (Long id, Usuario usuario) {
        var usuarioAux = usuarioRepository.findById(id);
        if (usuarioAux.isPresent()) {
            modelMapper.map(usuario, usuarioAux.get());
            return usuarioRepository.save(usuarioAux.get());
        } else {
            throw new RuntimeException("Usuario não encontrado");
        }
    }
}
