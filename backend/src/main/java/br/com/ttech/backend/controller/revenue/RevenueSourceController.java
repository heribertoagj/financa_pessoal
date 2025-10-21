package br.com.ttech.backend.controller.revenue;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.RevenueSourceRequest;
import br.com.ttech.backend.service.revenue.IRevenueSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/revenue/source")
public class RevenueSourceController {

    @Autowired
    IRevenueSourceService service;

    @GetMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> find(
            @RequestParam(required = true) String idUser){
        return ResponseEntity.ok(service.find(idUser));
    }

    @GetMapping(value = "/detail", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> detail(
            @RequestParam(required = true) String id){
        return ResponseEntity.ok(service.detail(id));
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@RequestBody()RevenueSourceRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update(@RequestBody()RevenueSourceRequest request){
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping(value = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> remove(@RequestParam(required = true) String id){
        return ResponseEntity.ok(service.remove(id));
    }
}
