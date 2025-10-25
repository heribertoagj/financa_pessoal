package br.com.ttech.backend.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity(name = "fonte_receita")
@Table(name = "fonte_receita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class FonteReceita {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idUser;

    private String dsFonteReceita;

    private Boolean ciNotaFiscal;

    private String cdCnpjPagador;

    private String dsRazaoSocialPagador;

    private LocalDateTime hrCreatedAt;

    private LocalDateTime hrUpdatedAt;
}
