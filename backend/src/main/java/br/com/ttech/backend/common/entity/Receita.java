package br.com.ttech.backend.common.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "receita")
@Table(name = "receita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idUser;

    @ManyToOne
    @JoinColumn(name = "idTipoReceita")
    private TipoReceita tipoReceita;

    @ManyToOne
    @JoinColumn(name = "idFonteReceita")
    private FonteReceita fonteReceita;

    private String dsReceita;

    private Integer nuDiaFaturamento;

    private boolean ciNotaFiscal;

    private BigDecimal vlReceita;

    private LocalDateTime hrCreatedAt;

    private LocalDateTime hrUpdatedAt;
}
