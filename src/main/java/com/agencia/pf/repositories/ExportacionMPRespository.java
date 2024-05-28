package com.agencia.pf.repositories;

import com.agencia.pf.models.mercadopago.ExportacionMP;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExportacionMPRespository extends CrudRepository<ExportacionMP, Long> {


}
