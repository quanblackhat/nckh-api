package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtNoiDenCongTac;
import com.vnpt.cdt.repository.CdtNoiDenCongTacRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtNoiDenCongTac}.
 */
@Service
@Transactional
public class CdtNoiDenCongTacService {

    private final Logger log = LoggerFactory.getLogger(CdtNoiDenCongTacService.class);

    private final CdtNoiDenCongTacRepository cdtNoiDenCongTacRepository;

    public CdtNoiDenCongTacService(CdtNoiDenCongTacRepository cdtNoiDenCongTacRepository) {
        this.cdtNoiDenCongTacRepository = cdtNoiDenCongTacRepository;
    }

    /**
     * Save a cdtNoiDenCongTac.
     *
     * @param cdtNoiDenCongTac the entity to save.
     * @return the persisted entity.
     */
    public CdtNoiDenCongTac save(CdtNoiDenCongTac cdtNoiDenCongTac) {
        log.debug("Request to save CdtNoiDenCongTac : {}", cdtNoiDenCongTac);
        return cdtNoiDenCongTacRepository.save(cdtNoiDenCongTac);
    }

    /**
     * Get all the cdtNoiDenCongTacs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtNoiDenCongTac> findAll(Pageable pageable) {
        log.debug("Request to get all CdtNoiDenCongTacs");
        return cdtNoiDenCongTacRepository.findAll(pageable);
    }


    /**
     * Get one cdtNoiDenCongTac by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtNoiDenCongTac> findOne(Long id) {
        log.debug("Request to get CdtNoiDenCongTac : {}", id);
        return cdtNoiDenCongTacRepository.findById(id);
    }

    /**
     * Delete the cdtNoiDenCongTac by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtNoiDenCongTac : {}", id);
        cdtNoiDenCongTacRepository.deleteById(id);
    }
}
