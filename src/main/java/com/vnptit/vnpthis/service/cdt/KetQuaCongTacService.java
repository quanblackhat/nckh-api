package com.vnptit.vnpthis.service.cdt;


import com.vnptit.vnpthis.domain.cdt.CdtKetquacongtac;
import com.vnptit.vnpthis.repository.cdt.KetQuaCongTacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KetQuaCongTacService {

    @Autowired private KetQuaCongTacRepository ketQuaCongTacRepository;

    public List<CdtKetquacongtac> getAll() {
        return ketQuaCongTacRepository.findAll();
    }

    public Optional<CdtKetquacongtac> getById(Integer id) {
        return ketQuaCongTacRepository.findById(id);
    }

    public int add(CdtKetquacongtac kq) {
        return ketQuaCongTacRepository.save(kq).getKetquacongtacid();
    }

    public void update(CdtKetquacongtac kq) {
        ketQuaCongTacRepository.save(kq);
    }

    public void delete(Integer id) {
        ketQuaCongTacRepository.deleteById(id);
    }
}
