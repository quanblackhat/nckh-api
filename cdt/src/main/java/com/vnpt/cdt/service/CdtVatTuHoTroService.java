package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtVatTuHoTro;
import com.vnpt.cdt.repository.CdtVatTuHoTroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtVatTuHoTro}.
 */
@Service
@Transactional
public class CdtVatTuHoTroService {

    private final Logger log = LoggerFactory.getLogger(CdtVatTuHoTroService.class);

    private final CdtVatTuHoTroRepository cdtVatTuHoTroRepository;

    public CdtVatTuHoTroService(CdtVatTuHoTroRepository cdtVatTuHoTroRepository) {
        this.cdtVatTuHoTroRepository = cdtVatTuHoTroRepository;
    }

    /**
     * Save a cdtVatTuHoTro.
     *
     * @param cdtVatTuHoTro the entity to save.
     * @return the persisted entity.
     */
    public CdtVatTuHoTro save(CdtVatTuHoTro cdtVatTuHoTro) {
        log.debug("Request to save CdtVatTuHoTro : {}", cdtVatTuHoTro);
        return cdtVatTuHoTroRepository.save(cdtVatTuHoTro);
    }

    /**
     * Get all the cdtVatTuHoTros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtVatTuHoTro> findAll(Pageable pageable) {
        log.debug("Request to get all CdtVatTuHoTros");
        return cdtVatTuHoTroRepository.findAll(pageable);
    }


    /**
     * Get one cdtVatTuHoTro by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtVatTuHoTro> findOne(Long id) {
        log.debug("Request to get CdtVatTuHoTro : {}", id);
        return cdtVatTuHoTroRepository.findById(id);
    }

    /**
     * Delete the cdtVatTuHoTro by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtVatTuHoTro : {}", id);
        cdtVatTuHoTroRepository.deleteById(id);
    }
}
