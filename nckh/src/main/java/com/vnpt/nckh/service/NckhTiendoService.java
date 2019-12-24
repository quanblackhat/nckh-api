package com.vnpt.nckh.service;

import com.vnpt.nckh.domain.NckhTiendo;
import com.vnpt.nckh.repository.NckhTiendoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NckhTiendo}.
 */
@Service
@Transactional
public class NckhTiendoService {

    private final Logger log = LoggerFactory.getLogger(NckhTiendoService.class);

    private final NckhTiendoRepository nckhTiendoRepository;

    public NckhTiendoService(NckhTiendoRepository nckhTiendoRepository) {
        this.nckhTiendoRepository = nckhTiendoRepository;
    }

    /**
     * Save a nckhTiendo.
     *
     * @param nckhTiendo the entity to save.
     * @return the persisted entity.
     */
    public NckhTiendo save(NckhTiendo nckhTiendo) {
        log.debug("Request to save NckhTiendo : {}", nckhTiendo);
        return nckhTiendoRepository.save(nckhTiendo);
    }

    /**
     * Get all the nckhTiendos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NckhTiendo> findAll(Pageable pageable) {
        log.debug("Request to get all NckhTiendos");
        return nckhTiendoRepository.findAll(pageable);
    }


    /**
     * Get one nckhTiendo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NckhTiendo> findOne(Long id) {
        log.debug("Request to get NckhTiendo : {}", id);
        return nckhTiendoRepository.findById(id);
    }

    /**
     * Delete the nckhTiendo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NckhTiendo : {}", id);
        nckhTiendoRepository.deleteById(id);
    }
}
