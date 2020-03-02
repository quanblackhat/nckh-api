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

import com.vnptit.vnpthis.domain.qldt.QldtQlHocvien;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtQlHocvienRepository;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCriteria;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienDTO;
import com.vnptit.vnpthis.service.mapper.QldtQlHocvienMapper;

/**
 * Service for executing complex queries for {@link QldtQlHocvien} entities in the database.
 * The main input is a {@link QldtQlHocvienCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtQlHocvienDTO} or a {@link Page} of {@link QldtQlHocvienDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtQlHocvienQueryService extends QueryService<QldtQlHocvien> {

    private final Logger log = LoggerFactory.getLogger(QldtQlHocvienQueryService.class);

    private final QldtQlHocvienRepository qldtQlHocvienRepository;

    private final QldtQlHocvienMapper qldtQlHocvienMapper;

    public QldtQlHocvienQueryService(QldtQlHocvienRepository qldtQlHocvienRepository, QldtQlHocvienMapper qldtQlHocvienMapper) {
        this.qldtQlHocvienRepository = qldtQlHocvienRepository;
        this.qldtQlHocvienMapper = qldtQlHocvienMapper;
    }

    /**
     * Return a {@link List} of {@link QldtQlHocvienDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtQlHocvienDTO> findByCriteria(QldtQlHocvienCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtQlHocvien> specification = createSpecification(criteria);
        return qldtQlHocvienMapper.toDto(qldtQlHocvienRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtQlHocvienDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtQlHocvienDTO> findByCriteria(QldtQlHocvienCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtQlHocvien> specification = createSpecification(criteria);
        return qldtQlHocvienRepository.findAll(specification, page)
            .map(qldtQlHocvienMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtQlHocvienCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtQlHocvien> specification = createSpecification(criteria);
        return qldtQlHocvienRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtQlHocvienCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtQlHocvien> createSpecification(QldtQlHocvienCriteria criteria) {
        Specification<QldtQlHocvien> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtQlHocvien_.id));
            }
            if (criteria.getDiemdanh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiemdanh(), QldtQlHocvien_.diemdanh));
            }
            if (criteria.getDiem() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiem(), QldtQlHocvien_.diem));
            }
            if (criteria.getDanhgia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDanhgia(), QldtQlHocvien_.danhgia));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtQlHocvien_.sudung));
            }
            if (criteria.getTrangthaithanhtoan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangthaithanhtoan(), QldtQlHocvien_.trangthaithanhtoan));
            }
            if (criteria.getNgaythanhtoan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaythanhtoan(), QldtQlHocvien_.ngaythanhtoan));
            }
            if (criteria.getMathanhtoan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMathanhtoan(), QldtQlHocvien_.mathanhtoan));
            }
            if (criteria.getQldtDaotaoId() != null) {
                specification = specification.and(buildSpecification(criteria.getQldtDaotaoId(),
                    root -> root.join(QldtQlHocvien_.qldtDaotao, JoinType.LEFT).get(QldtDaotao_.id)));
            }
        }
        return specification;
    }
}
