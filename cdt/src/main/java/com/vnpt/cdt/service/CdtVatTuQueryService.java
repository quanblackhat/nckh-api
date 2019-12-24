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

import com.vnpt.cdt.domain.CdtVatTu;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtVatTuRepository;
import com.vnpt.cdt.service.dto.CdtVatTuCriteria;

/**
 * Service for executing complex queries for {@link CdtVatTu} entities in the database.
 * The main input is a {@link CdtVatTuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtVatTu} or a {@link Page} of {@link CdtVatTu} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtVatTuQueryService extends QueryService<CdtVatTu> {

    private final Logger log = LoggerFactory.getLogger(CdtVatTuQueryService.class);

    private final CdtVatTuRepository cdtVatTuRepository;

    public CdtVatTuQueryService(CdtVatTuRepository cdtVatTuRepository) {
        this.cdtVatTuRepository = cdtVatTuRepository;
    }

    /**
     * Return a {@link List} of {@link CdtVatTu} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtVatTu> findByCriteria(CdtVatTuCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtVatTu> specification = createSpecification(criteria);
        return cdtVatTuRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtVatTu} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtVatTu> findByCriteria(CdtVatTuCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtVatTu> specification = createSpecification(criteria);
        return cdtVatTuRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtVatTuCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtVatTu> specification = createSpecification(criteria);
        return cdtVatTuRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtVatTuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtVatTu> createSpecification(CdtVatTuCriteria criteria) {
        Specification<CdtVatTu> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtVatTu_.id));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtVatTu_.csytid));
            }
            if (criteria.getCdtVatTuHoTroId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtVatTuHoTroId(),
                    root -> root.join(CdtVatTu_.cdtVatTuHoTro, JoinType.LEFT).get(CdtVatTuHoTro_.id)));
            }
            if (criteria.getCdtChiDaoTuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtChiDaoTuyenId(),
                    root -> root.join(CdtVatTu_.cdtChiDaoTuyen, JoinType.LEFT).get(CdtChiDaoTuyen_.id)));
            }
        }
        return specification;
    }
}
