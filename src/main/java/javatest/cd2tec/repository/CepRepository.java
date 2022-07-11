package javatest.cd2tec.repository;

import javatest.cd2tec.domain.ResponseCep;
import org.springframework.data.repository.CrudRepository;

public interface CepRepository extends CrudRepository<ResponseCep, Long> {
}
