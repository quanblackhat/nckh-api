package com.vnpt.cdt.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.vnpt.cdt.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.vnpt.cdt.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.vnpt.cdt.domain.User.class.getName());
            createCache(cm, com.vnpt.cdt.domain.Authority.class.getName());
            createCache(cm, com.vnpt.cdt.domain.User.class.getName() + ".authorities");
            createCache(cm, com.vnpt.cdt.domain.OrgOfficer.class.getName());
            createCache(cm, com.vnpt.cdt.domain.OrgOfficer.class.getName() + ".nhanviens");
            createCache(cm, com.vnpt.cdt.domain.CdtNhanVien.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtChiDaoTuyen.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtChiDaoTuyen.class.getName() + ".nhanviens");
            createCache(cm, com.vnpt.cdt.domain.CdtChiDaoTuyen.class.getName() + ".noidens");
            createCache(cm, com.vnpt.cdt.domain.CdtChiDaoTuyen.class.getName() + ".kythuats");
            createCache(cm, com.vnpt.cdt.domain.CdtChiDaoTuyen.class.getName() + ".vattus");
            createCache(cm, com.vnpt.cdt.domain.CdtChiDaoTuyen.class.getName() + ".lydocongtacs");
            createCache(cm, com.vnpt.cdt.domain.CdtChiDaoTuyen.class.getName() + ".ketquacongtacs");
            createCache(cm, com.vnpt.cdt.domain.CdtChiDaoTuyen.class.getName() + ".taichinhs");
            createCache(cm, com.vnpt.cdt.domain.CdtNoiDen.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtNoiDenCongTac.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtNoiDenCongTac.class.getName() + ".noidens");
            createCache(cm, com.vnpt.cdt.domain.CdtLyDoCongTac.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtTaiChinh.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtKyThuat.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtKyThuatHoTro.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtKyThuatHoTro.class.getName() + ".kythuats");
            createCache(cm, com.vnpt.cdt.domain.CdtVatTu.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtVatTuHoTro.class.getName());
            createCache(cm, com.vnpt.cdt.domain.CdtVatTuHoTro.class.getName() + ".vattus");
            createCache(cm, com.vnpt.cdt.domain.CdtKetQuaCongTac.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
