package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtChiDaoTuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtChiDaoTuyenRepository extends JpaRepository<CdtChiDaoTuyen, Long>, JpaSpecificationExecutor<CdtChiDaoTuyen> {

}
