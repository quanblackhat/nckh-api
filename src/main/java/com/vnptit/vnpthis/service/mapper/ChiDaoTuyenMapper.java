package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.cdt.CdtChidaotuyen;
import com.vnptit.vnpthis.domain.cdt.ChiDaoTuyenRequestEntity;
import com.vnptit.vnpthis.repository.cdt.*;
import com.vnptit.vnpthis.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ChiDaoTuyenMapper {
    @Autowired private  LyDoCongTacRepository lyDoCongTacRepository;
    @Autowired private  KetQuaCongTacRepository ketQuaCongTacRepository;
    @Autowired private  VatTuHoTroRepository vatTuHoTroRepository;
    @Autowired private  KiThuatHoTroRepository kiThuatHoTroRepository;
    @Autowired private NoiDenCongTacRepository noiDenCongTacRepository;
    public  CdtChidaotuyen toCdtChiDaoTuyen(ChiDaoTuyenRequestEntity requestEntity) {
        CdtChidaotuyen cdt = new CdtChidaotuyen();

        cdt.setSoquyetdinh(requestEntity.getSoquyetdinh());
        cdt.setNgayquyetdinh(DateConverter.convertStringToTime(requestEntity.getNgayquyetdinh()));
        cdt.setSohopdong(requestEntity.getSohopdong());
        cdt.setNgayhopdong(DateConverter.convertStringToTime(requestEntity.getNgayhopdong()));
        cdt.setGhichu(requestEntity.getGhichu());
        cdt.setNoidung(requestEntity.getNoidung());
        cdt.setNgaybd(DateConverter.convertStringToTime(requestEntity.getNgaybd()));
        cdt.setNgaykt(DateConverter.convertStringToTime(requestEntity.getNgaykt()));
        cdt.setNgaytao(DateConverter.convertStringToTime(requestEntity.getNgaytao()));
        cdt.setSobenhnhankham(requestEntity.getSobenhnhankham());
        cdt.setSobenhnhankythuat(requestEntity.getSobenhnhankythuat());
        cdt.setSocanbochuyengiao(requestEntity.getSocanbochuyengiao());

        cdt.setLydocongtac(lyDoCongTacRepository.findById(requestEntity.getLydocongtacId()).get());
        cdt.setLydocongtacid(requestEntity.getLydocongtacId());
        cdt.setKetquacongtac(ketQuaCongTacRepository.findById(requestEntity.getKetquacongtacId()).get());
        cdt.setKetquacongtacid(requestEntity.getKetquacongtacId());
        if(requestEntity.getDanhsachVatTuHoTroID() != null) {
            cdt.setDanhSachVatTuHoTro(new HashSet<>(vatTuHoTroRepository.findAllById(requestEntity.getDanhsachVatTuHoTroID())));
        }
        if(requestEntity.getDanhsachKiThuatHoTroID() != null) {
            cdt.setDanhSachKyThuatHoTro(new HashSet<>(kiThuatHoTroRepository.findAllById(requestEntity.getDanhsachKiThuatHoTroID())));
        }
        if(requestEntity.getDanhsachKiThuatHoTroID() != null) {
            cdt.setDanhSachNoiDen(new HashSet<>(noiDenCongTacRepository.findAllById(requestEntity.getDanhsachNoiDenCongTacID())));
        }
        return cdt;
    }

}
