package br.com.ttech.backend.controller.revenue;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.RevenueRequest;
import br.com.ttech.backend.service.revenue.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/revenue")
public class RevenueController {

    @Autowired
    IRevenueService service;

    @GetMapping(value="/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> find (
            @RequestParam(value = "idUser", required = true) String idUser,
            @RequestParam(value = "idRevenueSource", required = false) String idRevenueSource,
            @RequestParam(value = "idRevenueType", required = false) String idRevenueType){
        return ResponseEntity.ok(service.find(idUser, idRevenueSource, idRevenueType));
    }

    @GetMapping(value = "/detail", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> detail (
            @RequestParam(value = "id", required = true) String id){
        return ResponseEntity.ok(service.detail(id));
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create (
            @RequestBody() RevenueRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update (
            @RequestBody() RevenueRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping(value = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> remove (
            @RequestParam("id") String id){
        return ResponseEntity.ok(service.remove(id));
    }
}
