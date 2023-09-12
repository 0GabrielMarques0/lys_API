package br.com.lys.repositories;

import br.com.lys.models.parceiro.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
    Parceiro findByEmail(String email);
}
