package br.com.ttech.backend.service.revenue;

import br.com.ttech.backend.common.entity.TipoReceita;
import br.com.ttech.backend.common.enums.Messages;
import br.com.ttech.backend.common.exception.BadRequestException;
import br.com.ttech.backend.common.exception.NotFoundException;
import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.TipoReceitaRequest;
import br.com.ttech.backend.common.repository.TipoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RevenueTypeService implements IRevenueTypeService{

    @Autowired
    TipoReceitaRepository repository;

    @Override
    public Response find(String idUser) {
        Response response = new Response();

        if (idUser == null)
            throw new BadRequestException(Messages.USER_ID_IS_REQUIRED.getMessage());

        List<TipoReceita> result = repository.findByIdUser(idUser);

        response.setMessage((result.isEmpty())?
                Messages.REGISTERS_NOT_FOUND.getMessage():
                Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setCode(HttpStatus.OK.value());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    public Response detail(String id){
        Response response = new Response();

        var entity = repository.findById(id);
        if (entity.isEmpty()) throw new NotFoundException(Messages.REVENUE_TYPE_NOT_FOUND.getMessage());

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(entity.get());
        return response;
    }

    @Override
    public Response create(TipoReceitaRequest request) {
        Response response = new Response();

        TipoReceita entity = new TipoReceita();
        entity.setIdUser(request.idUser());
        entity.setDsTipoReceita(request.dsRevenueType());
        var result = repository.save(entity);

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    @Override
    public Response update(TipoReceitaRequest request) {
        Response response = new Response();

        var entity = detail(request.id());

        TipoReceita rt = (TipoReceita) entity.getData();

        rt.setDsTipoReceita(request.dsRevenueType());
        var result = repository.save(rt);

        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    @Override
    public Response remove(String id) {
        Response response = new Response();

        var entity = detail(id);
        repository.delete((TipoReceita) entity.getData());
        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        return response;
    }
}
