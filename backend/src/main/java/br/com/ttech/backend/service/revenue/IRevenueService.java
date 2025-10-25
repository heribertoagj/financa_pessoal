package br.com.ttech.backend.service.revenue;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.ReceitaRequest;

public interface IRevenueService {

    public Response find(String idUser, String idRevenueSource, String idRevenueType);

    public Response detail(String idUser);

    public Response create(ReceitaRequest receitaRequest);

    public Response update(ReceitaRequest receitaRequest);

    public Response remove(String id);
}
