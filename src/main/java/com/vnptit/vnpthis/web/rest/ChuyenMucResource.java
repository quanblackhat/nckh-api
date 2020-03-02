package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.service.ChuyenMucService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.ChuyenMucDTO;
import com.vnptit.vnpthis.service.dto.ChuyenMucCriteria;
import com.vnptit.vnpthis.service.ChuyenMucQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.ChuyenMuc}.
 */
@RestController
@RequestMapping("/api")
public class ChuyenMucResource {

    private final Logger log = LoggerFactory.getLogger(ChuyenMucResource.class);

    private static final String ENTITY_NAME = "chuyenMuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChuyenMucService chuyenMucService;

    private final ChuyenMucQueryService chuyenMucQueryService;

    public ChuyenMucResource(ChuyenMucService chuyenMucService, ChuyenMucQueryService chuyenMucQueryService) {
        this.chuyenMucService = chuyenMucService;
        this.chuyenMucQueryService = chuyenMucQueryService;
    }

    /**
     * {@code POST  /chuyen-mucs} : Create a new chuyenMuc.
     *
     * @param chuyenMucDTO the chuyenMucDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chuyenMucDTO, or with status {@code 400 (Bad Request)} if the chuyenMuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chuyen-mucs")
    public ResponseEntity<ChuyenMucDTO> createChuyenMuc(@Valid @RequestBody ChuyenMucDTO chuyenMucDTO) throws URISyntaxException {
        log.debug("REST request to save ChuyenMuc : {}", chuyenMucDTO);
        if (chuyenMucDTO.getId() != null) {
            throw new BadRequestAlertException("A new chuyenMuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChuyenMucDTO result = chuyenMucService.save(chuyenMucDTO);
        return ResponseEntity.created(new URI("/api/chuyen-mucs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chuyen-mucs} : Updates an existing chuyenMuc.
     *
     * @param chuyenMucDTO the chuyenMucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chuyenMucDTO,
     * or with status {@code 400 (Bad Request)} if the chuyenMucDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chuyenMucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chuyen-mucs")
    public ResponseEntity<ChuyenMucDTO> updateChuyenMuc(@Valid @RequestBody ChuyenMucDTO chuyenMucDTO) throws URISyntaxException {
        log.debug("REST request to update ChuyenMuc : {}", chuyenMucDTO);
        if (chuyenMucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChuyenMucDTO result = chuyenMucService.save(chuyenMucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, chuyenMucDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chuyen-mucs} : get all the chuyenMucs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chuyenMucs in body.
     */
    @GetMapping("/chuyen-mucs")
    public ResponseEntity<List<ChuyenMucDTO>> getAllChuyenMucs(ChuyenMucCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ChuyenMucs by criteria: {}", criteria);
        Page<ChuyenMucDTO> page = chuyenMucQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /chuyen-mucs/count} : count all the chuyenMucs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/chuyen-mucs/count")
    public ResponseEntity<Long> countChuyenMucs(ChuyenMucCriteria criteria) {
        log.debug("REST request to count ChuyenMucs by criteria: {}", criteria);
        return ResponseEntity.ok().body(chuyenMucQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /chuyen-mucs/:id} : get the "id" chuyenMuc.
     *
     * @param id the id of the chuyenMucDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chuyenMucDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chuyen-mucs/{id}")
    public ResponseEntity<ChuyenMucDTO> getChuyenMuc(@PathVariable Long id) {
        log.debug("REST request to get ChuyenMuc : {}", id);
        Optional<ChuyenMucDTO> chuyenMucDTO = chuyenMucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chuyenMucDTO);
    }

    /**
     * {@code DELETE  /chuyen-mucs/:id} : delete the "id" chuyenMuc.
     *
     * @param id the id of the chuyenMucDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chuyen-mucs/{id}")
    public ResponseEntity<Void> deleteChuyenMuc(@PathVariable Long id) {
        log.debug("REST request to delete ChuyenMuc : {}", id);
        chuyenMucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
