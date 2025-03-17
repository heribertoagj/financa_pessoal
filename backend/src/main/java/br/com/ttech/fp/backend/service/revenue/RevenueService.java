package br.com.ttech.fp.backend.service.impl;

import br.com.ttech.fp.backend.common.dto.ResponseDto;
import br.com.ttech.fp.backend.common.dto.RevenueDto;
import br.com.ttech.fp.backend.common.entity.Revenue;
import br.com.ttech.fp.backend.common.entity.RevenueSource;
import br.com.ttech.fp.backend.common.entity.RevenueType;
import br.com.ttech.fp.backend.common.enums.Messages;
import br.com.ttech.fp.backend.common.exception.BadRequestException;
import br.com.ttech.fp.backend.common.repository.RevenueRepository;
import br.com.ttech.fp.backend.service.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevenueService implements IRevenueService {

    @Autowired
    RevenueRepository respository;

    @Override
    public ResponseDto findByfilters(String idUser, String idRevenueSource, String idRevenueType) {
        ResponseDto response =  new ResponseDto();
        List<Revenue> revenueList = new ArrayList<>();

        if (idUser == null){
            throw new BadRequestException(Messages.USER_ID_IS_REQUIRED.getMessage());
        }

        if (idRevenueSource != null && idRevenueType != null){
            revenueList = respository.findByFilters(idUser, idRevenueSource, idRevenueType);
        }
        else if (idRevenueSource != null){
            revenueList = respository.findByRevenueSource(idUser, idRevenueSource);
        }
        else if (idRevenueType != null){
            revenueList = respository.findByRevenueType(idUser, idRevenueType);
        }
        else {
            revenueList = respository.findByIdUser(idUser);
        }

        response.setCode(HttpStatus.OK.value());
        if (revenueList.isEmpty()){
            response.setMessage(Messages.REGISTERS_NOT_FOUND.getMessage());
        }
        else {
            response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        }
        response.setDateTime(LocalDateTime.now());
        response.setData(revenueList);
        return response;
    }

    public ResponseDto save(RevenueDto revenue){
        ResponseDto response = new ResponseDto();

        var revenueExists = respository.findByIdUser(revenue.idUser());
        boolean exists = !revenueExists.isEmpty();

        var result = respository.save(setRevenueValues(revenue, exists));
        response.setCode(HttpStatus.OK.value());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setData(result);
        return response;
    }

    private Revenue setRevenueValues(RevenueDto revenue, boolean exists){
        RevenueType revenueType = new RevenueType();
        RevenueSource revenueSource = new RevenueSource();
        revenueSource.setId(revenue.idRevenueSource());
        revenueType.setId(revenue.idRevenueType());
        Revenue entity = new Revenue();

        entity.setIdUser(revenue.idUser());
        entity.setIdRevenueType(revenueType);
        entity.setIdRevenueSource(revenueSource);
        entity.setDsRevenue(revenue.dsRevenue());
        entity.setNuPayDay(revenue.nuPayDay());
        entity.setIssueInvoice(revenue.isIssueInvoice());
        entity.setVlAmount(revenue.vlAmount());
        if (exists) entity.setHrUpdatedAt(LocalDateTime.now());
        else entity.setHrCreatedAt(LocalDateTime.now());
        return entity;
    }
}
