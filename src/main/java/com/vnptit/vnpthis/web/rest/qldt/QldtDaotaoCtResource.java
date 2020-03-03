package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtDaotaoCtService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCtDTO;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCtCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDaotaoCtQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt}.
 */
@RestController
@RequestMapping("/api")
public class QldtDaotaoCtResource {

    private final Logger log = LoggerFactory.getLogger(QldtDaotaoCtResource.class);

    private static final String ENTITY_NAME = "qldtDaotaoCt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtDaotaoCtService qldtDaotaoCtService;

    private final QldtDaotaoCtQueryService qldtDaotaoCtQueryService;

    public QldtDaotaoCtResource(QldtDaotaoCtService qldtDaotaoCtService, QldtDaotaoCtQueryService qldtDaotaoCtQueryService) {
        this.qldtDaotaoCtService = qldtDaotaoCtService;
        this.qldtDaotaoCtQueryService = qldtDaotaoCtQueryService;
    }

    /**
     * {@code POST  /qldt-daotao-cts} : Create a new qldtDaotaoCt.
     *
     * @param qldtDaotaoCtDTO the qldtDaotaoCtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtDaotaoCtDTO, or with status {@code 400 (Bad Request)} if the qldtDaotaoCt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-daotao-cts")
    public ResponseEntity<QldtDaotaoCtDTO> createQldtDaotaoCt(@RequestBody QldtDaotaoCtDTO qldtDaotaoCtDTO) throws URISyntaxException {
        log.debug("REST request to save QldtDaotaoCt : {}", qldtDaotaoCtDTO);
        if (qldtDaotaoCtDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtDaotaoCt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtDaotaoCtDTO result = qldtDaotaoCtService.save(qldtDaotaoCtDTO);
        return ResponseEntity.created(new URI("/api/qldt-daotao-cts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-daotao-cts} : Updates an existing qldtDaotaoCt.
     *
     * @param qldtDaotaoCtDTO the qldtDaotaoCtDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtDaotaoCtDTO,
     * or with status {@code 400 (Bad Request)} if the qldtDaotaoCtDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtDaotaoCtDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-daotao-cts")
    public ResponseEntity<QldtDaotaoCtDTO> updateQldtDaotaoCt(@RequestBody QldtDaotaoCtDTO qldtDaotaoCtDTO) throws URISyntaxException {
        log.debug("REST request to update QldtDaotaoCt : {}", qldtDaotaoCtDTO);
        if (qldtDaotaoCtDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtDaotaoCtDTO result = qldtDaotaoCtService.save(qldtDaotaoCtDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtDaotaoCtDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-daotao-cts} : get all the qldtDaotaoCts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtDaotaoCts in body.
     */
    @GetMapping("/qldt-daotao-cts")
    public ResponseEntity<List<QldtDaotaoCtDTO>> getAllQldtDaotaoCts(QldtDaotaoCtCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtDaotaoCts by criteria: {}", criteria);
        Page<QldtDaotaoCtDTO> page = qldtDaotaoCtQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-daotao-cts/count} : count all the qldtDaotaoCts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-daotao-cts/count")
    public ResponseEntity<Long> countQldtDaotaoCts(QldtDaotaoCtCriteria criteria) {
        log.debug("REST request to count QldtDaotaoCts by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtDaotaoCtQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-daotao-cts/:id} : get the "id" qldtDaotaoCt.
     *
     * @param id the id of the qldtDaotaoCtDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtDaotaoCtDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-daotao-cts/{id}")
    public ResponseEntity<QldtDaotaoCtDTO> getQldtDaotaoCt(@PathVariable Long id) {
        log.debug("REST request to get QldtDaotaoCt : {}", id);
        Optional<QldtDaotaoCtDTO> qldtDaotaoCtDTO = qldtDaotaoCtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtDaotaoCtDTO);
    }

    /**
     * {@code DELETE  /qldt-daotao-cts/:id} : delete the "id" qldtDaotaoCt.
     *
     * @param id the id of the qldtDaotaoCtDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-daotao-cts/{id}")
    public ResponseEntity<Void> deleteQldtDaotaoCt(@PathVariable Long id) {
        log.debug("REST request to delete QldtDaotaoCt : {}", id);
        qldtDaotaoCtService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
