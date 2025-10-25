package br.com.ttech.backend.service.revenue;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.TipoReceitaRequest;

public interface IRevenueTypeService {

    public Response find(String idUser);

    public Response detail(String id);

    public Response create(TipoReceitaRequest request);

    public Response update(TipoReceitaRequest request);

    public Response remove(String id);
}
