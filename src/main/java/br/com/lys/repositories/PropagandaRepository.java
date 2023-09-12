package br.com.lys.repositories;

import br.com.lys.models.propaganda.Propaganda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropagandaRepository extends JpaRepository<Propaganda, Long> {
}
