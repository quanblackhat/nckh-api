package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.service.TienDoService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.TienDoDTO;
import com.vnptit.vnpthis.service.dto.TienDoCriteria;
import com.vnptit.vnpthis.service.TienDoQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.TienDo}.
 */
@RestController
@RequestMapping("/api")
public class TienDoResource {

    private final Logger log = LoggerFactory.getLogger(TienDoResource.class);

    private static final String ENTITY_NAME = "tienDo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TienDoService tienDoService;

    private final TienDoQueryService tienDoQueryService;

    public TienDoResource(TienDoService tienDoService, TienDoQueryService tienDoQueryService) {
        this.tienDoService = tienDoService;
        this.tienDoQueryService = tienDoQueryService;
    }

    /**
     * {@code POST  /tien-dos} : Create a new tienDo.
     *
     * @param tienDoDTO the tienDoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tienDoDTO, or with status {@code 400 (Bad Request)} if the tienDo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tien-dos")
    public ResponseEntity<TienDoDTO> createTienDo(@Valid @RequestBody TienDoDTO tienDoDTO) throws URISyntaxException {
        log.debug("REST request to save TienDo : {}", tienDoDTO);
        if (tienDoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tienDo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TienDoDTO result = tienDoService.save(tienDoDTO);
        return ResponseEntity.created(new URI("/api/tien-dos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tien-dos} : Updates an existing tienDo.
     *
     * @param tienDoDTO the tienDoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tienDoDTO,
     * or with status {@code 400 (Bad Request)} if the tienDoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tienDoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tien-dos")
    public ResponseEntity<TienDoDTO> updateTienDo(@Valid @RequestBody TienDoDTO tienDoDTO) throws URISyntaxException {
        log.debug("REST request to update TienDo : {}", tienDoDTO);
        if (tienDoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TienDoDTO result = tienDoService.save(tienDoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tienDoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tien-dos} : get all the tienDos.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tienDos in body.
     */
    @GetMapping("/tien-dos")
    public ResponseEntity<List<TienDoDTO>> getAllTienDos(TienDoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TienDos by criteria: {}", criteria);
        Page<TienDoDTO> page = tienDoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /tien-dos/count} : count all the tienDos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/tien-dos/count")
    public ResponseEntity<Long> countTienDos(TienDoCriteria criteria) {
        log.debug("REST request to count TienDos by criteria: {}", criteria);
        return ResponseEntity.ok().body(tienDoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tien-dos/:id} : get the "id" tienDo.
     *
     * @param id the id of the tienDoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tienDoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tien-dos/{id}")
    public ResponseEntity<TienDoDTO> getTienDo(@PathVariable Long id) {
        log.debug("REST request to get TienDo : {}", id);
        Optional<TienDoDTO> tienDoDTO = tienDoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tienDoDTO);
    }

    /**
     * {@code DELETE  /tien-dos/:id} : delete the "id" tienDo.
     *
     * @param id the id of the tienDoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tien-dos/{id}")
    public ResponseEntity<Void> deleteTienDo(@PathVariable Long id) {
        log.debug("REST request to delete TienDo : {}", id);
        tienDoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
