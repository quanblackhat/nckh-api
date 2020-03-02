package com.vnptit.vnpthis.repository.qldt;

import com.vnptit.vnpthis.domain.qldt.QldtQlHocvien;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QldtQlHocvien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QldtQlHocvienRepository extends JpaRepository<QldtQlHocvien, Long>, JpaSpecificationExecutor<QldtQlHocvien> {

}
