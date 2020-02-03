package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.DataSourceConfig;
import com.vnptit.vnpthis.repository.DataSourceConfigRepository;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;

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
 * Integration tests for the {@link DataSourceConfigResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class DataSourceConfigResourceIT {

    private static final String DEFAULT_DB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVER_CLASS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_CLASS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DB_URL = "AAAAAAAAAA";
    private static final String UPDATED_DB_URL = "BBBBBBBBBB";

    private static final String DEFAULT_DB_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_DB_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DB_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_DB_PASSWORD = "BBBBBBBBBB";

    @Autowired
    private DataSourceConfigRepository dataSourceConfigRepository;

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

    private MockMvc restDataSourceConfigMockMvc;

    private DataSourceConfig dataSourceConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DataSourceConfigResource dataSourceConfigResource = new DataSourceConfigResource(dataSourceConfigRepository);
        this.restDataSourceConfigMockMvc = MockMvcBuilders.standaloneSetup(dataSourceConfigResource)
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
    public static DataSourceConfig createEntity(EntityManager em) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
            .dbName(DEFAULT_DB_NAME)
            .driverClassName(DEFAULT_DRIVER_CLASS_NAME)
            .dbUrl(DEFAULT_DB_URL)
            .dbUsername(DEFAULT_DB_USERNAME)
            .dbPassword(DEFAULT_DB_PASSWORD);
        return dataSourceConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataSourceConfig createUpdatedEntity(EntityManager em) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
            .dbName(UPDATED_DB_NAME)
            .driverClassName(UPDATED_DRIVER_CLASS_NAME)
            .dbUrl(UPDATED_DB_URL)
            .dbUsername(UPDATED_DB_USERNAME)
            .dbPassword(UPDATED_DB_PASSWORD);
        return dataSourceConfig;
    }

    @BeforeEach
    public void initTest() {
        dataSourceConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataSourceConfig() throws Exception {
        int databaseSizeBeforeCreate = dataSourceConfigRepository.findAll().size();

        // Create the DataSourceConfig
        restDataSourceConfigMockMvc.perform(post("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfig)))
            .andExpect(status().isCreated());

        // Validate the DataSourceConfig in the database
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeCreate + 1);
        DataSourceConfig testDataSourceConfig = dataSourceConfigList.get(dataSourceConfigList.size() - 1);
        assertThat(testDataSourceConfig.getDbName()).isEqualTo(DEFAULT_DB_NAME);
        assertThat(testDataSourceConfig.getDriverClassName()).isEqualTo(DEFAULT_DRIVER_CLASS_NAME);
        assertThat(testDataSourceConfig.getDbUrl()).isEqualTo(DEFAULT_DB_URL);
        assertThat(testDataSourceConfig.getDbUsername()).isEqualTo(DEFAULT_DB_USERNAME);
        assertThat(testDataSourceConfig.getDbPassword()).isEqualTo(DEFAULT_DB_PASSWORD);
    }

    @Test
    @Transactional
    public void createDataSourceConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataSourceConfigRepository.findAll().size();

        // Create the DataSourceConfig with an existing ID
        dataSourceConfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataSourceConfigMockMvc.perform(post("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfig)))
            .andExpect(status().isBadRequest());

        // Validate the DataSourceConfig in the database
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDbNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataSourceConfigRepository.findAll().size();
        // set the field null
        dataSourceConfig.setDbName(null);

        // Create the DataSourceConfig, which fails.

        restDataSourceConfigMockMvc.perform(post("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfig)))
            .andExpect(status().isBadRequest());

        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDriverClassNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataSourceConfigRepository.findAll().size();
        // set the field null
        dataSourceConfig.setDriverClassName(null);

        // Create the DataSourceConfig, which fails.

        restDataSourceConfigMockMvc.perform(post("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfig)))
            .andExpect(status().isBadRequest());

        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDbUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataSourceConfigRepository.findAll().size();
        // set the field null
        dataSourceConfig.setDbUrl(null);

        // Create the DataSourceConfig, which fails.

        restDataSourceConfigMockMvc.perform(post("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfig)))
            .andExpect(status().isBadRequest());

        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDbUsernameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataSourceConfigRepository.findAll().size();
        // set the field null
        dataSourceConfig.setDbUsername(null);

        // Create the DataSourceConfig, which fails.

        restDataSourceConfigMockMvc.perform(post("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfig)))
            .andExpect(status().isBadRequest());

        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDbPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataSourceConfigRepository.findAll().size();
        // set the field null
        dataSourceConfig.setDbPassword(null);

        // Create the DataSourceConfig, which fails.

        restDataSourceConfigMockMvc.perform(post("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfig)))
            .andExpect(status().isBadRequest());

        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDataSourceConfigs() throws Exception {
        // Initialize the database
        dataSourceConfigRepository.saveAndFlush(dataSourceConfig);

        // Get all the dataSourceConfigList
        restDataSourceConfigMockMvc.perform(get("/api/data-source-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataSourceConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].dbName").value(hasItem(DEFAULT_DB_NAME)))
            .andExpect(jsonPath("$.[*].driverClassName").value(hasItem(DEFAULT_DRIVER_CLASS_NAME)))
            .andExpect(jsonPath("$.[*].dbUrl").value(hasItem(DEFAULT_DB_URL)))
            .andExpect(jsonPath("$.[*].dbUsername").value(hasItem(DEFAULT_DB_USERNAME)))
            .andExpect(jsonPath("$.[*].dbPassword").value(hasItem(DEFAULT_DB_PASSWORD)));
    }
    
    @Test
    @Transactional
    public void getDataSourceConfig() throws Exception {
        // Initialize the database
        dataSourceConfigRepository.saveAndFlush(dataSourceConfig);

        // Get the dataSourceConfig
        restDataSourceConfigMockMvc.perform(get("/api/data-source-configs/{id}", dataSourceConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dataSourceConfig.getId().intValue()))
            .andExpect(jsonPath("$.dbName").value(DEFAULT_DB_NAME))
            .andExpect(jsonPath("$.driverClassName").value(DEFAULT_DRIVER_CLASS_NAME))
            .andExpect(jsonPath("$.dbUrl").value(DEFAULT_DB_URL))
            .andExpect(jsonPath("$.dbUsername").value(DEFAULT_DB_USERNAME))
            .andExpect(jsonPath("$.dbPassword").value(DEFAULT_DB_PASSWORD));
    }

    @Test
    @Transactional
    public void getNonExistingDataSourceConfig() throws Exception {
        // Get the dataSourceConfig
        restDataSourceConfigMockMvc.perform(get("/api/data-source-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataSourceConfig() throws Exception {
        // Initialize the database
        dataSourceConfigRepository.saveAndFlush(dataSourceConfig);

        int databaseSizeBeforeUpdate = dataSourceConfigRepository.findAll().size();

        // Update the dataSourceConfig
        DataSourceConfig updatedDataSourceConfig = dataSourceConfigRepository.findById(dataSourceConfig.getId()).get();
        // Disconnect from session so that the updates on updatedDataSourceConfig are not directly saved in db
        em.detach(updatedDataSourceConfig);
        updatedDataSourceConfig
            .dbName(UPDATED_DB_NAME)
            .driverClassName(UPDATED_DRIVER_CLASS_NAME)
            .dbUrl(UPDATED_DB_URL)
            .dbUsername(UPDATED_DB_USERNAME)
            .dbPassword(UPDATED_DB_PASSWORD);

        restDataSourceConfigMockMvc.perform(put("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDataSourceConfig)))
            .andExpect(status().isOk());

        // Validate the DataSourceConfig in the database
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeUpdate);
        DataSourceConfig testDataSourceConfig = dataSourceConfigList.get(dataSourceConfigList.size() - 1);
        assertThat(testDataSourceConfig.getDbName()).isEqualTo(UPDATED_DB_NAME);
        assertThat(testDataSourceConfig.getDriverClassName()).isEqualTo(UPDATED_DRIVER_CLASS_NAME);
        assertThat(testDataSourceConfig.getDbUrl()).isEqualTo(UPDATED_DB_URL);
        assertThat(testDataSourceConfig.getDbUsername()).isEqualTo(UPDATED_DB_USERNAME);
        assertThat(testDataSourceConfig.getDbPassword()).isEqualTo(UPDATED_DB_PASSWORD);
    }

    @Test
    @Transactional
    public void updateNonExistingDataSourceConfig() throws Exception {
        int databaseSizeBeforeUpdate = dataSourceConfigRepository.findAll().size();

        // Create the DataSourceConfig

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataSourceConfigMockMvc.perform(put("/api/data-source-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataSourceConfig)))
            .andExpect(status().isBadRequest());

        // Validate the DataSourceConfig in the database
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataSourceConfig() throws Exception {
        // Initialize the database
        dataSourceConfigRepository.saveAndFlush(dataSourceConfig);

        int databaseSizeBeforeDelete = dataSourceConfigRepository.findAll().size();

        // Delete the dataSourceConfig
        restDataSourceConfigMockMvc.perform(delete("/api/data-source-configs/{id}", dataSourceConfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataSourceConfig> dataSourceConfigList = dataSourceConfigRepository.findAll();
        assertThat(dataSourceConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
