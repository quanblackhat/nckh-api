package com.vnptit.vnpthis.repository;

import com.vnptit.vnpthis.domain.nckh.DeTai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DeTai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeTaiRepository extends JpaRepository<DeTai, Long>, JpaSpecificationExecutor<DeTai> {

}
