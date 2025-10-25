package br.com.ttech.backend.common.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tipo_receita")
@Table(name = "tipo_receita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TipoReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idUser;

    private String dsTipoReceita;
}
