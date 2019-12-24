package com.vnpt.nckh.repository;
import com.vnpt.nckh.domain.NckhTiendo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NckhTiendo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NckhTiendoRepository extends JpaRepository<NckhTiendo, Long>, JpaSpecificationExecutor<NckhTiendo> {

}
