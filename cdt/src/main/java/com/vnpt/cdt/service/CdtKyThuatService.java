package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtKyThuat;
import com.vnpt.cdt.repository.CdtKyThuatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtKyThuat}.
 */
@Service
@Transactional
public class CdtKyThuatService {

    private final Logger log = LoggerFactory.getLogger(CdtKyThuatService.class);

    private final CdtKyThuatRepository cdtKyThuatRepository;

    public CdtKyThuatService(CdtKyThuatRepository cdtKyThuatRepository) {
        this.cdtKyThuatRepository = cdtKyThuatRepository;
    }

    /**
     * Save a cdtKyThuat.
     *
     * @param cdtKyThuat the entity to save.
     * @return the persisted entity.
     */
    public CdtKyThuat save(CdtKyThuat cdtKyThuat) {
        log.debug("Request to save CdtKyThuat : {}", cdtKyThuat);
        return cdtKyThuatRepository.save(cdtKyThuat);
    }

    /**
     * Get all the cdtKyThuats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtKyThuat> findAll(Pageable pageable) {
        log.debug("Request to get all CdtKyThuats");
        return cdtKyThuatRepository.findAll(pageable);
    }


    /**
     * Get one cdtKyThuat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtKyThuat> findOne(Long id) {
        log.debug("Request to get CdtKyThuat : {}", id);
        return cdtKyThuatRepository.findById(id);
    }

    /**
     * Delete the cdtKyThuat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtKyThuat : {}", id);
        cdtKyThuatRepository.deleteById(id);
    }
}
