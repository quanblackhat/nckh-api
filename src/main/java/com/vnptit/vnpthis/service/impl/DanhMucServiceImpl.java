package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.DanhMucService;
import com.vnptit.vnpthis.domain.nckh.DanhMuc;
import com.vnptit.vnpthis.repository.DanhMucRepository;
import com.vnptit.vnpthis.service.dto.DanhMucDTO;
import com.vnptit.vnpthis.service.mapper.DanhMucMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DanhMuc}.
 */
@Service
@Transactional
public class DanhMucServiceImpl implements DanhMucService {

    private final Logger log = LoggerFactory.getLogger(DanhMucServiceImpl.class);

    private final DanhMucRepository danhMucRepository;

    private final DanhMucMapper danhMucMapper;

    public DanhMucServiceImpl(DanhMucRepository danhMucRepository, DanhMucMapper danhMucMapper) {
        this.danhMucRepository = danhMucRepository;
        this.danhMucMapper = danhMucMapper;
    }

    /**
     * Save a danhMuc.
     *
     * @param danhMucDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DanhMucDTO save(DanhMucDTO danhMucDTO) {
        log.debug("Request to save DanhMuc : {}", danhMucDTO);
        DanhMuc danhMuc = danhMucMapper.toEntity(danhMucDTO);
        danhMuc = danhMucRepository.save(danhMuc);
        return danhMucMapper.toDto(danhMuc);
    }

    /**
     * Get all the danhMucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DanhMucDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhMucs");
        return danhMucRepository.findAll(pageable)
            .map(danhMucMapper::toDto);
    }


    /**
     * Get one danhMuc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucDTO> findOne(Long id) {
        log.debug("Request to get DanhMuc : {}", id);
        return danhMucRepository.findById(id)
            .map(danhMucMapper::toDto);
    }

    /**
     * Delete the danhMuc by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DanhMuc : {}", id);
        danhMucRepository.deleteById(id);
    }
}
