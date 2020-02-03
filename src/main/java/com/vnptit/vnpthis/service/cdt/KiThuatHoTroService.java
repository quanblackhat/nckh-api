package com.vnptit.vnpthis.service.cdt;

import com.vnptit.vnpthis.domain.cdt.CdtKythuathotro;
import com.vnptit.vnpthis.repository.cdt.KiThuatHoTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KiThuatHoTroService {
    @Autowired private KiThuatHoTroRepository kiThuatHoTroRepository;

    public List<CdtKythuathotro> getAll() {
        return kiThuatHoTroRepository.findAll();
    }

    public Optional<CdtKythuathotro> getById(Integer id) {
        return kiThuatHoTroRepository.findById(id);
    }

    public int add(CdtKythuathotro ktht) {
        return kiThuatHoTroRepository.save(ktht).getKythuathotroid();
    }

    public void update(CdtKythuathotro ktht) {
        kiThuatHoTroRepository.save(ktht);
    }

    public void delete(Integer id) {
        kiThuatHoTroRepository.deleteById(id);
    }
}
