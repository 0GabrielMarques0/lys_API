package br.com.lys.services;

import br.com.lys.models.propaganda.Propaganda;
import br.com.lys.repositories.ParceiroRepository;
import br.com.lys.repositories.PropagandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropagandaService {
    @Autowired
    private PropagandaRepository propagandaRepository;
    public Propaganda create (Propaganda propaganda){
        return propagandaRepository.save(propaganda);
    }
    public Propaganda read (Long id){
        return propagandaRepository.findById(id).orElse(null);
    }
    public void delete (Long id){
        propagandaRepository.deleteById(id);
    }
    public Page<Propaganda> findAll (Pageable page){
        return propagandaRepository.findAll(page);
    }
    public Propaganda update (Long id, Propaganda propaganda){
        var propagandaAux = propagandaRepository.findById(id).orElse(null);
        if(propagandaAux == null){
            return null;
        }
        if ( propaganda.getNome() != null){
            propagandaAux.setNome(propaganda.getNome());
        }
        if ( propaganda.getDescricao() != null){
            propagandaAux.setDescricao(propaganda.getDescricao());
        }
        if ( propaganda.getAnexo() != null){
            propagandaAux.setAnexo(propaganda.getAnexo());
        }
        if ( propaganda.getLink() != null){
            propagandaAux.setLink(propaganda.getLink());
        }
        return propagandaRepository.save(propagandaAux);
    }






}
