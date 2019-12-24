package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtNhanVien;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtNhanVien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtNhanVienRepository extends JpaRepository<CdtNhanVien, Long>, JpaSpecificationExecutor<CdtNhanVien> {

}
