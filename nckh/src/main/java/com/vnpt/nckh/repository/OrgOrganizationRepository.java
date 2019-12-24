package com.vnpt.nckh.repository;
import com.vnpt.nckh.domain.OrgOrganization;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrgOrganization entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrgOrganizationRepository extends JpaRepository<OrgOrganization, Long>, JpaSpecificationExecutor<OrgOrganization> {

}
