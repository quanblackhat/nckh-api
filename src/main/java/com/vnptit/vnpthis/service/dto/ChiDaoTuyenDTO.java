package com.vnptit.vnpthis.service.dto;

import com.vnptit.vnpthis.domain.CdtChidaotuyen;
import com.vnptit.vnpthis.domain.CdtKythuathotro;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

public class ChiDaoTuyenDTO {
    private int id;
    private String soquyetdinh;
    private Time ngayquyetdinh;
    private String sohopdong;
    private Time ngayhopdong;
    private String ghichu;
    private String noidung;
    private Time ngaybd;
    private Time ngaykt;
    private Time ngaytao;
    private Integer sobenhnhankham;
    private Integer sobenhnhankythuat;
    private Integer socanbochuyengiao;
    private String lydocongtac;
    private String ketquacongtac;
    private List<String> danhsachKiThuatHoTro;
    private List<String> danhsachVatTuHoTro;
    private List<String> danhsachNoiDenCongTac;

    public ChiDaoTuyenDTO(CdtChidaotuyen chidaotuyen) {
        id = chidaotuyen.getChidaotuyenid();
        soquyetdinh = chidaotuyen.getSoquyetdinh();
        ngayquyetdinh = chidaotuyen.getNgayquyetdinh();
        sohopdong = chidaotuyen.getSohopdong();
        ngayhopdong = chidaotuyen.getNgayhopdong();
        ghichu = chidaotuyen.getGhichu();
        noidung = chidaotuyen.getNoidung();
        ngaybd = chidaotuyen.getNgaybd();
        ngaykt = chidaotuyen.getNgaykt();
        ngaytao = chidaotuyen.getNgaytao();
        sobenhnhankham = chidaotuyen.getSobenhnhankham();
        sobenhnhankythuat = chidaotuyen.getSobenhnhankythuat();
        socanbochuyengiao = chidaotuyen.getSocanbochuyengiao();
        lydocongtac = chidaotuyen.getLydocongtac().getTenlydo();
        ketquacongtac = chidaotuyen.getKetquacongtac().getTenketqua();
        danhsachKiThuatHoTro = chidaotuyen.getDanhSachKyThuatHoTro()
                                .stream()
                                .map(kythuathotro -> kythuathotro.getTenkythuat())
                                .collect(Collectors.toList());
        danhsachVatTuHoTro = chidaotuyen.getDanhSachVatTuHoTro()
                                .stream()
                                .map(vattuhotro -> vattuhotro.getTenvattu())
                                .collect(Collectors.toList());
        danhsachNoiDenCongTac = chidaotuyen.getDanhSachNoiDen()
                                .stream()
                                .map(noidencongtac -> noidencongtac.getTennoiden())
                                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoquyetdinh() {
        return soquyetdinh;
    }

    public void setSoquyetdinh(String soquyetdinh) {
        this.soquyetdinh = soquyetdinh;
    }

    public Time getNgayquyetdinh() {
        return ngayquyetdinh;
    }

    public void setNgayquyetdinh(Time ngayquyetdinh) {
        this.ngayquyetdinh = ngayquyetdinh;
    }

    public String getSohopdong() {
        return sohopdong;
    }

    public void setSohopdong(String sohopdong) {
        this.sohopdong = sohopdong;
    }

    public Time getNgayhopdong() {
        return ngayhopdong;
    }

    public void setNgayhopdong(Time ngayhopdong) {
        this.ngayhopdong = ngayhopdong;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Time getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(Time ngaybd) {
        this.ngaybd = ngaybd;
    }

    public Time getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(Time ngaykt) {
        this.ngaykt = ngaykt;
    }

    public Time getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Time ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Integer getSobenhnhankham() {
        return sobenhnhankham;
    }

    public void setSobenhnhankham(Integer sobenhnhankham) {
        this.sobenhnhankham = sobenhnhankham;
    }

    public Integer getSobenhnhankythuat() {
        return sobenhnhankythuat;
    }

    public void setSobenhnhankythuat(Integer sobenhnhankythuat) {
        this.sobenhnhankythuat = sobenhnhankythuat;
    }

    public Integer getSocanbochuyengiao() {
        return socanbochuyengiao;
    }

    public void setSocanbochuyengiao(Integer socanbochuyengiao) {
        this.socanbochuyengiao = socanbochuyengiao;
    }

    public String getLydocongtac() {
        return lydocongtac;
    }

    public void setLydocongtac(String lydocongtac) {
        this.lydocongtac = lydocongtac;
    }

    public String getKetquacongtac() {
        return ketquacongtac;
    }

    public void setKetquacongtac(String ketquacongtac) {
        this.ketquacongtac = ketquacongtac;
    }

    public List<String> getDanhsachKiThuatHoTro() {
        return danhsachKiThuatHoTro;
    }

    public void setDanhsachKiThuatHoTro(List<String> danhsachKiThuatHoTro) {
        this.danhsachKiThuatHoTro = danhsachKiThuatHoTro;
    }

    public List<String> getDanhsachVatTuHoTro() {
        return danhsachVatTuHoTro;
    }

    public void setDanhsachVatTuHoTro(List<String> danhsachVatTuHoTro) {
        this.danhsachVatTuHoTro = danhsachVatTuHoTro;
    }

    public List<String> getDanhsachNoiDenCongTac() {
        return danhsachNoiDenCongTac;
    }

    public void setDanhsachNoiDenCongTac(List<String> danhsachNoiDenCongTac) {
        this.danhsachNoiDenCongTac = danhsachNoiDenCongTac;
    }
}
