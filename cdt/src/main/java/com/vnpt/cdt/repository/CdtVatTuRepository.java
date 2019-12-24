package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtVatTu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtVatTu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtVatTuRepository extends JpaRepository<CdtVatTu, Long>, JpaSpecificationExecutor<CdtVatTu> {

}
