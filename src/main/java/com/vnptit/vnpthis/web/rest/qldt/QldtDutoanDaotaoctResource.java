package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoctService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoctDTO;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoctCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoctQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotaoct}.
 */
@RestController
@RequestMapping("/api")
public class QldtDutoanDaotaoctResource {

    private final Logger log = LoggerFactory.getLogger(QldtDutoanDaotaoctResource.class);

    private static final String ENTITY_NAME = "qldtDutoanDaotaoct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtDutoanDaotaoctService qldtDutoanDaotaoctService;

    private final QldtDutoanDaotaoctQueryService qldtDutoanDaotaoctQueryService;

    public QldtDutoanDaotaoctResource(QldtDutoanDaotaoctService qldtDutoanDaotaoctService, QldtDutoanDaotaoctQueryService qldtDutoanDaotaoctQueryService) {
        this.qldtDutoanDaotaoctService = qldtDutoanDaotaoctService;
        this.qldtDutoanDaotaoctQueryService = qldtDutoanDaotaoctQueryService;
    }

    /**
     * {@code POST  /qldt-dutoan-daotaocts} : Create a new qldtDutoanDaotaoct.
     *
     * @param qldtDutoanDaotaoctDTO the qldtDutoanDaotaoctDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtDutoanDaotaoctDTO, or with status {@code 400 (Bad Request)} if the qldtDutoanDaotaoct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-dutoan-daotaocts")
    public ResponseEntity<QldtDutoanDaotaoctDTO> createQldtDutoanDaotaoct(@RequestBody QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO) throws URISyntaxException {
        log.debug("REST request to save QldtDutoanDaotaoct : {}", qldtDutoanDaotaoctDTO);
        if (qldtDutoanDaotaoctDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtDutoanDaotaoct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtDutoanDaotaoctDTO result = qldtDutoanDaotaoctService.save(qldtDutoanDaotaoctDTO);
        return ResponseEntity.created(new URI("/api/qldt-dutoan-daotaocts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-dutoan-daotaocts} : Updates an existing qldtDutoanDaotaoct.
     *
     * @param qldtDutoanDaotaoctDTO the qldtDutoanDaotaoctDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtDutoanDaotaoctDTO,
     * or with status {@code 400 (Bad Request)} if the qldtDutoanDaotaoctDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtDutoanDaotaoctDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-dutoan-daotaocts")
    public ResponseEntity<QldtDutoanDaotaoctDTO> updateQldtDutoanDaotaoct(@RequestBody QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO) throws URISyntaxException {
        log.debug("REST request to update QldtDutoanDaotaoct : {}", qldtDutoanDaotaoctDTO);
        if (qldtDutoanDaotaoctDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtDutoanDaotaoctDTO result = qldtDutoanDaotaoctService.save(qldtDutoanDaotaoctDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtDutoanDaotaoctDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-dutoan-daotaocts} : get all the qldtDutoanDaotaocts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtDutoanDaotaocts in body.
     */
    @GetMapping("/qldt-dutoan-daotaocts")
    public ResponseEntity<List<QldtDutoanDaotaoctDTO>> getAllQldtDutoanDaotaocts(QldtDutoanDaotaoctCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtDutoanDaotaocts by criteria: {}", criteria);
        Page<QldtDutoanDaotaoctDTO> page = qldtDutoanDaotaoctQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-dutoan-daotaocts/count} : count all the qldtDutoanDaotaocts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-dutoan-daotaocts/count")
    public ResponseEntity<Long> countQldtDutoanDaotaocts(QldtDutoanDaotaoctCriteria criteria) {
        log.debug("REST request to count QldtDutoanDaotaocts by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtDutoanDaotaoctQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-dutoan-daotaocts/:id} : get the "id" qldtDutoanDaotaoct.
     *
     * @param id the id of the qldtDutoanDaotaoctDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtDutoanDaotaoctDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-dutoan-daotaocts/{id}")
    public ResponseEntity<QldtDutoanDaotaoctDTO> getQldtDutoanDaotaoct(@PathVariable Long id) {
        log.debug("REST request to get QldtDutoanDaotaoct : {}", id);
        Optional<QldtDutoanDaotaoctDTO> qldtDutoanDaotaoctDTO = qldtDutoanDaotaoctService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtDutoanDaotaoctDTO);
    }

    /**
     * {@code DELETE  /qldt-dutoan-daotaocts/:id} : delete the "id" qldtDutoanDaotaoct.
     *
     * @param id the id of the qldtDutoanDaotaoctDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-dutoan-daotaocts/{id}")
    public ResponseEntity<Void> deleteQldtDutoanDaotaoct(@PathVariable Long id) {
        log.debug("REST request to delete QldtDutoanDaotaoct : {}", id);
        qldtDutoanDaotaoctService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
