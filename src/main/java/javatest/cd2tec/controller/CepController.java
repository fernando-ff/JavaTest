package javatest.cd2tec.controller;


import javatest.cd2tec.domain.ResponseCep;
import javatest.cd2tec.service.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CepController {

    private final CepService service;
    @GetMapping("/consular/cep")
    private ResponseEntity<ResponseCep> calcularFrete(@RequestParam("peso") BigDecimal peso,
                                                      @RequestParam("cepOrigem") String cepOrigem,
                                                      @RequestParam("cepDestino") String cepDestino,
                                                      @RequestParam("nomeDestinatario") String nomeDestinatario) {


        return service.calcularFrete(peso,cepOrigem, cepDestino, nomeDestinatario);
    }
}
