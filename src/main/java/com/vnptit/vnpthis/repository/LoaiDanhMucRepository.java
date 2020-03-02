package com.vnptit.vnpthis.repository;

import com.vnptit.vnpthis.domain.LoaiDanhMuc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LoaiDanhMuc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiDanhMucRepository extends JpaRepository<LoaiDanhMuc, Long>, JpaSpecificationExecutor<LoaiDanhMuc> {

}
