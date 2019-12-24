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

import com.vnpt.cdt.domain.CdtKetQuaCongTac;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtKetQuaCongTacRepository;
import com.vnpt.cdt.service.dto.CdtKetQuaCongTacCriteria;

/**
 * Service for executing complex queries for {@link CdtKetQuaCongTac} entities in the database.
 * The main input is a {@link CdtKetQuaCongTacCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtKetQuaCongTac} or a {@link Page} of {@link CdtKetQuaCongTac} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtKetQuaCongTacQueryService extends QueryService<CdtKetQuaCongTac> {

    private final Logger log = LoggerFactory.getLogger(CdtKetQuaCongTacQueryService.class);

    private final CdtKetQuaCongTacRepository cdtKetQuaCongTacRepository;

    public CdtKetQuaCongTacQueryService(CdtKetQuaCongTacRepository cdtKetQuaCongTacRepository) {
        this.cdtKetQuaCongTacRepository = cdtKetQuaCongTacRepository;
    }

    /**
     * Return a {@link List} of {@link CdtKetQuaCongTac} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtKetQuaCongTac> findByCriteria(CdtKetQuaCongTacCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtKetQuaCongTac> specification = createSpecification(criteria);
        return cdtKetQuaCongTacRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtKetQuaCongTac} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtKetQuaCongTac> findByCriteria(CdtKetQuaCongTacCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtKetQuaCongTac> specification = createSpecification(criteria);
        return cdtKetQuaCongTacRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtKetQuaCongTacCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtKetQuaCongTac> specification = createSpecification(criteria);
        return cdtKetQuaCongTacRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtKetQuaCongTacCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtKetQuaCongTac> createSpecification(CdtKetQuaCongTacCriteria criteria) {
        Specification<CdtKetQuaCongTac> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtKetQuaCongTac_.id));
            }
            if (criteria.getMaKetQua() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaKetQua(), CdtKetQuaCongTac_.maKetQua));
            }
            if (criteria.getTenKetQua() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenKetQua(), CdtKetQuaCongTac_.tenKetQua));
            }
            if (criteria.getThuTuSapXep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThuTuSapXep(), CdtKetQuaCongTac_.thuTuSapXep));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtKetQuaCongTac_.csytid));
            }
            if (criteria.getCdtChiDaoTuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getCdtChiDaoTuyenId(),
                    root -> root.join(CdtKetQuaCongTac_.cdtChiDaoTuyen, JoinType.LEFT).get(CdtChiDaoTuyen_.id)));
            }
        }
        return specification;
    }
}
