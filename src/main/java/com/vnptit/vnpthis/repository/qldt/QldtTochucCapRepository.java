package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtTochucCap;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtTochucCap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtTochucCapRepository extends JpaRepository<QldtTochucCap, Long>, JpaSpecificationExecutor<QldtTochucCap> {

}
