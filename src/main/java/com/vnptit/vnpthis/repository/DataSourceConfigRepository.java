package com.vnptit.vnpthis.repository;

import com.vnptit.vnpthis.domain.DataSourceConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DataSourceConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {

}
