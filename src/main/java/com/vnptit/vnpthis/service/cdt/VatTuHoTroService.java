package com.vnptit.vnpthis.service.cdt;


import com.vnptit.vnpthis.domain.cdt.CdtVattuhotro;
import com.vnptit.vnpthis.repository.cdt.VatTuHoTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VatTuHoTroService {
    @Autowired private VatTuHoTroRepository vatTuHoTroRepository;

    public List<CdtVattuhotro> getAll() {
        return vatTuHoTroRepository.findAll();
    }

    public Optional<CdtVattuhotro> getById(Integer id) {
        return vatTuHoTroRepository.findById(id);
    }

    public int add(CdtVattuhotro vattuhotro) {
        return  vatTuHoTroRepository.save(vattuhotro).getVattuhotroid();
    }

    public void update(CdtVattuhotro vattuhotro) {
        vatTuHoTroRepository.save(vattuhotro);
    }

    public void delete(Integer id) {
        vatTuHoTroRepository.deleteById(id);
    }
}
