package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.NckhApp;
import com.vnpt.nckh.domain.NckhDetai;
import com.vnpt.nckh.domain.NckhNhanSu;
import com.vnpt.nckh.domain.NckhTiendo;
import com.vnpt.nckh.domain.NckhUpfile;
import com.vnpt.nckh.domain.NckhDanhmuc;
import com.vnpt.nckh.repository.NckhDetaiRepository;
import com.vnpt.nckh.service.NckhDetaiService;
import com.vnpt.nckh.web.rest.errors.ExceptionTranslator;
import com.vnpt.nckh.service.dto.NckhDetaiCriteria;
import com.vnpt.nckh.service.NckhDetaiQueryService;

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
 * Integration tests for the {@link NckhDetaiResource} REST controller.
 */
@SpringBootTest(classes = NckhApp.class)
public class NckhDetaiResourceIT {

    private static final String DEFAULT_MA_DETAI = "AAAAAAAAAA";
    private static final String UPDATED_MA_DETAI = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_DETAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DETAI = "BBBBBBBBBB";

    private static final String DEFAULT_MUCTIEU = "AAAAAAAAAA";
    private static final String UPDATED_MUCTIEU = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_BD_DU_KIEN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_BD_DU_KIEN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_NGAY_KT_DU_KIEN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_KT_DU_KIEN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_KINH_PHI_DU_KIEN = 1F;
    private static final Float UPDATED_KINH_PHI_DU_KIEN = 2F;
    private static final Float SMALLER_KINH_PHI_DU_KIEN = 1F - 1F;

    private static final String DEFAULT_NOI_DUNG_TONG_QUAN = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG_TONG_QUAN = "BBBBBBBBBB";

    private static final Float DEFAULT_KINH_PHI_HOAN_THANH = 1F;
    private static final Float UPDATED_KINH_PHI_HOAN_THANH = 2F;
    private static final Float SMALLER_KINH_PHI_HOAN_THANH = 1F - 1F;

    private static final Float DEFAULT_TONG_DIEM = 1F;
    private static final Float UPDATED_TONG_DIEM = 2F;
    private static final Float SMALLER_TONG_DIEM = 1F - 1F;

    @Autowired
    private NckhDetaiRepository nckhDetaiRepository;

    @Autowired
    private NckhDetaiService nckhDetaiService;

    @Autowired
    private NckhDetaiQueryService nckhDetaiQueryService;

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

    private MockMvc restNckhDetaiMockMvc;

