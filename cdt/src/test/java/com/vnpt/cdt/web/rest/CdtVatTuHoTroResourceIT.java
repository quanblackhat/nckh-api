package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtVatTuHoTro;
import com.vnpt.cdt.domain.CdtVatTu;
import com.vnpt.cdt.repository.CdtVatTuHoTroRepository;
import com.vnpt.cdt.service.CdtVatTuHoTroService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtVatTuHoTroCriteria;
import com.vnpt.cdt.service.CdtVatTuHoTroQueryService;

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
 * Integration tests for the {@link CdtVatTuHoTroResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtVatTuHoTroResourceIT {

    private static final String DEFAULT_MA_VAT_TU = "AAAAAAAAAA";
    private static final String UPDATED_MA_VAT_TU = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_VAT_TU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_VAT_TU = "BBBBBBBBBB";

    private static final Long DEFAULT_THU_TU_SAP_XEP = 1L;
    private static final Long UPDATED_THU_TU_SAP_XEP = 2L;
    private static final Long SMALLER_THU_TU_SAP_XEP = 1L - 1L;

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    @Autowired
    private CdtVatTuHoTroRepository cdtVatTuHoTroRepository;

    @Autowired
    private CdtVatTuHoTroService cdtVatTuHoTroService;

    @Autowired
    private CdtVatTuHoTroQueryService cdtVatTuHoTroQueryService;

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

    private MockMvc restCdtVatTuHoTroMockMvc;

    private CdtVatTuHoTro cdtVatTuHoTro;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtVatTuHoTroResource cdtVatTuHoTroResource = new CdtVatTuHoTroResource(cdtVatTuHoTroService, cdtVatTuHoTroQueryService);
        this.restCdtVatTuHoTroMockMvc = MockMvcBuilders.standaloneSetup(cdtVatTuHoTroResource)
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
    public static CdtVatTuHoTro createEntity(EntityManager em) {
        CdtVatTuHoTro cdtVatTuHoTro = new CdtVatTuHoTro()
            .maVatTu(DEFAULT_MA_VAT_TU)
            .tenVatTu(DEFAULT_TEN_VAT_TU)
            .thuTuSapXep(DEFAULT_THU_TU_SAP_XEP)
            .csytid(DEFAULT_CSYTID);
        return cdtVatTuHoTro;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtVatTuHoTro createUpdatedEntity(EntityManager em) {
        CdtVatTuHoTro cdtVatTuHoTro = new CdtVatTuHoTro()
            .maVatTu(UPDATED_MA_VAT_TU)
            .tenVatTu(UPDATED_TEN_VAT_TU)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);
        return cdtVatTuHoTro;
    }

    @BeforeEach
    public void initTest() {
        cdtVatTuHoTro = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtVatTuHoTro() throws Exception {
        int databaseSizeBeforeCreate = cdtVatTuHoTroRepository.findAll().size();

        // Create the CdtVatTuHoTro
        restCdtVatTuHoTroMockMvc.perform(post("/api/cdt-vat-tu-ho-tros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtVatTuHoTro)))
            .andExpect(status().isCreated());

        // Validate the CdtVatTuHoTro in the database
        List<CdtVatTuHoTro> cdtVatTuHoTroList = cdtVatTuHoTroRepository.findAll();
        assertThat(cdtVatTuHoTroList).hasSize(databaseSizeBeforeCreate + 1);
        CdtVatTuHoTro testCdtVatTuHoTro = cdtVatTuHoTroList.get(cdtVatTuHoTroList.size() - 1);
        assertThat(testCdtVatTuHoTro.getMaVatTu()).isEqualTo(DEFAULT_MA_VAT_TU);
        assertThat(testCdtVatTuHoTro.getTenVatTu()).isEqualTo(DEFAULT_TEN_VAT_TU);
        assertThat(testCdtVatTuHoTro.getThuTuSapXep()).isEqualTo(DEFAULT_THU_TU_SAP_XEP);
        assertThat(testCdtVatTuHoTro.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtVatTuHoTroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtVatTuHoTroRepository.findAll().size();

        // Create the CdtVatTuHoTro with an existing ID
        cdtVatTuHoTro.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtVatTuHoTroMockMvc.perform(post("/api/cdt-vat-tu-ho-tros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtVatTuHoTro)))
            .andExpect(status().isBadRequest());

        // Validate the CdtVatTuHoTro in the database
        List<CdtVatTuHoTro> cdtVatTuHoTroList = cdtVatTuHoTroRepository.findAll();
        assertThat(cdtVatTuHoTroList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtVatTuHoTros() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList
        restCdtVatTuHoTroMockMvc.perform(get("/api/cdt-vat-tu-ho-tros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtVatTuHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maVatTu").value(hasItem(DEFAULT_MA_VAT_TU)))
            .andExpect(jsonPath("$.[*].tenVatTu").value(hasItem(DEFAULT_TEN_VAT_TU)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtVatTuHoTro() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get the cdtVatTuHoTro
        restCdtVatTuHoTroMockMvc.perform(get("/api/cdt-vat-tu-ho-tros/{id}", cdtVatTuHoTro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtVatTuHoTro.getId().intValue()))
            .andExpect(jsonPath("$.maVatTu").value(DEFAULT_MA_VAT_TU))
            .andExpect(jsonPath("$.tenVatTu").value(DEFAULT_TEN_VAT_TU))
            .andExpect(jsonPath("$.thuTuSapXep").value(DEFAULT_THU_TU_SAP_XEP.intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtVatTuHoTrosByIdFiltering() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        Long id = cdtVatTuHoTro.getId();

        defaultCdtVatTuHoTroShouldBeFound("id.equals=" + id);
        defaultCdtVatTuHoTroShouldNotBeFound("id.notEquals=" + id);

        defaultCdtVatTuHoTroShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtVatTuHoTroShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtVatTuHoTroShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtVatTuHoTroShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByMaVatTuIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where maVatTu equals to DEFAULT_MA_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("maVatTu.equals=" + DEFAULT_MA_VAT_TU);

        // Get all the cdtVatTuHoTroList where maVatTu equals to UPDATED_MA_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("maVatTu.equals=" + UPDATED_MA_VAT_TU);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByMaVatTuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where maVatTu not equals to DEFAULT_MA_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("maVatTu.notEquals=" + DEFAULT_MA_VAT_TU);

        // Get all the cdtVatTuHoTroList where maVatTu not equals to UPDATED_MA_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("maVatTu.notEquals=" + UPDATED_MA_VAT_TU);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByMaVatTuIsInShouldWork() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where maVatTu in DEFAULT_MA_VAT_TU or UPDATED_MA_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("maVatTu.in=" + DEFAULT_MA_VAT_TU + "," + UPDATED_MA_VAT_TU);

        // Get all the cdtVatTuHoTroList where maVatTu equals to UPDATED_MA_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("maVatTu.in=" + UPDATED_MA_VAT_TU);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByMaVatTuIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where maVatTu is not null
        defaultCdtVatTuHoTroShouldBeFound("maVatTu.specified=true");

        // Get all the cdtVatTuHoTroList where maVatTu is null
        defaultCdtVatTuHoTroShouldNotBeFound("maVatTu.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByMaVatTuContainsSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where maVatTu contains DEFAULT_MA_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("maVatTu.contains=" + DEFAULT_MA_VAT_TU);

        // Get all the cdtVatTuHoTroList where maVatTu contains UPDATED_MA_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("maVatTu.contains=" + UPDATED_MA_VAT_TU);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByMaVatTuNotContainsSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where maVatTu does not contain DEFAULT_MA_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("maVatTu.doesNotContain=" + DEFAULT_MA_VAT_TU);

        // Get all the cdtVatTuHoTroList where maVatTu does not contain UPDATED_MA_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("maVatTu.doesNotContain=" + UPDATED_MA_VAT_TU);
    }


    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByTenVatTuIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where tenVatTu equals to DEFAULT_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("tenVatTu.equals=" + DEFAULT_TEN_VAT_TU);

        // Get all the cdtVatTuHoTroList where tenVatTu equals to UPDATED_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("tenVatTu.equals=" + UPDATED_TEN_VAT_TU);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByTenVatTuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where tenVatTu not equals to DEFAULT_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("tenVatTu.notEquals=" + DEFAULT_TEN_VAT_TU);

        // Get all the cdtVatTuHoTroList where tenVatTu not equals to UPDATED_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("tenVatTu.notEquals=" + UPDATED_TEN_VAT_TU);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByTenVatTuIsInShouldWork() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where tenVatTu in DEFAULT_TEN_VAT_TU or UPDATED_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("tenVatTu.in=" + DEFAULT_TEN_VAT_TU + "," + UPDATED_TEN_VAT_TU);

        // Get all the cdtVatTuHoTroList where tenVatTu equals to UPDATED_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("tenVatTu.in=" + UPDATED_TEN_VAT_TU);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByTenVatTuIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where tenVatTu is not null
        defaultCdtVatTuHoTroShouldBeFound("tenVatTu.specified=true");

        // Get all the cdtVatTuHoTroList where tenVatTu is null
        defaultCdtVatTuHoTroShouldNotBeFound("tenVatTu.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByTenVatTuContainsSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where tenVatTu contains DEFAULT_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("tenVatTu.contains=" + DEFAULT_TEN_VAT_TU);

        // Get all the cdtVatTuHoTroList where tenVatTu contains UPDATED_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("tenVatTu.contains=" + UPDATED_TEN_VAT_TU);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByTenVatTuNotContainsSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where tenVatTu does not contain DEFAULT_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldNotBeFound("tenVatTu.doesNotContain=" + DEFAULT_TEN_VAT_TU);

        // Get all the cdtVatTuHoTroList where tenVatTu does not contain UPDATED_TEN_VAT_TU
        defaultCdtVatTuHoTroShouldBeFound("tenVatTu.doesNotContain=" + UPDATED_TEN_VAT_TU);
    }


    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByThuTuSapXepIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where thuTuSapXep equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldBeFound("thuTuSapXep.equals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtVatTuHoTroList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldNotBeFound("thuTuSapXep.equals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByThuTuSapXepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where thuTuSapXep not equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldNotBeFound("thuTuSapXep.notEquals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtVatTuHoTroList where thuTuSapXep not equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldBeFound("thuTuSapXep.notEquals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByThuTuSapXepIsInShouldWork() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where thuTuSapXep in DEFAULT_THU_TU_SAP_XEP or UPDATED_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldBeFound("thuTuSapXep.in=" + DEFAULT_THU_TU_SAP_XEP + "," + UPDATED_THU_TU_SAP_XEP);

        // Get all the cdtVatTuHoTroList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldNotBeFound("thuTuSapXep.in=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByThuTuSapXepIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where thuTuSapXep is not null
        defaultCdtVatTuHoTroShouldBeFound("thuTuSapXep.specified=true");

        // Get all the cdtVatTuHoTroList where thuTuSapXep is null
        defaultCdtVatTuHoTroShouldNotBeFound("thuTuSapXep.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByThuTuSapXepIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where thuTuSapXep is greater than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldBeFound("thuTuSapXep.greaterThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtVatTuHoTroList where thuTuSapXep is greater than or equal to UPDATED_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldNotBeFound("thuTuSapXep.greaterThanOrEqual=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByThuTuSapXepIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where thuTuSapXep is less than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldBeFound("thuTuSapXep.lessThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtVatTuHoTroList where thuTuSapXep is less than or equal to SMALLER_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldNotBeFound("thuTuSapXep.lessThanOrEqual=" + SMALLER_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByThuTuSapXepIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where thuTuSapXep is less than DEFAULT_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldNotBeFound("thuTuSapXep.lessThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtVatTuHoTroList where thuTuSapXep is less than UPDATED_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldBeFound("thuTuSapXep.lessThan=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByThuTuSapXepIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where thuTuSapXep is greater than DEFAULT_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldNotBeFound("thuTuSapXep.greaterThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtVatTuHoTroList where thuTuSapXep is greater than SMALLER_THU_TU_SAP_XEP
        defaultCdtVatTuHoTroShouldBeFound("thuTuSapXep.greaterThan=" + SMALLER_THU_TU_SAP_XEP);
    }


    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where csytid equals to DEFAULT_CSYTID
        defaultCdtVatTuHoTroShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuHoTroList where csytid equals to UPDATED_CSYTID
        defaultCdtVatTuHoTroShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where csytid not equals to DEFAULT_CSYTID
        defaultCdtVatTuHoTroShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuHoTroList where csytid not equals to UPDATED_CSYTID
        defaultCdtVatTuHoTroShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtVatTuHoTroShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtVatTuHoTroList where csytid equals to UPDATED_CSYTID
        defaultCdtVatTuHoTroShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where csytid is not null
        defaultCdtVatTuHoTroShouldBeFound("csytid.specified=true");

        // Get all the cdtVatTuHoTroList where csytid is null
        defaultCdtVatTuHoTroShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtVatTuHoTroShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuHoTroList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtVatTuHoTroShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtVatTuHoTroShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuHoTroList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtVatTuHoTroShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where csytid is less than DEFAULT_CSYTID
        defaultCdtVatTuHoTroShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuHoTroList where csytid is less than UPDATED_CSYTID
        defaultCdtVatTuHoTroShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);

        // Get all the cdtVatTuHoTroList where csytid is greater than DEFAULT_CSYTID
        defaultCdtVatTuHoTroShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtVatTuHoTroList where csytid is greater than SMALLER_CSYTID
        defaultCdtVatTuHoTroShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtVatTuHoTrosByVattuIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);
        CdtVatTu vattu = CdtVatTuResourceIT.createEntity(em);
        em.persist(vattu);
        em.flush();
        cdtVatTuHoTro.addVattu(vattu);
        cdtVatTuHoTroRepository.saveAndFlush(cdtVatTuHoTro);
        Long vattuId = vattu.getId();

        // Get all the cdtVatTuHoTroList where vattu equals to vattuId
        defaultCdtVatTuHoTroShouldBeFound("vattuId.equals=" + vattuId);

        // Get all the cdtVatTuHoTroList where vattu equals to vattuId + 1
        defaultCdtVatTuHoTroShouldNotBeFound("vattuId.equals=" + (vattuId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtVatTuHoTroShouldBeFound(String filter) throws Exception {
        restCdtVatTuHoTroMockMvc.perform(get("/api/cdt-vat-tu-ho-tros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtVatTuHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maVatTu").value(hasItem(DEFAULT_MA_VAT_TU)))
            .andExpect(jsonPath("$.[*].tenVatTu").value(hasItem(DEFAULT_TEN_VAT_TU)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtVatTuHoTroMockMvc.perform(get("/api/cdt-vat-tu-ho-tros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtVatTuHoTroShouldNotBeFound(String filter) throws Exception {
        restCdtVatTuHoTroMockMvc.perform(get("/api/cdt-vat-tu-ho-tros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtVatTuHoTroMockMvc.perform(get("/api/cdt-vat-tu-ho-tros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtVatTuHoTro() throws Exception {
        // Get the cdtVatTuHoTro
        restCdtVatTuHoTroMockMvc.perform(get("/api/cdt-vat-tu-ho-tros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtVatTuHoTro() throws Exception {
        // Initialize the database
        cdtVatTuHoTroService.save(cdtVatTuHoTro);

        int databaseSizeBeforeUpdate = cdtVatTuHoTroRepository.findAll().size();

        // Update the cdtVatTuHoTro
        CdtVatTuHoTro updatedCdtVatTuHoTro = cdtVatTuHoTroRepository.findById(cdtVatTuHoTro.getId()).get();
        // Disconnect from session so that the updates on updatedCdtVatTuHoTro are not directly saved in db
        em.detach(updatedCdtVatTuHoTro);
        updatedCdtVatTuHoTro
            .maVatTu(UPDATED_MA_VAT_TU)
            .tenVatTu(UPDATED_TEN_VAT_TU)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);

        restCdtVatTuHoTroMockMvc.perform(put("/api/cdt-vat-tu-ho-tros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtVatTuHoTro)))
            .andExpect(status().isOk());

        // Validate the CdtVatTuHoTro in the database
        List<CdtVatTuHoTro> cdtVatTuHoTroList = cdtVatTuHoTroRepository.findAll();
        assertThat(cdtVatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
        CdtVatTuHoTro testCdtVatTuHoTro = cdtVatTuHoTroList.get(cdtVatTuHoTroList.size() - 1);
        assertThat(testCdtVatTuHoTro.getMaVatTu()).isEqualTo(UPDATED_MA_VAT_TU);
        assertThat(testCdtVatTuHoTro.getTenVatTu()).isEqualTo(UPDATED_TEN_VAT_TU);
        assertThat(testCdtVatTuHoTro.getThuTuSapXep()).isEqualTo(UPDATED_THU_TU_SAP_XEP);
        assertThat(testCdtVatTuHoTro.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = cdtVatTuHoTroRepository.findAll().size();

        // Create the CdtVatTuHoTro

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtVatTuHoTroMockMvc.perform(put("/api/cdt-vat-tu-ho-tros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtVatTuHoTro)))
            .andExpect(status().isBadRequest());

        // Validate the CdtVatTuHoTro in the database
        List<CdtVatTuHoTro> cdtVatTuHoTroList = cdtVatTuHoTroRepository.findAll();
        assertThat(cdtVatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtVatTuHoTro() throws Exception {
        // Initialize the database
        cdtVatTuHoTroService.save(cdtVatTuHoTro);

        int databaseSizeBeforeDelete = cdtVatTuHoTroRepository.findAll().size();

        // Delete the cdtVatTuHoTro
        restCdtVatTuHoTroMockMvc.perform(delete("/api/cdt-vat-tu-ho-tros/{id}", cdtVatTuHoTro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtVatTuHoTro> cdtVatTuHoTroList = cdtVatTuHoTroRepository.findAll();
        assertThat(cdtVatTuHoTroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
