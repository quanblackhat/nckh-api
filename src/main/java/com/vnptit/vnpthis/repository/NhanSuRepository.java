package com.vnptit.vnpthis.repository;

import com.vnptit.vnpthis.domain.NhanSu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NhanSu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhanSuRepository extends JpaRepository<NhanSu, Long>, JpaSpecificationExecutor<NhanSu> {

}
