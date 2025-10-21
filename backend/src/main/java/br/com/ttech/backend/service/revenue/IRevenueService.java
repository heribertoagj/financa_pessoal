package br.com.ttech.backend.service.revenue;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.RevenueRequest;

public interface IRevenueService {

    public Response find(String idUser, String idRevenueSource, String idRevenueType);

    public Response detail(String idUser);

    public Response create(RevenueRequest revenueRequest);

    public Response update(RevenueRequest revenueRequest);

    public Response remove(String id);
}
