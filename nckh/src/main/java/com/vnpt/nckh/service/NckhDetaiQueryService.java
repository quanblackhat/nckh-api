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

import com.vnpt.nckh.domain.NckhDetai;
import com.vnpt.nckh.domain.*; // for static metamodels
import com.vnpt.nckh.repository.NckhDetaiRepository;
import com.vnpt.nckh.service.dto.NckhDetaiCriteria;

/**
 * Service for executing complex queries for {@link NckhDetai} entities in the database.
 * The main input is a {@link NckhDetaiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NckhDetai} or a {@link Page} of {@link NckhDetai} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NckhDetaiQueryService extends QueryService<NckhDetai> {

    private final Logger log = LoggerFactory.getLogger(NckhDetaiQueryService.class);

    private final NckhDetaiRepository nckhDetaiRepository;

    public NckhDetaiQueryService(NckhDetaiRepository nckhDetaiRepository) {
        this.nckhDetaiRepository = nckhDetaiRepository;
    }

    /**
     * Return a {@link List} of {@link NckhDetai} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NckhDetai> findByCriteria(NckhDetaiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NckhDetai> specification = createSpecification(criteria);
        return nckhDetaiRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link NckhDetai} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhDetai> findByCriteria(NckhDetaiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NckhDetai> specification = createSpecification(criteria);
        return nckhDetaiRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NckhDetaiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NckhDetai> specification = createSpecification(criteria);
        return nckhDetaiRepository.count(specification);
    }

    /**
     * Function to convert {@link NckhDetaiCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NckhDetai> createSpecification(NckhDetaiCriteria criteria) {
        Specification<NckhDetai> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NckhDetai_.id));
            }
            if (criteria.getMaDetai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaDetai(), NckhDetai_.maDetai));
            }
            if (criteria.getTenDetai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDetai(), NckhDetai_.tenDetai));
            }
            if (criteria.getMuctieu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMuctieu(), NckhDetai_.muctieu));
            }
            if (criteria.getNgayBdDuKien() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBdDuKien(), NckhDetai_.ngayBdDuKien));
            }
            if (criteria.getNgayKtDuKien() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKtDuKien(), NckhDetai_.ngayKtDuKien));
            }
            if (criteria.getKinhPhiDuKien() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKinhPhiDuKien(), NckhDetai_.kinhPhiDuKien));
            }
            if (criteria.getNoiDungTongQuan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiDungTongQuan(), NckhDetai_.noiDungTongQuan));
            }
            if (criteria.getKinhPhiHoanThanh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKinhPhiHoanThanh(), NckhDetai_.kinhPhiHoanThanh));
            }
            if (criteria.getTongDiem() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTongDiem(), NckhDetai_.tongDiem));
            }
            if (criteria.getNckhNhanSuId() != null) {
                specification = specification.and(buildSpecification(criteria.getNckhNhanSuId(),
                    root -> root.join(NckhDetai_.nckhNhanSus, JoinType.LEFT).get(NckhNhanSu_.id)));
            }
            if (criteria.getNckhTiendoId() != null) {
                specification = specification.and(buildSpecification(criteria.getNckhTiendoId(),
                    root -> root.join(NckhDetai_.nckhTiendos, JoinType.LEFT).get(NckhTiendo_.id)));
            }
            if (criteria.getNckhUpfileId() != null) {
                specification = specification.and(buildSpecification(criteria.getNckhUpfileId(),
                    root -> root.join(NckhDetai_.nckhUpfiles, JoinType.LEFT).get(NckhUpfile_.id)));
            }
            if (criteria.getNckhDanhmucId() != null) {
                specification = specification.and(buildSpecification(criteria.getNckhDanhmucId(),
                    root -> root.join(NckhDetai_.nckhDanhmuc, JoinType.LEFT).get(NckhDanhmuc_.id)));
            }
        }
        return specification;
    }
}
