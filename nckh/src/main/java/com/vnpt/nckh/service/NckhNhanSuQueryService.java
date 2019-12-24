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

import com.vnpt.nckh.domain.NckhNhanSu;
import com.vnpt.nckh.domain.*; // for static metamodels
import com.vnpt.nckh.repository.NckhNhanSuRepository;
import com.vnpt.nckh.service.dto.NckhNhanSuCriteria;

/**
 * Service for executing complex queries for {@link NckhNhanSu} entities in the database.
 * The main input is a {@link NckhNhanSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NckhNhanSu} or a {@link Page} of {@link NckhNhanSu} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NckhNhanSuQueryService extends QueryService<NckhNhanSu> {

    private final Logger log = LoggerFactory.getLogger(NckhNhanSuQueryService.class);

    private final NckhNhanSuRepository nckhNhanSuRepository;

    public NckhNhanSuQueryService(NckhNhanSuRepository nckhNhanSuRepository) {
        this.nckhNhanSuRepository = nckhNhanSuRepository;
    }

    /**
     * Return a {@link List} of {@link NckhNhanSu} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NckhNhanSu> findByCriteria(NckhNhanSuCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NckhNhanSu> specification = createSpecification(criteria);
        return nckhNhanSuRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link NckhNhanSu} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhNhanSu> findByCriteria(NckhNhanSuCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NckhNhanSu> specification = createSpecification(criteria);
        return nckhNhanSuRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NckhNhanSuCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NckhNhanSu> specification = createSpecification(criteria);
        return nckhNhanSuRepository.count(specification);
    }

    /**
     * Function to convert {@link NckhNhanSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NckhNhanSu> createSpecification(NckhNhanSuCriteria criteria) {
        Specification<NckhNhanSu> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NckhNhanSu_.id));
            }
            if (criteria.getOrgOfficerId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrgOfficerId(),
                    root -> root.join(NckhNhanSu_.orgOfficer, JoinType.LEFT).get(OrgOfficer_.id)));
            }
            if (criteria.getNckhDetaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNckhDetaiId(),
                    root -> root.join(NckhNhanSu_.nckhDetai, JoinType.LEFT).get(NckhDetai_.id)));
            }
        }
        return specification;
    }
}
