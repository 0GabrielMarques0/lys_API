package br.com.lys.services;

import br.com.lys.models.usuario.Usuario;
import br.com.lys.models.voluntario.Voluntario;
import br.com.lys.repositories.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoluntarioService {
    @Autowired
    private VoluntarioRepository voluntarioRepository;
    public Voluntario create (Voluntario voluntario){
        return voluntarioRepository.save(voluntario);
    }
    public Voluntario read (Long id){
        return voluntarioRepository.findById(id).orElse(null);
    }
    public void delete (Long id){
        voluntarioRepository.deleteById(id);
    }
    public List <Voluntario> findAll (){
        return voluntarioRepository.findAll();
    }



}
