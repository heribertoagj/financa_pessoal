package br.com.ttech.backend.common.repository;

import br.com.ttech.backend.common.entity.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, String> {
}
