package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.NckhApp;
import com.vnpt.nckh.domain.NckhTiendo;
import com.vnpt.nckh.domain.NckhDetai;
import com.vnpt.nckh.repository.NckhTiendoRepository;
import com.vnpt.nckh.service.NckhTiendoService;
import com.vnpt.nckh.web.rest.errors.ExceptionTranslator;
import com.vnpt.nckh.service.dto.NckhTiendoCriteria;
import com.vnpt.nckh.service.NckhTiendoQueryService;

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

import static com.vnpt.nckh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NckhTiendoResource} REST controller.
 */
@SpringBootTest(classes = NckhApp.class)
public class NckhTiendoResourceIT {

    private static final Long DEFAULT_TIEN_DO_HOAN_THANH = 1L;
    private static final Long UPDATED_TIEN_DO_HOAN_THANH = 2L;
    private static final Long SMALLER_TIEN_DO_HOAN_THANH = 1L - 1L;

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_CAP_NHAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_CAP_NHAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private NckhTiendoRepository nckhTiendoRepository;

    @Autowired
    private NckhTiendoService nckhTiendoService;

    @Autowired
    private NckhTiendoQueryService nckhTiendoQueryService;

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

    private MockMvc restNckhTiendoMockMvc;

    private NckhTiendo nckhTiendo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NckhTiendoResource nckhTiendoResource = new NckhTiendoResource(nckhTiendoService, nckhTiendoQueryService);
        this.restNckhTiendoMockMvc = MockMvcBuilders.standaloneSetup(nckhTiendoResource)
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
    public static NckhTiendo createEntity(EntityManager em) {
        NckhTiendo nckhTiendo = new NckhTiendo()
            .tienDoHoanThanh(DEFAULT_TIEN_DO_HOAN_THANH)
            .moTa(DEFAULT_MO_TA)
            .ngayCapNhat(DEFAULT_NGAY_CAP_NHAT);
        return nckhTiendo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NckhTiendo createUpdatedEntity(EntityManager em) {
        NckhTiendo nckhTiendo = new NckhTiendo()
            .tienDoHoanThanh(UPDATED_TIEN_DO_HOAN_THANH)
            .moTa(UPDATED_MO_TA)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);
        return nckhTiendo;
    }

    @BeforeEach
    public void initTest() {
        nckhTiendo = createEntity(em);
    }

