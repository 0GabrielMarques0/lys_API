package br.com.lys.models.post;

import br.com.lys.models.voluntario.Voluntario;
import jakarta.validation.constraints.NotBlank;

public record PostRecord(@NotBlank(message = "")
                         Long id,
                         String texto,
                         String titulo,
                         String anexo,
                         String data,
                         Voluntario voluntario) {
    public Post toPost(){
        return new Post(id(), texto(), titulo(), anexo(), data(), voluntario());
    }
}
