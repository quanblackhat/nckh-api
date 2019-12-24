package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtKyThuatHoTro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtKyThuatHoTro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtKyThuatHoTroRepository extends JpaRepository<CdtKyThuatHoTro, Long>, JpaSpecificationExecutor<CdtKyThuatHoTro> {

}
