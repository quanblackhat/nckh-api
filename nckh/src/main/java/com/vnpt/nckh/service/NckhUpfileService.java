package com.vnpt.nckh.service;

import com.vnpt.nckh.domain.NckhUpfile;
import com.vnpt.nckh.repository.NckhUpfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NckhUpfile}.
 */
@Service
@Transactional
public class NckhUpfileService {

    private final Logger log = LoggerFactory.getLogger(NckhUpfileService.class);

    private final NckhUpfileRepository nckhUpfileRepository;

    public NckhUpfileService(NckhUpfileRepository nckhUpfileRepository) {
        this.nckhUpfileRepository = nckhUpfileRepository;
    }

    /**
     * Save a nckhUpfile.
     *
     * @param nckhUpfile the entity to save.
     * @return the persisted entity.
     */
    public NckhUpfile save(NckhUpfile nckhUpfile) {
        log.debug("Request to save NckhUpfile : {}", nckhUpfile);
        return nckhUpfileRepository.save(nckhUpfile);
    }

    /**
     * Get all the nckhUpfiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhUpfile> findAll(Pageable pageable) {
        log.debug("Request to get all NckhUpfiles");
        return nckhUpfileRepository.findAll(pageable);
    }


    /**
     * Get one nckhUpfile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NckhUpfile> findOne(Long id) {
        log.debug("Request to get NckhUpfile : {}", id);
        return nckhUpfileRepository.findById(id);
    }

    /**
     * Delete the nckhUpfile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NckhUpfile : {}", id);
        nckhUpfileRepository.deleteById(id);
    }
}
