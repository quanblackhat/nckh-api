package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtKyThuat;
import com.vnpt.cdt.service.CdtKyThuatService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtKyThuatCriteria;
import com.vnpt.cdt.service.CdtKyThuatQueryService;

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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtKyThuat}.
 */
@RestController
@RequestMapping("/api")
public class CdtKyThuatResource {

    private final Logger log = LoggerFactory.getLogger(CdtKyThuatResource.class);

    private static final String ENTITY_NAME = "cdtKyThuat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtKyThuatService cdtKyThuatService;

    private final CdtKyThuatQueryService cdtKyThuatQueryService;

    public CdtKyThuatResource(CdtKyThuatService cdtKyThuatService, CdtKyThuatQueryService cdtKyThuatQueryService) {
        this.cdtKyThuatService = cdtKyThuatService;
        this.cdtKyThuatQueryService = cdtKyThuatQueryService;
    }

    /**
     * {@code POST  /cdt-ky-thuats} : Create a new cdtKyThuat.
     *
     * @param cdtKyThuat the cdtKyThuat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtKyThuat, or with status {@code 400 (Bad Request)} if the cdtKyThuat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-ky-thuats")
    public ResponseEntity<CdtKyThuat> createCdtKyThuat(@RequestBody CdtKyThuat cdtKyThuat) throws URISyntaxException {
        log.debug("REST request to save CdtKyThuat : {}", cdtKyThuat);
        if (cdtKyThuat.getId() != null) {
            throw new BadRequestAlertException("A new cdtKyThuat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtKyThuat result = cdtKyThuatService.save(cdtKyThuat);
        return ResponseEntity.created(new URI("/api/cdt-ky-thuats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-ky-thuats} : Updates an existing cdtKyThuat.
     *
     * @param cdtKyThuat the cdtKyThuat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtKyThuat,
     * or with status {@code 400 (Bad Request)} if the cdtKyThuat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtKyThuat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-ky-thuats")
    public ResponseEntity<CdtKyThuat> updateCdtKyThuat(@RequestBody CdtKyThuat cdtKyThuat) throws URISyntaxException {
        log.debug("REST request to update CdtKyThuat : {}", cdtKyThuat);
        if (cdtKyThuat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtKyThuat result = cdtKyThuatService.save(cdtKyThuat);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtKyThuat.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-ky-thuats} : get all the cdtKyThuats.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtKyThuats in body.
     */
    @GetMapping("/cdt-ky-thuats")
    public ResponseEntity<List<CdtKyThuat>> getAllCdtKyThuats(CdtKyThuatCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtKyThuats by criteria: {}", criteria);
        Page<CdtKyThuat> page = cdtKyThuatQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-ky-thuats/count} : count all the cdtKyThuats.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-ky-thuats/count")
    public ResponseEntity<Long> countCdtKyThuats(CdtKyThuatCriteria criteria) {
        log.debug("REST request to count CdtKyThuats by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtKyThuatQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-ky-thuats/:id} : get the "id" cdtKyThuat.
     *
     * @param id the id of the cdtKyThuat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtKyThuat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-ky-thuats/{id}")
    public ResponseEntity<CdtKyThuat> getCdtKyThuat(@PathVariable Long id) {
        log.debug("REST request to get CdtKyThuat : {}", id);
        Optional<CdtKyThuat> cdtKyThuat = cdtKyThuatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtKyThuat);
    }

    /**
     * {@code DELETE  /cdt-ky-thuats/:id} : delete the "id" cdtKyThuat.
     *
     * @param id the id of the cdtKyThuat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-ky-thuats/{id}")
    public ResponseEntity<Void> deleteCdtKyThuat(@PathVariable Long id) {
        log.debug("REST request to delete CdtKyThuat : {}", id);
        cdtKyThuatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
