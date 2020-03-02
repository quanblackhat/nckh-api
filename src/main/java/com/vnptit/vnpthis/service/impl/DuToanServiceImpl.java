package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.DuToanService;
import com.vnptit.vnpthis.domain.nckh.DuToan;
import com.vnptit.vnpthis.repository.DuToanRepository;
import com.vnptit.vnpthis.service.dto.DuToanDTO;
import com.vnptit.vnpthis.service.mapper.DuToanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DuToan}.
 */
@Service
@Transactional
public class DuToanServiceImpl implements DuToanService {

    private final Logger log = LoggerFactory.getLogger(DuToanServiceImpl.class);

    private final DuToanRepository duToanRepository;

    private final DuToanMapper duToanMapper;

    public DuToanServiceImpl(DuToanRepository duToanRepository, DuToanMapper duToanMapper) {
        this.duToanRepository = duToanRepository;
        this.duToanMapper = duToanMapper;
    }

    /**
     * Save a duToan.
     *
     * @param duToanDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DuToanDTO save(DuToanDTO duToanDTO) {
        log.debug("Request to save DuToan : {}", duToanDTO);
        DuToan duToan = duToanMapper.toEntity(duToanDTO);
        duToan = duToanRepository.save(duToan);
        return duToanMapper.toDto(duToan);
    }

    /**
     * Get all the duToans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DuToanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DuToans");
        return duToanRepository.findAll(pageable)
            .map(duToanMapper::toDto);
    }


    /**
     * Get one duToan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DuToanDTO> findOne(Long id) {
        log.debug("Request to get DuToan : {}", id);
        return duToanRepository.findById(id)
            .map(duToanMapper::toDto);
    }

    /**
     * Delete the duToan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DuToan : {}", id);
        duToanRepository.deleteById(id);
    }
}
