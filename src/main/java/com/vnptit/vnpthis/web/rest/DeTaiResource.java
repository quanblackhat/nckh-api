package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.service.DeTaiService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.DeTaiDTO;
import com.vnptit.vnpthis.service.dto.DeTaiCriteria;
import com.vnptit.vnpthis.service.DeTaiQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.DeTai}.
 */
@RestController
@RequestMapping("/api")
public class DeTaiResource {

    private final Logger log = LoggerFactory.getLogger(DeTaiResource.class);

    private static final String ENTITY_NAME = "deTai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeTaiService deTaiService;

    private final DeTaiQueryService deTaiQueryService;

    public DeTaiResource(DeTaiService deTaiService, DeTaiQueryService deTaiQueryService) {
        this.deTaiService = deTaiService;
        this.deTaiQueryService = deTaiQueryService;
    }

    /**
     * {@code POST  /de-tais} : Create a new deTai.
     *
     * @param deTaiDTO the deTaiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deTaiDTO, or with status {@code 400 (Bad Request)} if the deTai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/de-tais")
    public ResponseEntity<DeTaiDTO> createDeTai(@Valid @RequestBody DeTaiDTO deTaiDTO) throws URISyntaxException {
        log.debug("REST request to save DeTai : {}", deTaiDTO);
        if (deTaiDTO.getId() != null) {
            throw new BadRequestAlertException("A new deTai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeTaiDTO result = deTaiService.save(deTaiDTO);
        return ResponseEntity.created(new URI("/api/de-tais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /de-tais} : Updates an existing deTai.
     *
     * @param deTaiDTO the deTaiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deTaiDTO,
     * or with status {@code 400 (Bad Request)} if the deTaiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deTaiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/de-tais")
    public ResponseEntity<DeTaiDTO> updateDeTai(@Valid @RequestBody DeTaiDTO deTaiDTO) throws URISyntaxException {
        log.debug("REST request to update DeTai : {}", deTaiDTO);
        if (deTaiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeTaiDTO result = deTaiService.save(deTaiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deTaiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /de-tais} : get all the deTais.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deTais in body.
     */
    @GetMapping("/de-tais")
    public ResponseEntity<List<DeTaiDTO>> getAllDeTais(DeTaiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DeTais by criteria: {}", criteria);
        Page<DeTaiDTO> page = deTaiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /de-tais/count} : count all the deTais.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/de-tais/count")
    public ResponseEntity<Long> countDeTais(DeTaiCriteria criteria) {
        log.debug("REST request to count DeTais by criteria: {}", criteria);
        return ResponseEntity.ok().body(deTaiQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /de-tais/:id} : get the "id" deTai.
     *
     * @param id the id of the deTaiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deTaiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/de-tais/{id}")
    public ResponseEntity<DeTaiDTO> getDeTai(@PathVariable Long id) {
        log.debug("REST request to get DeTai : {}", id);
        Optional<DeTaiDTO> deTaiDTO = deTaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deTaiDTO);
    }

    /**
     * {@code DELETE  /de-tais/:id} : delete the "id" deTai.
     *
     * @param id the id of the deTaiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/de-tais/{id}")
    public ResponseEntity<Void> deleteDeTai(@PathVariable Long id) {
        log.debug("REST request to delete DeTai : {}", id);
        deTaiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
