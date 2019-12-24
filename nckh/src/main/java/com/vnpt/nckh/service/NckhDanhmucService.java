package com.vnpt.nckh.service;

import com.vnpt.nckh.domain.NckhDanhmuc;
import com.vnpt.nckh.repository.NckhDanhmucRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NckhDanhmuc}.
 */
@Service
@Transactional
public class NckhDanhmucService {

    private final Logger log = LoggerFactory.getLogger(NckhDanhmucService.class);

    private final NckhDanhmucRepository nckhDanhmucRepository;

    public NckhDanhmucService(NckhDanhmucRepository nckhDanhmucRepository) {
        this.nckhDanhmucRepository = nckhDanhmucRepository;
    }

    /**
     * Save a nckhDanhmuc.
     *
     * @param nckhDanhmuc the entity to save.
     * @return the persisted entity.
     */
    public NckhDanhmuc save(NckhDanhmuc nckhDanhmuc) {
        log.debug("Request to save NckhDanhmuc : {}", nckhDanhmuc);
        return nckhDanhmucRepository.save(nckhDanhmuc);
    }

    /**
     * Get all the nckhDanhmucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhDanhmuc> findAll(Pageable pageable) {
        log.debug("Request to get all NckhDanhmucs");
        return nckhDanhmucRepository.findAll(pageable);
    }


    /**
     * Get one nckhDanhmuc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NckhDanhmuc> findOne(Long id) {
        log.debug("Request to get NckhDanhmuc : {}", id);
        return nckhDanhmucRepository.findById(id);
    }

    /**
     * Delete the nckhDanhmuc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NckhDanhmuc : {}", id);
        nckhDanhmucRepository.deleteById(id);
    }
}
