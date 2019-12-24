package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtKyThuat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtKyThuat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtKyThuatRepository extends JpaRepository<CdtKyThuat, Long>, JpaSpecificationExecutor<CdtKyThuat> {

}
