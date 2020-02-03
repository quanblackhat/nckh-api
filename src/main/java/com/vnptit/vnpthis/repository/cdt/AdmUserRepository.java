package com.vnptit.vnpthis.repository.cdt;

import com.vnptit.vnpthis.domain.cdt.AdmUser;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AdmUserRepository extends JpaRepository<AdmUser, Long> {
    Optional<AdmUser> getByUserName(String username);
}
