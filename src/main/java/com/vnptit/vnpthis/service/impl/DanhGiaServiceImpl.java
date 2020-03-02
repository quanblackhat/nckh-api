package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.DanhGiaService;
import com.vnptit.vnpthis.domain.DanhGia;
import com.vnptit.vnpthis.repository.DanhGiaRepository;
import com.vnptit.vnpthis.service.dto.DanhGiaDTO;
import com.vnptit.vnpthis.service.mapper.DanhGiaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DanhGia}.
 */
@Service
@Transactional
public class DanhGiaServiceImpl implements DanhGiaService {

    private final Logger log = LoggerFactory.getLogger(DanhGiaServiceImpl.class);

    private final DanhGiaRepository danhGiaRepository;

    private final DanhGiaMapper danhGiaMapper;

    public DanhGiaServiceImpl(DanhGiaRepository danhGiaRepository, DanhGiaMapper danhGiaMapper) {
        this.danhGiaRepository = danhGiaRepository;
        this.danhGiaMapper = danhGiaMapper;
    }

    /**
     * Save a danhGia.
     *
     * @param danhGiaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DanhGiaDTO save(DanhGiaDTO danhGiaDTO) {
        log.debug("Request to save DanhGia : {}", danhGiaDTO);
        DanhGia danhGia = danhGiaMapper.toEntity(danhGiaDTO);
        danhGia = danhGiaRepository.save(danhGia);
        return danhGiaMapper.toDto(danhGia);
    }

    /**
     * Get all the danhGias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DanhGiaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhGias");
        return danhGiaRepository.findAll(pageable)
            .map(danhGiaMapper::toDto);
    }


    /**
     * Get one danhGia by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DanhGiaDTO> findOne(Long id) {
        log.debug("Request to get DanhGia : {}", id);
        return danhGiaRepository.findById(id)
            .map(danhGiaMapper::toDto);
    }

    /**
     * Delete the danhGia by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DanhGia : {}", id);
        danhGiaRepository.deleteById(id);
    }
}
