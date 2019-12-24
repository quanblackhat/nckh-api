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

import com.vnpt.nckh.domain.NckhTiendo;
import com.vnpt.nckh.domain.*; // for static metamodels
import com.vnpt.nckh.repository.NckhTiendoRepository;
import com.vnpt.nckh.service.dto.NckhTiendoCriteria;

/**
 * Service for executing complex queries for {@link NckhTiendo} entities in the database.
 * The main input is a {@link NckhTiendoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NckhTiendo} or a {@link Page} of {@link NckhTiendo} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NckhTiendoQueryService extends QueryService<NckhTiendo> {

    private final Logger log = LoggerFactory.getLogger(NckhTiendoQueryService.class);

    private final NckhTiendoRepository nckhTiendoRepository;

    public NckhTiendoQueryService(NckhTiendoRepository nckhTiendoRepository) {
        this.nckhTiendoRepository = nckhTiendoRepository;
    }

    /**
     * Return a {@link List} of {@link NckhTiendo} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NckhTiendo> findByCriteria(NckhTiendoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NckhTiendo> specification = createSpecification(criteria);
        return nckhTiendoRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link NckhTiendo} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhTiendo> findByCriteria(NckhTiendoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NckhTiendo> specification = createSpecification(criteria);
        return nckhTiendoRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NckhTiendoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NckhTiendo> specification = createSpecification(criteria);
        return nckhTiendoRepository.count(specification);
    }

    /**
     * Function to convert {@link NckhTiendoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NckhTiendo> createSpecification(NckhTiendoCriteria criteria) {
        Specification<NckhTiendo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NckhTiendo_.id));
            }
            if (criteria.getTienDoHoanThanh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTienDoHoanThanh(), NckhTiendo_.tienDoHoanThanh));
            }
            if (criteria.getMoTa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoTa(), NckhTiendo_.moTa));
            }
            if (criteria.getNgayCapNhat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCapNhat(), NckhTiendo_.ngayCapNhat));
            }
            if (criteria.getNckhDetaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNckhDetaiId(),
                    root -> root.join(NckhTiendo_.nckhDetai, JoinType.LEFT).get(NckhDetai_.id)));
            }
        }
        return specification;
    }
}
