package com.vnptit.vnpthis.service.cdt;

import com.vnptit.vnpthis.domain.cdt.CdtNoidencongtac;
import com.vnptit.vnpthis.repository.cdt.NoiDenCongTacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoiDenCongTacService {
    @Autowired private NoiDenCongTacRepository noiDenCongTacRepository;

    public List<CdtNoidencongtac> getAll() {
        return noiDenCongTacRepository.findAll();
    }

    public Optional<CdtNoidencongtac> getById(Integer id) {
        return noiDenCongTacRepository.findById(id);
    }

    public int add(CdtNoidencongtac noidencongtac) {
        return  noiDenCongTacRepository.save(noidencongtac).getNoidencongtacid();
    }

    public void update(CdtNoidencongtac noidencongtac) {
        noiDenCongTacRepository.save(noidencongtac);
    }

    public void delete(Integer id) {
        noiDenCongTacRepository.deleteById(id);
    }
}
