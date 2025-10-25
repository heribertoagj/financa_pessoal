package br.com.ttech.backend.common.repository;

import br.com.ttech.backend.common.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, String> {

    List<Revenue> findByIdUser(String idUser);

    @Query("Select r " +
            " from revenue r " +
            "where idUser = :idUser " +
            "  and revenueSource.id = :idRevenueSource" +
            "  and revenueType.id = :idRevenueType")
    List<Revenue> findByFilters(@Param("idUser") String idUser,
                                    @Param("idRevenueSource") String idRevenueSource,
                                        @Param("idRevenueType") String idRevenueType);

    @Query("Select r " +
            " from revenue r " +
            "where idUser = :idUser " +
            "  and revenueSource.id = :idRevenueSource")
    List<Revenue> findByRevenueSource(@Param("idUser") String idUser,
                                            @Param("idRevenueSource") String idRevenueSource);

    @Query("Select r " +
            " from revenue r " +
            "where idUser = :idUser " +
            "  and revenueType.id = :idRevenueType")
    List<Revenue> findByRevenueType(@Param("idUser") String idUser,
                                            @Param("idRevenueType") String idRevenueType);

}
