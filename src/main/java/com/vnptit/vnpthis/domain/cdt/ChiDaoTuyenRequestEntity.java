package com.vnptit.vnpthis.domain.cdt;

import java.util.List;

public class ChiDaoTuyenRequestEntity {

    public int getChidaotuyenid() {
        return chidaotuyenid;
    }

    public void setChidaotuyenid(int chidaotuyenid) {
        this.chidaotuyenid = chidaotuyenid;
    }

    private int chidaotuyenid;
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
    private List<Integer> danhsachKiThuatHoTroID;
    private List<Integer> danhsachVatTuHoTroID;
    private List<Integer> danhsachNoiDenCongTacID;
    private Integer lydocongtacId;
    private Integer ketquacongtacId;

    public String getSoquyetdinh() {
        return soquyetdinh;
    }

    public void setSoquyetdinh(String soquyetdinh) {
        this.soquyetdinh = soquyetdinh;
    }



    public String getSohopdong() {
        return sohopdong;
    }

    public void setSohopdong(String sohopdong) {
        this.sohopdong = sohopdong;
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

    public String getNgayquyetdinh() {
        return ngayquyetdinh;
    }

    public void setNgayquyetdinh(String ngayquyetdinh) {
        this.ngayquyetdinh = ngayquyetdinh;
    }

    public String getNgayhopdong() {
        return ngayhopdong;
    }

    public void setNgayhopdong(String ngayhopdong) {
        this.ngayhopdong = ngayhopdong;
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

    public List<Integer> getDanhsachKiThuatHoTroID() {
        return danhsachKiThuatHoTroID;
    }

    public void setDanhsachKiThuatHoTroID(List<Integer> danhsachKiThuatHoTroID) {
        this.danhsachKiThuatHoTroID = danhsachKiThuatHoTroID;
    }

    public List<Integer> getDanhsachVatTuHoTroID() {
        return danhsachVatTuHoTroID;
    }

    public void setDanhsachVatTuHoTroID(List<Integer> danhsachVatTuHoTroID) {
        this.danhsachVatTuHoTroID = danhsachVatTuHoTroID;
    }

    public List<Integer> getDanhsachNoiDenCongTacID() {
        return danhsachNoiDenCongTacID;
    }

    public void setDanhsachNoiDenCongTacID(List<Integer> danhsachNoiDenCongTacID) {
        this.danhsachNoiDenCongTacID = danhsachNoiDenCongTacID;
    }

    public Integer getLydocongtacId() {
        return lydocongtacId;
    }

    public void setLydocongtacId(Integer lydocongtacId) {
        this.lydocongtacId = lydocongtacId;
    }

    public Integer getKetquacongtacId() {
        return ketquacongtacId;
    }

    public void setKetquacongtacId(Integer ketquacongtacId) {
        this.ketquacongtacId = ketquacongtacId;
    }
}
