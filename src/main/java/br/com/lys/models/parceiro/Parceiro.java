package br.com.lys.models.parceiro;
import br.com.lys.models.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data

public class Parceiro extends Pessoa {
    private String cnpj;
    public Parceiro(Long id, String nome, String email, String telefone, String senha, String cnpj) {
        super(id, nome, email, telefone, senha);
        this.cnpj = cnpj;
    }
    public Parceiro() {
    }
}
