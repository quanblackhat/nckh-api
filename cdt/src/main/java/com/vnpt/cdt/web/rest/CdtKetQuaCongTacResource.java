package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtKetQuaCongTac;
import com.vnpt.cdt.service.CdtKetQuaCongTacService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtKetQuaCongTacCriteria;
import com.vnpt.cdt.service.CdtKetQuaCongTacQueryService;

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
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtKetQuaCongTac}.
 */
@RestController
@RequestMapping("/api")
public class CdtKetQuaCongTacResource {

    private final Logger log = LoggerFactory.getLogger(CdtKetQuaCongTacResource.class);

    private static final String ENTITY_NAME = "cdtKetQuaCongTac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtKetQuaCongTacService cdtKetQuaCongTacService;

    private final CdtKetQuaCongTacQueryService cdtKetQuaCongTacQueryService;

    public CdtKetQuaCongTacResource(CdtKetQuaCongTacService cdtKetQuaCongTacService, CdtKetQuaCongTacQueryService cdtKetQuaCongTacQueryService) {
        this.cdtKetQuaCongTacService = cdtKetQuaCongTacService;
        this.cdtKetQuaCongTacQueryService = cdtKetQuaCongTacQueryService;
    }

    /**
     * {@code POST  /cdt-ket-qua-cong-tacs} : Create a new cdtKetQuaCongTac.
     *
     * @param cdtKetQuaCongTac the cdtKetQuaCongTac to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtKetQuaCongTac, or with status {@code 400 (Bad Request)} if the cdtKetQuaCongTac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-ket-qua-cong-tacs")
    public ResponseEntity<CdtKetQuaCongTac> createCdtKetQuaCongTac(@Valid @RequestBody CdtKetQuaCongTac cdtKetQuaCongTac) throws URISyntaxException {
        log.debug("REST request to save CdtKetQuaCongTac : {}", cdtKetQuaCongTac);
        if (cdtKetQuaCongTac.getId() != null) {
            throw new BadRequestAlertException("A new cdtKetQuaCongTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtKetQuaCongTac result = cdtKetQuaCongTacService.save(cdtKetQuaCongTac);
        return ResponseEntity.created(new URI("/api/cdt-ket-qua-cong-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-ket-qua-cong-tacs} : Updates an existing cdtKetQuaCongTac.
     *
     * @param cdtKetQuaCongTac the cdtKetQuaCongTac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtKetQuaCongTac,
     * or with status {@code 400 (Bad Request)} if the cdtKetQuaCongTac is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtKetQuaCongTac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-ket-qua-cong-tacs")
    public ResponseEntity<CdtKetQuaCongTac> updateCdtKetQuaCongTac(@Valid @RequestBody CdtKetQuaCongTac cdtKetQuaCongTac) throws URISyntaxException {
        log.debug("REST request to update CdtKetQuaCongTac : {}", cdtKetQuaCongTac);
        if (cdtKetQuaCongTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtKetQuaCongTac result = cdtKetQuaCongTacService.save(cdtKetQuaCongTac);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtKetQuaCongTac.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-ket-qua-cong-tacs} : get all the cdtKetQuaCongTacs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtKetQuaCongTacs in body.
     */
    @GetMapping("/cdt-ket-qua-cong-tacs")
    public ResponseEntity<List<CdtKetQuaCongTac>> getAllCdtKetQuaCongTacs(CdtKetQuaCongTacCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtKetQuaCongTacs by criteria: {}", criteria);
        Page<CdtKetQuaCongTac> page = cdtKetQuaCongTacQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-ket-qua-cong-tacs/count} : count all the cdtKetQuaCongTacs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-ket-qua-cong-tacs/count")
    public ResponseEntity<Long> countCdtKetQuaCongTacs(CdtKetQuaCongTacCriteria criteria) {
        log.debug("REST request to count CdtKetQuaCongTacs by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtKetQuaCongTacQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-ket-qua-cong-tacs/:id} : get the "id" cdtKetQuaCongTac.
     *
     * @param id the id of the cdtKetQuaCongTac to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtKetQuaCongTac, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-ket-qua-cong-tacs/{id}")
    public ResponseEntity<CdtKetQuaCongTac> getCdtKetQuaCongTac(@PathVariable Long id) {
        log.debug("REST request to get CdtKetQuaCongTac : {}", id);
        Optional<CdtKetQuaCongTac> cdtKetQuaCongTac = cdtKetQuaCongTacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtKetQuaCongTac);
    }

    /**
     * {@code DELETE  /cdt-ket-qua-cong-tacs/:id} : delete the "id" cdtKetQuaCongTac.
     *
     * @param id the id of the cdtKetQuaCongTac to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-ket-qua-cong-tacs/{id}")
    public ResponseEntity<Void> deleteCdtKetQuaCongTac(@PathVariable Long id) {
        log.debug("REST request to delete CdtKetQuaCongTac : {}", id);
        cdtKetQuaCongTacService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
