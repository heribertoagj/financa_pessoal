package br.com.ttech.backend.common.records.revenue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ReceitaRequest(
        String id,

        @NotNull(message = "User id is required")
        @NotBlank(message = "User id is required")
        String idUser,

        @NotNull(message = "Revenue type id is required")
        @NotBlank(message = "Revenue type id is required")
        String idRevenueType,

        @NotNull(message = "Revenue source id is required")
        @NotBlank(message = "Revenue source id is required")
        String idRevenueSource,


        @NotNull(message = "Revenue description is required")
        @NotBlank(message = "Revenue description is required")
        String dsRevenue,

        Integer nuPayDay,
        boolean ciIssuedInvoice,
        BigDecimal vlAmount) { }
