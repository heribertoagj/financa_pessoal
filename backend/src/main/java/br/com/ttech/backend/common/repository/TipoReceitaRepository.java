package br.com.ttech.backend.common.repository;

import br.com.ttech.backend.common.entity.RevenueType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevenueTypeRepository extends JpaRepository<RevenueType, String> {
    List<RevenueType> findByIdUser(String idUser);
}
