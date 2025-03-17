package br.com.ttech.fp.backend.common.repository;

import br.com.ttech.fp.backend.common.entity.RevenueSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevenueSourceRepository extends JpaRepository<RevenueSource, String> {
    List<RevenueSource> findByIdUser(String idUser);
}
