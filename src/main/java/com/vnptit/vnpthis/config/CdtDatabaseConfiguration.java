package com.vnptit.vnpthis.config;


import com.vnptit.vnpthis.constant.DatabaseName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//    entityManagerFactoryRef = "cdtEntityManagerFactory",
//    transactionManagerRef = "cdtTransactionManager",
//    basePackages = {"com.vnptit.vnpthis.repository.cdt"}
//)
public class CdtDatabaseConfiguration {

//    @Value("${spring.jpa.hibernate.dialect}")
//    private String dialect;
//
//    @Autowired
//    private DataSourceProvider provider;
//
//    @Bean
//    public DataSource cdtDatasource() {
//        return provider.dataSources.get(DatabaseName.CHI_DAO_TUYEN);
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean cdtEntityManagerFactory(@Qualifier("cdtDatasource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em
//            = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan(
//            new String[] { "com.vnptit.vnpthis.domain.cdt" });
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", dialect);
//
//        em.setJpaPropertyMap(properties);
//        return em;
//    }
//
//    @Bean
//    public PlatformTransactionManager cdtTransactionManager(
//        @Qualifier("cdtEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}
