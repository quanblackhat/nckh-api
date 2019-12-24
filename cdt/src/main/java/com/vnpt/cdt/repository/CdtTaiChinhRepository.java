package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtTaiChinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtTaiChinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtTaiChinhRepository extends JpaRepository<CdtTaiChinh, Long>, JpaSpecificationExecutor<CdtTaiChinh> {

}
