package com.vnptit.vnpthis.web.rest.cdt;

import com.vnptit.vnpthis.domain.CdtLydocongtac;
import com.vnptit.vnpthis.service.cdt.LyDoCongTacService;
import com.vnptit.vnpthis.service.dto.LyDoCongTacDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lydocongtac")
public class LyDoCongTacResource {

    private static class LyDoCongTacResourceException extends RuntimeException {
        private LyDoCongTacResourceException(String message) {
            super(message);
        }
    }

    @Autowired
    private LyDoCongTacService lyDoCongTacService;

    @GetMapping("")
    public List<LyDoCongTacDTO> getAll() {
        return lyDoCongTacService.getAll()
            .stream()
            .map(LyDoCongTacDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LyDoCongTacDTO getById(@PathVariable(name = "id") Integer id) {
        return lyDoCongTacService.getById(id)
            .map(LyDoCongTacDTO::new)
            .orElseThrow(() -> new LyDoCongTacResourceException("Khong tim thay ly do cong tac"));
    }

    @PostMapping("")
    public int add(@RequestBody CdtLydocongtac lydocongtac) {
        return lyDoCongTacService.add(lydocongtac);
    }

    @PutMapping("")
    public void update(@RequestBody CdtLydocongtac lydocongtac) {
        lyDoCongTacService.update(lydocongtac);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        lyDoCongTacService.delete(id);
    }
}
