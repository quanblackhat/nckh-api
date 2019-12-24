package com.vnpt.cdt.service;

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

import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.domain.*; // for static metamodels
import com.vnpt.cdt.repository.CdtChiDaoTuyenRepository;
import com.vnpt.cdt.service.dto.CdtChiDaoTuyenCriteria;

/**
 * Service for executing complex queries for {@link CdtChiDaoTuyen} entities in the database.
 * The main input is a {@link CdtChiDaoTuyenCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CdtChiDaoTuyen} or a {@link Page} of {@link CdtChiDaoTuyen} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CdtChiDaoTuyenQueryService extends QueryService<CdtChiDaoTuyen> {

    private final Logger log = LoggerFactory.getLogger(CdtChiDaoTuyenQueryService.class);

    private final CdtChiDaoTuyenRepository cdtChiDaoTuyenRepository;

    public CdtChiDaoTuyenQueryService(CdtChiDaoTuyenRepository cdtChiDaoTuyenRepository) {
        this.cdtChiDaoTuyenRepository = cdtChiDaoTuyenRepository;
    }

    /**
     * Return a {@link List} of {@link CdtChiDaoTuyen} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CdtChiDaoTuyen> findByCriteria(CdtChiDaoTuyenCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CdtChiDaoTuyen> specification = createSpecification(criteria);
        return cdtChiDaoTuyenRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CdtChiDaoTuyen} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtChiDaoTuyen> findByCriteria(CdtChiDaoTuyenCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CdtChiDaoTuyen> specification = createSpecification(criteria);
        return cdtChiDaoTuyenRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CdtChiDaoTuyenCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CdtChiDaoTuyen> specification = createSpecification(criteria);
        return cdtChiDaoTuyenRepository.count(specification);
    }

    /**
     * Function to convert {@link CdtChiDaoTuyenCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CdtChiDaoTuyen> createSpecification(CdtChiDaoTuyenCriteria criteria) {
        Specification<CdtChiDaoTuyen> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CdtChiDaoTuyen_.id));
            }
            if (criteria.getSoQuyetDinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoQuyetDinh(), CdtChiDaoTuyen_.soQuyetDinh));
            }
            if (criteria.getNgayQuyetDinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayQuyetDinh(), CdtChiDaoTuyen_.ngayQuyetDinh));
            }
            if (criteria.getSoHopDong() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoHopDong(), CdtChiDaoTuyen_.soHopDong));
            }
            if (criteria.getNgayHopDong() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayHopDong(), CdtChiDaoTuyen_.ngayHopDong));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), CdtChiDaoTuyen_.ghiChu));
            }
            if (criteria.getNoiDung() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiDung(), CdtChiDaoTuyen_.noiDung));
            }
            if (criteria.getNgayBD() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBD(), CdtChiDaoTuyen_.ngayBD));
            }
            if (criteria.getNgayKT() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKT(), CdtChiDaoTuyen_.ngayKT));
            }
            if (criteria.getNgayTao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayTao(), CdtChiDaoTuyen_.ngayTao));
            }
            if (criteria.getSoBenhNhanKham() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoBenhNhanKham(), CdtChiDaoTuyen_.soBenhNhanKham));
            }
            if (criteria.getSoBenhNhanKyThuat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoBenhNhanKyThuat(), CdtChiDaoTuyen_.soBenhNhanKyThuat));
            }
            if (criteria.getSoCanBoChuyenGiao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoCanBoChuyenGiao(), CdtChiDaoTuyen_.soCanBoChuyenGiao));
            }
            if (criteria.getNhanvienId() != null) {
                specification = specification.and(buildSpecification(criteria.getNhanvienId(),
                    root -> root.join(CdtChiDaoTuyen_.nhanviens, JoinType.LEFT).get(CdtNhanVien_.id)));
            }
            if (criteria.getNoidenId() != null) {
                specification = specification.and(buildSpecification(criteria.getNoidenId(),
                    root -> root.join(CdtChiDaoTuyen_.noidens, JoinType.LEFT).get(CdtNoiDen_.id)));
            }
            if (criteria.getKythuatId() != null) {
                specification = specification.and(buildSpecification(criteria.getKythuatId(),
                    root -> root.join(CdtChiDaoTuyen_.kythuats, JoinType.LEFT).get(CdtKyThuat_.id)));
            }
            if (criteria.getVattuId() != null) {
                specification = specification.and(buildSpecification(criteria.getVattuId(),
                    root -> root.join(CdtChiDaoTuyen_.vattus, JoinType.LEFT).get(CdtVatTu_.id)));
            }
            if (criteria.getLydocongtacId() != null) {
                specification = specification.and(buildSpecification(criteria.getLydocongtacId(),
                    root -> root.join(CdtChiDaoTuyen_.lydocongtacs, JoinType.LEFT).get(CdtLyDoCongTac_.id)));
            }
            if (criteria.getKetquacongtacId() != null) {
                specification = specification.and(buildSpecification(criteria.getKetquacongtacId(),
                    root -> root.join(CdtChiDaoTuyen_.ketquacongtacs, JoinType.LEFT).get(CdtKetQuaCongTac_.id)));
            }
            if (criteria.getTaichinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getTaichinhId(),
                    root -> root.join(CdtChiDaoTuyen_.taichinhs, JoinType.LEFT).get(CdtTaiChinh_.id)));
            }
        }
        return specification;
    }
}
