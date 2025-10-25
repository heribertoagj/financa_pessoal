package br.com.ttech.backend.service.revenue;

import br.com.ttech.backend.common.entity.FonteReceita;
import br.com.ttech.backend.common.enums.Messages;
import br.com.ttech.backend.common.exception.BadRequestException;
import br.com.ttech.backend.common.exception.NotFoundException;
import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.FonteReceitaRequest;
import br.com.ttech.backend.common.repository.FonteReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RevenueSourceService implements IRevenueSourceService {

    @Autowired
    FonteReceitaRepository repository;

    @Override
    public Response find(String idUser){
        Response response = new Response();

        if (idUser == null)
            throw new BadRequestException(Messages.USER_ID_IS_REQUIRED.getMessage());

        var result = repository.findByIdUser(idUser);

        response.setMessage((result.isEmpty())?
                Messages.REGISTERS_NOT_FOUND.getMessage():
                Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setCode(HttpStatus.OK.value());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    @Override
    public Response detail(String id) {
        Response response = new Response();
        var result = repository.findById(id);
        if (result.isEmpty()) throw new NotFoundException(Messages.REGISTERS_NOT_FOUND.getMessage());

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(result.get());
        return response;
    }

    @Override
    public Response create(FonteReceitaRequest request) {
        Response response = new Response();

        FonteReceita entity = setRevenueSourceValues(request, new FonteReceita());
        entity.setIdUser(request.idUser());
        entity.setHrCreatedAt(LocalDateTime.now());

        var result = repository.save(entity);

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    @Override
    public Response update(FonteReceitaRequest request) {
        Response response = new Response();

        if (request.id() == null) throw new BadRequestException(Messages.ID_IS_REQUIRED.getMessage());

        var detailResponse = detail(request.id());
        var entityOld = (FonteReceita) detailResponse.getData();
        var entityNew = setRevenueSourceValues(request, entityOld);
        entityNew.setHrUpdatedAt(LocalDateTime.now());
        var result = repository.save(entityNew);

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    @Override
    public Response remove(String id) {
        Response response = new Response();

        var detailResponse = detail(id);
        var entityOld = (FonteReceita) detailResponse.getData();
        repository.delete(entityOld);

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        return response;
    }

    private FonteReceita setRevenueSourceValues(FonteReceitaRequest request, FonteReceita entity){
        entity.setDsFonteReceita(request.dsRevenueSource());
        entity.setCdCnpjPagador(request.cdCnpjSource());
        entity.setDsRazaoSocialPagador(request.dsLegalName());
        return entity;
    }
}
