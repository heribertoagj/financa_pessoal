package br.com.ttech.backend.common.repository;

import br.com.ttech.backend.common.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, String> {

    List<Receita> findByIdUser(String idUser);

    @Query("Select r " +
            " from receita r " +
            "where idUser = :idUser " +
            "  and fonteReceita.id = :idFonteReceita" +
            "  and tipoReceita.id = :idTipoReceita")
    List<Receita> findByFilters(@Param("idUser") String idUser,
                                @Param("idFonteReceita") String idFonteReceita,
                                @Param("idTipoReceita") String idTipoReceita);

    @Query("Select r " +
            " from receita r " +
            "where idUser = :idUser " +
            "  and fonteReceita.id = :idFonteReceita")
    List<Receita> findByRevenueSource(@Param("idUser") String idUser,
                                      @Param("idFonteReceita") String idFonteReceita);

    @Query("Select r " +
            " from receita r " +
            "where idUser = :idUser " +
            "  and tipoReceita.id = :idTipoReceita")
    List<Receita> findByRevenueType(@Param("idUser") String idUser,
                                    @Param("idTipoReceita") String idTipoReceita);

}
