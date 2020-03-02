package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.LoaiDanhMucService;
import com.vnptit.vnpthis.domain.nckh.LoaiDanhMuc;
import com.vnptit.vnpthis.repository.LoaiDanhMucRepository;
import com.vnptit.vnpthis.service.dto.LoaiDanhMucDTO;
import com.vnptit.vnpthis.service.mapper.LoaiDanhMucMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LoaiDanhMuc}.
 */
@Service
@Transactional
public class LoaiDanhMucServiceImpl implements LoaiDanhMucService {

    private final Logger log = LoggerFactory.getLogger(LoaiDanhMucServiceImpl.class);

    private final LoaiDanhMucRepository loaiDanhMucRepository;

    private final LoaiDanhMucMapper loaiDanhMucMapper;

    public LoaiDanhMucServiceImpl(LoaiDanhMucRepository loaiDanhMucRepository, LoaiDanhMucMapper loaiDanhMucMapper) {
        this.loaiDanhMucRepository = loaiDanhMucRepository;
        this.loaiDanhMucMapper = loaiDanhMucMapper;
    }

    /**
     * Save a loaiDanhMuc.
     *
     * @param loaiDanhMucDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LoaiDanhMucDTO save(LoaiDanhMucDTO loaiDanhMucDTO) {
        log.debug("Request to save LoaiDanhMuc : {}", loaiDanhMucDTO);
        LoaiDanhMuc loaiDanhMuc = loaiDanhMucMapper.toEntity(loaiDanhMucDTO);
        loaiDanhMuc = loaiDanhMucRepository.save(loaiDanhMuc);
        return loaiDanhMucMapper.toDto(loaiDanhMuc);
    }

    /**
     * Get all the loaiDanhMucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LoaiDanhMucDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LoaiDanhMucs");
        return loaiDanhMucRepository.findAll(pageable)
            .map(loaiDanhMucMapper::toDto);
    }


    /**
     * Get one loaiDanhMuc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LoaiDanhMucDTO> findOne(Long id) {
        log.debug("Request to get LoaiDanhMuc : {}", id);
        return loaiDanhMucRepository.findById(id)
            .map(loaiDanhMucMapper::toDto);
    }

    /**
     * Delete the loaiDanhMuc by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LoaiDanhMuc : {}", id);
        loaiDanhMucRepository.deleteById(id);
    }
}
