package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt;
import com.vnptit.vnpthis.domain.qldt.QldtQlHocvienCt;
import com.vnptit.vnpthis.domain.qldt.QldtDaotao;
import com.vnptit.vnpthis.repository.qldt.QldtDaotaoCtRepository;
import com.vnptit.vnpthis.service.qldt.QldtDaotaoCtService;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCtDTO;
import com.vnptit.vnpthis.service.mapper.QldtDaotaoCtMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtDaotaoCtResource;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCtCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDaotaoCtQueryService;

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
 * Integration tests for the {@link QldtDaotaoCtResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtDaotaoCtResourceIT {

    private static final String DEFAULT_MADAOTAOCHITIET = "AAAAAAAAAA";
    private static final String UPDATED_MADAOTAOCHITIET = "BBBBBBBBBB";

    private static final String DEFAULT_TENDAOTAOCHITIET = "AAAAAAAAAA";
    private static final String UPDATED_TENDAOTAOCHITIET = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_BD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BD = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_BD = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAY_KT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_KT = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_THOIGIANDAOTAOCT = 1;
    private static final Integer UPDATED_THOIGIANDAOTAOCT = 2;
    private static final Integer SMALLER_THOIGIANDAOTAOCT = 1 - 1;

    private static final String DEFAULT_NOIDUNGDAOTAOCT = "AAAAAAAAAA";
    private static final String UPDATED_NOIDUNGDAOTAOCT = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANGTHAIDAOTAOCT = 1;
    private static final Integer UPDATED_TRANGTHAIDAOTAOCT = 2;
    private static final Integer SMALLER_TRANGTHAIDAOTAOCT = 1 - 1;

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtDaotaoCtRepository qldtDaotaoCtRepository;

    @Autowired
    private QldtDaotaoCtMapper qldtDaotaoCtMapper;

    @Autowired
    private QldtDaotaoCtService qldtDaotaoCtService;

    @Autowired
    private QldtDaotaoCtQueryService qldtDaotaoCtQueryService;

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

    private MockMvc restQldtDaotaoCtMockMvc;

    private QldtDaotaoCt qldtDaotaoCt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtDaotaoCtResource qldtDaotaoCtResource = new QldtDaotaoCtResource(qldtDaotaoCtService, qldtDaotaoCtQueryService);
        this.restQldtDaotaoCtMockMvc = MockMvcBuilders.standaloneSetup(qldtDaotaoCtResource)
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
    public static QldtDaotaoCt createEntity(EntityManager em) {
        QldtDaotaoCt qldtDaotaoCt = new QldtDaotaoCt()
            .madaotaochitiet(DEFAULT_MADAOTAOCHITIET)
            .tendaotaochitiet(DEFAULT_TENDAOTAOCHITIET)
            .ngayBd(DEFAULT_NGAY_BD)
            .ngayKt(DEFAULT_NGAY_KT)
            .thoigiandaotaoct(DEFAULT_THOIGIANDAOTAOCT)
            .noidungdaotaoct(DEFAULT_NOIDUNGDAOTAOCT)
            .trangthaidaotaoct(DEFAULT_TRANGTHAIDAOTAOCT)
            .sudung(DEFAULT_SUDUNG);
        return qldtDaotaoCt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtDaotaoCt createUpdatedEntity(EntityManager em) {
        QldtDaotaoCt qldtDaotaoCt = new QldtDaotaoCt()
            .madaotaochitiet(UPDATED_MADAOTAOCHITIET)
            .tendaotaochitiet(UPDATED_TENDAOTAOCHITIET)
            .ngayBd(UPDATED_NGAY_BD)
            .ngayKt(UPDATED_NGAY_KT)
            .thoigiandaotaoct(UPDATED_THOIGIANDAOTAOCT)
            .noidungdaotaoct(UPDATED_NOIDUNGDAOTAOCT)
            .trangthaidaotaoct(UPDATED_TRANGTHAIDAOTAOCT)
            .sudung(UPDATED_SUDUNG);
        return qldtDaotaoCt;
    }

    @BeforeEach
    public void initTest() {
        qldtDaotaoCt = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtDaotaoCt() throws Exception {
        int databaseSizeBeforeCreate = qldtDaotaoCtRepository.findAll().size();

        // Create the QldtDaotaoCt
        QldtDaotaoCtDTO qldtDaotaoCtDTO = qldtDaotaoCtMapper.toDto(qldtDaotaoCt);
        restQldtDaotaoCtMockMvc.perform(post("/api/qldt-daotao-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDaotaoCtDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtDaotaoCt in the database
        List<QldtDaotaoCt> qldtDaotaoCtList = qldtDaotaoCtRepository.findAll();
        assertThat(qldtDaotaoCtList).hasSize(databaseSizeBeforeCreate + 1);
        QldtDaotaoCt testQldtDaotaoCt = qldtDaotaoCtList.get(qldtDaotaoCtList.size() - 1);
        assertThat(testQldtDaotaoCt.getMadaotaochitiet()).isEqualTo(DEFAULT_MADAOTAOCHITIET);
        assertThat(testQldtDaotaoCt.getTendaotaochitiet()).isEqualTo(DEFAULT_TENDAOTAOCHITIET);
        assertThat(testQldtDaotaoCt.getNgayBd()).isEqualTo(DEFAULT_NGAY_BD);
        assertThat(testQldtDaotaoCt.getNgayKt()).isEqualTo(DEFAULT_NGAY_KT);
        assertThat(testQldtDaotaoCt.getThoigiandaotaoct()).isEqualTo(DEFAULT_THOIGIANDAOTAOCT);
        assertThat(testQldtDaotaoCt.getNoidungdaotaoct()).isEqualTo(DEFAULT_NOIDUNGDAOTAOCT);
        assertThat(testQldtDaotaoCt.getTrangthaidaotaoct()).isEqualTo(DEFAULT_TRANGTHAIDAOTAOCT);
        assertThat(testQldtDaotaoCt.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtDaotaoCtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtDaotaoCtRepository.findAll().size();

        // Create the QldtDaotaoCt with an existing ID
        qldtDaotaoCt.setId(1L);
        QldtDaotaoCtDTO qldtDaotaoCtDTO = qldtDaotaoCtMapper.toDto(qldtDaotaoCt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtDaotaoCtMockMvc.perform(post("/api/qldt-daotao-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDaotaoCtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDaotaoCt in the database
        List<QldtDaotaoCt> qldtDaotaoCtList = qldtDaotaoCtRepository.findAll();
        assertThat(qldtDaotaoCtList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCts() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList
        restQldtDaotaoCtMockMvc.perform(get("/api/qldt-daotao-cts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDaotaoCt.getId().intValue())))
            .andExpect(jsonPath("$.[*].madaotaochitiet").value(hasItem(DEFAULT_MADAOTAOCHITIET)))
            .andExpect(jsonPath("$.[*].tendaotaochitiet").value(hasItem(DEFAULT_TENDAOTAOCHITIET)))
            .andExpect(jsonPath("$.[*].ngayBd").value(hasItem(DEFAULT_NGAY_BD.toString())))
            .andExpect(jsonPath("$.[*].ngayKt").value(hasItem(DEFAULT_NGAY_KT.toString())))
            .andExpect(jsonPath("$.[*].thoigiandaotaoct").value(hasItem(DEFAULT_THOIGIANDAOTAOCT)))
            .andExpect(jsonPath("$.[*].noidungdaotaoct").value(hasItem(DEFAULT_NOIDUNGDAOTAOCT)))
            .andExpect(jsonPath("$.[*].trangthaidaotaoct").value(hasItem(DEFAULT_TRANGTHAIDAOTAOCT)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtDaotaoCt() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get the qldtDaotaoCt
        restQldtDaotaoCtMockMvc.perform(get("/api/qldt-daotao-cts/{id}", qldtDaotaoCt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtDaotaoCt.getId().intValue()))
            .andExpect(jsonPath("$.madaotaochitiet").value(DEFAULT_MADAOTAOCHITIET))
            .andExpect(jsonPath("$.tendaotaochitiet").value(DEFAULT_TENDAOTAOCHITIET))
            .andExpect(jsonPath("$.ngayBd").value(DEFAULT_NGAY_BD.toString()))
            .andExpect(jsonPath("$.ngayKt").value(DEFAULT_NGAY_KT.toString()))
            .andExpect(jsonPath("$.thoigiandaotaoct").value(DEFAULT_THOIGIANDAOTAOCT))
            .andExpect(jsonPath("$.noidungdaotaoct").value(DEFAULT_NOIDUNGDAOTAOCT))
            .andExpect(jsonPath("$.trangthaidaotaoct").value(DEFAULT_TRANGTHAIDAOTAOCT))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtDaotaoCtsByIdFiltering() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        Long id = qldtDaotaoCt.getId();

        defaultQldtDaotaoCtShouldBeFound("id.equals=" + id);
        defaultQldtDaotaoCtShouldNotBeFound("id.notEquals=" + id);

        defaultQldtDaotaoCtShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtDaotaoCtShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtDaotaoCtShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtDaotaoCtShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByMadaotaochitietIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where madaotaochitiet equals to DEFAULT_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("madaotaochitiet.equals=" + DEFAULT_MADAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where madaotaochitiet equals to UPDATED_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("madaotaochitiet.equals=" + UPDATED_MADAOTAOCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByMadaotaochitietIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where madaotaochitiet not equals to DEFAULT_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("madaotaochitiet.notEquals=" + DEFAULT_MADAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where madaotaochitiet not equals to UPDATED_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("madaotaochitiet.notEquals=" + UPDATED_MADAOTAOCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByMadaotaochitietIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where madaotaochitiet in DEFAULT_MADAOTAOCHITIET or UPDATED_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("madaotaochitiet.in=" + DEFAULT_MADAOTAOCHITIET + "," + UPDATED_MADAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where madaotaochitiet equals to UPDATED_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("madaotaochitiet.in=" + UPDATED_MADAOTAOCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByMadaotaochitietIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where madaotaochitiet is not null
        defaultQldtDaotaoCtShouldBeFound("madaotaochitiet.specified=true");

        // Get all the qldtDaotaoCtList where madaotaochitiet is null
        defaultQldtDaotaoCtShouldNotBeFound("madaotaochitiet.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDaotaoCtsByMadaotaochitietContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where madaotaochitiet contains DEFAULT_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("madaotaochitiet.contains=" + DEFAULT_MADAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where madaotaochitiet contains UPDATED_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("madaotaochitiet.contains=" + UPDATED_MADAOTAOCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByMadaotaochitietNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where madaotaochitiet does not contain DEFAULT_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("madaotaochitiet.doesNotContain=" + DEFAULT_MADAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where madaotaochitiet does not contain UPDATED_MADAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("madaotaochitiet.doesNotContain=" + UPDATED_MADAOTAOCHITIET);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTendaotaochitietIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where tendaotaochitiet equals to DEFAULT_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("tendaotaochitiet.equals=" + DEFAULT_TENDAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where tendaotaochitiet equals to UPDATED_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("tendaotaochitiet.equals=" + UPDATED_TENDAOTAOCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTendaotaochitietIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where tendaotaochitiet not equals to DEFAULT_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("tendaotaochitiet.notEquals=" + DEFAULT_TENDAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where tendaotaochitiet not equals to UPDATED_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("tendaotaochitiet.notEquals=" + UPDATED_TENDAOTAOCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTendaotaochitietIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where tendaotaochitiet in DEFAULT_TENDAOTAOCHITIET or UPDATED_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("tendaotaochitiet.in=" + DEFAULT_TENDAOTAOCHITIET + "," + UPDATED_TENDAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where tendaotaochitiet equals to UPDATED_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("tendaotaochitiet.in=" + UPDATED_TENDAOTAOCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTendaotaochitietIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where tendaotaochitiet is not null
        defaultQldtDaotaoCtShouldBeFound("tendaotaochitiet.specified=true");

        // Get all the qldtDaotaoCtList where tendaotaochitiet is null
        defaultQldtDaotaoCtShouldNotBeFound("tendaotaochitiet.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTendaotaochitietContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where tendaotaochitiet contains DEFAULT_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("tendaotaochitiet.contains=" + DEFAULT_TENDAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where tendaotaochitiet contains UPDATED_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("tendaotaochitiet.contains=" + UPDATED_TENDAOTAOCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTendaotaochitietNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where tendaotaochitiet does not contain DEFAULT_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldNotBeFound("tendaotaochitiet.doesNotContain=" + DEFAULT_TENDAOTAOCHITIET);

        // Get all the qldtDaotaoCtList where tendaotaochitiet does not contain UPDATED_TENDAOTAOCHITIET
        defaultQldtDaotaoCtShouldBeFound("tendaotaochitiet.doesNotContain=" + UPDATED_TENDAOTAOCHITIET);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayBdIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayBd equals to DEFAULT_NGAY_BD
        defaultQldtDaotaoCtShouldBeFound("ngayBd.equals=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoCtList where ngayBd equals to UPDATED_NGAY_BD
        defaultQldtDaotaoCtShouldNotBeFound("ngayBd.equals=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayBdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayBd not equals to DEFAULT_NGAY_BD
        defaultQldtDaotaoCtShouldNotBeFound("ngayBd.notEquals=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoCtList where ngayBd not equals to UPDATED_NGAY_BD
        defaultQldtDaotaoCtShouldBeFound("ngayBd.notEquals=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayBdIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayBd in DEFAULT_NGAY_BD or UPDATED_NGAY_BD
        defaultQldtDaotaoCtShouldBeFound("ngayBd.in=" + DEFAULT_NGAY_BD + "," + UPDATED_NGAY_BD);

        // Get all the qldtDaotaoCtList where ngayBd equals to UPDATED_NGAY_BD
        defaultQldtDaotaoCtShouldNotBeFound("ngayBd.in=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayBdIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayBd is not null
        defaultQldtDaotaoCtShouldBeFound("ngayBd.specified=true");

        // Get all the qldtDaotaoCtList where ngayBd is null
        defaultQldtDaotaoCtShouldNotBeFound("ngayBd.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayBdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayBd is greater than or equal to DEFAULT_NGAY_BD
        defaultQldtDaotaoCtShouldBeFound("ngayBd.greaterThanOrEqual=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoCtList where ngayBd is greater than or equal to UPDATED_NGAY_BD
        defaultQldtDaotaoCtShouldNotBeFound("ngayBd.greaterThanOrEqual=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayBdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayBd is less than or equal to DEFAULT_NGAY_BD
        defaultQldtDaotaoCtShouldBeFound("ngayBd.lessThanOrEqual=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoCtList where ngayBd is less than or equal to SMALLER_NGAY_BD
        defaultQldtDaotaoCtShouldNotBeFound("ngayBd.lessThanOrEqual=" + SMALLER_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayBdIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayBd is less than DEFAULT_NGAY_BD
        defaultQldtDaotaoCtShouldNotBeFound("ngayBd.lessThan=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoCtList where ngayBd is less than UPDATED_NGAY_BD
        defaultQldtDaotaoCtShouldBeFound("ngayBd.lessThan=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayBdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayBd is greater than DEFAULT_NGAY_BD
        defaultQldtDaotaoCtShouldNotBeFound("ngayBd.greaterThan=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoCtList where ngayBd is greater than SMALLER_NGAY_BD
        defaultQldtDaotaoCtShouldBeFound("ngayBd.greaterThan=" + SMALLER_NGAY_BD);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayKtIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayKt equals to DEFAULT_NGAY_KT
        defaultQldtDaotaoCtShouldBeFound("ngayKt.equals=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoCtList where ngayKt equals to UPDATED_NGAY_KT
        defaultQldtDaotaoCtShouldNotBeFound("ngayKt.equals=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayKtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayKt not equals to DEFAULT_NGAY_KT
        defaultQldtDaotaoCtShouldNotBeFound("ngayKt.notEquals=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoCtList where ngayKt not equals to UPDATED_NGAY_KT
        defaultQldtDaotaoCtShouldBeFound("ngayKt.notEquals=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayKtIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayKt in DEFAULT_NGAY_KT or UPDATED_NGAY_KT
        defaultQldtDaotaoCtShouldBeFound("ngayKt.in=" + DEFAULT_NGAY_KT + "," + UPDATED_NGAY_KT);

        // Get all the qldtDaotaoCtList where ngayKt equals to UPDATED_NGAY_KT
        defaultQldtDaotaoCtShouldNotBeFound("ngayKt.in=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayKtIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayKt is not null
        defaultQldtDaotaoCtShouldBeFound("ngayKt.specified=true");

        // Get all the qldtDaotaoCtList where ngayKt is null
        defaultQldtDaotaoCtShouldNotBeFound("ngayKt.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayKtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayKt is greater than or equal to DEFAULT_NGAY_KT
        defaultQldtDaotaoCtShouldBeFound("ngayKt.greaterThanOrEqual=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoCtList where ngayKt is greater than or equal to UPDATED_NGAY_KT
        defaultQldtDaotaoCtShouldNotBeFound("ngayKt.greaterThanOrEqual=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayKtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayKt is less than or equal to DEFAULT_NGAY_KT
        defaultQldtDaotaoCtShouldBeFound("ngayKt.lessThanOrEqual=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoCtList where ngayKt is less than or equal to SMALLER_NGAY_KT
        defaultQldtDaotaoCtShouldNotBeFound("ngayKt.lessThanOrEqual=" + SMALLER_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayKtIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayKt is less than DEFAULT_NGAY_KT
        defaultQldtDaotaoCtShouldNotBeFound("ngayKt.lessThan=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoCtList where ngayKt is less than UPDATED_NGAY_KT
        defaultQldtDaotaoCtShouldBeFound("ngayKt.lessThan=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNgayKtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where ngayKt is greater than DEFAULT_NGAY_KT
        defaultQldtDaotaoCtShouldNotBeFound("ngayKt.greaterThan=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoCtList where ngayKt is greater than SMALLER_NGAY_KT
        defaultQldtDaotaoCtShouldBeFound("ngayKt.greaterThan=" + SMALLER_NGAY_KT);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByThoigiandaotaoctIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct equals to DEFAULT_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("thoigiandaotaoct.equals=" + DEFAULT_THOIGIANDAOTAOCT);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct equals to UPDATED_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("thoigiandaotaoct.equals=" + UPDATED_THOIGIANDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByThoigiandaotaoctIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct not equals to DEFAULT_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("thoigiandaotaoct.notEquals=" + DEFAULT_THOIGIANDAOTAOCT);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct not equals to UPDATED_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("thoigiandaotaoct.notEquals=" + UPDATED_THOIGIANDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByThoigiandaotaoctIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct in DEFAULT_THOIGIANDAOTAOCT or UPDATED_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("thoigiandaotaoct.in=" + DEFAULT_THOIGIANDAOTAOCT + "," + UPDATED_THOIGIANDAOTAOCT);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct equals to UPDATED_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("thoigiandaotaoct.in=" + UPDATED_THOIGIANDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByThoigiandaotaoctIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is not null
        defaultQldtDaotaoCtShouldBeFound("thoigiandaotaoct.specified=true");

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is null
        defaultQldtDaotaoCtShouldNotBeFound("thoigiandaotaoct.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByThoigiandaotaoctIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is greater than or equal to DEFAULT_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("thoigiandaotaoct.greaterThanOrEqual=" + DEFAULT_THOIGIANDAOTAOCT);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is greater than or equal to UPDATED_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("thoigiandaotaoct.greaterThanOrEqual=" + UPDATED_THOIGIANDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByThoigiandaotaoctIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is less than or equal to DEFAULT_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("thoigiandaotaoct.lessThanOrEqual=" + DEFAULT_THOIGIANDAOTAOCT);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is less than or equal to SMALLER_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("thoigiandaotaoct.lessThanOrEqual=" + SMALLER_THOIGIANDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByThoigiandaotaoctIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is less than DEFAULT_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("thoigiandaotaoct.lessThan=" + DEFAULT_THOIGIANDAOTAOCT);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is less than UPDATED_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("thoigiandaotaoct.lessThan=" + UPDATED_THOIGIANDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByThoigiandaotaoctIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is greater than DEFAULT_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("thoigiandaotaoct.greaterThan=" + DEFAULT_THOIGIANDAOTAOCT);

        // Get all the qldtDaotaoCtList where thoigiandaotaoct is greater than SMALLER_THOIGIANDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("thoigiandaotaoct.greaterThan=" + SMALLER_THOIGIANDAOTAOCT);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNoidungdaotaoctIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where noidungdaotaoct equals to DEFAULT_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("noidungdaotaoct.equals=" + DEFAULT_NOIDUNGDAOTAOCT);

        // Get all the qldtDaotaoCtList where noidungdaotaoct equals to UPDATED_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("noidungdaotaoct.equals=" + UPDATED_NOIDUNGDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNoidungdaotaoctIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where noidungdaotaoct not equals to DEFAULT_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("noidungdaotaoct.notEquals=" + DEFAULT_NOIDUNGDAOTAOCT);

        // Get all the qldtDaotaoCtList where noidungdaotaoct not equals to UPDATED_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("noidungdaotaoct.notEquals=" + UPDATED_NOIDUNGDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNoidungdaotaoctIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where noidungdaotaoct in DEFAULT_NOIDUNGDAOTAOCT or UPDATED_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("noidungdaotaoct.in=" + DEFAULT_NOIDUNGDAOTAOCT + "," + UPDATED_NOIDUNGDAOTAOCT);

        // Get all the qldtDaotaoCtList where noidungdaotaoct equals to UPDATED_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("noidungdaotaoct.in=" + UPDATED_NOIDUNGDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNoidungdaotaoctIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where noidungdaotaoct is not null
        defaultQldtDaotaoCtShouldBeFound("noidungdaotaoct.specified=true");

        // Get all the qldtDaotaoCtList where noidungdaotaoct is null
        defaultQldtDaotaoCtShouldNotBeFound("noidungdaotaoct.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNoidungdaotaoctContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where noidungdaotaoct contains DEFAULT_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("noidungdaotaoct.contains=" + DEFAULT_NOIDUNGDAOTAOCT);

        // Get all the qldtDaotaoCtList where noidungdaotaoct contains UPDATED_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("noidungdaotaoct.contains=" + UPDATED_NOIDUNGDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByNoidungdaotaoctNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where noidungdaotaoct does not contain DEFAULT_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("noidungdaotaoct.doesNotContain=" + DEFAULT_NOIDUNGDAOTAOCT);

        // Get all the qldtDaotaoCtList where noidungdaotaoct does not contain UPDATED_NOIDUNGDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("noidungdaotaoct.doesNotContain=" + UPDATED_NOIDUNGDAOTAOCT);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTrangthaidaotaoctIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct equals to DEFAULT_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("trangthaidaotaoct.equals=" + DEFAULT_TRANGTHAIDAOTAOCT);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct equals to UPDATED_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("trangthaidaotaoct.equals=" + UPDATED_TRANGTHAIDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTrangthaidaotaoctIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct not equals to DEFAULT_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("trangthaidaotaoct.notEquals=" + DEFAULT_TRANGTHAIDAOTAOCT);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct not equals to UPDATED_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("trangthaidaotaoct.notEquals=" + UPDATED_TRANGTHAIDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTrangthaidaotaoctIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct in DEFAULT_TRANGTHAIDAOTAOCT or UPDATED_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("trangthaidaotaoct.in=" + DEFAULT_TRANGTHAIDAOTAOCT + "," + UPDATED_TRANGTHAIDAOTAOCT);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct equals to UPDATED_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("trangthaidaotaoct.in=" + UPDATED_TRANGTHAIDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTrangthaidaotaoctIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is not null
        defaultQldtDaotaoCtShouldBeFound("trangthaidaotaoct.specified=true");

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is null
        defaultQldtDaotaoCtShouldNotBeFound("trangthaidaotaoct.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTrangthaidaotaoctIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is greater than or equal to DEFAULT_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("trangthaidaotaoct.greaterThanOrEqual=" + DEFAULT_TRANGTHAIDAOTAOCT);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is greater than or equal to UPDATED_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("trangthaidaotaoct.greaterThanOrEqual=" + UPDATED_TRANGTHAIDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTrangthaidaotaoctIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is less than or equal to DEFAULT_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("trangthaidaotaoct.lessThanOrEqual=" + DEFAULT_TRANGTHAIDAOTAOCT);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is less than or equal to SMALLER_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("trangthaidaotaoct.lessThanOrEqual=" + SMALLER_TRANGTHAIDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTrangthaidaotaoctIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is less than DEFAULT_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("trangthaidaotaoct.lessThan=" + DEFAULT_TRANGTHAIDAOTAOCT);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is less than UPDATED_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("trangthaidaotaoct.lessThan=" + UPDATED_TRANGTHAIDAOTAOCT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByTrangthaidaotaoctIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is greater than DEFAULT_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldNotBeFound("trangthaidaotaoct.greaterThan=" + DEFAULT_TRANGTHAIDAOTAOCT);

        // Get all the qldtDaotaoCtList where trangthaidaotaoct is greater than SMALLER_TRANGTHAIDAOTAOCT
        defaultQldtDaotaoCtShouldBeFound("trangthaidaotaoct.greaterThan=" + SMALLER_TRANGTHAIDAOTAOCT);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where sudung equals to DEFAULT_SUDUNG
        defaultQldtDaotaoCtShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoCtList where sudung equals to UPDATED_SUDUNG
        defaultQldtDaotaoCtShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtDaotaoCtShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoCtList where sudung not equals to UPDATED_SUDUNG
        defaultQldtDaotaoCtShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtDaotaoCtShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtDaotaoCtList where sudung equals to UPDATED_SUDUNG
        defaultQldtDaotaoCtShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where sudung is not null
        defaultQldtDaotaoCtShouldBeFound("sudung.specified=true");

        // Get all the qldtDaotaoCtList where sudung is null
        defaultQldtDaotaoCtShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtDaotaoCtShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoCtList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtDaotaoCtShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtDaotaoCtShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoCtList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtDaotaoCtShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where sudung is less than DEFAULT_SUDUNG
        defaultQldtDaotaoCtShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoCtList where sudung is less than UPDATED_SUDUNG
        defaultQldtDaotaoCtShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaoCtsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        // Get all the qldtDaotaoCtList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtDaotaoCtShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoCtList where sudung is greater than SMALLER_SUDUNG
        defaultQldtDaotaoCtShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByHocVienCtIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);
        QldtQlHocvienCt hocVienCt = QldtQlHocvienCtResourceIT.createEntity(em);
        em.persist(hocVienCt);
        em.flush();
        qldtDaotaoCt.addHocVienCt(hocVienCt);
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);
        Long hocVienCtId = hocVienCt.getId();

        // Get all the qldtDaotaoCtList where hocVienCt equals to hocVienCtId
        defaultQldtDaotaoCtShouldBeFound("hocVienCtId.equals=" + hocVienCtId);

        // Get all the qldtDaotaoCtList where hocVienCt equals to hocVienCtId + 1
        defaultQldtDaotaoCtShouldNotBeFound("hocVienCtId.equals=" + (hocVienCtId + 1));
    }


    @Test
    @Transactional
    public void getAllQldtDaotaoCtsByQldtDaotaoIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);
        QldtDaotao qldtDaotao = QldtDaotaoResourceIT.createEntity(em);
        em.persist(qldtDaotao);
        em.flush();
        qldtDaotaoCt.setQldtDaotao(qldtDaotao);
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);
        Long qldtDaotaoId = qldtDaotao.getId();

        // Get all the qldtDaotaoCtList where qldtDaotao equals to qldtDaotaoId
        defaultQldtDaotaoCtShouldBeFound("qldtDaotaoId.equals=" + qldtDaotaoId);

        // Get all the qldtDaotaoCtList where qldtDaotao equals to qldtDaotaoId + 1
        defaultQldtDaotaoCtShouldNotBeFound("qldtDaotaoId.equals=" + (qldtDaotaoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtDaotaoCtShouldBeFound(String filter) throws Exception {
        restQldtDaotaoCtMockMvc.perform(get("/api/qldt-daotao-cts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDaotaoCt.getId().intValue())))
            .andExpect(jsonPath("$.[*].madaotaochitiet").value(hasItem(DEFAULT_MADAOTAOCHITIET)))
            .andExpect(jsonPath("$.[*].tendaotaochitiet").value(hasItem(DEFAULT_TENDAOTAOCHITIET)))
            .andExpect(jsonPath("$.[*].ngayBd").value(hasItem(DEFAULT_NGAY_BD.toString())))
            .andExpect(jsonPath("$.[*].ngayKt").value(hasItem(DEFAULT_NGAY_KT.toString())))
            .andExpect(jsonPath("$.[*].thoigiandaotaoct").value(hasItem(DEFAULT_THOIGIANDAOTAOCT)))
            .andExpect(jsonPath("$.[*].noidungdaotaoct").value(hasItem(DEFAULT_NOIDUNGDAOTAOCT)))
            .andExpect(jsonPath("$.[*].trangthaidaotaoct").value(hasItem(DEFAULT_TRANGTHAIDAOTAOCT)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtDaotaoCtMockMvc.perform(get("/api/qldt-daotao-cts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtDaotaoCtShouldNotBeFound(String filter) throws Exception {
        restQldtDaotaoCtMockMvc.perform(get("/api/qldt-daotao-cts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtDaotaoCtMockMvc.perform(get("/api/qldt-daotao-cts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtDaotaoCt() throws Exception {
        // Get the qldtDaotaoCt
        restQldtDaotaoCtMockMvc.perform(get("/api/qldt-daotao-cts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtDaotaoCt() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        int databaseSizeBeforeUpdate = qldtDaotaoCtRepository.findAll().size();

        // Update the qldtDaotaoCt
        QldtDaotaoCt updatedQldtDaotaoCt = qldtDaotaoCtRepository.findById(qldtDaotaoCt.getId()).get();
        // Disconnect from session so that the updates on updatedQldtDaotaoCt are not directly saved in db
        em.detach(updatedQldtDaotaoCt);
        updatedQldtDaotaoCt
            .madaotaochitiet(UPDATED_MADAOTAOCHITIET)
            .tendaotaochitiet(UPDATED_TENDAOTAOCHITIET)
            .ngayBd(UPDATED_NGAY_BD)
            .ngayKt(UPDATED_NGAY_KT)
            .thoigiandaotaoct(UPDATED_THOIGIANDAOTAOCT)
            .noidungdaotaoct(UPDATED_NOIDUNGDAOTAOCT)
            .trangthaidaotaoct(UPDATED_TRANGTHAIDAOTAOCT)
            .sudung(UPDATED_SUDUNG);
        QldtDaotaoCtDTO qldtDaotaoCtDTO = qldtDaotaoCtMapper.toDto(updatedQldtDaotaoCt);

        restQldtDaotaoCtMockMvc.perform(put("/api/qldt-daotao-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDaotaoCtDTO)))
            .andExpect(status().isOk());

        // Validate the QldtDaotaoCt in the database
        List<QldtDaotaoCt> qldtDaotaoCtList = qldtDaotaoCtRepository.findAll();
        assertThat(qldtDaotaoCtList).hasSize(databaseSizeBeforeUpdate);
        QldtDaotaoCt testQldtDaotaoCt = qldtDaotaoCtList.get(qldtDaotaoCtList.size() - 1);
        assertThat(testQldtDaotaoCt.getMadaotaochitiet()).isEqualTo(UPDATED_MADAOTAOCHITIET);
        assertThat(testQldtDaotaoCt.getTendaotaochitiet()).isEqualTo(UPDATED_TENDAOTAOCHITIET);
        assertThat(testQldtDaotaoCt.getNgayBd()).isEqualTo(UPDATED_NGAY_BD);
        assertThat(testQldtDaotaoCt.getNgayKt()).isEqualTo(UPDATED_NGAY_KT);
        assertThat(testQldtDaotaoCt.getThoigiandaotaoct()).isEqualTo(UPDATED_THOIGIANDAOTAOCT);
        assertThat(testQldtDaotaoCt.getNoidungdaotaoct()).isEqualTo(UPDATED_NOIDUNGDAOTAOCT);
        assertThat(testQldtDaotaoCt.getTrangthaidaotaoct()).isEqualTo(UPDATED_TRANGTHAIDAOTAOCT);
        assertThat(testQldtDaotaoCt.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtDaotaoCt() throws Exception {
        int databaseSizeBeforeUpdate = qldtDaotaoCtRepository.findAll().size();

        // Create the QldtDaotaoCt
        QldtDaotaoCtDTO qldtDaotaoCtDTO = qldtDaotaoCtMapper.toDto(qldtDaotaoCt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtDaotaoCtMockMvc.perform(put("/api/qldt-daotao-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDaotaoCtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDaotaoCt in the database
        List<QldtDaotaoCt> qldtDaotaoCtList = qldtDaotaoCtRepository.findAll();
        assertThat(qldtDaotaoCtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtDaotaoCt() throws Exception {
        // Initialize the database
        qldtDaotaoCtRepository.saveAndFlush(qldtDaotaoCt);

        int databaseSizeBeforeDelete = qldtDaotaoCtRepository.findAll().size();

        // Delete the qldtDaotaoCt
        restQldtDaotaoCtMockMvc.perform(delete("/api/qldt-daotao-cts/{id}", qldtDaotaoCt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtDaotaoCt> qldtDaotaoCtList = qldtDaotaoCtRepository.findAll();
        assertThat(qldtDaotaoCtList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
