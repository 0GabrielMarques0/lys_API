package br.com.lys.models.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRecord(@NotBlank(message = "")
                            Long id,
                            String nome,
                            String email,
                            String senha,
                            String telefone){
    public Usuario toUsuario(){
        return new Usuario(id(), nome(), email(),telefone(), senha());
    }
}
