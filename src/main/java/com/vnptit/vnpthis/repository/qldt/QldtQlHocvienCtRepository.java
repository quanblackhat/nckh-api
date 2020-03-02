package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtQlHocvienCt;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtQlHocvienCt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtQlHocvienCtRepository extends JpaRepository<QldtQlHocvienCt, Long>, JpaSpecificationExecutor<QldtQlHocvienCt> {

}
