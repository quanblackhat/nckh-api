package com.vnptit.vnpthis.repository.jhipster;

import com.vnptit.vnpthis.domain.jhipster.DataSourceConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DataSourceConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {

}
