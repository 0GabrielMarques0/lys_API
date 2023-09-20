package br.com.lys.models.parceiro;

import jakarta.validation.constraints.NotBlank;

public record ParceiroRecord(@NotBlank(message = "")
                            Long id,
                            String nome,
                            String telefone,
                            String email,
                            String endereco,
                            String cidade,
                            String estado,
                            String cep,
                            String cnpj) {
    public Parceiro toParceiro(){
        return new Parceiro(id(), nome(), telefone(), email(), endereco(), cidade(), estado(), cep(), cnpj());
    }
}
