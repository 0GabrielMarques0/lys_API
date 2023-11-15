package br.com.lys.services;
import br.com.lys.models.diario.Diario;
import br.com.lys.repositories.DiarioRepository;
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
public class DiarioService {

    private final DiarioRepository diarioRepository;


    private final ModelMapper modelMapper;


    @Cacheable(value = "diario", key = "#diario.id")
    public Diario create (Diario diario){
        return diarioRepository.save(diario);
    }

    @Cacheable(value = "diario", key = "#id")
    public Diario read (Long id){
        return diarioRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "diario", key = "#id")
    public void delete (Long id){
        diarioRepository.deleteById(id);
    }

    @Cacheable(value = "diario", key = "#page.pageNumber")
    public Page<Diario> findAll (Pageable page){
        return diarioRepository.findAll(page);
    }

    @CachePut(value = "diario", key = "#id")
    public Diario update (Long id, Diario diario){
        var diarioAux = diarioRepository.findById(id);
        if(diarioAux.isPresent()){
            modelMapper.map(diario, diarioAux.get());
            return diarioRepository.save(diarioAux.get());
        }else {
            throw new RuntimeException("Diario não encontrado");
        }
    }
}
