package br.com.lys.services;

import br.com.lys.models.propaganda.Propaganda;
import br.com.lys.repositories.PropagandaRepository;
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
public class PropagandaService {

    private final PropagandaRepository propagandaRepository;

    private final ModelMapper modelMapper;
    @Cacheable(value = "propaganda", key = "#propaganda.id")
    public Propaganda create (Propaganda propaganda){
        return propagandaRepository.save(propaganda);
    }
    @Cacheable(value = "propaganda", key = "#id")
    public Propaganda read (Long id){
        return propagandaRepository.findById(id).orElse(null);
    }
    @CacheEvict(value = "propaganda", key = "#id")
    public void delete (Long id){
        propagandaRepository.deleteById(id);
    }
    @Cacheable(value = "propaganda", key = "#page.pageNumber")
    public Page<Propaganda> findAll (Pageable page){
        return propagandaRepository.findAll(page);
    }
    @CachePut(value = "propaganda", key = "#id")
    public Propaganda update (Long id, Propaganda propaganda){
        var propagandaAux = propagandaRepository.findById(id);
        if(propagandaAux.isPresent()){
            modelMapper.map(propaganda, propagandaAux.get());
            return propagandaRepository.save(propagandaAux.get());
        } else {
            throw new RuntimeException("Propaganda não encontrada");
        }
    }
}
