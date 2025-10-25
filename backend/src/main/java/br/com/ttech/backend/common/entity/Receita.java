package br.com.ttech.backend.common.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "revenue")
@Table(name = "revenue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idUser;

    @ManyToOne
    @JoinColumn(name = "idRevenueType")
    private RevenueType revenueType;

    @ManyToOne
    @JoinColumn(name = "idRevenueSource")
    private RevenueSource revenueSource;

    private String dsRevenue;

    private Integer nuPayDay;

    private boolean ciIssuedInvoice;

    private BigDecimal vlAmount;

    private LocalDateTime hrCreatedAt;

    private LocalDateTime hrUpdatedAt;
}
