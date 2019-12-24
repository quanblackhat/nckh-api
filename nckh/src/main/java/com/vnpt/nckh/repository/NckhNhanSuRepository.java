package com.vnpt.nckh.repository;
import com.vnpt.nckh.domain.NckhNhanSu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NckhNhanSu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NckhNhanSuRepository extends JpaRepository<NckhNhanSu, Long>, JpaSpecificationExecutor<NckhNhanSu> {

}
