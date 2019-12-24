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

import com.vnpt.nckh.domain.NckhUpfile;
import com.vnpt.nckh.domain.*; // for static metamodels
import com.vnpt.nckh.repository.NckhUpfileRepository;
import com.vnpt.nckh.service.dto.NckhUpfileCriteria;

/**
 * Service for executing complex queries for {@link NckhUpfile} entities in the database.
 * The main input is a {@link NckhUpfileCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NckhUpfile} or a {@link Page} of {@link NckhUpfile} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NckhUpfileQueryService extends QueryService<NckhUpfile> {

    private final Logger log = LoggerFactory.getLogger(NckhUpfileQueryService.class);

    private final NckhUpfileRepository nckhUpfileRepository;

    public NckhUpfileQueryService(NckhUpfileRepository nckhUpfileRepository) {
        this.nckhUpfileRepository = nckhUpfileRepository;
    }

    /**
     * Return a {@link List} of {@link NckhUpfile} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NckhUpfile> findByCriteria(NckhUpfileCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NckhUpfile> specification = createSpecification(criteria);
        return nckhUpfileRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link NckhUpfile} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhUpfile> findByCriteria(NckhUpfileCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NckhUpfile> specification = createSpecification(criteria);
        return nckhUpfileRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NckhUpfileCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NckhUpfile> specification = createSpecification(criteria);
        return nckhUpfileRepository.count(specification);
    }

    /**
     * Function to convert {@link NckhUpfileCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NckhUpfile> createSpecification(NckhUpfileCriteria criteria) {
        Specification<NckhUpfile> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NckhUpfile_.id));
            }
            if (criteria.getMoTa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoTa(), NckhUpfile_.moTa));
            }
            if (criteria.getNguoiCN() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiCN(), NckhUpfile_.nguoiCN));
            }
            if (criteria.getNgayCN() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCN(), NckhUpfile_.ngayCN));
            }
            if (criteria.getNckhDetaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNckhDetaiId(),
                    root -> root.join(NckhUpfile_.nckhDetai, JoinType.LEFT).get(NckhDetai_.id)));
            }
        }
        return specification;
    }
}
