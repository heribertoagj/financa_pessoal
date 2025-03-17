package br.com.ttech.fp.backend.common.records.revenue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record RevenueSourceRequest(
        String id,

        @NotNull(message = "User id is required")
        @NotBlank(message = "User id is required")
        String idUser,

        @NotNull(message = "Revenue source description is required")
        @NotBlank(message = "Revenue source description required")
        String dsRevenueSource,

        boolean ciIssueInvoice,
        String cdCnpjSource,
        String dsLegalName) {}


