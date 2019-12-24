package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtTaiChinh;
import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.repository.CdtTaiChinhRepository;
import com.vnpt.cdt.service.CdtTaiChinhService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtTaiChinhCriteria;
import com.vnpt.cdt.service.CdtTaiChinhQueryService;

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
 * Integration tests for the {@link CdtTaiChinhResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtTaiChinhResourceIT {

    private static final String DEFAULT_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_LOAI = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_TIEN = 1L;
    private static final Long UPDATED_SO_TIEN = 2L;
    private static final Long SMALLER_SO_TIEN = 1L - 1L;

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    @Autowired
    private CdtTaiChinhRepository cdtTaiChinhRepository;

    @Autowired
    private CdtTaiChinhService cdtTaiChinhService;

    @Autowired
    private CdtTaiChinhQueryService cdtTaiChinhQueryService;

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

    private MockMvc restCdtTaiChinhMockMvc;

    private CdtTaiChinh cdtTaiChinh;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtTaiChinhResource cdtTaiChinhResource = new CdtTaiChinhResource(cdtTaiChinhService, cdtTaiChinhQueryService);
        this.restCdtTaiChinhMockMvc = MockMvcBuilders.standaloneSetup(cdtTaiChinhResource)
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
    public static CdtTaiChinh createEntity(EntityManager em) {
        CdtTaiChinh cdtTaiChinh = new CdtTaiChinh()
            .loai(DEFAULT_LOAI)
            .soTien(DEFAULT_SO_TIEN)
            .csytid(DEFAULT_CSYTID);
        return cdtTaiChinh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtTaiChinh createUpdatedEntity(EntityManager em) {
        CdtTaiChinh cdtTaiChinh = new CdtTaiChinh()
            .loai(UPDATED_LOAI)
            .soTien(UPDATED_SO_TIEN)
            .csytid(UPDATED_CSYTID);
        return cdtTaiChinh;
    }

    @BeforeEach
    public void initTest() {
        cdtTaiChinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtTaiChinh() throws Exception {
        int databaseSizeBeforeCreate = cdtTaiChinhRepository.findAll().size();

        // Create the CdtTaiChinh
        restCdtTaiChinhMockMvc.perform(post("/api/cdt-tai-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtTaiChinh)))
            .andExpect(status().isCreated());

        // Validate the CdtTaiChinh in the database
        List<CdtTaiChinh> cdtTaiChinhList = cdtTaiChinhRepository.findAll();
        assertThat(cdtTaiChinhList).hasSize(databaseSizeBeforeCreate + 1);
        CdtTaiChinh testCdtTaiChinh = cdtTaiChinhList.get(cdtTaiChinhList.size() - 1);
        assertThat(testCdtTaiChinh.getLoai()).isEqualTo(DEFAULT_LOAI);
        assertThat(testCdtTaiChinh.getSoTien()).isEqualTo(DEFAULT_SO_TIEN);
        assertThat(testCdtTaiChinh.getCsytid()).isEqualTo(DEFAULT_CSYTID);
    }

    @Test
    @Transactional
    public void createCdtTaiChinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtTaiChinhRepository.findAll().size();

        // Create the CdtTaiChinh with an existing ID
        cdtTaiChinh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtTaiChinhMockMvc.perform(post("/api/cdt-tai-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtTaiChinh)))
            .andExpect(status().isBadRequest());

        // Validate the CdtTaiChinh in the database
        List<CdtTaiChinh> cdtTaiChinhList = cdtTaiChinhRepository.findAll();
        assertThat(cdtTaiChinhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtTaiChinhs() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList
        restCdtTaiChinhMockMvc.perform(get("/api/cdt-tai-chinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtTaiChinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].soTien").value(hasItem(DEFAULT_SO_TIEN.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtTaiChinh() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get the cdtTaiChinh
        restCdtTaiChinhMockMvc.perform(get("/api/cdt-tai-chinhs/{id}", cdtTaiChinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtTaiChinh.getId().intValue()))
            .andExpect(jsonPath("$.loai").value(DEFAULT_LOAI))
            .andExpect(jsonPath("$.soTien").value(DEFAULT_SO_TIEN.intValue()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()));
    }


    @Test
    @Transactional
    public void getCdtTaiChinhsByIdFiltering() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        Long id = cdtTaiChinh.getId();

        defaultCdtTaiChinhShouldBeFound("id.equals=" + id);
        defaultCdtTaiChinhShouldNotBeFound("id.notEquals=" + id);

        defaultCdtTaiChinhShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtTaiChinhShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtTaiChinhShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtTaiChinhShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtTaiChinhsByLoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where loai equals to DEFAULT_LOAI
        defaultCdtTaiChinhShouldBeFound("loai.equals=" + DEFAULT_LOAI);

        // Get all the cdtTaiChinhList where loai equals to UPDATED_LOAI
        defaultCdtTaiChinhShouldNotBeFound("loai.equals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByLoaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where loai not equals to DEFAULT_LOAI
        defaultCdtTaiChinhShouldNotBeFound("loai.notEquals=" + DEFAULT_LOAI);

        // Get all the cdtTaiChinhList where loai not equals to UPDATED_LOAI
        defaultCdtTaiChinhShouldBeFound("loai.notEquals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByLoaiIsInShouldWork() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where loai in DEFAULT_LOAI or UPDATED_LOAI
        defaultCdtTaiChinhShouldBeFound("loai.in=" + DEFAULT_LOAI + "," + UPDATED_LOAI);

        // Get all the cdtTaiChinhList where loai equals to UPDATED_LOAI
        defaultCdtTaiChinhShouldNotBeFound("loai.in=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByLoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where loai is not null
        defaultCdtTaiChinhShouldBeFound("loai.specified=true");

        // Get all the cdtTaiChinhList where loai is null
        defaultCdtTaiChinhShouldNotBeFound("loai.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtTaiChinhsByLoaiContainsSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where loai contains DEFAULT_LOAI
        defaultCdtTaiChinhShouldBeFound("loai.contains=" + DEFAULT_LOAI);

        // Get all the cdtTaiChinhList where loai contains UPDATED_LOAI
        defaultCdtTaiChinhShouldNotBeFound("loai.contains=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByLoaiNotContainsSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where loai does not contain DEFAULT_LOAI
        defaultCdtTaiChinhShouldNotBeFound("loai.doesNotContain=" + DEFAULT_LOAI);

        // Get all the cdtTaiChinhList where loai does not contain UPDATED_LOAI
        defaultCdtTaiChinhShouldBeFound("loai.doesNotContain=" + UPDATED_LOAI);
    }


    @Test
    @Transactional
    public void getAllCdtTaiChinhsBySoTienIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where soTien equals to DEFAULT_SO_TIEN
        defaultCdtTaiChinhShouldBeFound("soTien.equals=" + DEFAULT_SO_TIEN);

        // Get all the cdtTaiChinhList where soTien equals to UPDATED_SO_TIEN
        defaultCdtTaiChinhShouldNotBeFound("soTien.equals=" + UPDATED_SO_TIEN);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsBySoTienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where soTien not equals to DEFAULT_SO_TIEN
        defaultCdtTaiChinhShouldNotBeFound("soTien.notEquals=" + DEFAULT_SO_TIEN);

        // Get all the cdtTaiChinhList where soTien not equals to UPDATED_SO_TIEN
        defaultCdtTaiChinhShouldBeFound("soTien.notEquals=" + UPDATED_SO_TIEN);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsBySoTienIsInShouldWork() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where soTien in DEFAULT_SO_TIEN or UPDATED_SO_TIEN
        defaultCdtTaiChinhShouldBeFound("soTien.in=" + DEFAULT_SO_TIEN + "," + UPDATED_SO_TIEN);

        // Get all the cdtTaiChinhList where soTien equals to UPDATED_SO_TIEN
        defaultCdtTaiChinhShouldNotBeFound("soTien.in=" + UPDATED_SO_TIEN);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsBySoTienIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where soTien is not null
        defaultCdtTaiChinhShouldBeFound("soTien.specified=true");

        // Get all the cdtTaiChinhList where soTien is null
        defaultCdtTaiChinhShouldNotBeFound("soTien.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsBySoTienIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where soTien is greater than or equal to DEFAULT_SO_TIEN
        defaultCdtTaiChinhShouldBeFound("soTien.greaterThanOrEqual=" + DEFAULT_SO_TIEN);

        // Get all the cdtTaiChinhList where soTien is greater than or equal to UPDATED_SO_TIEN
        defaultCdtTaiChinhShouldNotBeFound("soTien.greaterThanOrEqual=" + UPDATED_SO_TIEN);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsBySoTienIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where soTien is less than or equal to DEFAULT_SO_TIEN
        defaultCdtTaiChinhShouldBeFound("soTien.lessThanOrEqual=" + DEFAULT_SO_TIEN);

        // Get all the cdtTaiChinhList where soTien is less than or equal to SMALLER_SO_TIEN
        defaultCdtTaiChinhShouldNotBeFound("soTien.lessThanOrEqual=" + SMALLER_SO_TIEN);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsBySoTienIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where soTien is less than DEFAULT_SO_TIEN
        defaultCdtTaiChinhShouldNotBeFound("soTien.lessThan=" + DEFAULT_SO_TIEN);

        // Get all the cdtTaiChinhList where soTien is less than UPDATED_SO_TIEN
        defaultCdtTaiChinhShouldBeFound("soTien.lessThan=" + UPDATED_SO_TIEN);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsBySoTienIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where soTien is greater than DEFAULT_SO_TIEN
        defaultCdtTaiChinhShouldNotBeFound("soTien.greaterThan=" + DEFAULT_SO_TIEN);

        // Get all the cdtTaiChinhList where soTien is greater than SMALLER_SO_TIEN
        defaultCdtTaiChinhShouldBeFound("soTien.greaterThan=" + SMALLER_SO_TIEN);
    }


    @Test
    @Transactional
    public void getAllCdtTaiChinhsByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where csytid equals to DEFAULT_CSYTID
        defaultCdtTaiChinhShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the cdtTaiChinhList where csytid equals to UPDATED_CSYTID
        defaultCdtTaiChinhShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where csytid not equals to DEFAULT_CSYTID
        defaultCdtTaiChinhShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the cdtTaiChinhList where csytid not equals to UPDATED_CSYTID
        defaultCdtTaiChinhShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultCdtTaiChinhShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the cdtTaiChinhList where csytid equals to UPDATED_CSYTID
        defaultCdtTaiChinhShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where csytid is not null
        defaultCdtTaiChinhShouldBeFound("csytid.specified=true");

        // Get all the cdtTaiChinhList where csytid is null
        defaultCdtTaiChinhShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultCdtTaiChinhShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtTaiChinhList where csytid is greater than or equal to UPDATED_CSYTID
        defaultCdtTaiChinhShouldNotBeFound("csytid.greaterThanOrEqual=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where csytid is less than or equal to DEFAULT_CSYTID
        defaultCdtTaiChinhShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the cdtTaiChinhList where csytid is less than or equal to SMALLER_CSYTID
        defaultCdtTaiChinhShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where csytid is less than DEFAULT_CSYTID
        defaultCdtTaiChinhShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the cdtTaiChinhList where csytid is less than UPDATED_CSYTID
        defaultCdtTaiChinhShouldBeFound("csytid.lessThan=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllCdtTaiChinhsByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);

        // Get all the cdtTaiChinhList where csytid is greater than DEFAULT_CSYTID
        defaultCdtTaiChinhShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the cdtTaiChinhList where csytid is greater than SMALLER_CSYTID
        defaultCdtTaiChinhShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllCdtTaiChinhsByCdtChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);
        CdtChiDaoTuyen cdtChiDaoTuyen = CdtChiDaoTuyenResourceIT.createEntity(em);
        em.persist(cdtChiDaoTuyen);
        em.flush();
        cdtTaiChinh.setCdtChiDaoTuyen(cdtChiDaoTuyen);
        cdtTaiChinhRepository.saveAndFlush(cdtTaiChinh);
        Long cdtChiDaoTuyenId = cdtChiDaoTuyen.getId();

        // Get all the cdtTaiChinhList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId
        defaultCdtTaiChinhShouldBeFound("cdtChiDaoTuyenId.equals=" + cdtChiDaoTuyenId);

        // Get all the cdtTaiChinhList where cdtChiDaoTuyen equals to cdtChiDaoTuyenId + 1
        defaultCdtTaiChinhShouldNotBeFound("cdtChiDaoTuyenId.equals=" + (cdtChiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtTaiChinhShouldBeFound(String filter) throws Exception {
        restCdtTaiChinhMockMvc.perform(get("/api/cdt-tai-chinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtTaiChinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].soTien").value(hasItem(DEFAULT_SO_TIEN.intValue())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())));

        // Check, that the count call also returns 1
        restCdtTaiChinhMockMvc.perform(get("/api/cdt-tai-chinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtTaiChinhShouldNotBeFound(String filter) throws Exception {
        restCdtTaiChinhMockMvc.perform(get("/api/cdt-tai-chinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtTaiChinhMockMvc.perform(get("/api/cdt-tai-chinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtTaiChinh() throws Exception {
        // Get the cdtTaiChinh
        restCdtTaiChinhMockMvc.perform(get("/api/cdt-tai-chinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtTaiChinh() throws Exception {
        // Initialize the database
        cdtTaiChinhService.save(cdtTaiChinh);

        int databaseSizeBeforeUpdate = cdtTaiChinhRepository.findAll().size();

        // Update the cdtTaiChinh
        CdtTaiChinh updatedCdtTaiChinh = cdtTaiChinhRepository.findById(cdtTaiChinh.getId()).get();
        // Disconnect from session so that the updates on updatedCdtTaiChinh are not directly saved in db
        em.detach(updatedCdtTaiChinh);
        updatedCdtTaiChinh
            .loai(UPDATED_LOAI)
            .soTien(UPDATED_SO_TIEN)
            .csytid(UPDATED_CSYTID);

        restCdtTaiChinhMockMvc.perform(put("/api/cdt-tai-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtTaiChinh)))
            .andExpect(status().isOk());

        // Validate the CdtTaiChinh in the database
        List<CdtTaiChinh> cdtTaiChinhList = cdtTaiChinhRepository.findAll();
        assertThat(cdtTaiChinhList).hasSize(databaseSizeBeforeUpdate);
        CdtTaiChinh testCdtTaiChinh = cdtTaiChinhList.get(cdtTaiChinhList.size() - 1);
        assertThat(testCdtTaiChinh.getLoai()).isEqualTo(UPDATED_LOAI);
        assertThat(testCdtTaiChinh.getSoTien()).isEqualTo(UPDATED_SO_TIEN);
        assertThat(testCdtTaiChinh.getCsytid()).isEqualTo(UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtTaiChinh() throws Exception {
        int databaseSizeBeforeUpdate = cdtTaiChinhRepository.findAll().size();

        // Create the CdtTaiChinh

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtTaiChinhMockMvc.perform(put("/api/cdt-tai-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtTaiChinh)))
            .andExpect(status().isBadRequest());

        // Validate the CdtTaiChinh in the database
        List<CdtTaiChinh> cdtTaiChinhList = cdtTaiChinhRepository.findAll();
        assertThat(cdtTaiChinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtTaiChinh() throws Exception {
        // Initialize the database
        cdtTaiChinhService.save(cdtTaiChinh);

        int databaseSizeBeforeDelete = cdtTaiChinhRepository.findAll().size();

        // Delete the cdtTaiChinh
        restCdtTaiChinhMockMvc.perform(delete("/api/cdt-tai-chinhs/{id}", cdtTaiChinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtTaiChinh> cdtTaiChinhList = cdtTaiChinhRepository.findAll();
        assertThat(cdtTaiChinhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
