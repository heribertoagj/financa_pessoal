package br.com.ttech.backend.common.repository;

import br.com.ttech.backend.common.entity.TipoReceita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoReceitaRepository extends JpaRepository<TipoReceita, String> {
    List<TipoReceita> findByIdUser(String idUser);
}
