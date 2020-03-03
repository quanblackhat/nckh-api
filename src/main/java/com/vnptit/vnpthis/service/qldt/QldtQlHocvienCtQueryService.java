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

import com.vnptit.vnpthis.domain.qldt.QldtQlHocvienCt;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtQlHocvienCtRepository;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCtCriteria;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCtDTO;
import com.vnptit.vnpthis.service.mapper.QldtQlHocvienCtMapper;

/**
 * Service for executing complex queries for {@link QldtQlHocvienCt} entities in the database.
 * The main input is a {@link QldtQlHocvienCtCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtQlHocvienCtDTO} or a {@link Page} of {@link QldtQlHocvienCtDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtQlHocvienCtQueryService extends QueryService<QldtQlHocvienCt> {

    private final Logger log = LoggerFactory.getLogger(QldtQlHocvienCtQueryService.class);

    private final QldtQlHocvienCtRepository qldtQlHocvienCtRepository;

    private final QldtQlHocvienCtMapper qldtQlHocvienCtMapper;

    public QldtQlHocvienCtQueryService(QldtQlHocvienCtRepository qldtQlHocvienCtRepository, QldtQlHocvienCtMapper qldtQlHocvienCtMapper) {
        this.qldtQlHocvienCtRepository = qldtQlHocvienCtRepository;
        this.qldtQlHocvienCtMapper = qldtQlHocvienCtMapper;
    }

    /**
     * Return a {@link List} of {@link QldtQlHocvienCtDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtQlHocvienCtDTO> findByCriteria(QldtQlHocvienCtCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtQlHocvienCt> specification = createSpecification(criteria);
        return qldtQlHocvienCtMapper.toDto(qldtQlHocvienCtRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtQlHocvienCtDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtQlHocvienCtDTO> findByCriteria(QldtQlHocvienCtCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtQlHocvienCt> specification = createSpecification(criteria);
        return qldtQlHocvienCtRepository.findAll(specification, page)
            .map(qldtQlHocvienCtMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtQlHocvienCtCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtQlHocvienCt> specification = createSpecification(criteria);
        return qldtQlHocvienCtRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtQlHocvienCtCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtQlHocvienCt> createSpecification(QldtQlHocvienCtCriteria criteria) {
        Specification<QldtQlHocvienCt> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtQlHocvienCt_.id));
            }
            if (criteria.getDiemdanh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiemdanh(), QldtQlHocvienCt_.diemdanh));
            }
            if (criteria.getDiemthi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiemthi(), QldtQlHocvienCt_.diemthi));
            }
            if (criteria.getDanhgia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDanhgia(), QldtQlHocvienCt_.danhgia));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtQlHocvienCt_.sudung));
            }
            if (criteria.getQldtDaotaoCtId() != null) {
                specification = specification.and(buildSpecification(criteria.getQldtDaotaoCtId(),
                    root -> root.join(QldtQlHocvienCt_.qldtDaotaoCt, JoinType.LEFT).get(QldtDaotaoCt_.id)));
            }
        }
        return specification;
    }
}
