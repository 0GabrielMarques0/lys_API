package br.com.lys.repositories;

import br.com.lys.models.voluntario.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
}
