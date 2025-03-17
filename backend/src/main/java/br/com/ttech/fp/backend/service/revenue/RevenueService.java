package br.com.ttech.fp.backend.service.revenue;

import br.com.ttech.fp.backend.common.entity.Revenue;
import br.com.ttech.fp.backend.common.exception.NotFoundException;
import br.com.ttech.fp.backend.common.records.Response;
import br.com.ttech.fp.backend.common.records.revenue.RevenueRequest;
import br.com.ttech.fp.backend.common.entity.RevenueSource;
import br.com.ttech.fp.backend.common.entity.RevenueType;
import br.com.ttech.fp.backend.common.enums.Messages;
import br.com.ttech.fp.backend.common.exception.BadRequestException;
import br.com.ttech.fp.backend.common.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevenueService implements IRevenueService {

    @Autowired
    RevenueRepository repository;

    @Autowired
    IRevenueSourceService revenueSourceService;

    @Autowired
    IRevenueTypeService revenueTypeService;

    @Override
    public Response find(String idUser, String idRevenueSource, String idRevenueType) {
        Response response =  new Response();
        List<br.com.ttech.fp.backend.common.entity.Revenue> revenueList = new ArrayList<>();

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

    public Response create(RevenueRequest request){
        Response response = new Response();

        Revenue entity = setRevenueValues(request, new Revenue());
        entity.setHrCreatedAt(LocalDateTime.now());
        entity.setIdUser(request.idUser());
        entity.setCiIssuedInvoice(false);

        var revenueTypeEntity = revenueTypeService.detail(request.idRevenueType());
        entity.setRevenueType((RevenueType) revenueTypeEntity.getData());

        var revenueSourceEntity = revenueSourceService.detail(request.idRevenueSource());
        entity.setRevenueSource((RevenueSource) revenueSourceEntity.getData());

        var result = repository.save(entity);

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    public Response update(RevenueRequest request){
        Response response = new Response();

        if (request.id() == null) throw new BadRequestException(Messages.ID_IS_REQUIRED.getMessage());

        var revenueOld = detail(request.id());

        var entity = setRevenueValues(request, (Revenue) revenueOld.getData());
        entity.setHrUpdatedAt(LocalDateTime.now());

        var revenueTypeEntity = revenueTypeService.detail(request.idRevenueType());
        entity.setRevenueType((RevenueType) revenueTypeEntity.getData());

        var revenueSourceEntity = revenueSourceService.detail(request.idRevenueSource());
        entity.setRevenueSource((RevenueSource) revenueSourceEntity.getData());

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

        repository.delete((Revenue) revenue.getData());
        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        return response;
    }

    private Revenue setRevenueValues(RevenueRequest request, Revenue entity){
        entity.setDsRevenue(request.dsRevenue());
        entity.setNuPayDay(request.nuPayDay());
        entity.setVlAmount(request.vlAmount());
        return entity;
    }
}
