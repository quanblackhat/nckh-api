package com.vnpt.nckh.repository;
import com.vnpt.nckh.domain.NckhUpfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NckhUpfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NckhUpfileRepository extends JpaRepository<NckhUpfile, Long>, JpaSpecificationExecutor<NckhUpfile> {

}
