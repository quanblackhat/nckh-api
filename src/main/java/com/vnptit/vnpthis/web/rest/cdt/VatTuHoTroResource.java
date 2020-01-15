package com.vnptit.vnpthis.web.rest.cdt;

import com.vnptit.vnpthis.domain.CdtVattuhotro;
import com.vnptit.vnpthis.service.cdt.VatTuHoTroService;
import com.vnptit.vnpthis.service.dto.VatTuHoTroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cdt/api/v1.0/vattuhotro")
public class VatTuHoTroResource {

    private static class VatTuHoTroResourceException extends RuntimeException {
        private VatTuHoTroResourceException(String message) {
            super(message);
        }
    }

    @Autowired
    private VatTuHoTroService vatTuHoTroService;

    @GetMapping("/")
    public List<VatTuHoTroDTO> getAll() {
        return vatTuHoTroService.getAll()
            .stream()
            .map(VatTuHoTroDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VatTuHoTroDTO getById(@PathVariable(name = "id") Integer id) {
        return vatTuHoTroService.getById(id)
            .map(VatTuHoTroDTO::new)
            .orElseThrow(() -> new VatTuHoTroResourceException("Khong tim thay vat tu ho tro"));
    }

    @PostMapping("/")
    public int add(@RequestBody CdtVattuhotro vattuhotro) {
        return vatTuHoTroService.add(vattuhotro);
    }

    @PutMapping("/")
    public void update(@RequestBody CdtVattuhotro vattuhotro) {
        vatTuHoTroService.update(vattuhotro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        vatTuHoTroService.delete(id);
    }
}
