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

import com.vnpt.nckh.domain.OrgOfficer;
import com.vnpt.nckh.domain.*; // for static metamodels
import com.vnpt.nckh.repository.OrgOfficerRepository;
import com.vnpt.nckh.service.dto.OrgOfficerCriteria;

/**
 * Service for executing complex queries for {@link OrgOfficer} entities in the database.
 * The main input is a {@link OrgOfficerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrgOfficer} or a {@link Page} of {@link OrgOfficer} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrgOfficerQueryService extends QueryService<OrgOfficer> {

    private final Logger log = LoggerFactory.getLogger(OrgOfficerQueryService.class);

    private final OrgOfficerRepository orgOfficerRepository;

    public OrgOfficerQueryService(OrgOfficerRepository orgOfficerRepository) {
        this.orgOfficerRepository = orgOfficerRepository;
    }

    /**
     * Return a {@link List} of {@link OrgOfficer} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrgOfficer> findByCriteria(OrgOfficerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrgOfficer> specification = createSpecification(criteria);
        return orgOfficerRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link OrgOfficer} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrgOfficer> findByCriteria(OrgOfficerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrgOfficer> specification = createSpecification(criteria);
        return orgOfficerRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrgOfficerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrgOfficer> specification = createSpecification(criteria);
        return orgOfficerRepository.count(specification);
    }

    /**
     * Function to convert {@link OrgOfficerCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OrgOfficer> createSpecification(OrgOfficerCriteria criteria) {
        Specification<OrgOfficer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OrgOfficer_.id));
            }
            if (criteria.getOfficerType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOfficerType(), OrgOfficer_.officerType));
            }
            if (criteria.getOfficerCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOfficerCode(), OrgOfficer_.officerCode));
            }
            if (criteria.getOfficerName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOfficerName(), OrgOfficer_.officerName));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), OrgOfficer_.email));
            }
            if (criteria.getNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNote(), OrgOfficer_.note));
            }
            if (criteria.getTrinhDo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTrinhDo(), OrgOfficer_.trinhDo));
            }
            if (criteria.getChucDanh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChucDanh(), OrgOfficer_.chucDanh));
            }
            if (criteria.getMaBacSi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBacSi(), OrgOfficer_.maBacSi));
            }
            if (criteria.getHocHam() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHocHam(), OrgOfficer_.hocHam));
            }
            if (criteria.getHocVi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHocVi(), OrgOfficer_.hocVi));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), OrgOfficer_.phone));
            }
            if (criteria.getOpt() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOpt(), OrgOfficer_.opt));
            }
            if (criteria.getDiaChi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiaChi(), OrgOfficer_.diaChi));
            }
            if (criteria.getNgaySinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaySinh(), OrgOfficer_.ngaySinh));
            }
            if (criteria.getGioiTinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGioiTinh(), OrgOfficer_.gioiTinh));
            }
            if (criteria.getDeptId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeptId(), OrgOfficer_.deptId));
            }
            if (criteria.getSubDeptId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSubDeptId(), OrgOfficer_.subDeptId));
            }
            if (criteria.getNckhNhanSuId() != null) {
                specification = specification.and(buildSpecification(criteria.getNckhNhanSuId(),
                    root -> root.join(OrgOfficer_.nckhNhanSus, JoinType.LEFT).get(NckhNhanSu_.id)));
            }
            if (criteria.getOrgOrganizationId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrgOrganizationId(),
                    root -> root.join(OrgOfficer_.orgOrganization, JoinType.LEFT).get(OrgOrganization_.id)));
            }
        }
        return specification;
    }
}
