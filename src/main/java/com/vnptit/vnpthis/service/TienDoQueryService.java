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

import com.vnptit.vnpthis.domain.nckh.TienDo;
import com.vnptit.vnpthis.domain.*; // for static metamodels
import com.vnptit.vnpthis.repository.TienDoRepository;
import com.vnptit.vnpthis.service.dto.TienDoCriteria;
import com.vnptit.vnpthis.service.dto.TienDoDTO;
import com.vnptit.vnpthis.service.mapper.TienDoMapper;

/**
 * Service for executing complex queries for {@link TienDo} entities in the database.
 * The main input is a {@link TienDoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TienDoDTO} or a {@link Page} of {@link TienDoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TienDoQueryService extends QueryService<TienDo> {

    private final Logger log = LoggerFactory.getLogger(TienDoQueryService.class);

    private final TienDoRepository tienDoRepository;

    private final TienDoMapper tienDoMapper;

    public TienDoQueryService(TienDoRepository tienDoRepository, TienDoMapper tienDoMapper) {
        this.tienDoRepository = tienDoRepository;
        this.tienDoMapper = tienDoMapper;
    }

    /**
     * Return a {@link List} of {@link TienDoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TienDoDTO> findByCriteria(TienDoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TienDo> specification = createSpecification(criteria);
        return tienDoMapper.toDto(tienDoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TienDoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TienDoDTO> findByCriteria(TienDoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TienDo> specification = createSpecification(criteria);
        return tienDoRepository.findAll(specification, page)
            .map(tienDoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TienDoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TienDo> specification = createSpecification(criteria);
        return tienDoRepository.count(specification);
    }

    /**
     * Function to convert {@link TienDoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TienDo> createSpecification(TienDoCriteria criteria) {
        Specification<TienDo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TienDo_.id));
            }
            if (criteria.getTyleHoanthanh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTyleHoanthanh(), TienDo_.tyleHoanthanh));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), TienDo_.mota));
            }
            if (criteria.getNgayCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCn(), TienDo_.ngayCn));
            }
            if (criteria.getNguoiCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiCn(), TienDo_.nguoiCn));
            }
            if (criteria.getUpFileId() != null) {
                specification = specification.and(buildSpecification(criteria.getUpFileId(),
                    root -> root.join(TienDo_.upFiles, JoinType.LEFT).get(UpFile_.id)));
            }
            if (criteria.getDeTaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeTaiId(),
                    root -> root.join(TienDo_.deTai, JoinType.LEFT).get(DeTai_.id)));
            }
        }
        return specification;
    }
}
