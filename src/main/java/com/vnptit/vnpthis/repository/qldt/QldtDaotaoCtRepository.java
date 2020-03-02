package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtDaotaoCt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtDaotaoCtRepository extends JpaRepository<QldtDaotaoCt, Long>, JpaSpecificationExecutor<QldtDaotaoCt> {

}
