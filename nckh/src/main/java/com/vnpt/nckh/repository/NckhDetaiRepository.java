package com.vnpt.nckh.repository;
import com.vnpt.nckh.domain.NckhDetai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NckhDetai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NckhDetaiRepository extends JpaRepository<NckhDetai, Long>, JpaSpecificationExecutor<NckhDetai> {

}
