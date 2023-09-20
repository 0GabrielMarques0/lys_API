package br.com.lys.models.propaganda;
import br.com.lys.models.parceiro.Parceiro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "propagandas")

public class Propaganda {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private String anexo;
    private String link;
    private String data;


    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    private Parceiro parceiro;

}
