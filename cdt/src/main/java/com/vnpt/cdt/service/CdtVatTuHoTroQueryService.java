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

import com.vnpt.cdt.domain.CdtVatTuHoTro;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtVatTuHoTroRepository;
import com.vnpt.cdt.service.dto.CdtVatTuHoTroCriteria;

/**
 * Service for executing complex queries for {@link CdtVatTuHoTro} entities in the database.
 * The main input is a {@link CdtVatTuHoTroCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtVatTuHoTro} or a {@link Page} of {@link CdtVatTuHoTro} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtVatTuHoTroQueryService extends QueryService<CdtVatTuHoTro> {

    private final Logger log = LoggerFactory.getLogger(CdtVatTuHoTroQueryService.class);

    private final CdtVatTuHoTroRepository cdtVatTuHoTroRepository;

    public CdtVatTuHoTroQueryService(CdtVatTuHoTroRepository cdtVatTuHoTroRepository) {
        this.cdtVatTuHoTroRepository = cdtVatTuHoTroRepository;
    }

    /**
     * Return a {@link List} of {@link CdtVatTuHoTro} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtVatTuHoTro> findByCriteria(CdtVatTuHoTroCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtVatTuHoTro> specification = createSpecification(criteria);
        return cdtVatTuHoTroRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtVatTuHoTro} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtVatTuHoTro> findByCriteria(CdtVatTuHoTroCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtVatTuHoTro> specification = createSpecification(criteria);
        return cdtVatTuHoTroRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtVatTuHoTroCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtVatTuHoTro> specification = createSpecification(criteria);
        return cdtVatTuHoTroRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtVatTuHoTroCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtVatTuHoTro> createSpecification(CdtVatTuHoTroCriteria criteria) {
        Specification<CdtVatTuHoTro> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtVatTuHoTro_.id));
            }
            if (criteria.getMaVatTu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaVatTu(), CdtVatTuHoTro_.maVatTu));
            }
            if (criteria.getTenVatTu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenVatTu(), CdtVatTuHoTro_.tenVatTu));
            }
            if (criteria.getThuTuSapXep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThuTuSapXep(), CdtVatTuHoTro_.thuTuSapXep));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtVatTuHoTro_.csytid));
            }
            if (criteria.getVattuId() != null) {
                specification = specification.and(buildSpecification(criteria.getVattuId(),
                    root -> root.join(CdtVatTuHoTro_.vattus, JoinType.LEFT).get(CdtVatTu_.id)));
            }
        }
        return specification;
    }
}
