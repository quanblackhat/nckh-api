package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.NhanSuService;
import com.vnptit.vnpthis.domain.nckh.NhanSu;
import com.vnptit.vnpthis.repository.NhanSuRepository;
import com.vnptit.vnpthis.service.dto.NhanSuDTO;
import com.vnptit.vnpthis.service.mapper.NhanSuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NhanSu}.
 */
@Service
@Transactional
public class NhanSuServiceImpl implements NhanSuService {

    private final Logger log = LoggerFactory.getLogger(NhanSuServiceImpl.class);

    private final NhanSuRepository nhanSuRepository;

    private final NhanSuMapper nhanSuMapper;

    public NhanSuServiceImpl(NhanSuRepository nhanSuRepository, NhanSuMapper nhanSuMapper) {
        this.nhanSuRepository = nhanSuRepository;
        this.nhanSuMapper = nhanSuMapper;
    }

    /**
     * Save a nhanSu.
     *
     * @param nhanSuDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NhanSuDTO save(NhanSuDTO nhanSuDTO) {
        log.debug("Request to save NhanSu : {}", nhanSuDTO);
        NhanSu nhanSu = nhanSuMapper.toEntity(nhanSuDTO);
        nhanSu = nhanSuRepository.save(nhanSu);
        return nhanSuMapper.toDto(nhanSu);
    }

    /**
     * Get all the nhanSus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NhanSuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NhanSus");
        return nhanSuRepository.findAll(pageable)
            .map(nhanSuMapper::toDto);
    }


    /**
     * Get one nhanSu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NhanSuDTO> findOne(Long id) {
        log.debug("Request to get NhanSu : {}", id);
        return nhanSuRepository.findById(id)
            .map(nhanSuMapper::toDto);
    }

    /**
     * Delete the nhanSu by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NhanSu : {}", id);
        nhanSuRepository.deleteById(id);
    }
}
