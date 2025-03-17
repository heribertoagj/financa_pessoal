package br.com.ttech.fp.backend.common.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "establishment")
@Table(name = "establishment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idUser;

    private String dsEstablishment;
}
