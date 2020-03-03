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

import com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtDaotaoCtRepository;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCtCriteria;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCtDTO;
import com.vnptit.vnpthis.service.mapper.QldtDaotaoCtMapper;

/**
 * Service for executing complex queries for {@link QldtDaotaoCt} entities in the database.
 * The main input is a {@link QldtDaotaoCtCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtDaotaoCtDTO} or a {@link Page} of {@link QldtDaotaoCtDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtDaotaoCtQueryService extends QueryService<QldtDaotaoCt> {

    private final Logger log = LoggerFactory.getLogger(QldtDaotaoCtQueryService.class);

    private final QldtDaotaoCtRepository qldtDaotaoCtRepository;

    private final QldtDaotaoCtMapper qldtDaotaoCtMapper;

    public QldtDaotaoCtQueryService(QldtDaotaoCtRepository qldtDaotaoCtRepository, QldtDaotaoCtMapper qldtDaotaoCtMapper) {
        this.qldtDaotaoCtRepository = qldtDaotaoCtRepository;
        this.qldtDaotaoCtMapper = qldtDaotaoCtMapper;
    }

    /**
     * Return a {@link List} of {@link QldtDaotaoCtDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtDaotaoCtDTO> findByCriteria(QldtDaotaoCtCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtDaotaoCt> specification = createSpecification(criteria);
        return qldtDaotaoCtMapper.toDto(qldtDaotaoCtRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtDaotaoCtDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtDaotaoCtDTO> findByCriteria(QldtDaotaoCtCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtDaotaoCt> specification = createSpecification(criteria);
        return qldtDaotaoCtRepository.findAll(specification, page)
            .map(qldtDaotaoCtMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtDaotaoCtCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtDaotaoCt> specification = createSpecification(criteria);
        return qldtDaotaoCtRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtDaotaoCtCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtDaotaoCt> createSpecification(QldtDaotaoCtCriteria criteria) {
        Specification<QldtDaotaoCt> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtDaotaoCt_.id));
            }
            if (criteria.getMadaotaochitiet() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMadaotaochitiet(), QldtDaotaoCt_.madaotaochitiet));
            }
            if (criteria.getTendaotaochitiet() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTendaotaochitiet(), QldtDaotaoCt_.tendaotaochitiet));
            }
            if (criteria.getNgayBd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBd(), QldtDaotaoCt_.ngayBd));
            }
            if (criteria.getNgayKt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKt(), QldtDaotaoCt_.ngayKt));
            }
            if (criteria.getThoigiandaotaoct() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThoigiandaotaoct(), QldtDaotaoCt_.thoigiandaotaoct));
            }
            if (criteria.getNoidungdaotaoct() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoidungdaotaoct(), QldtDaotaoCt_.noidungdaotaoct));
            }
            if (criteria.getTrangthaidaotaoct() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangthaidaotaoct(), QldtDaotaoCt_.trangthaidaotaoct));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtDaotaoCt_.sudung));
            }
            if (criteria.getHocVienCtId() != null) {
                specification = specification.and(buildSpecification(criteria.getHocVienCtId(),
                    root -> root.join(QldtDaotaoCt_.hocVienCts, JoinType.LEFT).get(QldtQlHocvienCt_.id)));
            }
            if (criteria.getQldtDaotaoId() != null) {
                specification = specification.and(buildSpecification(criteria.getQldtDaotaoId(),
                    root -> root.join(QldtDaotaoCt_.qldtDaotao, JoinType.LEFT).get(QldtDaotao_.id)));
            }
        }
        return specification;
    }
}
