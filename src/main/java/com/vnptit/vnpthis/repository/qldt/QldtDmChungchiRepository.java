package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtDmChungchi;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtDmChungchi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtDmChungchiRepository extends JpaRepository<QldtDmChungchi, Long>, JpaSpecificationExecutor<QldtDmChungchi> {

}
