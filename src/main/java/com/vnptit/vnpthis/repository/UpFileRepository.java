package com.vnptit.vnpthis.repository;

import com.vnptit.vnpthis.domain.nckh.UpFile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UpFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UpFileRepository extends JpaRepository<UpFile, Long>, JpaSpecificationExecutor<UpFile> {

}
