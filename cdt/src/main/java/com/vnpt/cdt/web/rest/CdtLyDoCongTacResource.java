package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtLyDoCongTac;
import com.vnpt.cdt.service.CdtLyDoCongTacService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtLyDoCongTacCriteria;
import com.vnpt.cdt.service.CdtLyDoCongTacQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtLyDoCongTac}.
 */
@RestController
@RequestMapping("/api")
public class CdtLyDoCongTacResource {

    private final Logger log = LoggerFactory.getLogger(CdtLyDoCongTacResource.class);

    private static final String ENTITY_NAME = "cdtLyDoCongTac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtLyDoCongTacService cdtLyDoCongTacService;

    private final CdtLyDoCongTacQueryService cdtLyDoCongTacQueryService;

    public CdtLyDoCongTacResource(CdtLyDoCongTacService cdtLyDoCongTacService, CdtLyDoCongTacQueryService cdtLyDoCongTacQueryService) {
        this.cdtLyDoCongTacService = cdtLyDoCongTacService;
        this.cdtLyDoCongTacQueryService = cdtLyDoCongTacQueryService;
    }

    /**
     * {@code POST  /cdt-ly-do-cong-tacs} : Create a new cdtLyDoCongTac.
     *
     * @param cdtLyDoCongTac the cdtLyDoCongTac to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtLyDoCongTac, or with status {@code 400 (Bad Request)} if the cdtLyDoCongTac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-ly-do-cong-tacs")
    public ResponseEntity<CdtLyDoCongTac> createCdtLyDoCongTac(@Valid @RequestBody CdtLyDoCongTac cdtLyDoCongTac) throws URISyntaxException {
        log.debug("REST request to save CdtLyDoCongTac : {}", cdtLyDoCongTac);
        if (cdtLyDoCongTac.getId() != null) {
            throw new BadRequestAlertException("A new cdtLyDoCongTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtLyDoCongTac result = cdtLyDoCongTacService.save(cdtLyDoCongTac);
        return ResponseEntity.created(new URI("/api/cdt-ly-do-cong-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-ly-do-cong-tacs} : Updates an existing cdtLyDoCongTac.
     *
     * @param cdtLyDoCongTac the cdtLyDoCongTac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtLyDoCongTac,
     * or with status {@code 400 (Bad Request)} if the cdtLyDoCongTac is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtLyDoCongTac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-ly-do-cong-tacs")
    public ResponseEntity<CdtLyDoCongTac> updateCdtLyDoCongTac(@Valid @RequestBody CdtLyDoCongTac cdtLyDoCongTac) throws URISyntaxException {
        log.debug("REST request to update CdtLyDoCongTac : {}", cdtLyDoCongTac);
        if (cdtLyDoCongTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtLyDoCongTac result = cdtLyDoCongTacService.save(cdtLyDoCongTac);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtLyDoCongTac.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-ly-do-cong-tacs} : get all the cdtLyDoCongTacs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtLyDoCongTacs in body.
     */
    @GetMapping("/cdt-ly-do-cong-tacs")
    public ResponseEntity<List<CdtLyDoCongTac>> getAllCdtLyDoCongTacs(CdtLyDoCongTacCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtLyDoCongTacs by criteria: {}", criteria);
        Page<CdtLyDoCongTac> page = cdtLyDoCongTacQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-ly-do-cong-tacs/count} : count all the cdtLyDoCongTacs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-ly-do-cong-tacs/count")
    public ResponseEntity<Long> countCdtLyDoCongTacs(CdtLyDoCongTacCriteria criteria) {
        log.debug("REST request to count CdtLyDoCongTacs by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtLyDoCongTacQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-ly-do-cong-tacs/:id} : get the "id" cdtLyDoCongTac.
     *
     * @param id the id of the cdtLyDoCongTac to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtLyDoCongTac, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-ly-do-cong-tacs/{id}")
    public ResponseEntity<CdtLyDoCongTac> getCdtLyDoCongTac(@PathVariable Long id) {
        log.debug("REST request to get CdtLyDoCongTac : {}", id);
        Optional<CdtLyDoCongTac> cdtLyDoCongTac = cdtLyDoCongTacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtLyDoCongTac);
    }

    /**
     * {@code DELETE  /cdt-ly-do-cong-tacs/:id} : delete the "id" cdtLyDoCongTac.
     *
     * @param id the id of the cdtLyDoCongTac to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-ly-do-cong-tacs/{id}")
    public ResponseEntity<Void> deleteCdtLyDoCongTac(@PathVariable Long id) {
        log.debug("REST request to delete CdtLyDoCongTac : {}", id);
        cdtLyDoCongTacService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
