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

import com.vnptit.vnpthis.domain.qldt.QldtDaotao;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtDaotaoRepository;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCriteria;
import com.vnptit.vnpthis.service.dto.QldtDaotaoDTO;
import com.vnptit.vnpthis.service.mapper.QldtDaotaoMapper;

/**
 * Service for executing complex queries for {@link QldtDaotao} entities in the database.
 * The main input is a {@link QldtDaotaoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtDaotaoDTO} or a {@link Page} of {@link QldtDaotaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtDaotaoQueryService extends QueryService<QldtDaotao> {

    private final Logger log = LoggerFactory.getLogger(QldtDaotaoQueryService.class);

    private final QldtDaotaoRepository qldtDaotaoRepository;

    private final QldtDaotaoMapper qldtDaotaoMapper;

    public QldtDaotaoQueryService(QldtDaotaoRepository qldtDaotaoRepository, QldtDaotaoMapper qldtDaotaoMapper) {
        this.qldtDaotaoRepository = qldtDaotaoRepository;
        this.qldtDaotaoMapper = qldtDaotaoMapper;
    }

    /**
     * Return a {@link List} of {@link QldtDaotaoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtDaotaoDTO> findByCriteria(QldtDaotaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtDaotao> specification = createSpecification(criteria);
        return qldtDaotaoMapper.toDto(qldtDaotaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtDaotaoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtDaotaoDTO> findByCriteria(QldtDaotaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtDaotao> specification = createSpecification(criteria);
        return qldtDaotaoRepository.findAll(specification, page)
            .map(qldtDaotaoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtDaotaoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtDaotao> specification = createSpecification(criteria);
        return qldtDaotaoRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtDaotaoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtDaotao> createSpecification(QldtDaotaoCriteria criteria) {
        Specification<QldtDaotao> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtDaotao_.id));
            }
            if (criteria.getMadaotao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMadaotao(), QldtDaotao_.madaotao));
            }
            if (criteria.getTendaotao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTendaotao(), QldtDaotao_.tendaotao));
            }
            if (criteria.getNgayBd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBd(), QldtDaotao_.ngayBd));
            }
            if (criteria.getNgayKt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKt(), QldtDaotao_.ngayKt));
            }
            if (criteria.getDiachi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiachi(), QldtDaotao_.diachi));
            }
            if (criteria.getDoituongchitiet() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDoituongchitiet(), QldtDaotao_.doituongchitiet));
            }
            if (criteria.getNoidungdaotao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoidungdaotao(), QldtDaotao_.noidungdaotao));
            }
            if (criteria.getThoigiandaotao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThoigiandaotao(), QldtDaotao_.thoigiandaotao));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtDaotao_.sudung));
            }
            if (criteria.getDaoTaoCtId() != null) {
                specification = specification.and(buildSpecification(criteria.getDaoTaoCtId(),
                    root -> root.join(QldtDaotao_.daoTaoCts, JoinType.LEFT).get(QldtDaotaoCt_.id)));
            }
            if (criteria.getHocVienId() != null) {
                specification = specification.and(buildSpecification(criteria.getHocVienId(),
                    root -> root.join(QldtDaotao_.hocViens, JoinType.LEFT).get(QldtQlHocvien_.id)));
            }
        }
        return specification;
    }
}
