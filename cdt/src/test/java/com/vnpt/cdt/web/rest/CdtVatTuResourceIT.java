package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtVatTu;
import com.vnpt.cdt.domain.CdtVatTuHoTro;
import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.repository.CdtVatTuRepository;
import com.vnpt.cdt.service.CdtVatTuService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtVatTuCriteria;
import com.vnpt.cdt.service.CdtVatTuQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.vnpt.cdt.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CdtVatTuResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtVatTuResourceIT {

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    @Autowired
    private CdtVatTuRepository cdtVatTuRepository;

    @Autowired
    private CdtVatTuService cdtVatTuService;

    @Autowired
    private CdtVatTuQueryService cdtVatTuQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCdtVatTuMockMvc;

    private CdtVatTu cdtVatTu;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtVatTuResource cdtVatTuResource = new CdtVatTuResource(cdtVatTuService, cdtVatTuQueryService);
        this.restCdtVatTuMockMvc = MockMvcBuilders.standaloneSetup(cdtVatTuResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtVatTu createEntity(EntityManager em) {
        CdtVatTu cdtVatTu = new CdtVatTu()
            .csytid(DEFAULT_CSYTID);
        return cdtVatTu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtVatTu createUpdatedEntity(EntityManager em) {
        CdtVatTu cdtVatTu = new CdtVatTu()
            .csytid(UPDATED_CSYTID);
        return cdtVatTu;
    }

    @BeforeEach
    public void initTest() {
        cdtVatTu = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtVatTu() throws Exception {
        int databaseSizeBeforeCreate = cdtVatTuRepository.findAll().size();

        // Create the CdtVatTu
        restCdtVatTuMockMvc.perform(post("/api/cdt-vat-tus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtVatTu)))
            .andExpect(status().isCreated());

        // Validate the CdtVatTu in the database
        List<CdtVatTu> cdtVatTuList = cdtVatTuRepository.findAll();
        assertThat(cdtVatTuList).hasSize(databaseSizeBeforeCreate + 1);
        CdtVatTu testCdtVatTu = cdtVatTuList.get(cdtVatTuList.size() - 1);
        assertThat(testCdtVatTu.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtVatTuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtVatTuRepository.findAll().size();

        // Create the CdtVatTu with an existing ID
        cdtVatTu.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtVatTuMockMvc.perform(post("/api/cdt-vat-tus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtVatTu)))
            .andExpect(status().isBadRequest());

        // Validate the CdtVatTu in the database
        List<CdtVatTu> cdtVatTuList = cdtVatTuRepository.findAll();
        assertThat(cdtVatTuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtVatTus() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get all the cdtVatTuList
        restCdtVatTuMockMvc.perform(get("/api/cdt-vat-tus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtVatTu.getId().intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtVatTu() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get the cdtVatTu
        restCdtVatTuMockMvc.perform(get("/api/cdt-vat-tus/{id}", cdtVatTu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtVatTu.getId().intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtVatTusByIdFiltering() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        Long id = cdtVatTu.getId();

        defaultCdtVatTuShouldBeFound("id.equals=" + id);
        defaultCdtVatTuShouldNotBeFound("id.notEquals=" + id);

        defaultCdtVatTuShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtVatTuShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtVatTuShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtVatTuShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtVatTusByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get all the cdtVatTuList where csytid equals to DEFAULT_CSYTID
        defaultCdtVatTuShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuList where csytid equals to UPDATED_CSYTID
        defaultCdtVatTuShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTusByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get all the cdtVatTuList where csytid not equals to DEFAULT_CSYTID
        defaultCdtVatTuShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuList where csytid not equals to UPDATED_CSYTID
        defaultCdtVatTuShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTusByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get all the cdtVatTuList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtVatTuShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtVatTuList where csytid equals to UPDATED_CSYTID
        defaultCdtVatTuShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTusByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get all the cdtVatTuList where csytid is not null
        defaultCdtVatTuShouldBeFound("csytid.specified=true");

        // Get all the cdtVatTuList where csytid is null
        defaultCdtVatTuShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtVatTusByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get all the cdtVatTuList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtVatTuShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtVatTuShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTusByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get all the cdtVatTuList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtVatTuShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtVatTuShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTusByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get all the cdtVatTuList where csytid is less than DEFAULT_CSYTID
        defaultCdtVatTuShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuList where csytid is less than UPDATED_CSYTID
        defaultCdtVatTuShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTusByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);

        // Get all the cdtVatTuList where csytid is greater than DEFAULT_CSYTID
        defaultCdtVatTuShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuList where csytid is greater than SMALLER_CSYTID
        defaultCdtVatTuShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtVatTusByCdtVatTuHoTroIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);
        CdtVatTuHoTro cdtVatTuHoTro = CdtVatTuHoTroResourceIT.createEntity(em);
        em.persist(cdtVatTuHoTro);
        em.flush();
        cdtVatTu.setCdtVatTuHoTro(cdtVatTuHoTro);
        cdtVatTuRepository.saveAndFlush(cdtVatTu);
        Long cdtVatTuHoTroId = cdtVatTuHoTro.getId();

        // Get all the cdtVatTuList where cdtVatTuHoTro equals to cdtVatTuHoTroId
        defaultCdtVatTuShouldBeFound("cdtVatTuHoTroId.equals=" + cdtVatTuHoTroId);

        // Get all the cdtVatTuList where cdtVatTuHoTro equals to cdtVatTuHoTroId + 1
        defaultCdtVatTuShouldNotBeFound("cdtVatTuHoTroId.equals=" + (cdtVatTuHoTroId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtVatTusByCdtChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuRepository.saveAndFlush(cdtVatTu);
        CdtChiDaoTuyen cdtChiDaoTuyen = CdtChiDaoTuyenResourceIT.createEntity(em);
        em.persist(cdtChiDaoTuyen);
        em.flush();
        cdtVatTu.setCdtChiDaoTuyen(cdtChiDaoTuyen);
        cdtVatTuRepository.saveAndFlush(cdtVatTu);
        Long cdtChiDaoTuyenId = cdtChiDaoTuyen.getId();

        // Get all the cdtVatTuList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId
        defaultCdtVatTuShouldBeFound("cdtChiDaoTuyenId.equals=" + cdtChiDaoTuyenId);

        // Get all the cdtVatTuList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId + 1
        defaultCdtVatTuShouldNotBeFound("cdtChiDaoTuyenId.equals=" + (cdtChiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtVatTuShouldBeFound(String filter) throws Exception {
        restCdtVatTuMockMvc.perform(get("/api/cdt-vat-tus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtVatTu.getId().intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtVatTuMockMvc.perform(get("/api/cdt-vat-tus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtVatTuShouldNotBeFound(String filter) throws Exception {
        restCdtVatTuMockMvc.perform(get("/api/cdt-vat-tus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtVatTuMockMvc.perform(get("/api/cdt-vat-tus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtVatTu() throws Exception {
        // Get the cdtVatTu
        restCdtVatTuMockMvc.perform(get("/api/cdt-vat-tus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtVatTu() throws Exception {
        // Initialize the database
        cdtVatTuService.save(cdtVatTu);

        int databaseSizeBeforeUpdate = cdtVatTuRepository.findAll().size();

        // Update the cdtVatTu
        CdtVatTu updatedCdtVatTu = cdtVatTuRepository.findById(cdtVatTu.getId()).get();
        // Disconnect from session so that the updates on updatedCdtVatTu are not directly saved in db
        em.detach(updatedCdtVatTu);
        updatedCdtVatTu
            .csytid(UPDATED_CSYTID);

        restCdtVatTuMockMvc.perform(put("/api/cdt-vat-tus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtVatTu)))
            .andExpect(status().isOk());

        // Validate the CdtVatTu in the database
        List<CdtVatTu> cdtVatTuList = cdtVatTuRepository.findAll();
        assertThat(cdtVatTuList).hasSize(databaseSizeBeforeUpdate);
        CdtVatTu testCdtVatTu = cdtVatTuList.get(cdtVatTuList.size() - 1);
        assertThat(testCdtVatTu.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtVatTu() throws Exception {
        int databaseSizeBeforeUpdate = cdtVatTuRepository.findAll().size();

        // Create the CdtVatTu

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtVatTuMockMvc.perform(put("/api/cdt-vat-tus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtVatTu)))
            .andExpect(status().isBadRequest());

        // Validate the CdtVatTu in the database
        List<CdtVatTu> cdtVatTuList = cdtVatTuRepository.findAll();
        assertThat(cdtVatTuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtVatTu() throws Exception {
        // Initialize the database
        cdtVatTuService.save(cdtVatTu);

        int databaseSizeBeforeDelete = cdtVatTuRepository.findAll().size();

        // Delete the cdtVatTu
        restCdtVatTuMockMvc.perform(delete("/api/cdt-vat-tus/{id}", cdtVatTu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtVatTu> cdtVatTuList = cdtVatTuRepository.findAll();
        assertThat(cdtVatTuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
