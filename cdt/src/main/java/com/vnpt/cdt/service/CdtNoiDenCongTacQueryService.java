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

import com.vnpt.cdt.domain.CdtNoiDenCongTac;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtNoiDenCongTacRepository;
import com.vnpt.cdt.service.dto.CdtNoiDenCongTacCriteria;

/**
 * Service for executing complex queries for {@link CdtNoiDenCongTac} entities in the database.
 * The main input is a {@link CdtNoiDenCongTacCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtNoiDenCongTac} or a {@link Page} of {@link CdtNoiDenCongTac} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtNoiDenCongTacQueryService extends QueryService<CdtNoiDenCongTac> {

    private final Logger log = LoggerFactory.getLogger(CdtNoiDenCongTacQueryService.class);

    private final CdtNoiDenCongTacRepository cdtNoiDenCongTacRepository;

    public CdtNoiDenCongTacQueryService(CdtNoiDenCongTacRepository cdtNoiDenCongTacRepository) {
        this.cdtNoiDenCongTacRepository = cdtNoiDenCongTacRepository;
    }

    /**
     * Return a {@link List} of {@link CdtNoiDenCongTac} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtNoiDenCongTac> findByCriteria(CdtNoiDenCongTacCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtNoiDenCongTac> specification = createSpecification(criteria);
        return cdtNoiDenCongTacRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtNoiDenCongTac} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtNoiDenCongTac> findByCriteria(CdtNoiDenCongTacCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtNoiDenCongTac> specification = createSpecification(criteria);
        return cdtNoiDenCongTacRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtNoiDenCongTacCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtNoiDenCongTac> specification = createSpecification(criteria);
        return cdtNoiDenCongTacRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtNoiDenCongTacCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtNoiDenCongTac> createSpecification(CdtNoiDenCongTacCriteria criteria) {
        Specification<CdtNoiDenCongTac> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtNoiDenCongTac_.id));
            }
            if (criteria.getMaNoiDen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaNoiDen(), CdtNoiDenCongTac_.maNoiDen));
            }
            if (criteria.getTenNoiDen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenNoiDen(), CdtNoiDenCongTac_.tenNoiDen));
            }
            if (criteria.getThuTuSapXep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThuTuSapXep(), CdtNoiDenCongTac_.thuTuSapXep));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtNoiDenCongTac_.csytid));
            }
            if (criteria.getNoidenId() != null) {
                specification = specification.and(buildSpecification(criteria.getNoidenId(),
                    root -> root.join(CdtNoiDenCongTac_.noidens, JoinType.LEFT).get(CdtNoiDen_.id)));
            }
        }
        return specification;
    }
}
