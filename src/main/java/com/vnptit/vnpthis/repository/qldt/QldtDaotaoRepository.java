package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtDaotao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtDaotao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtDaotaoRepository extends JpaRepository<QldtDaotao, Long>, JpaSpecificationExecutor<QldtDaotao> {

}
