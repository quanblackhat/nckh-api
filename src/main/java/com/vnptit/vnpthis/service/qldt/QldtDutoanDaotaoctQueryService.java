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

import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotaoct;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtDutoanDaotaoctRepository;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoctCriteria;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoctDTO;
import com.vnptit.vnpthis.service.mapper.QldtDutoanDaotaoctMapper;

/**
 * Service for executing complex queries for {@link QldtDutoanDaotaoct} entities in the database.
 * The main input is a {@link QldtDutoanDaotaoctCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtDutoanDaotaoctDTO} or a {@link Page} of {@link QldtDutoanDaotaoctDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtDutoanDaotaoctQueryService extends QueryService<QldtDutoanDaotaoct> {

    private final Logger log = LoggerFactory.getLogger(QldtDutoanDaotaoctQueryService.class);

    private final QldtDutoanDaotaoctRepository qldtDutoanDaotaoctRepository;

    private final QldtDutoanDaotaoctMapper qldtDutoanDaotaoctMapper;

    public QldtDutoanDaotaoctQueryService(QldtDutoanDaotaoctRepository qldtDutoanDaotaoctRepository, QldtDutoanDaotaoctMapper qldtDutoanDaotaoctMapper) {
        this.qldtDutoanDaotaoctRepository = qldtDutoanDaotaoctRepository;
        this.qldtDutoanDaotaoctMapper = qldtDutoanDaotaoctMapper;
    }

    /**
     * Return a {@link List} of {@link QldtDutoanDaotaoctDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtDutoanDaotaoctDTO> findByCriteria(QldtDutoanDaotaoctCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtDutoanDaotaoct> specification = createSpecification(criteria);
        return qldtDutoanDaotaoctMapper.toDto(qldtDutoanDaotaoctRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtDutoanDaotaoctDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtDutoanDaotaoctDTO> findByCriteria(QldtDutoanDaotaoctCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtDutoanDaotaoct> specification = createSpecification(criteria);
        return qldtDutoanDaotaoctRepository.findAll(specification, page)
            .map(qldtDutoanDaotaoctMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtDutoanDaotaoctCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtDutoanDaotaoct> specification = createSpecification(criteria);
        return qldtDutoanDaotaoctRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtDutoanDaotaoctCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtDutoanDaotaoct> createSpecification(QldtDutoanDaotaoctCriteria criteria) {
        Specification<QldtDutoanDaotaoct> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtDutoanDaotaoct_.id));
            }
            if (criteria.getSoluong() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoluong(), QldtDutoanDaotaoct_.soluong));
            }
            if (criteria.getMucchi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMucchi(), QldtDutoanDaotaoct_.mucchi));
            }
            if (criteria.getThanhtien() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThanhtien(), QldtDutoanDaotaoct_.thanhtien));
            }
            if (criteria.getNoidung() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoidung(), QldtDutoanDaotaoct_.noidung));
            }
            if (criteria.getTrangthaict() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangthaict(), QldtDutoanDaotaoct_.trangthaict));
            }
            if (criteria.getDathanhtoan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDathanhtoan(), QldtDutoanDaotaoct_.dathanhtoan));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtDutoanDaotaoct_.sudung));
            }
            if (criteria.getQldtDutoanDaotaoId() != null) {
                specification = specification.and(buildSpecification(criteria.getQldtDutoanDaotaoId(),
                    root -> root.join(QldtDutoanDaotaoct_.qldtDutoanDaotao, JoinType.LEFT).get(QldtDutoanDaotao_.id)));
            }
            if (criteria.getQldtDmNoidungId() != null) {
                specification = specification.and(buildSpecification(criteria.getQldtDmNoidungId(),
                    root -> root.join(QldtDutoanDaotaoct_.qldtDmNoidung, JoinType.LEFT).get(QldtDmNoidung_.id)));
            }
        }
        return specification;
    }
}
