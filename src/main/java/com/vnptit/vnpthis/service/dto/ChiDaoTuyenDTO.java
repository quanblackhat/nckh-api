package com.vnptit.vnpthis.service.dto;

import com.vnptit.vnpthis.domain.cdt.CdtChidaotuyen;

import java.util.List;
import java.util.stream.Collectors;

public class ChiDaoTuyenDTO {
    private int id;
    private String soquyetdinh;
    private String ngayquyetdinh;
    private String sohopdong;
    private String ngayhopdong;
    private String ghichu;
    private String noidung;
    private String ngaybd;
    private String ngaykt;
    private String ngaytao;
    private Integer sobenhnhankham;
    private Integer sobenhnhankythuat;
    private Integer socanbochuyengiao;
    private String lydocongtac;
    private int lydocongtacId;
    private String ketquacongtac;

    public int getLydocongtacId() {
        return lydocongtacId;
    }

    public void setLydocongtacId(int lydocongtacId) {
        this.lydocongtacId = lydocongtacId;
    }

    public int getKetquacongtacId() {
        return ketquacongtacId;
    }

    public void setKetquacongtacId(int ketquacongtacId) {
        this.ketquacongtacId = ketquacongtacId;
    }

    private int ketquacongtacId;
    private List<String> danhsachKiThuatHoTro;
    private List<String> danhsachVatTuHoTro;
    private List<String> danhsachNoiDenCongTac;

    public ChiDaoTuyenDTO(CdtChidaotuyen chidaotuyen) {
        id = chidaotuyen.getChidaotuyenid();
        soquyetdinh = chidaotuyen.getSoquyetdinh();
        ngayquyetdinh = chidaotuyen.getNgayquyetdinh().toString();
        sohopdong = chidaotuyen.getSohopdong();
        ngayhopdong = chidaotuyen.getNgayhopdong().toString();
        ghichu = chidaotuyen.getGhichu();
        noidung = chidaotuyen.getNoidung();
        ngaybd = chidaotuyen.getNgaybd().toString();
        ngaykt = chidaotuyen.getNgaykt().toString();
        ngaytao = chidaotuyen.getNgaytao().toString();
        sobenhnhankham = chidaotuyen.getSobenhnhankham();
        sobenhnhankythuat = chidaotuyen.getSobenhnhankythuat();
        socanbochuyengiao = chidaotuyen.getSocanbochuyengiao();
        lydocongtac = chidaotuyen.getLydocongtac().getTenlydo();
        lydocongtacId = chidaotuyen.getLydocongtac().getLydocongtacid();
        ketquacongtac = chidaotuyen.getKetquacongtac().getTenketqua();
        ketquacongtacId = chidaotuyen.getKetquacongtac().getKetquacongtacid();

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

    public String getNgayquyetdinh() {
        return ngayquyetdinh;
    }

    public void setNgayquyetdinh(String ngayquyetdinh) {
        this.ngayquyetdinh = ngayquyetdinh;
    }

    public String getSohopdong() {
        return sohopdong;
    }

    public void setSohopdong(String sohopdong) {
        this.sohopdong = sohopdong;
    }

    public String getNgayhopdong() {
        return ngayhopdong;
    }

    public void setNgayhopdong(String ngayhopdong) {
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

    public String getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(String ngaybd) {
        this.ngaybd = ngaybd;
    }

    public String getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(String ngaykt) {
        this.ngaykt = ngaykt;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
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
