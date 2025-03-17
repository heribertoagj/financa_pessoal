package br.com.ttech.fp.backend.service;

import br.com.ttech.fp.backend.common.dto.ResponseDto;
import br.com.ttech.fp.backend.common.dto.RevenueDto;
import br.com.ttech.fp.backend.common.entity.Revenue;

import java.util.List;

public interface IRevenueService {

    public ResponseDto findByfilters(String idUser, String idRevenueSource, String idRevenueType);
    public ResponseDto save(RevenueDto revenue);
}
