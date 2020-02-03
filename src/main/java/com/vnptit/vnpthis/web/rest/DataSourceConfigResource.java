package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.domain.DataSourceConfig;
import com.vnptit.vnpthis.repository.DataSourceConfigRepository;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.vnptit.vnpthis.domain.DataSourceConfig}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DataSourceConfigResource {

    private final Logger log = LoggerFactory.getLogger(DataSourceConfigResource.class);

    private static final String ENTITY_NAME = "dataSourceConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataSourceConfigRepository dataSourceConfigRepository;

    public DataSourceConfigResource(DataSourceConfigRepository dataSourceConfigRepository) {
        this.dataSourceConfigRepository = dataSourceConfigRepository;
    }

    /**
     * {@code POST  /data-source-configs} : Create a new dataSourceConfig.
     *
     * @param dataSourceConfig the dataSourceConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataSourceConfig, or with status {@code 400 (Bad Request)} if the dataSourceConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-source-configs")
    public ResponseEntity<DataSourceConfig> createDataSourceConfig(@Valid @RequestBody DataSourceConfig dataSourceConfig) throws URISyntaxException {
        log.debug("REST request to save DataSourceConfig : {}", dataSourceConfig);
        if (dataSourceConfig.getId() != null) {
            throw new BadRequestAlertException("A new dataSourceConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataSourceConfig result = dataSourceConfigRepository.save(dataSourceConfig);
        return ResponseEntity.created(new URI("/api/data-source-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-source-configs} : Updates an existing dataSourceConfig.
     *
     * @param dataSourceConfig the dataSourceConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataSourceConfig,
     * or with status {@code 400 (Bad Request)} if the dataSourceConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataSourceConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-source-configs")
    public ResponseEntity<DataSourceConfig> updateDataSourceConfig(@Valid @RequestBody DataSourceConfig dataSourceConfig) throws URISyntaxException {
        log.debug("REST request to update DataSourceConfig : {}", dataSourceConfig);
        if (dataSourceConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DataSourceConfig result = dataSourceConfigRepository.save(dataSourceConfig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dataSourceConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /data-source-configs} : get all the dataSourceConfigs.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataSourceConfigs in body.
     */
    @GetMapping("/data-source-configs")
    public List<DataSourceConfig> getAllDataSourceConfigs() {
        log.debug("REST request to get all DataSourceConfigs");
        return dataSourceConfigRepository.findAll();
    }

    /**
     * {@code GET  /data-source-configs/:id} : get the "id" dataSourceConfig.
     *
     * @param id the id of the dataSourceConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataSourceConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-source-configs/{id}")
    public ResponseEntity<DataSourceConfig> getDataSourceConfig(@PathVariable Long id) {
        log.debug("REST request to get DataSourceConfig : {}", id);
        Optional<DataSourceConfig> dataSourceConfig = dataSourceConfigRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dataSourceConfig);
    }

    /**
     * {@code DELETE  /data-source-configs/:id} : delete the "id" dataSourceConfig.
     *
     * @param id the id of the dataSourceConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-source-configs/{id}")
    public ResponseEntity<Void> deleteDataSourceConfig(@PathVariable Long id) {
        log.debug("REST request to delete DataSourceConfig : {}", id);
        dataSourceConfigRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
