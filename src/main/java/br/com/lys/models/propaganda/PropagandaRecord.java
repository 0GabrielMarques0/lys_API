package br.com.lys.models.propaganda;

import br.com.lys.models.parceiro.Parceiro;
import jakarta.validation.constraints.NotBlank;

public record PropagandaRecord (@NotBlank(message = "")
                                Long id,
                                String nome,
                                String anexo,
                                String link,
                                String descricao,
                                String data,
                                Parceiro parceiro) {
    public Propaganda toPropaganda() {
        return new Propaganda(id(), nome(), anexo(), link(), descricao(), data(), parceiro());
    }
}