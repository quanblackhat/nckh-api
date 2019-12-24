package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtVatTu;
import com.vnpt.cdt.service.CdtVatTuService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtVatTuCriteria;
import com.vnpt.cdt.service.CdtVatTuQueryService;

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
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtVatTu}.
 */
@RestController
@RequestMapping("/api")
public class CdtVatTuResource {

    private final Logger log = LoggerFactory.getLogger(CdtVatTuResource.class);

    private static final String ENTITY_NAME = "cdtVatTu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtVatTuService cdtVatTuService;

    private final CdtVatTuQueryService cdtVatTuQueryService;

    public CdtVatTuResource(CdtVatTuService cdtVatTuService, CdtVatTuQueryService cdtVatTuQueryService) {
        this.cdtVatTuService = cdtVatTuService;
        this.cdtVatTuQueryService = cdtVatTuQueryService;
    }

    /**
     * {@code POST  /cdt-vat-tus} : Create a new cdtVatTu.
     *
     * @param cdtVatTu the cdtVatTu to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtVatTu, or with status {@code 400 (Bad Request)} if the cdtVatTu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-vat-tus")
    public ResponseEntity<CdtVatTu> createCdtVatTu(@RequestBody CdtVatTu cdtVatTu) throws URISyntaxException {
        log.debug("REST request to save CdtVatTu : {}", cdtVatTu);
        if (cdtVatTu.getId() != null) {
            throw new BadRequestAlertException("A new cdtVatTu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtVatTu result = cdtVatTuService.save(cdtVatTu);
        return ResponseEntity.created(new URI("/api/cdt-vat-tus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-vat-tus} : Updates an existing cdtVatTu.
     *
     * @param cdtVatTu the cdtVatTu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtVatTu,
     * or with status {@code 400 (Bad Request)} if the cdtVatTu is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtVatTu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-vat-tus")
    public ResponseEntity<CdtVatTu> updateCdtVatTu(@RequestBody CdtVatTu cdtVatTu) throws URISyntaxException {
        log.debug("REST request to update CdtVatTu : {}", cdtVatTu);
        if (cdtVatTu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtVatTu result = cdtVatTuService.save(cdtVatTu);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtVatTu.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-vat-tus} : get all the cdtVatTus.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtVatTus in body.
     */
    @GetMapping("/cdt-vat-tus")
    public ResponseEntity<List<CdtVatTu>> getAllCdtVatTus(CdtVatTuCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtVatTus by criteria: {}", criteria);
        Page<CdtVatTu> page = cdtVatTuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-vat-tus/count} : count all the cdtVatTus.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-vat-tus/count")
    public ResponseEntity<Long> countCdtVatTus(CdtVatTuCriteria criteria) {
        log.debug("REST request to count CdtVatTus by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtVatTuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-vat-tus/:id} : get the "id" cdtVatTu.
     *
     * @param id the id of the cdtVatTu to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtVatTu, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-vat-tus/{id}")
    public ResponseEntity<CdtVatTu> getCdtVatTu(@PathVariable Long id) {
        log.debug("REST request to get CdtVatTu : {}", id);
        Optional<CdtVatTu> cdtVatTu = cdtVatTuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtVatTu);
    }

    /**
     * {@code DELETE  /cdt-vat-tus/:id} : delete the "id" cdtVatTu.
     *
     * @param id the id of the cdtVatTu to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-vat-tus/{id}")
    public ResponseEntity<Void> deleteCdtVatTu(@PathVariable Long id) {
        log.debug("REST request to delete CdtVatTu : {}", id);
        cdtVatTuService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
