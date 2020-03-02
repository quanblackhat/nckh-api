package com.vnptit.vnpthis.service;

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

import com.vnptit.vnpthis.domain.nckh.ChuyenMuc;
import com.vnptit.vnpthis.domain.nckh.*;
import com.vnptit.vnpthis.repository.ChuyenMucRepository;
import com.vnptit.vnpthis.service.dto.ChuyenMucCriteria;
import com.vnptit.vnpthis.service.dto.ChuyenMucDTO;
import com.vnptit.vnpthis.service.mapper.ChuyenMucMapper;

/**
 * Service for executing complex queries for {@link ChuyenMuc} entities in the database.
 * The main input is a {@link ChuyenMucCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ChuyenMucDTO} or a {@link Page} of {@link ChuyenMucDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ChuyenMucQueryService extends QueryService<ChuyenMuc> {

    private final Logger log = LoggerFactory.getLogger(ChuyenMucQueryService.class);

    private final ChuyenMucRepository chuyenMucRepository;

    private final ChuyenMucMapper chuyenMucMapper;

    public ChuyenMucQueryService(ChuyenMucRepository chuyenMucRepository, ChuyenMucMapper chuyenMucMapper) {
        this.chuyenMucRepository = chuyenMucRepository;
        this.chuyenMucMapper = chuyenMucMapper;
    }

    /**
     * Return a {@link List} of {@link ChuyenMucDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ChuyenMucDTO> findByCriteria(ChuyenMucCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ChuyenMuc> specification = createSpecification(criteria);
        return chuyenMucMapper.toDto(chuyenMucRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ChuyenMucDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ChuyenMucDTO> findByCriteria(ChuyenMucCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ChuyenMuc> specification = createSpecification(criteria);
        return chuyenMucRepository.findAll(specification, page)
            .map(chuyenMucMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ChuyenMucCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ChuyenMuc> specification = createSpecification(criteria);
        return chuyenMucRepository.count(specification);
    }

    /**
     * Function to convert {@link ChuyenMucCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ChuyenMuc> createSpecification(ChuyenMucCriteria criteria) {
        Specification<ChuyenMuc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ChuyenMuc_.id));
            }
            if (criteria.getNgaytao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaytao(), ChuyenMuc_.ngaytao));
            }
            if (criteria.getSott() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSott(), ChuyenMuc_.sott));
            }
            if (criteria.getTenchuyenmuc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenchuyenmuc(), ChuyenMuc_.tenchuyenmuc));
            }
            if (criteria.getDeTaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeTaiId(),
                    root -> root.join(ChuyenMuc_.deTais, JoinType.LEFT).get(DeTai_.id)));
            }
        }
        return specification;
    }
}
