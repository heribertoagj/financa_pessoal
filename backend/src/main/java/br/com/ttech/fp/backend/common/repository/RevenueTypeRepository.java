package br.com.ttech.fp.backend.common.repository;

import br.com.ttech.fp.backend.common.entity.RevenueType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevenueTypeRepository extends JpaRepository<RevenueType, String> {
    List<RevenueType> findByIdUser(String idUser);
}
