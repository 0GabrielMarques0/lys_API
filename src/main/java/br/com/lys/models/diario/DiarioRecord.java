package br.com.lys.models.diario;

import br.com.lys.models.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;

public record DiarioRecord(@NotBlank(message = "")
                           Long id,
                           String texto,
                           String data,
                           Usuario usuario) {
    public Diario toDiario(){
        return new Diario(id(), texto(), data(), usuario());
    }
}
