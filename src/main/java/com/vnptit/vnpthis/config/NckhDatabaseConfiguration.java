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
//    entityManagerFactoryRef = "nckhEntityManagerFactory",
//    transactionManagerRef = "nckhTransactionManager",
//    basePackages = {"com.vnptit.vnpthis.repository.nckh"}
//)
public class NckhDatabaseConfiguration {

//    @Value("${spring.jpa.hibernate.dialect}")
//    private String dialect;
//
//    @Autowired
//    private DataSourceProvider provider;
//
//    @Bean
//    public DataSource nckhDatasource() {
//        return provider.dataSources.get(DatabaseName.NGHIEN_CUU_KHOA_HOC);
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean nckhEntityManagerFactory(@Qualifier("nckhDatasource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em
//            = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan(
//            new String[] { "com.vnptit.vnpthis.domain.nckh" });
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
//    public PlatformTransactionManager nckhTransactionManager(
//        @Qualifier("nckhEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}
