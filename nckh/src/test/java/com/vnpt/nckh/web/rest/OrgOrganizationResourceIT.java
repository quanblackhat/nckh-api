package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.NckhApp;
import com.vnpt.nckh.domain.OrgOrganization;
import com.vnpt.nckh.domain.OrgOfficer;
import com.vnpt.nckh.repository.OrgOrganizationRepository;
import com.vnpt.nckh.service.OrgOrganizationService;
import com.vnpt.nckh.web.rest.errors.ExceptionTranslator;
import com.vnpt.nckh.service.dto.OrgOrganizationCriteria;
import com.vnpt.nckh.service.OrgOrganizationQueryService;

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

import static com.vnpt.nckh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrgOrganizationResource} REST controller.
 */
@SpringBootTest(classes = NckhApp.class)
public class OrgOrganizationResourceIT {

    private static final Long DEFAULT_PARENT_ID = 1L;
    private static final Long UPDATED_PARENT_ID = 2L;
    private static final Long SMALLER_PARENT_ID = 1L - 1L;

    private static final Long DEFAULT_ORG_TYPE = 1L;
    private static final Long UPDATED_ORG_TYPE = 2L;
    private static final Long SMALLER_ORG_TYPE = 1L - 1L;

    private static final String DEFAULT_ORG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_ORG_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_DB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DB_SCHEMA = "AAAAAAAAAA";
    private static final String UPDATED_DB_SCHEMA = "BBBBBBBBBB";

    private static final Long DEFAULT_PROVINCE_ID = 1L;
    private static final Long UPDATED_PROVINCE_ID = 2L;
    private static final Long SMALLER_PROVINCE_ID = 1L - 1L;

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;
    private static final Long SMALLER_STATUS = 1L - 1L;

    private static final String DEFAULT_ORG_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_HOSPITAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HOSPITAL_CODE = "BBBBBBBBBB";

    @Autowired
    private OrgOrganizationRepository orgOrganizationRepository;

    @Autowired
    private OrgOrganizationService orgOrganizationService;

    @Autowired
    private OrgOrganizationQueryService orgOrganizationQueryService;

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

    private MockMvc restOrgOrganizationMockMvc;

