package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtChungChi;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtChungChi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtChungChiRepository extends JpaRepository<QldtChungChi, Long>, JpaSpecificationExecutor<QldtChungChi> {

}
