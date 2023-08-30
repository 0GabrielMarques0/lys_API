package br.com.lys.models.voluntario;

import br.com.lys.models.pessoa.Pessoa;
import jakarta.persistence.Entity;


@Entity
public class Voluntario extends Pessoa {
    public Voluntario(Long id, String nome, String email, String telefone, String senha) {
        super(id, nome, email, telefone, senha);
    }

    public Voluntario() {
    }
}
