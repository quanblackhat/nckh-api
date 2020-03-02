package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.ChuyenMucService;
import com.vnptit.vnpthis.domain.nckh.ChuyenMuc;
import com.vnptit.vnpthis.repository.ChuyenMucRepository;
import com.vnptit.vnpthis.service.dto.ChuyenMucDTO;
import com.vnptit.vnpthis.service.mapper.ChuyenMucMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ChuyenMuc}.
 */
@Service
@Transactional
public class ChuyenMucServiceImpl implements ChuyenMucService {

    private final Logger log = LoggerFactory.getLogger(ChuyenMucServiceImpl.class);

    private final ChuyenMucRepository chuyenMucRepository;

    private final ChuyenMucMapper chuyenMucMapper;

    public ChuyenMucServiceImpl(ChuyenMucRepository chuyenMucRepository, ChuyenMucMapper chuyenMucMapper) {
        this.chuyenMucRepository = chuyenMucRepository;
        this.chuyenMucMapper = chuyenMucMapper;
    }

    /**
     * Save a chuyenMuc.
     *
     * @param chuyenMucDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ChuyenMucDTO save(ChuyenMucDTO chuyenMucDTO) {
        log.debug("Request to save ChuyenMuc : {}", chuyenMucDTO);
        ChuyenMuc chuyenMuc = chuyenMucMapper.toEntity(chuyenMucDTO);
        chuyenMuc = chuyenMucRepository.save(chuyenMuc);
        return chuyenMucMapper.toDto(chuyenMuc);
    }

    /**
     * Get all the chuyenMucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChuyenMucDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChuyenMucs");
        return chuyenMucRepository.findAll(pageable)
            .map(chuyenMucMapper::toDto);
    }


    /**
     * Get one chuyenMuc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChuyenMucDTO> findOne(Long id) {
        log.debug("Request to get ChuyenMuc : {}", id);
        return chuyenMucRepository.findById(id)
            .map(chuyenMucMapper::toDto);
    }

    /**
     * Delete the chuyenMuc by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChuyenMuc : {}", id);
        chuyenMucRepository.deleteById(id);
    }
}
