package br.com.ttech.backend.controller.receita;

import br.com.ttech.backend.common.records.Response;
import br.com.ttech.backend.common.records.revenue.ReceitaRequest;
import br.com.ttech.backend.service.revenue.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/receita")
public class ReceitaController {

    @Autowired
    IRevenueService service;

    @GetMapping(value="/pesquisar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> pesquisar (
            @RequestParam(value = "idUser", required = true) String idUser,
            @RequestParam(value = "idFonteReceita", required = false) String idFonteReceita,
            @RequestParam(value = "idTipoReceita", required = false) String idTipoReceita){
        return ResponseEntity.ok(service.find(idUser, idFonteReceita, idTipoReceita));
    }

    @GetMapping(value = "/detalhar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> detalhar (
            @RequestParam(value = "id", required = true) String id){
        return ResponseEntity.ok(service.detail(id));
    }

    @PostMapping(value = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> criar (
            @RequestBody() ReceitaRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping(value = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> atualizar (
            @RequestBody() ReceitaRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping(value = "/remover", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> remover (
            @RequestParam("id") String id){
        return ResponseEntity.ok(service.remove(id));
    }
}
