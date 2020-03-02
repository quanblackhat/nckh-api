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

import com.vnptit.vnpthis.domain.qldt.QldtDmChungchi;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtDmChungchiRepository;
import com.vnptit.vnpthis.service.dto.QldtDmChungchiCriteria;
import com.vnptit.vnpthis.service.dto.QldtDmChungchiDTO;
import com.vnptit.vnpthis.service.mapper.QldtDmChungchiMapper;

/**
 * Service for executing complex queries for {@link QldtDmChungchi} entities in the database.
 * The main input is a {@link QldtDmChungchiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtDmChungchiDTO} or a {@link Page} of {@link QldtDmChungchiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtDmChungchiQueryService extends QueryService<QldtDmChungchi> {

    private final Logger log = LoggerFactory.getLogger(QldtDmChungchiQueryService.class);

    private final QldtDmChungchiRepository qldtDmChungchiRepository;

    private final QldtDmChungchiMapper qldtDmChungchiMapper;

    public QldtDmChungchiQueryService(QldtDmChungchiRepository qldtDmChungchiRepository, QldtDmChungchiMapper qldtDmChungchiMapper) {
        this.qldtDmChungchiRepository = qldtDmChungchiRepository;
        this.qldtDmChungchiMapper = qldtDmChungchiMapper;
    }

    /**
     * Return a {@link List} of {@link QldtDmChungchiDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtDmChungchiDTO> findByCriteria(QldtDmChungchiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtDmChungchi> specification = createSpecification(criteria);
        return qldtDmChungchiMapper.toDto(qldtDmChungchiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtDmChungchiDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtDmChungchiDTO> findByCriteria(QldtDmChungchiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtDmChungchi> specification = createSpecification(criteria);
        return qldtDmChungchiRepository.findAll(specification, page)
            .map(qldtDmChungchiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtDmChungchiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtDmChungchi> specification = createSpecification(criteria);
        return qldtDmChungchiRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtDmChungchiCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtDmChungchi> createSpecification(QldtDmChungchiCriteria criteria) {
        Specification<QldtDmChungchi> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtDmChungchi_.id));
            }
            if (criteria.getMachungchi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMachungchi(), QldtDmChungchi_.machungchi));
            }
            if (criteria.getTenchungchi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenchungchi(), QldtDmChungchi_.tenchungchi));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), QldtDmChungchi_.mota));
            }
            if (criteria.getTrangthai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangthai(), QldtDmChungchi_.trangthai));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtDmChungchi_.sudung));
            }
            if (criteria.getChungChiId() != null) {
                specification = specification.and(buildSpecification(criteria.getChungChiId(),
                    root -> root.join(QldtDmChungchi_.chungChis, JoinType.LEFT).get(QldtChungChi_.id)));
            }
            if (criteria.getQldtTochucCapId() != null) {
                specification = specification.and(buildSpecification(criteria.getQldtTochucCapId(),
                    root -> root.join(QldtDmChungchi_.qldtTochucCap, JoinType.LEFT).get(QldtTochucCap_.id)));
            }
        }
        return specification;
    }
}
