package com.agencia.pf.repositories;

import com.agencia.pf.models.mercadopago.ExportacionMP;
import com.agencia.pf.models.mercadopago.MercadoPagoLink;
import com.agencia.pf.models.rapipago.Rapipagoconsolidado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RapipagoConsolidadoRepository extends CrudRepository<Rapipagoconsolidado, Long> {
    List<Rapipagoconsolidado> findByDocumento(String documento);
    List<Rapipagoconsolidado> findByMotorbusqueda(String motorbusqueda);
    List<Rapipagoconsolidado> findByCodigofactura(String codigofactura);


}
