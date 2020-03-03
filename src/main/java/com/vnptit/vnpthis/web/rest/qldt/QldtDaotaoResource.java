package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtDaotaoService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtDaotaoDTO;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDaotaoQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtDaotao}.
 */
@RestController
@RequestMapping("/api")
public class QldtDaotaoResource {

    private final Logger log = LoggerFactory.getLogger(QldtDaotaoResource.class);

    private static final String ENTITY_NAME = "qldtDaotao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtDaotaoService qldtDaotaoService;

    private final QldtDaotaoQueryService qldtDaotaoQueryService;

    public QldtDaotaoResource(QldtDaotaoService qldtDaotaoService, QldtDaotaoQueryService qldtDaotaoQueryService) {
        this.qldtDaotaoService = qldtDaotaoService;
        this.qldtDaotaoQueryService = qldtDaotaoQueryService;
    }

    /**
     * {@code POST  /qldt-daotaos} : Create a new qldtDaotao.
     *
     * @param qldtDaotaoDTO the qldtDaotaoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtDaotaoDTO, or with status {@code 400 (Bad Request)} if the qldtDaotao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-daotaos")
    public ResponseEntity<QldtDaotaoDTO> createQldtDaotao(@RequestBody QldtDaotaoDTO qldtDaotaoDTO) throws URISyntaxException {
        log.debug("REST request to save QldtDaotao : {}", qldtDaotaoDTO);
        if (qldtDaotaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtDaotao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtDaotaoDTO result = qldtDaotaoService.save(qldtDaotaoDTO);
        return ResponseEntity.created(new URI("/api/qldt-daotaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-daotaos} : Updates an existing qldtDaotao.
     *
     * @param qldtDaotaoDTO the qldtDaotaoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtDaotaoDTO,
     * or with status {@code 400 (Bad Request)} if the qldtDaotaoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtDaotaoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-daotaos")
    public ResponseEntity<QldtDaotaoDTO> updateQldtDaotao(@RequestBody QldtDaotaoDTO qldtDaotaoDTO) throws URISyntaxException {
        log.debug("REST request to update QldtDaotao : {}", qldtDaotaoDTO);
        if (qldtDaotaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtDaotaoDTO result = qldtDaotaoService.save(qldtDaotaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtDaotaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-daotaos} : get all the qldtDaotaos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtDaotaos in body.
     */
    @GetMapping("/qldt-daotaos")
    public ResponseEntity<List<QldtDaotaoDTO>> getAllQldtDaotaos(QldtDaotaoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtDaotaos by criteria: {}", criteria);
        Page<QldtDaotaoDTO> page = qldtDaotaoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-daotaos/count} : count all the qldtDaotaos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-daotaos/count")
    public ResponseEntity<Long> countQldtDaotaos(QldtDaotaoCriteria criteria) {
        log.debug("REST request to count QldtDaotaos by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtDaotaoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-daotaos/:id} : get the "id" qldtDaotao.
     *
     * @param id the id of the qldtDaotaoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtDaotaoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-daotaos/{id}")
    public ResponseEntity<QldtDaotaoDTO> getQldtDaotao(@PathVariable Long id) {
        log.debug("REST request to get QldtDaotao : {}", id);
        Optional<QldtDaotaoDTO> qldtDaotaoDTO = qldtDaotaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtDaotaoDTO);
    }

    /**
     * {@code DELETE  /qldt-daotaos/:id} : delete the "id" qldtDaotao.
     *
     * @param id the id of the qldtDaotaoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-daotaos/{id}")
    public ResponseEntity<Void> deleteQldtDaotao(@PathVariable Long id) {
        log.debug("REST request to delete QldtDaotao : {}", id);
        qldtDaotaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
