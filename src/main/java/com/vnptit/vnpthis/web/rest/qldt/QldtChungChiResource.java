package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtChungChiService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtChungChiDTO;
import com.vnptit.vnpthis.service.dto.QldtChungChiCriteria;
import com.vnptit.vnpthis.service.qldt.QldtChungChiQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtChungChi}.
 */
@RestController
@RequestMapping("/api")
public class QldtChungChiResource {

    private final Logger log = LoggerFactory.getLogger(QldtChungChiResource.class);

    private static final String ENTITY_NAME = "qldtChungChi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtChungChiService qldtChungChiService;

    private final QldtChungChiQueryService qldtChungChiQueryService;

    public QldtChungChiResource(QldtChungChiService qldtChungChiService, QldtChungChiQueryService qldtChungChiQueryService) {
        this.qldtChungChiService = qldtChungChiService;
        this.qldtChungChiQueryService = qldtChungChiQueryService;
    }

    /**
     * {@code POST  /qldt-chung-chis} : Create a new qldtChungChi.
     *
     * @param qldtChungChiDTO the qldtChungChiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtChungChiDTO, or with status {@code 400 (Bad Request)} if the qldtChungChi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-chung-chis")
    public ResponseEntity<QldtChungChiDTO> createQldtChungChi(@RequestBody QldtChungChiDTO qldtChungChiDTO) throws URISyntaxException {
        log.debug("REST request to save QldtChungChi : {}", qldtChungChiDTO);
        if (qldtChungChiDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtChungChi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtChungChiDTO result = qldtChungChiService.save(qldtChungChiDTO);
        return ResponseEntity.created(new URI("/api/qldt-chung-chis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-chung-chis} : Updates an existing qldtChungChi.
     *
     * @param qldtChungChiDTO the qldtChungChiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtChungChiDTO,
     * or with status {@code 400 (Bad Request)} if the qldtChungChiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtChungChiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-chung-chis")
    public ResponseEntity<QldtChungChiDTO> updateQldtChungChi(@RequestBody QldtChungChiDTO qldtChungChiDTO) throws URISyntaxException {
        log.debug("REST request to update QldtChungChi : {}", qldtChungChiDTO);
        if (qldtChungChiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtChungChiDTO result = qldtChungChiService.save(qldtChungChiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtChungChiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-chung-chis} : get all the qldtChungChis.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtChungChis in body.
     */
    @GetMapping("/qldt-chung-chis")
    public ResponseEntity<List<QldtChungChiDTO>> getAllQldtChungChis(QldtChungChiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtChungChis by criteria: {}", criteria);
        Page<QldtChungChiDTO> page = qldtChungChiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-chung-chis/count} : count all the qldtChungChis.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-chung-chis/count")
    public ResponseEntity<Long> countQldtChungChis(QldtChungChiCriteria criteria) {
        log.debug("REST request to count QldtChungChis by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtChungChiQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-chung-chis/:id} : get the "id" qldtChungChi.
     *
     * @param id the id of the qldtChungChiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtChungChiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-chung-chis/{id}")
    public ResponseEntity<QldtChungChiDTO> getQldtChungChi(@PathVariable Long id) {
        log.debug("REST request to get QldtChungChi : {}", id);
        Optional<QldtChungChiDTO> qldtChungChiDTO = qldtChungChiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtChungChiDTO);
    }

    /**
     * {@code DELETE  /qldt-chung-chis/:id} : delete the "id" qldtChungChi.
     *
     * @param id the id of the qldtChungChiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-chung-chis/{id}")
    public ResponseEntity<Void> deleteQldtChungChi(@PathVariable Long id) {
        log.debug("REST request to delete QldtChungChi : {}", id);
        qldtChungChiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
