package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotaoct;
import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotao;
import com.vnptit.vnpthis.domain.qldt.QldtDmNoidung;
import com.vnptit.vnpthis.repository.qldt.QldtDutoanDaotaoctRepository;
import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoctService;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoctDTO;
import com.vnptit.vnpthis.service.mapper.QldtDutoanDaotaoctMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtDutoanDaotaoctResource;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoctCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoctQueryService;

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

import static com.vnptit.vnpthis.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QldtDutoanDaotaoctResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtDutoanDaotaoctResourceIT {

    private static final Integer DEFAULT_SOLUONG = 1;
    private static final Integer UPDATED_SOLUONG = 2;
    private static final Integer SMALLER_SOLUONG = 1 - 1;

    private static final Integer DEFAULT_MUCCHI = 1;
    private static final Integer UPDATED_MUCCHI = 2;
    private static final Integer SMALLER_MUCCHI = 1 - 1;

    private static final Integer DEFAULT_THANHTIEN = 1;
    private static final Integer UPDATED_THANHTIEN = 2;
    private static final Integer SMALLER_THANHTIEN = 1 - 1;

    private static final String DEFAULT_NOIDUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOIDUNG = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANGTHAICT = 1;
    private static final Integer UPDATED_TRANGTHAICT = 2;
    private static final Integer SMALLER_TRANGTHAICT = 1 - 1;

    private static final Integer DEFAULT_DATHANHTOAN = 1;
    private static final Integer UPDATED_DATHANHTOAN = 2;
    private static final Integer SMALLER_DATHANHTOAN = 1 - 1;

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtDutoanDaotaoctRepository qldtDutoanDaotaoctRepository;

    @Autowired
    private QldtDutoanDaotaoctMapper qldtDutoanDaotaoctMapper;

    @Autowired
    private QldtDutoanDaotaoctService qldtDutoanDaotaoctService;

    @Autowired
    private QldtDutoanDaotaoctQueryService qldtDutoanDaotaoctQueryService;

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

    private MockMvc restQldtDutoanDaotaoctMockMvc;

    private QldtDutoanDaotaoct qldtDutoanDaotaoct;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtDutoanDaotaoctResource qldtDutoanDaotaoctResource = new QldtDutoanDaotaoctResource(qldtDutoanDaotaoctService, qldtDutoanDaotaoctQueryService);
        this.restQldtDutoanDaotaoctMockMvc = MockMvcBuilders.standaloneSetup(qldtDutoanDaotaoctResource)
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
    public static QldtDutoanDaotaoct createEntity(EntityManager em) {
        QldtDutoanDaotaoct qldtDutoanDaotaoct = new QldtDutoanDaotaoct()
            .soluong(DEFAULT_SOLUONG)
            .mucchi(DEFAULT_MUCCHI)
            .thanhtien(DEFAULT_THANHTIEN)
            .noidung(DEFAULT_NOIDUNG)
            .trangthaict(DEFAULT_TRANGTHAICT)
            .dathanhtoan(DEFAULT_DATHANHTOAN)
            .sudung(DEFAULT_SUDUNG);
        return qldtDutoanDaotaoct;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtDutoanDaotaoct createUpdatedEntity(EntityManager em) {
        QldtDutoanDaotaoct qldtDutoanDaotaoct = new QldtDutoanDaotaoct()
            .soluong(UPDATED_SOLUONG)
            .mucchi(UPDATED_MUCCHI)
            .thanhtien(UPDATED_THANHTIEN)
            .noidung(UPDATED_NOIDUNG)
            .trangthaict(UPDATED_TRANGTHAICT)
            .dathanhtoan(UPDATED_DATHANHTOAN)
            .sudung(UPDATED_SUDUNG);
        return qldtDutoanDaotaoct;
    }

    @BeforeEach
    public void initTest() {
        qldtDutoanDaotaoct = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtDutoanDaotaoct() throws Exception {
        int databaseSizeBeforeCreate = qldtDutoanDaotaoctRepository.findAll().size();

        // Create the QldtDutoanDaotaoct
        QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO = qldtDutoanDaotaoctMapper.toDto(qldtDutoanDaotaoct);
        restQldtDutoanDaotaoctMockMvc.perform(post("/api/qldt-dutoan-daotaocts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDutoanDaotaoctDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtDutoanDaotaoct in the database
        List<QldtDutoanDaotaoct> qldtDutoanDaotaoctList = qldtDutoanDaotaoctRepository.findAll();
        assertThat(qldtDutoanDaotaoctList).hasSize(databaseSizeBeforeCreate + 1);
        QldtDutoanDaotaoct testQldtDutoanDaotaoct = qldtDutoanDaotaoctList.get(qldtDutoanDaotaoctList.size() - 1);
        assertThat(testQldtDutoanDaotaoct.getSoluong()).isEqualTo(DEFAULT_SOLUONG);
        assertThat(testQldtDutoanDaotaoct.getMucchi()).isEqualTo(DEFAULT_MUCCHI);
        assertThat(testQldtDutoanDaotaoct.getThanhtien()).isEqualTo(DEFAULT_THANHTIEN);
        assertThat(testQldtDutoanDaotaoct.getNoidung()).isEqualTo(DEFAULT_NOIDUNG);
        assertThat(testQldtDutoanDaotaoct.getTrangthaict()).isEqualTo(DEFAULT_TRANGTHAICT);
        assertThat(testQldtDutoanDaotaoct.getDathanhtoan()).isEqualTo(DEFAULT_DATHANHTOAN);
        assertThat(testQldtDutoanDaotaoct.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtDutoanDaotaoctWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtDutoanDaotaoctRepository.findAll().size();

        // Create the QldtDutoanDaotaoct with an existing ID
        qldtDutoanDaotaoct.setId(1L);
        QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO = qldtDutoanDaotaoctMapper.toDto(qldtDutoanDaotaoct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtDutoanDaotaoctMockMvc.perform(post("/api/qldt-dutoan-daotaocts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDutoanDaotaoctDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDutoanDaotaoct in the database
        List<QldtDutoanDaotaoct> qldtDutoanDaotaoctList = qldtDutoanDaotaoctRepository.findAll();
        assertThat(qldtDutoanDaotaoctList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaocts() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList
        restQldtDutoanDaotaoctMockMvc.perform(get("/api/qldt-dutoan-daotaocts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDutoanDaotaoct.getId().intValue())))
            .andExpect(jsonPath("$.[*].soluong").value(hasItem(DEFAULT_SOLUONG)))
            .andExpect(jsonPath("$.[*].mucchi").value(hasItem(DEFAULT_MUCCHI)))
            .andExpect(jsonPath("$.[*].thanhtien").value(hasItem(DEFAULT_THANHTIEN)))
            .andExpect(jsonPath("$.[*].noidung").value(hasItem(DEFAULT_NOIDUNG)))
            .andExpect(jsonPath("$.[*].trangthaict").value(hasItem(DEFAULT_TRANGTHAICT)))
            .andExpect(jsonPath("$.[*].dathanhtoan").value(hasItem(DEFAULT_DATHANHTOAN)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtDutoanDaotaoct() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get the qldtDutoanDaotaoct
        restQldtDutoanDaotaoctMockMvc.perform(get("/api/qldt-dutoan-daotaocts/{id}", qldtDutoanDaotaoct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtDutoanDaotaoct.getId().intValue()))
            .andExpect(jsonPath("$.soluong").value(DEFAULT_SOLUONG))
            .andExpect(jsonPath("$.mucchi").value(DEFAULT_MUCCHI))
            .andExpect(jsonPath("$.thanhtien").value(DEFAULT_THANHTIEN))
            .andExpect(jsonPath("$.noidung").value(DEFAULT_NOIDUNG))
            .andExpect(jsonPath("$.trangthaict").value(DEFAULT_TRANGTHAICT))
            .andExpect(jsonPath("$.dathanhtoan").value(DEFAULT_DATHANHTOAN))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtDutoanDaotaoctsByIdFiltering() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        Long id = qldtDutoanDaotaoct.getId();

        defaultQldtDutoanDaotaoctShouldBeFound("id.equals=" + id);
        defaultQldtDutoanDaotaoctShouldNotBeFound("id.notEquals=" + id);

        defaultQldtDutoanDaotaoctShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtDutoanDaotaoctShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtDutoanDaotaoctShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtDutoanDaotaoctShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySoluongIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where soluong equals to DEFAULT_SOLUONG
        defaultQldtDutoanDaotaoctShouldBeFound("soluong.equals=" + DEFAULT_SOLUONG);

        // Get all the qldtDutoanDaotaoctList where soluong equals to UPDATED_SOLUONG
        defaultQldtDutoanDaotaoctShouldNotBeFound("soluong.equals=" + UPDATED_SOLUONG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySoluongIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where soluong not equals to DEFAULT_SOLUONG
        defaultQldtDutoanDaotaoctShouldNotBeFound("soluong.notEquals=" + DEFAULT_SOLUONG);

        // Get all the qldtDutoanDaotaoctList where soluong not equals to UPDATED_SOLUONG
        defaultQldtDutoanDaotaoctShouldBeFound("soluong.notEquals=" + UPDATED_SOLUONG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySoluongIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where soluong in DEFAULT_SOLUONG or UPDATED_SOLUONG
        defaultQldtDutoanDaotaoctShouldBeFound("soluong.in=" + DEFAULT_SOLUONG + "," + UPDATED_SOLUONG);

        // Get all the qldtDutoanDaotaoctList where soluong equals to UPDATED_SOLUONG
        defaultQldtDutoanDaotaoctShouldNotBeFound("soluong.in=" + UPDATED_SOLUONG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySoluongIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where soluong is not null
        defaultQldtDutoanDaotaoctShouldBeFound("soluong.specified=true");

        // Get all the qldtDutoanDaotaoctList where soluong is null
        defaultQldtDutoanDaotaoctShouldNotBeFound("soluong.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySoluongIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where soluong is greater than or equal to DEFAULT_SOLUONG
        defaultQldtDutoanDaotaoctShouldBeFound("soluong.greaterThanOrEqual=" + DEFAULT_SOLUONG);

        // Get all the qldtDutoanDaotaoctList where soluong is greater than or equal to UPDATED_SOLUONG
        defaultQldtDutoanDaotaoctShouldNotBeFound("soluong.greaterThanOrEqual=" + UPDATED_SOLUONG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySoluongIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where soluong is less than or equal to DEFAULT_SOLUONG
        defaultQldtDutoanDaotaoctShouldBeFound("soluong.lessThanOrEqual=" + DEFAULT_SOLUONG);

        // Get all the qldtDutoanDaotaoctList where soluong is less than or equal to SMALLER_SOLUONG
        defaultQldtDutoanDaotaoctShouldNotBeFound("soluong.lessThanOrEqual=" + SMALLER_SOLUONG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySoluongIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where soluong is less than DEFAULT_SOLUONG
        defaultQldtDutoanDaotaoctShouldNotBeFound("soluong.lessThan=" + DEFAULT_SOLUONG);

        // Get all the qldtDutoanDaotaoctList where soluong is less than UPDATED_SOLUONG
        defaultQldtDutoanDaotaoctShouldBeFound("soluong.lessThan=" + UPDATED_SOLUONG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySoluongIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where soluong is greater than DEFAULT_SOLUONG
        defaultQldtDutoanDaotaoctShouldNotBeFound("soluong.greaterThan=" + DEFAULT_SOLUONG);

        // Get all the qldtDutoanDaotaoctList where soluong is greater than SMALLER_SOLUONG
        defaultQldtDutoanDaotaoctShouldBeFound("soluong.greaterThan=" + SMALLER_SOLUONG);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByMucchiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where mucchi equals to DEFAULT_MUCCHI
        defaultQldtDutoanDaotaoctShouldBeFound("mucchi.equals=" + DEFAULT_MUCCHI);

        // Get all the qldtDutoanDaotaoctList where mucchi equals to UPDATED_MUCCHI
        defaultQldtDutoanDaotaoctShouldNotBeFound("mucchi.equals=" + UPDATED_MUCCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByMucchiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where mucchi not equals to DEFAULT_MUCCHI
        defaultQldtDutoanDaotaoctShouldNotBeFound("mucchi.notEquals=" + DEFAULT_MUCCHI);

        // Get all the qldtDutoanDaotaoctList where mucchi not equals to UPDATED_MUCCHI
        defaultQldtDutoanDaotaoctShouldBeFound("mucchi.notEquals=" + UPDATED_MUCCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByMucchiIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where mucchi in DEFAULT_MUCCHI or UPDATED_MUCCHI
        defaultQldtDutoanDaotaoctShouldBeFound("mucchi.in=" + DEFAULT_MUCCHI + "," + UPDATED_MUCCHI);

        // Get all the qldtDutoanDaotaoctList where mucchi equals to UPDATED_MUCCHI
        defaultQldtDutoanDaotaoctShouldNotBeFound("mucchi.in=" + UPDATED_MUCCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByMucchiIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where mucchi is not null
        defaultQldtDutoanDaotaoctShouldBeFound("mucchi.specified=true");

        // Get all the qldtDutoanDaotaoctList where mucchi is null
        defaultQldtDutoanDaotaoctShouldNotBeFound("mucchi.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByMucchiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where mucchi is greater than or equal to DEFAULT_MUCCHI
        defaultQldtDutoanDaotaoctShouldBeFound("mucchi.greaterThanOrEqual=" + DEFAULT_MUCCHI);

        // Get all the qldtDutoanDaotaoctList where mucchi is greater than or equal to UPDATED_MUCCHI
        defaultQldtDutoanDaotaoctShouldNotBeFound("mucchi.greaterThanOrEqual=" + UPDATED_MUCCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByMucchiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where mucchi is less than or equal to DEFAULT_MUCCHI
        defaultQldtDutoanDaotaoctShouldBeFound("mucchi.lessThanOrEqual=" + DEFAULT_MUCCHI);

        // Get all the qldtDutoanDaotaoctList where mucchi is less than or equal to SMALLER_MUCCHI
        defaultQldtDutoanDaotaoctShouldNotBeFound("mucchi.lessThanOrEqual=" + SMALLER_MUCCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByMucchiIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where mucchi is less than DEFAULT_MUCCHI
        defaultQldtDutoanDaotaoctShouldNotBeFound("mucchi.lessThan=" + DEFAULT_MUCCHI);

        // Get all the qldtDutoanDaotaoctList where mucchi is less than UPDATED_MUCCHI
        defaultQldtDutoanDaotaoctShouldBeFound("mucchi.lessThan=" + UPDATED_MUCCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByMucchiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where mucchi is greater than DEFAULT_MUCCHI
        defaultQldtDutoanDaotaoctShouldNotBeFound("mucchi.greaterThan=" + DEFAULT_MUCCHI);

        // Get all the qldtDutoanDaotaoctList where mucchi is greater than SMALLER_MUCCHI
        defaultQldtDutoanDaotaoctShouldBeFound("mucchi.greaterThan=" + SMALLER_MUCCHI);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByThanhtienIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where thanhtien equals to DEFAULT_THANHTIEN
        defaultQldtDutoanDaotaoctShouldBeFound("thanhtien.equals=" + DEFAULT_THANHTIEN);

        // Get all the qldtDutoanDaotaoctList where thanhtien equals to UPDATED_THANHTIEN
        defaultQldtDutoanDaotaoctShouldNotBeFound("thanhtien.equals=" + UPDATED_THANHTIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByThanhtienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where thanhtien not equals to DEFAULT_THANHTIEN
        defaultQldtDutoanDaotaoctShouldNotBeFound("thanhtien.notEquals=" + DEFAULT_THANHTIEN);

        // Get all the qldtDutoanDaotaoctList where thanhtien not equals to UPDATED_THANHTIEN
        defaultQldtDutoanDaotaoctShouldBeFound("thanhtien.notEquals=" + UPDATED_THANHTIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByThanhtienIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where thanhtien in DEFAULT_THANHTIEN or UPDATED_THANHTIEN
        defaultQldtDutoanDaotaoctShouldBeFound("thanhtien.in=" + DEFAULT_THANHTIEN + "," + UPDATED_THANHTIEN);

        // Get all the qldtDutoanDaotaoctList where thanhtien equals to UPDATED_THANHTIEN
        defaultQldtDutoanDaotaoctShouldNotBeFound("thanhtien.in=" + UPDATED_THANHTIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByThanhtienIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where thanhtien is not null
        defaultQldtDutoanDaotaoctShouldBeFound("thanhtien.specified=true");

        // Get all the qldtDutoanDaotaoctList where thanhtien is null
        defaultQldtDutoanDaotaoctShouldNotBeFound("thanhtien.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByThanhtienIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where thanhtien is greater than or equal to DEFAULT_THANHTIEN
        defaultQldtDutoanDaotaoctShouldBeFound("thanhtien.greaterThanOrEqual=" + DEFAULT_THANHTIEN);

        // Get all the qldtDutoanDaotaoctList where thanhtien is greater than or equal to UPDATED_THANHTIEN
        defaultQldtDutoanDaotaoctShouldNotBeFound("thanhtien.greaterThanOrEqual=" + UPDATED_THANHTIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByThanhtienIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where thanhtien is less than or equal to DEFAULT_THANHTIEN
        defaultQldtDutoanDaotaoctShouldBeFound("thanhtien.lessThanOrEqual=" + DEFAULT_THANHTIEN);

        // Get all the qldtDutoanDaotaoctList where thanhtien is less than or equal to SMALLER_THANHTIEN
        defaultQldtDutoanDaotaoctShouldNotBeFound("thanhtien.lessThanOrEqual=" + SMALLER_THANHTIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByThanhtienIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where thanhtien is less than DEFAULT_THANHTIEN
        defaultQldtDutoanDaotaoctShouldNotBeFound("thanhtien.lessThan=" + DEFAULT_THANHTIEN);

        // Get all the qldtDutoanDaotaoctList where thanhtien is less than UPDATED_THANHTIEN
        defaultQldtDutoanDaotaoctShouldBeFound("thanhtien.lessThan=" + UPDATED_THANHTIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByThanhtienIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where thanhtien is greater than DEFAULT_THANHTIEN
        defaultQldtDutoanDaotaoctShouldNotBeFound("thanhtien.greaterThan=" + DEFAULT_THANHTIEN);

        // Get all the qldtDutoanDaotaoctList where thanhtien is greater than SMALLER_THANHTIEN
        defaultQldtDutoanDaotaoctShouldBeFound("thanhtien.greaterThan=" + SMALLER_THANHTIEN);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByNoidungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where noidung equals to DEFAULT_NOIDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("noidung.equals=" + DEFAULT_NOIDUNG);

        // Get all the qldtDutoanDaotaoctList where noidung equals to UPDATED_NOIDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("noidung.equals=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByNoidungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where noidung not equals to DEFAULT_NOIDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("noidung.notEquals=" + DEFAULT_NOIDUNG);

        // Get all the qldtDutoanDaotaoctList where noidung not equals to UPDATED_NOIDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("noidung.notEquals=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByNoidungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where noidung in DEFAULT_NOIDUNG or UPDATED_NOIDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("noidung.in=" + DEFAULT_NOIDUNG + "," + UPDATED_NOIDUNG);

        // Get all the qldtDutoanDaotaoctList where noidung equals to UPDATED_NOIDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("noidung.in=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByNoidungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where noidung is not null
        defaultQldtDutoanDaotaoctShouldBeFound("noidung.specified=true");

        // Get all the qldtDutoanDaotaoctList where noidung is null
        defaultQldtDutoanDaotaoctShouldNotBeFound("noidung.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByNoidungContainsSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where noidung contains DEFAULT_NOIDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("noidung.contains=" + DEFAULT_NOIDUNG);

        // Get all the qldtDutoanDaotaoctList where noidung contains UPDATED_NOIDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("noidung.contains=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByNoidungNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where noidung does not contain DEFAULT_NOIDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("noidung.doesNotContain=" + DEFAULT_NOIDUNG);

        // Get all the qldtDutoanDaotaoctList where noidung does not contain UPDATED_NOIDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("noidung.doesNotContain=" + UPDATED_NOIDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByTrangthaictIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where trangthaict equals to DEFAULT_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldBeFound("trangthaict.equals=" + DEFAULT_TRANGTHAICT);

        // Get all the qldtDutoanDaotaoctList where trangthaict equals to UPDATED_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldNotBeFound("trangthaict.equals=" + UPDATED_TRANGTHAICT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByTrangthaictIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where trangthaict not equals to DEFAULT_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldNotBeFound("trangthaict.notEquals=" + DEFAULT_TRANGTHAICT);

        // Get all the qldtDutoanDaotaoctList where trangthaict not equals to UPDATED_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldBeFound("trangthaict.notEquals=" + UPDATED_TRANGTHAICT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByTrangthaictIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where trangthaict in DEFAULT_TRANGTHAICT or UPDATED_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldBeFound("trangthaict.in=" + DEFAULT_TRANGTHAICT + "," + UPDATED_TRANGTHAICT);

        // Get all the qldtDutoanDaotaoctList where trangthaict equals to UPDATED_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldNotBeFound("trangthaict.in=" + UPDATED_TRANGTHAICT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByTrangthaictIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where trangthaict is not null
        defaultQldtDutoanDaotaoctShouldBeFound("trangthaict.specified=true");

        // Get all the qldtDutoanDaotaoctList where trangthaict is null
        defaultQldtDutoanDaotaoctShouldNotBeFound("trangthaict.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByTrangthaictIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where trangthaict is greater than or equal to DEFAULT_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldBeFound("trangthaict.greaterThanOrEqual=" + DEFAULT_TRANGTHAICT);

        // Get all the qldtDutoanDaotaoctList where trangthaict is greater than or equal to UPDATED_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldNotBeFound("trangthaict.greaterThanOrEqual=" + UPDATED_TRANGTHAICT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByTrangthaictIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where trangthaict is less than or equal to DEFAULT_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldBeFound("trangthaict.lessThanOrEqual=" + DEFAULT_TRANGTHAICT);

        // Get all the qldtDutoanDaotaoctList where trangthaict is less than or equal to SMALLER_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldNotBeFound("trangthaict.lessThanOrEqual=" + SMALLER_TRANGTHAICT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByTrangthaictIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where trangthaict is less than DEFAULT_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldNotBeFound("trangthaict.lessThan=" + DEFAULT_TRANGTHAICT);

        // Get all the qldtDutoanDaotaoctList where trangthaict is less than UPDATED_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldBeFound("trangthaict.lessThan=" + UPDATED_TRANGTHAICT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByTrangthaictIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where trangthaict is greater than DEFAULT_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldNotBeFound("trangthaict.greaterThan=" + DEFAULT_TRANGTHAICT);

        // Get all the qldtDutoanDaotaoctList where trangthaict is greater than SMALLER_TRANGTHAICT
        defaultQldtDutoanDaotaoctShouldBeFound("trangthaict.greaterThan=" + SMALLER_TRANGTHAICT);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByDathanhtoanIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan equals to DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldBeFound("dathanhtoan.equals=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan equals to UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldNotBeFound("dathanhtoan.equals=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByDathanhtoanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan not equals to DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldNotBeFound("dathanhtoan.notEquals=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan not equals to UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldBeFound("dathanhtoan.notEquals=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByDathanhtoanIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan in DEFAULT_DATHANHTOAN or UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldBeFound("dathanhtoan.in=" + DEFAULT_DATHANHTOAN + "," + UPDATED_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan equals to UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldNotBeFound("dathanhtoan.in=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByDathanhtoanIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is not null
        defaultQldtDutoanDaotaoctShouldBeFound("dathanhtoan.specified=true");

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is null
        defaultQldtDutoanDaotaoctShouldNotBeFound("dathanhtoan.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByDathanhtoanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is greater than or equal to DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldBeFound("dathanhtoan.greaterThanOrEqual=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is greater than or equal to UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldNotBeFound("dathanhtoan.greaterThanOrEqual=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByDathanhtoanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is less than or equal to DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldBeFound("dathanhtoan.lessThanOrEqual=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is less than or equal to SMALLER_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldNotBeFound("dathanhtoan.lessThanOrEqual=" + SMALLER_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByDathanhtoanIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is less than DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldNotBeFound("dathanhtoan.lessThan=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is less than UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldBeFound("dathanhtoan.lessThan=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByDathanhtoanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is greater than DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldNotBeFound("dathanhtoan.greaterThan=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoctList where dathanhtoan is greater than SMALLER_DATHANHTOAN
        defaultQldtDutoanDaotaoctShouldBeFound("dathanhtoan.greaterThan=" + SMALLER_DATHANHTOAN);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where sudung equals to DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoctList where sudung equals to UPDATED_SUDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoctList where sudung not equals to UPDATED_SUDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtDutoanDaotaoctList where sudung equals to UPDATED_SUDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where sudung is not null
        defaultQldtDutoanDaotaoctShouldBeFound("sudung.specified=true");

        // Get all the qldtDutoanDaotaoctList where sudung is null
        defaultQldtDutoanDaotaoctShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoctList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoctList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where sudung is less than DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoctList where sudung is less than UPDATED_SUDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        // Get all the qldtDutoanDaotaoctList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoctShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoctList where sudung is greater than SMALLER_SUDUNG
        defaultQldtDutoanDaotaoctShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByQldtDutoanDaotaoIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);
        QldtDutoanDaotao qldtDutoanDaotao = QldtDutoanDaotaoResourceIT.createEntity(em);
        em.persist(qldtDutoanDaotao);
        em.flush();
        qldtDutoanDaotaoct.setQldtDutoanDaotao(qldtDutoanDaotao);
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);
        Long qldtDutoanDaotaoId = qldtDutoanDaotao.getId();

        // Get all the qldtDutoanDaotaoctList where qldtDutoanDaotao equals to qldtDutoanDaotaoId
        defaultQldtDutoanDaotaoctShouldBeFound("qldtDutoanDaotaoId.equals=" + qldtDutoanDaotaoId);

        // Get all the qldtDutoanDaotaoctList where qldtDutoanDaotao equals to qldtDutoanDaotaoId + 1
        defaultQldtDutoanDaotaoctShouldNotBeFound("qldtDutoanDaotaoId.equals=" + (qldtDutoanDaotaoId + 1));
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaoctsByQldtDmNoidungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);
        QldtDmNoidung qldtDmNoidung = QldtDmNoidungResourceIT.createEntity(em);
        em.persist(qldtDmNoidung);
        em.flush();
        qldtDutoanDaotaoct.setQldtDmNoidung(qldtDmNoidung);
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);
        Long qldtDmNoidungId = qldtDmNoidung.getId();

        // Get all the qldtDutoanDaotaoctList where qldtDmNoidung equals to qldtDmNoidungId
        defaultQldtDutoanDaotaoctShouldBeFound("qldtDmNoidungId.equals=" + qldtDmNoidungId);

        // Get all the qldtDutoanDaotaoctList where qldtDmNoidung equals to qldtDmNoidungId + 1
        defaultQldtDutoanDaotaoctShouldNotBeFound("qldtDmNoidungId.equals=" + (qldtDmNoidungId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtDutoanDaotaoctShouldBeFound(String filter) throws Exception {
        restQldtDutoanDaotaoctMockMvc.perform(get("/api/qldt-dutoan-daotaocts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDutoanDaotaoct.getId().intValue())))
            .andExpect(jsonPath("$.[*].soluong").value(hasItem(DEFAULT_SOLUONG)))
            .andExpect(jsonPath("$.[*].mucchi").value(hasItem(DEFAULT_MUCCHI)))
            .andExpect(jsonPath("$.[*].thanhtien").value(hasItem(DEFAULT_THANHTIEN)))
            .andExpect(jsonPath("$.[*].noidung").value(hasItem(DEFAULT_NOIDUNG)))
            .andExpect(jsonPath("$.[*].trangthaict").value(hasItem(DEFAULT_TRANGTHAICT)))
            .andExpect(jsonPath("$.[*].dathanhtoan").value(hasItem(DEFAULT_DATHANHTOAN)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtDutoanDaotaoctMockMvc.perform(get("/api/qldt-dutoan-daotaocts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtDutoanDaotaoctShouldNotBeFound(String filter) throws Exception {
        restQldtDutoanDaotaoctMockMvc.perform(get("/api/qldt-dutoan-daotaocts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtDutoanDaotaoctMockMvc.perform(get("/api/qldt-dutoan-daotaocts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtDutoanDaotaoct() throws Exception {
        // Get the qldtDutoanDaotaoct
        restQldtDutoanDaotaoctMockMvc.perform(get("/api/qldt-dutoan-daotaocts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtDutoanDaotaoct() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        int databaseSizeBeforeUpdate = qldtDutoanDaotaoctRepository.findAll().size();

        // Update the qldtDutoanDaotaoct
        QldtDutoanDaotaoct updatedQldtDutoanDaotaoct = qldtDutoanDaotaoctRepository.findById(qldtDutoanDaotaoct.getId()).get();
        // Disconnect from session so that the updates on updatedQldtDutoanDaotaoct are not directly saved in db
        em.detach(updatedQldtDutoanDaotaoct);
        updatedQldtDutoanDaotaoct
            .soluong(UPDATED_SOLUONG)
            .mucchi(UPDATED_MUCCHI)
            .thanhtien(UPDATED_THANHTIEN)
            .noidung(UPDATED_NOIDUNG)
            .trangthaict(UPDATED_TRANGTHAICT)
            .dathanhtoan(UPDATED_DATHANHTOAN)
            .sudung(UPDATED_SUDUNG);
        QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO = qldtDutoanDaotaoctMapper.toDto(updatedQldtDutoanDaotaoct);

        restQldtDutoanDaotaoctMockMvc.perform(put("/api/qldt-dutoan-daotaocts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDutoanDaotaoctDTO)))
            .andExpect(status().isOk());

        // Validate the QldtDutoanDaotaoct in the database
        List<QldtDutoanDaotaoct> qldtDutoanDaotaoctList = qldtDutoanDaotaoctRepository.findAll();
        assertThat(qldtDutoanDaotaoctList).hasSize(databaseSizeBeforeUpdate);
        QldtDutoanDaotaoct testQldtDutoanDaotaoct = qldtDutoanDaotaoctList.get(qldtDutoanDaotaoctList.size() - 1);
        assertThat(testQldtDutoanDaotaoct.getSoluong()).isEqualTo(UPDATED_SOLUONG);
        assertThat(testQldtDutoanDaotaoct.getMucchi()).isEqualTo(UPDATED_MUCCHI);
        assertThat(testQldtDutoanDaotaoct.getThanhtien()).isEqualTo(UPDATED_THANHTIEN);
        assertThat(testQldtDutoanDaotaoct.getNoidung()).isEqualTo(UPDATED_NOIDUNG);
        assertThat(testQldtDutoanDaotaoct.getTrangthaict()).isEqualTo(UPDATED_TRANGTHAICT);
        assertThat(testQldtDutoanDaotaoct.getDathanhtoan()).isEqualTo(UPDATED_DATHANHTOAN);
        assertThat(testQldtDutoanDaotaoct.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtDutoanDaotaoct() throws Exception {
        int databaseSizeBeforeUpdate = qldtDutoanDaotaoctRepository.findAll().size();

        // Create the QldtDutoanDaotaoct
        QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO = qldtDutoanDaotaoctMapper.toDto(qldtDutoanDaotaoct);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtDutoanDaotaoctMockMvc.perform(put("/api/qldt-dutoan-daotaocts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDutoanDaotaoctDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDutoanDaotaoct in the database
        List<QldtDutoanDaotaoct> qldtDutoanDaotaoctList = qldtDutoanDaotaoctRepository.findAll();
        assertThat(qldtDutoanDaotaoctList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtDutoanDaotaoct() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoctRepository.saveAndFlush(qldtDutoanDaotaoct);

        int databaseSizeBeforeDelete = qldtDutoanDaotaoctRepository.findAll().size();

        // Delete the qldtDutoanDaotaoct
        restQldtDutoanDaotaoctMockMvc.perform(delete("/api/qldt-dutoan-daotaocts/{id}", qldtDutoanDaotaoct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtDutoanDaotaoct> qldtDutoanDaotaoctList = qldtDutoanDaotaoctRepository.findAll();
        assertThat(qldtDutoanDaotaoctList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
