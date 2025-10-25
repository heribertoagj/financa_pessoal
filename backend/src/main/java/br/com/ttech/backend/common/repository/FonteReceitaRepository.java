package br.com.ttech.backend.common.repository;

import br.com.ttech.backend.common.entity.FonteReceita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FonteReceitaRepository extends JpaRepository<FonteReceita, String> {
    List<FonteReceita> findByIdUser(String idUser);
}
