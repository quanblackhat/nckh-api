package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtKyThuat;
import com.vnpt.cdt.domain.CdtKyThuatHoTro;
import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.repository.CdtKyThuatRepository;
import com.vnpt.cdt.service.CdtKyThuatService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtKyThuatCriteria;
import com.vnpt.cdt.service.CdtKyThuatQueryService;

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
 * Integration tests for the {@link CdtKyThuatResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtKyThuatResourceIT {

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    @Autowired
    private CdtKyThuatRepository cdtKyThuatRepository;

    @Autowired
    private CdtKyThuatService cdtKyThuatService;

    @Autowired
    private CdtKyThuatQueryService cdtKyThuatQueryService;

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

    private MockMvc restCdtKyThuatMockMvc;

    private CdtKyThuat cdtKyThuat;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtKyThuatResource cdtKyThuatResource = new CdtKyThuatResource(cdtKyThuatService, cdtKyThuatQueryService);
        this.restCdtKyThuatMockMvc = MockMvcBuilders.standaloneSetup(cdtKyThuatResource)
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
    public static CdtKyThuat createEntity(EntityManager em) {
        CdtKyThuat cdtKyThuat = new CdtKyThuat()
            .csytid(DEFAULT_CSYTID);
        return cdtKyThuat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtKyThuat createUpdatedEntity(EntityManager em) {
        CdtKyThuat cdtKyThuat = new CdtKyThuat()
            .csytid(UPDATED_CSYTID);
        return cdtKyThuat;
    }

    @BeforeEach
    public void initTest() {
        cdtKyThuat = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtKyThuat() throws Exception {
        int databaseSizeBeforeCreate = cdtKyThuatRepository.findAll().size();

        // Create the CdtKyThuat
        restCdtKyThuatMockMvc.perform(post("/api/cdt-ky-thuats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtKyThuat)))
            .andExpect(status().isCreated());

        // Validate the CdtKyThuat in the database
        List<CdtKyThuat> cdtKyThuatList = cdtKyThuatRepository.findAll();
        assertThat(cdtKyThuatList).hasSize(databaseSizeBeforeCreate + 1);
        CdtKyThuat testCdtKyThuat = cdtKyThuatList.get(cdtKyThuatList.size() - 1);
        assertThat(testCdtKyThuat.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtKyThuatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtKyThuatRepository.findAll().size();

        // Create the CdtKyThuat with an existing ID
        cdtKyThuat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtKyThuatMockMvc.perform(post("/api/cdt-ky-thuats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtKyThuat)))
            .andExpect(status().isBadRequest());

        // Validate the CdtKyThuat in the database
        List<CdtKyThuat> cdtKyThuatList = cdtKyThuatRepository.findAll();
        assertThat(cdtKyThuatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtKyThuats() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get all the cdtKyThuatList
        restCdtKyThuatMockMvc.perform(get("/api/cdt-ky-thuats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtKyThuat.getId().intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtKyThuat() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get the cdtKyThuat
        restCdtKyThuatMockMvc.perform(get("/api/cdt-ky-thuats/{id}", cdtKyThuat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtKyThuat.getId().intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtKyThuatsByIdFiltering() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        Long id = cdtKyThuat.getId();

        defaultCdtKyThuatShouldBeFound("id.equals=" + id);
        defaultCdtKyThuatShouldNotBeFound("id.notEquals=" + id);

        defaultCdtKyThuatShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtKyThuatShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtKyThuatShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtKyThuatShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtKyThuatsByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get all the cdtKyThuatList where csytid equals to DEFAULT_CSYTID
        defaultCdtKyThuatShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatList where csytid equals to UPDATED_CSYTID
        defaultCdtKyThuatShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatsByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get all the cdtKyThuatList where csytid not equals to DEFAULT_CSYTID
        defaultCdtKyThuatShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatList where csytid not equals to UPDATED_CSYTID
        defaultCdtKyThuatShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatsByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get all the cdtKyThuatList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtKyThuatShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtKyThuatList where csytid equals to UPDATED_CSYTID
        defaultCdtKyThuatShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatsByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get all the cdtKyThuatList where csytid is not null
        defaultCdtKyThuatShouldBeFound("csytid.specified=true");

        // Get all the cdtKyThuatList where csytid is null
        defaultCdtKyThuatShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatsByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get all the cdtKyThuatList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtKyThuatShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtKyThuatShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatsByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get all the cdtKyThuatList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtKyThuatShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtKyThuatShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatsByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get all the cdtKyThuatList where csytid is less than DEFAULT_CSYTID
        defaultCdtKyThuatShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatList where csytid is less than UPDATED_CSYTID
        defaultCdtKyThuatShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatsByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);

        // Get all the cdtKyThuatList where csytid is greater than DEFAULT_CSYTID
        defaultCdtKyThuatShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatList where csytid is greater than SMALLER_CSYTID
        defaultCdtKyThuatShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtKyThuatsByCdtKyThuatHoTroIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);
        CdtKyThuatHoTro cdtKyThuatHoTro = CdtKyThuatHoTroResourceIT.createEntity(em);
        em.persist(cdtKyThuatHoTro);
        em.flush();
        cdtKyThuat.setCdtKyThuatHoTro(cdtKyThuatHoTro);
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);
        Long cdtKyThuatHoTroId = cdtKyThuatHoTro.getId();

        // Get all the cdtKyThuatList where cdtKyThuatHoTro equals to cdtKyThuatHoTroId
        defaultCdtKyThuatShouldBeFound("cdtKyThuatHoTroId.equals=" + cdtKyThuatHoTroId);

        // Get all the cdtKyThuatList where cdtKyThuatHoTro equals to cdtKyThuatHoTroId + 1
        defaultCdtKyThuatShouldNotBeFound("cdtKyThuatHoTroId.equals=" + (cdtKyThuatHoTroId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtKyThuatsByCdtChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);
        CdtChiDaoTuyen cdtChiDaoTuyen = CdtChiDaoTuyenResourceIT.createEntity(em);
        em.persist(cdtChiDaoTuyen);
        em.flush();
        cdtKyThuat.setCdtChiDaoTuyen(cdtChiDaoTuyen);
        cdtKyThuatRepository.saveAndFlush(cdtKyThuat);
        Long cdtChiDaoTuyenId = cdtChiDaoTuyen.getId();

        // Get all the cdtKyThuatList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId
        defaultCdtKyThuatShouldBeFound("cdtChiDaoTuyenId.equals=" + cdtChiDaoTuyenId);

        // Get all the cdtKyThuatList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId + 1
        defaultCdtKyThuatShouldNotBeFound("cdtChiDaoTuyenId.equals=" + (cdtChiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtKyThuatShouldBeFound(String filter) throws Exception {
        restCdtKyThuatMockMvc.perform(get("/api/cdt-ky-thuats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtKyThuat.getId().intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtKyThuatMockMvc.perform(get("/api/cdt-ky-thuats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtKyThuatShouldNotBeFound(String filter) throws Exception {
        restCdtKyThuatMockMvc.perform(get("/api/cdt-ky-thuats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtKyThuatMockMvc.perform(get("/api/cdt-ky-thuats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtKyThuat() throws Exception {
        // Get the cdtKyThuat
        restCdtKyThuatMockMvc.perform(get("/api/cdt-ky-thuats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtKyThuat() throws Exception {
        // Initialize the database
        cdtKyThuatService.save(cdtKyThuat);

        int databaseSizeBeforeUpdate = cdtKyThuatRepository.findAll().size();

        // Update the cdtKyThuat
        CdtKyThuat updatedCdtKyThuat = cdtKyThuatRepository.findById(cdtKyThuat.getId()).get();
        // Disconnect from session so that the updates on updatedCdtKyThuat are not directly saved in db
        em.detach(updatedCdtKyThuat);
        updatedCdtKyThuat
            .csytid(UPDATED_CSYTID);

        restCdtKyThuatMockMvc.perform(put("/api/cdt-ky-thuats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtKyThuat)))
            .andExpect(status().isOk());

        // Validate the CdtKyThuat in the database
        List<CdtKyThuat> cdtKyThuatList = cdtKyThuatRepository.findAll();
        assertThat(cdtKyThuatList).hasSize(databaseSizeBeforeUpdate);
        CdtKyThuat testCdtKyThuat = cdtKyThuatList.get(cdtKyThuatList.size() - 1);
        assertThat(testCdtKyThuat.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtKyThuat() throws Exception {
        int databaseSizeBeforeUpdate = cdtKyThuatRepository.findAll().size();

        // Create the CdtKyThuat

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtKyThuatMockMvc.perform(put("/api/cdt-ky-thuats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtKyThuat)))
            .andExpect(status().isBadRequest());

        // Validate the CdtKyThuat in the database
        List<CdtKyThuat> cdtKyThuatList = cdtKyThuatRepository.findAll();
        assertThat(cdtKyThuatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtKyThuat() throws Exception {
        // Initialize the database
        cdtKyThuatService.save(cdtKyThuat);

        int databaseSizeBeforeDelete = cdtKyThuatRepository.findAll().size();

        // Delete the cdtKyThuat
        restCdtKyThuatMockMvc.perform(delete("/api/cdt-ky-thuats/{id}", cdtKyThuat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtKyThuat> cdtKyThuatList = cdtKyThuatRepository.findAll();
        assertThat(cdtKyThuatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
