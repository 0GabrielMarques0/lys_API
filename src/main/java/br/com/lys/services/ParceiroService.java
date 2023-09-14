package br.com.lys.services;

import br.com.lys.models.parceiro.Parceiro;
import br.com.lys.repositories.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ParceiroService {
    @Autowired
    private ParceiroRepository parceiroRepository;

    public Parceiro create (Parceiro parceiro){
        return parceiroRepository.save(parceiro);
    }
    public Parceiro read (Long id){
        return parceiroRepository.findById(id).orElse(null);
    }
    public void delete (Long id){
        parceiroRepository.deleteById(id);
    }
    public List <Parceiro> findAll (){
        return parceiroRepository.findAll();
    }
}
