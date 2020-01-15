package com.vnptit.vnpthis.web.rest.cdt;

import com.vnptit.vnpthis.domain.CdtKetquacongtac;
import com.vnptit.vnpthis.service.cdt.KetQuaCongTacService;
import com.vnptit.vnpthis.service.dto.KetQuaCongTacDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cdt/api/v1.0/ketquacongtac")
public class KetQuaCongTacResource {

    private static class KetQuaCongTacResourceException extends RuntimeException {
        private KetQuaCongTacResourceException(String message) {
            super(message);
        }
    }

    @Autowired private KetQuaCongTacService ketQuaCongTacService;

    @GetMapping("")
    public List<KetQuaCongTacDTO> getAll() {
        return ketQuaCongTacService.getAll()
            .stream()
            .map(KetQuaCongTacDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public KetQuaCongTacDTO getById(@PathVariable(name = "id") Integer id) {
        return ketQuaCongTacService.getById(id)
            .map(KetQuaCongTacDTO::new)
            .orElseThrow(() -> new KetQuaCongTacResourceException("Khong tim thay ket qua cong tac"));
    }

    @PostMapping("")
    public int add(@RequestBody CdtKetquacongtac ketquacongtac) {
        if(ketquacongtac != null) {
            return ketQuaCongTacService.add(ketquacongtac);
        }
        return 0;
    }

    @PutMapping("")
    public void update(@RequestBody CdtKetquacongtac ketquacongtac) {
        if(ketquacongtac != null) ketQuaCongTacService.update(ketquacongtac);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        if(id != null) ketQuaCongTacService.delete(id);
    }

}
