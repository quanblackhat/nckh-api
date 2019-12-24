package com.vnpt.cdt.service;

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

import com.vnpt.cdt.domain.CdtNhanVien;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtNhanVienRepository;
import com.vnpt.cdt.service.dto.CdtNhanVienCriteria;

/**
 * Service for executing complex queries for {@link CdtNhanVien} entities in the database.
 * The main input is a {@link CdtNhanVienCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtNhanVien} or a {@link Page} of {@link CdtNhanVien} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtNhanVienQueryService extends QueryService<CdtNhanVien> {

    private final Logger log = LoggerFactory.getLogger(CdtNhanVienQueryService.class);

    private final CdtNhanVienRepository cdtNhanVienRepository;

    public CdtNhanVienQueryService(CdtNhanVienRepository cdtNhanVienRepository) {
        this.cdtNhanVienRepository = cdtNhanVienRepository;
    }

    /**
     * Return a {@link List} of {@link CdtNhanVien} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtNhanVien> findByCriteria(CdtNhanVienCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtNhanVien> specification = createSpecification(criteria);
        return cdtNhanVienRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtNhanVien} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtNhanVien> findByCriteria(CdtNhanVienCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtNhanVien> specification = createSpecification(criteria);
        return cdtNhanVienRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtNhanVienCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtNhanVien> specification = createSpecification(criteria);
        return cdtNhanVienRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtNhanVienCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtNhanVien> createSpecification(CdtNhanVienCriteria criteria) {
        Specification<CdtNhanVien> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtNhanVien_.id));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtNhanVien_.csytid));
            }
            if (criteria.getOrgOfficerId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrgOfficerId(),
                    root -> root.join(CdtNhanVien_.orgOfficer, JoinType.LEFT).get(OrgOfficer_.id)));
            }
            if (criteria.getCdtChiDaoTuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtChiDaoTuyenId(),
                    root -> root.join(CdtNhanVien_.cdtChiDaoTuyen, JoinType.LEFT).get(CdtChiDaoTuyen_.id)));
            }
        }
        return specification;
    }
}
