package com.vnptit.vnpthis.repository;

import com.vnptit.vnpthis.domain.nckh.ChuyenMuc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ChuyenMuc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChuyenMucRepository extends JpaRepository<ChuyenMuc, Long>, JpaSpecificationExecutor<ChuyenMuc> {

}
