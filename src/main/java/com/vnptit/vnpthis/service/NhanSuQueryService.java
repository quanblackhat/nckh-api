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

import com.vnptit.vnpthis.domain.nckh.NhanSu;
import com.vnptit.vnpthis.domain.nckh.*; // for static metamodels
import com.vnptit.vnpthis.repository.NhanSuRepository;
import com.vnptit.vnpthis.service.dto.NhanSuCriteria;
import com.vnptit.vnpthis.service.dto.NhanSuDTO;
import com.vnptit.vnpthis.service.mapper.NhanSuMapper;

/**
 * Service for executing complex queries for {@link NhanSu} entities in the database.
 * The main input is a {@link NhanSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NhanSuDTO} or a {@link Page} of {@link NhanSuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NhanSuQueryService extends QueryService<NhanSu> {

    private final Logger log = LoggerFactory.getLogger(NhanSuQueryService.class);

    private final NhanSuRepository nhanSuRepository;

    private final NhanSuMapper nhanSuMapper;

    public NhanSuQueryService(NhanSuRepository nhanSuRepository, NhanSuMapper nhanSuMapper) {
        this.nhanSuRepository = nhanSuRepository;
        this.nhanSuMapper = nhanSuMapper;
    }

    /**
     * Return a {@link List} of {@link NhanSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NhanSuDTO> findByCriteria(NhanSuCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NhanSu> specification = createSpecification(criteria);
        return nhanSuMapper.toDto(nhanSuRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NhanSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NhanSuDTO> findByCriteria(NhanSuCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NhanSu> specification = createSpecification(criteria);
        return nhanSuRepository.findAll(specification, page)
            .map(nhanSuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NhanSuCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NhanSu> specification = createSpecification(criteria);
        return nhanSuRepository.count(specification);
    }

    /**
     * Function to convert {@link NhanSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NhanSu> createSpecification(NhanSuCriteria criteria) {
        Specification<NhanSu> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NhanSu_.id));
            }
            if (criteria.getChunhiemdetai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChunhiemdetai(), NhanSu_.chunhiemdetai));
            }
            if (criteria.getNgayCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCn(), NhanSu_.ngayCn));
            }
            if (criteria.getNguoiCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiCn(), NhanSu_.nguoiCn));
            }
            if (criteria.getDeTaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeTaiId(),
                    root -> root.join(NhanSu_.deTai, JoinType.LEFT).get(DeTai_.id)));
            }
        }
        return specification;
    }
}
