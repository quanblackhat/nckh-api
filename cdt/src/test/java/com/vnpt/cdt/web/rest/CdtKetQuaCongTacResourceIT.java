package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtKetQuaCongTac;
import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.repository.CdtKetQuaCongTacRepository;
import com.vnpt.cdt.service.CdtKetQuaCongTacService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtKetQuaCongTacCriteria;
import com.vnpt.cdt.service.CdtKetQuaCongTacQueryService;

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
 * Integration tests for the {@link CdtKetQuaCongTacResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtKetQuaCongTacResourceIT {

    private static final String DEFAULT_MA_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_MA_KET_QUA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KET_QUA = "BBBBBBBBBB";

    private static final Long DEFAULT_THU_TU_SAP_XEP = 1L;
    private static final Long UPDATED_THU_TU_SAP_XEP = 2L;
    private static final Long SMALLER_THU_TU_SAP_XEP = 1L - 1L;

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    @Autowired
    private CdtKetQuaCongTacRepository cdtKetQuaCongTacRepository;

    @Autowired
    private CdtKetQuaCongTacService cdtKetQuaCongTacService;

    @Autowired
    private CdtKetQuaCongTacQueryService cdtKetQuaCongTacQueryService;

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

    private MockMvc restCdtKetQuaCongTacMockMvc;

    private CdtKetQuaCongTac cdtKetQuaCongTac;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtKetQuaCongTacResource cdtKetQuaCongTacResource = new CdtKetQuaCongTacResource(cdtKetQuaCongTacService, cdtKetQuaCongTacQueryService);
        this.restCdtKetQuaCongTacMockMvc = MockMvcBuilders.standaloneSetup(cdtKetQuaCongTacResource)
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
    public static CdtKetQuaCongTac createEntity(EntityManager em) {
        CdtKetQuaCongTac cdtKetQuaCongTac = new CdtKetQuaCongTac()
            .maKetQua(DEFAULT_MA_KET_QUA)
            .tenKetQua(DEFAULT_TEN_KET_QUA)
            .thuTuSapXep(DEFAULT_THU_TU_SAP_XEP)
            .csytid(DEFAULT_CSYTID);
        return cdtKetQuaCongTac;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtKetQuaCongTac createUpdatedEntity(EntityManager em) {
        CdtKetQuaCongTac cdtKetQuaCongTac = new CdtKetQuaCongTac()
            .maKetQua(UPDATED_MA_KET_QUA)
            .tenKetQua(UPDATED_TEN_KET_QUA)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);
        return cdtKetQuaCongTac;
    }

    @BeforeEach
    public void initTest() {
        cdtKetQuaCongTac = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtKetQuaCongTac() throws Exception {
        int databaseSizeBeforeCreate = cdtKetQuaCongTacRepository.findAll().size();

        // Create the CdtKetQuaCongTac
        restCdtKetQuaCongTacMockMvc.perform(post("/api/cdt-ket-qua-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtKetQuaCongTac)))
            .andExpect(status().isCreated());

        // Validate the CdtKetQuaCongTac in the database
        List<CdtKetQuaCongTac> cdtKetQuaCongTacList = cdtKetQuaCongTacRepository.findAll();
        assertThat(cdtKetQuaCongTacList).hasSize(databaseSizeBeforeCreate + 1);
        CdtKetQuaCongTac testCdtKetQuaCongTac = cdtKetQuaCongTacList.get(cdtKetQuaCongTacList.size() - 1);
        assertThat(testCdtKetQuaCongTac.getMaKetQua()).isEqualTo(DEFAULT_MA_KET_QUA);
        assertThat(testCdtKetQuaCongTac.getTenKetQua()).isEqualTo(DEFAULT_TEN_KET_QUA);
        assertThat(testCdtKetQuaCongTac.getThuTuSapXep()).isEqualTo(DEFAULT_THU_TU_SAP_XEP);
        assertThat(testCdtKetQuaCongTac.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtKetQuaCongTacWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtKetQuaCongTacRepository.findAll().size();

        // Create the CdtKetQuaCongTac with an existing ID
        cdtKetQuaCongTac.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtKetQuaCongTacMockMvc.perform(post("/api/cdt-ket-qua-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtKetQuaCongTac)))
            .andExpect(status().isBadRequest());

        // Validate the CdtKetQuaCongTac in the database
        List<CdtKetQuaCongTac> cdtKetQuaCongTacList = cdtKetQuaCongTacRepository.findAll();
        assertThat(cdtKetQuaCongTacList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacs() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList
        restCdtKetQuaCongTacMockMvc.perform(get("/api/cdt-ket-qua-cong-tacs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtKetQuaCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKetQua").value(hasItem(DEFAULT_MA_KET_QUA)))
            .andExpect(jsonPath("$.[*].tenKetQua").value(hasItem(DEFAULT_TEN_KET_QUA)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtKetQuaCongTac() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get the cdtKetQuaCongTac
        restCdtKetQuaCongTacMockMvc.perform(get("/api/cdt-ket-qua-cong-tacs/{id}", cdtKetQuaCongTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtKetQuaCongTac.getId().intValue()))
            .andExpect(jsonPath("$.maKetQua").value(DEFAULT_MA_KET_QUA))
            .andExpect(jsonPath("$.tenKetQua").value(DEFAULT_TEN_KET_QUA))
            .andExpect(jsonPath("$.thuTuSapXep").value(DEFAULT_THU_TU_SAP_XEP.intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtKetQuaCongTacsByIdFiltering() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        Long id = cdtKetQuaCongTac.getId();

        defaultCdtKetQuaCongTacShouldBeFound("id.equals=" + id);
        defaultCdtKetQuaCongTacShouldNotBeFound("id.notEquals=" + id);

        defaultCdtKetQuaCongTacShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtKetQuaCongTacShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtKetQuaCongTacShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtKetQuaCongTacShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByMaKetQuaIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where maKetQua equals to DEFAULT_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("maKetQua.equals=" + DEFAULT_MA_KET_QUA);

        // Get all the cdtKetQuaCongTacList where maKetQua equals to UPDATED_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("maKetQua.equals=" + UPDATED_MA_KET_QUA);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByMaKetQuaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where maKetQua not equals to DEFAULT_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("maKetQua.notEquals=" + DEFAULT_MA_KET_QUA);

        // Get all the cdtKetQuaCongTacList where maKetQua not equals to UPDATED_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("maKetQua.notEquals=" + UPDATED_MA_KET_QUA);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByMaKetQuaIsInShouldWork() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where maKetQua in DEFAULT_MA_KET_QUA or UPDATED_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("maKetQua.in=" + DEFAULT_MA_KET_QUA + "," + UPDATED_MA_KET_QUA);

        // Get all the cdtKetQuaCongTacList where maKetQua equals to UPDATED_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("maKetQua.in=" + UPDATED_MA_KET_QUA);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByMaKetQuaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where maKetQua is not null
        defaultCdtKetQuaCongTacShouldBeFound("maKetQua.specified=true");

        // Get all the cdtKetQuaCongTacList where maKetQua is null
        defaultCdtKetQuaCongTacShouldNotBeFound("maKetQua.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByMaKetQuaContainsSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where maKetQua contains DEFAULT_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("maKetQua.contains=" + DEFAULT_MA_KET_QUA);

        // Get all the cdtKetQuaCongTacList where maKetQua contains UPDATED_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("maKetQua.contains=" + UPDATED_MA_KET_QUA);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByMaKetQuaNotContainsSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where maKetQua does not contain DEFAULT_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("maKetQua.doesNotContain=" + DEFAULT_MA_KET_QUA);

        // Get all the cdtKetQuaCongTacList where maKetQua does not contain UPDATED_MA_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("maKetQua.doesNotContain=" + UPDATED_MA_KET_QUA);
    }


    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByTenKetQuaIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where tenKetQua equals to DEFAULT_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("tenKetQua.equals=" + DEFAULT_TEN_KET_QUA);

        // Get all the cdtKetQuaCongTacList where tenKetQua equals to UPDATED_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("tenKetQua.equals=" + UPDATED_TEN_KET_QUA);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByTenKetQuaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where tenKetQua not equals to DEFAULT_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("tenKetQua.notEquals=" + DEFAULT_TEN_KET_QUA);

        // Get all the cdtKetQuaCongTacList where tenKetQua not equals to UPDATED_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("tenKetQua.notEquals=" + UPDATED_TEN_KET_QUA);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByTenKetQuaIsInShouldWork() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where tenKetQua in DEFAULT_TEN_KET_QUA or UPDATED_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("tenKetQua.in=" + DEFAULT_TEN_KET_QUA + "," + UPDATED_TEN_KET_QUA);

        // Get all the cdtKetQuaCongTacList where tenKetQua equals to UPDATED_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("tenKetQua.in=" + UPDATED_TEN_KET_QUA);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByTenKetQuaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where tenKetQua is not null
        defaultCdtKetQuaCongTacShouldBeFound("tenKetQua.specified=true");

        // Get all the cdtKetQuaCongTacList where tenKetQua is null
        defaultCdtKetQuaCongTacShouldNotBeFound("tenKetQua.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByTenKetQuaContainsSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where tenKetQua contains DEFAULT_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("tenKetQua.contains=" + DEFAULT_TEN_KET_QUA);

        // Get all the cdtKetQuaCongTacList where tenKetQua contains UPDATED_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("tenKetQua.contains=" + UPDATED_TEN_KET_QUA);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByTenKetQuaNotContainsSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where tenKetQua does not contain DEFAULT_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldNotBeFound("tenKetQua.doesNotContain=" + DEFAULT_TEN_KET_QUA);

        // Get all the cdtKetQuaCongTacList where tenKetQua does not contain UPDATED_TEN_KET_QUA
        defaultCdtKetQuaCongTacShouldBeFound("tenKetQua.doesNotContain=" + UPDATED_TEN_KET_QUA);
    }


    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByThuTuSapXepIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldBeFound("thuTuSapXep.equals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldNotBeFound("thuTuSapXep.equals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByThuTuSapXepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep not equals to DEFAULT_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldNotBeFound("thuTuSapXep.notEquals=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep not equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldBeFound("thuTuSapXep.notEquals=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByThuTuSapXepIsInShouldWork() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep in DEFAULT_THU_TU_SAP_XEP or UPDATED_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldBeFound("thuTuSapXep.in=" + DEFAULT_THU_TU_SAP_XEP + "," + UPDATED_THU_TU_SAP_XEP);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep equals to UPDATED_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldNotBeFound("thuTuSapXep.in=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByThuTuSapXepIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is not null
        defaultCdtKetQuaCongTacShouldBeFound("thuTuSapXep.specified=true");

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is null
        defaultCdtKetQuaCongTacShouldNotBeFound("thuTuSapXep.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByThuTuSapXepIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is greater than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldBeFound("thuTuSapXep.greaterThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is greater than or equal to UPDATED_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldNotBeFound("thuTuSapXep.greaterThanOrEqual=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByThuTuSapXepIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is less than or equal to DEFAULT_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldBeFound("thuTuSapXep.lessThanOrEqual=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is less than or equal to SMALLER_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldNotBeFound("thuTuSapXep.lessThanOrEqual=" + SMALLER_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByThuTuSapXepIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is less than DEFAULT_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldNotBeFound("thuTuSapXep.lessThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is less than UPDATED_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldBeFound("thuTuSapXep.lessThan=" + UPDATED_THU_TU_SAP_XEP);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByThuTuSapXepIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is greater than DEFAULT_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldNotBeFound("thuTuSapXep.greaterThan=" + DEFAULT_THU_TU_SAP_XEP);

        // Get all the cdtKetQuaCongTacList where thuTuSapXep is greater than SMALLER_THU_TU_SAP_XEP
        defaultCdtKetQuaCongTacShouldBeFound("thuTuSapXep.greaterThan=" + SMALLER_THU_TU_SAP_XEP);
    }


    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where csytid equals to DEFAULT_CSYTID
        defaultCdtKetQuaCongTacShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtKetQuaCongTacList where csytid equals to UPDATED_CSYTID
        defaultCdtKetQuaCongTacShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where csytid not equals to DEFAULT_CSYTID
        defaultCdtKetQuaCongTacShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtKetQuaCongTacList where csytid not equals to UPDATED_CSYTID
        defaultCdtKetQuaCongTacShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtKetQuaCongTacShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtKetQuaCongTacList where csytid equals to UPDATED_CSYTID
        defaultCdtKetQuaCongTacShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where csytid is not null
        defaultCdtKetQuaCongTacShouldBeFound("csytid.specified=true");

        // Get all the cdtKetQuaCongTacList where csytid is null
        defaultCdtKetQuaCongTacShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtKetQuaCongTacShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtKetQuaCongTacList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtKetQuaCongTacShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtKetQuaCongTacShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtKetQuaCongTacList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtKetQuaCongTacShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where csytid is less than DEFAULT_CSYTID
        defaultCdtKetQuaCongTacShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtKetQuaCongTacList where csytid is less than UPDATED_CSYTID
        defaultCdtKetQuaCongTacShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);

        // Get all the cdtKetQuaCongTacList where csytid is greater than DEFAULT_CSYTID
        defaultCdtKetQuaCongTacShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtKetQuaCongTacList where csytid is greater than SMALLER_CSYTID
        defaultCdtKetQuaCongTacShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtKetQuaCongTacsByCdtChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);
        CdtChiDaoTuyen cdtChiDaoTuyen = CdtChiDaoTuyenResourceIT.createEntity(em);
        em.persist(cdtChiDaoTuyen);
        em.flush();
        cdtKetQuaCongTac.setCdtChiDaoTuyen(cdtChiDaoTuyen);
        cdtKetQuaCongTacRepository.saveAndFlush(cdtKetQuaCongTac);
        Long cdtChiDaoTuyenId = cdtChiDaoTuyen.getId();

        // Get all the cdtKetQuaCongTacList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId
        defaultCdtKetQuaCongTacShouldBeFound("cdtChiDaoTuyenId.equals=" + cdtChiDaoTuyenId);

        // Get all the cdtKetQuaCongTacList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId + 1
        defaultCdtKetQuaCongTacShouldNotBeFound("cdtChiDaoTuyenId.equals=" + (cdtChiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtKetQuaCongTacShouldBeFound(String filter) throws Exception {
        restCdtKetQuaCongTacMockMvc.perform(get("/api/cdt-ket-qua-cong-tacs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtKetQuaCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKetQua").value(hasItem(DEFAULT_MA_KET_QUA)))
            .andExpect(jsonPath("$.[*].tenKetQua").value(hasItem(DEFAULT_TEN_KET_QUA)))
            .andExpect(jsonPath("$.[*].thuTuSapXep").value(hasItem(DEFAULT_THU_TU_SAP_XEP.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtKetQuaCongTacMockMvc.perform(get("/api/cdt-ket-qua-cong-tacs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtKetQuaCongTacShouldNotBeFound(String filter) throws Exception {
        restCdtKetQuaCongTacMockMvc.perform(get("/api/cdt-ket-qua-cong-tacs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtKetQuaCongTacMockMvc.perform(get("/api/cdt-ket-qua-cong-tacs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtKetQuaCongTac() throws Exception {
        // Get the cdtKetQuaCongTac
        restCdtKetQuaCongTacMockMvc.perform(get("/api/cdt-ket-qua-cong-tacs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtKetQuaCongTac() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacService.save(cdtKetQuaCongTac);

        int databaseSizeBeforeUpdate = cdtKetQuaCongTacRepository.findAll().size();

        // Update the cdtKetQuaCongTac
        CdtKetQuaCongTac updatedCdtKetQuaCongTac = cdtKetQuaCongTacRepository.findById(cdtKetQuaCongTac.getId()).get();
        // Disconnect from session so that the updates on updatedCdtKetQuaCongTac are not directly saved in db
        em.detach(updatedCdtKetQuaCongTac);
        updatedCdtKetQuaCongTac
            .maKetQua(UPDATED_MA_KET_QUA)
            .tenKetQua(UPDATED_TEN_KET_QUA)
            .thuTuSapXep(UPDATED_THU_TU_SAP_XEP)
            .csytid(UPDATED_CSYTID);

        restCdtKetQuaCongTacMockMvc.perform(put("/api/cdt-ket-qua-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtKetQuaCongTac)))
            .andExpect(status().isOk());

        // Validate the CdtKetQuaCongTac in the database
        List<CdtKetQuaCongTac> cdtKetQuaCongTacList = cdtKetQuaCongTacRepository.findAll();
        assertThat(cdtKetQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
        CdtKetQuaCongTac testCdtKetQuaCongTac = cdtKetQuaCongTacList.get(cdtKetQuaCongTacList.size() - 1);
        assertThat(testCdtKetQuaCongTac.getMaKetQua()).isEqualTo(UPDATED_MA_KET_QUA);
        assertThat(testCdtKetQuaCongTac.getTenKetQua()).isEqualTo(UPDATED_TEN_KET_QUA);
        assertThat(testCdtKetQuaCongTac.getThuTuSapXep()).isEqualTo(UPDATED_THU_TU_SAP_XEP);
        assertThat(testCdtKetQuaCongTac.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = cdtKetQuaCongTacRepository.findAll().size();

        // Create the CdtKetQuaCongTac

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtKetQuaCongTacMockMvc.perform(put("/api/cdt-ket-qua-cong-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtKetQuaCongTac)))
            .andExpect(status().isBadRequest());

        // Validate the CdtKetQuaCongTac in the database
        List<CdtKetQuaCongTac> cdtKetQuaCongTacList = cdtKetQuaCongTacRepository.findAll();
        assertThat(cdtKetQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtKetQuaCongTac() throws Exception {
        // Initialize the database
        cdtKetQuaCongTacService.save(cdtKetQuaCongTac);

        int databaseSizeBeforeDelete = cdtKetQuaCongTacRepository.findAll().size();

        // Delete the cdtKetQuaCongTac
        restCdtKetQuaCongTacMockMvc.perform(delete("/api/cdt-ket-qua-cong-tacs/{id}", cdtKetQuaCongTac.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtKetQuaCongTac> cdtKetQuaCongTacList = cdtKetQuaCongTacRepository.findAll();
        assertThat(cdtKetQuaCongTacList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
