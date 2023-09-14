package br.com.lys.services;
import br.com.lys.models.diario.Diario;
import br.com.lys.repositories.DiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class DiarioService {
    @Autowired
    private DiarioRepository diarioRepository;

    public Diario create (Diario diario){
        return diarioRepository.save(diario);
    }
    public Diario read (Long id){
        return diarioRepository.findById(id).orElse(null);
    }
    public void delete (Long id){
        diarioRepository.deleteById(id);
    }
    public List <Diario>  findAll (){
        return diarioRepository.findAll();
    }
    public Diario update (Long id, Diario diario){
        var diarioAux = diarioRepository.findById(id).orElse(null);
        if(diarioAux == null){
            return null;
        }
        if ( diario.getTexto() != null){
            diarioAux.setTexto(diario.getTexto());
        }
        return diarioRepository.save(diarioAux);
    }
}
