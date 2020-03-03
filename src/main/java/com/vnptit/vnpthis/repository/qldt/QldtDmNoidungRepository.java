package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtDmNoidung;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtDmNoidung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtDmNoidungRepository extends JpaRepository<QldtDmNoidung, Long>, JpaSpecificationExecutor<QldtDmNoidung> {

}