    @Test
    @Transactional
    public void createNckhTiendo() throws Exception {
        int databaseSizeBeforeCreate = nckhTiendoRepository.findAll().size();

        // Create the NckhTiendo
        restNckhTiendoMockMvc.perform(post("/api/nckh-tiendos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhTiendo)))
            .andExpect(status().isCreated());

        // Validate the NckhTiendo in the database
        List<NckhTiendo> nckhTiendoList = nckhTiendoRepository.findAll();
        assertThat(nckhTiendoList).hasSize(databaseSizeBeforeCreate + 1);
        NckhTiendo testNckhTiendo = nckhTiendoList.get(nckhTiendoList.size() - 1);
        assertThat(testNckhTiendo.getTienDoHoanThanh()).isEqualTo(DEFAULT_TIEN_DO_HOAN_THANH);
        assertThat(testNckhTiendo.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testNckhTiendo.getNgayCapNhat()).isEqualTo(DEFAULT_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    public void createNckhTiendoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nckhTiendoRepository.findAll().size();

        // Create the NckhTiendo with an existing ID
        nckhTiendo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNckhTiendoMockMvc.perform(post("/api/nckh-tiendos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhTiendo)))
            .andExpect(status().isBadRequest());

        // Validate the NckhTiendo in the database
        List<NckhTiendo> nckhTiendoList = nckhTiendoRepository.findAll();
        assertThat(nckhTiendoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNckhTiendos() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList
        restNckhTiendoMockMvc.perform(get("/api/nckh-tiendos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhTiendo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tienDoHoanThanh").value(hasItem(DEFAULT_TIEN_DO_HOAN_THANH.intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].ngayCapNhat").value(hasItem(DEFAULT_NGAY_CAP_NHAT.toString())));
    }
    
    @Test
    @Transactional
    public void getNckhTiendo() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get the nckhTiendo
        restNckhTiendoMockMvc.perform(get("/api/nckh-tiendos/{id}", nckhTiendo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nckhTiendo.getId().intValue()))
            .andExpect(jsonPath("$.tienDoHoanThanh").value(DEFAULT_TIEN_DO_HOAN_THANH.intValue()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.ngayCapNhat").value(DEFAULT_NGAY_CAP_NHAT.toString()));
    }


    @Test
    @Transactional
    public void getNckhTiendosByIdFiltering() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        Long id = nckhTiendo.getId();

        defaultNckhTiendoShouldBeFound("id.equals=" + id);
        defaultNckhTiendoShouldNotBeFound("id.notEquals=" + id);

        defaultNckhTiendoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNckhTiendoShouldNotBeFound("id.greaterThan=" + id);

        defaultNckhTiendoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNckhTiendoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllNckhTiendosByTienDoHoanThanhIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where tienDoHoanThanh equals to DEFAULT_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldBeFound("tienDoHoanThanh.equals=" + DEFAULT_TIEN_DO_HOAN_THANH);

        // Get all the nckhTiendoList where tienDoHoanThanh equals to UPDATED_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldNotBeFound("tienDoHoanThanh.equals=" + UPDATED_TIEN_DO_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByTienDoHoanThanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where tienDoHoanThanh not equals to DEFAULT_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldNotBeFound("tienDoHoanThanh.notEquals=" + DEFAULT_TIEN_DO_HOAN_THANH);

        // Get all the nckhTiendoList where tienDoHoanThanh not equals to UPDATED_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldBeFound("tienDoHoanThanh.notEquals=" + UPDATED_TIEN_DO_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByTienDoHoanThanhIsInShouldWork() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where tienDoHoanThanh in DEFAULT_TIEN_DO_HOAN_THANH or UPDATED_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldBeFound("tienDoHoanThanh.in=" + DEFAULT_TIEN_DO_HOAN_THANH + "," + UPDATED_TIEN_DO_HOAN_THANH);

        // Get all the nckhTiendoList where tienDoHoanThanh equals to UPDATED_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldNotBeFound("tienDoHoanThanh.in=" + UPDATED_TIEN_DO_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByTienDoHoanThanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where tienDoHoanThanh is not null
        defaultNckhTiendoShouldBeFound("tienDoHoanThanh.specified=true");

        // Get all the nckhTiendoList where tienDoHoanThanh is null
        defaultNckhTiendoShouldNotBeFound("tienDoHoanThanh.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByTienDoHoanThanhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where tienDoHoanThanh is greater than or equal to DEFAULT_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldBeFound("tienDoHoanThanh.greaterThanOrEqual=" + DEFAULT_TIEN_DO_HOAN_THANH);

        // Get all the nckhTiendoList where tienDoHoanThanh is greater than or equal to UPDATED_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldNotBeFound("tienDoHoanThanh.greaterThanOrEqual=" + UPDATED_TIEN_DO_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByTienDoHoanThanhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where tienDoHoanThanh is less than or equal to DEFAULT_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldBeFound("tienDoHoanThanh.lessThanOrEqual=" + DEFAULT_TIEN_DO_HOAN_THANH);

        // Get all the nckhTiendoList where tienDoHoanThanh is less than or equal to SMALLER_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldNotBeFound("tienDoHoanThanh.lessThanOrEqual=" + SMALLER_TIEN_DO_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByTienDoHoanThanhIsLessThanSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where tienDoHoanThanh is less than DEFAULT_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldNotBeFound("tienDoHoanThanh.lessThan=" + DEFAULT_TIEN_DO_HOAN_THANH);

        // Get all the nckhTiendoList where tienDoHoanThanh is less than UPDATED_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldBeFound("tienDoHoanThanh.lessThan=" + UPDATED_TIEN_DO_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByTienDoHoanThanhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where tienDoHoanThanh is greater than DEFAULT_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldNotBeFound("tienDoHoanThanh.greaterThan=" + DEFAULT_TIEN_DO_HOAN_THANH);

        // Get all the nckhTiendoList where tienDoHoanThanh is greater than SMALLER_TIEN_DO_HOAN_THANH
        defaultNckhTiendoShouldBeFound("tienDoHoanThanh.greaterThan=" + SMALLER_TIEN_DO_HOAN_THANH);
    }


    @Test
    @Transactional
    public void getAllNckhTiendosByMoTaIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where moTa equals to DEFAULT_MO_TA
        defaultNckhTiendoShouldBeFound("moTa.equals=" + DEFAULT_MO_TA);

        // Get all the nckhTiendoList where moTa equals to UPDATED_MO_TA
        defaultNckhTiendoShouldNotBeFound("moTa.equals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByMoTaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where moTa not equals to DEFAULT_MO_TA
        defaultNckhTiendoShouldNotBeFound("moTa.notEquals=" + DEFAULT_MO_TA);

        // Get all the nckhTiendoList where moTa not equals to UPDATED_MO_TA
        defaultNckhTiendoShouldBeFound("moTa.notEquals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByMoTaIsInShouldWork() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where moTa in DEFAULT_MO_TA or UPDATED_MO_TA
        defaultNckhTiendoShouldBeFound("moTa.in=" + DEFAULT_MO_TA + "," + UPDATED_MO_TA);

        // Get all the nckhTiendoList where moTa equals to UPDATED_MO_TA
        defaultNckhTiendoShouldNotBeFound("moTa.in=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByMoTaIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where moTa is not null
        defaultNckhTiendoShouldBeFound("moTa.specified=true");

        // Get all the nckhTiendoList where moTa is null
        defaultNckhTiendoShouldNotBeFound("moTa.specified=false");
    }
                @Test
    @Transactional
    public void getAllNckhTiendosByMoTaContainsSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where moTa contains DEFAULT_MO_TA
        defaultNckhTiendoShouldBeFound("moTa.contains=" + DEFAULT_MO_TA);

        // Get all the nckhTiendoList where moTa contains UPDATED_MO_TA
        defaultNckhTiendoShouldNotBeFound("moTa.contains=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByMoTaNotContainsSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where moTa does not contain DEFAULT_MO_TA
        defaultNckhTiendoShouldNotBeFound("moTa.doesNotContain=" + DEFAULT_MO_TA);

        // Get all the nckhTiendoList where moTa does not contain UPDATED_MO_TA
        defaultNckhTiendoShouldBeFound("moTa.doesNotContain=" + UPDATED_MO_TA);
    }


    @Test
    @Transactional
    public void getAllNckhTiendosByNgayCapNhatIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where ngayCapNhat equals to DEFAULT_NGAY_CAP_NHAT
        defaultNckhTiendoShouldBeFound("ngayCapNhat.equals=" + DEFAULT_NGAY_CAP_NHAT);

        // Get all the nckhTiendoList where ngayCapNhat equals to UPDATED_NGAY_CAP_NHAT
        defaultNckhTiendoShouldNotBeFound("ngayCapNhat.equals=" + UPDATED_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByNgayCapNhatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where ngayCapNhat not equals to DEFAULT_NGAY_CAP_NHAT
        defaultNckhTiendoShouldNotBeFound("ngayCapNhat.notEquals=" + DEFAULT_NGAY_CAP_NHAT);

        // Get all the nckhTiendoList where ngayCapNhat not equals to UPDATED_NGAY_CAP_NHAT
        defaultNckhTiendoShouldBeFound("ngayCapNhat.notEquals=" + UPDATED_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByNgayCapNhatIsInShouldWork() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where ngayCapNhat in DEFAULT_NGAY_CAP_NHAT or UPDATED_NGAY_CAP_NHAT
        defaultNckhTiendoShouldBeFound("ngayCapNhat.in=" + DEFAULT_NGAY_CAP_NHAT + "," + UPDATED_NGAY_CAP_NHAT);

        // Get all the nckhTiendoList where ngayCapNhat equals to UPDATED_NGAY_CAP_NHAT
        defaultNckhTiendoShouldNotBeFound("ngayCapNhat.in=" + UPDATED_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByNgayCapNhatIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);

        // Get all the nckhTiendoList where ngayCapNhat is not null
        defaultNckhTiendoShouldBeFound("ngayCapNhat.specified=true");

        // Get all the nckhTiendoList where ngayCapNhat is null
        defaultNckhTiendoShouldNotBeFound("ngayCapNhat.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhTiendosByNckhDetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhTiendoRepository.saveAndFlush(nckhTiendo);
        NckhDetai nckhDetai = NckhDetaiResourceIT.createEntity(em);
        em.persist(nckhDetai);
        em.flush();
        nckhTiendo.setNckhDetai(nckhDetai);
        nckhTiendoRepository.saveAndFlush(nckhTiendo);
        Long nckhDetaiId = nckhDetai.getId();

        // Get all the nckhTiendoList where nckhDetai equals to nckhDetaiId
        defaultNckhTiendoShouldBeFound("nckhDetaiId.equals=" + nckhDetaiId);

        // Get all the nckhTiendoList where nckhDetai equals to nckhDetaiId + 1
        defaultNckhTiendoShouldNotBeFound("nckhDetaiId.equals=" + (nckhDetaiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNckhTiendoShouldBeFound(String filter) throws Exception {
        restNckhTiendoMockMvc.perform(get("/api/nckh-tiendos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhTiendo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tienDoHoanThanh").value(hasItem(DEFAULT_TIEN_DO_HOAN_THANH.intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].ngayCapNhat").value(hasItem(DEFAULT_NGAY_CAP_NHAT.toString())));

        // Check, that the count call also returns 1
        restNckhTiendoMockMvc.perform(get("/api/nckh-tiendos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNckhTiendoShouldNotBeFound(String filter) throws Exception {
        restNckhTiendoMockMvc.perform(get("/api/nckh-tiendos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNckhTiendoMockMvc.perform(get("/api/nckh-tiendos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNckhTiendo() throws Exception {
        // Get the nckhTiendo
        restNckhTiendoMockMvc.perform(get("/api/nckh-tiendos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNckhTiendo() throws Exception {
        // Initialize the database
        nckhTiendoService.save(nckhTiendo);

        int databaseSizeBeforeUpdate = nckhTiendoRepository.findAll().size();

        // Update the nckhTiendo
        NckhTiendo updatedNckhTiendo = nckhTiendoRepository.findById(nckhTiendo.getId()).get();
        // Disconnect from session so that the updates on updatedNckhTiendo are not directly saved in db
        em.detach(updatedNckhTiendo);
        updatedNckhTiendo
            .tienDoHoanThanh(UPDATED_TIEN_DO_HOAN_THANH)
            .moTa(UPDATED_MO_TA)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);

        restNckhTiendoMockMvc.perform(put("/api/nckh-tiendos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNckhTiendo)))
            .andExpect(status().isOk());

        // Validate the NckhTiendo in the database
        List<NckhTiendo> nckhTiendoList = nckhTiendoRepository.findAll();
        assertThat(nckhTiendoList).hasSize(databaseSizeBeforeUpdate);
        NckhTiendo testNckhTiendo = nckhTiendoList.get(nckhTiendoList.size() - 1);
        assertThat(testNckhTiendo.getTienDoHoanThanh()).isEqualTo(UPDATED_TIEN_DO_HOAN_THANH);
        assertThat(testNckhTiendo.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testNckhTiendo.getNgayCapNhat()).isEqualTo(UPDATED_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    public void updateNonExistingNckhTiendo() throws Exception {
        int databaseSizeBeforeUpdate = nckhTiendoRepository.findAll().size();

        // Create the NckhTiendo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNckhTiendoMockMvc.perform(put("/api/nckh-tiendos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhTiendo)))
            .andExpect(status().isBadRequest());

        // Validate the NckhTiendo in the database
        List<NckhTiendo> nckhTiendoList = nckhTiendoRepository.findAll();
        assertThat(nckhTiendoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNckhTiendo() throws Exception {
        // Initialize the database
        nckhTiendoService.save(nckhTiendo);

        int databaseSizeBeforeDelete = nckhTiendoRepository.findAll().size();

        // Delete the nckhTiendo
        restNckhTiendoMockMvc.perform(delete("/api/nckh-tiendos/{id}", nckhTiendo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NckhTiendo> nckhTiendoList = nckhTiendoRepository.findAll();
        assertThat(nckhTiendoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
