package br.com.ttech.backend.common.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "estabelecimento")
@Table(name = "estabelecimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idUser;

    private String dsEstabelecimento;
}
