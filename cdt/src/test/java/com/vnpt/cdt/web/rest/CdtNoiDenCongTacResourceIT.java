package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtNoiDenCongTac;
import com.vnpt.cdt.domain.CdtNoiDen;
import com.vnpt.cdt.repository.CdtNoiDenCongTacRepository;
import com.vnpt.cdt.service.CdtNoiDenCongTacService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtNoiDenCongTacCriteria;
import com.vnpt.cdt.service.CdtNoiDenCongTacQueryService;

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
 * Integration tests for the {@link CdtNoiDenCongTacResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtNoiDenCongTacResourceIT {

    private static final String DEFAULT_MA_NOI_DEN = "AAAAAAAAAA";
    private static final String UPDATED_MA_NOI_DEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_NOI_DEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NOI_DEN = "BBBBBBBBBB";

    private static final Long DEFAULT_THU_TU_SAP_XEP = 1L;
    private static final Long UPDATED_THU_TU_SAP_XEP = 2L;
    private static final Long SMALLER_THU_TU_SAP_XEP = 1L - 1L;

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    @Autowired
    private CdtNoiDenCongTacRepository cdtNoiDenCongTacRepository;

    @Autowired
    private CdtNoiDenCongTacService cdtNoiDenCongTacService;

    @Autowired
    private CdtNoiDenCongTacQueryService cdtNoiDenCongTacQueryService;

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

    private MockMvc restCdtNoiDenCongTacMockMvc;

    private CdtNoiDenCongTac cdtNoiDenCongTac;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtNoiDenCongTacResource cdtNoiDenCongTacResource = new CdtNoiDenCongTacResource(cdtNoiDenCongTacService, cdtNoiDenCongTacQueryService);
        this.restCdtNoiDenCongTacMockMvc = MockMvcBuilders.standaloneSetup(cdtNoiDenCongTacResource)
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
    public static CdtNoiDenCongTac createEntity(EntityManager em) {
        CdtNoiDenCongTac cdtNoiDenCongTac = new CdtNoiDenCongTac()
            .maNoiDen(DEFAULT_MA_NOI_DEN)
            .tenNoiDen(DEFAULT_TEN_NOI_DEN)
            .thuTuSapXep(DEFAULT_THU_TU_SAP_XEP)
            .csytid(DEFAULT_CSYTID);
        return cdtNoiDenCongTac;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtNoiDenCongTac createUpdatedEntity(EntityManager em) {
        CdtNoiDenCongTac cdtNoiDenCongTac = new CdtNoiDenCongTac()
            .maNoiDen(UPDATED_MA_NOI_DEN)
            .tenNoiDen(UPDATED_TEN_NOI_DEN)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);
        return cdtNoiDenCongTac;
    }

    @BeforeEach
    public void initTest() {
        cdtNoiDenCongTac = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtNoiDenCongTac() throws Exception {
        int databaseSizeBeforeCreate = cdtNoiDenCongTacRepository.findAll().size();

        // Create the CdtNoiDenCongTac
        restCdtNoiDenCongTacMockMvc.perform(post("/api/cdt-noi-den-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtNoiDenCongTac)))
            .andExpect(status().isCreated());

        // Validate the CdtNoiDenCongTac in the database
        List<CdtNoiDenCongTac> cdtNoiDenCongTacList = cdtNoiDenCongTacRepository.findAll();
        assertThat(cdtNoiDenCongTacList).hasSize(databaseSizeBeforeCreate + 1);
        CdtNoiDenCongTac testCdtNoiDenCongTac = cdtNoiDenCongTacList.get(cdtNoiDenCongTacList.size() - 1);
        assertThat(testCdtNoiDenCongTac.getMaNoiDen()).isEqualTo(DEFAULT_MA_NOI_DEN);
        assertThat(testCdtNoiDenCongTac.getTenNoiDen()).isEqualTo(DEFAULT_TEN_NOI_DEN);
        assertThat(testCdtNoiDenCongTac.getThuTuSapXep()).isEqualTo(DEFAULT_THU_TU_SAP_XEP);
        assertThat(testCdtNoiDenCongTac.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtNoiDenCongTacWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtNoiDenCongTacRepository.findAll().size();

        // Create the CdtNoiDenCongTac with an existing ID
        cdtNoiDenCongTac.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtNoiDenCongTacMockMvc.perform(post("/api/cdt-noi-den-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtNoiDenCongTac)))
            .andExpect(status().isBadRequest());

        // Validate the CdtNoiDenCongTac in the database
        List<CdtNoiDenCongTac> cdtNoiDenCongTacList = cdtNoiDenCongTacRepository.findAll();
        assertThat(cdtNoiDenCongTacList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacs() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList
        restCdtNoiDenCongTacMockMvc.perform(get("/api/cdt-noi-den-cong-tacs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtNoiDenCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNoiDen").value(hasItem(DEFAULT_MA_NOI_DEN)))
            .andExpect(jsonPath("$.[*].tenNoiDen").value(hasItem(DEFAULT_TEN_NOI_DEN)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtNoiDenCongTac() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get the cdtNoiDenCongTac
        restCdtNoiDenCongTacMockMvc.perform(get("/api/cdt-noi-den-cong-tacs/{id}", cdtNoiDenCongTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtNoiDenCongTac.getId().intValue()))
            .andExpect(jsonPath("$.maNoiDen").value(DEFAULT_MA_NOI_DEN))
            .andExpect(jsonPath("$.tenNoiDen").value(DEFAULT_TEN_NOI_DEN))
            .andExpect(jsonPath("$.thuTuSapXep").value(DEFAULT_THU_TU_SAP_XEP.intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtNoiDenCongTacsByIdFiltering() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        Long id = cdtNoiDenCongTac.getId();

        defaultCdtNoiDenCongTacShouldBeFound("id.equals=" + id);
        defaultCdtNoiDenCongTacShouldNotBeFound("id.notEquals=" + id);

        defaultCdtNoiDenCongTacShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtNoiDenCongTacShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtNoiDenCongTacShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtNoiDenCongTacShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByMaNoiDenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where maNoiDen equals to DEFAULT_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("maNoiDen.equals=" + DEFAULT_MA_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where maNoiDen equals to UPDATED_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("maNoiDen.equals=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByMaNoiDenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where maNoiDen not equals to DEFAULT_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("maNoiDen.notEquals=" + DEFAULT_MA_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where maNoiDen not equals to UPDATED_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("maNoiDen.notEquals=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByMaNoiDenIsInShouldWork() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where maNoiDen in DEFAULT_MA_NOI_DEN or UPDATED_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("maNoiDen.in=" + DEFAULT_MA_NOI_DEN + "," + UPDATED_MA_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where maNoiDen equals to UPDATED_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("maNoiDen.in=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByMaNoiDenIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where maNoiDen is not null
        defaultCdtNoiDenCongTacShouldBeFound("maNoiDen.specified=true");

        // Get all the cdtNoiDenCongTacList where maNoiDen is null
        defaultCdtNoiDenCongTacShouldNotBeFound("maNoiDen.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByMaNoiDenContainsSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where maNoiDen contains DEFAULT_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("maNoiDen.contains=" + DEFAULT_MA_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where maNoiDen contains UPDATED_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("maNoiDen.contains=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByMaNoiDenNotContainsSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where maNoiDen does not contain DEFAULT_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("maNoiDen.doesNotContain=" + DEFAULT_MA_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where maNoiDen does not contain UPDATED_MA_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("maNoiDen.doesNotContain=" + UPDATED_MA_NOI_DEN);
    }


    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByTenNoiDenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where tenNoiDen equals to DEFAULT_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("tenNoiDen.equals=" + DEFAULT_TEN_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where tenNoiDen equals to UPDATED_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("tenNoiDen.equals=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByTenNoiDenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where tenNoiDen not equals to DEFAULT_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("tenNoiDen.notEquals=" + DEFAULT_TEN_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where tenNoiDen not equals to UPDATED_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("tenNoiDen.notEquals=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByTenNoiDenIsInShouldWork() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where tenNoiDen in DEFAULT_TEN_NOI_DEN or UPDATED_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("tenNoiDen.in=" + DEFAULT_TEN_NOI_DEN + "," + UPDATED_TEN_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where tenNoiDen equals to UPDATED_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("tenNoiDen.in=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByTenNoiDenIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where tenNoiDen is not null
        defaultCdtNoiDenCongTacShouldBeFound("tenNoiDen.specified=true");

        // Get all the cdtNoiDenCongTacList where tenNoiDen is null
        defaultCdtNoiDenCongTacShouldNotBeFound("tenNoiDen.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByTenNoiDenContainsSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where tenNoiDen contains DEFAULT_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("tenNoiDen.contains=" + DEFAULT_TEN_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where tenNoiDen contains UPDATED_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("tenNoiDen.contains=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByTenNoiDenNotContainsSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where tenNoiDen does not contain DEFAULT_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldNotBeFound("tenNoiDen.doesNotContain=" + DEFAULT_TEN_NOI_DEN);

        // Get all the cdtNoiDenCongTacList where tenNoiDen does not contain UPDATED_TEN_NOI_DEN
        defaultCdtNoiDenCongTacShouldBeFound("tenNoiDen.doesNotContain=" + UPDATED_TEN_NOI_DEN);
    }


    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByThuTuSapXepIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldBeFound("thuTuSapXep.equals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldNotBeFound("thuTuSapXep.equals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByThuTuSapXepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep not equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldNotBeFound("thuTuSapXep.notEquals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep not equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldBeFound("thuTuSapXep.notEquals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByThuTuSapXepIsInShouldWork() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep in DEFAULT_THU_TU_SAP_XEP or UPDATED_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldBeFound("thuTuSapXep.in=" + DEFAULT_THU_TU_SAP_XEP + "," + UPDATED_THU_TU_SAP_XEP);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldNotBeFound("thuTuSapXep.in=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByThuTuSapXepIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is not null
        defaultCdtNoiDenCongTacShouldBeFound("thuTuSapXep.specified=true");

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is null
        defaultCdtNoiDenCongTacShouldNotBeFound("thuTuSapXep.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByThuTuSapXepIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is greater than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldBeFound("thuTuSapXep.greaterThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is greater than or equal to UPDATED_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldNotBeFound("thuTuSapXep.greaterThanOrEqual=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByThuTuSapXepIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is less than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldBeFound("thuTuSapXep.lessThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is less than or equal to SMALLER_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldNotBeFound("thuTuSapXep.lessThanOrEqual=" + SMALLER_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByThuTuSapXepIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is less than DEFAULT_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldNotBeFound("thuTuSapXep.lessThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is less than UPDATED_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldBeFound("thuTuSapXep.lessThan=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByThuTuSapXepIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is greater than DEFAULT_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldNotBeFound("thuTuSapXep.greaterThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtNoiDenCongTacList where thuTuSapXep is greater than SMALLER_THU_TU_SAP_XEP
        defaultCdtNoiDenCongTacShouldBeFound("thuTuSapXep.greaterThan=" + SMALLER_THU_TU_SAP_XEP);
    }


    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where csytid equals to DEFAULT_CSYTID
        defaultCdtNoiDenCongTacShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenCongTacList where csytid equals to UPDATED_CSYTID
        defaultCdtNoiDenCongTacShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where csytid not equals to DEFAULT_CSYTID
        defaultCdtNoiDenCongTacShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenCongTacList where csytid not equals to UPDATED_CSYTID
        defaultCdtNoiDenCongTacShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtNoiDenCongTacShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtNoiDenCongTacList where csytid equals to UPDATED_CSYTID
        defaultCdtNoiDenCongTacShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where csytid is not null
        defaultCdtNoiDenCongTacShouldBeFound("csytid.specified=true");

        // Get all the cdtNoiDenCongTacList where csytid is null
        defaultCdtNoiDenCongTacShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtNoiDenCongTacShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenCongTacList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtNoiDenCongTacShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtNoiDenCongTacShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenCongTacList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtNoiDenCongTacShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where csytid is less than DEFAULT_CSYTID
        defaultCdtNoiDenCongTacShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenCongTacList where csytid is less than UPDATED_CSYTID
        defaultCdtNoiDenCongTacShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);

        // Get all the cdtNoiDenCongTacList where csytid is greater than DEFAULT_CSYTID
        defaultCdtNoiDenCongTacShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtNoiDenCongTacList where csytid is greater than SMALLER_CSYTID
        defaultCdtNoiDenCongTacShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtNoiDenCongTacsByNoidenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);
        CdtNoiDen noiden = CdtNoiDenResourceIT.createEntity(em);
        em.persist(noiden);
        em.flush();
        cdtNoiDenCongTac.addNoiden(noiden);
        cdtNoiDenCongTacRepository.saveAndFlush(cdtNoiDenCongTac);
        Long noidenId = noiden.getId();

        // Get all the cdtNoiDenCongTacList where noiden equals to noidenId
        defaultCdtNoiDenCongTacShouldBeFound("noidenId.equals=" + noidenId);

        // Get all the cdtNoiDenCongTacList where noiden equals to noidenId + 1
        defaultCdtNoiDenCongTacShouldNotBeFound("noidenId.equals=" + (noidenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtNoiDenCongTacShouldBeFound(String filter) throws Exception {
        restCdtNoiDenCongTacMockMvc.perform(get("/api/cdt-noi-den-cong-tacs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtNoiDenCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNoiDen").value(hasItem(DEFAULT_MA_NOI_DEN)))
            .andExpect(jsonPath("$.[*].tenNoiDen").value(hasItem(DEFAULT_TEN_NOI_DEN)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtNoiDenCongTacMockMvc.perform(get("/api/cdt-noi-den-cong-tacs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtNoiDenCongTacShouldNotBeFound(String filter) throws Exception {
        restCdtNoiDenCongTacMockMvc.perform(get("/api/cdt-noi-den-cong-tacs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtNoiDenCongTacMockMvc.perform(get("/api/cdt-noi-den-cong-tacs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtNoiDenCongTac() throws Exception {
        // Get the cdtNoiDenCongTac
        restCdtNoiDenCongTacMockMvc.perform(get("/api/cdt-noi-den-cong-tacs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtNoiDenCongTac() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacService.save(cdtNoiDenCongTac);

        int databaseSizeBeforeUpdate = cdtNoiDenCongTacRepository.findAll().size();

        // Update the cdtNoiDenCongTac
        CdtNoiDenCongTac updatedCdtNoiDenCongTac = cdtNoiDenCongTacRepository.findById(cdtNoiDenCongTac.getId()).get();
        // Disconnect from session so that the updates on updatedCdtNoiDenCongTac are not directly saved in db
        em.detach(updatedCdtNoiDenCongTac);
        updatedCdtNoiDenCongTac
            .maNoiDen(UPDATED_MA_NOI_DEN)
            .tenNoiDen(UPDATED_TEN_NOI_DEN)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);

        restCdtNoiDenCongTacMockMvc.perform(put("/api/cdt-noi-den-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtNoiDenCongTac)))
            .andExpect(status().isOk());

        // Validate the CdtNoiDenCongTac in the database
        List<CdtNoiDenCongTac> cdtNoiDenCongTacList = cdtNoiDenCongTacRepository.findAll();
        assertThat(cdtNoiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
        CdtNoiDenCongTac testCdtNoiDenCongTac = cdtNoiDenCongTacList.get(cdtNoiDenCongTacList.size() - 1);
        assertThat(testCdtNoiDenCongTac.getMaNoiDen()).isEqualTo(UPDATED_MA_NOI_DEN);
        assertThat(testCdtNoiDenCongTac.getTenNoiDen()).isEqualTo(UPDATED_TEN_NOI_DEN);
        assertThat(testCdtNoiDenCongTac.getThuTuSapXep()).isEqualTo(UPDATED_THU_TU_SAP_XEP);
        assertThat(testCdtNoiDenCongTac.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = cdtNoiDenCongTacRepository.findAll().size();

        // Create the CdtNoiDenCongTac

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtNoiDenCongTacMockMvc.perform(put("/api/cdt-noi-den-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtNoiDenCongTac)))
            .andExpect(status().isBadRequest());

        // Validate the CdtNoiDenCongTac in the database
        List<CdtNoiDenCongTac> cdtNoiDenCongTacList = cdtNoiDenCongTacRepository.findAll();
        assertThat(cdtNoiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtNoiDenCongTac() throws Exception {
        // Initialize the database
        cdtNoiDenCongTacService.save(cdtNoiDenCongTac);

        int databaseSizeBeforeDelete = cdtNoiDenCongTacRepository.findAll().size();

        // Delete the cdtNoiDenCongTac
        restCdtNoiDenCongTacMockMvc.perform(delete("/api/cdt-noi-den-cong-tacs/{id}", cdtNoiDenCongTac.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtNoiDenCongTac> cdtNoiDenCongTacList = cdtNoiDenCongTacRepository.findAll();
        assertThat(cdtNoiDenCongTacList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
