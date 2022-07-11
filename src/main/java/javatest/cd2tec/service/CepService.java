package javatest.cd2tec.service;

import javatest.cd2tec.domain.ResponseCep;
import javatest.cd2tec.domain.Viacep;
import javatest.cd2tec.exception.BadRequestException;
import javatest.cd2tec.repository.CepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class CepService {

    private final RestTemplate restTemplate;
    private final CepRepository repository;

    public ResponseEntity calcularFrete(BigDecimal peso, String cepOrigem, String cepDestino, String nomeDestinatario) {
        Viacep cepOrigemViacep;
        Viacep cepDestinoViacep;
        BigDecimal desconto = new BigDecimal(1);
        Integer previsaoEntrega = 10;
        log.info("Requisitando dados do cep de origem ao viacep");
        try {
            cepOrigemViacep = restTemplate.getForEntity(
                    String.format("https://viacep.com.br/ws/%s/json/", cepOrigem),
                    Viacep.class
            ).getBody();
        } catch (RestClientException e) {
            throw new BadRequestException(e.getMessage());
        }
        log.info("Requisitando dados do cep de destino ao viacep");
        try {
            cepDestinoViacep = restTemplate.getForEntity(
                    String.format("https://viacep.com.br/ws/%s/json/", cepDestino),
                    Viacep.class
            ).getBody();
        } catch (RestClientException e) {
            throw new BadRequestException(e.getMessage());
        }
        if(cepDestinoViacep.getDdd().equals(cepOrigemViacep.getDdd())) {
            desconto = new BigDecimal(0.5);
            previsaoEntrega = 1;
        } else if(cepDestinoViacep.getUf().equals(cepOrigemViacep.getUf())){
            desconto = new BigDecimal(0.75);
            previsaoEntrega = 3;
        } else {
            previsaoEntrega = 10;
        }
        ResponseCep persistEntity = ResponseCep
                .builder()
                .cepOrigem(cepOrigem)
                .cepDestinatario(cepDestino)
                .nomeDestinatario(nomeDestinatario)
                .dataPrevistaEntrega(previsaoEntrega)
                .dataConsulta(LocalDateTime.now())
                .vlTotalFrete(peso.multiply(desconto))
                .peso(peso)
                .build();
        log.info("Persistindo dados");
        repository.save(persistEntity);
        log.info("Dados persistidos");
        return ResponseEntity.ok().body(persistEntity);
    }
}
