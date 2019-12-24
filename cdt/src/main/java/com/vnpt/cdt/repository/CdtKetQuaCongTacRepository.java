package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.CdtKetQuaCongTac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CdtKetQuaCongTac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CdtKetQuaCongTacRepository extends JpaRepository<CdtKetQuaCongTac, Long>, JpaSpecificationExecutor<CdtKetQuaCongTac> {

}
