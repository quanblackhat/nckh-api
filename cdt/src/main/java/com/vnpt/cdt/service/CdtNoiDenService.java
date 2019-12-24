package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtNoiDen;
import com.vnpt.cdt.repository.CdtNoiDenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtNoiDen}.
 */
@Service
@Transactional
public class CdtNoiDenService {

    private final Logger log = LoggerFactory.getLogger(CdtNoiDenService.class);

    private final CdtNoiDenRepository cdtNoiDenRepository;

    public CdtNoiDenService(CdtNoiDenRepository cdtNoiDenRepository) {
        this.cdtNoiDenRepository = cdtNoiDenRepository;
    }

    /**
     * Save a cdtNoiDen.
     *
     * @param cdtNoiDen the entity to save.
     * @return the persisted entity.
     */
    public CdtNoiDen save(CdtNoiDen cdtNoiDen) {
        log.debug("Request to save CdtNoiDen : {}", cdtNoiDen);
        return cdtNoiDenRepository.save(cdtNoiDen);
    }

    /**
     * Get all the cdtNoiDens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtNoiDen> findAll(Pageable pageable) {
        log.debug("Request to get all CdtNoiDens");
        return cdtNoiDenRepository.findAll(pageable);
    }


    /**
     * Get one cdtNoiDen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtNoiDen> findOne(Long id) {
        log.debug("Request to get CdtNoiDen : {}", id);
        return cdtNoiDenRepository.findById(id);
    }

    /**
     * Delete the cdtNoiDen by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtNoiDen : {}", id);
        cdtNoiDenRepository.deleteById(id);
    }
}
