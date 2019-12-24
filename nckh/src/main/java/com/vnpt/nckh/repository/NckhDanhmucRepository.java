package com.vnpt.nckh.repository;
import com.vnpt.nckh.domain.NckhDanhmuc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NckhDanhmuc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NckhDanhmucRepository extends JpaRepository<NckhDanhmuc, Long>, JpaSpecificationExecutor<NckhDanhmuc> {

}
