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

import com.vnptit.vnpthis.domain.qldt.QldtDmNoidung;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtDmNoidungRepository;
import com.vnptit.vnpthis.service.dto.QldtDmNoidungCriteria;
import com.vnptit.vnpthis.service.dto.QldtDmNoidungDTO;
import com.vnptit.vnpthis.service.mapper.QldtDmNoidungMapper;

/**
 * Service for executing complex queries for {@link QldtDmNoidung} entities in the database.
 * The main input is a {@link QldtDmNoidungCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtDmNoidungDTO} or a {@link Page} of {@link QldtDmNoidungDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtDmNoidungQueryService extends QueryService<QldtDmNoidung> {

    private final Logger log = LoggerFactory.getLogger(QldtDmNoidungQueryService.class);

    private final QldtDmNoidungRepository qldtDmNoidungRepository;

    private final QldtDmNoidungMapper qldtDmNoidungMapper;

    public QldtDmNoidungQueryService(QldtDmNoidungRepository qldtDmNoidungRepository, QldtDmNoidungMapper qldtDmNoidungMapper) {
        this.qldtDmNoidungRepository = qldtDmNoidungRepository;
        this.qldtDmNoidungMapper = qldtDmNoidungMapper;
    }

    /**
     * Return a {@link List} of {@link QldtDmNoidungDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtDmNoidungDTO> findByCriteria(QldtDmNoidungCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtDmNoidung> specification = createSpecification(criteria);
        return qldtDmNoidungMapper.toDto(qldtDmNoidungRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtDmNoidungDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtDmNoidungDTO> findByCriteria(QldtDmNoidungCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtDmNoidung> specification = createSpecification(criteria);
        return qldtDmNoidungRepository.findAll(specification, page)
            .map(qldtDmNoidungMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtDmNoidungCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtDmNoidung> specification = createSpecification(criteria);
        return qldtDmNoidungRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtDmNoidungCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtDmNoidung> createSpecification(QldtDmNoidungCriteria criteria) {
        Specification<QldtDmNoidung> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtDmNoidung_.id));
            }
            if (criteria.getNoidung() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoidung(), QldtDmNoidung_.noidung));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtDmNoidung_.sudung));
            }
            if (criteria.getDuToandaotaoCtId() != null) {
                specification = specification.and(buildSpecification(criteria.getDuToandaotaoCtId(),
                    root -> root.join(QldtDmNoidung_.duToandaotaoCts, JoinType.LEFT).get(QldtDutoanDaotaoct_.id)));
            }
        }
        return specification;
    }
}
