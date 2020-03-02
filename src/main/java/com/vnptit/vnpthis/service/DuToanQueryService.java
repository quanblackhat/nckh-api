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

import com.vnptit.vnpthis.domain.DuToan;
import com.vnptit.vnpthis.domain.*; // for static metamodels
import com.vnptit.vnpthis.repository.DuToanRepository;
import com.vnptit.vnpthis.service.dto.DuToanCriteria;
import com.vnptit.vnpthis.service.dto.DuToanDTO;
import com.vnptit.vnpthis.service.mapper.DuToanMapper;

/**
 * Service for executing complex queries for {@link DuToan} entities in the database.
 * The main input is a {@link DuToanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DuToanDTO} or a {@link Page} of {@link DuToanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DuToanQueryService extends QueryService<DuToan> {

    private final Logger log = LoggerFactory.getLogger(DuToanQueryService.class);

    private final DuToanRepository duToanRepository;

    private final DuToanMapper duToanMapper;

    public DuToanQueryService(DuToanRepository duToanRepository, DuToanMapper duToanMapper) {
        this.duToanRepository = duToanRepository;
        this.duToanMapper = duToanMapper;
    }

    /**
     * Return a {@link List} of {@link DuToanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DuToanDTO> findByCriteria(DuToanCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DuToan> specification = createSpecification(criteria);
        return duToanMapper.toDto(duToanRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DuToanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DuToanDTO> findByCriteria(DuToanCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DuToan> specification = createSpecification(criteria);
        return duToanRepository.findAll(specification, page)
            .map(duToanMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DuToanCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DuToan> specification = createSpecification(criteria);
        return duToanRepository.count(specification);
    }

    /**
     * Function to convert {@link DuToanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DuToan> createSpecification(DuToanCriteria criteria) {
        Specification<DuToan> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DuToan_.id));
            }
            if (criteria.getTienDukien() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTienDukien(), DuToan_.tienDukien));
            }
            if (criteria.getTienHoanthanh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTienHoanthanh(), DuToan_.tienHoanthanh));
            }
            if (criteria.getGhichu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhichu(), DuToan_.ghichu));
            }
            if (criteria.getNgayCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCn(), DuToan_.ngayCn));
            }
            if (criteria.getNguoiCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiCn(), DuToan_.nguoiCn));
            }
            if (criteria.getDeTaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeTaiId(),
                    root -> root.join(DuToan_.deTai, JoinType.LEFT).get(DeTai_.id)));
            }
        }
        return specification;
    }
}
