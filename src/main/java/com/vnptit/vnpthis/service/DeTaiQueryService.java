package com.vnptit.vnpthis.service;

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

import com.vnptit.vnpthis.domain.nckh.DeTai;
import com.vnptit.vnpthis.domain.nckh.*; // for static metamodels
import com.vnptit.vnpthis.repository.DeTaiRepository;
import com.vnptit.vnpthis.service.dto.DeTaiCriteria;
import com.vnptit.vnpthis.service.dto.DeTaiDTO;
import com.vnptit.vnpthis.service.mapper.DeTaiMapper;

/**
 * Service for executing complex queries for {@link DeTai} entities in the database.
 * The main input is a {@link DeTaiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DeTaiDTO} or a {@link Page} of {@link DeTaiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DeTaiQueryService extends QueryService<DeTai> {

    private final Logger log = LoggerFactory.getLogger(DeTaiQueryService.class);

    private final DeTaiRepository deTaiRepository;

    private final DeTaiMapper deTaiMapper;

    public DeTaiQueryService(DeTaiRepository deTaiRepository, DeTaiMapper deTaiMapper) {
        this.deTaiRepository = deTaiRepository;
        this.deTaiMapper = deTaiMapper;
    }

    /**
     * Return a {@link List} of {@link DeTaiDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DeTaiDTO> findByCriteria(DeTaiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DeTai> specification = createSpecification(criteria);
        return deTaiMapper.toDto(deTaiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DeTaiDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DeTaiDTO> findByCriteria(DeTaiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DeTai> specification = createSpecification(criteria);
        return deTaiRepository.findAll(specification, page)
            .map(deTaiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DeTaiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DeTai> specification = createSpecification(criteria);
        return deTaiRepository.count(specification);
    }

    /**
     * Function to convert {@link DeTaiCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DeTai> createSpecification(DeTaiCriteria criteria) {
        Specification<DeTai> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DeTai_.id));
            }
            if (criteria.getSott() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSott(), DeTai_.sott));
            }
            if (criteria.getTendetai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTendetai(), DeTai_.tendetai));
            }
            if (criteria.getNgaytao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaytao(), DeTai_.ngaytao));
            }
            if (criteria.getNgaypheduyet() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaypheduyet(), DeTai_.ngaypheduyet));
            }
            if (criteria.getNgaybd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaybd(), DeTai_.ngaybd));
            }
            if (criteria.getNgaykt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaykt(), DeTai_.ngaykt));
            }
            if (criteria.getHientrang() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHientrang(), DeTai_.hientrang));
            }
            if (criteria.getXuatban() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getXuatban(), DeTai_.xuatban));
            }
            if (criteria.getCapquanly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCapquanly(), DeTai_.capquanly));
            }
            if (criteria.getNgaynghiemthu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaynghiemthu(), DeTai_.ngaynghiemthu));
            }
            if (criteria.getKinhphithuchien() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKinhphithuchien(), DeTai_.kinhphithuchien));
            }
            if (criteria.getNguonkinhphi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNguonkinhphi(), DeTai_.nguonkinhphi));
            }
            if (criteria.getMuctieu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMuctieu(), DeTai_.muctieu));
            }
            if (criteria.getKinhphiDukien() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKinhphiDukien(), DeTai_.kinhphiDukien));
            }
            if (criteria.getChunhiemdetai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChunhiemdetai(), DeTai_.chunhiemdetai));
            }
            if (criteria.getNoidungtongquan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoidungtongquan(), DeTai_.noidungtongquan));
            }
            if (criteria.getKinhphiHoanthanh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKinhphiHoanthanh(), DeTai_.kinhphiHoanthanh));
            }
            if (criteria.getTongdiem() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTongdiem(), DeTai_.tongdiem));
            }
            if (criteria.getGhichu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhichu(), DeTai_.ghichu));
            }
            if (criteria.getNguoiCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiCn(), DeTai_.nguoiCn));
            }
            if (criteria.getNgayCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCn(), DeTai_.ngayCn));
            }
            if (criteria.getUpFileId() != null) {
                specification = specification.and(buildSpecification(criteria.getUpFileId(),
                    root -> root.join(DeTai_.upFiles, JoinType.LEFT).get(UpFile_.id)));
            }
            if (criteria.getTienDoId() != null) {
                specification = specification.and(buildSpecification(criteria.getTienDoId(),
                    root -> root.join(DeTai_.tienDos, JoinType.LEFT).get(TienDo_.id)));
            }
            if (criteria.getNhanSuId() != null) {
                specification = specification.and(buildSpecification(criteria.getNhanSuId(),
                    root -> root.join(DeTai_.nhanSus, JoinType.LEFT).get(NhanSu_.id)));
            }
            if (criteria.getDuToanId() != null) {
                specification = specification.and(buildSpecification(criteria.getDuToanId(),
                    root -> root.join(DeTai_.duToans, JoinType.LEFT).get(DuToan_.id)));
            }
            if (criteria.getDanhGiaId() != null) {
                specification = specification.and(buildSpecification(criteria.getDanhGiaId(),
                    root -> root.join(DeTai_.danhGias, JoinType.LEFT).get(DanhGia_.id)));
            }
            if (criteria.getChuyenMucId() != null) {
                specification = specification.and(buildSpecification(criteria.getChuyenMucId(),
                    root -> root.join(DeTai_.chuyenMuc, JoinType.LEFT).get(ChuyenMuc_.id)));
            }
        }
        return specification;
    }
}
