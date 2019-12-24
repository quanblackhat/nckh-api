package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtLyDoCongTac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtLyDoCongTac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtLyDoCongTacRepository extends JpaRepository<CdtLyDoCongTac, Long>, JpaSpecificationExecutor<CdtLyDoCongTac> {

}
