package br.com.lys.services;

import br.com.lys.models.voluntario.Voluntario;
import br.com.lys.repositories.VoluntarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    private final ModelMapper modelMapper;

    public Voluntario create (Voluntario voluntario){
        return voluntarioRepository.save(voluntario);
    }
    public Voluntario read (Long id){
        return voluntarioRepository.findById(id).orElse(null);
    }
    public void delete (Long id){
        voluntarioRepository.deleteById(id);
    }
    public Page<Voluntario> findAll (Pageable page){
        return voluntarioRepository.findAll(page);
    }
    public Voluntario update (Long id, Voluntario voluntario) {
        var voluntarioAux = voluntarioRepository.findById(id);
        if (voluntarioAux.isPresent()) {
            modelMapper.map(voluntario, voluntarioAux.get());
            return voluntarioRepository.save(voluntarioAux.get());
        } else {
            throw new RuntimeException("Voluntario não encontrado");
        }
    }
}
