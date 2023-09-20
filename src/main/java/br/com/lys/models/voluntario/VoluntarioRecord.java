package br.com.lys.models.voluntario;

import jakarta.validation.constraints.NotBlank;


public record VoluntarioRecord(@NotBlank(message = "")
                               Long id,
                               String nome,
                               String email,
                               String telefone,
                               String senha) {
    public Voluntario toVoluntario(){
        return new Voluntario(id(), nome(), email(), telefone(), senha());
    }
}
