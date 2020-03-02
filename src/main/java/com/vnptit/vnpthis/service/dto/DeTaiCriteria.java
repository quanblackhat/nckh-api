package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.vnptit.vnpthis.domain.DeTai} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.DeTaiResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /de-tais?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DeTaiCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sott;

    private StringFilter tendetai;

    private LocalDateFilter ngaytao;

    private LocalDateFilter ngaypheduyet;

    private LocalDateFilter ngaybd;

    private LocalDateFilter ngaykt;

    private IntegerFilter hientrang;

    private IntegerFilter xuatban;

    private IntegerFilter capquanly;

    private LocalDateFilter ngaynghiemthu;

    private IntegerFilter kinhphithuchien;

    private StringFilter nguonkinhphi;

    private StringFilter muctieu;

    private IntegerFilter kinhphiDukien;

    private IntegerFilter chunhiemdetai;

    private StringFilter noidungtongquan;

    private IntegerFilter kinhphiHoanthanh;

    private IntegerFilter tongdiem;

    private StringFilter ghichu;

    private IntegerFilter nguoiCn;

    private LocalDateFilter ngayCn;

    private LongFilter upFileId;

    private LongFilter tienDoId;

    private LongFilter nhanSuId;

    private LongFilter duToanId;

    private LongFilter danhGiaId;

    private LongFilter chuyenMucId;

    public DeTaiCriteria(){
    }

    public DeTaiCriteria(DeTaiCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sott = other.sott == null ? null : other.sott.copy();
        this.tendetai = other.tendetai == null ? null : other.tendetai.copy();
        this.ngaytao = other.ngaytao == null ? null : other.ngaytao.copy();
        this.ngaypheduyet = other.ngaypheduyet == null ? null : other.ngaypheduyet.copy();
        this.ngaybd = other.ngaybd == null ? null : other.ngaybd.copy();
        this.ngaykt = other.ngaykt == null ? null : other.ngaykt.copy();
        this.hientrang = other.hientrang == null ? null : other.hientrang.copy();
        this.xuatban = other.xuatban == null ? null : other.xuatban.copy();
        this.capquanly = other.capquanly == null ? null : other.capquanly.copy();
        this.ngaynghiemthu = other.ngaynghiemthu == null ? null : other.ngaynghiemthu.copy();
        this.kinhphithuchien = other.kinhphithuchien == null ? null : other.kinhphithuchien.copy();
        this.nguonkinhphi = other.nguonkinhphi == null ? null : other.nguonkinhphi.copy();
        this.muctieu = other.muctieu == null ? null : other.muctieu.copy();
        this.kinhphiDukien = other.kinhphiDukien == null ? null : other.kinhphiDukien.copy();
        this.chunhiemdetai = other.chunhiemdetai == null ? null : other.chunhiemdetai.copy();
        this.noidungtongquan = other.noidungtongquan == null ? null : other.noidungtongquan.copy();
        this.kinhphiHoanthanh = other.kinhphiHoanthanh == null ? null : other.kinhphiHoanthanh.copy();
        this.tongdiem = other.tongdiem == null ? null : other.tongdiem.copy();
        this.ghichu = other.ghichu == null ? null : other.ghichu.copy();
        this.nguoiCn = other.nguoiCn == null ? null : other.nguoiCn.copy();
        this.ngayCn = other.ngayCn == null ? null : other.ngayCn.copy();
        this.upFileId = other.upFileId == null ? null : other.upFileId.copy();
        this.tienDoId = other.tienDoId == null ? null : other.tienDoId.copy();
        this.nhanSuId = other.nhanSuId == null ? null : other.nhanSuId.copy();
        this.duToanId = other.duToanId == null ? null : other.duToanId.copy();
        this.danhGiaId = other.danhGiaId == null ? null : other.danhGiaId.copy();
        this.chuyenMucId = other.chuyenMucId == null ? null : other.chuyenMucId.copy();
    }

    @Override
    public DeTaiCriteria copy() {
        return new DeTaiCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSott() {
        return sott;
    }

    public void setSott(IntegerFilter sott) {
        this.sott = sott;
    }

    public StringFilter getTendetai() {
        return tendetai;
    }

    public void setTendetai(StringFilter tendetai) {
        this.tendetai = tendetai;
    }

    public LocalDateFilter getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateFilter ngaytao) {
        this.ngaytao = ngaytao;
    }

    public LocalDateFilter getNgaypheduyet() {
        return ngaypheduyet;
    }

    public void setNgaypheduyet(LocalDateFilter ngaypheduyet) {
        this.ngaypheduyet = ngaypheduyet;
    }

    public LocalDateFilter getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(LocalDateFilter ngaybd) {
        this.ngaybd = ngaybd;
    }

    public LocalDateFilter getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(LocalDateFilter ngaykt) {
        this.ngaykt = ngaykt;
    }

    public IntegerFilter getHientrang() {
        return hientrang;
    }

    public void setHientrang(IntegerFilter hientrang) {
        this.hientrang = hientrang;
    }

    public IntegerFilter getXuatban() {
        return xuatban;
    }

    public void setXuatban(IntegerFilter xuatban) {
        this.xuatban = xuatban;
    }

    public IntegerFilter getCapquanly() {
        return capquanly;
    }

    public void setCapquanly(IntegerFilter capquanly) {
        this.capquanly = capquanly;
    }

    public LocalDateFilter getNgaynghiemthu() {
        return ngaynghiemthu;
    }

    public void setNgaynghiemthu(LocalDateFilter ngaynghiemthu) {
        this.ngaynghiemthu = ngaynghiemthu;
    }

    public IntegerFilter getKinhphithuchien() {
        return kinhphithuchien;
    }

    public void setKinhphithuchien(IntegerFilter kinhphithuchien) {
        this.kinhphithuchien = kinhphithuchien;
    }

    public StringFilter getNguonkinhphi() {
        return nguonkinhphi;
    }

    public void setNguonkinhphi(StringFilter nguonkinhphi) {
        this.nguonkinhphi = nguonkinhphi;
    }

    public StringFilter getMuctieu() {
        return muctieu;
    }

    public void setMuctieu(StringFilter muctieu) {
        this.muctieu = muctieu;
    }

    public IntegerFilter getKinhphiDukien() {
        return kinhphiDukien;
    }

    public void setKinhphiDukien(IntegerFilter kinhphiDukien) {
        this.kinhphiDukien = kinhphiDukien;
    }

    public IntegerFilter getChunhiemdetai() {
        return chunhiemdetai;
    }

    public void setChunhiemdetai(IntegerFilter chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
    }

    public StringFilter getNoidungtongquan() {
        return noidungtongquan;
    }

    public void setNoidungtongquan(StringFilter noidungtongquan) {
        this.noidungtongquan = noidungtongquan;
    }

    public IntegerFilter getKinhphiHoanthanh() {
        return kinhphiHoanthanh;
    }

    public void setKinhphiHoanthanh(IntegerFilter kinhphiHoanthanh) {
        this.kinhphiHoanthanh = kinhphiHoanthanh;
    }

    public IntegerFilter getTongdiem() {
        return tongdiem;
    }

    public void setTongdiem(IntegerFilter tongdiem) {
        this.tongdiem = tongdiem;
    }

    public StringFilter getGhichu() {
        return ghichu;
    }

    public void setGhichu(StringFilter ghichu) {
        this.ghichu = ghichu;
    }

    public IntegerFilter getNguoiCn() {
        return nguoiCn;
    }

    public void setNguoiCn(IntegerFilter nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public LocalDateFilter getNgayCn() {
        return ngayCn;
    }

    public void setNgayCn(LocalDateFilter ngayCn) {
        this.ngayCn = ngayCn;
    }

    public LongFilter getUpFileId() {
        return upFileId;
    }

    public void setUpFileId(LongFilter upFileId) {
        this.upFileId = upFileId;
    }

    public LongFilter getTienDoId() {
        return tienDoId;
    }

    public void setTienDoId(LongFilter tienDoId) {
        this.tienDoId = tienDoId;
    }

    public LongFilter getNhanSuId() {
        return nhanSuId;
    }

    public void setNhanSuId(LongFilter nhanSuId) {
        this.nhanSuId = nhanSuId;
    }

    public LongFilter getDuToanId() {
        return duToanId;
    }

    public void setDuToanId(LongFilter duToanId) {
        this.duToanId = duToanId;
    }

    public LongFilter getDanhGiaId() {
        return danhGiaId;
    }

    public void setDanhGiaId(LongFilter danhGiaId) {
        this.danhGiaId = danhGiaId;
    }

    public LongFilter getChuyenMucId() {
        return chuyenMucId;
    }

    public void setChuyenMucId(LongFilter chuyenMucId) {
        this.chuyenMucId = chuyenMucId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DeTaiCriteria that = (DeTaiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sott, that.sott) &&
            Objects.equals(tendetai, that.tendetai) &&
            Objects.equals(ngaytao, that.ngaytao) &&
            Objects.equals(ngaypheduyet, that.ngaypheduyet) &&
            Objects.equals(ngaybd, that.ngaybd) &&
            Objects.equals(ngaykt, that.ngaykt) &&
            Objects.equals(hientrang, that.hientrang) &&
            Objects.equals(xuatban, that.xuatban) &&
            Objects.equals(capquanly, that.capquanly) &&
            Objects.equals(ngaynghiemthu, that.ngaynghiemthu) &&
            Objects.equals(kinhphithuchien, that.kinhphithuchien) &&
            Objects.equals(nguonkinhphi, that.nguonkinhphi) &&
            Objects.equals(muctieu, that.muctieu) &&
            Objects.equals(kinhphiDukien, that.kinhphiDukien) &&
            Objects.equals(chunhiemdetai, that.chunhiemdetai) &&
            Objects.equals(noidungtongquan, that.noidungtongquan) &&
            Objects.equals(kinhphiHoanthanh, that.kinhphiHoanthanh) &&
            Objects.equals(tongdiem, that.tongdiem) &&
            Objects.equals(ghichu, that.ghichu) &&
            Objects.equals(nguoiCn, that.nguoiCn) &&
            Objects.equals(ngayCn, that.ngayCn) &&
            Objects.equals(upFileId, that.upFileId) &&
            Objects.equals(tienDoId, that.tienDoId) &&
            Objects.equals(nhanSuId, that.nhanSuId) &&
            Objects.equals(duToanId, that.duToanId) &&
            Objects.equals(danhGiaId, that.danhGiaId) &&
            Objects.equals(chuyenMucId, that.chuyenMucId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sott,
        tendetai,
        ngaytao,
        ngaypheduyet,
        ngaybd,
        ngaykt,
        hientrang,
        xuatban,
        capquanly,
        ngaynghiemthu,
        kinhphithuchien,
        nguonkinhphi,
        muctieu,
        kinhphiDukien,
        chunhiemdetai,
        noidungtongquan,
        kinhphiHoanthanh,
        tongdiem,
        ghichu,
        nguoiCn,
        ngayCn,
        upFileId,
        tienDoId,
        nhanSuId,
        duToanId,
        danhGiaId,
        chuyenMucId
        );
    }

    @Override
    public String toString() {
        return "DeTaiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sott != null ? "sott=" + sott + ", " : "") +
                (tendetai != null ? "tendetai=" + tendetai + ", " : "") +
                (ngaytao != null ? "ngaytao=" + ngaytao + ", " : "") +
                (ngaypheduyet != null ? "ngaypheduyet=" + ngaypheduyet + ", " : "") +
                (ngaybd != null ? "ngaybd=" + ngaybd + ", " : "") +
                (ngaykt != null ? "ngaykt=" + ngaykt + ", " : "") +
                (hientrang != null ? "hientrang=" + hientrang + ", " : "") +
                (xuatban != null ? "xuatban=" + xuatban + ", " : "") +
                (capquanly != null ? "capquanly=" + capquanly + ", " : "") +
                (ngaynghiemthu != null ? "ngaynghiemthu=" + ngaynghiemthu + ", " : "") +
                (kinhphithuchien != null ? "kinhphithuchien=" + kinhphithuchien + ", " : "") +
                (nguonkinhphi != null ? "nguonkinhphi=" + nguonkinhphi + ", " : "") +
                (muctieu != null ? "muctieu=" + muctieu + ", " : "") +
                (kinhphiDukien != null ? "kinhphiDukien=" + kinhphiDukien + ", " : "") +
                (chunhiemdetai != null ? "chunhiemdetai=" + chunhiemdetai + ", " : "") +
                (noidungtongquan != null ? "noidungtongquan=" + noidungtongquan + ", " : "") +
                (kinhphiHoanthanh != null ? "kinhphiHoanthanh=" + kinhphiHoanthanh + ", " : "") +
                (tongdiem != null ? "tongdiem=" + tongdiem + ", " : "") +
                (ghichu != null ? "ghichu=" + ghichu + ", " : "") +
                (nguoiCn != null ? "nguoiCn=" + nguoiCn + ", " : "") +
                (ngayCn != null ? "ngayCn=" + ngayCn + ", " : "") +
                (upFileId != null ? "upFileId=" + upFileId + ", " : "") +
                (tienDoId != null ? "tienDoId=" + tienDoId + ", " : "") +
                (nhanSuId != null ? "nhanSuId=" + nhanSuId + ", " : "") +
                (duToanId != null ? "duToanId=" + duToanId + ", " : "") +
                (danhGiaId != null ? "danhGiaId=" + danhGiaId + ", " : "") +
                (chuyenMucId != null ? "chuyenMucId=" + chuyenMucId + ", " : "") +
            "}";
    }

}
