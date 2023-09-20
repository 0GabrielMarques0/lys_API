package br.com.lys.models.post;
import br.com.lys.models.voluntario.Voluntario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String texto;
    private String titulo;
    private String anexo;
    private String data;

    @ManyToOne
    @JoinColumn(name = "id_voluntario")
    private Voluntario voluntario;
}
