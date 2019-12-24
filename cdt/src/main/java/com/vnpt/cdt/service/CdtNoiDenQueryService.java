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

import com.vnpt.cdt.domain.CdtNoiDen;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtNoiDenRepository;
import com.vnpt.cdt.service.dto.CdtNoiDenCriteria;

/**
 * Service for executing complex queries for {@link CdtNoiDen} entities in the database.
 * The main input is a {@link CdtNoiDenCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtNoiDen} or a {@link Page} of {@link CdtNoiDen} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtNoiDenQueryService extends QueryService<CdtNoiDen> {

    private final Logger log = LoggerFactory.getLogger(CdtNoiDenQueryService.class);

    private final CdtNoiDenRepository cdtNoiDenRepository;

    public CdtNoiDenQueryService(CdtNoiDenRepository cdtNoiDenRepository) {
        this.cdtNoiDenRepository = cdtNoiDenRepository;
    }

    /**
     * Return a {@link List} of {@link CdtNoiDen} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtNoiDen> findByCriteria(CdtNoiDenCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtNoiDen> specification = createSpecification(criteria);
        return cdtNoiDenRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtNoiDen} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtNoiDen> findByCriteria(CdtNoiDenCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtNoiDen> specification = createSpecification(criteria);
        return cdtNoiDenRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtNoiDenCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtNoiDen> specification = createSpecification(criteria);
        return cdtNoiDenRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtNoiDenCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtNoiDen> createSpecification(CdtNoiDenCriteria criteria) {
        Specification<CdtNoiDen> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtNoiDen_.id));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtNoiDen_.csytid));
            }
            if (criteria.getCdtNoiDenCongTacId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtNoiDenCongTacId(),
                    root -> root.join(CdtNoiDen_.cdtNoiDenCongTac, JoinType.LEFT).get(CdtNoiDenCongTac_.id)));
            }
            if (criteria.getCdtChiDaoTuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtChiDaoTuyenId(),
                    root -> root.join(CdtNoiDen_.cdtChiDaoTuyen, JoinType.LEFT).get(CdtChiDaoTuyen_.id)));
            }
        }
        return specification;
    }
}
