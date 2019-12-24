package com.vnpt.nckh.service;

import com.vnpt.nckh.domain.NckhDetai;
import com.vnpt.nckh.repository.NckhDetaiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NckhDetai}.
 */
@Service
@Transactional
public class NckhDetaiService {

    private final Logger log = LoggerFactory.getLogger(NckhDetaiService.class);

    private final NckhDetaiRepository nckhDetaiRepository;

    public NckhDetaiService(NckhDetaiRepository nckhDetaiRepository) {
        this.nckhDetaiRepository = nckhDetaiRepository;
    }

    /**
     * Save a nckhDetai.
     *
     * @param nckhDetai the entity to save.
     * @return the persisted entity.
     */
    public NckhDetai save(NckhDetai nckhDetai) {
        log.debug("Request to save NckhDetai : {}", nckhDetai);
        return nckhDetaiRepository.save(nckhDetai);
    }

    /**
     * Get all the nckhDetais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhDetai> findAll(Pageable pageable) {
        log.debug("Request to get all NckhDetais");
        return nckhDetaiRepository.findAll(pageable);
    }


    /**
     * Get one nckhDetai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NckhDetai> findOne(Long id) {
        log.debug("Request to get NckhDetai : {}", id);
        return nckhDetaiRepository.findById(id);
    }

    /**
     * Delete the nckhDetai by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NckhDetai : {}", id);
        nckhDetaiRepository.deleteById(id);
    }
}
