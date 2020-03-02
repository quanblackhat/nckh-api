package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtDmNguoidung;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtDmNguoidung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtDmNguoidungRepository extends JpaRepository<QldtDmNguoidung, Long>, JpaSpecificationExecutor<QldtDmNguoidung> {

}
