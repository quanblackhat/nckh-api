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

import com.vnptit.vnpthis.domain.nckh.DanhMuc;
import com.vnptit.vnpthis.domain.nckh.*;
import com.vnptit.vnpthis.repository.DanhMucRepository;
import com.vnptit.vnpthis.service.dto.DanhMucCriteria;
import com.vnptit.vnpthis.service.dto.DanhMucDTO;
import com.vnptit.vnpthis.service.mapper.DanhMucMapper;

/**
 * Service for executing complex queries for {@link DanhMuc} entities in the database.
 * The main input is a {@link DanhMucCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DanhMucDTO} or a {@link Page} of {@link DanhMucDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucQueryService extends QueryService<DanhMuc> {

    private final Logger log = LoggerFactory.getLogger(DanhMucQueryService.class);

    private final DanhMucRepository danhMucRepository;

    private final DanhMucMapper danhMucMapper;

    public DanhMucQueryService(DanhMucRepository danhMucRepository, DanhMucMapper danhMucMapper) {
        this.danhMucRepository = danhMucRepository;
        this.danhMucMapper = danhMucMapper;
    }

    /**
     * Return a {@link List} of {@link DanhMucDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DanhMucDTO> findByCriteria(DanhMucCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DanhMuc> specification = createSpecification(criteria);
        return danhMucMapper.toDto(danhMucRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DanhMucDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucDTO> findByCriteria(DanhMucCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMuc> specification = createSpecification(criteria);
        return danhMucRepository.findAll(specification, page)
            .map(danhMucMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DanhMuc> specification = createSpecification(criteria);
        return danhMucRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMuc> createSpecification(DanhMucCriteria criteria) {
        Specification<DanhMuc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DanhMuc_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), DanhMuc_.ma));
            }
            if (criteria.getNgaytao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaytao(), DanhMuc_.ngaytao));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), DanhMuc_.sudung));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), DanhMuc_.ten));
            }
            if (criteria.getThutu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThutu(), DanhMuc_.thutu));
            }
            if (criteria.getLoaiDanhMucId() != null) {
                specification = specification.and(buildSpecification(criteria.getLoaiDanhMucId(),
                    root -> root.join(DanhMuc_.loaiDanhMuc, JoinType.LEFT).get(LoaiDanhMuc_.id)));
            }
        }
        return specification;
    }
}
