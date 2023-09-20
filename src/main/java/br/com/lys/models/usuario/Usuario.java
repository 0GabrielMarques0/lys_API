package br.com.lys.models.usuario;
import br.com.lys.models.pessoa.Pessoa;
import jakarta.persistence.Entity;

@Entity

public class Usuario extends Pessoa {

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String telefone, String senha) {
        super(id, nome, email, telefone, senha);
    }
}
