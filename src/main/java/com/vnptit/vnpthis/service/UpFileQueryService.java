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

import com.vnptit.vnpthis.domain.nckh.UpFile;
import com.vnptit.vnpthis.domain.nckh.*; // for static metamodels
import com.vnptit.vnpthis.repository.UpFileRepository;
import com.vnptit.vnpthis.service.dto.UpFileCriteria;
import com.vnptit.vnpthis.service.dto.UpFileDTO;
import com.vnptit.vnpthis.service.mapper.UpFileMapper;

/**
 * Service for executing complex queries for {@link UpFile} entities in the database.
 * The main input is a {@link UpFileCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UpFileDTO} or a {@link Page} of {@link UpFileDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UpFileQueryService extends QueryService<UpFile> {

    private final Logger log = LoggerFactory.getLogger(UpFileQueryService.class);

    private final UpFileRepository upFileRepository;

    private final UpFileMapper upFileMapper;

    public UpFileQueryService(UpFileRepository upFileRepository, UpFileMapper upFileMapper) {
        this.upFileRepository = upFileRepository;
        this.upFileMapper = upFileMapper;
    }

    /**
     * Return a {@link List} of {@link UpFileDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UpFileDTO> findByCriteria(UpFileCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<UpFile> specification = createSpecification(criteria);
        return upFileMapper.toDto(upFileRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UpFileDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UpFileDTO> findByCriteria(UpFileCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<UpFile> specification = createSpecification(criteria);
        return upFileRepository.findAll(specification, page)
            .map(upFileMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UpFileCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<UpFile> specification = createSpecification(criteria);
        return upFileRepository.count(specification);
    }

    /**
     * Function to convert {@link UpFileCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<UpFile> createSpecification(UpFileCriteria criteria) {
        Specification<UpFile> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), UpFile_.id));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), UpFile_.mota));
            }
            if (criteria.getNgayCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCn(), UpFile_.ngayCn));
            }
            if (criteria.getNguoiCn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiCn(), UpFile_.nguoiCn));
            }
            if (criteria.getDeTaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeTaiId(),
                    root -> root.join(UpFile_.deTai, JoinType.LEFT).get(DeTai_.id)));
            }
            if (criteria.getTienDoId() != null) {
                specification = specification.and(buildSpecification(criteria.getTienDoId(),
                    root -> root.join(UpFile_.tienDo, JoinType.LEFT).get(TienDo_.id)));
            }
        }
        return specification;
    }
}
