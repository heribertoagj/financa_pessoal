package br.com.ttech.backend.controller.receita;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.FonteReceitaRequest;
import br.com.ttech.backend.service.revenue.IRevenueSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/receita/fonte")
public class FonteReceitaController {

    @Autowired
    IRevenueSourceService service;

    @GetMapping(value = "/pesquisar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> pesquisar(
            @RequestParam(required = true) String idUser){
        return ResponseEntity.ok(service.find(idUser));
    }

    @GetMapping(value = "/detalhar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> detalhar(
            @RequestParam(required = true) String id){
        return ResponseEntity.ok(service.detail(id));
    }

    @PostMapping(value = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> criar(@RequestBody() FonteReceitaRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping(value = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> atualizar(@RequestBody() FonteReceitaRequest request){
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping(value = "/remover", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> remover(@RequestParam(required = true) String id){
        return ResponseEntity.ok(service.remove(id));
    }
}
