package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotaoct;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtDutoanDaotaoct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtDutoanDaotaoctRepository extends JpaRepository<QldtDutoanDaotaoct, Long>, JpaSpecificationExecutor<QldtDutoanDaotaoct> {

}