    private NckhDetai nckhDetai;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NckhDetaiResource nckhDetaiResource = new NckhDetaiResource(nckhDetaiService, nckhDetaiQueryService);
        this.restNckhDetaiMockMvc = MockMvcBuilders.standaloneSetup(nckhDetaiResource)
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
    public static NckhDetai createEntity(EntityManager em) {
        NckhDetai nckhDetai = new NckhDetai()
            .maDetai(DEFAULT_MA_DETAI)
            .tenDetai(DEFAULT_TEN_DETAI)
            .muctieu(DEFAULT_MUCTIEU)
            .ngayBdDuKien(DEFAULT_NGAY_BD_DU_KIEN)
            .ngayKtDuKien(DEFAULT_NGAY_KT_DU_KIEN)
            .kinhPhiDuKien(DEFAULT_KINH_PHI_DU_KIEN)
            .noiDungTongQuan(DEFAULT_NOI_DUNG_TONG_QUAN)
            .kinhPhiHoanThanh(DEFAULT_KINH_PHI_HOAN_THANH)
            .tongDiem(DEFAULT_TONG_DIEM);
        return nckhDetai;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NckhDetai createUpdatedEntity(EntityManager em) {
        NckhDetai nckhDetai = new NckhDetai()
            .maDetai(UPDATED_MA_DETAI)
            .tenDetai(UPDATED_TEN_DETAI)
            .muctieu(UPDATED_MUCTIEU)
            .ngayBdDuKien(UPDATED_NGAY_BD_DU_KIEN)
            .ngayKtDuKien(UPDATED_NGAY_KT_DU_KIEN)
            .kinhPhiDuKien(UPDATED_KINH_PHI_DU_KIEN)
            .noiDungTongQuan(UPDATED_NOI_DUNG_TONG_QUAN)
            .kinhPhiHoanThanh(UPDATED_KINH_PHI_HOAN_THANH)
            .tongDiem(UPDATED_TONG_DIEM);
        return nckhDetai;
    }

    @BeforeEach
    public void initTest() {
        nckhDetai = createEntity(em);
    }

    @Test
    @Transactional
    public void createNckhDetai() throws Exception {
        int databaseSizeBeforeCreate = nckhDetaiRepository.findAll().size();

        // Create the NckhDetai
        restNckhDetaiMockMvc.perform(post("/api/nckh-detais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhDetai)))
            .andExpect(status().isCreated());

        // Validate the NckhDetai in the database
        List<NckhDetai> nckhDetaiList = nckhDetaiRepository.findAll();
        assertThat(nckhDetaiList).hasSize(databaseSizeBeforeCreate + 1);
        NckhDetai testNckhDetai = nckhDetaiList.get(nckhDetaiList.size() - 1);
        assertThat(testNckhDetai.getMaDetai()).isEqualTo(DEFAULT_MA_DETAI);
        assertThat(testNckhDetai.getTenDetai()).isEqualTo(DEFAULT_TEN_DETAI);
        assertThat(testNckhDetai.getMuctieu()).isEqualTo(DEFAULT_MUCTIEU);
        assertThat(testNckhDetai.getNgayBdDuKien()).isEqualTo(DEFAULT_NGAY_BD_DU_KIEN);
        assertThat(testNckhDetai.getNgayKtDuKien()).isEqualTo(DEFAULT_NGAY_KT_DU_KIEN);
        assertThat(testNckhDetai.getKinhPhiDuKien()).isEqualTo(DEFAULT_KINH_PHI_DU_KIEN);
        assertThat(testNckhDetai.getNoiDungTongQuan()).isEqualTo(DEFAULT_NOI_DUNG_TONG_QUAN);
        assertThat(testNckhDetai.getKinhPhiHoanThanh()).isEqualTo(DEFAULT_KINH_PHI_HOAN_THANH);
        assertThat(testNckhDetai.getTongDiem()).isEqualTo(DEFAULT_TONG_DIEM);
    }

    @Test
    @Transactional
    public void createNckhDetaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nckhDetaiRepository.findAll().size();

        // Create the NckhDetai with an existing ID
        nckhDetai.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNckhDetaiMockMvc.perform(post("/api/nckh-detais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhDetai)))
            .andExpect(status().isBadRequest());

        // Validate the NckhDetai in the database
        List<NckhDetai> nckhDetaiList = nckhDetaiRepository.findAll();
        assertThat(nckhDetaiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaDetaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = nckhDetaiRepository.findAll().size();
        // set the field null
        nckhDetai.setMaDetai(null);

        // Create the NckhDetai, which fails.

        restNckhDetaiMockMvc.perform(post("/api/nckh-detais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhDetai)))
            .andExpect(status().isBadRequest());

        List<NckhDetai> nckhDetaiList = nckhDetaiRepository.findAll();
        assertThat(nckhDetaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTenDetaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = nckhDetaiRepository.findAll().size();
        // set the field null
        nckhDetai.setTenDetai(null);

        // Create the NckhDetai, which fails.

        restNckhDetaiMockMvc.perform(post("/api/nckh-detais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhDetai)))
            .andExpect(status().isBadRequest());

        List<NckhDetai> nckhDetaiList = nckhDetaiRepository.findAll();
        assertThat(nckhDetaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNckhDetais() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList
        restNckhDetaiMockMvc.perform(get("/api/nckh-detais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhDetai.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDetai").value(hasItem(DEFAULT_MA_DETAI)))
            .andExpect(jsonPath("$.[*].tenDetai").value(hasItem(DEFAULT_TEN_DETAI)))
            .andExpect(jsonPath("$.[*].muctieu").value(hasItem(DEFAULT_MUCTIEU)))
            .andExpect(jsonPath("$.[*].ngayBdDuKien").value(hasItem(DEFAULT_NGAY_BD_DU_KIEN.toString())))
            .andExpect(jsonPath("$.[*].ngayKtDuKien").value(hasItem(DEFAULT_NGAY_KT_DU_KIEN.toString())))
            .andExpect(jsonPath("$.[*].kinhPhiDuKien").value(hasItem(DEFAULT_KINH_PHI_DU_KIEN.doubleValue())))
            .andExpect(jsonPath("$.[*].noiDungTongQuan").value(hasItem(DEFAULT_NOI_DUNG_TONG_QUAN)))
            .andExpect(jsonPath("$.[*].kinhPhiHoanThanh").value(hasItem(DEFAULT_KINH_PHI_HOAN_THANH.doubleValue())))
            .andExpect(jsonPath("$.[*].tongDiem").value(hasItem(DEFAULT_TONG_DIEM.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getNckhDetai() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get the nckhDetai
        restNckhDetaiMockMvc.perform(get("/api/nckh-detais/{id}", nckhDetai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nckhDetai.getId().intValue()))
            .andExpect(jsonPath("$.maDetai").value(DEFAULT_MA_DETAI))
            .andExpect(jsonPath("$.tenDetai").value(DEFAULT_TEN_DETAI))
            .andExpect(jsonPath("$.muctieu").value(DEFAULT_MUCTIEU))
            .andExpect(jsonPath("$.ngayBdDuKien").value(DEFAULT_NGAY_BD_DU_KIEN.toString()))
            .andExpect(jsonPath("$.ngayKtDuKien").value(DEFAULT_NGAY_KT_DU_KIEN.toString()))
            .andExpect(jsonPath("$.kinhPhiDuKien").value(DEFAULT_KINH_PHI_DU_KIEN.doubleValue()))
            .andExpect(jsonPath("$.noiDungTongQuan").value(DEFAULT_NOI_DUNG_TONG_QUAN))
            .andExpect(jsonPath("$.kinhPhiHoanThanh").value(DEFAULT_KINH_PHI_HOAN_THANH.doubleValue()))
            .andExpect(jsonPath("$.tongDiem").value(DEFAULT_TONG_DIEM.doubleValue()));
    }


    @Test
    @Transactional
    public void getNckhDetaisByIdFiltering() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        Long id = nckhDetai.getId();

        defaultNckhDetaiShouldBeFound("id.equals=" + id);
        defaultNckhDetaiShouldNotBeFound("id.notEquals=" + id);

        defaultNckhDetaiShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNckhDetaiShouldNotBeFound("id.greaterThan=" + id);

        defaultNckhDetaiShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNckhDetaiShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByMaDetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where maDetai equals to DEFAULT_MA_DETAI
        defaultNckhDetaiShouldBeFound("maDetai.equals=" + DEFAULT_MA_DETAI);

        // Get all the nckhDetaiList where maDetai equals to UPDATED_MA_DETAI
        defaultNckhDetaiShouldNotBeFound("maDetai.equals=" + UPDATED_MA_DETAI);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByMaDetaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where maDetai not equals to DEFAULT_MA_DETAI
        defaultNckhDetaiShouldNotBeFound("maDetai.notEquals=" + DEFAULT_MA_DETAI);

        // Get all the nckhDetaiList where maDetai not equals to UPDATED_MA_DETAI
        defaultNckhDetaiShouldBeFound("maDetai.notEquals=" + UPDATED_MA_DETAI);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByMaDetaiIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where maDetai in DEFAULT_MA_DETAI or UPDATED_MA_DETAI
        defaultNckhDetaiShouldBeFound("maDetai.in=" + DEFAULT_MA_DETAI + "," + UPDATED_MA_DETAI);

        // Get all the nckhDetaiList where maDetai equals to UPDATED_MA_DETAI
        defaultNckhDetaiShouldNotBeFound("maDetai.in=" + UPDATED_MA_DETAI);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByMaDetaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where maDetai is not null
        defaultNckhDetaiShouldBeFound("maDetai.specified=true");

        // Get all the nckhDetaiList where maDetai is null
        defaultNckhDetaiShouldNotBeFound("maDetai.specified=false");
    }
                @Test
    @Transactional
    public void getAllNckhDetaisByMaDetaiContainsSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where maDetai contains DEFAULT_MA_DETAI
        defaultNckhDetaiShouldBeFound("maDetai.contains=" + DEFAULT_MA_DETAI);

        // Get all the nckhDetaiList where maDetai contains UPDATED_MA_DETAI
        defaultNckhDetaiShouldNotBeFound("maDetai.contains=" + UPDATED_MA_DETAI);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByMaDetaiNotContainsSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where maDetai does not contain DEFAULT_MA_DETAI
        defaultNckhDetaiShouldNotBeFound("maDetai.doesNotContain=" + DEFAULT_MA_DETAI);

        // Get all the nckhDetaiList where maDetai does not contain UPDATED_MA_DETAI
        defaultNckhDetaiShouldBeFound("maDetai.doesNotContain=" + UPDATED_MA_DETAI);
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByTenDetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tenDetai equals to DEFAULT_TEN_DETAI
        defaultNckhDetaiShouldBeFound("tenDetai.equals=" + DEFAULT_TEN_DETAI);

        // Get all the nckhDetaiList where tenDetai equals to UPDATED_TEN_DETAI
        defaultNckhDetaiShouldNotBeFound("tenDetai.equals=" + UPDATED_TEN_DETAI);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTenDetaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tenDetai not equals to DEFAULT_TEN_DETAI
        defaultNckhDetaiShouldNotBeFound("tenDetai.notEquals=" + DEFAULT_TEN_DETAI);

        // Get all the nckhDetaiList where tenDetai not equals to UPDATED_TEN_DETAI
        defaultNckhDetaiShouldBeFound("tenDetai.notEquals=" + UPDATED_TEN_DETAI);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTenDetaiIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tenDetai in DEFAULT_TEN_DETAI or UPDATED_TEN_DETAI
        defaultNckhDetaiShouldBeFound("tenDetai.in=" + DEFAULT_TEN_DETAI + "," + UPDATED_TEN_DETAI);

        // Get all the nckhDetaiList where tenDetai equals to UPDATED_TEN_DETAI
        defaultNckhDetaiShouldNotBeFound("tenDetai.in=" + UPDATED_TEN_DETAI);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTenDetaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tenDetai is not null
        defaultNckhDetaiShouldBeFound("tenDetai.specified=true");

        // Get all the nckhDetaiList where tenDetai is null
        defaultNckhDetaiShouldNotBeFound("tenDetai.specified=false");
    }
                @Test
    @Transactional
    public void getAllNckhDetaisByTenDetaiContainsSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tenDetai contains DEFAULT_TEN_DETAI
        defaultNckhDetaiShouldBeFound("tenDetai.contains=" + DEFAULT_TEN_DETAI);

        // Get all the nckhDetaiList where tenDetai contains UPDATED_TEN_DETAI
        defaultNckhDetaiShouldNotBeFound("tenDetai.contains=" + UPDATED_TEN_DETAI);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTenDetaiNotContainsSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tenDetai does not contain DEFAULT_TEN_DETAI
        defaultNckhDetaiShouldNotBeFound("tenDetai.doesNotContain=" + DEFAULT_TEN_DETAI);

        // Get all the nckhDetaiList where tenDetai does not contain UPDATED_TEN_DETAI
        defaultNckhDetaiShouldBeFound("tenDetai.doesNotContain=" + UPDATED_TEN_DETAI);
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByMuctieuIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where muctieu equals to DEFAULT_MUCTIEU
        defaultNckhDetaiShouldBeFound("muctieu.equals=" + DEFAULT_MUCTIEU);

        // Get all the nckhDetaiList where muctieu equals to UPDATED_MUCTIEU
        defaultNckhDetaiShouldNotBeFound("muctieu.equals=" + UPDATED_MUCTIEU);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByMuctieuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where muctieu not equals to DEFAULT_MUCTIEU
        defaultNckhDetaiShouldNotBeFound("muctieu.notEquals=" + DEFAULT_MUCTIEU);

        // Get all the nckhDetaiList where muctieu not equals to UPDATED_MUCTIEU
        defaultNckhDetaiShouldBeFound("muctieu.notEquals=" + UPDATED_MUCTIEU);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByMuctieuIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where muctieu in DEFAULT_MUCTIEU or UPDATED_MUCTIEU
        defaultNckhDetaiShouldBeFound("muctieu.in=" + DEFAULT_MUCTIEU + "," + UPDATED_MUCTIEU);

        // Get all the nckhDetaiList where muctieu equals to UPDATED_MUCTIEU
        defaultNckhDetaiShouldNotBeFound("muctieu.in=" + UPDATED_MUCTIEU);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByMuctieuIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where muctieu is not null
        defaultNckhDetaiShouldBeFound("muctieu.specified=true");

        // Get all the nckhDetaiList where muctieu is null
        defaultNckhDetaiShouldNotBeFound("muctieu.specified=false");
    }
                @Test
    @Transactional
    public void getAllNckhDetaisByMuctieuContainsSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where muctieu contains DEFAULT_MUCTIEU
        defaultNckhDetaiShouldBeFound("muctieu.contains=" + DEFAULT_MUCTIEU);

        // Get all the nckhDetaiList where muctieu contains UPDATED_MUCTIEU
        defaultNckhDetaiShouldNotBeFound("muctieu.contains=" + UPDATED_MUCTIEU);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByMuctieuNotContainsSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where muctieu does not contain DEFAULT_MUCTIEU
        defaultNckhDetaiShouldNotBeFound("muctieu.doesNotContain=" + DEFAULT_MUCTIEU);

        // Get all the nckhDetaiList where muctieu does not contain UPDATED_MUCTIEU
        defaultNckhDetaiShouldBeFound("muctieu.doesNotContain=" + UPDATED_MUCTIEU);
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByNgayBdDuKienIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where ngayBdDuKien equals to DEFAULT_NGAY_BD_DU_KIEN
        defaultNckhDetaiShouldBeFound("ngayBdDuKien.equals=" + DEFAULT_NGAY_BD_DU_KIEN);

        // Get all the nckhDetaiList where ngayBdDuKien equals to UPDATED_NGAY_BD_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("ngayBdDuKien.equals=" + UPDATED_NGAY_BD_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNgayBdDuKienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where ngayBdDuKien not equals to DEFAULT_NGAY_BD_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("ngayBdDuKien.notEquals=" + DEFAULT_NGAY_BD_DU_KIEN);

        // Get all the nckhDetaiList where ngayBdDuKien not equals to UPDATED_NGAY_BD_DU_KIEN
        defaultNckhDetaiShouldBeFound("ngayBdDuKien.notEquals=" + UPDATED_NGAY_BD_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNgayBdDuKienIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where ngayBdDuKien in DEFAULT_NGAY_BD_DU_KIEN or UPDATED_NGAY_BD_DU_KIEN
        defaultNckhDetaiShouldBeFound("ngayBdDuKien.in=" + DEFAULT_NGAY_BD_DU_KIEN + "," + UPDATED_NGAY_BD_DU_KIEN);

        // Get all the nckhDetaiList where ngayBdDuKien equals to UPDATED_NGAY_BD_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("ngayBdDuKien.in=" + UPDATED_NGAY_BD_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNgayBdDuKienIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where ngayBdDuKien is not null
        defaultNckhDetaiShouldBeFound("ngayBdDuKien.specified=true");

        // Get all the nckhDetaiList where ngayBdDuKien is null
        defaultNckhDetaiShouldNotBeFound("ngayBdDuKien.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNgayKtDuKienIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where ngayKtDuKien equals to DEFAULT_NGAY_KT_DU_KIEN
        defaultNckhDetaiShouldBeFound("ngayKtDuKien.equals=" + DEFAULT_NGAY_KT_DU_KIEN);

        // Get all the nckhDetaiList where ngayKtDuKien equals to UPDATED_NGAY_KT_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("ngayKtDuKien.equals=" + UPDATED_NGAY_KT_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNgayKtDuKienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where ngayKtDuKien not equals to DEFAULT_NGAY_KT_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("ngayKtDuKien.notEquals=" + DEFAULT_NGAY_KT_DU_KIEN);

        // Get all the nckhDetaiList where ngayKtDuKien not equals to UPDATED_NGAY_KT_DU_KIEN
        defaultNckhDetaiShouldBeFound("ngayKtDuKien.notEquals=" + UPDATED_NGAY_KT_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNgayKtDuKienIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where ngayKtDuKien in DEFAULT_NGAY_KT_DU_KIEN or UPDATED_NGAY_KT_DU_KIEN
        defaultNckhDetaiShouldBeFound("ngayKtDuKien.in=" + DEFAULT_NGAY_KT_DU_KIEN + "," + UPDATED_NGAY_KT_DU_KIEN);

        // Get all the nckhDetaiList where ngayKtDuKien equals to UPDATED_NGAY_KT_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("ngayKtDuKien.in=" + UPDATED_NGAY_KT_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNgayKtDuKienIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where ngayKtDuKien is not null
        defaultNckhDetaiShouldBeFound("ngayKtDuKien.specified=true");

        // Get all the nckhDetaiList where ngayKtDuKien is null
        defaultNckhDetaiShouldNotBeFound("ngayKtDuKien.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiDuKienIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiDuKien equals to DEFAULT_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldBeFound("kinhPhiDuKien.equals=" + DEFAULT_KINH_PHI_DU_KIEN);

        // Get all the nckhDetaiList where kinhPhiDuKien equals to UPDATED_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("kinhPhiDuKien.equals=" + UPDATED_KINH_PHI_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiDuKienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiDuKien not equals to DEFAULT_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("kinhPhiDuKien.notEquals=" + DEFAULT_KINH_PHI_DU_KIEN);

        // Get all the nckhDetaiList where kinhPhiDuKien not equals to UPDATED_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldBeFound("kinhPhiDuKien.notEquals=" + UPDATED_KINH_PHI_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiDuKienIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiDuKien in DEFAULT_KINH_PHI_DU_KIEN or UPDATED_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldBeFound("kinhPhiDuKien.in=" + DEFAULT_KINH_PHI_DU_KIEN + "," + UPDATED_KINH_PHI_DU_KIEN);

        // Get all the nckhDetaiList where kinhPhiDuKien equals to UPDATED_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("kinhPhiDuKien.in=" + UPDATED_KINH_PHI_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiDuKienIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiDuKien is not null
        defaultNckhDetaiShouldBeFound("kinhPhiDuKien.specified=true");

        // Get all the nckhDetaiList where kinhPhiDuKien is null
        defaultNckhDetaiShouldNotBeFound("kinhPhiDuKien.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiDuKienIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiDuKien is greater than or equal to DEFAULT_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldBeFound("kinhPhiDuKien.greaterThanOrEqual=" + DEFAULT_KINH_PHI_DU_KIEN);

        // Get all the nckhDetaiList where kinhPhiDuKien is greater than or equal to UPDATED_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("kinhPhiDuKien.greaterThanOrEqual=" + UPDATED_KINH_PHI_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiDuKienIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiDuKien is less than or equal to DEFAULT_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldBeFound("kinhPhiDuKien.lessThanOrEqual=" + DEFAULT_KINH_PHI_DU_KIEN);

        // Get all the nckhDetaiList where kinhPhiDuKien is less than or equal to SMALLER_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("kinhPhiDuKien.lessThanOrEqual=" + SMALLER_KINH_PHI_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiDuKienIsLessThanSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiDuKien is less than DEFAULT_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("kinhPhiDuKien.lessThan=" + DEFAULT_KINH_PHI_DU_KIEN);

        // Get all the nckhDetaiList where kinhPhiDuKien is less than UPDATED_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldBeFound("kinhPhiDuKien.lessThan=" + UPDATED_KINH_PHI_DU_KIEN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiDuKienIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiDuKien is greater than DEFAULT_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldNotBeFound("kinhPhiDuKien.greaterThan=" + DEFAULT_KINH_PHI_DU_KIEN);

        // Get all the nckhDetaiList where kinhPhiDuKien is greater than SMALLER_KINH_PHI_DU_KIEN
        defaultNckhDetaiShouldBeFound("kinhPhiDuKien.greaterThan=" + SMALLER_KINH_PHI_DU_KIEN);
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByNoiDungTongQuanIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where noiDungTongQuan equals to DEFAULT_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldBeFound("noiDungTongQuan.equals=" + DEFAULT_NOI_DUNG_TONG_QUAN);

        // Get all the nckhDetaiList where noiDungTongQuan equals to UPDATED_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldNotBeFound("noiDungTongQuan.equals=" + UPDATED_NOI_DUNG_TONG_QUAN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNoiDungTongQuanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where noiDungTongQuan not equals to DEFAULT_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldNotBeFound("noiDungTongQuan.notEquals=" + DEFAULT_NOI_DUNG_TONG_QUAN);

        // Get all the nckhDetaiList where noiDungTongQuan not equals to UPDATED_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldBeFound("noiDungTongQuan.notEquals=" + UPDATED_NOI_DUNG_TONG_QUAN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNoiDungTongQuanIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where noiDungTongQuan in DEFAULT_NOI_DUNG_TONG_QUAN or UPDATED_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldBeFound("noiDungTongQuan.in=" + DEFAULT_NOI_DUNG_TONG_QUAN + "," + UPDATED_NOI_DUNG_TONG_QUAN);

        // Get all the nckhDetaiList where noiDungTongQuan equals to UPDATED_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldNotBeFound("noiDungTongQuan.in=" + UPDATED_NOI_DUNG_TONG_QUAN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNoiDungTongQuanIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where noiDungTongQuan is not null
        defaultNckhDetaiShouldBeFound("noiDungTongQuan.specified=true");

        // Get all the nckhDetaiList where noiDungTongQuan is null
        defaultNckhDetaiShouldNotBeFound("noiDungTongQuan.specified=false");
    }
                @Test
    @Transactional
    public void getAllNckhDetaisByNoiDungTongQuanContainsSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where noiDungTongQuan contains DEFAULT_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldBeFound("noiDungTongQuan.contains=" + DEFAULT_NOI_DUNG_TONG_QUAN);

        // Get all the nckhDetaiList where noiDungTongQuan contains UPDATED_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldNotBeFound("noiDungTongQuan.contains=" + UPDATED_NOI_DUNG_TONG_QUAN);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByNoiDungTongQuanNotContainsSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where noiDungTongQuan does not contain DEFAULT_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldNotBeFound("noiDungTongQuan.doesNotContain=" + DEFAULT_NOI_DUNG_TONG_QUAN);

        // Get all the nckhDetaiList where noiDungTongQuan does not contain UPDATED_NOI_DUNG_TONG_QUAN
        defaultNckhDetaiShouldBeFound("noiDungTongQuan.doesNotContain=" + UPDATED_NOI_DUNG_TONG_QUAN);
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiHoanThanhIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiHoanThanh equals to DEFAULT_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldBeFound("kinhPhiHoanThanh.equals=" + DEFAULT_KINH_PHI_HOAN_THANH);

        // Get all the nckhDetaiList where kinhPhiHoanThanh equals to UPDATED_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldNotBeFound("kinhPhiHoanThanh.equals=" + UPDATED_KINH_PHI_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiHoanThanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiHoanThanh not equals to DEFAULT_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldNotBeFound("kinhPhiHoanThanh.notEquals=" + DEFAULT_KINH_PHI_HOAN_THANH);

        // Get all the nckhDetaiList where kinhPhiHoanThanh not equals to UPDATED_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldBeFound("kinhPhiHoanThanh.notEquals=" + UPDATED_KINH_PHI_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiHoanThanhIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiHoanThanh in DEFAULT_KINH_PHI_HOAN_THANH or UPDATED_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldBeFound("kinhPhiHoanThanh.in=" + DEFAULT_KINH_PHI_HOAN_THANH + "," + UPDATED_KINH_PHI_HOAN_THANH);

        // Get all the nckhDetaiList where kinhPhiHoanThanh equals to UPDATED_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldNotBeFound("kinhPhiHoanThanh.in=" + UPDATED_KINH_PHI_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiHoanThanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiHoanThanh is not null
        defaultNckhDetaiShouldBeFound("kinhPhiHoanThanh.specified=true");

        // Get all the nckhDetaiList where kinhPhiHoanThanh is null
        defaultNckhDetaiShouldNotBeFound("kinhPhiHoanThanh.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiHoanThanhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiHoanThanh is greater than or equal to DEFAULT_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldBeFound("kinhPhiHoanThanh.greaterThanOrEqual=" + DEFAULT_KINH_PHI_HOAN_THANH);

        // Get all the nckhDetaiList where kinhPhiHoanThanh is greater than or equal to UPDATED_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldNotBeFound("kinhPhiHoanThanh.greaterThanOrEqual=" + UPDATED_KINH_PHI_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiHoanThanhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiHoanThanh is less than or equal to DEFAULT_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldBeFound("kinhPhiHoanThanh.lessThanOrEqual=" + DEFAULT_KINH_PHI_HOAN_THANH);

        // Get all the nckhDetaiList where kinhPhiHoanThanh is less than or equal to SMALLER_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldNotBeFound("kinhPhiHoanThanh.lessThanOrEqual=" + SMALLER_KINH_PHI_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiHoanThanhIsLessThanSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiHoanThanh is less than DEFAULT_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldNotBeFound("kinhPhiHoanThanh.lessThan=" + DEFAULT_KINH_PHI_HOAN_THANH);

        // Get all the nckhDetaiList where kinhPhiHoanThanh is less than UPDATED_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldBeFound("kinhPhiHoanThanh.lessThan=" + UPDATED_KINH_PHI_HOAN_THANH);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByKinhPhiHoanThanhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where kinhPhiHoanThanh is greater than DEFAULT_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldNotBeFound("kinhPhiHoanThanh.greaterThan=" + DEFAULT_KINH_PHI_HOAN_THANH);

        // Get all the nckhDetaiList where kinhPhiHoanThanh is greater than SMALLER_KINH_PHI_HOAN_THANH
        defaultNckhDetaiShouldBeFound("kinhPhiHoanThanh.greaterThan=" + SMALLER_KINH_PHI_HOAN_THANH);
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByTongDiemIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tongDiem equals to DEFAULT_TONG_DIEM
        defaultNckhDetaiShouldBeFound("tongDiem.equals=" + DEFAULT_TONG_DIEM);

        // Get all the nckhDetaiList where tongDiem equals to UPDATED_TONG_DIEM
        defaultNckhDetaiShouldNotBeFound("tongDiem.equals=" + UPDATED_TONG_DIEM);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTongDiemIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tongDiem not equals to DEFAULT_TONG_DIEM
        defaultNckhDetaiShouldNotBeFound("tongDiem.notEquals=" + DEFAULT_TONG_DIEM);

        // Get all the nckhDetaiList where tongDiem not equals to UPDATED_TONG_DIEM
        defaultNckhDetaiShouldBeFound("tongDiem.notEquals=" + UPDATED_TONG_DIEM);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTongDiemIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tongDiem in DEFAULT_TONG_DIEM or UPDATED_TONG_DIEM
        defaultNckhDetaiShouldBeFound("tongDiem.in=" + DEFAULT_TONG_DIEM + "," + UPDATED_TONG_DIEM);

        // Get all the nckhDetaiList where tongDiem equals to UPDATED_TONG_DIEM
        defaultNckhDetaiShouldNotBeFound("tongDiem.in=" + UPDATED_TONG_DIEM);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTongDiemIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tongDiem is not null
        defaultNckhDetaiShouldBeFound("tongDiem.specified=true");

        // Get all the nckhDetaiList where tongDiem is null
        defaultNckhDetaiShouldNotBeFound("tongDiem.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTongDiemIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tongDiem is greater than or equal to DEFAULT_TONG_DIEM
        defaultNckhDetaiShouldBeFound("tongDiem.greaterThanOrEqual=" + DEFAULT_TONG_DIEM);

        // Get all the nckhDetaiList where tongDiem is greater than or equal to UPDATED_TONG_DIEM
        defaultNckhDetaiShouldNotBeFound("tongDiem.greaterThanOrEqual=" + UPDATED_TONG_DIEM);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTongDiemIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tongDiem is less than or equal to DEFAULT_TONG_DIEM
        defaultNckhDetaiShouldBeFound("tongDiem.lessThanOrEqual=" + DEFAULT_TONG_DIEM);

        // Get all the nckhDetaiList where tongDiem is less than or equal to SMALLER_TONG_DIEM
        defaultNckhDetaiShouldNotBeFound("tongDiem.lessThanOrEqual=" + SMALLER_TONG_DIEM);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTongDiemIsLessThanSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tongDiem is less than DEFAULT_TONG_DIEM
        defaultNckhDetaiShouldNotBeFound("tongDiem.lessThan=" + DEFAULT_TONG_DIEM);

        // Get all the nckhDetaiList where tongDiem is less than UPDATED_TONG_DIEM
        defaultNckhDetaiShouldBeFound("tongDiem.lessThan=" + UPDATED_TONG_DIEM);
    }

    @Test
    @Transactional
    public void getAllNckhDetaisByTongDiemIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);

        // Get all the nckhDetaiList where tongDiem is greater than DEFAULT_TONG_DIEM
        defaultNckhDetaiShouldNotBeFound("tongDiem.greaterThan=" + DEFAULT_TONG_DIEM);

        // Get all the nckhDetaiList where tongDiem is greater than SMALLER_TONG_DIEM
        defaultNckhDetaiShouldBeFound("tongDiem.greaterThan=" + SMALLER_TONG_DIEM);
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByNckhNhanSuIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);
        NckhNhanSu nckhNhanSu = NckhNhanSuResourceIT.createEntity(em);
        em.persist(nckhNhanSu);
        em.flush();
        nckhDetai.addNckhNhanSu(nckhNhanSu);
        nckhDetaiRepository.saveAndFlush(nckhDetai);
        Long nckhNhanSuId = nckhNhanSu.getId();

        // Get all the nckhDetaiList where nckhNhanSu equals to nckhNhanSuId
        defaultNckhDetaiShouldBeFound("nckhNhanSuId.equals=" + nckhNhanSuId);

        // Get all the nckhDetaiList where nckhNhanSu equals to nckhNhanSuId + 1
        defaultNckhDetaiShouldNotBeFound("nckhNhanSuId.equals=" + (nckhNhanSuId + 1));
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByNckhTiendoIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);
        NckhTiendo nckhTiendo = NckhTiendoResourceIT.createEntity(em);
        em.persist(nckhTiendo);
        em.flush();
        nckhDetai.addNckhTiendo(nckhTiendo);
        nckhDetaiRepository.saveAndFlush(nckhDetai);
        Long nckhTiendoId = nckhTiendo.getId();

        // Get all the nckhDetaiList where nckhTiendo equals to nckhTiendoId
        defaultNckhDetaiShouldBeFound("nckhTiendoId.equals=" + nckhTiendoId);

        // Get all the nckhDetaiList where nckhTiendo equals to nckhTiendoId + 1
        defaultNckhDetaiShouldNotBeFound("nckhTiendoId.equals=" + (nckhTiendoId + 1));
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByNckhUpfileIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);
        NckhUpfile nckhUpfile = NckhUpfileResourceIT.createEntity(em);
        em.persist(nckhUpfile);
        em.flush();
        nckhDetai.addNckhUpfile(nckhUpfile);
        nckhDetaiRepository.saveAndFlush(nckhDetai);
        Long nckhUpfileId = nckhUpfile.getId();

        // Get all the nckhDetaiList where nckhUpfile equals to nckhUpfileId
        defaultNckhDetaiShouldBeFound("nckhUpfileId.equals=" + nckhUpfileId);

        // Get all the nckhDetaiList where nckhUpfile equals to nckhUpfileId + 1
        defaultNckhDetaiShouldNotBeFound("nckhUpfileId.equals=" + (nckhUpfileId + 1));
    }


    @Test
    @Transactional
    public void getAllNckhDetaisByNckhDanhmucIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDetaiRepository.saveAndFlush(nckhDetai);
        NckhDanhmuc nckhDanhmuc = NckhDanhmucResourceIT.createEntity(em);
        em.persist(nckhDanhmuc);
        em.flush();
        nckhDetai.setNckhDanhmuc(nckhDanhmuc);
        nckhDetaiRepository.saveAndFlush(nckhDetai);
        Long nckhDanhmucId = nckhDanhmuc.getId();

        // Get all the nckhDetaiList where nckhDanhmuc equals to nckhDanhmucId
        defaultNckhDetaiShouldBeFound("nckhDanhmucId.equals=" + nckhDanhmucId);

        // Get all the nckhDetaiList where nckhDanhmuc equals to nckhDanhmucId + 1
        defaultNckhDetaiShouldNotBeFound("nckhDanhmucId.equals=" + (nckhDanhmucId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNckhDetaiShouldBeFound(String filter) throws Exception {
        restNckhDetaiMockMvc.perform(get("/api/nckh-detais?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhDetai.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDetai").value(hasItem(DEFAULT_MA_DETAI)))
            .andExpect(jsonPath("$.[*].tenDetai").value(hasItem(DEFAULT_TEN_DETAI)))
            .andExpect(jsonPath("$.[*].muctieu").value(hasItem(DEFAULT_MUCTIEU)))
            .andExpect(jsonPath("$.[*].ngayBdDuKien").value(hasItem(DEFAULT_NGAY_BD_DU_KIEN.toString())))
            .andExpect(jsonPath("$.[*].ngayKtDuKien").value(hasItem(DEFAULT_NGAY_KT_DU_KIEN.toString())))
            .andExpect(jsonPath("$.[*].kinhPhiDuKien").value(hasItem(DEFAULT_KINH_PHI_DU_KIEN.doubleValue())))
            .andExpect(jsonPath("$.[*].noiDungTongQuan").value(hasItem(DEFAULT_NOI_DUNG_TONG_QUAN)))
            .andExpect(jsonPath("$.[*].kinhPhiHoanThanh").value(hasItem(DEFAULT_KINH_PHI_HOAN_THANH.doubleValue())))
            .andExpect(jsonPath("$.[*].tongDiem").value(hasItem(DEFAULT_TONG_DIEM.doubleValue())));

        // Check, that the count call also returns 1
        restNckhDetaiMockMvc.perform(get("/api/nckh-detais/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNckhDetaiShouldNotBeFound(String filter) throws Exception {
        restNckhDetaiMockMvc.perform(get("/api/nckh-detais?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNckhDetaiMockMvc.perform(get("/api/nckh-detais/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNckhDetai() throws Exception {
        // Get the nckhDetai
        restNckhDetaiMockMvc.perform(get("/api/nckh-detais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNckhDetai() throws Exception {
        // Initialize the database
        nckhDetaiService.save(nckhDetai);

        int databaseSizeBeforeUpdate = nckhDetaiRepository.findAll().size();

        // Update the nckhDetai
        NckhDetai updatedNckhDetai = nckhDetaiRepository.findById(nckhDetai.getId()).get();
        // Disconnect from session so that the updates on updatedNckhDetai are not directly saved in db
        em.detach(updatedNckhDetai);
        updatedNckhDetai
            .maDetai(UPDATED_MA_DETAI)
            .tenDetai(UPDATED_TEN_DETAI)
            .muctieu(UPDATED_MUCTIEU)
            .ngayBdDuKien(UPDATED_NGAY_BD_DU_KIEN)
            .ngayKtDuKien(UPDATED_NGAY_KT_DU_KIEN)
            .kinhPhiDuKien(UPDATED_KINH_PHI_DU_KIEN)
            .noiDungTongQuan(UPDATED_NOI_DUNG_TONG_QUAN)
            .kinhPhiHoanThanh(UPDATED_KINH_PHI_HOAN_THANH)
            .tongDiem(UPDATED_TONG_DIEM);

        restNckhDetaiMockMvc.perform(put("/api/nckh-detais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNckhDetai)))
            .andExpect(status().isOk());

        // Validate the NckhDetai in the database
        List<NckhDetai> nckhDetaiList = nckhDetaiRepository.findAll();
        assertThat(nckhDetaiList).hasSize(databaseSizeBeforeUpdate);
        NckhDetai testNckhDetai = nckhDetaiList.get(nckhDetaiList.size() - 1);
        assertThat(testNckhDetai.getMaDetai()).isEqualTo(UPDATED_MA_DETAI);
        assertThat(testNckhDetai.getTenDetai()).isEqualTo(UPDATED_TEN_DETAI);
        assertThat(testNckhDetai.getMuctieu()).isEqualTo(UPDATED_MUCTIEU);
        assertThat(testNckhDetai.getNgayBdDuKien()).isEqualTo(UPDATED_NGAY_BD_DU_KIEN);
        assertThat(testNckhDetai.getNgayKtDuKien()).isEqualTo(UPDATED_NGAY_KT_DU_KIEN);
        assertThat(testNckhDetai.getKinhPhiDuKien()).isEqualTo(UPDATED_KINH_PHI_DU_KIEN);
        assertThat(testNckhDetai.getNoiDungTongQuan()).isEqualTo(UPDATED_NOI_DUNG_TONG_QUAN);
        assertThat(testNckhDetai.getKinhPhiHoanThanh()).isEqualTo(UPDATED_KINH_PHI_HOAN_THANH);
        assertThat(testNckhDetai.getTongDiem()).isEqualTo(UPDATED_TONG_DIEM);
    }

    @Test
    @Transactional
    public void updateNonExistingNckhDetai() throws Exception {
        int databaseSizeBeforeUpdate = nckhDetaiRepository.findAll().size();

        // Create the NckhDetai

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNckhDetaiMockMvc.perform(put("/api/nckh-detais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhDetai)))
            .andExpect(status().isBadRequest());

        // Validate the NckhDetai in the database
        List<NckhDetai> nckhDetaiList = nckhDetaiRepository.findAll();
        assertThat(nckhDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNckhDetai() throws Exception {
        // Initialize the database
        nckhDetaiService.save(nckhDetai);

        int databaseSizeBeforeDelete = nckhDetaiRepository.findAll().size();

        // Delete the nckhDetai
        restNckhDetaiMockMvc.perform(delete("/api/nckh-detais/{id}", nckhDetai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NckhDetai> nckhDetaiList = nckhDetaiRepository.findAll();
        assertThat(nckhDetaiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
