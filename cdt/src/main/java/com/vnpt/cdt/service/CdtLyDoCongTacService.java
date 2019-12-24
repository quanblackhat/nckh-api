package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtLyDoCongTac;
import com.vnpt.cdt.repository.CdtLyDoCongTacRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtLyDoCongTac}.
 */
@Service
@Transactional
public class CdtLyDoCongTacService {

    private final Logger log = LoggerFactory.getLogger(CdtLyDoCongTacService.class);

    private final CdtLyDoCongTacRepository cdtLyDoCongTacRepository;

    public CdtLyDoCongTacService(CdtLyDoCongTacRepository cdtLyDoCongTacRepository) {
        this.cdtLyDoCongTacRepository = cdtLyDoCongTacRepository;
    }

    /**
     * Save a cdtLyDoCongTac.
     *
     * @param cdtLyDoCongTac the entity to save.
     * @return the persisted entity.
     */
    public CdtLyDoCongTac save(CdtLyDoCongTac cdtLyDoCongTac) {
        log.debug("Request to save CdtLyDoCongTac : {}", cdtLyDoCongTac);
        return cdtLyDoCongTacRepository.save(cdtLyDoCongTac);
    }

    /**
     * Get all the cdtLyDoCongTacs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtLyDoCongTac> findAll(Pageable pageable) {
        log.debug("Request to get all CdtLyDoCongTacs");
        return cdtLyDoCongTacRepository.findAll(pageable);
    }


    /**
     * Get one cdtLyDoCongTac by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtLyDoCongTac> findOne(Long id) {
        log.debug("Request to get CdtLyDoCongTac : {}", id);
        return cdtLyDoCongTacRepository.findById(id);
    }

    /**
     * Delete the cdtLyDoCongTac by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtLyDoCongTac : {}", id);
        cdtLyDoCongTacRepository.deleteById(id);
    }
}
