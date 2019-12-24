package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtKyThuatHoTro;
import com.vnpt.cdt.service.CdtKyThuatHoTroService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtKyThuatHoTroCriteria;
import com.vnpt.cdt.service.CdtKyThuatHoTroQueryService;

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
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtKyThuatHoTro}.
 */
@RestController
@RequestMapping("/api")
public class CdtKyThuatHoTroResource {

    private final Logger log = LoggerFactory.getLogger(CdtKyThuatHoTroResource.class);

    private static final String ENTITY_NAME = "cdtKyThuatHoTro";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtKyThuatHoTroService cdtKyThuatHoTroService;

    private final CdtKyThuatHoTroQueryService cdtKyThuatHoTroQueryService;

    public CdtKyThuatHoTroResource(CdtKyThuatHoTroService cdtKyThuatHoTroService, CdtKyThuatHoTroQueryService cdtKyThuatHoTroQueryService) {
        this.cdtKyThuatHoTroService = cdtKyThuatHoTroService;
        this.cdtKyThuatHoTroQueryService = cdtKyThuatHoTroQueryService;
    }

    /**
     * {@code POST  /cdt-ky-thuat-ho-tros} : Create a new cdtKyThuatHoTro.
     *
     * @param cdtKyThuatHoTro the cdtKyThuatHoTro to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtKyThuatHoTro, or with status {@code 400 (Bad Request)} if the cdtKyThuatHoTro has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-ky-thuat-ho-tros")
    public ResponseEntity<CdtKyThuatHoTro> createCdtKyThuatHoTro(@Valid @RequestBody CdtKyThuatHoTro cdtKyThuatHoTro) throws URISyntaxException {
        log.debug("REST request to save CdtKyThuatHoTro : {}", cdtKyThuatHoTro);
        if (cdtKyThuatHoTro.getId() != null) {
            throw new BadRequestAlertException("A new cdtKyThuatHoTro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtKyThuatHoTro result = cdtKyThuatHoTroService.save(cdtKyThuatHoTro);
        return ResponseEntity.created(new URI("/api/cdt-ky-thuat-ho-tros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-ky-thuat-ho-tros} : Updates an existing cdtKyThuatHoTro.
     *
     * @param cdtKyThuatHoTro the cdtKyThuatHoTro to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtKyThuatHoTro,
     * or with status {@code 400 (Bad Request)} if the cdtKyThuatHoTro is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtKyThuatHoTro couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-ky-thuat-ho-tros")
    public ResponseEntity<CdtKyThuatHoTro> updateCdtKyThuatHoTro(@Valid @RequestBody CdtKyThuatHoTro cdtKyThuatHoTro) throws URISyntaxException {
        log.debug("REST request to update CdtKyThuatHoTro : {}", cdtKyThuatHoTro);
        if (cdtKyThuatHoTro.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtKyThuatHoTro result = cdtKyThuatHoTroService.save(cdtKyThuatHoTro);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtKyThuatHoTro.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-ky-thuat-ho-tros} : get all the cdtKyThuatHoTros.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtKyThuatHoTros in body.
     */
    @GetMapping("/cdt-ky-thuat-ho-tros")
    public ResponseEntity<List<CdtKyThuatHoTro>> getAllCdtKyThuatHoTros(CdtKyThuatHoTroCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtKyThuatHoTros by criteria: {}", criteria);
        Page<CdtKyThuatHoTro> page = cdtKyThuatHoTroQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-ky-thuat-ho-tros/count} : count all the cdtKyThuatHoTros.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-ky-thuat-ho-tros/count")
    public ResponseEntity<Long> countCdtKyThuatHoTros(CdtKyThuatHoTroCriteria criteria) {
        log.debug("REST request to count CdtKyThuatHoTros by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtKyThuatHoTroQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-ky-thuat-ho-tros/:id} : get the "id" cdtKyThuatHoTro.
     *
     * @param id the id of the cdtKyThuatHoTro to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtKyThuatHoTro, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-ky-thuat-ho-tros/{id}")
    public ResponseEntity<CdtKyThuatHoTro> getCdtKyThuatHoTro(@PathVariable Long id) {
        log.debug("REST request to get CdtKyThuatHoTro : {}", id);
        Optional<CdtKyThuatHoTro> cdtKyThuatHoTro = cdtKyThuatHoTroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtKyThuatHoTro);
    }

    /**
     * {@code DELETE  /cdt-ky-thuat-ho-tros/:id} : delete the "id" cdtKyThuatHoTro.
     *
     * @param id the id of the cdtKyThuatHoTro to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-ky-thuat-ho-tros/{id}")
    public ResponseEntity<Void> deleteCdtKyThuatHoTro(@PathVariable Long id) {
        log.debug("REST request to delete CdtKyThuatHoTro : {}", id);
        cdtKyThuatHoTroService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
