package br.com.lys.repositories;

import br.com.lys.models.diario.Diario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiarioRepository extends JpaRepository<Diario, Long> {
}
