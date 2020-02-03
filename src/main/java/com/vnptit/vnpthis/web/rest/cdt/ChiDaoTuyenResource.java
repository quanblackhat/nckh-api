package com.vnptit.vnpthis.web.rest.cdt;

import com.vnptit.vnpthis.domain.cdt.CdtChidaotuyen;
import com.vnptit.vnpthis.domain.cdt.ChiDaoTuyenRequestEntity;
import com.vnptit.vnpthis.service.cdt.ChiDaoTuyenService;
import com.vnptit.vnpthis.service.dto.ChiDaoTuyenDTO;
import com.vnptit.vnpthis.service.mapper.ChiDaoTuyenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chidaotuyen")
public class ChiDaoTuyenResource {
    @Autowired private ChiDaoTuyenMapper chiDaoTuyenMapper;

    private static class ChiDaoTuyenResourceException extends RuntimeException {
        private ChiDaoTuyenResourceException(String message) {
            super(message);
        }
    }

    @Autowired private ChiDaoTuyenService chiDaoTuyenService;

    @GetMapping("")
    public List<ChiDaoTuyenDTO> getAll() {
        return chiDaoTuyenService.getAll()
            .stream()
            .map(ChiDaoTuyenDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ChiDaoTuyenDTO getById(@PathVariable(name = "id") Integer id) {
        return chiDaoTuyenService.getById(id)
            .map(ChiDaoTuyenDTO::new)
            .orElseThrow(() -> new ChiDaoTuyenResourceException("Khong tim thay ky thuat ho tro"));
    }

    @PostMapping("")
    public int add(@RequestBody ChiDaoTuyenRequestEntity request) {
        CdtChidaotuyen cdt = chiDaoTuyenMapper.toCdtChiDaoTuyen(request);
        return chiDaoTuyenService.add(cdt);
    }

    @PutMapping("")
    public void update(@RequestBody CdtChidaotuyen chidaotuyen) {
        if(chiDaoTuyenService.getById(chidaotuyen.getChidaotuyenid()).isPresent()) {
            chiDaoTuyenService.update(chidaotuyen);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        chiDaoTuyenService.delete(id);
    }
}
