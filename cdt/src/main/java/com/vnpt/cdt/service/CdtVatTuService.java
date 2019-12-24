package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtVatTu;
import com.vnpt.cdt.repository.CdtVatTuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtVatTu}.
 */
@Service
@Transactional
public class CdtVatTuService {

    private final Logger log = LoggerFactory.getLogger(CdtVatTuService.class);

    private final CdtVatTuRepository cdtVatTuRepository;

    public CdtVatTuService(CdtVatTuRepository cdtVatTuRepository) {
        this.cdtVatTuRepository = cdtVatTuRepository;
    }

    /**
     * Save a cdtVatTu.
     *
     * @param cdtVatTu the entity to save.
     * @return the persisted entity.
     */
    public CdtVatTu save(CdtVatTu cdtVatTu) {
        log.debug("Request to save CdtVatTu : {}", cdtVatTu);
        return cdtVatTuRepository.save(cdtVatTu);
    }

    /**
     * Get all the cdtVatTus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtVatTu> findAll(Pageable pageable) {
        log.debug("Request to get all CdtVatTus");
        return cdtVatTuRepository.findAll(pageable);
    }


    /**
     * Get one cdtVatTu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtVatTu> findOne(Long id) {
        log.debug("Request to get CdtVatTu : {}", id);
        return cdtVatTuRepository.findById(id);
    }

    /**
     * Delete the cdtVatTu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtVatTu : {}", id);
        cdtVatTuRepository.deleteById(id);
    }
}
