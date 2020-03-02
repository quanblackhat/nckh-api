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

import com.vnptit.vnpthis.domain.qldt.QldtChungChi;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtChungChiRepository;
import com.vnptit.vnpthis.service.dto.QldtChungChiCriteria;
import com.vnptit.vnpthis.service.dto.QldtChungChiDTO;
import com.vnptit.vnpthis.service.mapper.QldtChungChiMapper;

/**
 * Service for executing complex queries for {@link QldtChungChi} entities in the database.
 * The main input is a {@link QldtChungChiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtChungChiDTO} or a {@link Page} of {@link QldtChungChiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtChungChiQueryService extends QueryService<QldtChungChi> {

    private final Logger log = LoggerFactory.getLogger(QldtChungChiQueryService.class);

    private final QldtChungChiRepository qldtChungChiRepository;

    private final QldtChungChiMapper qldtChungChiMapper;

    public QldtChungChiQueryService(QldtChungChiRepository qldtChungChiRepository, QldtChungChiMapper qldtChungChiMapper) {
        this.qldtChungChiRepository = qldtChungChiRepository;
        this.qldtChungChiMapper = qldtChungChiMapper;
    }

    /**
     * Return a {@link List} of {@link QldtChungChiDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtChungChiDTO> findByCriteria(QldtChungChiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtChungChi> specification = createSpecification(criteria);
        return qldtChungChiMapper.toDto(qldtChungChiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtChungChiDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtChungChiDTO> findByCriteria(QldtChungChiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtChungChi> specification = createSpecification(criteria);
        return qldtChungChiRepository.findAll(specification, page)
            .map(qldtChungChiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtChungChiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtChungChi> specification = createSpecification(criteria);
        return qldtChungChiRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtChungChiCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtChungChi> createSpecification(QldtChungChiCriteria criteria) {
        Specification<QldtChungChi> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtChungChi_.id));
            }
            if (criteria.getHan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHan(), QldtChungChi_.han));
            }
            if (criteria.getNgaycap() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaycap(), QldtChungChi_.ngaycap));
            }
            if (criteria.getNgayhethan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayhethan(), QldtChungChi_.ngayhethan));
            }
            if (criteria.getUrlchungchi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrlchungchi(), QldtChungChi_.urlchungchi));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtChungChi_.sudung));
            }
            if (criteria.getQldtDmChungchiId() != null) {
                specification = specification.and(buildSpecification(criteria.getQldtDmChungchiId(),
                    root -> root.join(QldtChungChi_.qldtDmChungchi, JoinType.LEFT).get(QldtDmChungchi_.id)));
            }
        }
        return specification;
    }
}
