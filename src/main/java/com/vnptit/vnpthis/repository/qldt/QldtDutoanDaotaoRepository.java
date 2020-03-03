package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtDutoanDaotao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtDutoanDaotaoRepository extends JpaRepository<QldtDutoanDaotao, Long>, JpaSpecificationExecutor<QldtDutoanDaotao> {

}
