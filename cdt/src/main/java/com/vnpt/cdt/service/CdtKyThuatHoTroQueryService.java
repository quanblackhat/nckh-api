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

import com.vnpt.cdt.domain.CdtKyThuatHoTro;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtKyThuatHoTroRepository;
import com.vnpt.cdt.service.dto.CdtKyThuatHoTroCriteria;

/**
 * Service for executing complex queries for {@link CdtKyThuatHoTro} entities in the database.
 * The main input is a {@link CdtKyThuatHoTroCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtKyThuatHoTro} or a {@link Page} of {@link CdtKyThuatHoTro} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtKyThuatHoTroQueryService extends QueryService<CdtKyThuatHoTro> {

    private final Logger log = LoggerFactory.getLogger(CdtKyThuatHoTroQueryService.class);

    private final CdtKyThuatHoTroRepository cdtKyThuatHoTroRepository;

    public CdtKyThuatHoTroQueryService(CdtKyThuatHoTroRepository cdtKyThuatHoTroRepository) {
        this.cdtKyThuatHoTroRepository = cdtKyThuatHoTroRepository;
    }

    /**
     * Return a {@link List} of {@link CdtKyThuatHoTro} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtKyThuatHoTro> findByCriteria(CdtKyThuatHoTroCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtKyThuatHoTro> specification = createSpecification(criteria);
        return cdtKyThuatHoTroRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtKyThuatHoTro} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtKyThuatHoTro> findByCriteria(CdtKyThuatHoTroCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtKyThuatHoTro> specification = createSpecification(criteria);
        return cdtKyThuatHoTroRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtKyThuatHoTroCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtKyThuatHoTro> specification = createSpecification(criteria);
        return cdtKyThuatHoTroRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtKyThuatHoTroCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtKyThuatHoTro> createSpecification(CdtKyThuatHoTroCriteria criteria) {
        Specification<CdtKyThuatHoTro> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtKyThuatHoTro_.id));
            }
            if (criteria.getMaNoiDen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaNoiDen(), CdtKyThuatHoTro_.maNoiDen));
            }
            if (criteria.getTenNoiDen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenNoiDen(), CdtKyThuatHoTro_.tenNoiDen));
            }
            if (criteria.getThuTuSapXep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThuTuSapXep(), CdtKyThuatHoTro_.thuTuSapXep));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), CdtKyThuatHoTro_.csytid));
            }
            if (criteria.getKythuatId() != null) {
                specification = specification.and(buildSpecification(criteria.getKythuatId(),
                    root -> root.join(CdtKyThuatHoTro_.kythuats, JoinType.LEFT).get(CdtKyThuat_.id)));
            }
        }
        return specification;
    }
}
