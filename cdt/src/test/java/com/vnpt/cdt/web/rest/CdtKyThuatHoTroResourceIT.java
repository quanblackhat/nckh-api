package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtKyThuatHoTro;
import com.vnpt.cdt.domain.CdtKyThuat;
import com.vnpt.cdt.repository.CdtKyThuatHoTroRepository;
import com.vnpt.cdt.service.CdtKyThuatHoTroService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtKyThuatHoTroCriteria;
import com.vnpt.cdt.service.CdtKyThuatHoTroQueryService;

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
 * Integration tests for the {@link CdtKyThuatHoTroResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtKyThuatHoTroResourceIT {

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
    private CdtKyThuatHoTroRepository cdtKyThuatHoTroRepository;

    @Autowired
    private CdtKyThuatHoTroService cdtKyThuatHoTroService;

    @Autowired
    private CdtKyThuatHoTroQueryService cdtKyThuatHoTroQueryService;

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

    private MockMvc restCdtKyThuatHoTroMockMvc;

    private CdtKyThuatHoTro cdtKyThuatHoTro;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtKyThuatHoTroResource cdtKyThuatHoTroResource = new CdtKyThuatHoTroResource(cdtKyThuatHoTroService, cdtKyThuatHoTroQueryService);
        this.restCdtKyThuatHoTroMockMvc = MockMvcBuilders.standaloneSetup(cdtKyThuatHoTroResource)
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
    public static CdtKyThuatHoTro createEntity(EntityManager em) {
        CdtKyThuatHoTro cdtKyThuatHoTro = new CdtKyThuatHoTro()
            .maNoiDen(DEFAULT_MA_NOI_DEN)
            .tenNoiDen(DEFAULT_TEN_NOI_DEN)
            .thuTuSapXep(DEFAULT_THU_TU_SAP_XEP)
            .csytid(DEFAULT_CSYTID);
        return cdtKyThuatHoTro;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtKyThuatHoTro createUpdatedEntity(EntityManager em) {
        CdtKyThuatHoTro cdtKyThuatHoTro = new CdtKyThuatHoTro()
            .maNoiDen(UPDATED_MA_NOI_DEN)
            .tenNoiDen(UPDATED_TEN_NOI_DEN)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);
        return cdtKyThuatHoTro;
    }

    @BeforeEach
    public void initTest() {
        cdtKyThuatHoTro = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtKyThuatHoTro() throws Exception {
        int databaseSizeBeforeCreate = cdtKyThuatHoTroRepository.findAll().size();

        // Create the CdtKyThuatHoTro
        restCdtKyThuatHoTroMockMvc.perform(post("/api/cdt-ky-thuat-ho-tros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtKyThuatHoTro)))
            .andExpect(status().isCreated());

        // Validate the CdtKyThuatHoTro in the database
        List<CdtKyThuatHoTro> cdtKyThuatHoTroList = cdtKyThuatHoTroRepository.findAll();
        assertThat(cdtKyThuatHoTroList).hasSize(databaseSizeBeforeCreate + 1);
        CdtKyThuatHoTro testCdtKyThuatHoTro = cdtKyThuatHoTroList.get(cdtKyThuatHoTroList.size() - 1);
        assertThat(testCdtKyThuatHoTro.getMaNoiDen()).isEqualTo(DEFAULT_MA_NOI_DEN);
        assertThat(testCdtKyThuatHoTro.getTenNoiDen()).isEqualTo(DEFAULT_TEN_NOI_DEN);
        assertThat(testCdtKyThuatHoTro.getThuTuSapXep()).isEqualTo(DEFAULT_THU_TU_SAP_XEP);
        assertThat(testCdtKyThuatHoTro.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtKyThuatHoTroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtKyThuatHoTroRepository.findAll().size();

        // Create the CdtKyThuatHoTro with an existing ID
        cdtKyThuatHoTro.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtKyThuatHoTroMockMvc.perform(post("/api/cdt-ky-thuat-ho-tros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtKyThuatHoTro)))
            .andExpect(status().isBadRequest());

        // Validate the CdtKyThuatHoTro in the database
        List<CdtKyThuatHoTro> cdtKyThuatHoTroList = cdtKyThuatHoTroRepository.findAll();
        assertThat(cdtKyThuatHoTroList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtKyThuatHoTros() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList
        restCdtKyThuatHoTroMockMvc.perform(get("/api/cdt-ky-thuat-ho-tros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtKyThuatHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNoiDen").value(hasItem(DEFAULT_MA_NOI_DEN)))
            .andExpect(jsonPath("$.[*].tenNoiDen").value(hasItem(DEFAULT_TEN_NOI_DEN)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtKyThuatHoTro() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get the cdtKyThuatHoTro
        restCdtKyThuatHoTroMockMvc.perform(get("/api/cdt-ky-thuat-ho-tros/{id}", cdtKyThuatHoTro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtKyThuatHoTro.getId().intValue()))
            .andExpect(jsonPath("$.maNoiDen").value(DEFAULT_MA_NOI_DEN))
            .andExpect(jsonPath("$.tenNoiDen").value(DEFAULT_TEN_NOI_DEN))
            .andExpect(jsonPath("$.thuTuSapXep").value(DEFAULT_THU_TU_SAP_XEP.intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtKyThuatHoTrosByIdFiltering() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        Long id = cdtKyThuatHoTro.getId();

        defaultCdtKyThuatHoTroShouldBeFound("id.equals=" + id);
        defaultCdtKyThuatHoTroShouldNotBeFound("id.notEquals=" + id);

        defaultCdtKyThuatHoTroShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtKyThuatHoTroShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtKyThuatHoTroShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtKyThuatHoTroShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByMaNoiDenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where maNoiDen equals to DEFAULT_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("maNoiDen.equals=" + DEFAULT_MA_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where maNoiDen equals to UPDATED_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("maNoiDen.equals=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByMaNoiDenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where maNoiDen not equals to DEFAULT_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("maNoiDen.notEquals=" + DEFAULT_MA_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where maNoiDen not equals to UPDATED_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("maNoiDen.notEquals=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByMaNoiDenIsInShouldWork() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where maNoiDen in DEFAULT_MA_NOI_DEN or UPDATED_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("maNoiDen.in=" + DEFAULT_MA_NOI_DEN + "," + UPDATED_MA_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where maNoiDen equals to UPDATED_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("maNoiDen.in=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByMaNoiDenIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where maNoiDen is not null
        defaultCdtKyThuatHoTroShouldBeFound("maNoiDen.specified=true");

        // Get all the cdtKyThuatHoTroList where maNoiDen is null
        defaultCdtKyThuatHoTroShouldNotBeFound("maNoiDen.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByMaNoiDenContainsSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where maNoiDen contains DEFAULT_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("maNoiDen.contains=" + DEFAULT_MA_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where maNoiDen contains UPDATED_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("maNoiDen.contains=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByMaNoiDenNotContainsSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where maNoiDen does not contain DEFAULT_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("maNoiDen.doesNotContain=" + DEFAULT_MA_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where maNoiDen does not contain UPDATED_MA_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("maNoiDen.doesNotContain=" + UPDATED_MA_NOI_DEN);
    }


    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByTenNoiDenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where tenNoiDen equals to DEFAULT_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("tenNoiDen.equals=" + DEFAULT_TEN_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where tenNoiDen equals to UPDATED_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("tenNoiDen.equals=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByTenNoiDenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where tenNoiDen not equals to DEFAULT_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("tenNoiDen.notEquals=" + DEFAULT_TEN_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where tenNoiDen not equals to UPDATED_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("tenNoiDen.notEquals=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByTenNoiDenIsInShouldWork() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where tenNoiDen in DEFAULT_TEN_NOI_DEN or UPDATED_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("tenNoiDen.in=" + DEFAULT_TEN_NOI_DEN + "," + UPDATED_TEN_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where tenNoiDen equals to UPDATED_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("tenNoiDen.in=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByTenNoiDenIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where tenNoiDen is not null
        defaultCdtKyThuatHoTroShouldBeFound("tenNoiDen.specified=true");

        // Get all the cdtKyThuatHoTroList where tenNoiDen is null
        defaultCdtKyThuatHoTroShouldNotBeFound("tenNoiDen.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByTenNoiDenContainsSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where tenNoiDen contains DEFAULT_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("tenNoiDen.contains=" + DEFAULT_TEN_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where tenNoiDen contains UPDATED_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("tenNoiDen.contains=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByTenNoiDenNotContainsSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where tenNoiDen does not contain DEFAULT_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldNotBeFound("tenNoiDen.doesNotContain=" + DEFAULT_TEN_NOI_DEN);

        // Get all the cdtKyThuatHoTroList where tenNoiDen does not contain UPDATED_TEN_NOI_DEN
        defaultCdtKyThuatHoTroShouldBeFound("tenNoiDen.doesNotContain=" + UPDATED_TEN_NOI_DEN);
    }


    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByThuTuSapXepIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldBeFound("thuTuSapXep.equals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldNotBeFound("thuTuSapXep.equals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByThuTuSapXepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep not equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldNotBeFound("thuTuSapXep.notEquals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep not equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldBeFound("thuTuSapXep.notEquals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByThuTuSapXepIsInShouldWork() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep in DEFAULT_THU_TU_SAP_XEP or UPDATED_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldBeFound("thuTuSapXep.in=" + DEFAULT_THU_TU_SAP_XEP + "," + UPDATED_THU_TU_SAP_XEP);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldNotBeFound("thuTuSapXep.in=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByThuTuSapXepIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is not null
        defaultCdtKyThuatHoTroShouldBeFound("thuTuSapXep.specified=true");

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is null
        defaultCdtKyThuatHoTroShouldNotBeFound("thuTuSapXep.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByThuTuSapXepIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is greater than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldBeFound("thuTuSapXep.greaterThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is greater than or equal to UPDATED_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldNotBeFound("thuTuSapXep.greaterThanOrEqual=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByThuTuSapXepIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is less than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldBeFound("thuTuSapXep.lessThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is less than or equal to SMALLER_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldNotBeFound("thuTuSapXep.lessThanOrEqual=" + SMALLER_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByThuTuSapXepIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is less than DEFAULT_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldNotBeFound("thuTuSapXep.lessThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is less than UPDATED_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldBeFound("thuTuSapXep.lessThan=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByThuTuSapXepIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is greater than DEFAULT_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldNotBeFound("thuTuSapXep.greaterThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKyThuatHoTroList where thuTuSapXep is greater than SMALLER_THU_TU_SAP_XEP
        defaultCdtKyThuatHoTroShouldBeFound("thuTuSapXep.greaterThan=" + SMALLER_THU_TU_SAP_XEP);
    }


    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where csytid equals to DEFAULT_CSYTID
        defaultCdtKyThuatHoTroShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatHoTroList where csytid equals to UPDATED_CSYTID
        defaultCdtKyThuatHoTroShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where csytid not equals to DEFAULT_CSYTID
        defaultCdtKyThuatHoTroShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatHoTroList where csytid not equals to UPDATED_CSYTID
        defaultCdtKyThuatHoTroShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtKyThuatHoTroShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtKyThuatHoTroList where csytid equals to UPDATED_CSYTID
        defaultCdtKyThuatHoTroShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where csytid is not null
        defaultCdtKyThuatHoTroShouldBeFound("csytid.specified=true");

        // Get all the cdtKyThuatHoTroList where csytid is null
        defaultCdtKyThuatHoTroShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtKyThuatHoTroShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatHoTroList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtKyThuatHoTroShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtKyThuatHoTroShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatHoTroList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtKyThuatHoTroShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where csytid is less than DEFAULT_CSYTID
        defaultCdtKyThuatHoTroShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatHoTroList where csytid is less than UPDATED_CSYTID
        defaultCdtKyThuatHoTroShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);

        // Get all the cdtKyThuatHoTroList where csytid is greater than DEFAULT_CSYTID
        defaultCdtKyThuatHoTroShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtKyThuatHoTroList where csytid is greater than SMALLER_CSYTID
        defaultCdtKyThuatHoTroShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtKyThuatHoTrosByKythuatIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);
        CdtKyThuat kythuat = CdtKyThuatResourceIT.createEntity(em);
        em.persist(kythuat);
        em.flush();
        cdtKyThuatHoTro.addKythuat(kythuat);
        cdtKyThuatHoTroRepository.saveAndFlush(cdtKyThuatHoTro);
        Long kythuatId = kythuat.getId();

        // Get all the cdtKyThuatHoTroList where kythuat equals to kythuatId
        defaultCdtKyThuatHoTroShouldBeFound("kythuatId.equals=" + kythuatId);

        // Get all the cdtKyThuatHoTroList where kythuat equals to kythuatId + 1
        defaultCdtKyThuatHoTroShouldNotBeFound("kythuatId.equals=" + (kythuatId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtKyThuatHoTroShouldBeFound(String filter) throws Exception {
        restCdtKyThuatHoTroMockMvc.perform(get("/api/cdt-ky-thuat-ho-tros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtKyThuatHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNoiDen").value(hasItem(DEFAULT_MA_NOI_DEN)))
            .andExpect(jsonPath("$.[*].tenNoiDen").value(hasItem(DEFAULT_TEN_NOI_DEN)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtKyThuatHoTroMockMvc.perform(get("/api/cdt-ky-thuat-ho-tros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtKyThuatHoTroShouldNotBeFound(String filter) throws Exception {
        restCdtKyThuatHoTroMockMvc.perform(get("/api/cdt-ky-thuat-ho-tros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtKyThuatHoTroMockMvc.perform(get("/api/cdt-ky-thuat-ho-tros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtKyThuatHoTro() throws Exception {
        // Get the cdtKyThuatHoTro
        restCdtKyThuatHoTroMockMvc.perform(get("/api/cdt-ky-thuat-ho-tros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtKyThuatHoTro() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroService.save(cdtKyThuatHoTro);

        int databaseSizeBeforeUpdate = cdtKyThuatHoTroRepository.findAll().size();

        // Update the cdtKyThuatHoTro
        CdtKyThuatHoTro updatedCdtKyThuatHoTro = cdtKyThuatHoTroRepository.findById(cdtKyThuatHoTro.getId()).get();
        // Disconnect from session so that the updates on updatedCdtKyThuatHoTro are not directly saved in db
        em.detach(updatedCdtKyThuatHoTro);
        updatedCdtKyThuatHoTro
            .maNoiDen(UPDATED_MA_NOI_DEN)
            .tenNoiDen(UPDATED_TEN_NOI_DEN)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);

        restCdtKyThuatHoTroMockMvc.perform(put("/api/cdt-ky-thuat-ho-tros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtKyThuatHoTro)))
            .andExpect(status().isOk());

        // Validate the CdtKyThuatHoTro in the database
        List<CdtKyThuatHoTro> cdtKyThuatHoTroList = cdtKyThuatHoTroRepository.findAll();
        assertThat(cdtKyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
        CdtKyThuatHoTro testCdtKyThuatHoTro = cdtKyThuatHoTroList.get(cdtKyThuatHoTroList.size() - 1);
        assertThat(testCdtKyThuatHoTro.getMaNoiDen()).isEqualTo(UPDATED_MA_NOI_DEN);
        assertThat(testCdtKyThuatHoTro.getTenNoiDen()).isEqualTo(UPDATED_TEN_NOI_DEN);
        assertThat(testCdtKyThuatHoTro.getThuTuSapXep()).isEqualTo(UPDATED_THU_TU_SAP_XEP);
        assertThat(testCdtKyThuatHoTro.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = cdtKyThuatHoTroRepository.findAll().size();

        // Create the CdtKyThuatHoTro

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtKyThuatHoTroMockMvc.perform(put("/api/cdt-ky-thuat-ho-tros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtKyThuatHoTro)))
            .andExpect(status().isBadRequest());

        // Validate the CdtKyThuatHoTro in the database
        List<CdtKyThuatHoTro> cdtKyThuatHoTroList = cdtKyThuatHoTroRepository.findAll();
        assertThat(cdtKyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtKyThuatHoTro() throws Exception {
        // Initialize the database
        cdtKyThuatHoTroService.save(cdtKyThuatHoTro);

        int databaseSizeBeforeDelete = cdtKyThuatHoTroRepository.findAll().size();

        // Delete the cdtKyThuatHoTro
        restCdtKyThuatHoTroMockMvc.perform(delete("/api/cdt-ky-thuat-ho-tros/{id}", cdtKyThuatHoTro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtKyThuatHoTro> cdtKyThuatHoTroList = cdtKyThuatHoTroRepository.findAll();
        assertThat(cdtKyThuatHoTroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
