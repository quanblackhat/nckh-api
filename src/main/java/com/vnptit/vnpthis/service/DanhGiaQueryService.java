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

import com.vnptit.vnpthis.domain.nckh.DanhGia;
import com.vnptit.vnpthis.domain.*; // for static metamodels
import com.vnptit.vnpthis.repository.DanhGiaRepository;
import com.vnptit.vnpthis.service.dto.DanhGiaCriteria;
import com.vnptit.vnpthis.service.dto.DanhGiaDTO;
import com.vnptit.vnpthis.service.mapper.DanhGiaMapper;

/**
 * Service for executing complex queries for {@link DanhGia} entities in the database.
 * The main input is a {@link DanhGiaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DanhGiaDTO} or a {@link Page} of {@link DanhGiaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhGiaQueryService extends QueryService<DanhGia> {

    private final Logger log = LoggerFactory.getLogger(DanhGiaQueryService.class);

    private final DanhGiaRepository danhGiaRepository;

    private final DanhGiaMapper danhGiaMapper;

    public DanhGiaQueryService(DanhGiaRepository danhGiaRepository, DanhGiaMapper danhGiaMapper) {
        this.danhGiaRepository = danhGiaRepository;
        this.danhGiaMapper = danhGiaMapper;
    }

    /**
     * Return a {@link List} of {@link DanhGiaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DanhGiaDTO> findByCriteria(DanhGiaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DanhGia> specification = createSpecification(criteria);
        return danhGiaMapper.toDto(danhGiaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DanhGiaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhGiaDTO> findByCriteria(DanhGiaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhGia> specification = createSpecification(criteria);
        return danhGiaRepository.findAll(specification, page)
            .map(danhGiaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhGiaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DanhGia> specification = createSpecification(criteria);
        return danhGiaRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhGiaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhGia> createSpecification(DanhGiaCriteria criteria) {
        Specification<DanhGia> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DanhGia_.id));
            }
            if (criteria.getDiemdanhgia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiemdanhgia(), DanhGia_.diemdanhgia));
            }
            if (criteria.getGhichu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhichu(), DanhGia_.ghichu));
            }
            if (criteria.getNgayCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCn(), DanhGia_.ngayCn));
            }
            if (criteria.getNguoiCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiCn(), DanhGia_.nguoiCn));
            }
            if (criteria.getDeTaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeTaiId(),
                    root -> root.join(DanhGia_.deTai, JoinType.LEFT).get(DeTai_.id)));
            }
        }
        return specification;
    }
}
