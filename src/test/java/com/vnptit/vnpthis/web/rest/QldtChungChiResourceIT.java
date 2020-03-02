package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtChungChi;
import com.vnptit.vnpthis.domain.qldt.QldtDmChungchi;
import com.vnptit.vnpthis.repository.qldt.QldtChungChiRepository;
import com.vnptit.vnpthis.service.qldt.QldtChungChiService;
import com.vnptit.vnpthis.service.dto.QldtChungChiDTO;
import com.vnptit.vnpthis.service.mapper.QldtChungChiMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtChungChiResource;
import com.vnptit.vnpthis.service.dto.QldtChungChiCriteria;
import com.vnptit.vnpthis.service.qldt.QldtChungChiQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.vnptit.vnpthis.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QldtChungChiResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtChungChiResourceIT {

    private static final Integer DEFAULT_HAN = 1;
    private static final Integer UPDATED_HAN = 2;
    private static final Integer SMALLER_HAN = 1 - 1;

    private static final LocalDate DEFAULT_NGAYCAP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYCAP = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYCAP = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAYHETHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYHETHAN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYHETHAN = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_URLCHUNGCHI = "AAAAAAAAAA";
    private static final String UPDATED_URLCHUNGCHI = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtChungChiRepository qldtChungChiRepository;

    @Autowired
    private QldtChungChiMapper qldtChungChiMapper;

    @Autowired
    private QldtChungChiService qldtChungChiService;

    @Autowired
    private QldtChungChiQueryService qldtChungChiQueryService;

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

    private MockMvc restQldtChungChiMockMvc;

    private QldtChungChi qldtChungChi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtChungChiResource qldtChungChiResource = new QldtChungChiResource(qldtChungChiService, qldtChungChiQueryService);
        this.restQldtChungChiMockMvc = MockMvcBuilders.standaloneSetup(qldtChungChiResource)
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
    public static QldtChungChi createEntity(EntityManager em) {
        QldtChungChi qldtChungChi = new QldtChungChi()
            .han(DEFAULT_HAN)
            .ngaycap(DEFAULT_NGAYCAP)
            .ngayhethan(DEFAULT_NGAYHETHAN)
            .urlchungchi(DEFAULT_URLCHUNGCHI)
            .sudung(DEFAULT_SUDUNG);
        return qldtChungChi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtChungChi createUpdatedEntity(EntityManager em) {
        QldtChungChi qldtChungChi = new QldtChungChi()
            .han(UPDATED_HAN)
            .ngaycap(UPDATED_NGAYCAP)
            .ngayhethan(UPDATED_NGAYHETHAN)
            .urlchungchi(UPDATED_URLCHUNGCHI)
            .sudung(UPDATED_SUDUNG);
        return qldtChungChi;
    }

    @BeforeEach
    public void initTest() {
        qldtChungChi = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtChungChi() throws Exception {
        int databaseSizeBeforeCreate = qldtChungChiRepository.findAll().size();

        // Create the QldtChungChi
        QldtChungChiDTO qldtChungChiDTO = qldtChungChiMapper.toDto(qldtChungChi);
        restQldtChungChiMockMvc.perform(post("/api/qldt-chung-chis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtChungChiDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtChungChi in the database
        List<QldtChungChi> qldtChungChiList = qldtChungChiRepository.findAll();
        assertThat(qldtChungChiList).hasSize(databaseSizeBeforeCreate + 1);
        QldtChungChi testQldtChungChi = qldtChungChiList.get(qldtChungChiList.size() - 1);
        assertThat(testQldtChungChi.getHan()).isEqualTo(DEFAULT_HAN);
        assertThat(testQldtChungChi.getNgaycap()).isEqualTo(DEFAULT_NGAYCAP);
        assertThat(testQldtChungChi.getNgayhethan()).isEqualTo(DEFAULT_NGAYHETHAN);
        assertThat(testQldtChungChi.getUrlchungchi()).isEqualTo(DEFAULT_URLCHUNGCHI);
        assertThat(testQldtChungChi.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtChungChiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtChungChiRepository.findAll().size();

        // Create the QldtChungChi with an existing ID
        qldtChungChi.setId(1L);
        QldtChungChiDTO qldtChungChiDTO = qldtChungChiMapper.toDto(qldtChungChi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtChungChiMockMvc.perform(post("/api/qldt-chung-chis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtChungChiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtChungChi in the database
        List<QldtChungChi> qldtChungChiList = qldtChungChiRepository.findAll();
        assertThat(qldtChungChiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtChungChis() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList
        restQldtChungChiMockMvc.perform(get("/api/qldt-chung-chis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtChungChi.getId().intValue())))
            .andExpect(jsonPath("$.[*].han").value(hasItem(DEFAULT_HAN)))
            .andExpect(jsonPath("$.[*].ngaycap").value(hasItem(DEFAULT_NGAYCAP.toString())))
            .andExpect(jsonPath("$.[*].ngayhethan").value(hasItem(DEFAULT_NGAYHETHAN.toString())))
            .andExpect(jsonPath("$.[*].urlchungchi").value(hasItem(DEFAULT_URLCHUNGCHI)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtChungChi() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get the qldtChungChi
        restQldtChungChiMockMvc.perform(get("/api/qldt-chung-chis/{id}", qldtChungChi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtChungChi.getId().intValue()))
            .andExpect(jsonPath("$.han").value(DEFAULT_HAN))
            .andExpect(jsonPath("$.ngaycap").value(DEFAULT_NGAYCAP.toString()))
            .andExpect(jsonPath("$.ngayhethan").value(DEFAULT_NGAYHETHAN.toString()))
            .andExpect(jsonPath("$.urlchungchi").value(DEFAULT_URLCHUNGCHI))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtChungChisByIdFiltering() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        Long id = qldtChungChi.getId();

        defaultQldtChungChiShouldBeFound("id.equals=" + id);
        defaultQldtChungChiShouldNotBeFound("id.notEquals=" + id);

        defaultQldtChungChiShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtChungChiShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtChungChiShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtChungChiShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtChungChisByHanIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where han equals to DEFAULT_HAN
        defaultQldtChungChiShouldBeFound("han.equals=" + DEFAULT_HAN);

        // Get all the qldtChungChiList where han equals to UPDATED_HAN
        defaultQldtChungChiShouldNotBeFound("han.equals=" + UPDATED_HAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByHanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where han not equals to DEFAULT_HAN
        defaultQldtChungChiShouldNotBeFound("han.notEquals=" + DEFAULT_HAN);

        // Get all the qldtChungChiList where han not equals to UPDATED_HAN
        defaultQldtChungChiShouldBeFound("han.notEquals=" + UPDATED_HAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByHanIsInShouldWork() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where han in DEFAULT_HAN or UPDATED_HAN
        defaultQldtChungChiShouldBeFound("han.in=" + DEFAULT_HAN + "," + UPDATED_HAN);

        // Get all the qldtChungChiList where han equals to UPDATED_HAN
        defaultQldtChungChiShouldNotBeFound("han.in=" + UPDATED_HAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByHanIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where han is not null
        defaultQldtChungChiShouldBeFound("han.specified=true");

        // Get all the qldtChungChiList where han is null
        defaultQldtChungChiShouldNotBeFound("han.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByHanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where han is greater than or equal to DEFAULT_HAN
        defaultQldtChungChiShouldBeFound("han.greaterThanOrEqual=" + DEFAULT_HAN);

        // Get all the qldtChungChiList where han is greater than or equal to UPDATED_HAN
        defaultQldtChungChiShouldNotBeFound("han.greaterThanOrEqual=" + UPDATED_HAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByHanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where han is less than or equal to DEFAULT_HAN
        defaultQldtChungChiShouldBeFound("han.lessThanOrEqual=" + DEFAULT_HAN);

        // Get all the qldtChungChiList where han is less than or equal to SMALLER_HAN
        defaultQldtChungChiShouldNotBeFound("han.lessThanOrEqual=" + SMALLER_HAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByHanIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where han is less than DEFAULT_HAN
        defaultQldtChungChiShouldNotBeFound("han.lessThan=" + DEFAULT_HAN);

        // Get all the qldtChungChiList where han is less than UPDATED_HAN
        defaultQldtChungChiShouldBeFound("han.lessThan=" + UPDATED_HAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByHanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where han is greater than DEFAULT_HAN
        defaultQldtChungChiShouldNotBeFound("han.greaterThan=" + DEFAULT_HAN);

        // Get all the qldtChungChiList where han is greater than SMALLER_HAN
        defaultQldtChungChiShouldBeFound("han.greaterThan=" + SMALLER_HAN);
    }


    @Test
    @Transactional
    public void getAllQldtChungChisByNgaycapIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngaycap equals to DEFAULT_NGAYCAP
        defaultQldtChungChiShouldBeFound("ngaycap.equals=" + DEFAULT_NGAYCAP);

        // Get all the qldtChungChiList where ngaycap equals to UPDATED_NGAYCAP
        defaultQldtChungChiShouldNotBeFound("ngaycap.equals=" + UPDATED_NGAYCAP);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgaycapIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngaycap not equals to DEFAULT_NGAYCAP
        defaultQldtChungChiShouldNotBeFound("ngaycap.notEquals=" + DEFAULT_NGAYCAP);

        // Get all the qldtChungChiList where ngaycap not equals to UPDATED_NGAYCAP
        defaultQldtChungChiShouldBeFound("ngaycap.notEquals=" + UPDATED_NGAYCAP);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgaycapIsInShouldWork() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngaycap in DEFAULT_NGAYCAP or UPDATED_NGAYCAP
        defaultQldtChungChiShouldBeFound("ngaycap.in=" + DEFAULT_NGAYCAP + "," + UPDATED_NGAYCAP);

        // Get all the qldtChungChiList where ngaycap equals to UPDATED_NGAYCAP
        defaultQldtChungChiShouldNotBeFound("ngaycap.in=" + UPDATED_NGAYCAP);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgaycapIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngaycap is not null
        defaultQldtChungChiShouldBeFound("ngaycap.specified=true");

        // Get all the qldtChungChiList where ngaycap is null
        defaultQldtChungChiShouldNotBeFound("ngaycap.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgaycapIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngaycap is greater than or equal to DEFAULT_NGAYCAP
        defaultQldtChungChiShouldBeFound("ngaycap.greaterThanOrEqual=" + DEFAULT_NGAYCAP);

        // Get all the qldtChungChiList where ngaycap is greater than or equal to UPDATED_NGAYCAP
        defaultQldtChungChiShouldNotBeFound("ngaycap.greaterThanOrEqual=" + UPDATED_NGAYCAP);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgaycapIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngaycap is less than or equal to DEFAULT_NGAYCAP
        defaultQldtChungChiShouldBeFound("ngaycap.lessThanOrEqual=" + DEFAULT_NGAYCAP);

        // Get all the qldtChungChiList where ngaycap is less than or equal to SMALLER_NGAYCAP
        defaultQldtChungChiShouldNotBeFound("ngaycap.lessThanOrEqual=" + SMALLER_NGAYCAP);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgaycapIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngaycap is less than DEFAULT_NGAYCAP
        defaultQldtChungChiShouldNotBeFound("ngaycap.lessThan=" + DEFAULT_NGAYCAP);

        // Get all the qldtChungChiList where ngaycap is less than UPDATED_NGAYCAP
        defaultQldtChungChiShouldBeFound("ngaycap.lessThan=" + UPDATED_NGAYCAP);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgaycapIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngaycap is greater than DEFAULT_NGAYCAP
        defaultQldtChungChiShouldNotBeFound("ngaycap.greaterThan=" + DEFAULT_NGAYCAP);

        // Get all the qldtChungChiList where ngaycap is greater than SMALLER_NGAYCAP
        defaultQldtChungChiShouldBeFound("ngaycap.greaterThan=" + SMALLER_NGAYCAP);
    }


    @Test
    @Transactional
    public void getAllQldtChungChisByNgayhethanIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngayhethan equals to DEFAULT_NGAYHETHAN
        defaultQldtChungChiShouldBeFound("ngayhethan.equals=" + DEFAULT_NGAYHETHAN);

        // Get all the qldtChungChiList where ngayhethan equals to UPDATED_NGAYHETHAN
        defaultQldtChungChiShouldNotBeFound("ngayhethan.equals=" + UPDATED_NGAYHETHAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgayhethanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngayhethan not equals to DEFAULT_NGAYHETHAN
        defaultQldtChungChiShouldNotBeFound("ngayhethan.notEquals=" + DEFAULT_NGAYHETHAN);

        // Get all the qldtChungChiList where ngayhethan not equals to UPDATED_NGAYHETHAN
        defaultQldtChungChiShouldBeFound("ngayhethan.notEquals=" + UPDATED_NGAYHETHAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgayhethanIsInShouldWork() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngayhethan in DEFAULT_NGAYHETHAN or UPDATED_NGAYHETHAN
        defaultQldtChungChiShouldBeFound("ngayhethan.in=" + DEFAULT_NGAYHETHAN + "," + UPDATED_NGAYHETHAN);

        // Get all the qldtChungChiList where ngayhethan equals to UPDATED_NGAYHETHAN
        defaultQldtChungChiShouldNotBeFound("ngayhethan.in=" + UPDATED_NGAYHETHAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgayhethanIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngayhethan is not null
        defaultQldtChungChiShouldBeFound("ngayhethan.specified=true");

        // Get all the qldtChungChiList where ngayhethan is null
        defaultQldtChungChiShouldNotBeFound("ngayhethan.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgayhethanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngayhethan is greater than or equal to DEFAULT_NGAYHETHAN
        defaultQldtChungChiShouldBeFound("ngayhethan.greaterThanOrEqual=" + DEFAULT_NGAYHETHAN);

        // Get all the qldtChungChiList where ngayhethan is greater than or equal to UPDATED_NGAYHETHAN
        defaultQldtChungChiShouldNotBeFound("ngayhethan.greaterThanOrEqual=" + UPDATED_NGAYHETHAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgayhethanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngayhethan is less than or equal to DEFAULT_NGAYHETHAN
        defaultQldtChungChiShouldBeFound("ngayhethan.lessThanOrEqual=" + DEFAULT_NGAYHETHAN);

        // Get all the qldtChungChiList where ngayhethan is less than or equal to SMALLER_NGAYHETHAN
        defaultQldtChungChiShouldNotBeFound("ngayhethan.lessThanOrEqual=" + SMALLER_NGAYHETHAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgayhethanIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngayhethan is less than DEFAULT_NGAYHETHAN
        defaultQldtChungChiShouldNotBeFound("ngayhethan.lessThan=" + DEFAULT_NGAYHETHAN);

        // Get all the qldtChungChiList where ngayhethan is less than UPDATED_NGAYHETHAN
        defaultQldtChungChiShouldBeFound("ngayhethan.lessThan=" + UPDATED_NGAYHETHAN);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByNgayhethanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where ngayhethan is greater than DEFAULT_NGAYHETHAN
        defaultQldtChungChiShouldNotBeFound("ngayhethan.greaterThan=" + DEFAULT_NGAYHETHAN);

        // Get all the qldtChungChiList where ngayhethan is greater than SMALLER_NGAYHETHAN
        defaultQldtChungChiShouldBeFound("ngayhethan.greaterThan=" + SMALLER_NGAYHETHAN);
    }


    @Test
    @Transactional
    public void getAllQldtChungChisByUrlchungchiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where urlchungchi equals to DEFAULT_URLCHUNGCHI
        defaultQldtChungChiShouldBeFound("urlchungchi.equals=" + DEFAULT_URLCHUNGCHI);

        // Get all the qldtChungChiList where urlchungchi equals to UPDATED_URLCHUNGCHI
        defaultQldtChungChiShouldNotBeFound("urlchungchi.equals=" + UPDATED_URLCHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByUrlchungchiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where urlchungchi not equals to DEFAULT_URLCHUNGCHI
        defaultQldtChungChiShouldNotBeFound("urlchungchi.notEquals=" + DEFAULT_URLCHUNGCHI);

        // Get all the qldtChungChiList where urlchungchi not equals to UPDATED_URLCHUNGCHI
        defaultQldtChungChiShouldBeFound("urlchungchi.notEquals=" + UPDATED_URLCHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByUrlchungchiIsInShouldWork() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where urlchungchi in DEFAULT_URLCHUNGCHI or UPDATED_URLCHUNGCHI
        defaultQldtChungChiShouldBeFound("urlchungchi.in=" + DEFAULT_URLCHUNGCHI + "," + UPDATED_URLCHUNGCHI);

        // Get all the qldtChungChiList where urlchungchi equals to UPDATED_URLCHUNGCHI
        defaultQldtChungChiShouldNotBeFound("urlchungchi.in=" + UPDATED_URLCHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByUrlchungchiIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where urlchungchi is not null
        defaultQldtChungChiShouldBeFound("urlchungchi.specified=true");

        // Get all the qldtChungChiList where urlchungchi is null
        defaultQldtChungChiShouldNotBeFound("urlchungchi.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtChungChisByUrlchungchiContainsSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where urlchungchi contains DEFAULT_URLCHUNGCHI
        defaultQldtChungChiShouldBeFound("urlchungchi.contains=" + DEFAULT_URLCHUNGCHI);

        // Get all the qldtChungChiList where urlchungchi contains UPDATED_URLCHUNGCHI
        defaultQldtChungChiShouldNotBeFound("urlchungchi.contains=" + UPDATED_URLCHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisByUrlchungchiNotContainsSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where urlchungchi does not contain DEFAULT_URLCHUNGCHI
        defaultQldtChungChiShouldNotBeFound("urlchungchi.doesNotContain=" + DEFAULT_URLCHUNGCHI);

        // Get all the qldtChungChiList where urlchungchi does not contain UPDATED_URLCHUNGCHI
        defaultQldtChungChiShouldBeFound("urlchungchi.doesNotContain=" + UPDATED_URLCHUNGCHI);
    }


    @Test
    @Transactional
    public void getAllQldtChungChisBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where sudung equals to DEFAULT_SUDUNG
        defaultQldtChungChiShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtChungChiList where sudung equals to UPDATED_SUDUNG
        defaultQldtChungChiShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtChungChiShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtChungChiList where sudung not equals to UPDATED_SUDUNG
        defaultQldtChungChiShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtChungChiShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtChungChiList where sudung equals to UPDATED_SUDUNG
        defaultQldtChungChiShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where sudung is not null
        defaultQldtChungChiShouldBeFound("sudung.specified=true");

        // Get all the qldtChungChiList where sudung is null
        defaultQldtChungChiShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtChungChisBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtChungChiShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtChungChiList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtChungChiShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtChungChiShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtChungChiList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtChungChiShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where sudung is less than DEFAULT_SUDUNG
        defaultQldtChungChiShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtChungChiList where sudung is less than UPDATED_SUDUNG
        defaultQldtChungChiShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtChungChisBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        // Get all the qldtChungChiList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtChungChiShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtChungChiList where sudung is greater than SMALLER_SUDUNG
        defaultQldtChungChiShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtChungChisByQldtDmChungchiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);
        QldtDmChungchi qldtDmChungchi = QldtDmChungchiResourceIT.createEntity(em);
        em.persist(qldtDmChungchi);
        em.flush();
        qldtChungChi.setQldtDmChungchi(qldtDmChungchi);
        qldtChungChiRepository.saveAndFlush(qldtChungChi);
        Long qldtDmChungchiId = qldtDmChungchi.getId();

        // Get all the qldtChungChiList where qldtDmChungchi equals to qldtDmChungchiId
        defaultQldtChungChiShouldBeFound("qldtDmChungchiId.equals=" + qldtDmChungchiId);

        // Get all the qldtChungChiList where qldtDmChungchi equals to qldtDmChungchiId + 1
        defaultQldtChungChiShouldNotBeFound("qldtDmChungchiId.equals=" + (qldtDmChungchiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtChungChiShouldBeFound(String filter) throws Exception {
        restQldtChungChiMockMvc.perform(get("/api/qldt-chung-chis?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtChungChi.getId().intValue())))
            .andExpect(jsonPath("$.[*].han").value(hasItem(DEFAULT_HAN)))
            .andExpect(jsonPath("$.[*].ngaycap").value(hasItem(DEFAULT_NGAYCAP.toString())))
            .andExpect(jsonPath("$.[*].ngayhethan").value(hasItem(DEFAULT_NGAYHETHAN.toString())))
            .andExpect(jsonPath("$.[*].urlchungchi").value(hasItem(DEFAULT_URLCHUNGCHI)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtChungChiMockMvc.perform(get("/api/qldt-chung-chis/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtChungChiShouldNotBeFound(String filter) throws Exception {
        restQldtChungChiMockMvc.perform(get("/api/qldt-chung-chis?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtChungChiMockMvc.perform(get("/api/qldt-chung-chis/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtChungChi() throws Exception {
        // Get the qldtChungChi
        restQldtChungChiMockMvc.perform(get("/api/qldt-chung-chis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtChungChi() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        int databaseSizeBeforeUpdate = qldtChungChiRepository.findAll().size();

        // Update the qldtChungChi
        QldtChungChi updatedQldtChungChi = qldtChungChiRepository.findById(qldtChungChi.getId()).get();
        // Disconnect from session so that the updates on updatedQldtChungChi are not directly saved in db
        em.detach(updatedQldtChungChi);
        updatedQldtChungChi
            .han(UPDATED_HAN)
            .ngaycap(UPDATED_NGAYCAP)
            .ngayhethan(UPDATED_NGAYHETHAN)
            .urlchungchi(UPDATED_URLCHUNGCHI)
            .sudung(UPDATED_SUDUNG);
        QldtChungChiDTO qldtChungChiDTO = qldtChungChiMapper.toDto(updatedQldtChungChi);

        restQldtChungChiMockMvc.perform(put("/api/qldt-chung-chis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtChungChiDTO)))
            .andExpect(status().isOk());

        // Validate the QldtChungChi in the database
        List<QldtChungChi> qldtChungChiList = qldtChungChiRepository.findAll();
        assertThat(qldtChungChiList).hasSize(databaseSizeBeforeUpdate);
        QldtChungChi testQldtChungChi = qldtChungChiList.get(qldtChungChiList.size() - 1);
        assertThat(testQldtChungChi.getHan()).isEqualTo(UPDATED_HAN);
        assertThat(testQldtChungChi.getNgaycap()).isEqualTo(UPDATED_NGAYCAP);
        assertThat(testQldtChungChi.getNgayhethan()).isEqualTo(UPDATED_NGAYHETHAN);
        assertThat(testQldtChungChi.getUrlchungchi()).isEqualTo(UPDATED_URLCHUNGCHI);
        assertThat(testQldtChungChi.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtChungChi() throws Exception {
        int databaseSizeBeforeUpdate = qldtChungChiRepository.findAll().size();

        // Create the QldtChungChi
        QldtChungChiDTO qldtChungChiDTO = qldtChungChiMapper.toDto(qldtChungChi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtChungChiMockMvc.perform(put("/api/qldt-chung-chis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtChungChiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtChungChi in the database
        List<QldtChungChi> qldtChungChiList = qldtChungChiRepository.findAll();
        assertThat(qldtChungChiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtChungChi() throws Exception {
        // Initialize the database
        qldtChungChiRepository.saveAndFlush(qldtChungChi);

        int databaseSizeBeforeDelete = qldtChungChiRepository.findAll().size();

        // Delete the qldtChungChi
        restQldtChungChiMockMvc.perform(delete("/api/qldt-chung-chis/{id}", qldtChungChi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtChungChi> qldtChungChiList = qldtChungChiRepository.findAll();
        assertThat(qldtChungChiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
