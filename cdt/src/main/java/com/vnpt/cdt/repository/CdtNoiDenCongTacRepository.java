package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtNoiDenCongTac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtNoiDenCongTac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtNoiDenCongTacRepository extends JpaRepository<CdtNoiDenCongTac, Long>, JpaSpecificationExecutor<CdtNoiDenCongTac> {

}
