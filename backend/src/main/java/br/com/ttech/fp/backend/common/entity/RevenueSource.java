package br.com.ttech.fp.backend.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity(name = "revenue_source")
@Table(name = "revenue_source")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RevenueSource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idUser;

    private String dsRevenueSource;

    private Boolean ciIssueInvoice;

    private String cdCnpjSource;

    private String dsLegalName;

    private LocalDateTime hrCreatedAt;

    private LocalDateTime hrUpdatedAt;
}
