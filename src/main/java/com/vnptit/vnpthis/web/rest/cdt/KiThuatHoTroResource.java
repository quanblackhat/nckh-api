package com.vnptit.vnpthis.web.rest.cdt;

import com.vnptit.vnpthis.domain.CdtKythuathotro;
import com.vnptit.vnpthis.service.cdt.KiThuatHoTroService;
import com.vnptit.vnpthis.service.dto.KiThuatHoTroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cdt/api/v1.0/kithuathotro")
public class KiThuatHoTroResource {

    private static class KiThuatHoTroResourceException extends RuntimeException {
        private KiThuatHoTroResourceException(String message) {
            super(message);
        }
    }

    @Autowired
    private KiThuatHoTroService kiThuatHoTroService;

    @GetMapping("")
    public List<KiThuatHoTroDTO> getAll() {
        return kiThuatHoTroService.getAll()
            .stream()
            .map(KiThuatHoTroDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public KiThuatHoTroDTO getById(@PathVariable(name = "id") Integer id) {
        return kiThuatHoTroService.getById(id)
            .map(KiThuatHoTroDTO::new)
            .orElseThrow(() -> new KiThuatHoTroResourceException("Khong tim thay ky thuat ho tro"));
    }

    @PostMapping("")
    public int add(@RequestBody CdtKythuathotro kythuathotro) {
        return kiThuatHoTroService.add(kythuathotro);
    }

    @PutMapping("")
    public void update(@RequestBody CdtKythuathotro kythuathotro) {
        kiThuatHoTroService.update(kythuathotro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        kiThuatHoTroService.delete(id);
    }
}
