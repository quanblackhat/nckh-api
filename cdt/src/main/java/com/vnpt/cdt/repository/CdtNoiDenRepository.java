package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtNoiDen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtNoiDen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtNoiDenRepository extends JpaRepository<CdtNoiDen, Long>, JpaSpecificationExecutor<CdtNoiDen> {

}
