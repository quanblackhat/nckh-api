package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.domain.NckhTiendo;
import com.vnpt.nckh.service.NckhTiendoService;
import com.vnpt.nckh.web.rest.errors.BadRequestAlertException;
import com.vnpt.nckh.service.dto.NckhTiendoCriteria;
import com.vnpt.nckh.service.NckhTiendoQueryService;

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
 * REST controller for managing {@link com.vnpt.nckh.domain.NckhTiendo}.
 */
@RestController
@RequestMapping("/api")
public class NckhTiendoResource {

    private final Logger log = LoggerFactory.getLogger(NckhTiendoResource.class);

    private static final String ENTITY_NAME = "nckhTiendo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NckhTiendoService nckhTiendoService;

    private final NckhTiendoQueryService nckhTiendoQueryService;

    public NckhTiendoResource(NckhTiendoService nckhTiendoService, NckhTiendoQueryService nckhTiendoQueryService) {
        this.nckhTiendoService = nckhTiendoService;
        this.nckhTiendoQueryService = nckhTiendoQueryService;
    }

    /**
     * {@code POST  /nckh-tiendos} : Create a new nckhTiendo.
     *
     * @param nckhTiendo the nckhTiendo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nckhTiendo, or with status {@code 400 (Bad Request)} if the nckhTiendo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nckh-tiendos")
    public ResponseEntity<NckhTiendo> createNckhTiendo(@Valid @RequestBody NckhTiendo nckhTiendo) throws URISyntaxException {
        log.debug("REST request to save NckhTiendo : {}", nckhTiendo);
        if (nckhTiendo.getId() != null) {
            throw new BadRequestAlertException("A new nckhTiendo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NckhTiendo result = nckhTiendoService.save(nckhTiendo);
        return ResponseEntity.created(new URI("/api/nckh-tiendos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nckh-tiendos} : Updates an existing nckhTiendo.
     *
     * @param nckhTiendo the nckhTiendo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nckhTiendo,
     * or with status {@code 400 (Bad Request)} if the nckhTiendo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nckhTiendo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nckh-tiendos")
    public ResponseEntity<NckhTiendo> updateNckhTiendo(@Valid @RequestBody NckhTiendo nckhTiendo) throws URISyntaxException {
        log.debug("REST request to update NckhTiendo : {}", nckhTiendo);
        if (nckhTiendo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NckhTiendo result = nckhTiendoService.save(nckhTiendo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nckhTiendo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nckh-tiendos} : get all the nckhTiendos.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nckhTiendos in body.
     */
    @GetMapping("/nckh-tiendos")
    public ResponseEntity<List<NckhTiendo>> getAllNckhTiendos(NckhTiendoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NckhTiendos by criteria: {}", criteria);
        Page<NckhTiendo> page = nckhTiendoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /nckh-tiendos/count} : count all the nckhTiendos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/nckh-tiendos/count")
    public ResponseEntity<Long> countNckhTiendos(NckhTiendoCriteria criteria) {
        log.debug("REST request to count NckhTiendos by criteria: {}", criteria);
        return ResponseEntity.ok().body(nckhTiendoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nckh-tiendos/:id} : get the "id" nckhTiendo.
     *
     * @param id the id of the nckhTiendo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nckhTiendo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nckh-tiendos/{id}")
    public ResponseEntity<NckhTiendo> getNckhTiendo(@PathVariable Long id) {
        log.debug("REST request to get NckhTiendo : {}", id);
        Optional<NckhTiendo> nckhTiendo = nckhTiendoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nckhTiendo);
    }

    /**
     * {@code DELETE  /nckh-tiendos/:id} : delete the "id" nckhTiendo.
     *
     * @param id the id of the nckhTiendo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nckh-tiendos/{id}")
    public ResponseEntity<Void> deleteNckhTiendo(@PathVariable Long id) {
        log.debug("REST request to delete NckhTiendo : {}", id);
        nckhTiendoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
