package javatest.cd2tec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCep {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal peso;
    private String cepOrigem;
    private String cepDestinatario;
    private String nomeDestinatario;
    private BigDecimal vlTotalFrete;
    private Integer dataPrevistaEntrega;
    private LocalDateTime dataConsulta;
}
