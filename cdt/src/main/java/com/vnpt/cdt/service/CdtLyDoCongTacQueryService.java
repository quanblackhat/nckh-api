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

import com.vnpt.cdt.domain.CdtLyDoCongTac;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtLyDoCongTacRepository;
import com.vnpt.cdt.service.dto.CdtLyDoCongTacCriteria;

/**
 * Service for executing complex queries for {@link CdtLyDoCongTac} entities in the database.
 * The main input is a {@link CdtLyDoCongTacCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtLyDoCongTac} or a {@link Page} of {@link CdtLyDoCongTac} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtLyDoCongTacQueryService extends QueryService<CdtLyDoCongTac> {

    private final Logger log = LoggerFactory.getLogger(CdtLyDoCongTacQueryService.class);

    private final CdtLyDoCongTacRepository cdtLyDoCongTacRepository;

    public CdtLyDoCongTacQueryService(CdtLyDoCongTacRepository cdtLyDoCongTacRepository) {
        this.cdtLyDoCongTacRepository = cdtLyDoCongTacRepository;
    }

    /**
     * Return a {@link List} of {@link CdtLyDoCongTac} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtLyDoCongTac> findByCriteria(CdtLyDoCongTacCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtLyDoCongTac> specification = createSpecification(criteria);
        return cdtLyDoCongTacRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtLyDoCongTac} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtLyDoCongTac> findByCriteria(CdtLyDoCongTacCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtLyDoCongTac> specification = createSpecification(criteria);
        return cdtLyDoCongTacRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtLyDoCongTacCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtLyDoCongTac> specification = createSpecification(criteria);
        return cdtLyDoCongTacRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtLyDoCongTacCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtLyDoCongTac> createSpecification(CdtLyDoCongTacCriteria criteria) {
        Specification<CdtLyDoCongTac> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtLyDoCongTac_.id));
            }
            if (criteria.getMaLyDo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaLyDo(), CdtLyDoCongTac_.maLyDo));
            }
            if (criteria.getTenLyDo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenLyDo(), CdtLyDoCongTac_.tenLyDo));
            }
            if (criteria.getThuTuSapXep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThuTuSapXep(), CdtLyDoCongTac_.thuTuSapXep));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtLyDoCongTac_.csytid));
            }
            if (criteria.getCdtChiDaoTuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtChiDaoTuyenId(),
                    root -> root.join(CdtLyDoCongTac_.cdtChiDaoTuyen, JoinType.LEFT).get(CdtChiDaoTuyen_.id)));
            }
        }
        return specification;
    }
}
