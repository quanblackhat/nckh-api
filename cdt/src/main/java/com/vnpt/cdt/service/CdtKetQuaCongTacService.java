package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtKetQuaCongTac;
import com.vnpt.cdt.repository.CdtKetQuaCongTacRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtKetQuaCongTac}.
 */
@Service
@Transactional
public class CdtKetQuaCongTacService {

    private final Logger log = LoggerFactory.getLogger(CdtKetQuaCongTacService.class);

    private final CdtKetQuaCongTacRepository cdtKetQuaCongTacRepository;

    public CdtKetQuaCongTacService(CdtKetQuaCongTacRepository cdtKetQuaCongTacRepository) {
        this.cdtKetQuaCongTacRepository = cdtKetQuaCongTacRepository;
    }

    /**
     * Save a cdtKetQuaCongTac.
     *
     * @param cdtKetQuaCongTac the entity to save.
     * @return the persisted entity.
     */
    public CdtKetQuaCongTac save(CdtKetQuaCongTac cdtKetQuaCongTac) {
        log.debug("Request to save CdtKetQuaCongTac : {}", cdtKetQuaCongTac);
        return cdtKetQuaCongTacRepository.save(cdtKetQuaCongTac);
    }

    /**
     * Get all the cdtKetQuaCongTacs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtKetQuaCongTac> findAll(Pageable pageable) {
        log.debug("Request to get all CdtKetQuaCongTacs");
        return cdtKetQuaCongTacRepository.findAll(pageable);
    }


    /**
     * Get one cdtKetQuaCongTac by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtKetQuaCongTac> findOne(Long id) {
        log.debug("Request to get CdtKetQuaCongTac : {}", id);
        return cdtKetQuaCongTacRepository.findById(id);
    }

    /**
     * Delete the cdtKetQuaCongTac by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtKetQuaCongTac : {}", id);
        cdtKetQuaCongTacRepository.deleteById(id);
    }
}
