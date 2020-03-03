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

import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotao;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtDutoanDaotaoRepository;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoCriteria;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoDTO;
import com.vnptit.vnpthis.service.mapper.QldtDutoanDaotaoMapper;

/**
 * Service for executing complex queries for {@link QldtDutoanDaotao} entities in the database.
 * The main input is a {@link QldtDutoanDaotaoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtDutoanDaotaoDTO} or a {@link Page} of {@link QldtDutoanDaotaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtDutoanDaotaoQueryService extends QueryService<QldtDutoanDaotao> {

    private final Logger log = LoggerFactory.getLogger(QldtDutoanDaotaoQueryService.class);

    private final QldtDutoanDaotaoRepository qldtDutoanDaotaoRepository;

    private final QldtDutoanDaotaoMapper qldtDutoanDaotaoMapper;

    public QldtDutoanDaotaoQueryService(QldtDutoanDaotaoRepository qldtDutoanDaotaoRepository, QldtDutoanDaotaoMapper qldtDutoanDaotaoMapper) {
        this.qldtDutoanDaotaoRepository = qldtDutoanDaotaoRepository;
        this.qldtDutoanDaotaoMapper = qldtDutoanDaotaoMapper;
    }

    /**
     * Return a {@link List} of {@link QldtDutoanDaotaoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtDutoanDaotaoDTO> findByCriteria(QldtDutoanDaotaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtDutoanDaotao> specification = createSpecification(criteria);
        return qldtDutoanDaotaoMapper.toDto(qldtDutoanDaotaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtDutoanDaotaoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtDutoanDaotaoDTO> findByCriteria(QldtDutoanDaotaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtDutoanDaotao> specification = createSpecification(criteria);
        return qldtDutoanDaotaoRepository.findAll(specification, page)
            .map(qldtDutoanDaotaoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtDutoanDaotaoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtDutoanDaotao> specification = createSpecification(criteria);
        return qldtDutoanDaotaoRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtDutoanDaotaoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtDutoanDaotao> createSpecification(QldtDutoanDaotaoCriteria criteria) {
        Specification<QldtDutoanDaotao> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtDutoanDaotao_.id));
            }
            if (criteria.getSolop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSolop(), QldtDutoanDaotao_.solop));
            }
            if (criteria.getSohocvien() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSohocvien(), QldtDutoanDaotao_.sohocvien));
            }
            if (criteria.getDathanhtoan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDathanhtoan(), QldtDutoanDaotao_.dathanhtoan));
            }
            if (criteria.getMadutoan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMadutoan(), QldtDutoanDaotao_.madutoan));
            }
            if (criteria.getTendutoan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTendutoan(), QldtDutoanDaotao_.tendutoan));
            }
            if (criteria.getTrangthai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangthai(), QldtDutoanDaotao_.trangthai));
            }
            if (criteria.getNgayBd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBd(), QldtDutoanDaotao_.ngayBd));
            }
            if (criteria.getNgayKt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKt(), QldtDutoanDaotao_.ngayKt));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtDutoanDaotao_.sudung));
            }
            if (criteria.getDuToanDaotaoCtId() != null) {
                specification = specification.and(buildSpecification(criteria.getDuToanDaotaoCtId(),
                    root -> root.join(QldtDutoanDaotao_.duToanDaotaoCts, JoinType.LEFT).get(QldtDutoanDaotaoct_.id)));
            }
        }
        return specification;
    }
}
