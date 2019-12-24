package com.vnpt.cdt.repository;
import com.vnpt.cdt.domain.OrgOfficer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrgOfficer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrgOfficerRepository extends JpaRepository<OrgOfficer, Long>, JpaSpecificationExecutor<OrgOfficer> {

}
