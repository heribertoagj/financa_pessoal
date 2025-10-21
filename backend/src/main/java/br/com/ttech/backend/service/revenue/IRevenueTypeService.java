package br.com.ttech.backend.service.revenue;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.RevenueTypeRequest;

public interface IRevenueTypeService {

    public Response find(String idUser);

    public Response detail(String id);

    public Response create(RevenueTypeRequest request);

    public Response update(RevenueTypeRequest request);

    public Response remove(String id);
}
