package br.com.ttech.backend.service.revenue;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.FonteReceitaRequest;

public interface IRevenueSourceService {

    public Response find(String idUser);
    public Response detail(String id);
    public Response create(FonteReceitaRequest request);
    public Response update(FonteReceitaRequest request);
    public Response remove(String id);
}
