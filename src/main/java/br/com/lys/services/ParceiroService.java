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
    public Parceiro update (Long id, Parceiro parceiro){
        var parceiroAux = parceiroRepository.findById(id).orElse(null);
        if(parceiroAux == null){
            return null;
        }
        if ( parceiro.getNome() != null){
            parceiroAux.setNome(parceiro.getNome());
        }
        if ( parceiro.getTelefone() != null){
            parceiroAux.setTelefone(parceiro.getTelefone());
        }
        if ( parceiro.getEmail() != null){
            parceiroAux.setEmail(parceiro.getEmail());
        }
        if ( parceiro.getSenha() != null){
            parceiroAux.setSenha(parceiro.getSenha());
        }
        if ( parceiro.getCnpj() != null){
            parceiroAux.setCnpj(parceiro.getCnpj());
        }
        return parceiroRepository.save(parceiroAux);
    }
}
