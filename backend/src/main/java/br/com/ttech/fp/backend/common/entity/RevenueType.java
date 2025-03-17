package br.com.ttech.fp.backend.common.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "revenue_type")
@Table(name = "revenue_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RevenueType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idUser;

    private String dsRevenueType;
}
