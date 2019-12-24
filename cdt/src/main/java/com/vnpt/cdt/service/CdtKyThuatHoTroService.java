package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtKyThuatHoTro;
import com.vnpt.cdt.repository.CdtKyThuatHoTroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtKyThuatHoTro}.
 */
@Service
@Transactional
public class CdtKyThuatHoTroService {

    private final Logger log = LoggerFactory.getLogger(CdtKyThuatHoTroService.class);

    private final CdtKyThuatHoTroRepository cdtKyThuatHoTroRepository;

    public CdtKyThuatHoTroService(CdtKyThuatHoTroRepository cdtKyThuatHoTroRepository) {
        this.cdtKyThuatHoTroRepository = cdtKyThuatHoTroRepository;
    }

    /**
     * Save a cdtKyThuatHoTro.
     *
     * @param cdtKyThuatHoTro the entity to save.
     * @return the persisted entity.
     */
    public CdtKyThuatHoTro save(CdtKyThuatHoTro cdtKyThuatHoTro) {
        log.debug("Request to save CdtKyThuatHoTro : {}", cdtKyThuatHoTro);
        return cdtKyThuatHoTroRepository.save(cdtKyThuatHoTro);
    }

    /**
     * Get all the cdtKyThuatHoTros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtKyThuatHoTro> findAll(Pageable pageable) {
        log.debug("Request to get all CdtKyThuatHoTros");
        return cdtKyThuatHoTroRepository.findAll(pageable);
    }


    /**
     * Get one cdtKyThuatHoTro by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtKyThuatHoTro> findOne(Long id) {
        log.debug("Request to get CdtKyThuatHoTro : {}", id);
        return cdtKyThuatHoTroRepository.findById(id);
    }

    /**
     * Delete the cdtKyThuatHoTro by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtKyThuatHoTro : {}", id);
        cdtKyThuatHoTroRepository.deleteById(id);
    }
}