    private OrgOrganization orgOrganization;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrgOrganizationResource orgOrganizationResource = new OrgOrganizationResource(orgOrganizationService, orgOrganizationQueryService);
        this.restOrgOrganizationMockMvc = MockMvcBuilders.standaloneSetup(orgOrganizationResource)
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
    public static OrgOrganization createEntity(EntityManager em) {
        OrgOrganization orgOrganization = new OrgOrganization()
            .parentId(DEFAULT_PARENT_ID)
            .orgType(DEFAULT_ORG_TYPE)
            .orgCode(DEFAULT_ORG_CODE)
            .orgName(DEFAULT_ORG_NAME)
            .orgLevel(DEFAULT_ORG_LEVEL)
            .dbName(DEFAULT_DB_NAME)
            .dbSchema(DEFAULT_DB_SCHEMA)
            .provinceId(DEFAULT_PROVINCE_ID)
            .status(DEFAULT_STATUS)
            .orgAddress(DEFAULT_ORG_ADDRESS)
            .hospitalCode(DEFAULT_HOSPITAL_CODE);
        return orgOrganization;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrgOrganization createUpdatedEntity(EntityManager em) {
        OrgOrganization orgOrganization = new OrgOrganization()
            .parentId(UPDATED_PARENT_ID)
            .orgType(UPDATED_ORG_TYPE)
            .orgCode(UPDATED_ORG_CODE)
            .orgName(UPDATED_ORG_NAME)
            .orgLevel(UPDATED_ORG_LEVEL)
            .dbName(UPDATED_DB_NAME)
            .dbSchema(UPDATED_DB_SCHEMA)
            .provinceId(UPDATED_PROVINCE_ID)
            .status(UPDATED_STATUS)
            .orgAddress(UPDATED_ORG_ADDRESS)
            .hospitalCode(UPDATED_HOSPITAL_CODE);
        return orgOrganization;
    }

    @BeforeEach
    public void initTest() {
        orgOrganization = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrgOrganization() throws Exception {
        int databaseSizeBeforeCreate = orgOrganizationRepository.findAll().size();

        // Create the OrgOrganization
        restOrgOrganizationMockMvc.perform(post("/api/org-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgOrganization)))
            .andExpect(status().isCreated());

        // Validate the OrgOrganization in the database
        List<OrgOrganization> orgOrganizationList = orgOrganizationRepository.findAll();
        assertThat(orgOrganizationList).hasSize(databaseSizeBeforeCreate + 1);
        OrgOrganization testOrgOrganization = orgOrganizationList.get(orgOrganizationList.size() - 1);
        assertThat(testOrgOrganization.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testOrgOrganization.getOrgType()).isEqualTo(DEFAULT_ORG_TYPE);
        assertThat(testOrgOrganization.getOrgCode()).isEqualTo(DEFAULT_ORG_CODE);
        assertThat(testOrgOrganization.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testOrgOrganization.getOrgLevel()).isEqualTo(DEFAULT_ORG_LEVEL);
        assertThat(testOrgOrganization.getDbName()).isEqualTo(DEFAULT_DB_NAME);
        assertThat(testOrgOrganization.getDbSchema()).isEqualTo(DEFAULT_DB_SCHEMA);
        assertThat(testOrgOrganization.getProvinceId()).isEqualTo(DEFAULT_PROVINCE_ID);
        assertThat(testOrgOrganization.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOrgOrganization.getOrgAddress()).isEqualTo(DEFAULT_ORG_ADDRESS);
        assertThat(testOrgOrganization.getHospitalCode()).isEqualTo(DEFAULT_HOSPITAL_CODE);
    }

    @Test
    @Transactional
    public void createOrgOrganizationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orgOrganizationRepository.findAll().size();

        // Create the OrgOrganization with an existing ID
        orgOrganization.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrgOrganizationMockMvc.perform(post("/api/org-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgOrganization)))
            .andExpect(status().isBadRequest());

        // Validate the OrgOrganization in the database
        List<OrgOrganization> orgOrganizationList = orgOrganizationRepository.findAll();
        assertThat(orgOrganizationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrgTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orgOrganizationRepository.findAll().size();
        // set the field null
        orgOrganization.setOrgType(null);

        // Create the OrgOrganization, which fails.

        restOrgOrganizationMockMvc.perform(post("/api/org-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgOrganization)))
            .andExpect(status().isBadRequest());

        List<OrgOrganization> orgOrganizationList = orgOrganizationRepository.findAll();
        assertThat(orgOrganizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizations() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList
        restOrgOrganizationMockMvc.perform(get("/api/org-organizations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orgOrganization.getId().intValue())))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].orgType").value(hasItem(DEFAULT_ORG_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].orgCode").value(hasItem(DEFAULT_ORG_CODE)))
            .andExpect(jsonPath("$.[*].orgName").value(hasItem(DEFAULT_ORG_NAME)))
            .andExpect(jsonPath("$.[*].orgLevel").value(hasItem(DEFAULT_ORG_LEVEL)))
            .andExpect(jsonPath("$.[*].dbName").value(hasItem(DEFAULT_DB_NAME)))
            .andExpect(jsonPath("$.[*].dbSchema").value(hasItem(DEFAULT_DB_SCHEMA)))
            .andExpect(jsonPath("$.[*].provinceId").value(hasItem(DEFAULT_PROVINCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].orgAddress").value(hasItem(DEFAULT_ORG_ADDRESS)))
            .andExpect(jsonPath("$.[*].hospitalCode").value(hasItem(DEFAULT_HOSPITAL_CODE)));
    }
    
    @Test
    @Transactional
    public void getOrgOrganization() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get the orgOrganization
        restOrgOrganizationMockMvc.perform(get("/api/org-organizations/{id}", orgOrganization.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orgOrganization.getId().intValue()))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID.intValue()))
            .andExpect(jsonPath("$.orgType").value(DEFAULT_ORG_TYPE.intValue()))
            .andExpect(jsonPath("$.orgCode").value(DEFAULT_ORG_CODE))
            .andExpect(jsonPath("$.orgName").value(DEFAULT_ORG_NAME))
            .andExpect(jsonPath("$.orgLevel").value(DEFAULT_ORG_LEVEL))
            .andExpect(jsonPath("$.dbName").value(DEFAULT_DB_NAME))
            .andExpect(jsonPath("$.dbSchema").value(DEFAULT_DB_SCHEMA))
            .andExpect(jsonPath("$.provinceId").value(DEFAULT_PROVINCE_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()))
            .andExpect(jsonPath("$.orgAddress").value(DEFAULT_ORG_ADDRESS))
            .andExpect(jsonPath("$.hospitalCode").value(DEFAULT_HOSPITAL_CODE));
    }


    @Test
    @Transactional
    public void getOrgOrganizationsByIdFiltering() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        Long id = orgOrganization.getId();

        defaultOrgOrganizationShouldBeFound("id.equals=" + id);
        defaultOrgOrganizationShouldNotBeFound("id.notEquals=" + id);

        defaultOrgOrganizationShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultOrgOrganizationShouldNotBeFound("id.greaterThan=" + id);

        defaultOrgOrganizationShouldBeFound("id.lessThanOrEqual=" + id);
        defaultOrgOrganizationShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByParentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where parentId equals to DEFAULT_PARENT_ID
        defaultOrgOrganizationShouldBeFound("parentId.equals=" + DEFAULT_PARENT_ID);

        // Get all the orgOrganizationList where parentId equals to UPDATED_PARENT_ID
        defaultOrgOrganizationShouldNotBeFound("parentId.equals=" + UPDATED_PARENT_ID);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByParentIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where parentId not equals to DEFAULT_PARENT_ID
        defaultOrgOrganizationShouldNotBeFound("parentId.notEquals=" + DEFAULT_PARENT_ID);

        // Get all the orgOrganizationList where parentId not equals to UPDATED_PARENT_ID
        defaultOrgOrganizationShouldBeFound("parentId.notEquals=" + UPDATED_PARENT_ID);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByParentIdIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where parentId in DEFAULT_PARENT_ID or UPDATED_PARENT_ID
        defaultOrgOrganizationShouldBeFound("parentId.in=" + DEFAULT_PARENT_ID + "," + UPDATED_PARENT_ID);

        // Get all the orgOrganizationList where parentId equals to UPDATED_PARENT_ID
        defaultOrgOrganizationShouldNotBeFound("parentId.in=" + UPDATED_PARENT_ID);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByParentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where parentId is not null
        defaultOrgOrganizationShouldBeFound("parentId.specified=true");

        // Get all the orgOrganizationList where parentId is null
        defaultOrgOrganizationShouldNotBeFound("parentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByParentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where parentId is greater than or equal to DEFAULT_PARENT_ID
        defaultOrgOrganizationShouldBeFound("parentId.greaterThanOrEqual=" + DEFAULT_PARENT_ID);

        // Get all the orgOrganizationList where parentId is greater than or equal to (DEFAULT_PARENT_ID + 1)
        defaultOrgOrganizationShouldNotBeFound("parentId.greaterThanOrEqual=" + (DEFAULT_PARENT_ID + 1));
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByParentIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where parentId is less than or equal to DEFAULT_PARENT_ID
        defaultOrgOrganizationShouldBeFound("parentId.lessThanOrEqual=" + DEFAULT_PARENT_ID);

        // Get all the orgOrganizationList where parentId is less than or equal to SMALLER_PARENT_ID
        defaultOrgOrganizationShouldNotBeFound("parentId.lessThanOrEqual=" + SMALLER_PARENT_ID);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByParentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where parentId is less than DEFAULT_PARENT_ID
        defaultOrgOrganizationShouldNotBeFound("parentId.lessThan=" + DEFAULT_PARENT_ID);

        // Get all the orgOrganizationList where parentId is less than (DEFAULT_PARENT_ID + 1)
        defaultOrgOrganizationShouldBeFound("parentId.lessThan=" + (DEFAULT_PARENT_ID + 1));
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByParentIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where parentId is greater than DEFAULT_PARENT_ID
        defaultOrgOrganizationShouldNotBeFound("parentId.greaterThan=" + DEFAULT_PARENT_ID);

        // Get all the orgOrganizationList where parentId is greater than SMALLER_PARENT_ID
        defaultOrgOrganizationShouldBeFound("parentId.greaterThan=" + SMALLER_PARENT_ID);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgType equals to DEFAULT_ORG_TYPE
        defaultOrgOrganizationShouldBeFound("orgType.equals=" + DEFAULT_ORG_TYPE);

        // Get all the orgOrganizationList where orgType equals to UPDATED_ORG_TYPE
        defaultOrgOrganizationShouldNotBeFound("orgType.equals=" + UPDATED_ORG_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgType not equals to DEFAULT_ORG_TYPE
        defaultOrgOrganizationShouldNotBeFound("orgType.notEquals=" + DEFAULT_ORG_TYPE);

        // Get all the orgOrganizationList where orgType not equals to UPDATED_ORG_TYPE
        defaultOrgOrganizationShouldBeFound("orgType.notEquals=" + UPDATED_ORG_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgTypeIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgType in DEFAULT_ORG_TYPE or UPDATED_ORG_TYPE
        defaultOrgOrganizationShouldBeFound("orgType.in=" + DEFAULT_ORG_TYPE + "," + UPDATED_ORG_TYPE);

        // Get all the orgOrganizationList where orgType equals to UPDATED_ORG_TYPE
        defaultOrgOrganizationShouldNotBeFound("orgType.in=" + UPDATED_ORG_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgType is not null
        defaultOrgOrganizationShouldBeFound("orgType.specified=true");

        // Get all the orgOrganizationList where orgType is null
        defaultOrgOrganizationShouldNotBeFound("orgType.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgType is greater than or equal to DEFAULT_ORG_TYPE
        defaultOrgOrganizationShouldBeFound("orgType.greaterThanOrEqual=" + DEFAULT_ORG_TYPE);

        // Get all the orgOrganizationList where orgType is greater than or equal to (DEFAULT_ORG_TYPE + 1)
        defaultOrgOrganizationShouldNotBeFound("orgType.greaterThanOrEqual=" + (DEFAULT_ORG_TYPE + 1));
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgTypeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgType is less than or equal to DEFAULT_ORG_TYPE
        defaultOrgOrganizationShouldBeFound("orgType.lessThanOrEqual=" + DEFAULT_ORG_TYPE);

        // Get all the orgOrganizationList where orgType is less than or equal to SMALLER_ORG_TYPE
        defaultOrgOrganizationShouldNotBeFound("orgType.lessThanOrEqual=" + SMALLER_ORG_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgType is less than DEFAULT_ORG_TYPE
        defaultOrgOrganizationShouldNotBeFound("orgType.lessThan=" + DEFAULT_ORG_TYPE);

        // Get all the orgOrganizationList where orgType is less than (DEFAULT_ORG_TYPE + 1)
        defaultOrgOrganizationShouldBeFound("orgType.lessThan=" + (DEFAULT_ORG_TYPE + 1));
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgTypeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgType is greater than DEFAULT_ORG_TYPE
        defaultOrgOrganizationShouldNotBeFound("orgType.greaterThan=" + DEFAULT_ORG_TYPE);

        // Get all the orgOrganizationList where orgType is greater than SMALLER_ORG_TYPE
        defaultOrgOrganizationShouldBeFound("orgType.greaterThan=" + SMALLER_ORG_TYPE);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgCode equals to DEFAULT_ORG_CODE
        defaultOrgOrganizationShouldBeFound("orgCode.equals=" + DEFAULT_ORG_CODE);

        // Get all the orgOrganizationList where orgCode equals to UPDATED_ORG_CODE
        defaultOrgOrganizationShouldNotBeFound("orgCode.equals=" + UPDATED_ORG_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgCode not equals to DEFAULT_ORG_CODE
        defaultOrgOrganizationShouldNotBeFound("orgCode.notEquals=" + DEFAULT_ORG_CODE);

        // Get all the orgOrganizationList where orgCode not equals to UPDATED_ORG_CODE
        defaultOrgOrganizationShouldBeFound("orgCode.notEquals=" + UPDATED_ORG_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgCodeIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgCode in DEFAULT_ORG_CODE or UPDATED_ORG_CODE
        defaultOrgOrganizationShouldBeFound("orgCode.in=" + DEFAULT_ORG_CODE + "," + UPDATED_ORG_CODE);

        // Get all the orgOrganizationList where orgCode equals to UPDATED_ORG_CODE
        defaultOrgOrganizationShouldNotBeFound("orgCode.in=" + UPDATED_ORG_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgCode is not null
        defaultOrgOrganizationShouldBeFound("orgCode.specified=true");

        // Get all the orgOrganizationList where orgCode is null
        defaultOrgOrganizationShouldNotBeFound("orgCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgCodeContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgCode contains DEFAULT_ORG_CODE
        defaultOrgOrganizationShouldBeFound("orgCode.contains=" + DEFAULT_ORG_CODE);

        // Get all the orgOrganizationList where orgCode contains UPDATED_ORG_CODE
        defaultOrgOrganizationShouldNotBeFound("orgCode.contains=" + UPDATED_ORG_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgCodeNotContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgCode does not contain DEFAULT_ORG_CODE
        defaultOrgOrganizationShouldNotBeFound("orgCode.doesNotContain=" + DEFAULT_ORG_CODE);

        // Get all the orgOrganizationList where orgCode does not contain UPDATED_ORG_CODE
        defaultOrgOrganizationShouldBeFound("orgCode.doesNotContain=" + UPDATED_ORG_CODE);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgNameIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgName equals to DEFAULT_ORG_NAME
        defaultOrgOrganizationShouldBeFound("orgName.equals=" + DEFAULT_ORG_NAME);

        // Get all the orgOrganizationList where orgName equals to UPDATED_ORG_NAME
        defaultOrgOrganizationShouldNotBeFound("orgName.equals=" + UPDATED_ORG_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgName not equals to DEFAULT_ORG_NAME
        defaultOrgOrganizationShouldNotBeFound("orgName.notEquals=" + DEFAULT_ORG_NAME);

        // Get all the orgOrganizationList where orgName not equals to UPDATED_ORG_NAME
        defaultOrgOrganizationShouldBeFound("orgName.notEquals=" + UPDATED_ORG_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgNameIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgName in DEFAULT_ORG_NAME or UPDATED_ORG_NAME
        defaultOrgOrganizationShouldBeFound("orgName.in=" + DEFAULT_ORG_NAME + "," + UPDATED_ORG_NAME);

        // Get all the orgOrganizationList where orgName equals to UPDATED_ORG_NAME
        defaultOrgOrganizationShouldNotBeFound("orgName.in=" + UPDATED_ORG_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgName is not null
        defaultOrgOrganizationShouldBeFound("orgName.specified=true");

        // Get all the orgOrganizationList where orgName is null
        defaultOrgOrganizationShouldNotBeFound("orgName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgNameContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgName contains DEFAULT_ORG_NAME
        defaultOrgOrganizationShouldBeFound("orgName.contains=" + DEFAULT_ORG_NAME);

        // Get all the orgOrganizationList where orgName contains UPDATED_ORG_NAME
        defaultOrgOrganizationShouldNotBeFound("orgName.contains=" + UPDATED_ORG_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgNameNotContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgName does not contain DEFAULT_ORG_NAME
        defaultOrgOrganizationShouldNotBeFound("orgName.doesNotContain=" + DEFAULT_ORG_NAME);

        // Get all the orgOrganizationList where orgName does not contain UPDATED_ORG_NAME
        defaultOrgOrganizationShouldBeFound("orgName.doesNotContain=" + UPDATED_ORG_NAME);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgLevel equals to DEFAULT_ORG_LEVEL
        defaultOrgOrganizationShouldBeFound("orgLevel.equals=" + DEFAULT_ORG_LEVEL);

        // Get all the orgOrganizationList where orgLevel equals to UPDATED_ORG_LEVEL
        defaultOrgOrganizationShouldNotBeFound("orgLevel.equals=" + UPDATED_ORG_LEVEL);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgLevelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgLevel not equals to DEFAULT_ORG_LEVEL
        defaultOrgOrganizationShouldNotBeFound("orgLevel.notEquals=" + DEFAULT_ORG_LEVEL);

        // Get all the orgOrganizationList where orgLevel not equals to UPDATED_ORG_LEVEL
        defaultOrgOrganizationShouldBeFound("orgLevel.notEquals=" + UPDATED_ORG_LEVEL);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgLevelIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgLevel in DEFAULT_ORG_LEVEL or UPDATED_ORG_LEVEL
        defaultOrgOrganizationShouldBeFound("orgLevel.in=" + DEFAULT_ORG_LEVEL + "," + UPDATED_ORG_LEVEL);

        // Get all the orgOrganizationList where orgLevel equals to UPDATED_ORG_LEVEL
        defaultOrgOrganizationShouldNotBeFound("orgLevel.in=" + UPDATED_ORG_LEVEL);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgLevel is not null
        defaultOrgOrganizationShouldBeFound("orgLevel.specified=true");

        // Get all the orgOrganizationList where orgLevel is null
        defaultOrgOrganizationShouldNotBeFound("orgLevel.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgLevelContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgLevel contains DEFAULT_ORG_LEVEL
        defaultOrgOrganizationShouldBeFound("orgLevel.contains=" + DEFAULT_ORG_LEVEL);

        // Get all the orgOrganizationList where orgLevel contains UPDATED_ORG_LEVEL
        defaultOrgOrganizationShouldNotBeFound("orgLevel.contains=" + UPDATED_ORG_LEVEL);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgLevelNotContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgLevel does not contain DEFAULT_ORG_LEVEL
        defaultOrgOrganizationShouldNotBeFound("orgLevel.doesNotContain=" + DEFAULT_ORG_LEVEL);

        // Get all the orgOrganizationList where orgLevel does not contain UPDATED_ORG_LEVEL
        defaultOrgOrganizationShouldBeFound("orgLevel.doesNotContain=" + UPDATED_ORG_LEVEL);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbNameIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbName equals to DEFAULT_DB_NAME
        defaultOrgOrganizationShouldBeFound("dbName.equals=" + DEFAULT_DB_NAME);

        // Get all the orgOrganizationList where dbName equals to UPDATED_DB_NAME
        defaultOrgOrganizationShouldNotBeFound("dbName.equals=" + UPDATED_DB_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbName not equals to DEFAULT_DB_NAME
        defaultOrgOrganizationShouldNotBeFound("dbName.notEquals=" + DEFAULT_DB_NAME);

        // Get all the orgOrganizationList where dbName not equals to UPDATED_DB_NAME
        defaultOrgOrganizationShouldBeFound("dbName.notEquals=" + UPDATED_DB_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbNameIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbName in DEFAULT_DB_NAME or UPDATED_DB_NAME
        defaultOrgOrganizationShouldBeFound("dbName.in=" + DEFAULT_DB_NAME + "," + UPDATED_DB_NAME);

        // Get all the orgOrganizationList where dbName equals to UPDATED_DB_NAME
        defaultOrgOrganizationShouldNotBeFound("dbName.in=" + UPDATED_DB_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbName is not null
        defaultOrgOrganizationShouldBeFound("dbName.specified=true");

        // Get all the orgOrganizationList where dbName is null
        defaultOrgOrganizationShouldNotBeFound("dbName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOrganizationsByDbNameContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbName contains DEFAULT_DB_NAME
        defaultOrgOrganizationShouldBeFound("dbName.contains=" + DEFAULT_DB_NAME);

        // Get all the orgOrganizationList where dbName contains UPDATED_DB_NAME
        defaultOrgOrganizationShouldNotBeFound("dbName.contains=" + UPDATED_DB_NAME);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbNameNotContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbName does not contain DEFAULT_DB_NAME
        defaultOrgOrganizationShouldNotBeFound("dbName.doesNotContain=" + DEFAULT_DB_NAME);

        // Get all the orgOrganizationList where dbName does not contain UPDATED_DB_NAME
        defaultOrgOrganizationShouldBeFound("dbName.doesNotContain=" + UPDATED_DB_NAME);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbSchemaIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbSchema equals to DEFAULT_DB_SCHEMA
        defaultOrgOrganizationShouldBeFound("dbSchema.equals=" + DEFAULT_DB_SCHEMA);

        // Get all the orgOrganizationList where dbSchema equals to UPDATED_DB_SCHEMA
        defaultOrgOrganizationShouldNotBeFound("dbSchema.equals=" + UPDATED_DB_SCHEMA);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbSchemaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbSchema not equals to DEFAULT_DB_SCHEMA
        defaultOrgOrganizationShouldNotBeFound("dbSchema.notEquals=" + DEFAULT_DB_SCHEMA);

        // Get all the orgOrganizationList where dbSchema not equals to UPDATED_DB_SCHEMA
        defaultOrgOrganizationShouldBeFound("dbSchema.notEquals=" + UPDATED_DB_SCHEMA);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbSchemaIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbSchema in DEFAULT_DB_SCHEMA or UPDATED_DB_SCHEMA
        defaultOrgOrganizationShouldBeFound("dbSchema.in=" + DEFAULT_DB_SCHEMA + "," + UPDATED_DB_SCHEMA);

        // Get all the orgOrganizationList where dbSchema equals to UPDATED_DB_SCHEMA
        defaultOrgOrganizationShouldNotBeFound("dbSchema.in=" + UPDATED_DB_SCHEMA);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbSchemaIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbSchema is not null
        defaultOrgOrganizationShouldBeFound("dbSchema.specified=true");

        // Get all the orgOrganizationList where dbSchema is null
        defaultOrgOrganizationShouldNotBeFound("dbSchema.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOrganizationsByDbSchemaContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbSchema contains DEFAULT_DB_SCHEMA
        defaultOrgOrganizationShouldBeFound("dbSchema.contains=" + DEFAULT_DB_SCHEMA);

        // Get all the orgOrganizationList where dbSchema contains UPDATED_DB_SCHEMA
        defaultOrgOrganizationShouldNotBeFound("dbSchema.contains=" + UPDATED_DB_SCHEMA);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByDbSchemaNotContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where dbSchema does not contain DEFAULT_DB_SCHEMA
        defaultOrgOrganizationShouldNotBeFound("dbSchema.doesNotContain=" + DEFAULT_DB_SCHEMA);

        // Get all the orgOrganizationList where dbSchema does not contain UPDATED_DB_SCHEMA
        defaultOrgOrganizationShouldBeFound("dbSchema.doesNotContain=" + UPDATED_DB_SCHEMA);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByProvinceIdIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where provinceId equals to DEFAULT_PROVINCE_ID
        defaultOrgOrganizationShouldBeFound("provinceId.equals=" + DEFAULT_PROVINCE_ID);

        // Get all the orgOrganizationList where provinceId equals to UPDATED_PROVINCE_ID
        defaultOrgOrganizationShouldNotBeFound("provinceId.equals=" + UPDATED_PROVINCE_ID);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByProvinceIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where provinceId not equals to DEFAULT_PROVINCE_ID
        defaultOrgOrganizationShouldNotBeFound("provinceId.notEquals=" + DEFAULT_PROVINCE_ID);

        // Get all the orgOrganizationList where provinceId not equals to UPDATED_PROVINCE_ID
        defaultOrgOrganizationShouldBeFound("provinceId.notEquals=" + UPDATED_PROVINCE_ID);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByProvinceIdIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where provinceId in DEFAULT_PROVINCE_ID or UPDATED_PROVINCE_ID
        defaultOrgOrganizationShouldBeFound("provinceId.in=" + DEFAULT_PROVINCE_ID + "," + UPDATED_PROVINCE_ID);

        // Get all the orgOrganizationList where provinceId equals to UPDATED_PROVINCE_ID
        defaultOrgOrganizationShouldNotBeFound("provinceId.in=" + UPDATED_PROVINCE_ID);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByProvinceIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where provinceId is not null
        defaultOrgOrganizationShouldBeFound("provinceId.specified=true");

        // Get all the orgOrganizationList where provinceId is null
        defaultOrgOrganizationShouldNotBeFound("provinceId.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByProvinceIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where provinceId is greater than or equal to DEFAULT_PROVINCE_ID
        defaultOrgOrganizationShouldBeFound("provinceId.greaterThanOrEqual=" + DEFAULT_PROVINCE_ID);

        // Get all the orgOrganizationList where provinceId is greater than or equal to (DEFAULT_PROVINCE_ID + 1)
        defaultOrgOrganizationShouldNotBeFound("provinceId.greaterThanOrEqual=" + (DEFAULT_PROVINCE_ID + 1));
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByProvinceIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where provinceId is less than or equal to DEFAULT_PROVINCE_ID
        defaultOrgOrganizationShouldBeFound("provinceId.lessThanOrEqual=" + DEFAULT_PROVINCE_ID);

        // Get all the orgOrganizationList where provinceId is less than or equal to SMALLER_PROVINCE_ID
        defaultOrgOrganizationShouldNotBeFound("provinceId.lessThanOrEqual=" + SMALLER_PROVINCE_ID);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByProvinceIdIsLessThanSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where provinceId is less than DEFAULT_PROVINCE_ID
        defaultOrgOrganizationShouldNotBeFound("provinceId.lessThan=" + DEFAULT_PROVINCE_ID);

        // Get all the orgOrganizationList where provinceId is less than (DEFAULT_PROVINCE_ID + 1)
        defaultOrgOrganizationShouldBeFound("provinceId.lessThan=" + (DEFAULT_PROVINCE_ID + 1));
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByProvinceIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where provinceId is greater than DEFAULT_PROVINCE_ID
        defaultOrgOrganizationShouldNotBeFound("provinceId.greaterThan=" + DEFAULT_PROVINCE_ID);

        // Get all the orgOrganizationList where provinceId is greater than SMALLER_PROVINCE_ID
        defaultOrgOrganizationShouldBeFound("provinceId.greaterThan=" + SMALLER_PROVINCE_ID);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where status equals to DEFAULT_STATUS
        defaultOrgOrganizationShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the orgOrganizationList where status equals to UPDATED_STATUS
        defaultOrgOrganizationShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where status not equals to DEFAULT_STATUS
        defaultOrgOrganizationShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

        // Get all the orgOrganizationList where status not equals to UPDATED_STATUS
        defaultOrgOrganizationShouldBeFound("status.notEquals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultOrgOrganizationShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the orgOrganizationList where status equals to UPDATED_STATUS
        defaultOrgOrganizationShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where status is not null
        defaultOrgOrganizationShouldBeFound("status.specified=true");

        // Get all the orgOrganizationList where status is null
        defaultOrgOrganizationShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where status is greater than or equal to DEFAULT_STATUS
        defaultOrgOrganizationShouldBeFound("status.greaterThanOrEqual=" + DEFAULT_STATUS);

        // Get all the orgOrganizationList where status is greater than or equal to (DEFAULT_STATUS + 1)
        defaultOrgOrganizationShouldNotBeFound("status.greaterThanOrEqual=" + (DEFAULT_STATUS + 1));
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByStatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where status is less than or equal to DEFAULT_STATUS
        defaultOrgOrganizationShouldBeFound("status.lessThanOrEqual=" + DEFAULT_STATUS);

        // Get all the orgOrganizationList where status is less than or equal to SMALLER_STATUS
        defaultOrgOrganizationShouldNotBeFound("status.lessThanOrEqual=" + SMALLER_STATUS);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where status is less than DEFAULT_STATUS
        defaultOrgOrganizationShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the orgOrganizationList where status is less than (DEFAULT_STATUS + 1)
        defaultOrgOrganizationShouldBeFound("status.lessThan=" + (DEFAULT_STATUS + 1));
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByStatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where status is greater than DEFAULT_STATUS
        defaultOrgOrganizationShouldNotBeFound("status.greaterThan=" + DEFAULT_STATUS);

        // Get all the orgOrganizationList where status is greater than SMALLER_STATUS
        defaultOrgOrganizationShouldBeFound("status.greaterThan=" + SMALLER_STATUS);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgAddress equals to DEFAULT_ORG_ADDRESS
        defaultOrgOrganizationShouldBeFound("orgAddress.equals=" + DEFAULT_ORG_ADDRESS);

        // Get all the orgOrganizationList where orgAddress equals to UPDATED_ORG_ADDRESS
        defaultOrgOrganizationShouldNotBeFound("orgAddress.equals=" + UPDATED_ORG_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgAddressIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgAddress not equals to DEFAULT_ORG_ADDRESS
        defaultOrgOrganizationShouldNotBeFound("orgAddress.notEquals=" + DEFAULT_ORG_ADDRESS);

        // Get all the orgOrganizationList where orgAddress not equals to UPDATED_ORG_ADDRESS
        defaultOrgOrganizationShouldBeFound("orgAddress.notEquals=" + UPDATED_ORG_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgAddressIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgAddress in DEFAULT_ORG_ADDRESS or UPDATED_ORG_ADDRESS
        defaultOrgOrganizationShouldBeFound("orgAddress.in=" + DEFAULT_ORG_ADDRESS + "," + UPDATED_ORG_ADDRESS);

        // Get all the orgOrganizationList where orgAddress equals to UPDATED_ORG_ADDRESS
        defaultOrgOrganizationShouldNotBeFound("orgAddress.in=" + UPDATED_ORG_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgAddress is not null
        defaultOrgOrganizationShouldBeFound("orgAddress.specified=true");

        // Get all the orgOrganizationList where orgAddress is null
        defaultOrgOrganizationShouldNotBeFound("orgAddress.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgAddressContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgAddress contains DEFAULT_ORG_ADDRESS
        defaultOrgOrganizationShouldBeFound("orgAddress.contains=" + DEFAULT_ORG_ADDRESS);

        // Get all the orgOrganizationList where orgAddress contains UPDATED_ORG_ADDRESS
        defaultOrgOrganizationShouldNotBeFound("orgAddress.contains=" + UPDATED_ORG_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgAddressNotContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where orgAddress does not contain DEFAULT_ORG_ADDRESS
        defaultOrgOrganizationShouldNotBeFound("orgAddress.doesNotContain=" + DEFAULT_ORG_ADDRESS);

        // Get all the orgOrganizationList where orgAddress does not contain UPDATED_ORG_ADDRESS
        defaultOrgOrganizationShouldBeFound("orgAddress.doesNotContain=" + UPDATED_ORG_ADDRESS);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByHospitalCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where hospitalCode equals to DEFAULT_HOSPITAL_CODE
        defaultOrgOrganizationShouldBeFound("hospitalCode.equals=" + DEFAULT_HOSPITAL_CODE);

        // Get all the orgOrganizationList where hospitalCode equals to UPDATED_HOSPITAL_CODE
        defaultOrgOrganizationShouldNotBeFound("hospitalCode.equals=" + UPDATED_HOSPITAL_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByHospitalCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where hospitalCode not equals to DEFAULT_HOSPITAL_CODE
        defaultOrgOrganizationShouldNotBeFound("hospitalCode.notEquals=" + DEFAULT_HOSPITAL_CODE);

        // Get all the orgOrganizationList where hospitalCode not equals to UPDATED_HOSPITAL_CODE
        defaultOrgOrganizationShouldBeFound("hospitalCode.notEquals=" + UPDATED_HOSPITAL_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByHospitalCodeIsInShouldWork() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where hospitalCode in DEFAULT_HOSPITAL_CODE or UPDATED_HOSPITAL_CODE
        defaultOrgOrganizationShouldBeFound("hospitalCode.in=" + DEFAULT_HOSPITAL_CODE + "," + UPDATED_HOSPITAL_CODE);

        // Get all the orgOrganizationList where hospitalCode equals to UPDATED_HOSPITAL_CODE
        defaultOrgOrganizationShouldNotBeFound("hospitalCode.in=" + UPDATED_HOSPITAL_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByHospitalCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where hospitalCode is not null
        defaultOrgOrganizationShouldBeFound("hospitalCode.specified=true");

        // Get all the orgOrganizationList where hospitalCode is null
        defaultOrgOrganizationShouldNotBeFound("hospitalCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrgOrganizationsByHospitalCodeContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where hospitalCode contains DEFAULT_HOSPITAL_CODE
        defaultOrgOrganizationShouldBeFound("hospitalCode.contains=" + DEFAULT_HOSPITAL_CODE);

        // Get all the orgOrganizationList where hospitalCode contains UPDATED_HOSPITAL_CODE
        defaultOrgOrganizationShouldNotBeFound("hospitalCode.contains=" + UPDATED_HOSPITAL_CODE);
    }

    @Test
    @Transactional
    public void getAllOrgOrganizationsByHospitalCodeNotContainsSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);

        // Get all the orgOrganizationList where hospitalCode does not contain DEFAULT_HOSPITAL_CODE
        defaultOrgOrganizationShouldNotBeFound("hospitalCode.doesNotContain=" + DEFAULT_HOSPITAL_CODE);

        // Get all the orgOrganizationList where hospitalCode does not contain UPDATED_HOSPITAL_CODE
        defaultOrgOrganizationShouldBeFound("hospitalCode.doesNotContain=" + UPDATED_HOSPITAL_CODE);
    }


    @Test
    @Transactional
    public void getAllOrgOrganizationsByOrgOfficerIsEqualToSomething() throws Exception {
        // Initialize the database
        orgOrganizationRepository.saveAndFlush(orgOrganization);
        OrgOfficer orgOfficer = OrgOfficerResourceIT.createEntity(em);
        em.persist(orgOfficer);
        em.flush();
        orgOrganization.addOrgOfficer(orgOfficer);
        orgOrganizationRepository.saveAndFlush(orgOrganization);
        Long orgOfficerId = orgOfficer.getId();

        // Get all the orgOrganizationList where orgOfficer equals to orgOfficerId
        defaultOrgOrganizationShouldBeFound("orgOfficerId.equals=" + orgOfficerId);

        // Get all the orgOrganizationList where orgOfficer equals to orgOfficerId + 1
        defaultOrgOrganizationShouldNotBeFound("orgOfficerId.equals=" + (orgOfficerId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultOrgOrganizationShouldBeFound(String filter) throws Exception {
        restOrgOrganizationMockMvc.perform(get("/api/org-organizations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orgOrganization.getId().intValue())))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].orgType").value(hasItem(DEFAULT_ORG_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].orgCode").value(hasItem(DEFAULT_ORG_CODE)))
            .andExpect(jsonPath("$.[*].orgName").value(hasItem(DEFAULT_ORG_NAME)))
            .andExpect(jsonPath("$.[*].orgLevel").value(hasItem(DEFAULT_ORG_LEVEL)))
            .andExpect(jsonPath("$.[*].dbName").value(hasItem(DEFAULT_DB_NAME)))
            .andExpect(jsonPath("$.[*].dbSchema").value(hasItem(DEFAULT_DB_SCHEMA)))
            .andExpect(jsonPath("$.[*].provinceId").value(hasItem(DEFAULT_PROVINCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].orgAddress").value(hasItem(DEFAULT_ORG_ADDRESS)))
            .andExpect(jsonPath("$.[*].hospitalCode").value(hasItem(DEFAULT_HOSPITAL_CODE)));

        // Check, that the count call also returns 1
        restOrgOrganizationMockMvc.perform(get("/api/org-organizations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultOrgOrganizationShouldNotBeFound(String filter) throws Exception {
        restOrgOrganizationMockMvc.perform(get("/api/org-organizations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOrgOrganizationMockMvc.perform(get("/api/org-organizations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOrgOrganization() throws Exception {
        // Get the orgOrganization
        restOrgOrganizationMockMvc.perform(get("/api/org-organizations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrgOrganization() throws Exception {
        // Initialize the database
        orgOrganizationService.save(orgOrganization);

        int databaseSizeBeforeUpdate = orgOrganizationRepository.findAll().size();

        // Update the orgOrganization
        OrgOrganization updatedOrgOrganization = orgOrganizationRepository.findById(orgOrganization.getId()).get();
        // Disconnect from session so that the updates on updatedOrgOrganization are not directly saved in db
        em.detach(updatedOrgOrganization);
        updatedOrgOrganization
            .parentId(UPDATED_PARENT_ID)
            .orgType(UPDATED_ORG_TYPE)
            .orgCode(UPDATED_ORG_CODE)
            .orgName(UPDATED_ORG_NAME)
            .orgLevel(UPDATED_ORG_LEVEL)
            .dbName(UPDATED_DB_NAME)
            .dbSchema(UPDATED_DB_SCHEMA)
            .provinceId(UPDATED_PROVINCE_ID)
            .status(UPDATED_STATUS)
            .orgAddress(UPDATED_ORG_ADDRESS)
            .hospitalCode(UPDATED_HOSPITAL_CODE);

        restOrgOrganizationMockMvc.perform(put("/api/org-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrgOrganization)))
            .andExpect(status().isOk());

        // Validate the OrgOrganization in the database
        List<OrgOrganization> orgOrganizationList = orgOrganizationRepository.findAll();
        assertThat(orgOrganizationList).hasSize(databaseSizeBeforeUpdate);
        OrgOrganization testOrgOrganization = orgOrganizationList.get(orgOrganizationList.size() - 1);
        assertThat(testOrgOrganization.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testOrgOrganization.getOrgType()).isEqualTo(UPDATED_ORG_TYPE);
        assertThat(testOrgOrganization.getOrgCode()).isEqualTo(UPDATED_ORG_CODE);
        assertThat(testOrgOrganization.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testOrgOrganization.getOrgLevel()).isEqualTo(UPDATED_ORG_LEVEL);
        assertThat(testOrgOrganization.getDbName()).isEqualTo(UPDATED_DB_NAME);
        assertThat(testOrgOrganization.getDbSchema()).isEqualTo(UPDATED_DB_SCHEMA);
        assertThat(testOrgOrganization.getProvinceId()).isEqualTo(UPDATED_PROVINCE_ID);
        assertThat(testOrgOrganization.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOrgOrganization.getOrgAddress()).isEqualTo(UPDATED_ORG_ADDRESS);
        assertThat(testOrgOrganization.getHospitalCode()).isEqualTo(UPDATED_HOSPITAL_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrgOrganization() throws Exception {
        int databaseSizeBeforeUpdate = orgOrganizationRepository.findAll().size();

        // Create the OrgOrganization

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrgOrganizationMockMvc.perform(put("/api/org-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgOrganization)))
            .andExpect(status().isBadRequest());

        // Validate the OrgOrganization in the database
        List<OrgOrganization> orgOrganizationList = orgOrganizationRepository.findAll();
        assertThat(orgOrganizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrgOrganization() throws Exception {
        // Initialize the database
        orgOrganizationService.save(orgOrganization);

        int databaseSizeBeforeDelete = orgOrganizationRepository.findAll().size();

        // Delete the orgOrganization
        restOrgOrganizationMockMvc.perform(delete("/api/org-organizations/{id}", orgOrganization.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrgOrganization> orgOrganizationList = orgOrganizationRepository.findAll();
        assertThat(orgOrganizationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
