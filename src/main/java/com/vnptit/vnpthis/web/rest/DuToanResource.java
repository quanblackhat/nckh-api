package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.domain.nckh.DuToan;
import com.vnptit.vnpthis.service.DuToanService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.DuToanDTO;
import com.vnptit.vnpthis.service.dto.DuToanCriteria;
import com.vnptit.vnpthis.service.DuToanQueryService;

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
 * REST controller for managing {@link DuToan}.
 */
@RestController
@RequestMapping("/api")
public class DuToanResource {

    private final Logger log = LoggerFactory.getLogger(DuToanResource.class);

    private static final String ENTITY_NAME = "duToan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DuToanService duToanService;

    private final DuToanQueryService duToanQueryService;

    public DuToanResource(DuToanService duToanService, DuToanQueryService duToanQueryService) {
        this.duToanService = duToanService;
        this.duToanQueryService = duToanQueryService;
    }

    /**
     * {@code POST  /du-toans} : Create a new duToan.
     *
     * @param duToanDTO the duToanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new duToanDTO, or with status {@code 400 (Bad Request)} if the duToan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/du-toans")
    public ResponseEntity<DuToanDTO> createDuToan(@Valid @RequestBody DuToanDTO duToanDTO) throws URISyntaxException {
        log.debug("REST request to save DuToan : {}", duToanDTO);
        if (duToanDTO.getId() != null) {
            throw new BadRequestAlertException("A new duToan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DuToanDTO result = duToanService.save(duToanDTO);
        return ResponseEntity.created(new URI("/api/du-toans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /du-toans} : Updates an existing duToan.
     *
     * @param duToanDTO the duToanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duToanDTO,
     * or with status {@code 400 (Bad Request)} if the duToanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the duToanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/du-toans")
    public ResponseEntity<DuToanDTO> updateDuToan(@Valid @RequestBody DuToanDTO duToanDTO) throws URISyntaxException {
        log.debug("REST request to update DuToan : {}", duToanDTO);
        if (duToanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DuToanDTO result = duToanService.save(duToanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, duToanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /du-toans} : get all the duToans.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of duToans in body.
     */
    @GetMapping("/du-toans")
    public ResponseEntity<List<DuToanDTO>> getAllDuToans(DuToanCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DuToans by criteria: {}", criteria);
        Page<DuToanDTO> page = duToanQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /du-toans/count} : count all the duToans.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/du-toans/count")
    public ResponseEntity<Long> countDuToans(DuToanCriteria criteria) {
        log.debug("REST request to count DuToans by criteria: {}", criteria);
        return ResponseEntity.ok().body(duToanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /du-toans/:id} : get the "id" duToan.
     *
     * @param id the id of the duToanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the duToanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/du-toans/{id}")
    public ResponseEntity<DuToanDTO> getDuToan(@PathVariable Long id) {
        log.debug("REST request to get DuToan : {}", id);
        Optional<DuToanDTO> duToanDTO = duToanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duToanDTO);
    }

    /**
     * {@code DELETE  /du-toans/:id} : delete the "id" duToan.
     *
     * @param id the id of the duToanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/du-toans/{id}")
    public ResponseEntity<Void> deleteDuToan(@PathVariable Long id) {
        log.debug("REST request to delete DuToan : {}", id);
        duToanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
