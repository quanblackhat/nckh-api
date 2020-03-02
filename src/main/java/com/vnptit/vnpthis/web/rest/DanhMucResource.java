package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.service.DanhMucService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.DanhMucDTO;
import com.vnptit.vnpthis.service.dto.DanhMucCriteria;
import com.vnptit.vnpthis.service.DanhMucQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.DanhMuc}.
 */
@RestController
@RequestMapping("/api")
public class DanhMucResource {

    private final Logger log = LoggerFactory.getLogger(DanhMucResource.class);

    private static final String ENTITY_NAME = "danhMuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucService danhMucService;

    private final DanhMucQueryService danhMucQueryService;

    public DanhMucResource(DanhMucService danhMucService, DanhMucQueryService danhMucQueryService) {
        this.danhMucService = danhMucService;
        this.danhMucQueryService = danhMucQueryService;
    }

    /**
     * {@code POST  /danh-mucs} : Create a new danhMuc.
     *
     * @param danhMucDTO the danhMucDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucDTO, or with status {@code 400 (Bad Request)} if the danhMuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/danh-mucs")
    public ResponseEntity<DanhMucDTO> createDanhMuc(@Valid @RequestBody DanhMucDTO danhMucDTO) throws URISyntaxException {
        log.debug("REST request to save DanhMuc : {}", danhMucDTO);
        if (danhMucDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhMucDTO result = danhMucService.save(danhMucDTO);
        return ResponseEntity.created(new URI("/api/danh-mucs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /danh-mucs} : Updates an existing danhMuc.
     *
     * @param danhMucDTO the danhMucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/danh-mucs")
    public ResponseEntity<DanhMucDTO> updateDanhMuc(@Valid @RequestBody DanhMucDTO danhMucDTO) throws URISyntaxException {
        log.debug("REST request to update DanhMuc : {}", danhMucDTO);
        if (danhMucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanhMucDTO result = danhMucService.save(danhMucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, danhMucDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /danh-mucs} : get all the danhMucs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucs in body.
     */
    @GetMapping("/danh-mucs")
    public ResponseEntity<List<DanhMucDTO>> getAllDanhMucs(DanhMucCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DanhMucs by criteria: {}", criteria);
        Page<DanhMucDTO> page = danhMucQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /danh-mucs/count} : count all the danhMucs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/danh-mucs/count")
    public ResponseEntity<Long> countDanhMucs(DanhMucCriteria criteria) {
        log.debug("REST request to count DanhMucs by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhMucQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danh-mucs/:id} : get the "id" danhMuc.
     *
     * @param id the id of the danhMucDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/danh-mucs/{id}")
    public ResponseEntity<DanhMucDTO> getDanhMuc(@PathVariable Long id) {
        log.debug("REST request to get DanhMuc : {}", id);
        Optional<DanhMucDTO> danhMucDTO = danhMucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucDTO);
    }

    /**
     * {@code DELETE  /danh-mucs/:id} : delete the "id" danhMuc.
     *
     * @param id the id of the danhMucDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/danh-mucs/{id}")
    public ResponseEntity<Void> deleteDanhMuc(@PathVariable Long id) {
        log.debug("REST request to delete DanhMuc : {}", id);
        danhMucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
