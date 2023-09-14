package br.com.lys.services;

import br.com.lys.models.propaganda.Propaganda;
import br.com.lys.repositories.ParceiroRepository;
import br.com.lys.repositories.PropagandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List <Propaganda> findAll (){
        return propagandaRepository.findAll();
    }






}
