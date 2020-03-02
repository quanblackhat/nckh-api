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

import com.vnptit.vnpthis.domain.nckh.LoaiDanhMuc;
import com.vnptit.vnpthis.domain.*; // for static metamodels
import com.vnptit.vnpthis.repository.LoaiDanhMucRepository;
import com.vnptit.vnpthis.service.dto.LoaiDanhMucCriteria;
import com.vnptit.vnpthis.service.dto.LoaiDanhMucDTO;
import com.vnptit.vnpthis.service.mapper.LoaiDanhMucMapper;

/**
 * Service for executing complex queries for {@link LoaiDanhMuc} entities in the database.
 * The main input is a {@link LoaiDanhMucCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LoaiDanhMucDTO} or a {@link Page} of {@link LoaiDanhMucDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LoaiDanhMucQueryService extends QueryService<LoaiDanhMuc> {

    private final Logger log = LoggerFactory.getLogger(LoaiDanhMucQueryService.class);

    private final LoaiDanhMucRepository loaiDanhMucRepository;

    private final LoaiDanhMucMapper loaiDanhMucMapper;

    public LoaiDanhMucQueryService(LoaiDanhMucRepository loaiDanhMucRepository, LoaiDanhMucMapper loaiDanhMucMapper) {
        this.loaiDanhMucRepository = loaiDanhMucRepository;
        this.loaiDanhMucMapper = loaiDanhMucMapper;
    }

    /**
     * Return a {@link List} of {@link LoaiDanhMucDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LoaiDanhMucDTO> findByCriteria(LoaiDanhMucCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LoaiDanhMuc> specification = createSpecification(criteria);
        return loaiDanhMucMapper.toDto(loaiDanhMucRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LoaiDanhMucDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LoaiDanhMucDTO> findByCriteria(LoaiDanhMucCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LoaiDanhMuc> specification = createSpecification(criteria);
        return loaiDanhMucRepository.findAll(specification, page)
            .map(loaiDanhMucMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LoaiDanhMucCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LoaiDanhMuc> specification = createSpecification(criteria);
        return loaiDanhMucRepository.count(specification);
    }

    /**
     * Function to convert {@link LoaiDanhMucCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LoaiDanhMuc> createSpecification(LoaiDanhMucCriteria criteria) {
        Specification<LoaiDanhMuc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), LoaiDanhMuc_.id));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), LoaiDanhMuc_.ten));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), LoaiDanhMuc_.sudung));
            }
            if (criteria.getDanhMucId() != null) {
                specification = specification.and(buildSpecification(criteria.getDanhMucId(),
                    root -> root.join(LoaiDanhMuc_.danhMucs, JoinType.LEFT).get(DanhMuc_.id)));
            }
        }
        return specification;
    }
}
