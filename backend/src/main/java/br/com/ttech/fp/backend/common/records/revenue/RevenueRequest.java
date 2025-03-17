package br.com.ttech.fp.backend.common.dto;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RevenueDto(
        @NotBlank(message = "User id is required")
        String idUser,
        @NotBlank(message = "Revenue Type is required")
        String idRevenueType,
        @NotBlank(message = "Revenue source is required")
        String idRevenueSource,

        String dsRevenue,
        Integer nuPayDay,
        boolean isIssueInvoice,
        BigDecimal vlAmount) { }
