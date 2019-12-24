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

import com.vnpt.cdt.domain.CdtTaiChinh;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtTaiChinhRepository;
import com.vnpt.cdt.service.dto.CdtTaiChinhCriteria;

/**
 * Service for executing complex queries for {@link CdtTaiChinh} entities in the database.
 * The main input is a {@link CdtTaiChinhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtTaiChinh} or a {@link Page} of {@link CdtTaiChinh} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtTaiChinhQueryService extends QueryService<CdtTaiChinh> {

    private final Logger log = LoggerFactory.getLogger(CdtTaiChinhQueryService.class);

    private final CdtTaiChinhRepository cdtTaiChinhRepository;

    public CdtTaiChinhQueryService(CdtTaiChinhRepository cdtTaiChinhRepository) {
        this.cdtTaiChinhRepository = cdtTaiChinhRepository;
    }

    /**
     * Return a {@link List} of {@link CdtTaiChinh} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtTaiChinh> findByCriteria(CdtTaiChinhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtTaiChinh> specification = createSpecification(criteria);
        return cdtTaiChinhRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtTaiChinh} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtTaiChinh> findByCriteria(CdtTaiChinhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtTaiChinh> specification = createSpecification(criteria);
        return cdtTaiChinhRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtTaiChinhCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtTaiChinh> specification = createSpecification(criteria);
        return cdtTaiChinhRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtTaiChinhCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtTaiChinh> createSpecification(CdtTaiChinhCriteria criteria) {
        Specification<CdtTaiChinh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtTaiChinh_.id));
            }
            if (criteria.getLoai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLoai(), CdtTaiChinh_.loai));
            }
            if (criteria.getSoTien() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoTien(), CdtTaiChinh_.soTien));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtTaiChinh_.csytid));
            }
            if (criteria.getCdtChiDaoTuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtChiDaoTuyenId(),
                    root -> root.join(CdtTaiChinh_.cdtChiDaoTuyen, JoinType.LEFT).get(CdtChiDaoTuyen_.id)));
            }
        }
        return specification;
    }
}
