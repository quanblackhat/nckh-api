package com.vnptit.vnpthis.service.qldt;

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

import com.vnptit.vnpthis.domain.qldt.QldtTochucCap;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtTochucCapRepository;
import com.vnptit.vnpthis.service.dto.QldtTochucCapCriteria;
import com.vnptit.vnpthis.service.dto.QldtTochucCapDTO;
import com.vnptit.vnpthis.service.mapper.QldtTochucCapMapper;

/**
 * Service for executing complex queries for {@link QldtTochucCap} entities in the database.
 * The main input is a {@link QldtTochucCapCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtTochucCapDTO} or a {@link Page} of {@link QldtTochucCapDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtTochucCapQueryService extends QueryService<QldtTochucCap> {

    private final Logger log = LoggerFactory.getLogger(QldtTochucCapQueryService.class);

    private final QldtTochucCapRepository qldtTochucCapRepository;

    private final QldtTochucCapMapper qldtTochucCapMapper;

    public QldtTochucCapQueryService(QldtTochucCapRepository qldtTochucCapRepository, QldtTochucCapMapper qldtTochucCapMapper) {
        this.qldtTochucCapRepository = qldtTochucCapRepository;
        this.qldtTochucCapMapper = qldtTochucCapMapper;
    }

    /**
     * Return a {@link List} of {@link QldtTochucCapDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtTochucCapDTO> findByCriteria(QldtTochucCapCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtTochucCap> specification = createSpecification(criteria);
        return qldtTochucCapMapper.toDto(qldtTochucCapRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtTochucCapDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtTochucCapDTO> findByCriteria(QldtTochucCapCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtTochucCap> specification = createSpecification(criteria);
        return qldtTochucCapRepository.findAll(specification, page)
            .map(qldtTochucCapMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtTochucCapCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtTochucCap> specification = createSpecification(criteria);
        return qldtTochucCapRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtTochucCapCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtTochucCap> createSpecification(QldtTochucCapCriteria criteria) {
        Specification<QldtTochucCap> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtTochucCap_.id));
            }
            if (criteria.getMatochuc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatochuc(), QldtTochucCap_.matochuc));
            }
            if (criteria.getTentochuc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTentochuc(), QldtTochucCap_.tentochuc));
            }
            if (criteria.getNoidung() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoidung(), QldtTochucCap_.noidung));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtTochucCap_.sudung));
            }
            if (criteria.getDmChungchiId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmChungchiId(),
                    root -> root.join(QldtTochucCap_.dmChungchis, JoinType.LEFT).get(QldtDmChungchi_.id)));
            }
        }
        return specification;
    }
}
