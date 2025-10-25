package br.com.ttech.backend.service.revenue;

import br.com.ttech.backend.common.entity.Receita;
import br.com.ttech.backend.common.entity.FonteReceita;
import br.com.ttech.backend.common.entity.TipoReceita;
import br.com.ttech.backend.common.enums.Messages;
import br.com.ttech.backend.common.exception.BadRequestException;
import br.com.ttech.backend.common.exception.NotFoundException;
import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.ReceitaRequest;
import br.com.ttech.backend.common.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevenueService implements IRevenueService {

    @Autowired
    ReceitaRepository repository;

    @Autowired
    IRevenueSourceService revenueSourceService;

    @Autowired
    IRevenueTypeService revenueTypeService;

    @Override
    public Response find(String idUser, String idRevenueSource, String idRevenueType) {
        Response response =  new Response();
        List<Receita> revenueList = new ArrayList<>();

        if (idRevenueSource != null && idRevenueType != null){
            revenueList = repository.findByFilters(idUser, idRevenueSource, idRevenueType);
        }
        else if (idRevenueSource != null){
            revenueList = repository.findByRevenueSource(idUser, idRevenueSource);
        }
        else if (idRevenueType != null){
            revenueList = repository.findByRevenueType(idUser, idRevenueType);
        }
        else {
            revenueList = repository.findByIdUser(idUser);
        }

        response.setCode(HttpStatus.OK.value());
        response.setMessage((revenueList.isEmpty())?
                Messages.REGISTERS_NOT_FOUND.getMessage():
                Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(revenueList);
        return response;
    }

    @Override
    public Response detail(String id) {
        Response response = new Response();

        var entity = repository.findById(id);
        if (entity.isEmpty()) throw new NotFoundException(Messages.REVENUE_NOT_FOUND.getMessage());

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(entity.get());
        return response;
    }

    public Response create(ReceitaRequest request){
        Response response = new Response();

        Receita entity = setRevenueValues(request, new Receita());
        entity.setHrCreatedAt(LocalDateTime.now());
        entity.setIdUser(request.idUser());
        entity.setCiNotaFiscal(false);

        var revenueTypeEntity = revenueTypeService.detail(request.idRevenueType());
        entity.setTipoReceita((TipoReceita) revenueTypeEntity.getData());

        var revenueSourceEntity = revenueSourceService.detail(request.idRevenueSource());
        entity.setFonteReceita((FonteReceita) revenueSourceEntity.getData());

        var result = repository.save(entity);

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    public Response update(ReceitaRequest request){
        Response response = new Response();

        if (request.id() == null) throw new BadRequestException(Messages.ID_IS_REQUIRED.getMessage());

        var revenueOld = detail(request.id());

        var entity = setRevenueValues(request, (Receita) revenueOld.getData());
        entity.setHrUpdatedAt(LocalDateTime.now());

        var revenueTypeEntity = revenueTypeService.detail(request.idRevenueType());
        entity.setTipoReceita((TipoReceita) revenueTypeEntity.getData());

        var revenueSourceEntity = revenueSourceService.detail(request.idRevenueSource());
        entity.setFonteReceita((FonteReceita) revenueSourceEntity.getData());

        var result = repository.save(entity);

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    public Response remove(String id){
        Response response = new Response();

        var revenue = detail(id);

        repository.delete((Receita) revenue.getData());
        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        return response;
    }

    private Receita setRevenueValues(ReceitaRequest request, Receita entity){
        entity.setDsReceita(request.dsRevenue());
        entity.setNuDiaFaturamento(request.nuPayDay());
        entity.setVlReceita(request.vlAmount());
        return entity;
    }
}
