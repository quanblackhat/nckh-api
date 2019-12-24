package com.vnpt.nckh.service;

import com.vnpt.nckh.domain.NckhNhanSu;
import com.vnpt.nckh.repository.NckhNhanSuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NckhNhanSu}.
 */
@Service
@Transactional
public class NckhNhanSuService {

    private final Logger log = LoggerFactory.getLogger(NckhNhanSuService.class);

    private final NckhNhanSuRepository nckhNhanSuRepository;

    public NckhNhanSuService(NckhNhanSuRepository nckhNhanSuRepository) {
        this.nckhNhanSuRepository = nckhNhanSuRepository;
    }

    /**
     * Save a nckhNhanSu.
     *
     * @param nckhNhanSu the entity to save.
     * @return the persisted entity.
     */
    public NckhNhanSu save(NckhNhanSu nckhNhanSu) {
        log.debug("Request to save NckhNhanSu : {}", nckhNhanSu);
        return nckhNhanSuRepository.save(nckhNhanSu);
    }

    /**
     * Get all the nckhNhanSus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhNhanSu> findAll(Pageable pageable) {
        log.debug("Request to get all NckhNhanSus");
        return nckhNhanSuRepository.findAll(pageable);
    }


    /**
     * Get one nckhNhanSu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NckhNhanSu> findOne(Long id) {
        log.debug("Request to get NckhNhanSu : {}", id);
        return nckhNhanSuRepository.findById(id);
    }

    /**
     * Delete the nckhNhanSu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NckhNhanSu : {}", id);
        nckhNhanSuRepository.deleteById(id);
    }
}
