package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.UpFile;
import com.vnptit.vnpthis.domain.DeTai;
import com.vnptit.vnpthis.domain.TienDo;
import com.vnptit.vnpthis.repository.UpFileRepository;
import com.vnptit.vnpthis.service.UpFileService;
import com.vnptit.vnpthis.service.dto.UpFileDTO;
import com.vnptit.vnpthis.service.mapper.UpFileMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.service.dto.UpFileCriteria;
import com.vnptit.vnpthis.service.UpFileQueryService;

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
import org.springframework.util.Base64Utils;
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
 * Integration tests for the {@link UpFileResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class UpFileResourceIT {

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final byte[] DEFAULT_NOIDUNG = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOIDUNG = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_NOIDUNG_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOIDUNG_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_NGAY_CN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CN = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_NGUOI_CN = 1;
    private static final Integer UPDATED_NGUOI_CN = 2;
    private static final Integer SMALLER_NGUOI_CN = 1 - 1;

    @Autowired
    private UpFileRepository upFileRepository;

    @Autowired
    private UpFileMapper upFileMapper;

    @Autowired
    private UpFileService upFileService;

    @Autowired
    private UpFileQueryService upFileQueryService;

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

    private MockMvc restUpFileMockMvc;

    private UpFile upFile;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UpFileResource upFileResource = new UpFileResource(upFileService, upFileQueryService);
        this.restUpFileMockMvc = MockMvcBuilders.standaloneSetup(upFileResource)
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
    public static UpFile createEntity(EntityManager em) {
        UpFile upFile = new UpFile()
            .mota(DEFAULT_MOTA)
            .noidung(DEFAULT_NOIDUNG)
//            .noidungContentType(DEFAULT_NOIDUNG_CONTENT_TYPE)
            .ngayCn(DEFAULT_NGAY_CN)
            .nguoiCn(DEFAULT_NGUOI_CN);
        return upFile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UpFile createUpdatedEntity(EntityManager em) {
        UpFile upFile = new UpFile()
            .mota(UPDATED_MOTA)
            .noidung(UPDATED_NOIDUNG)
//            .noidungContentType(UPDATED_NOIDUNG_CONTENT_TYPE)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        return upFile;
    }

    @BeforeEach
    public void initTest() {
        upFile = createEntity(em);
    }

    @Test
    @Transactional
    public void createUpFile() throws Exception {
        int databaseSizeBeforeCreate = upFileRepository.findAll().size();

        // Create the UpFile
        UpFileDTO upFileDTO = upFileMapper.toDto(upFile);
        restUpFileMockMvc.perform(post("/api/up-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(upFileDTO)))
            .andExpect(status().isCreated());

        // Validate the UpFile in the database
        List<UpFile> upFileList = upFileRepository.findAll();
        assertThat(upFileList).hasSize(databaseSizeBeforeCreate + 1);
        UpFile testUpFile = upFileList.get(upFileList.size() - 1);
        assertThat(testUpFile.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testUpFile.getNoidung()).isEqualTo(DEFAULT_NOIDUNG);
//        assertThat(testUpFile.getNoidungContentType()).isEqualTo(DEFAULT_NOIDUNG_CONTENT_TYPE);
        assertThat(testUpFile.getNgayCn()).isEqualTo(DEFAULT_NGAY_CN);
        assertThat(testUpFile.getNguoiCn()).isEqualTo(DEFAULT_NGUOI_CN);
    }

    @Test
    @Transactional
    public void createUpFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = upFileRepository.findAll().size();

        // Create the UpFile with an existing ID
        upFile.setId(1L);
        UpFileDTO upFileDTO = upFileMapper.toDto(upFile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUpFileMockMvc.perform(post("/api/up-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(upFileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UpFile in the database
        List<UpFile> upFileList = upFileRepository.findAll();
        assertThat(upFileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = upFileRepository.findAll().size();
        // set the field null
        upFile.setMota(null);

        // Create the UpFile, which fails.
        UpFileDTO upFileDTO = upFileMapper.toDto(upFile);

        restUpFileMockMvc.perform(post("/api/up-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(upFileDTO)))
            .andExpect(status().isBadRequest());

        List<UpFile> upFileList = upFileRepository.findAll();
        assertThat(upFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = upFileRepository.findAll().size();
        // set the field null
        upFile.setNgayCn(null);

        // Create the UpFile, which fails.
        UpFileDTO upFileDTO = upFileMapper.toDto(upFile);

        restUpFileMockMvc.perform(post("/api/up-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(upFileDTO)))
            .andExpect(status().isBadRequest());

        List<UpFile> upFileList = upFileRepository.findAll();
        assertThat(upFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNguoiCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = upFileRepository.findAll().size();
        // set the field null
        upFile.setNguoiCn(null);

        // Create the UpFile, which fails.
        UpFileDTO upFileDTO = upFileMapper.toDto(upFile);

        restUpFileMockMvc.perform(post("/api/up-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(upFileDTO)))
            .andExpect(status().isBadRequest());

        List<UpFile> upFileList = upFileRepository.findAll();
        assertThat(upFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUpFiles() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList
        restUpFileMockMvc.perform(get("/api/up-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(upFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].noidungContentType").value(hasItem(DEFAULT_NOIDUNG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].noidung").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOIDUNG))))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));
    }

    @Test
    @Transactional
    public void getUpFile() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get the upFile
        restUpFileMockMvc.perform(get("/api/up-files/{id}", upFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(upFile.getId().intValue()))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.noidungContentType").value(DEFAULT_NOIDUNG_CONTENT_TYPE))
            .andExpect(jsonPath("$.noidung").value(Base64Utils.encodeToString(DEFAULT_NOIDUNG)))
            .andExpect(jsonPath("$.ngayCn").value(DEFAULT_NGAY_CN.toString()))
            .andExpect(jsonPath("$.nguoiCn").value(DEFAULT_NGUOI_CN));
    }


    @Test
    @Transactional
    public void getUpFilesByIdFiltering() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        Long id = upFile.getId();

        defaultUpFileShouldBeFound("id.equals=" + id);
        defaultUpFileShouldNotBeFound("id.notEquals=" + id);

        defaultUpFileShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultUpFileShouldNotBeFound("id.greaterThan=" + id);

        defaultUpFileShouldBeFound("id.lessThanOrEqual=" + id);
        defaultUpFileShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllUpFilesByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where mota equals to DEFAULT_MOTA
        defaultUpFileShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the upFileList where mota equals to UPDATED_MOTA
        defaultUpFileShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllUpFilesByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where mota not equals to DEFAULT_MOTA
        defaultUpFileShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the upFileList where mota not equals to UPDATED_MOTA
        defaultUpFileShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllUpFilesByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultUpFileShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the upFileList where mota equals to UPDATED_MOTA
        defaultUpFileShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllUpFilesByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where mota is not null
        defaultUpFileShouldBeFound("mota.specified=true");

        // Get all the upFileList where mota is null
        defaultUpFileShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllUpFilesByMotaContainsSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where mota contains DEFAULT_MOTA
        defaultUpFileShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the upFileList where mota contains UPDATED_MOTA
        defaultUpFileShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllUpFilesByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where mota does not contain DEFAULT_MOTA
        defaultUpFileShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the upFileList where mota does not contain UPDATED_MOTA
        defaultUpFileShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllUpFilesByNgayCnIsEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where ngayCn equals to DEFAULT_NGAY_CN
        defaultUpFileShouldBeFound("ngayCn.equals=" + DEFAULT_NGAY_CN);

        // Get all the upFileList where ngayCn equals to UPDATED_NGAY_CN
        defaultUpFileShouldNotBeFound("ngayCn.equals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNgayCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where ngayCn not equals to DEFAULT_NGAY_CN
        defaultUpFileShouldNotBeFound("ngayCn.notEquals=" + DEFAULT_NGAY_CN);

        // Get all the upFileList where ngayCn not equals to UPDATED_NGAY_CN
        defaultUpFileShouldBeFound("ngayCn.notEquals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNgayCnIsInShouldWork() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where ngayCn in DEFAULT_NGAY_CN or UPDATED_NGAY_CN
        defaultUpFileShouldBeFound("ngayCn.in=" + DEFAULT_NGAY_CN + "," + UPDATED_NGAY_CN);

        // Get all the upFileList where ngayCn equals to UPDATED_NGAY_CN
        defaultUpFileShouldNotBeFound("ngayCn.in=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNgayCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where ngayCn is not null
        defaultUpFileShouldBeFound("ngayCn.specified=true");

        // Get all the upFileList where ngayCn is null
        defaultUpFileShouldNotBeFound("ngayCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllUpFilesByNgayCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where ngayCn is greater than or equal to DEFAULT_NGAY_CN
        defaultUpFileShouldBeFound("ngayCn.greaterThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the upFileList where ngayCn is greater than or equal to UPDATED_NGAY_CN
        defaultUpFileShouldNotBeFound("ngayCn.greaterThanOrEqual=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNgayCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where ngayCn is less than or equal to DEFAULT_NGAY_CN
        defaultUpFileShouldBeFound("ngayCn.lessThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the upFileList where ngayCn is less than or equal to SMALLER_NGAY_CN
        defaultUpFileShouldNotBeFound("ngayCn.lessThanOrEqual=" + SMALLER_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNgayCnIsLessThanSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where ngayCn is less than DEFAULT_NGAY_CN
        defaultUpFileShouldNotBeFound("ngayCn.lessThan=" + DEFAULT_NGAY_CN);

        // Get all the upFileList where ngayCn is less than UPDATED_NGAY_CN
        defaultUpFileShouldBeFound("ngayCn.lessThan=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNgayCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where ngayCn is greater than DEFAULT_NGAY_CN
        defaultUpFileShouldNotBeFound("ngayCn.greaterThan=" + DEFAULT_NGAY_CN);

        // Get all the upFileList where ngayCn is greater than SMALLER_NGAY_CN
        defaultUpFileShouldBeFound("ngayCn.greaterThan=" + SMALLER_NGAY_CN);
    }


    @Test
    @Transactional
    public void getAllUpFilesByNguoiCnIsEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where nguoiCn equals to DEFAULT_NGUOI_CN
        defaultUpFileShouldBeFound("nguoiCn.equals=" + DEFAULT_NGUOI_CN);

        // Get all the upFileList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultUpFileShouldNotBeFound("nguoiCn.equals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNguoiCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where nguoiCn not equals to DEFAULT_NGUOI_CN
        defaultUpFileShouldNotBeFound("nguoiCn.notEquals=" + DEFAULT_NGUOI_CN);

        // Get all the upFileList where nguoiCn not equals to UPDATED_NGUOI_CN
        defaultUpFileShouldBeFound("nguoiCn.notEquals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNguoiCnIsInShouldWork() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where nguoiCn in DEFAULT_NGUOI_CN or UPDATED_NGUOI_CN
        defaultUpFileShouldBeFound("nguoiCn.in=" + DEFAULT_NGUOI_CN + "," + UPDATED_NGUOI_CN);

        // Get all the upFileList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultUpFileShouldNotBeFound("nguoiCn.in=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNguoiCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where nguoiCn is not null
        defaultUpFileShouldBeFound("nguoiCn.specified=true");

        // Get all the upFileList where nguoiCn is null
        defaultUpFileShouldNotBeFound("nguoiCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllUpFilesByNguoiCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where nguoiCn is greater than or equal to DEFAULT_NGUOI_CN
        defaultUpFileShouldBeFound("nguoiCn.greaterThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the upFileList where nguoiCn is greater than or equal to UPDATED_NGUOI_CN
        defaultUpFileShouldNotBeFound("nguoiCn.greaterThanOrEqual=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNguoiCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where nguoiCn is less than or equal to DEFAULT_NGUOI_CN
        defaultUpFileShouldBeFound("nguoiCn.lessThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the upFileList where nguoiCn is less than or equal to SMALLER_NGUOI_CN
        defaultUpFileShouldNotBeFound("nguoiCn.lessThanOrEqual=" + SMALLER_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNguoiCnIsLessThanSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where nguoiCn is less than DEFAULT_NGUOI_CN
        defaultUpFileShouldNotBeFound("nguoiCn.lessThan=" + DEFAULT_NGUOI_CN);

        // Get all the upFileList where nguoiCn is less than UPDATED_NGUOI_CN
        defaultUpFileShouldBeFound("nguoiCn.lessThan=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllUpFilesByNguoiCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        // Get all the upFileList where nguoiCn is greater than DEFAULT_NGUOI_CN
        defaultUpFileShouldNotBeFound("nguoiCn.greaterThan=" + DEFAULT_NGUOI_CN);

        // Get all the upFileList where nguoiCn is greater than SMALLER_NGUOI_CN
        defaultUpFileShouldBeFound("nguoiCn.greaterThan=" + SMALLER_NGUOI_CN);
    }


    @Test
    @Transactional
    public void getAllUpFilesByDeTaiIsEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);
        DeTai deTai = DeTaiResourceIT.createEntity(em);
        em.persist(deTai);
        em.flush();
        upFile.setDeTai(deTai);
        upFileRepository.saveAndFlush(upFile);
        Long deTaiId = deTai.getId();

        // Get all the upFileList where deTai equals to deTaiId
        defaultUpFileShouldBeFound("deTaiId.equals=" + deTaiId);

        // Get all the upFileList where deTai equals to deTaiId + 1
        defaultUpFileShouldNotBeFound("deTaiId.equals=" + (deTaiId + 1));
    }


    @Test
    @Transactional
    public void getAllUpFilesByTienDoIsEqualToSomething() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);
        TienDo tienDo = TienDoResourceIT.createEntity(em);
        em.persist(tienDo);
        em.flush();
        upFile.setTienDo(tienDo);
        upFileRepository.saveAndFlush(upFile);
        Long tienDoId = tienDo.getId();

        // Get all the upFileList where tienDo equals to tienDoId
        defaultUpFileShouldBeFound("tienDoId.equals=" + tienDoId);

        // Get all the upFileList where tienDo equals to tienDoId + 1
        defaultUpFileShouldNotBeFound("tienDoId.equals=" + (tienDoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultUpFileShouldBeFound(String filter) throws Exception {
        restUpFileMockMvc.perform(get("/api/up-files?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(upFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].noidungContentType").value(hasItem(DEFAULT_NOIDUNG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].noidung").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOIDUNG))))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));

        // Check, that the count call also returns 1
        restUpFileMockMvc.perform(get("/api/up-files/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultUpFileShouldNotBeFound(String filter) throws Exception {
        restUpFileMockMvc.perform(get("/api/up-files?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUpFileMockMvc.perform(get("/api/up-files/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingUpFile() throws Exception {
        // Get the upFile
        restUpFileMockMvc.perform(get("/api/up-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUpFile() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        int databaseSizeBeforeUpdate = upFileRepository.findAll().size();

        // Update the upFile
        UpFile updatedUpFile = upFileRepository.findById(upFile.getId()).get();
        // Disconnect from session so that the updates on updatedUpFile are not directly saved in db
        em.detach(updatedUpFile);
        updatedUpFile
            .mota(UPDATED_MOTA)
            .noidung(UPDATED_NOIDUNG)
//            .noidungContentType(UPDATED_NOIDUNG_CONTENT_TYPE)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        UpFileDTO upFileDTO = upFileMapper.toDto(updatedUpFile);

        restUpFileMockMvc.perform(put("/api/up-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(upFileDTO)))
            .andExpect(status().isOk());

        // Validate the UpFile in the database
        List<UpFile> upFileList = upFileRepository.findAll();
        assertThat(upFileList).hasSize(databaseSizeBeforeUpdate);
        UpFile testUpFile = upFileList.get(upFileList.size() - 1);
        assertThat(testUpFile.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testUpFile.getNoidung()).isEqualTo(UPDATED_NOIDUNG);
//        assertThat(testUpFile.getNoidungContentType()).isEqualTo(UPDATED_NOIDUNG_CONTENT_TYPE);
        assertThat(testUpFile.getNgayCn()).isEqualTo(UPDATED_NGAY_CN);
        assertThat(testUpFile.getNguoiCn()).isEqualTo(UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void updateNonExistingUpFile() throws Exception {
        int databaseSizeBeforeUpdate = upFileRepository.findAll().size();

        // Create the UpFile
        UpFileDTO upFileDTO = upFileMapper.toDto(upFile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUpFileMockMvc.perform(put("/api/up-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(upFileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UpFile in the database
        List<UpFile> upFileList = upFileRepository.findAll();
        assertThat(upFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUpFile() throws Exception {
        // Initialize the database
        upFileRepository.saveAndFlush(upFile);

        int databaseSizeBeforeDelete = upFileRepository.findAll().size();

        // Delete the upFile
        restUpFileMockMvc.perform(delete("/api/up-files/{id}", upFile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UpFile> upFileList = upFileRepository.findAll();
        assertThat(upFileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
