package com.vnptit.vnpthis.config;

import java.time.Duration;

import com.vnptit.vnpthis.domain.jhipster.Authority;
import com.vnptit.vnpthis.domain.jhipster.DataSourceConfig;
import com.vnptit.vnpthis.domain.jhipster.User;
import com.vnptit.vnpthis.domain.nckh.*;
import com.vnptit.vnpthis.repository.jhipster.UserRepository;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
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
            createCache(cm, UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, User.class.getName());
            createCache(cm, Authority.class.getName());
            createCache(cm, User.class.getName() + ".authorities");
            createCache(cm, DataSourceConfig.class.getName());
            createCache(cm, ChuyenMuc.class.getName());
            createCache(cm, ChuyenMuc.class.getName() + ".deTais");
            createCache(cm, DanhGia.class.getName());
            createCache(cm, DanhMuc.class.getName());
            createCache(cm, DeTai.class.getName());
            createCache(cm, DeTai.class.getName() + ".upFiles");
            createCache(cm, DeTai.class.getName() + ".tienDos");
            createCache(cm, DeTai.class.getName() + ".nhanSus");
            createCache(cm, DeTai.class.getName() + ".duToans");
            createCache(cm, DeTai.class.getName() + ".danhGias");
            createCache(cm, DuToan.class.getName());
            createCache(cm, LoaiDanhMuc.class.getName());
            createCache(cm, LoaiDanhMuc.class.getName() + ".danhMucs");
            createCache(cm, NhanSu.class.getName());
            createCache(cm, TienDo.class.getName());
            createCache(cm, TienDo.class.getName() + ".upFiles");
            createCache(cm, UpFile.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtTochucCap.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtTochucCap.class.getName() + ".dmChungchis");
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDmChungchi.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDmChungchi.class.getName() + ".chungChis");
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtChungChi.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt.class.getName() + ".hocVienCts");
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtQlHocvienCt.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDmNguoidung.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDaotao.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDaotao.class.getName() + ".daoTaoCts");
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDaotao.class.getName() + ".hocViens");
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtQlHocvien.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotao.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotao.class.getName() + ".duToanDaotaoCts");
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotaoct.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDmNoidung.class.getName());
            createCache(cm, com.vnptit.vnpthis.domain.qldt.QldtDmNoidung.class.getName() + ".duToandaotaoCts");
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
