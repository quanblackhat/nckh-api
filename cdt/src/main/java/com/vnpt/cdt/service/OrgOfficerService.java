package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.OrgOfficer;
import com.vnpt.cdt.repository.OrgOfficerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrgOfficer}.
 */
@Service
@Transactional
public class OrgOfficerService {

    private final Logger log = LoggerFactory.getLogger(OrgOfficerService.class);

    private final OrgOfficerRepository orgOfficerRepository;

    public OrgOfficerService(OrgOfficerRepository orgOfficerRepository) {
        this.orgOfficerRepository = orgOfficerRepository;
    }

    /**
     * Save a orgOfficer.
     *
     * @param orgOfficer the entity to save.
     * @return the persisted entity.
     */
    public OrgOfficer save(OrgOfficer orgOfficer) {
        log.debug("Request to save OrgOfficer : {}", orgOfficer);
        return orgOfficerRepository.save(orgOfficer);
    }

    /**
     * Get all the orgOfficers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrgOfficer> findAll(Pageable pageable) {
        log.debug("Request to get all OrgOfficers");
        return orgOfficerRepository.findAll(pageable);
    }


    /**
     * Get one orgOfficer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrgOfficer> findOne(Long id) {
        log.debug("Request to get OrgOfficer : {}", id);
        return orgOfficerRepository.findById(id);
    }

    /**
     * Delete the orgOfficer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrgOfficer : {}", id);
        orgOfficerRepository.deleteById(id);
    }
}
