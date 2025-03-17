package br.com.ttech.fp.backend.common.repository;

import br.com.ttech.fp.backend.common.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepository extends JpaRepository<Establishment, String> {
}
