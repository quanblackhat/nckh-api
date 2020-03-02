package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.TienDoService;
import com.vnptit.vnpthis.domain.TienDo;
import com.vnptit.vnpthis.repository.TienDoRepository;
import com.vnptit.vnpthis.service.dto.TienDoDTO;
import com.vnptit.vnpthis.service.mapper.TienDoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TienDo}.
 */
@Service
@Transactional
public class TienDoServiceImpl implements TienDoService {

    private final Logger log = LoggerFactory.getLogger(TienDoServiceImpl.class);

    private final TienDoRepository tienDoRepository;

    private final TienDoMapper tienDoMapper;

    public TienDoServiceImpl(TienDoRepository tienDoRepository, TienDoMapper tienDoMapper) {
        this.tienDoRepository = tienDoRepository;
        this.tienDoMapper = tienDoMapper;
    }

    /**
     * Save a tienDo.
     *
     * @param tienDoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TienDoDTO save(TienDoDTO tienDoDTO) {
        log.debug("Request to save TienDo : {}", tienDoDTO);
        TienDo tienDo = tienDoMapper.toEntity(tienDoDTO);
        tienDo = tienDoRepository.save(tienDo);
        return tienDoMapper.toDto(tienDo);
    }

    /**
     * Get all the tienDos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TienDoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TienDos");
        return tienDoRepository.findAll(pageable)
            .map(tienDoMapper::toDto);
    }


    /**
     * Get one tienDo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TienDoDTO> findOne(Long id) {
        log.debug("Request to get TienDo : {}", id);
        return tienDoRepository.findById(id)
            .map(tienDoMapper::toDto);
    }

    /**
     * Delete the tienDo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TienDo : {}", id);
        tienDoRepository.deleteById(id);
    }
}
