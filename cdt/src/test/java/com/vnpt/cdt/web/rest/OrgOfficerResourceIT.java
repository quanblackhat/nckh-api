package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.OrgOfficer;
import com.vnpt.cdt.domain.CdtNhanVien;
import com.vnpt.cdt.repository.OrgOfficerRepository;
import com.vnpt.cdt.service.OrgOfficerService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.OrgOfficerCriteria;
import com.vnpt.cdt.service.OrgOfficerQueryService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.vnpt.cdt.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrgOfficerResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class OrgOfficerResourceIT {

    private static final Long DEFAULT_OFFICER_TYPE = 1L;
    private static final Long UPDATED_OFFICER_TYPE = 2L;
    private static final Long SMALLER_OFFICER_TYPE = 1L - 1L;

    private static final String DEFAULT_OFFICER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_OFFICER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFICER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_TRINH_DO = "AAAAAAAAAA";
    private static final String UPDATED_TRINH_DO = "BBBBBBBBBB";

    private static final String DEFAULT_CHUC_DANH = "AAAAAAAAAA";
    private static final String UPDATED_CHUC_DANH = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BAC_SI = "AAAAAAAAAA";
    private static final String UPDATED_MA_BAC_SI = "BBBBBBBBBB";

    private static final String DEFAULT_HOC_HAM = "AAAAAAAAAA";
    private static final String UPDATED_HOC_HAM = "BBBBBBBBBB";

    private static final String DEFAULT_HOC_VI = "AAAAAAAAAA";
    private static final String UPDATED_HOC_VI = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_OPT = "AAAAAAAAAA";
    private static final String UPDATED_OPT = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_SINH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_SINH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_GIOI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_GIOI_TINH = "BBBBBBBBBB";

    @Autowired
    private OrgOfficerRepository orgOfficerRepository;

    @Autowired
    private OrgOfficerService orgOfficerService;

    @Autowired
    private OrgOfficerQueryService orgOfficerQueryService;

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

    private MockMvc restOrgOfficerMockMvc;

    private OrgOfficer orgOfficer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrgOfficerResource orgOfficerResource = new OrgOfficerResource(orgOfficerService, orgOfficerQueryService);
        this.restOrgOfficerMockMvc = MockMvcBuilders.standaloneSetup(orgOfficerResource)
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
    public static OrgOfficer createEntity(EntityManager em) {
        OrgOfficer orgOfficer = new OrgOfficer()
            .officerType(DEFAULT_OFFICER_TYPE)
            .officerCode(DEFAULT_OFFICER_CODE)
            .officerName(DEFAULT_OFFICER_NAME)
            .email(DEFAULT_EMAIL)
            .note(DEFAULT_NOTE)
            .trinhDo(DEFAULT_TRINH_DO)
            .chucDanh(DEFAULT_CHUC_DANH)
            .maBacSi(DEFAULT_MA_BAC_SI)
            .hocHam(DEFAULT_HOC_HAM)
            .hocVi(DEFAULT_HOC_VI)
            .phone(DEFAULT_PHONE)
            .opt(DEFAULT_OPT)
            .diaChi(DEFAULT_DIA_CHI)
            .ngaySinh(DEFAULT_NGAY_SINH)
            .gioiTinh(DEFAULT_GIOI_TINH);
        return orgOfficer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrgOfficer createUpdatedEntity(EntityManager em) {
        OrgOfficer orgOfficer = new OrgOfficer()
            .officerType(UPDATED_OFFICER_TYPE)
            .officerCode(UPDATED_OFFICER_CODE)
            .officerName(UPDATED_OFFICER_NAME)
            .email(UPDATED_EMAIL)
            .note(UPDATED_NOTE)
            .trinhDo(UPDATED_TRINH_DO)
            .chucDanh(UPDATED_CHUC_DANH)
            .maBacSi(UPDATED_MA_BAC_SI)
            .hocHam(UPDATED_HOC_HAM)
            .hocVi(UPDATED_HOC_VI)
            .phone(UPDATED_PHONE)
            .opt(UPDATED_OPT)
            .diaChi(UPDATED_DIA_CHI)
            .ngaySinh(UPDATED_NGAY_SINH)
            .gioiTinh(UPDATED_GIOI_TINH);
        return orgOfficer;
    }

    @BeforeEach
    public void initTest() {
        orgOfficer = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrgOfficer() throws Exception {
        int databaseSizeBeforeCreate = orgOfficerRepository.findAll().size();

        // Create the OrgOfficer
        restOrgOfficerMockMvc.perform(post("/api/org-officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgOfficer)))
            .andExpect(status().isCreated());

        // Validate the OrgOfficer in the database
        List<OrgOfficer> orgOfficerList = orgOfficerRepository.findAll();
        assertThat(orgOfficerList).hasSize(databaseSizeBeforeCreate + 1);
        OrgOfficer testOrgOfficer = orgOfficerList.get(orgOfficerList.size() - 1);
        assertThat(testOrgOfficer.getOfficerType()).isEqualTo(DEFAULT_OFFICER_TYPE);
        assertThat(testOrgOfficer.getOfficerCode()).isEqualTo(DEFAULT_OFFICER_CODE);
        assertThat(testOrgOfficer.getOfficerName()).isEqualTo(DEFAULT_OFFICER_NAME);
        assertThat(testOrgOfficer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOrgOfficer.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testOrgOfficer.getTrinhDo()).isEqualTo(DEFAULT_TRINH_DO);
        assertThat(testOrgOfficer.getChucDanh()).isEqualTo(DEFAULT_CHUC_DANH);
        assertThat(testOrgOfficer.getMaBacSi()).isEqualTo(DEFAULT_MA_BAC_SI);
        assertThat(testOrgOfficer.getHocHam()).isEqualTo(DEFAULT_HOC_HAM);
        assertThat(testOrgOfficer.getHocVi()).isEqualTo(DEFAULT_HOC_VI);
        assertThat(testOrgOfficer.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testOrgOfficer.getOpt()).isEqualTo(DEFAULT_OPT);
        assertThat(testOrgOfficer.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
        assertThat(testOrgOfficer.getNgaySinh()).isEqualTo(DEFAULT_NGAY_SINH);
        assertThat(testOrgOfficer.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
    }

    @Test
    @Transactional
    public void createOrgOfficerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orgOfficerRepository.findAll().size();

        // Create the OrgOfficer with an existing ID
        orgOfficer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrgOfficerMockMvc.perform(post("/api/org-officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgOfficer)))
            .andExpect(status().isBadRequest());

        // Validate the OrgOfficer in the database
        List<OrgOfficer> orgOfficerList = orgOfficerRepository.findAll();
        assertThat(orgOfficerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrgOfficers() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList
        restOrgOfficerMockMvc.perform(get("/api/org-officers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orgOfficer.getId().intValue())))
            .andExpect(jsonPath("$.[*].officerType").value(hasItem(DEFAULT_OFFICER_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].officerCode").value(hasItem(DEFAULT_OFFICER_CODE)))
            .andExpect(jsonPath("$.[*].officerName").value(hasItem(DEFAULT_OFFICER_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].trinhDo").value(hasItem(DEFAULT_TRINH_DO)))
            .andExpect(jsonPath("$.[*].chucDanh").value(hasItem(DEFAULT_CHUC_DANH)))
            .andExpect(jsonPath("$.[*].maBacSi").value(hasItem(DEFAULT_MA_BAC_SI)))
            .andExpect(jsonPath("$.[*].hocHam").value(hasItem(DEFAULT_HOC_HAM)))
            .andExpect(jsonPath("$.[*].hocVi").value(hasItem(DEFAULT_HOC_VI)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].opt").value(hasItem(DEFAULT_OPT)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].ngaySinh").value(hasItem(DEFAULT_NGAY_SINH.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)));
    }
    
    @Test
    @Transactional
    public void getOrgOfficer() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get the orgOfficer
        restOrgOfficerMockMvc.perform(get("/api/org-officers/{id}", orgOfficer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orgOfficer.getId().intValue()))
            .andExpect(jsonPath("$.officerType").value(DEFAULT_OFFICER_TYPE.intValue()))
            .andExpect(jsonPath("$.officerCode").value(DEFAULT_OFFICER_CODE))
            .andExpect(jsonPath("$.officerName").value(DEFAULT_OFFICER_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.trinhDo").value(DEFAULT_TRINH_DO))
            .andExpect(jsonPath("$.chucDanh").value(DEFAULT_CHUC_DANH))
            .andExpect(jsonPath("$.maBacSi").value(DEFAULT_MA_BAC_SI))
            .andExpect(jsonPath("$.hocHam").value(DEFAULT_HOC_HAM))
            .andExpect(jsonPath("$.hocVi").value(DEFAULT_HOC_VI))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.opt").value(DEFAULT_OPT))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.ngaySinh").value(DEFAULT_NGAY_SINH.toString()))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH));
    }


    @Test
    @Transactional
    public void getOrgOfficersByIdFiltering() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        Long id = orgOfficer.getId();

        defaultOrgOfficerShouldBeFound("id.equals=" + id);
        defaultOrgOfficerShouldNotBeFound("id.notEquals=" + id);

        defaultOrgOfficerShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultOrgOfficerShouldNotBeFound("id.greaterThan=" + id);

        defaultOrgOfficerShouldBeFound("id.lessThanOrEqual=" + id);
        defaultOrgOfficerShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerType equals to DEFAULT_OFFICER_TYPE
        defaultOrgOfficerShouldBeFound("officerType.equals=" + DEFAULT_OFFICER_TYPE);

        // Get all the orgOfficerList where officerType equals to UPDATED_OFFICER_TYPE
        defaultOrgOfficerShouldNotBeFound("officerType.equals=" + UPDATED_OFFICER_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerType not equals to DEFAULT_OFFICER_TYPE
        defaultOrgOfficerShouldNotBeFound("officerType.notEquals=" + DEFAULT_OFFICER_TYPE);

        // Get all the orgOfficerList where officerType not equals to UPDATED_OFFICER_TYPE
        defaultOrgOfficerShouldBeFound("officerType.notEquals=" + UPDATED_OFFICER_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerTypeIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerType in DEFAULT_OFFICER_TYPE or UPDATED_OFFICER_TYPE
        defaultOrgOfficerShouldBeFound("officerType.in=" + DEFAULT_OFFICER_TYPE + "," + UPDATED_OFFICER_TYPE);

        // Get all the orgOfficerList where officerType equals to UPDATED_OFFICER_TYPE
        defaultOrgOfficerShouldNotBeFound("officerType.in=" + UPDATED_OFFICER_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerType is not null
        defaultOrgOfficerShouldBeFound("officerType.specified=true");

        // Get all the orgOfficerList where officerType is null
        defaultOrgOfficerShouldNotBeFound("officerType.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerType is greater than or equal to DEFAULT_OFFICER_TYPE
        defaultOrgOfficerShouldBeFound("officerType.greaterThanOrEqual=" + DEFAULT_OFFICER_TYPE);

        // Get all the orgOfficerList where officerType is greater than or equal to UPDATED_OFFICER_TYPE
        defaultOrgOfficerShouldNotBeFound("officerType.greaterThanOrEqual=" + UPDATED_OFFICER_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerTypeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerType is less than or equal to DEFAULT_OFFICER_TYPE
        defaultOrgOfficerShouldBeFound("officerType.lessThanOrEqual=" + DEFAULT_OFFICER_TYPE);

        // Get all the orgOfficerList where officerType is less than or equal to SMALLER_OFFICER_TYPE
        defaultOrgOfficerShouldNotBeFound("officerType.lessThanOrEqual=" + SMALLER_OFFICER_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerType is less than DEFAULT_OFFICER_TYPE
        defaultOrgOfficerShouldNotBeFound("officerType.lessThan=" + DEFAULT_OFFICER_TYPE);

        // Get all the orgOfficerList where officerType is less than UPDATED_OFFICER_TYPE
        defaultOrgOfficerShouldBeFound("officerType.lessThan=" + UPDATED_OFFICER_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerTypeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerType is greater than DEFAULT_OFFICER_TYPE
        defaultOrgOfficerShouldNotBeFound("officerType.greaterThan=" + DEFAULT_OFFICER_TYPE);

        // Get all the orgOfficerList where officerType is greater than SMALLER_OFFICER_TYPE
        defaultOrgOfficerShouldBeFound("officerType.greaterThan=" + SMALLER_OFFICER_TYPE);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerCode equals to DEFAULT_OFFICER_CODE
        defaultOrgOfficerShouldBeFound("officerCode.equals=" + DEFAULT_OFFICER_CODE);

        // Get all the orgOfficerList where officerCode equals to UPDATED_OFFICER_CODE
        defaultOrgOfficerShouldNotBeFound("officerCode.equals=" + UPDATED_OFFICER_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerCode not equals to DEFAULT_OFFICER_CODE
        defaultOrgOfficerShouldNotBeFound("officerCode.notEquals=" + DEFAULT_OFFICER_CODE);

        // Get all the orgOfficerList where officerCode not equals to UPDATED_OFFICER_CODE
        defaultOrgOfficerShouldBeFound("officerCode.notEquals=" + UPDATED_OFFICER_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerCodeIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerCode in DEFAULT_OFFICER_CODE or UPDATED_OFFICER_CODE
        defaultOrgOfficerShouldBeFound("officerCode.in=" + DEFAULT_OFFICER_CODE + "," + UPDATED_OFFICER_CODE);

        // Get all the orgOfficerList where officerCode equals to UPDATED_OFFICER_CODE
        defaultOrgOfficerShouldNotBeFound("officerCode.in=" + UPDATED_OFFICER_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerCode is not null
        defaultOrgOfficerShouldBeFound("officerCode.specified=true");

        // Get all the orgOfficerList where officerCode is null
        defaultOrgOfficerShouldNotBeFound("officerCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByOfficerCodeContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerCode contains DEFAULT_OFFICER_CODE
        defaultOrgOfficerShouldBeFound("officerCode.contains=" + DEFAULT_OFFICER_CODE);

        // Get all the orgOfficerList where officerCode contains UPDATED_OFFICER_CODE
        defaultOrgOfficerShouldNotBeFound("officerCode.contains=" + UPDATED_OFFICER_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerCodeNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerCode does not contain DEFAULT_OFFICER_CODE
        defaultOrgOfficerShouldNotBeFound("officerCode.doesNotContain=" + DEFAULT_OFFICER_CODE);

        // Get all the orgOfficerList where officerCode does not contain UPDATED_OFFICER_CODE
        defaultOrgOfficerShouldBeFound("officerCode.doesNotContain=" + UPDATED_OFFICER_CODE);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerNameIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerName equals to DEFAULT_OFFICER_NAME
        defaultOrgOfficerShouldBeFound("officerName.equals=" + DEFAULT_OFFICER_NAME);

        // Get all the orgOfficerList where officerName equals to UPDATED_OFFICER_NAME
        defaultOrgOfficerShouldNotBeFound("officerName.equals=" + UPDATED_OFFICER_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerName not equals to DEFAULT_OFFICER_NAME
        defaultOrgOfficerShouldNotBeFound("officerName.notEquals=" + DEFAULT_OFFICER_NAME);

        // Get all the orgOfficerList where officerName not equals to UPDATED_OFFICER_NAME
        defaultOrgOfficerShouldBeFound("officerName.notEquals=" + UPDATED_OFFICER_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerNameIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerName in DEFAULT_OFFICER_NAME or UPDATED_OFFICER_NAME
        defaultOrgOfficerShouldBeFound("officerName.in=" + DEFAULT_OFFICER_NAME + "," + UPDATED_OFFICER_NAME);

        // Get all the orgOfficerList where officerName equals to UPDATED_OFFICER_NAME
        defaultOrgOfficerShouldNotBeFound("officerName.in=" + UPDATED_OFFICER_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerName is not null
        defaultOrgOfficerShouldBeFound("officerName.specified=true");

        // Get all the orgOfficerList where officerName is null
        defaultOrgOfficerShouldNotBeFound("officerName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByOfficerNameContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerName contains DEFAULT_OFFICER_NAME
        defaultOrgOfficerShouldBeFound("officerName.contains=" + DEFAULT_OFFICER_NAME);

        // Get all the orgOfficerList where officerName contains UPDATED_OFFICER_NAME
        defaultOrgOfficerShouldNotBeFound("officerName.contains=" + UPDATED_OFFICER_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOfficerNameNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where officerName does not contain DEFAULT_OFFICER_NAME
        defaultOrgOfficerShouldNotBeFound("officerName.doesNotContain=" + DEFAULT_OFFICER_NAME);

        // Get all the orgOfficerList where officerName does not contain UPDATED_OFFICER_NAME
        defaultOrgOfficerShouldBeFound("officerName.doesNotContain=" + UPDATED_OFFICER_NAME);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where email equals to DEFAULT_EMAIL
        defaultOrgOfficerShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the orgOfficerList where email equals to UPDATED_EMAIL
        defaultOrgOfficerShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where email not equals to DEFAULT_EMAIL
        defaultOrgOfficerShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the orgOfficerList where email not equals to UPDATED_EMAIL
        defaultOrgOfficerShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultOrgOfficerShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the orgOfficerList where email equals to UPDATED_EMAIL
        defaultOrgOfficerShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where email is not null
        defaultOrgOfficerShouldBeFound("email.specified=true");

        // Get all the orgOfficerList where email is null
        defaultOrgOfficerShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByEmailContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where email contains DEFAULT_EMAIL
        defaultOrgOfficerShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the orgOfficerList where email contains UPDATED_EMAIL
        defaultOrgOfficerShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where email does not contain DEFAULT_EMAIL
        defaultOrgOfficerShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the orgOfficerList where email does not contain UPDATED_EMAIL
        defaultOrgOfficerShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByNoteIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where note equals to DEFAULT_NOTE
        defaultOrgOfficerShouldBeFound("note.equals=" + DEFAULT_NOTE);

        // Get all the orgOfficerList where note equals to UPDATED_NOTE
        defaultOrgOfficerShouldNotBeFound("note.equals=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByNoteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where note not equals to DEFAULT_NOTE
        defaultOrgOfficerShouldNotBeFound("note.notEquals=" + DEFAULT_NOTE);

        // Get all the orgOfficerList where note not equals to UPDATED_NOTE
        defaultOrgOfficerShouldBeFound("note.notEquals=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByNoteIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where note in DEFAULT_NOTE or UPDATED_NOTE
        defaultOrgOfficerShouldBeFound("note.in=" + DEFAULT_NOTE + "," + UPDATED_NOTE);

        // Get all the orgOfficerList where note equals to UPDATED_NOTE
        defaultOrgOfficerShouldNotBeFound("note.in=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByNoteIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where note is not null
        defaultOrgOfficerShouldBeFound("note.specified=true");

        // Get all the orgOfficerList where note is null
        defaultOrgOfficerShouldNotBeFound("note.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByNoteContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where note contains DEFAULT_NOTE
        defaultOrgOfficerShouldBeFound("note.contains=" + DEFAULT_NOTE);

        // Get all the orgOfficerList where note contains UPDATED_NOTE
        defaultOrgOfficerShouldNotBeFound("note.contains=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByNoteNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where note does not contain DEFAULT_NOTE
        defaultOrgOfficerShouldNotBeFound("note.doesNotContain=" + DEFAULT_NOTE);

        // Get all the orgOfficerList where note does not contain UPDATED_NOTE
        defaultOrgOfficerShouldBeFound("note.doesNotContain=" + UPDATED_NOTE);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByTrinhDoIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where trinhDo equals to DEFAULT_TRINH_DO
        defaultOrgOfficerShouldBeFound("trinhDo.equals=" + DEFAULT_TRINH_DO);

        // Get all the orgOfficerList where trinhDo equals to UPDATED_TRINH_DO
        defaultOrgOfficerShouldNotBeFound("trinhDo.equals=" + UPDATED_TRINH_DO);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByTrinhDoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where trinhDo not equals to DEFAULT_TRINH_DO
        defaultOrgOfficerShouldNotBeFound("trinhDo.notEquals=" + DEFAULT_TRINH_DO);

        // Get all the orgOfficerList where trinhDo not equals to UPDATED_TRINH_DO
        defaultOrgOfficerShouldBeFound("trinhDo.notEquals=" + UPDATED_TRINH_DO);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByTrinhDoIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where trinhDo in DEFAULT_TRINH_DO or UPDATED_TRINH_DO
        defaultOrgOfficerShouldBeFound("trinhDo.in=" + DEFAULT_TRINH_DO + "," + UPDATED_TRINH_DO);

        // Get all the orgOfficerList where trinhDo equals to UPDATED_TRINH_DO
        defaultOrgOfficerShouldNotBeFound("trinhDo.in=" + UPDATED_TRINH_DO);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByTrinhDoIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where trinhDo is not null
        defaultOrgOfficerShouldBeFound("trinhDo.specified=true");

        // Get all the orgOfficerList where trinhDo is null
        defaultOrgOfficerShouldNotBeFound("trinhDo.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByTrinhDoContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where trinhDo contains DEFAULT_TRINH_DO
        defaultOrgOfficerShouldBeFound("trinhDo.contains=" + DEFAULT_TRINH_DO);

        // Get all the orgOfficerList where trinhDo contains UPDATED_TRINH_DO
        defaultOrgOfficerShouldNotBeFound("trinhDo.contains=" + UPDATED_TRINH_DO);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByTrinhDoNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where trinhDo does not contain DEFAULT_TRINH_DO
        defaultOrgOfficerShouldNotBeFound("trinhDo.doesNotContain=" + DEFAULT_TRINH_DO);

        // Get all the orgOfficerList where trinhDo does not contain UPDATED_TRINH_DO
        defaultOrgOfficerShouldBeFound("trinhDo.doesNotContain=" + UPDATED_TRINH_DO);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByChucDanhIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where chucDanh equals to DEFAULT_CHUC_DANH
        defaultOrgOfficerShouldBeFound("chucDanh.equals=" + DEFAULT_CHUC_DANH);

        // Get all the orgOfficerList where chucDanh equals to UPDATED_CHUC_DANH
        defaultOrgOfficerShouldNotBeFound("chucDanh.equals=" + UPDATED_CHUC_DANH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByChucDanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where chucDanh not equals to DEFAULT_CHUC_DANH
        defaultOrgOfficerShouldNotBeFound("chucDanh.notEquals=" + DEFAULT_CHUC_DANH);

        // Get all the orgOfficerList where chucDanh not equals to UPDATED_CHUC_DANH
        defaultOrgOfficerShouldBeFound("chucDanh.notEquals=" + UPDATED_CHUC_DANH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByChucDanhIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where chucDanh in DEFAULT_CHUC_DANH or UPDATED_CHUC_DANH
        defaultOrgOfficerShouldBeFound("chucDanh.in=" + DEFAULT_CHUC_DANH + "," + UPDATED_CHUC_DANH);

        // Get all the orgOfficerList where chucDanh equals to UPDATED_CHUC_DANH
        defaultOrgOfficerShouldNotBeFound("chucDanh.in=" + UPDATED_CHUC_DANH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByChucDanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where chucDanh is not null
        defaultOrgOfficerShouldBeFound("chucDanh.specified=true");

        // Get all the orgOfficerList where chucDanh is null
        defaultOrgOfficerShouldNotBeFound("chucDanh.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByChucDanhContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where chucDanh contains DEFAULT_CHUC_DANH
        defaultOrgOfficerShouldBeFound("chucDanh.contains=" + DEFAULT_CHUC_DANH);

        // Get all the orgOfficerList where chucDanh contains UPDATED_CHUC_DANH
        defaultOrgOfficerShouldNotBeFound("chucDanh.contains=" + UPDATED_CHUC_DANH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByChucDanhNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where chucDanh does not contain DEFAULT_CHUC_DANH
        defaultOrgOfficerShouldNotBeFound("chucDanh.doesNotContain=" + DEFAULT_CHUC_DANH);

        // Get all the orgOfficerList where chucDanh does not contain UPDATED_CHUC_DANH
        defaultOrgOfficerShouldBeFound("chucDanh.doesNotContain=" + UPDATED_CHUC_DANH);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByMaBacSiIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where maBacSi equals to DEFAULT_MA_BAC_SI
        defaultOrgOfficerShouldBeFound("maBacSi.equals=" + DEFAULT_MA_BAC_SI);

        // Get all the orgOfficerList where maBacSi equals to UPDATED_MA_BAC_SI
        defaultOrgOfficerShouldNotBeFound("maBacSi.equals=" + UPDATED_MA_BAC_SI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByMaBacSiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where maBacSi not equals to DEFAULT_MA_BAC_SI
        defaultOrgOfficerShouldNotBeFound("maBacSi.notEquals=" + DEFAULT_MA_BAC_SI);

        // Get all the orgOfficerList where maBacSi not equals to UPDATED_MA_BAC_SI
        defaultOrgOfficerShouldBeFound("maBacSi.notEquals=" + UPDATED_MA_BAC_SI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByMaBacSiIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where maBacSi in DEFAULT_MA_BAC_SI or UPDATED_MA_BAC_SI
        defaultOrgOfficerShouldBeFound("maBacSi.in=" + DEFAULT_MA_BAC_SI + "," + UPDATED_MA_BAC_SI);

        // Get all the orgOfficerList where maBacSi equals to UPDATED_MA_BAC_SI
        defaultOrgOfficerShouldNotBeFound("maBacSi.in=" + UPDATED_MA_BAC_SI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByMaBacSiIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where maBacSi is not null
        defaultOrgOfficerShouldBeFound("maBacSi.specified=true");

        // Get all the orgOfficerList where maBacSi is null
        defaultOrgOfficerShouldNotBeFound("maBacSi.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByMaBacSiContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where maBacSi contains DEFAULT_MA_BAC_SI
        defaultOrgOfficerShouldBeFound("maBacSi.contains=" + DEFAULT_MA_BAC_SI);

        // Get all the orgOfficerList where maBacSi contains UPDATED_MA_BAC_SI
        defaultOrgOfficerShouldNotBeFound("maBacSi.contains=" + UPDATED_MA_BAC_SI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByMaBacSiNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where maBacSi does not contain DEFAULT_MA_BAC_SI
        defaultOrgOfficerShouldNotBeFound("maBacSi.doesNotContain=" + DEFAULT_MA_BAC_SI);

        // Get all the orgOfficerList where maBacSi does not contain UPDATED_MA_BAC_SI
        defaultOrgOfficerShouldBeFound("maBacSi.doesNotContain=" + UPDATED_MA_BAC_SI);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByHocHamIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocHam equals to DEFAULT_HOC_HAM
        defaultOrgOfficerShouldBeFound("hocHam.equals=" + DEFAULT_HOC_HAM);

        // Get all the orgOfficerList where hocHam equals to UPDATED_HOC_HAM
        defaultOrgOfficerShouldNotBeFound("hocHam.equals=" + UPDATED_HOC_HAM);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByHocHamIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocHam not equals to DEFAULT_HOC_HAM
        defaultOrgOfficerShouldNotBeFound("hocHam.notEquals=" + DEFAULT_HOC_HAM);

        // Get all the orgOfficerList where hocHam not equals to UPDATED_HOC_HAM
        defaultOrgOfficerShouldBeFound("hocHam.notEquals=" + UPDATED_HOC_HAM);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByHocHamIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocHam in DEFAULT_HOC_HAM or UPDATED_HOC_HAM
        defaultOrgOfficerShouldBeFound("hocHam.in=" + DEFAULT_HOC_HAM + "," + UPDATED_HOC_HAM);

        // Get all the orgOfficerList where hocHam equals to UPDATED_HOC_HAM
        defaultOrgOfficerShouldNotBeFound("hocHam.in=" + UPDATED_HOC_HAM);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByHocHamIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocHam is not null
        defaultOrgOfficerShouldBeFound("hocHam.specified=true");

        // Get all the orgOfficerList where hocHam is null
        defaultOrgOfficerShouldNotBeFound("hocHam.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByHocHamContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocHam contains DEFAULT_HOC_HAM
        defaultOrgOfficerShouldBeFound("hocHam.contains=" + DEFAULT_HOC_HAM);

        // Get all the orgOfficerList where hocHam contains UPDATED_HOC_HAM
        defaultOrgOfficerShouldNotBeFound("hocHam.contains=" + UPDATED_HOC_HAM);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByHocHamNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocHam does not contain DEFAULT_HOC_HAM
        defaultOrgOfficerShouldNotBeFound("hocHam.doesNotContain=" + DEFAULT_HOC_HAM);

        // Get all the orgOfficerList where hocHam does not contain UPDATED_HOC_HAM
        defaultOrgOfficerShouldBeFound("hocHam.doesNotContain=" + UPDATED_HOC_HAM);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByHocViIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocVi equals to DEFAULT_HOC_VI
        defaultOrgOfficerShouldBeFound("hocVi.equals=" + DEFAULT_HOC_VI);

        // Get all the orgOfficerList where hocVi equals to UPDATED_HOC_VI
        defaultOrgOfficerShouldNotBeFound("hocVi.equals=" + UPDATED_HOC_VI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByHocViIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocVi not equals to DEFAULT_HOC_VI
        defaultOrgOfficerShouldNotBeFound("hocVi.notEquals=" + DEFAULT_HOC_VI);

        // Get all the orgOfficerList where hocVi not equals to UPDATED_HOC_VI
        defaultOrgOfficerShouldBeFound("hocVi.notEquals=" + UPDATED_HOC_VI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByHocViIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocVi in DEFAULT_HOC_VI or UPDATED_HOC_VI
        defaultOrgOfficerShouldBeFound("hocVi.in=" + DEFAULT_HOC_VI + "," + UPDATED_HOC_VI);

        // Get all the orgOfficerList where hocVi equals to UPDATED_HOC_VI
        defaultOrgOfficerShouldNotBeFound("hocVi.in=" + UPDATED_HOC_VI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByHocViIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocVi is not null
        defaultOrgOfficerShouldBeFound("hocVi.specified=true");

        // Get all the orgOfficerList where hocVi is null
        defaultOrgOfficerShouldNotBeFound("hocVi.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByHocViContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocVi contains DEFAULT_HOC_VI
        defaultOrgOfficerShouldBeFound("hocVi.contains=" + DEFAULT_HOC_VI);

        // Get all the orgOfficerList where hocVi contains UPDATED_HOC_VI
        defaultOrgOfficerShouldNotBeFound("hocVi.contains=" + UPDATED_HOC_VI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByHocViNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where hocVi does not contain DEFAULT_HOC_VI
        defaultOrgOfficerShouldNotBeFound("hocVi.doesNotContain=" + DEFAULT_HOC_VI);

        // Get all the orgOfficerList where hocVi does not contain UPDATED_HOC_VI
        defaultOrgOfficerShouldBeFound("hocVi.doesNotContain=" + UPDATED_HOC_VI);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where phone equals to DEFAULT_PHONE
        defaultOrgOfficerShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the orgOfficerList where phone equals to UPDATED_PHONE
        defaultOrgOfficerShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where phone not equals to DEFAULT_PHONE
        defaultOrgOfficerShouldNotBeFound("phone.notEquals=" + DEFAULT_PHONE);

        // Get all the orgOfficerList where phone not equals to UPDATED_PHONE
        defaultOrgOfficerShouldBeFound("phone.notEquals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultOrgOfficerShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the orgOfficerList where phone equals to UPDATED_PHONE
        defaultOrgOfficerShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where phone is not null
        defaultOrgOfficerShouldBeFound("phone.specified=true");

        // Get all the orgOfficerList where phone is null
        defaultOrgOfficerShouldNotBeFound("phone.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByPhoneContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where phone contains DEFAULT_PHONE
        defaultOrgOfficerShouldBeFound("phone.contains=" + DEFAULT_PHONE);

        // Get all the orgOfficerList where phone contains UPDATED_PHONE
        defaultOrgOfficerShouldNotBeFound("phone.contains=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByPhoneNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where phone does not contain DEFAULT_PHONE
        defaultOrgOfficerShouldNotBeFound("phone.doesNotContain=" + DEFAULT_PHONE);

        // Get all the orgOfficerList where phone does not contain UPDATED_PHONE
        defaultOrgOfficerShouldBeFound("phone.doesNotContain=" + UPDATED_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByOptIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where opt equals to DEFAULT_OPT
        defaultOrgOfficerShouldBeFound("opt.equals=" + DEFAULT_OPT);

        // Get all the orgOfficerList where opt equals to UPDATED_OPT
        defaultOrgOfficerShouldNotBeFound("opt.equals=" + UPDATED_OPT);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOptIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where opt not equals to DEFAULT_OPT
        defaultOrgOfficerShouldNotBeFound("opt.notEquals=" + DEFAULT_OPT);

        // Get all the orgOfficerList where opt not equals to UPDATED_OPT
        defaultOrgOfficerShouldBeFound("opt.notEquals=" + UPDATED_OPT);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOptIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where opt in DEFAULT_OPT or UPDATED_OPT
        defaultOrgOfficerShouldBeFound("opt.in=" + DEFAULT_OPT + "," + UPDATED_OPT);

        // Get all the orgOfficerList where opt equals to UPDATED_OPT
        defaultOrgOfficerShouldNotBeFound("opt.in=" + UPDATED_OPT);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOptIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where opt is not null
        defaultOrgOfficerShouldBeFound("opt.specified=true");

        // Get all the orgOfficerList where opt is null
        defaultOrgOfficerShouldNotBeFound("opt.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByOptContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where opt contains DEFAULT_OPT
        defaultOrgOfficerShouldBeFound("opt.contains=" + DEFAULT_OPT);

        // Get all the orgOfficerList where opt contains UPDATED_OPT
        defaultOrgOfficerShouldNotBeFound("opt.contains=" + UPDATED_OPT);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByOptNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where opt does not contain DEFAULT_OPT
        defaultOrgOfficerShouldNotBeFound("opt.doesNotContain=" + DEFAULT_OPT);

        // Get all the orgOfficerList where opt does not contain UPDATED_OPT
        defaultOrgOfficerShouldBeFound("opt.doesNotContain=" + UPDATED_OPT);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByDiaChiIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where diaChi equals to DEFAULT_DIA_CHI
        defaultOrgOfficerShouldBeFound("diaChi.equals=" + DEFAULT_DIA_CHI);

        // Get all the orgOfficerList where diaChi equals to UPDATED_DIA_CHI
        defaultOrgOfficerShouldNotBeFound("diaChi.equals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByDiaChiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where diaChi not equals to DEFAULT_DIA_CHI
        defaultOrgOfficerShouldNotBeFound("diaChi.notEquals=" + DEFAULT_DIA_CHI);

        // Get all the orgOfficerList where diaChi not equals to UPDATED_DIA_CHI
        defaultOrgOfficerShouldBeFound("diaChi.notEquals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByDiaChiIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where diaChi in DEFAULT_DIA_CHI or UPDATED_DIA_CHI
        defaultOrgOfficerShouldBeFound("diaChi.in=" + DEFAULT_DIA_CHI + "," + UPDATED_DIA_CHI);

        // Get all the orgOfficerList where diaChi equals to UPDATED_DIA_CHI
        defaultOrgOfficerShouldNotBeFound("diaChi.in=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByDiaChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where diaChi is not null
        defaultOrgOfficerShouldBeFound("diaChi.specified=true");

        // Get all the orgOfficerList where diaChi is null
        defaultOrgOfficerShouldNotBeFound("diaChi.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByDiaChiContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where diaChi contains DEFAULT_DIA_CHI
        defaultOrgOfficerShouldBeFound("diaChi.contains=" + DEFAULT_DIA_CHI);

        // Get all the orgOfficerList where diaChi contains UPDATED_DIA_CHI
        defaultOrgOfficerShouldNotBeFound("diaChi.contains=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByDiaChiNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where diaChi does not contain DEFAULT_DIA_CHI
        defaultOrgOfficerShouldNotBeFound("diaChi.doesNotContain=" + DEFAULT_DIA_CHI);

        // Get all the orgOfficerList where diaChi does not contain UPDATED_DIA_CHI
        defaultOrgOfficerShouldBeFound("diaChi.doesNotContain=" + UPDATED_DIA_CHI);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByNgaySinhIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where ngaySinh equals to DEFAULT_NGAY_SINH
        defaultOrgOfficerShouldBeFound("ngaySinh.equals=" + DEFAULT_NGAY_SINH);

        // Get all the orgOfficerList where ngaySinh equals to UPDATED_NGAY_SINH
        defaultOrgOfficerShouldNotBeFound("ngaySinh.equals=" + UPDATED_NGAY_SINH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByNgaySinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where ngaySinh not equals to DEFAULT_NGAY_SINH
        defaultOrgOfficerShouldNotBeFound("ngaySinh.notEquals=" + DEFAULT_NGAY_SINH);

        // Get all the orgOfficerList where ngaySinh not equals to UPDATED_NGAY_SINH
        defaultOrgOfficerShouldBeFound("ngaySinh.notEquals=" + UPDATED_NGAY_SINH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByNgaySinhIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where ngaySinh in DEFAULT_NGAY_SINH or UPDATED_NGAY_SINH
        defaultOrgOfficerShouldBeFound("ngaySinh.in=" + DEFAULT_NGAY_SINH + "," + UPDATED_NGAY_SINH);

        // Get all the orgOfficerList where ngaySinh equals to UPDATED_NGAY_SINH
        defaultOrgOfficerShouldNotBeFound("ngaySinh.in=" + UPDATED_NGAY_SINH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByNgaySinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where ngaySinh is not null
        defaultOrgOfficerShouldBeFound("ngaySinh.specified=true");

        // Get all the orgOfficerList where ngaySinh is null
        defaultOrgOfficerShouldNotBeFound("ngaySinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByGioiTinhIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where gioiTinh equals to DEFAULT_GIOI_TINH
        defaultOrgOfficerShouldBeFound("gioiTinh.equals=" + DEFAULT_GIOI_TINH);

        // Get all the orgOfficerList where gioiTinh equals to UPDATED_GIOI_TINH
        defaultOrgOfficerShouldNotBeFound("gioiTinh.equals=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByGioiTinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where gioiTinh not equals to DEFAULT_GIOI_TINH
        defaultOrgOfficerShouldNotBeFound("gioiTinh.notEquals=" + DEFAULT_GIOI_TINH);

        // Get all the orgOfficerList where gioiTinh not equals to UPDATED_GIOI_TINH
        defaultOrgOfficerShouldBeFound("gioiTinh.notEquals=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByGioiTinhIsInShouldWork() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where gioiTinh in DEFAULT_GIOI_TINH or UPDATED_GIOI_TINH
        defaultOrgOfficerShouldBeFound("gioiTinh.in=" + DEFAULT_GIOI_TINH + "," + UPDATED_GIOI_TINH);

        // Get all the orgOfficerList where gioiTinh equals to UPDATED_GIOI_TINH
        defaultOrgOfficerShouldNotBeFound("gioiTinh.in=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByGioiTinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where gioiTinh is not null
        defaultOrgOfficerShouldBeFound("gioiTinh.specified=true");

        // Get all the orgOfficerList where gioiTinh is null
        defaultOrgOfficerShouldNotBeFound("gioiTinh.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOfficersByGioiTinhContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where gioiTinh contains DEFAULT_GIOI_TINH
        defaultOrgOfficerShouldBeFound("gioiTinh.contains=" + DEFAULT_GIOI_TINH);

        // Get all the orgOfficerList where gioiTinh contains UPDATED_GIOI_TINH
        defaultOrgOfficerShouldNotBeFound("gioiTinh.contains=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    public void getAllOrgOfficersByGioiTinhNotContainsSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);

        // Get all the orgOfficerList where gioiTinh does not contain DEFAULT_GIOI_TINH
        defaultOrgOfficerShouldNotBeFound("gioiTinh.doesNotContain=" + DEFAULT_GIOI_TINH);

        // Get all the orgOfficerList where gioiTinh does not contain UPDATED_GIOI_TINH
        defaultOrgOfficerShouldBeFound("gioiTinh.doesNotContain=" + UPDATED_GIOI_TINH);
    }


    @Test
    @Transactional
    public void getAllOrgOfficersByNhanvienIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOfficerRepository.saveAndFlush(orgOfficer);
        CdtNhanVien nhanvien = CdtNhanVienResourceIT.createEntity(em);
        em.persist(nhanvien);
        em.flush();
        orgOfficer.addNhanvien(nhanvien);
        orgOfficerRepository.saveAndFlush(orgOfficer);
        Long nhanvienId = nhanvien.getId();

        // Get all the orgOfficerList where nhanvien equals to nhanvienId
        defaultOrgOfficerShouldBeFound("nhanvienId.equals=" + nhanvienId);

        // Get all the orgOfficerList where nhanvien equals to nhanvienId + 1
        defaultOrgOfficerShouldNotBeFound("nhanvienId.equals=" + (nhanvienId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultOrgOfficerShouldBeFound(String filter) throws Exception {
        restOrgOfficerMockMvc.perform(get("/api/org-officers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orgOfficer.getId().intValue())))
            .andExpect(jsonPath("$.[*].officerType").value(hasItem(DEFAULT_OFFICER_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].officerCode").value(hasItem(DEFAULT_OFFICER_CODE)))
            .andExpect(jsonPath("$.[*].officerName").value(hasItem(DEFAULT_OFFICER_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].trinhDo").value(hasItem(DEFAULT_TRINH_DO)))
            .andExpect(jsonPath("$.[*].chucDanh").value(hasItem(DEFAULT_CHUC_DANH)))
            .andExpect(jsonPath("$.[*].maBacSi").value(hasItem(DEFAULT_MA_BAC_SI)))
            .andExpect(jsonPath("$.[*].hocHam").value(hasItem(DEFAULT_HOC_HAM)))
            .andExpect(jsonPath("$.[*].hocVi").value(hasItem(DEFAULT_HOC_VI)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].opt").value(hasItem(DEFAULT_OPT)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].ngaySinh").value(hasItem(DEFAULT_NGAY_SINH.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)));

        // Check, that the count call also returns 1
        restOrgOfficerMockMvc.perform(get("/api/org-officers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultOrgOfficerShouldNotBeFound(String filter) throws Exception {
        restOrgOfficerMockMvc.perform(get("/api/org-officers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOrgOfficerMockMvc.perform(get("/api/org-officers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOrgOfficer() throws Exception {
        // Get the orgOfficer
        restOrgOfficerMockMvc.perform(get("/api/org-officers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrgOfficer() throws Exception {
        // Initialize the database
        orgOfficerService.save(orgOfficer);

        int databaseSizeBeforeUpdate = orgOfficerRepository.findAll().size();

        // Update the orgOfficer
        OrgOfficer updatedOrgOfficer = orgOfficerRepository.findById(orgOfficer.getId()).get();
        // Disconnect from session so that the updates on updatedOrgOfficer are not directly saved in db
        em.detach(updatedOrgOfficer);
        updatedOrgOfficer
            .officerType(UPDATED_OFFICER_TYPE)
            .officerCode(UPDATED_OFFICER_CODE)
            .officerName(UPDATED_OFFICER_NAME)
            .email(UPDATED_EMAIL)
            .note(UPDATED_NOTE)
            .trinhDo(UPDATED_TRINH_DO)
            .chucDanh(UPDATED_CHUC_DANH)
            .maBacSi(UPDATED_MA_BAC_SI)
            .hocHam(UPDATED_HOC_HAM)
            .hocVi(UPDATED_HOC_VI)
            .phone(UPDATED_PHONE)
            .opt(UPDATED_OPT)
            .diaChi(UPDATED_DIA_CHI)
            .ngaySinh(UPDATED_NGAY_SINH)
            .gioiTinh(UPDATED_GIOI_TINH);

        restOrgOfficerMockMvc.perform(put("/api/org-officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrgOfficer)))
            .andExpect(status().isOk());

        // Validate the OrgOfficer in the database
        List<OrgOfficer> orgOfficerList = orgOfficerRepository.findAll();
        assertThat(orgOfficerList).hasSize(databaseSizeBeforeUpdate);
        OrgOfficer testOrgOfficer = orgOfficerList.get(orgOfficerList.size() - 1);
        assertThat(testOrgOfficer.getOfficerType()).isEqualTo(UPDATED_OFFICER_TYPE);
        assertThat(testOrgOfficer.getOfficerCode()).isEqualTo(UPDATED_OFFICER_CODE);
        assertThat(testOrgOfficer.getOfficerName()).isEqualTo(UPDATED_OFFICER_NAME);
        assertThat(testOrgOfficer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOrgOfficer.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testOrgOfficer.getTrinhDo()).isEqualTo(UPDATED_TRINH_DO);
        assertThat(testOrgOfficer.getChucDanh()).isEqualTo(UPDATED_CHUC_DANH);
        assertThat(testOrgOfficer.getMaBacSi()).isEqualTo(UPDATED_MA_BAC_SI);
        assertThat(testOrgOfficer.getHocHam()).isEqualTo(UPDATED_HOC_HAM);
        assertThat(testOrgOfficer.getHocVi()).isEqualTo(UPDATED_HOC_VI);
        assertThat(testOrgOfficer.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOrgOfficer.getOpt()).isEqualTo(UPDATED_OPT);
        assertThat(testOrgOfficer.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testOrgOfficer.getNgaySinh()).isEqualTo(UPDATED_NGAY_SINH);
        assertThat(testOrgOfficer.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    public void updateNonExistingOrgOfficer() throws Exception {
        int databaseSizeBeforeUpdate = orgOfficerRepository.findAll().size();

        // Create the OrgOfficer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrgOfficerMockMvc.perform(put("/api/org-officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgOfficer)))
            .andExpect(status().isBadRequest());

        // Validate the OrgOfficer in the database
        List<OrgOfficer> orgOfficerList = orgOfficerRepository.findAll();
        assertThat(orgOfficerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrgOfficer() throws Exception {
        // Initialize the database
        orgOfficerService.save(orgOfficer);

        int databaseSizeBeforeDelete = orgOfficerRepository.findAll().size();

        // Delete the orgOfficer
        restOrgOfficerMockMvc.perform(delete("/api/org-officers/{id}", orgOfficer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrgOfficer> orgOfficerList = orgOfficerRepository.findAll();
        assertThat(orgOfficerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
