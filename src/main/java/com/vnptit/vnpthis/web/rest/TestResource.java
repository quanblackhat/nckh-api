package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.domain.CdtChidaotuyen;
import com.vnptit.vnpthis.repository.ChiDaoTuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestResource {
    @Autowired private ChiDaoTuyenRepository chiDaoTuyenRepository;

    @GetMapping("/cdt/{id}")
    public List<String> getKyThuat(@PathVariable(name = "id") Integer id) {
        CdtChidaotuyen cdtChidaotuyen = chiDaoTuyenRepository.getOne(id);
        return cdtChidaotuyen.getDanhSachKyThuatHoTro()
            .stream()
            .map(cdtKythuathotro -> cdtKythuathotro.getTenkythuat())
            .collect(Collectors.toList());
    }

}
