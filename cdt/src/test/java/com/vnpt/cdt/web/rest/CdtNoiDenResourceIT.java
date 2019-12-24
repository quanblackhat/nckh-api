package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtNoiDen;
import com.vnpt.cdt.domain.CdtNoiDenCongTac;
import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.repository.CdtNoiDenRepository;
import com.vnpt.cdt.service.CdtNoiDenService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtNoiDenCriteria;
import com.vnpt.cdt.service.CdtNoiDenQueryService;

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
 * Integration tests for the {@link CdtNoiDenResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtNoiDenResourceIT {

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    @Autowired
    private CdtNoiDenRepository cdtNoiDenRepository;

    @Autowired
    private CdtNoiDenService cdtNoiDenService;

    @Autowired
    private CdtNoiDenQueryService cdtNoiDenQueryService;

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

    private MockMvc restCdtNoiDenMockMvc;

    private CdtNoiDen cdtNoiDen;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtNoiDenResource cdtNoiDenResource = new CdtNoiDenResource(cdtNoiDenService, cdtNoiDenQueryService);
        this.restCdtNoiDenMockMvc = MockMvcBuilders.standaloneSetup(cdtNoiDenResource)
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
    public static CdtNoiDen createEntity(EntityManager em) {
        CdtNoiDen cdtNoiDen = new CdtNoiDen()
            .csytid(DEFAULT_CSYTID);
        return cdtNoiDen;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtNoiDen createUpdatedEntity(EntityManager em) {
        CdtNoiDen cdtNoiDen = new CdtNoiDen()
            .csytid(UPDATED_CSYTID);
        return cdtNoiDen;
    }

    @BeforeEach
    public void initTest() {
        cdtNoiDen = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtNoiDen() throws Exception {
        int databaseSizeBeforeCreate = cdtNoiDenRepository.findAll().size();

        // Create the CdtNoiDen
        restCdtNoiDenMockMvc.perform(post("/api/cdt-noi-dens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtNoiDen)))
            .andExpect(status().isCreated());

        // Validate the CdtNoiDen in the database
        List<CdtNoiDen> cdtNoiDenList = cdtNoiDenRepository.findAll();
        assertThat(cdtNoiDenList).hasSize(databaseSizeBeforeCreate + 1);
        CdtNoiDen testCdtNoiDen = cdtNoiDenList.get(cdtNoiDenList.size() - 1);
        assertThat(testCdtNoiDen.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtNoiDenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtNoiDenRepository.findAll().size();

        // Create the CdtNoiDen with an existing ID
        cdtNoiDen.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtNoiDenMockMvc.perform(post("/api/cdt-noi-dens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtNoiDen)))
            .andExpect(status().isBadRequest());

        // Validate the CdtNoiDen in the database
        List<CdtNoiDen> cdtNoiDenList = cdtNoiDenRepository.findAll();
        assertThat(cdtNoiDenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtNoiDens() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get all the cdtNoiDenList
        restCdtNoiDenMockMvc.perform(get("/api/cdt-noi-dens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtNoiDen.getId().intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtNoiDen() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get the cdtNoiDen
        restCdtNoiDenMockMvc.perform(get("/api/cdt-noi-dens/{id}", cdtNoiDen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtNoiDen.getId().intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtNoiDensByIdFiltering() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        Long id = cdtNoiDen.getId();

        defaultCdtNoiDenShouldBeFound("id.equals=" + id);
        defaultCdtNoiDenShouldNotBeFound("id.notEquals=" + id);

        defaultCdtNoiDenShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtNoiDenShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtNoiDenShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtNoiDenShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtNoiDensByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get all the cdtNoiDenList where csytid equals to DEFAULT_CSYTID
        defaultCdtNoiDenShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenList where csytid equals to UPDATED_CSYTID
        defaultCdtNoiDenShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDensByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get all the cdtNoiDenList where csytid not equals to DEFAULT_CSYTID
        defaultCdtNoiDenShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenList where csytid not equals to UPDATED_CSYTID
        defaultCdtNoiDenShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDensByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get all the cdtNoiDenList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtNoiDenShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtNoiDenList where csytid equals to UPDATED_CSYTID
        defaultCdtNoiDenShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDensByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get all the cdtNoiDenList where csytid is not null
        defaultCdtNoiDenShouldBeFound("csytid.specified=true");

        // Get all the cdtNoiDenList where csytid is null
        defaultCdtNoiDenShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtNoiDensByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get all the cdtNoiDenList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtNoiDenShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtNoiDenShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDensByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get all the cdtNoiDenList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtNoiDenShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtNoiDenShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDensByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get all the cdtNoiDenList where csytid is less than DEFAULT_CSYTID
        defaultCdtNoiDenShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenList where csytid is less than UPDATED_CSYTID
        defaultCdtNoiDenShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDensByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);

        // Get all the cdtNoiDenList where csytid is greater than DEFAULT_CSYTID
        defaultCdtNoiDenShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenList where csytid is greater than SMALLER_CSYTID
        defaultCdtNoiDenShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtNoiDensByCdtNoiDenCongTacIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);
        CdtNoiDenCongTac cdtNoiDenCongTac = CdtNoiDenCongTacResourceIT.createEntity(em);
        em.persist(cdtNoiDenCongTac);
        em.flush();
        cdtNoiDen.setCdtNoiDenCongTac(cdtNoiDenCongTac);
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);
        Long cdtNoiDenCongTacId = cdtNoiDenCongTac.getId();

        // Get all the cdtNoiDenList where cdtNoiDenCongTac equals to cdtNoiDenCongTacId
        defaultCdtNoiDenShouldBeFound("cdtNoiDenCongTacId.equals=" + cdtNoiDenCongTacId);

        // Get all the cdtNoiDenList where cdtNoiDenCongTac equals to cdtNoiDenCongTacId + 1
        defaultCdtNoiDenShouldNotBeFound("cdtNoiDenCongTacId.equals=" + (cdtNoiDenCongTacId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtNoiDensByCdtChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);
        CdtChiDaoTuyen cdtChiDaoTuyen = CdtChiDaoTuyenResourceIT.createEntity(em);
        em.persist(cdtChiDaoTuyen);
        em.flush();
        cdtNoiDen.setCdtChiDaoTuyen(cdtChiDaoTuyen);
        cdtNoiDenRepository.saveAndFlush(cdtNoiDen);
        Long cdtChiDaoTuyenId = cdtChiDaoTuyen.getId();

        // Get all the cdtNoiDenList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId
        defaultCdtNoiDenShouldBeFound("cdtChiDaoTuyenId.equals=" + cdtChiDaoTuyenId);

        // Get all the cdtNoiDenList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId + 1
        defaultCdtNoiDenShouldNotBeFound("cdtChiDaoTuyenId.equals=" + (cdtChiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtNoiDenShouldBeFound(String filter) throws Exception {
        restCdtNoiDenMockMvc.perform(get("/api/cdt-noi-dens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtNoiDen.getId().intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtNoiDenMockMvc.perform(get("/api/cdt-noi-dens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtNoiDenShouldNotBeFound(String filter) throws Exception {
        restCdtNoiDenMockMvc.perform(get("/api/cdt-noi-dens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtNoiDenMockMvc.perform(get("/api/cdt-noi-dens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtNoiDen() throws Exception {
        // Get the cdtNoiDen
        restCdtNoiDenMockMvc.perform(get("/api/cdt-noi-dens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtNoiDen() throws Exception {
        // Initialize the database
        cdtNoiDenService.save(cdtNoiDen);

        int databaseSizeBeforeUpdate = cdtNoiDenRepository.findAll().size();

        // Update the cdtNoiDen
        CdtNoiDen updatedCdtNoiDen = cdtNoiDenRepository.findById(cdtNoiDen.getId()).get();
        // Disconnect from session so that the updates on updatedCdtNoiDen are not directly saved in db
        em.detach(updatedCdtNoiDen);
        updatedCdtNoiDen
            .csytid(UPDATED_CSYTID);

        restCdtNoiDenMockMvc.perform(put("/api/cdt-noi-dens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtNoiDen)))
            .andExpect(status().isOk());

        // Validate the CdtNoiDen in the database
        List<CdtNoiDen> cdtNoiDenList = cdtNoiDenRepository.findAll();
        assertThat(cdtNoiDenList).hasSize(databaseSizeBeforeUpdate);
        CdtNoiDen testCdtNoiDen = cdtNoiDenList.get(cdtNoiDenList.size() - 1);
        assertThat(testCdtNoiDen.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtNoiDen() throws Exception {
        int databaseSizeBeforeUpdate = cdtNoiDenRepository.findAll().size();

        // Create the CdtNoiDen

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtNoiDenMockMvc.perform(put("/api/cdt-noi-dens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtNoiDen)))
            .andExpect(status().isBadRequest());

        // Validate the CdtNoiDen in the database
        List<CdtNoiDen> cdtNoiDenList = cdtNoiDenRepository.findAll();
        assertThat(cdtNoiDenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtNoiDen() throws Exception {
        // Initialize the database
        cdtNoiDenService.save(cdtNoiDen);

        int databaseSizeBeforeDelete = cdtNoiDenRepository.findAll().size();

        // Delete the cdtNoiDen
        restCdtNoiDenMockMvc.perform(delete("/api/cdt-noi-dens/{id}", cdtNoiDen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtNoiDen> cdtNoiDenList = cdtNoiDenRepository.findAll();
        assertThat(cdtNoiDenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
