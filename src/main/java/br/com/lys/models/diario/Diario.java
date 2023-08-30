package br.com.lys.models.diario;
import br.com.lys.models.usuario.Usuario;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Diario {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String texto;
    @Timestamp("dd/MM/yyyy HH:mm")
    private String data;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
