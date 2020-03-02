package com.vnptit.vnpthis.repository;

import com.vnptit.vnpthis.domain.nckh.DanhGia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DanhGia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Long>, JpaSpecificationExecutor<DanhGia> {

}
