package com.vnpt.nckh.service;

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

import com.vnpt.nckh.domain.NckhDanhmuc;
import com.vnpt.nckh.domain.*; // for static metamodels
import com.vnpt.nckh.repository.NckhDanhmucRepository;
import com.vnpt.nckh.service.dto.NckhDanhmucCriteria;

/**
 * Service for executing complex queries for {@link NckhDanhmuc} entities in the database.
 * The main input is a {@link NckhDanhmucCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NckhDanhmuc} or a {@link Page} of {@link NckhDanhmuc} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NckhDanhmucQueryService extends QueryService<NckhDanhmuc> {

    private final Logger log = LoggerFactory.getLogger(NckhDanhmucQueryService.class);

    private final NckhDanhmucRepository nckhDanhmucRepository;

    public NckhDanhmucQueryService(NckhDanhmucRepository nckhDanhmucRepository) {
        this.nckhDanhmucRepository = nckhDanhmucRepository;
    }

    /**
     * Return a {@link List} of {@link NckhDanhmuc} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NckhDanhmuc> findByCriteria(NckhDanhmucCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NckhDanhmuc> specification = createSpecification(criteria);
        return nckhDanhmucRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link NckhDanhmuc} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhDanhmuc> findByCriteria(NckhDanhmucCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NckhDanhmuc> specification = createSpecification(criteria);
        return nckhDanhmucRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NckhDanhmucCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NckhDanhmuc> specification = createSpecification(criteria);
        return nckhDanhmucRepository.count(specification);
    }

    /**
     * Function to convert {@link NckhDanhmucCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NckhDanhmuc> createSpecification(NckhDanhmucCriteria criteria) {
        Specification<NckhDanhmuc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NckhDanhmuc_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), NckhDanhmuc_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), NckhDanhmuc_.ten));
            }
            if (criteria.getNgaytao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaytao(), NckhDanhmuc_.ngaytao));
            }
            if (criteria.getCsytid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCsytid(), NckhDanhmuc_.csytid));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), NckhDanhmuc_.sudung));
            }
            if (criteria.getThutu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThutu(), NckhDanhmuc_.thutu));
            }
            if (criteria.getNckhDetaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNckhDetaiId(),
                    root -> root.join(NckhDanhmuc_.nckhDetais, JoinType.LEFT).get(NckhDetai_.id)));
            }
        }
        return specification;
    }
}
