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

import com.vnpt.cdt.domain.CdtKyThuat;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtKyThuatRepository;
import com.vnpt.cdt.service.dto.CdtKyThuatCriteria;

/**
 * Service for executing complex queries for {@link CdtKyThuat} entities in the database.
 * The main input is a {@link CdtKyThuatCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtKyThuat} or a {@link Page} of {@link CdtKyThuat} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtKyThuatQueryService extends QueryService<CdtKyThuat> {

    private final Logger log = LoggerFactory.getLogger(CdtKyThuatQueryService.class);

    private final CdtKyThuatRepository cdtKyThuatRepository;

    public CdtKyThuatQueryService(CdtKyThuatRepository cdtKyThuatRepository) {
        this.cdtKyThuatRepository = cdtKyThuatRepository;
    }

    /**
     * Return a {@link List} of {@link CdtKyThuat} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtKyThuat> findByCriteria(CdtKyThuatCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtKyThuat> specification = createSpecification(criteria);
        return cdtKyThuatRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtKyThuat} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtKyThuat> findByCriteria(CdtKyThuatCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtKyThuat> specification = createSpecification(criteria);
        return cdtKyThuatRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtKyThuatCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtKyThuat> specification = createSpecification(criteria);
        return cdtKyThuatRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtKyThuatCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtKyThuat> createSpecification(CdtKyThuatCriteria criteria) {
        Specification<CdtKyThuat> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtKyThuat_.id));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtKyThuat_.csytid));
            }
            if (criteria.getCdtKyThuatHoTroId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtKyThuatHoTroId(),
                    root -> root.join(CdtKyThuat_.cdtKyThuatHoTro, JoinType.LEFT).get(CdtKyThuatHoTro_.id)));
            }
            if (criteria.getCdtChiDaoTuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtChiDaoTuyenId(),
                    root -> root.join(CdtKyThuat_.cdtChiDaoTuyen, JoinType.LEFT).get(CdtChiDaoTuyen_.id)));
            }
        }
        return specification;
    }
}
