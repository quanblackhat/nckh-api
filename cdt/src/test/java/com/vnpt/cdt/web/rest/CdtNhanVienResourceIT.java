package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtNhanVien;
import com.vnpt.cdt.domain.OrgOfficer;
import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.repository.CdtNhanVienRepository;
import com.vnpt.cdt.service.CdtNhanVienService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtNhanVienCriteria;
import com.vnpt.cdt.service.CdtNhanVienQueryService;

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
 * Integration tests for the {@link CdtNhanVienResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtNhanVienResourceIT {

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    @Autowired
    private CdtNhanVienRepository cdtNhanVienRepository;

    @Autowired
    private CdtNhanVienService cdtNhanVienService;

    @Autowired
    private CdtNhanVienQueryService cdtNhanVienQueryService;

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

    private MockMvc restCdtNhanVienMockMvc;

    private CdtNhanVien cdtNhanVien;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtNhanVienResource cdtNhanVienResource = new CdtNhanVienResource(cdtNhanVienService, cdtNhanVienQueryService);
        this.restCdtNhanVienMockMvc = MockMvcBuilders.standaloneSetup(cdtNhanVienResource)
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
    public static CdtNhanVien createEntity(EntityManager em) {
        CdtNhanVien cdtNhanVien = new CdtNhanVien()
            .csytid(DEFAULT_CSYTID);
        return cdtNhanVien;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtNhanVien createUpdatedEntity(EntityManager em) {
        CdtNhanVien cdtNhanVien = new CdtNhanVien()
            .csytid(UPDATED_CSYTID);
        return cdtNhanVien;
    }

    @BeforeEach
    public void initTest() {
        cdtNhanVien = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtNhanVien() throws Exception {
        int databaseSizeBeforeCreate = cdtNhanVienRepository.findAll().size();

        // Create the CdtNhanVien
        restCdtNhanVienMockMvc.perform(post("/api/cdt-nhan-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtNhanVien)))
            .andExpect(status().isCreated());

        // Validate the CdtNhanVien in the database
        List<CdtNhanVien> cdtNhanVienList = cdtNhanVienRepository.findAll();
        assertThat(cdtNhanVienList).hasSize(databaseSizeBeforeCreate + 1);
        CdtNhanVien testCdtNhanVien = cdtNhanVienList.get(cdtNhanVienList.size() - 1);
        assertThat(testCdtNhanVien.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtNhanVienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtNhanVienRepository.findAll().size();

        // Create the CdtNhanVien with an existing ID
        cdtNhanVien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtNhanVienMockMvc.perform(post("/api/cdt-nhan-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtNhanVien)))
            .andExpect(status().isBadRequest());

        // Validate the CdtNhanVien in the database
        List<CdtNhanVien> cdtNhanVienList = cdtNhanVienRepository.findAll();
        assertThat(cdtNhanVienList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtNhanViens() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get all the cdtNhanVienList
        restCdtNhanVienMockMvc.perform(get("/api/cdt-nhan-viens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtNhanVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtNhanVien() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get the cdtNhanVien
        restCdtNhanVienMockMvc.perform(get("/api/cdt-nhan-viens/{id}", cdtNhanVien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtNhanVien.getId().intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtNhanViensByIdFiltering() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        Long id = cdtNhanVien.getId();

        defaultCdtNhanVienShouldBeFound("id.equals=" + id);
        defaultCdtNhanVienShouldNotBeFound("id.notEquals=" + id);

        defaultCdtNhanVienShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtNhanVienShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtNhanVienShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtNhanVienShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtNhanViensByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get all the cdtNhanVienList where csytid equals to DEFAULT_CSYTID
        defaultCdtNhanVienShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtNhanVienList where csytid equals to UPDATED_CSYTID
        defaultCdtNhanVienShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNhanViensByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get all the cdtNhanVienList where csytid not equals to DEFAULT_CSYTID
        defaultCdtNhanVienShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtNhanVienList where csytid not equals to UPDATED_CSYTID
        defaultCdtNhanVienShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNhanViensByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get all the cdtNhanVienList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtNhanVienShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtNhanVienList where csytid equals to UPDATED_CSYTID
        defaultCdtNhanVienShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNhanViensByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get all the cdtNhanVienList where csytid is not null
        defaultCdtNhanVienShouldBeFound("csytid.specified=true");

        // Get all the cdtNhanVienList where csytid is null
        defaultCdtNhanVienShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtNhanViensByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get all the cdtNhanVienList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtNhanVienShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtNhanVienList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtNhanVienShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNhanViensByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get all the cdtNhanVienList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtNhanVienShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtNhanVienList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtNhanVienShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNhanViensByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get all the cdtNhanVienList where csytid is less than DEFAULT_CSYTID
        defaultCdtNhanVienShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtNhanVienList where csytid is less than UPDATED_CSYTID
        defaultCdtNhanVienShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNhanViensByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);

        // Get all the cdtNhanVienList where csytid is greater than DEFAULT_CSYTID
        defaultCdtNhanVienShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtNhanVienList where csytid is greater than SMALLER_CSYTID
        defaultCdtNhanVienShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtNhanViensByOrgOfficerIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);
        OrgOfficer orgOfficer = OrgOfficerResourceIT.createEntity(em);
        em.persist(orgOfficer);
        em.flush();
        cdtNhanVien.setOrgOfficer(orgOfficer);
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);
        Long orgOfficerId = orgOfficer.getId();

        // Get all the cdtNhanVienList where orgOfficer equals to orgOfficerId
        defaultCdtNhanVienShouldBeFound("orgOfficerId.equals=" + orgOfficerId);

        // Get all the cdtNhanVienList where orgOfficer equals to orgOfficerId + 1
        defaultCdtNhanVienShouldNotBeFound("orgOfficerId.equals=" + (orgOfficerId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtNhanViensByCdtChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);
        CdtChiDaoTuyen cdtChiDaoTuyen = CdtChiDaoTuyenResourceIT.createEntity(em);
        em.persist(cdtChiDaoTuyen);
        em.flush();
        cdtNhanVien.setCdtChiDaoTuyen(cdtChiDaoTuyen);
        cdtNhanVienRepository.saveAndFlush(cdtNhanVien);
        Long cdtChiDaoTuyenId = cdtChiDaoTuyen.getId();

        // Get all the cdtNhanVienList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId
        defaultCdtNhanVienShouldBeFound("cdtChiDaoTuyenId.equals=" + cdtChiDaoTuyenId);

        // Get all the cdtNhanVienList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId + 1
        defaultCdtNhanVienShouldNotBeFound("cdtChiDaoTuyenId.equals=" + (cdtChiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtNhanVienShouldBeFound(String filter) throws Exception {
        restCdtNhanVienMockMvc.perform(get("/api/cdt-nhan-viens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtNhanVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtNhanVienMockMvc.perform(get("/api/cdt-nhan-viens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtNhanVienShouldNotBeFound(String filter) throws Exception {
        restCdtNhanVienMockMvc.perform(get("/api/cdt-nhan-viens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtNhanVienMockMvc.perform(get("/api/cdt-nhan-viens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtNhanVien() throws Exception {
        // Get the cdtNhanVien
        restCdtNhanVienMockMvc.perform(get("/api/cdt-nhan-viens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtNhanVien() throws Exception {
        // Initialize the database
        cdtNhanVienService.save(cdtNhanVien);

        int databaseSizeBeforeUpdate = cdtNhanVienRepository.findAll().size();

        // Update the cdtNhanVien
        CdtNhanVien updatedCdtNhanVien = cdtNhanVienRepository.findById(cdtNhanVien.getId()).get();
        // Disconnect from session so that the updates on updatedCdtNhanVien are not directly saved in db
        em.detach(updatedCdtNhanVien);
        updatedCdtNhanVien
            .csytid(UPDATED_CSYTID);

        restCdtNhanVienMockMvc.perform(put("/api/cdt-nhan-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtNhanVien)))
            .andExpect(status().isOk());

        // Validate the CdtNhanVien in the database
        List<CdtNhanVien> cdtNhanVienList = cdtNhanVienRepository.findAll();
        assertThat(cdtNhanVienList).hasSize(databaseSizeBeforeUpdate);
        CdtNhanVien testCdtNhanVien = cdtNhanVienList.get(cdtNhanVienList.size() - 1);
        assertThat(testCdtNhanVien.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtNhanVien() throws Exception {
        int databaseSizeBeforeUpdate = cdtNhanVienRepository.findAll().size();

        // Create the CdtNhanVien

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtNhanVienMockMvc.perform(put("/api/cdt-nhan-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtNhanVien)))
            .andExpect(status().isBadRequest());

        // Validate the CdtNhanVien in the database
        List<CdtNhanVien> cdtNhanVienList = cdtNhanVienRepository.findAll();
        assertThat(cdtNhanVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtNhanVien() throws Exception {
        // Initialize the database
        cdtNhanVienService.save(cdtNhanVien);

        int databaseSizeBeforeDelete = cdtNhanVienRepository.findAll().size();

        // Delete the cdtNhanVien
        restCdtNhanVienMockMvc.perform(delete("/api/cdt-nhan-viens/{id}", cdtNhanVien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtNhanVien> cdtNhanVienList = cdtNhanVienRepository.findAll();
        assertThat(cdtNhanVienList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
