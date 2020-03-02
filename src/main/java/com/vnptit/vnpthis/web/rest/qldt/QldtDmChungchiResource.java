package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtDmChungchiService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtDmChungchiDTO;
import com.vnptit.vnpthis.service.dto.QldtDmChungchiCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDmChungchiQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtDmChungchi}.
 */
@RestController
@RequestMapping("/api")
public class QldtDmChungchiResource {

    private final Logger log = LoggerFactory.getLogger(QldtDmChungchiResource.class);

    private static final String ENTITY_NAME = "qldtDmChungchi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtDmChungchiService qldtDmChungchiService;

    private final QldtDmChungchiQueryService qldtDmChungchiQueryService;

    public QldtDmChungchiResource(QldtDmChungchiService qldtDmChungchiService, QldtDmChungchiQueryService qldtDmChungchiQueryService) {
        this.qldtDmChungchiService = qldtDmChungchiService;
        this.qldtDmChungchiQueryService = qldtDmChungchiQueryService;
    }

    /**
     * {@code POST  /qldt-dm-chungchis} : Create a new qldtDmChungchi.
     *
     * @param qldtDmChungchiDTO the qldtDmChungchiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtDmChungchiDTO, or with status {@code 400 (Bad Request)} if the qldtDmChungchi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-dm-chungchis")
    public ResponseEntity<QldtDmChungchiDTO> createQldtDmChungchi(@RequestBody QldtDmChungchiDTO qldtDmChungchiDTO) throws URISyntaxException {
        log.debug("REST request to save QldtDmChungchi : {}", qldtDmChungchiDTO);
        if (qldtDmChungchiDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtDmChungchi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtDmChungchiDTO result = qldtDmChungchiService.save(qldtDmChungchiDTO);
        return ResponseEntity.created(new URI("/api/qldt-dm-chungchis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-dm-chungchis} : Updates an existing qldtDmChungchi.
     *
     * @param qldtDmChungchiDTO the qldtDmChungchiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtDmChungchiDTO,
     * or with status {@code 400 (Bad Request)} if the qldtDmChungchiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtDmChungchiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-dm-chungchis")
    public ResponseEntity<QldtDmChungchiDTO> updateQldtDmChungchi(@RequestBody QldtDmChungchiDTO qldtDmChungchiDTO) throws URISyntaxException {
        log.debug("REST request to update QldtDmChungchi : {}", qldtDmChungchiDTO);
        if (qldtDmChungchiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtDmChungchiDTO result = qldtDmChungchiService.save(qldtDmChungchiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtDmChungchiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-dm-chungchis} : get all the qldtDmChungchis.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtDmChungchis in body.
     */
    @GetMapping("/qldt-dm-chungchis")
    public ResponseEntity<List<QldtDmChungchiDTO>> getAllQldtDmChungchis(QldtDmChungchiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtDmChungchis by criteria: {}", criteria);
        Page<QldtDmChungchiDTO> page = qldtDmChungchiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-dm-chungchis/count} : count all the qldtDmChungchis.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-dm-chungchis/count")
    public ResponseEntity<Long> countQldtDmChungchis(QldtDmChungchiCriteria criteria) {
        log.debug("REST request to count QldtDmChungchis by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtDmChungchiQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-dm-chungchis/:id} : get the "id" qldtDmChungchi.
     *
     * @param id the id of the qldtDmChungchiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtDmChungchiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-dm-chungchis/{id}")
    public ResponseEntity<QldtDmChungchiDTO> getQldtDmChungchi(@PathVariable Long id) {
        log.debug("REST request to get QldtDmChungchi : {}", id);
        Optional<QldtDmChungchiDTO> qldtDmChungchiDTO = qldtDmChungchiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtDmChungchiDTO);
    }

    /**
     * {@code DELETE  /qldt-dm-chungchis/:id} : delete the "id" qldtDmChungchi.
     *
     * @param id the id of the qldtDmChungchiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-dm-chungchis/{id}")
    public ResponseEntity<Void> deleteQldtDmChungchi(@PathVariable Long id) {
        log.debug("REST request to delete QldtDmChungchi : {}", id);
        qldtDmChungchiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
