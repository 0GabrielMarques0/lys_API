package br.com.lys.services;

import br.com.lys.models.parceiro.Parceiro;
import br.com.lys.repositories.ParceiroRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ParceiroService {

    private final ParceiroRepository parceiroRepository;
    private final ModelMapper modelMapper;


    @Cacheable(value = "parceiro", key = "#parceiro.id")
    public Parceiro create (Parceiro parceiro){
        return parceiroRepository.save(parceiro);
    }
    @Cacheable(value = "parceiro", key = "#id")
    public Parceiro read (Long id){
        return parceiroRepository.findById(id).orElse(null);
    }
    @CacheEvict(value = "parceiro", key = "#id")
    public void delete (Long id){
        parceiroRepository.deleteById(id);
    }
    @Cacheable(value = "parceiro", key = "#page.pageNumber")
    public Page<Parceiro> findAll (Pageable page){
        return parceiroRepository.findAll(page);
    }
    @CachePut(value = "parceiro", key = "#id")
    public Parceiro update (Long id, Parceiro parceiro){
        var parceiroAux = parceiroRepository.findById(id);
       if(parceiroAux.isPresent()){
           modelMapper.map(parceiro, parceiroAux.get());
           return parceiroRepository.save(parceiroAux.get());
       }else {
           throw new RuntimeException("Parceiro não encontrado");
       }

    }
}
