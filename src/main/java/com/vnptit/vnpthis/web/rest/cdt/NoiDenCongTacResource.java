package com.vnptit.vnpthis.web.rest.cdt;


import com.vnptit.vnpthis.domain.CdtNoidencongtac;
import com.vnptit.vnpthis.service.cdt.NoiDenCongTacService;
import com.vnptit.vnpthis.service.dto.NoiDenCongTacDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/noidencongtac")
public class NoiDenCongTacResource {

    private static class NoiDenCongTacResourceException extends RuntimeException {
        private NoiDenCongTacResourceException(String message) {
            super(message);
        }
    }

    @Autowired
    private NoiDenCongTacService noiDenCongTacService;

    @GetMapping("")
    public List<NoiDenCongTacDTO> getAll() {
        return noiDenCongTacService.getAll()
            .stream()
            .map(NoiDenCongTacDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public NoiDenCongTacDTO getById(@PathVariable(name = "id") Integer id) {
        return noiDenCongTacService.getById(id)
            .map(NoiDenCongTacDTO::new)
            .orElseThrow(() -> new NoiDenCongTacResourceException("Khong tim thay noi den cong tac"));
    }

    @PostMapping("")
    public int add(@RequestBody CdtNoidencongtac noidencongtac) {
        return noiDenCongTacService.add(noidencongtac);
    }

    @PutMapping("")
    public void update(@RequestBody CdtNoidencongtac noidencongtac) {
        noiDenCongTacService.update(noidencongtac);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        noiDenCongTacService.delete(id);
    }
}
