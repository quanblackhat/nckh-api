package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtVatTuHoTro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtVatTuHoTro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtVatTuHoTroRepository extends JpaRepository<CdtVatTuHoTro, Long>, JpaSpecificationExecutor<CdtVatTuHoTro> {

}
