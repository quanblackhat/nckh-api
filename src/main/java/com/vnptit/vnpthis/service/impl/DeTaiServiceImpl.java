package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.DeTaiService;
import com.vnptit.vnpthis.domain.nckh.DeTai;
import com.vnptit.vnpthis.repository.DeTaiRepository;
import com.vnptit.vnpthis.service.dto.DeTaiDTO;
import com.vnptit.vnpthis.service.mapper.DeTaiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DeTai}.
 */
@Service
@Transactional
public class DeTaiServiceImpl implements DeTaiService {

    private final Logger log = LoggerFactory.getLogger(DeTaiServiceImpl.class);

    private final DeTaiRepository deTaiRepository;

    private final DeTaiMapper deTaiMapper;

    public DeTaiServiceImpl(DeTaiRepository deTaiRepository, DeTaiMapper deTaiMapper) {
        this.deTaiRepository = deTaiRepository;
        this.deTaiMapper = deTaiMapper;
    }

    /**
     * Save a deTai.
     *
     * @param deTaiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DeTaiDTO save(DeTaiDTO deTaiDTO) {
        log.debug("Request to save DeTai : {}", deTaiDTO);
        DeTai deTai = deTaiMapper.toEntity(deTaiDTO);
        deTai = deTaiRepository.save(deTai);
        return deTaiMapper.toDto(deTai);
    }

    /**
     * Get all the deTais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeTaiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeTais");
        return deTaiRepository.findAll(pageable)
            .map(deTaiMapper::toDto);
    }


    /**
     * Get one deTai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DeTaiDTO> findOne(Long id) {
        log.debug("Request to get DeTai : {}", id);
        return deTaiRepository.findById(id)
            .map(deTaiMapper::toDto);
    }

    /**
     * Delete the deTai by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeTai : {}", id);
        deTaiRepository.deleteById(id);
    }
}
