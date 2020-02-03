package com.vnptit.vnpthis.service.cdt;

import com.vnptit.vnpthis.domain.cdt.CdtChidaotuyen;
import com.vnptit.vnpthis.repository.cdt.ChiDaoTuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChiDaoTuyenService {
    @Autowired private ChiDaoTuyenRepository chiDaoTuyenRepository;

    public List<CdtChidaotuyen> getAll() {
        return chiDaoTuyenRepository.findAll();
    }

    public Optional<CdtChidaotuyen> getById(Integer id) {
        return chiDaoTuyenRepository.findById(id);
    }

    public int add(CdtChidaotuyen kq) {
        return chiDaoTuyenRepository.save(kq).getChidaotuyenid();
    }

    public void update(CdtChidaotuyen kq) {
        chiDaoTuyenRepository.save(kq);
    }

    public void delete(Integer id) {
        chiDaoTuyenRepository.deleteById(id);
    }
}
