package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtLyDoCongTac;
import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.repository.CdtLyDoCongTacRepository;
import com.vnpt.cdt.service.CdtLyDoCongTacService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtLyDoCongTacCriteria;
import com.vnpt.cdt.service.CdtLyDoCongTacQueryService;

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
 * Integration tests for the {@link CdtLyDoCongTacResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtLyDoCongTacResourceIT {

    private static final String DEFAULT_MA_LY_DO = "AAAAAAAAAA";
    private static final String UPDATED_MA_LY_DO = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_LY_DO = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LY_DO = "BBBBBBBBBB";

    private static final Long DEFAULT_THU_TU_SAP_XEP = 1L;
    private static final Long UPDATED_THU_TU_SAP_XEP = 2L;
    private static final Long SMALLER_THU_TU_SAP_XEP = 1L - 1L;

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    @Autowired
    private CdtLyDoCongTacRepository cdtLyDoCongTacRepository;

    @Autowired
    private CdtLyDoCongTacService cdtLyDoCongTacService;

    @Autowired
    private CdtLyDoCongTacQueryService cdtLyDoCongTacQueryService;

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

    private MockMvc restCdtLyDoCongTacMockMvc;

    private CdtLyDoCongTac cdtLyDoCongTac;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtLyDoCongTacResource cdtLyDoCongTacResource = new CdtLyDoCongTacResource(cdtLyDoCongTacService, cdtLyDoCongTacQueryService);
        this.restCdtLyDoCongTacMockMvc = MockMvcBuilders.standaloneSetup(cdtLyDoCongTacResource)
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
    public static CdtLyDoCongTac createEntity(EntityManager em) {
        CdtLyDoCongTac cdtLyDoCongTac = new CdtLyDoCongTac()
            .maLyDo(DEFAULT_MA_LY_DO)
            .tenLyDo(DEFAULT_TEN_LY_DO)
            .thuTuSapXep(DEFAULT_THU_TU_SAP_XEP)
            .csytid(DEFAULT_CSYTID);
        return cdtLyDoCongTac;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtLyDoCongTac createUpdatedEntity(EntityManager em) {
        CdtLyDoCongTac cdtLyDoCongTac = new CdtLyDoCongTac()
            .maLyDo(UPDATED_MA_LY_DO)
            .tenLyDo(UPDATED_TEN_LY_DO)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);
        return cdtLyDoCongTac;
    }

    @BeforeEach
    public void initTest() {
        cdtLyDoCongTac = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtLyDoCongTac() throws Exception {
        int databaseSizeBeforeCreate = cdtLyDoCongTacRepository.findAll().size();

        // Create the CdtLyDoCongTac
        restCdtLyDoCongTacMockMvc.perform(post("/api/cdt-ly-do-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtLyDoCongTac)))
            .andExpect(status().isCreated());

        // Validate the CdtLyDoCongTac in the database
        List<CdtLyDoCongTac> cdtLyDoCongTacList = cdtLyDoCongTacRepository.findAll();
        assertThat(cdtLyDoCongTacList).hasSize(databaseSizeBeforeCreate + 1);
        CdtLyDoCongTac testCdtLyDoCongTac = cdtLyDoCongTacList.get(cdtLyDoCongTacList.size() - 1);
        assertThat(testCdtLyDoCongTac.getMaLyDo()).isEqualTo(DEFAULT_MA_LY_DO);
        assertThat(testCdtLyDoCongTac.getTenLyDo()).isEqualTo(DEFAULT_TEN_LY_DO);
        assertThat(testCdtLyDoCongTac.getThuTuSapXep()).isEqualTo(DEFAULT_THU_TU_SAP_XEP);
        assertThat(testCdtLyDoCongTac.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtLyDoCongTacWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtLyDoCongTacRepository.findAll().size();

        // Create the CdtLyDoCongTac with an existing ID
        cdtLyDoCongTac.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtLyDoCongTacMockMvc.perform(post("/api/cdt-ly-do-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtLyDoCongTac)))
            .andExpect(status().isBadRequest());

        // Validate the CdtLyDoCongTac in the database
        List<CdtLyDoCongTac> cdtLyDoCongTacList = cdtLyDoCongTacRepository.findAll();
        assertThat(cdtLyDoCongTacList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtLyDoCongTacs() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList
        restCdtLyDoCongTacMockMvc.perform(get("/api/cdt-ly-do-cong-tacs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtLyDoCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLyDo").value(hasItem(DEFAULT_MA_LY_DO)))
            .andExpect(jsonPath("$.[*].tenLyDo").value(hasItem(DEFAULT_TEN_LY_DO)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtLyDoCongTac() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get the cdtLyDoCongTac
        restCdtLyDoCongTacMockMvc.perform(get("/api/cdt-ly-do-cong-tacs/{id}", cdtLyDoCongTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtLyDoCongTac.getId().intValue()))
            .andExpect(jsonPath("$.maLyDo").value(DEFAULT_MA_LY_DO))
            .andExpect(jsonPath("$.tenLyDo").value(DEFAULT_TEN_LY_DO))
            .andExpect(jsonPath("$.thuTuSapXep").value(DEFAULT_THU_TU_SAP_XEP.intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtLyDoCongTacsByIdFiltering() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        Long id = cdtLyDoCongTac.getId();

        defaultCdtLyDoCongTacShouldBeFound("id.equals=" + id);
        defaultCdtLyDoCongTacShouldNotBeFound("id.notEquals=" + id);

        defaultCdtLyDoCongTacShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtLyDoCongTacShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtLyDoCongTacShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtLyDoCongTacShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByMaLyDoIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where maLyDo equals to DEFAULT_MA_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("maLyDo.equals=" + DEFAULT_MA_LY_DO);

        // Get all the cdtLyDoCongTacList where maLyDo equals to UPDATED_MA_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("maLyDo.equals=" + UPDATED_MA_LY_DO);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByMaLyDoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where maLyDo not equals to DEFAULT_MA_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("maLyDo.notEquals=" + DEFAULT_MA_LY_DO);

        // Get all the cdtLyDoCongTacList where maLyDo not equals to UPDATED_MA_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("maLyDo.notEquals=" + UPDATED_MA_LY_DO);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByMaLyDoIsInShouldWork() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where maLyDo in DEFAULT_MA_LY_DO or UPDATED_MA_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("maLyDo.in=" + DEFAULT_MA_LY_DO + "," + UPDATED_MA_LY_DO);

        // Get all the cdtLyDoCongTacList where maLyDo equals to UPDATED_MA_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("maLyDo.in=" + UPDATED_MA_LY_DO);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByMaLyDoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where maLyDo is not null
        defaultCdtLyDoCongTacShouldBeFound("maLyDo.specified=true");

        // Get all the cdtLyDoCongTacList where maLyDo is null
        defaultCdtLyDoCongTacShouldNotBeFound("maLyDo.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByMaLyDoContainsSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where maLyDo contains DEFAULT_MA_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("maLyDo.contains=" + DEFAULT_MA_LY_DO);

        // Get all the cdtLyDoCongTacList where maLyDo contains UPDATED_MA_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("maLyDo.contains=" + UPDATED_MA_LY_DO);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByMaLyDoNotContainsSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where maLyDo does not contain DEFAULT_MA_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("maLyDo.doesNotContain=" + DEFAULT_MA_LY_DO);

        // Get all the cdtLyDoCongTacList where maLyDo does not contain UPDATED_MA_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("maLyDo.doesNotContain=" + UPDATED_MA_LY_DO);
    }


    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByTenLyDoIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where tenLyDo equals to DEFAULT_TEN_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("tenLyDo.equals=" + DEFAULT_TEN_LY_DO);

        // Get all the cdtLyDoCongTacList where tenLyDo equals to UPDATED_TEN_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("tenLyDo.equals=" + UPDATED_TEN_LY_DO);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByTenLyDoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where tenLyDo not equals to DEFAULT_TEN_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("tenLyDo.notEquals=" + DEFAULT_TEN_LY_DO);

        // Get all the cdtLyDoCongTacList where tenLyDo not equals to UPDATED_TEN_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("tenLyDo.notEquals=" + UPDATED_TEN_LY_DO);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByTenLyDoIsInShouldWork() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where tenLyDo in DEFAULT_TEN_LY_DO or UPDATED_TEN_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("tenLyDo.in=" + DEFAULT_TEN_LY_DO + "," + UPDATED_TEN_LY_DO);

        // Get all the cdtLyDoCongTacList where tenLyDo equals to UPDATED_TEN_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("tenLyDo.in=" + UPDATED_TEN_LY_DO);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByTenLyDoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where tenLyDo is not null
        defaultCdtLyDoCongTacShouldBeFound("tenLyDo.specified=true");

        // Get all the cdtLyDoCongTacList where tenLyDo is null
        defaultCdtLyDoCongTacShouldNotBeFound("tenLyDo.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByTenLyDoContainsSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where tenLyDo contains DEFAULT_TEN_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("tenLyDo.contains=" + DEFAULT_TEN_LY_DO);

        // Get all the cdtLyDoCongTacList where tenLyDo contains UPDATED_TEN_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("tenLyDo.contains=" + UPDATED_TEN_LY_DO);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByTenLyDoNotContainsSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where tenLyDo does not contain DEFAULT_TEN_LY_DO
        defaultCdtLyDoCongTacShouldNotBeFound("tenLyDo.doesNotContain=" + DEFAULT_TEN_LY_DO);

        // Get all the cdtLyDoCongTacList where tenLyDo does not contain UPDATED_TEN_LY_DO
        defaultCdtLyDoCongTacShouldBeFound("tenLyDo.doesNotContain=" + UPDATED_TEN_LY_DO);
    }


    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByThuTuSapXepIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where thuTuSapXep equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldBeFound("thuTuSapXep.equals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtLyDoCongTacList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldNotBeFound("thuTuSapXep.equals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByThuTuSapXepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where thuTuSapXep not equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldNotBeFound("thuTuSapXep.notEquals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtLyDoCongTacList where thuTuSapXep not equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldBeFound("thuTuSapXep.notEquals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByThuTuSapXepIsInShouldWork() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where thuTuSapXep in DEFAULT_THU_TU_SAP_XEP or UPDATED_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldBeFound("thuTuSapXep.in=" + DEFAULT_THU_TU_SAP_XEP + "," + UPDATED_THU_TU_SAP_XEP);

        // Get all the cdtLyDoCongTacList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldNotBeFound("thuTuSapXep.in=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByThuTuSapXepIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where thuTuSapXep is not null
        defaultCdtLyDoCongTacShouldBeFound("thuTuSapXep.specified=true");

        // Get all the cdtLyDoCongTacList where thuTuSapXep is null
        defaultCdtLyDoCongTacShouldNotBeFound("thuTuSapXep.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByThuTuSapXepIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where thuTuSapXep is greater than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldBeFound("thuTuSapXep.greaterThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtLyDoCongTacList where thuTuSapXep is greater than or equal to UPDATED_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldNotBeFound("thuTuSapXep.greaterThanOrEqual=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByThuTuSapXepIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where thuTuSapXep is less than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldBeFound("thuTuSapXep.lessThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtLyDoCongTacList where thuTuSapXep is less than or equal to SMALLER_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldNotBeFound("thuTuSapXep.lessThanOrEqual=" + SMALLER_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByThuTuSapXepIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where thuTuSapXep is less than DEFAULT_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldNotBeFound("thuTuSapXep.lessThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtLyDoCongTacList where thuTuSapXep is less than UPDATED_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldBeFound("thuTuSapXep.lessThan=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByThuTuSapXepIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where thuTuSapXep is greater than DEFAULT_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldNotBeFound("thuTuSapXep.greaterThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtLyDoCongTacList where thuTuSapXep is greater than SMALLER_THU_TU_SAP_XEP
        defaultCdtLyDoCongTacShouldBeFound("thuTuSapXep.greaterThan=" + SMALLER_THU_TU_SAP_XEP);
    }


    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where csytid equals to DEFAULT_CSYTID
        defaultCdtLyDoCongTacShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtLyDoCongTacList where csytid equals to UPDATED_CSYTID
        defaultCdtLyDoCongTacShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where csytid not equals to DEFAULT_CSYTID
        defaultCdtLyDoCongTacShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtLyDoCongTacList where csytid not equals to UPDATED_CSYTID
        defaultCdtLyDoCongTacShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtLyDoCongTacShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtLyDoCongTacList where csytid equals to UPDATED_CSYTID
        defaultCdtLyDoCongTacShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where csytid is not null
        defaultCdtLyDoCongTacShouldBeFound("csytid.specified=true");

        // Get all the cdtLyDoCongTacList where csytid is null
        defaultCdtLyDoCongTacShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtLyDoCongTacShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtLyDoCongTacList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtLyDoCongTacShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtLyDoCongTacShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtLyDoCongTacList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtLyDoCongTacShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where csytid is less than DEFAULT_CSYTID
        defaultCdtLyDoCongTacShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtLyDoCongTacList where csytid is less than UPDATED_CSYTID
        defaultCdtLyDoCongTacShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);

        // Get all the cdtLyDoCongTacList where csytid is greater than DEFAULT_CSYTID
        defaultCdtLyDoCongTacShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtLyDoCongTacList where csytid is greater than SMALLER_CSYTID
        defaultCdtLyDoCongTacShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtLyDoCongTacsByCdtChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);
        CdtChiDaoTuyen cdtChiDaoTuyen = CdtChiDaoTuyenResourceIT.createEntity(em);
        em.persist(cdtChiDaoTuyen);
        em.flush();
        cdtLyDoCongTac.setCdtChiDaoTuyen(cdtChiDaoTuyen);
        cdtLyDoCongTacRepository.saveAndFlush(cdtLyDoCongTac);
        Long cdtChiDaoTuyenId = cdtChiDaoTuyen.getId();

        // Get all the cdtLyDoCongTacList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId
        defaultCdtLyDoCongTacShouldBeFound("cdtChiDaoTuyenId.equals=" + cdtChiDaoTuyenId);

        // Get all the cdtLyDoCongTacList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId + 1
        defaultCdtLyDoCongTacShouldNotBeFound("cdtChiDaoTuyenId.equals=" + (cdtChiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtLyDoCongTacShouldBeFound(String filter) throws Exception {
        restCdtLyDoCongTacMockMvc.perform(get("/api/cdt-ly-do-cong-tacs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtLyDoCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLyDo").value(hasItem(DEFAULT_MA_LY_DO)))
            .andExpect(jsonPath("$.[*].tenLyDo").value(hasItem(DEFAULT_TEN_LY_DO)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtLyDoCongTacMockMvc.perform(get("/api/cdt-ly-do-cong-tacs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtLyDoCongTacShouldNotBeFound(String filter) throws Exception {
        restCdtLyDoCongTacMockMvc.perform(get("/api/cdt-ly-do-cong-tacs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtLyDoCongTacMockMvc.perform(get("/api/cdt-ly-do-cong-tacs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtLyDoCongTac() throws Exception {
        // Get the cdtLyDoCongTac
        restCdtLyDoCongTacMockMvc.perform(get("/api/cdt-ly-do-cong-tacs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtLyDoCongTac() throws Exception {
        // Initialize the database
        cdtLyDoCongTacService.save(cdtLyDoCongTac);

        int databaseSizeBeforeUpdate = cdtLyDoCongTacRepository.findAll().size();

        // Update the cdtLyDoCongTac
        CdtLyDoCongTac updatedCdtLyDoCongTac = cdtLyDoCongTacRepository.findById(cdtLyDoCongTac.getId()).get();
        // Disconnect from session so that the updates on updatedCdtLyDoCongTac are not directly saved in db
        em.detach(updatedCdtLyDoCongTac);
        updatedCdtLyDoCongTac
            .maLyDo(UPDATED_MA_LY_DO)
            .tenLyDo(UPDATED_TEN_LY_DO)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);

        restCdtLyDoCongTacMockMvc.perform(put("/api/cdt-ly-do-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtLyDoCongTac)))
            .andExpect(status().isOk());

        // Validate the CdtLyDoCongTac in the database
        List<CdtLyDoCongTac> cdtLyDoCongTacList = cdtLyDoCongTacRepository.findAll();
        assertThat(cdtLyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
        CdtLyDoCongTac testCdtLyDoCongTac = cdtLyDoCongTacList.get(cdtLyDoCongTacList.size() - 1);
        assertThat(testCdtLyDoCongTac.getMaLyDo()).isEqualTo(UPDATED_MA_LY_DO);
        assertThat(testCdtLyDoCongTac.getTenLyDo()).isEqualTo(UPDATED_TEN_LY_DO);
        assertThat(testCdtLyDoCongTac.getThuTuSapXep()).isEqualTo(UPDATED_THU_TU_SAP_XEP);
        assertThat(testCdtLyDoCongTac.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtLyDoCongTac() throws Exception {
        int databaseSizeBeforeUpdate = cdtLyDoCongTacRepository.findAll().size();

        // Create the CdtLyDoCongTac

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtLyDoCongTacMockMvc.perform(put("/api/cdt-ly-do-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtLyDoCongTac)))
            .andExpect(status().isBadRequest());

        // Validate the CdtLyDoCongTac in the database
        List<CdtLyDoCongTac> cdtLyDoCongTacList = cdtLyDoCongTacRepository.findAll();
        assertThat(cdtLyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtLyDoCongTac() throws Exception {
        // Initialize the database
        cdtLyDoCongTacService.save(cdtLyDoCongTac);

        int databaseSizeBeforeDelete = cdtLyDoCongTacRepository.findAll().size();

        // Delete the cdtLyDoCongTac
        restCdtLyDoCongTacMockMvc.perform(delete("/api/cdt-ly-do-cong-tacs/{id}", cdtLyDoCongTac.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtLyDoCongTac> cdtLyDoCongTacList = cdtLyDoCongTacRepository.findAll();
        assertThat(cdtLyDoCongTacList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
