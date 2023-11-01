package br.com.lys.services;

import br.com.lys.models.parceiro.Parceiro;
import br.com.lys.repositories.ParceiroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ParceiroService {

    private final ParceiroRepository parceiroRepository;
    private final ModelMapper modelMapper;

    public ParceiroService(ParceiroRepository parceiroRepository, ModelMapper modelMapper) {
        this.parceiroRepository = parceiroRepository;
        this.modelMapper = modelMapper;
    }

    public Parceiro create (Parceiro parceiro){
        return parceiroRepository.save(parceiro);
    }
    public Parceiro read (Long id){
        return parceiroRepository.findById(id).orElse(null);
    }
    public void delete (Long id){
        parceiroRepository.deleteById(id);
    }
    public Page<Parceiro> findAll (Pageable page){
        return parceiroRepository.findAll(page);
    }
    public Parceiro update (Long id, Parceiro parceiro){
        var parceiroAux = parceiroRepository.findById(id).orElse(null);
       if(parceiroAux != null){
           modelMapper.map(parceiro, parceiroAux);
           return parceiroRepository.save(parceiroAux);
       }else {
           throw new RuntimeException("Parceiro não encontrado");
       }

    }
}
