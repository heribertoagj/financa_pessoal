package br.com.ttech.backend.service.revenue;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.RevenueSourceRequest;

public interface IRevenueSourceService {

    public Response find(String idUser);
    public Response detail(String id);
    public Response create(RevenueSourceRequest request);
    public Response update(RevenueSourceRequest request);
    public Response remove(String id);
}
