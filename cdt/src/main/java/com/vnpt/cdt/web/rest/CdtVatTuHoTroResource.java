package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtVatTuHoTro;
import com.vnpt.cdt.service.CdtVatTuHoTroService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtVatTuHoTroCriteria;
import com.vnpt.cdt.service.CdtVatTuHoTroQueryService;

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
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtVatTuHoTro}.
 */
@RestController
@RequestMapping("/api")
public class CdtVatTuHoTroResource {

    private final Logger log = LoggerFactory.getLogger(CdtVatTuHoTroResource.class);

    private static final String ENTITY_NAME = "cdtVatTuHoTro";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtVatTuHoTroService cdtVatTuHoTroService;

    private final CdtVatTuHoTroQueryService cdtVatTuHoTroQueryService;

    public CdtVatTuHoTroResource(CdtVatTuHoTroService cdtVatTuHoTroService, CdtVatTuHoTroQueryService cdtVatTuHoTroQueryService) {
        this.cdtVatTuHoTroService = cdtVatTuHoTroService;
        this.cdtVatTuHoTroQueryService = cdtVatTuHoTroQueryService;
    }

    /**
     * {@code POST  /cdt-vat-tu-ho-tros} : Create a new cdtVatTuHoTro.
     *
     * @param cdtVatTuHoTro the cdtVatTuHoTro to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtVatTuHoTro, or with status {@code 400 (Bad Request)} if the cdtVatTuHoTro has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-vat-tu-ho-tros")
    public ResponseEntity<CdtVatTuHoTro> createCdtVatTuHoTro(@Valid @RequestBody CdtVatTuHoTro cdtVatTuHoTro) throws URISyntaxException {
        log.debug("REST request to save CdtVatTuHoTro : {}", cdtVatTuHoTro);
        if (cdtVatTuHoTro.getId() != null) {
            throw new BadRequestAlertException("A new cdtVatTuHoTro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtVatTuHoTro result = cdtVatTuHoTroService.save(cdtVatTuHoTro);
        return ResponseEntity.created(new URI("/api/cdt-vat-tu-ho-tros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-vat-tu-ho-tros} : Updates an existing cdtVatTuHoTro.
     *
     * @param cdtVatTuHoTro the cdtVatTuHoTro to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtVatTuHoTro,
     * or with status {@code 400 (Bad Request)} if the cdtVatTuHoTro is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtVatTuHoTro couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-vat-tu-ho-tros")
    public ResponseEntity<CdtVatTuHoTro> updateCdtVatTuHoTro(@Valid @RequestBody CdtVatTuHoTro cdtVatTuHoTro) throws URISyntaxException {
        log.debug("REST request to update CdtVatTuHoTro : {}", cdtVatTuHoTro);
        if (cdtVatTuHoTro.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtVatTuHoTro result = cdtVatTuHoTroService.save(cdtVatTuHoTro);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtVatTuHoTro.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-vat-tu-ho-tros} : get all the cdtVatTuHoTros.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtVatTuHoTros in body.
     */
    @GetMapping("/cdt-vat-tu-ho-tros")
    public ResponseEntity<List<CdtVatTuHoTro>> getAllCdtVatTuHoTros(CdtVatTuHoTroCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtVatTuHoTros by criteria: {}", criteria);
        Page<CdtVatTuHoTro> page = cdtVatTuHoTroQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-vat-tu-ho-tros/count} : count all the cdtVatTuHoTros.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-vat-tu-ho-tros/count")
    public ResponseEntity<Long> countCdtVatTuHoTros(CdtVatTuHoTroCriteria criteria) {
        log.debug("REST request to count CdtVatTuHoTros by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtVatTuHoTroQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-vat-tu-ho-tros/:id} : get the "id" cdtVatTuHoTro.
     *
     * @param id the id of the cdtVatTuHoTro to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtVatTuHoTro, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-vat-tu-ho-tros/{id}")
    public ResponseEntity<CdtVatTuHoTro> getCdtVatTuHoTro(@PathVariable Long id) {
        log.debug("REST request to get CdtVatTuHoTro : {}", id);
        Optional<CdtVatTuHoTro> cdtVatTuHoTro = cdtVatTuHoTroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtVatTuHoTro);
    }

    /**
     * {@code DELETE  /cdt-vat-tu-ho-tros/:id} : delete the "id" cdtVatTuHoTro.
     *
     * @param id the id of the cdtVatTuHoTro to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-vat-tu-ho-tros/{id}")
    public ResponseEntity<Void> deleteCdtVatTuHoTro(@PathVariable Long id) {
        log.debug("REST request to delete CdtVatTuHoTro : {}", id);
        cdtVatTuHoTroService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
