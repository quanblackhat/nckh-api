package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoDTO;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotao}.
 */
@RestController
@RequestMapping("/api")
public class QldtDutoanDaotaoResource {

    private final Logger log = LoggerFactory.getLogger(QldtDutoanDaotaoResource.class);

    private static final String ENTITY_NAME = "qldtDutoanDaotao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtDutoanDaotaoService qldtDutoanDaotaoService;

    private final QldtDutoanDaotaoQueryService qldtDutoanDaotaoQueryService;

    public QldtDutoanDaotaoResource(QldtDutoanDaotaoService qldtDutoanDaotaoService, QldtDutoanDaotaoQueryService qldtDutoanDaotaoQueryService) {
        this.qldtDutoanDaotaoService = qldtDutoanDaotaoService;
        this.qldtDutoanDaotaoQueryService = qldtDutoanDaotaoQueryService;
    }

    /**
     * {@code POST  /qldt-dutoan-daotaos} : Create a new qldtDutoanDaotao.
     *
     * @param qldtDutoanDaotaoDTO the qldtDutoanDaotaoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtDutoanDaotaoDTO, or with status {@code 400 (Bad Request)} if the qldtDutoanDaotao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-dutoan-daotaos")
    public ResponseEntity<QldtDutoanDaotaoDTO> createQldtDutoanDaotao(@RequestBody QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO) throws URISyntaxException {
        log.debug("REST request to save QldtDutoanDaotao : {}", qldtDutoanDaotaoDTO);
        if (qldtDutoanDaotaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtDutoanDaotao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtDutoanDaotaoDTO result = qldtDutoanDaotaoService.save(qldtDutoanDaotaoDTO);
        return ResponseEntity.created(new URI("/api/qldt-dutoan-daotaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-dutoan-daotaos} : Updates an existing qldtDutoanDaotao.
     *
     * @param qldtDutoanDaotaoDTO the qldtDutoanDaotaoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtDutoanDaotaoDTO,
     * or with status {@code 400 (Bad Request)} if the qldtDutoanDaotaoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtDutoanDaotaoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-dutoan-daotaos")
    public ResponseEntity<QldtDutoanDaotaoDTO> updateQldtDutoanDaotao(@RequestBody QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO) throws URISyntaxException {
        log.debug("REST request to update QldtDutoanDaotao : {}", qldtDutoanDaotaoDTO);
        if (qldtDutoanDaotaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtDutoanDaotaoDTO result = qldtDutoanDaotaoService.save(qldtDutoanDaotaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtDutoanDaotaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-dutoan-daotaos} : get all the qldtDutoanDaotaos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtDutoanDaotaos in body.
     */
    @GetMapping("/qldt-dutoan-daotaos")
    public ResponseEntity<List<QldtDutoanDaotaoDTO>> getAllQldtDutoanDaotaos(QldtDutoanDaotaoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtDutoanDaotaos by criteria: {}", criteria);
        Page<QldtDutoanDaotaoDTO> page = qldtDutoanDaotaoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-dutoan-daotaos/count} : count all the qldtDutoanDaotaos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-dutoan-daotaos/count")
    public ResponseEntity<Long> countQldtDutoanDaotaos(QldtDutoanDaotaoCriteria criteria) {
        log.debug("REST request to count QldtDutoanDaotaos by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtDutoanDaotaoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-dutoan-daotaos/:id} : get the "id" qldtDutoanDaotao.
     *
     * @param id the id of the qldtDutoanDaotaoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtDutoanDaotaoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-dutoan-daotaos/{id}")
    public ResponseEntity<QldtDutoanDaotaoDTO> getQldtDutoanDaotao(@PathVariable Long id) {
        log.debug("REST request to get QldtDutoanDaotao : {}", id);
        Optional<QldtDutoanDaotaoDTO> qldtDutoanDaotaoDTO = qldtDutoanDaotaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtDutoanDaotaoDTO);
    }

    /**
     * {@code DELETE  /qldt-dutoan-daotaos/:id} : delete the "id" qldtDutoanDaotao.
     *
     * @param id the id of the qldtDutoanDaotaoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-dutoan-daotaos/{id}")
    public ResponseEntity<Void> deleteQldtDutoanDaotao(@PathVariable Long id) {
        log.debug("REST request to delete QldtDutoanDaotao : {}", id);
        qldtDutoanDaotaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
