package br.com.ttech.fp.backend.controller;

import br.com.ttech.fp.backend.common.dto.ResponseDto;
import br.com.ttech.fp.backend.common.dto.RevenueDto;
import br.com.ttech.fp.backend.service.impl.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/revenue")
public class RevenueController {

    @Autowired
    RevenueService service;

    @GetMapping("/find")
    public ResponseEntity<ResponseDto> findRevenueType(
            @RequestParam(value = "idUser", required = true) String idUser,
            @RequestParam(value = "idRevenueSource", required = false) String idRevenueSource,
            @RequestParam(value = "idRevenueType", required = false) String idRevenueType){
        return ResponseEntity.ok(service.findByfilters(idUser, idRevenueSource, idRevenueType));
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto> saveRevenue(
            @RequestBody()RevenueDto revenue){
        return ResponseEntity.ok(service.save(revenue));
    }
}
