package com.vnpt.nckh.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.vnpt.nckh.domain.OrgOrganization;
import com.vnpt.nckh.domain.*; // for static metamodels
import com.vnpt.nckh.repository.OrgOrganizationRepository;
import com.vnpt.nckh.service.dto.OrgOrganizationCriteria;

/**
 * Service for executing complex queries for {@link OrgOrganization} entities in the database.
 * The main input is a {@link OrgOrganizationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrgOrganization} or a {@link Page} of {@link OrgOrganization} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrgOrganizationQueryService extends QueryService<OrgOrganization> {

    private final Logger log = LoggerFactory.getLogger(OrgOrganizationQueryService.class);

    private final OrgOrganizationRepository orgOrganizationRepository;

    public OrgOrganizationQueryService(OrgOrganizationRepository orgOrganizationRepository) {
        this.orgOrganizationRepository = orgOrganizationRepository;
    }

    /**
     * Return a {@link List} of {@link OrgOrganization} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrgOrganization> findByCriteria(OrgOrganizationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrgOrganization> specification = createSpecification(criteria);
        return orgOrganizationRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link OrgOrganization} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrgOrganization> findByCriteria(OrgOrganizationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrgOrganization> specification = createSpecification(criteria);
        return orgOrganizationRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrgOrganizationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrgOrganization> specification = createSpecification(criteria);
        return orgOrganizationRepository.count(specification);
    }

    /**
     * Function to convert {@link OrgOrganizationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OrgOrganization> createSpecification(OrgOrganizationCriteria criteria) {
        Specification<OrgOrganization> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OrgOrganization_.id));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParentId(), OrgOrganization_.parentId));
            }
            if (criteria.getOrgType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrgType(), OrgOrganization_.orgType));
            }
            if (criteria.getOrgCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrgCode(), OrgOrganization_.orgCode));
            }
            if (criteria.getOrgName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrgName(), OrgOrganization_.orgName));
            }
            if (criteria.getOrgLevel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrgLevel(), OrgOrganization_.orgLevel));
            }
            if (criteria.getDbName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDbName(), OrgOrganization_.dbName));
            }
            if (criteria.getDbSchema() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDbSchema(), OrgOrganization_.dbSchema));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProvinceId(), OrgOrganization_.provinceId));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), OrgOrganization_.status));
            }
            if (criteria.getOrgAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrgAddress(), OrgOrganization_.orgAddress));
            }
            if (criteria.getHospitalCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHospitalCode(), OrgOrganization_.hospitalCode));
            }
            if (criteria.getOrgOfficerId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrgOfficerId(),
                    root -> root.join(OrgOrganization_.orgOfficers, JoinType.LEFT).get(OrgOfficer_.id)));
            }
        }
        return specification;
    }
}
