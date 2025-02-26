package br.com.ttech.fp.backend.controller;


import br.com.ttech.fp.backend.common.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/admin")
public class AdministrativoController {

    @GetMapping("/expenses/type")
    public ResponseEntity<ResponseDto> getFindExpensesType(){
        return ResponseEntity.ok(new ResponseDto());
    }

}
