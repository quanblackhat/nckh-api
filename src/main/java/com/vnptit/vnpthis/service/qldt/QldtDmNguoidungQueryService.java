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

import com.vnptit.vnpthis.domain.qldt.QldtDmNguoidung;
import com.vnptit.vnpthis.domain.qldt.*; // for static metamodels
import com.vnptit.vnpthis.repository.qldt.QldtDmNguoidungRepository;
import com.vnptit.vnpthis.service.dto.QldtDmNguoidungCriteria;
import com.vnptit.vnpthis.service.dto.QldtDmNguoidungDTO;
import com.vnptit.vnpthis.service.mapper.QldtDmNguoidungMapper;

/**
 * Service for executing complex queries for {@link QldtDmNguoidung} entities in the database.
 * The main input is a {@link QldtDmNguoidungCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QldtDmNguoidungDTO} or a {@link Page} of {@link QldtDmNguoidungDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QldtDmNguoidungQueryService extends QueryService<QldtDmNguoidung> {

    private final Logger log = LoggerFactory.getLogger(QldtDmNguoidungQueryService.class);

    private final QldtDmNguoidungRepository qldtDmNguoidungRepository;

    private final QldtDmNguoidungMapper qldtDmNguoidungMapper;

    public QldtDmNguoidungQueryService(QldtDmNguoidungRepository qldtDmNguoidungRepository, QldtDmNguoidungMapper qldtDmNguoidungMapper) {
        this.qldtDmNguoidungRepository = qldtDmNguoidungRepository;
        this.qldtDmNguoidungMapper = qldtDmNguoidungMapper;
    }

    /**
     * Return a {@link List} of {@link QldtDmNguoidungDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QldtDmNguoidungDTO> findByCriteria(QldtDmNguoidungCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QldtDmNguoidung> specification = createSpecification(criteria);
        return qldtDmNguoidungMapper.toDto(qldtDmNguoidungRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QldtDmNguoidungDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QldtDmNguoidungDTO> findByCriteria(QldtDmNguoidungCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QldtDmNguoidung> specification = createSpecification(criteria);
        return qldtDmNguoidungRepository.findAll(specification, page)
            .map(qldtDmNguoidungMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QldtDmNguoidungCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QldtDmNguoidung> specification = createSpecification(criteria);
        return qldtDmNguoidungRepository.count(specification);
    }

    /**
     * Function to convert {@link QldtDmNguoidungCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QldtDmNguoidung> createSpecification(QldtDmNguoidungCriteria criteria) {
        Specification<QldtDmNguoidung> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QldtDmNguoidung_.id));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), QldtDmNguoidung_.ten));
            }
            if (criteria.getNgaysinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaysinh(), QldtDmNguoidung_.ngaysinh));
            }
            if (criteria.getMabenhvien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMabenhvien(), QldtDmNguoidung_.mabenhvien));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), QldtDmNguoidung_.sudung));
            }
            if (criteria.getDaoTaoCtId() != null) {
                specification = specification.and(buildSpecification(criteria.getDaoTaoCtId(),
                    root -> root.join(QldtDmNguoidung_.daoTaoCt, JoinType.LEFT).get(QldtDaotaoCt_.id)));
            }
            if (criteria.getHocVienId() != null) {
                specification = specification.and(buildSpecification(criteria.getHocVienId(),
                    root -> root.join(QldtDmNguoidung_.hocVien, JoinType.LEFT).get(QldtQlHocvien_.id)));
            }
        }
        return specification;
    }
}
