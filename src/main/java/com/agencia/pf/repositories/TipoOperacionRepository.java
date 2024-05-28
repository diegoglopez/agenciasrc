package com.agencia.pf.repositories;

import com.agencia.pf.models.TipoOperacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TipoOperacionRepository extends CrudRepository<TipoOperacion, Long> {

    List<TipoOperacion> findByCodigo(String codigo);

}
