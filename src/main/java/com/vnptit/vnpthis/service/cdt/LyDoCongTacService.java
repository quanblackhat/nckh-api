package com.vnptit.vnpthis.service.cdt;

import com.vnptit.vnpthis.domain.CdtLydocongtac;
import com.vnptit.vnpthis.repository.cdt.LyDoCongTacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LyDoCongTacService {

    @Autowired private LyDoCongTacRepository lyDoCongTacRepository;

    public List<CdtLydocongtac> getAll() {
        return lyDoCongTacRepository.findAll();
    }

    public Optional<CdtLydocongtac> getById(Integer id) {
        return lyDoCongTacRepository.findById(id);
    }

    public int add(CdtLydocongtac lydocongtac) {
        return  lyDoCongTacRepository.save(lydocongtac).getLydocongtacid();
    }

    public void update(CdtLydocongtac lydocongtac) {
        lyDoCongTacRepository.save(lydocongtac);
    }

    public void delete(Integer id) {
        lyDoCongTacRepository.deleteById(id);
    }
}
