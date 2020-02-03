package com.vnptit.vnpthis.config;

import com.vnptit.vnpthis.domain.jhipster.DataSourceConfig;
import com.vnptit.vnpthis.repository.jhipster.DataSourceConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Component
public class DataSourceProvider {
    public HashMap<String, DataSource> dataSources = new HashMap<>();

    @Autowired
    private DataSourceConfigRepository dsConfigRepository;

    @PostConstruct
    public void prepareDatasource() {
        List<DataSourceConfig> list = dsConfigRepository.findAll();
        for(DataSourceConfig ds: list) {
            DataSource dataSource = createDatasource(ds);
            if(dataSource != null) dataSources.put(ds.getDbName(), dataSource);
        }
    }

    private DataSource createDatasource(DataSourceConfig ds) {
        if(ds != null) {
            DataSource dataSource = DataSourceBuilder
                                    .create()
                                    .driverClassName(ds.getDriverClassName())
                                    .username(ds.getDbUsername())
                                    .password(ds.getDbPassword())
                                    .url(ds.getDbUrl())
                                    .build();
            return dataSource;
        }
        return null;
    }

}
