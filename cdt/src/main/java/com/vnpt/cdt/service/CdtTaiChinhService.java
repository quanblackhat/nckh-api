package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtTaiChinh;
import com.vnpt.cdt.repository.CdtTaiChinhRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtTaiChinh}.
 */
@Service
@Transactional
public class CdtTaiChinhService {

    private final Logger log = LoggerFactory.getLogger(CdtTaiChinhService.class);

    private final CdtTaiChinhRepository cdtTaiChinhRepository;

    public CdtTaiChinhService(CdtTaiChinhRepository cdtTaiChinhRepository) {
        this.cdtTaiChinhRepository = cdtTaiChinhRepository;
    }

    /**
     * Save a cdtTaiChinh.
     *
     * @param cdtTaiChinh the entity to save.
     * @return the persisted entity.
     */
    public CdtTaiChinh save(CdtTaiChinh cdtTaiChinh) {
        log.debug("Request to save CdtTaiChinh : {}", cdtTaiChinh);
        return cdtTaiChinhRepository.save(cdtTaiChinh);
    }

    /**
     * Get all the cdtTaiChinhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtTaiChinh> findAll(Pageable pageable) {
        log.debug("Request to get all CdtTaiChinhs");
        return cdtTaiChinhRepository.findAll(pageable);
    }


    /**
     * Get one cdtTaiChinh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtTaiChinh> findOne(Long id) {
        log.debug("Request to get CdtTaiChinh : {}", id);
        return cdtTaiChinhRepository.findById(id);
    }

    /**
     * Delete the cdtTaiChinh by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtTaiChinh : {}", id);
        cdtTaiChinhRepository.deleteById(id);
    }
}
