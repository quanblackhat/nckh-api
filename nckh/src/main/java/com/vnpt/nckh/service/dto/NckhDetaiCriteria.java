package com.vnpt.nckh.service.dto;

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
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.vnpt.nckh.domain.NckhDetai} entity. This class is used
 * in {@link com.vnpt.nckh.web.rest.NckhDetaiResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nckh-detais?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NckhDetaiCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maDetai;

    private StringFilter tenDetai;

    private StringFilter muctieu;

    private InstantFilter ngayBdDuKien;

    private InstantFilter ngayKtDuKien;

    private FloatFilter kinhPhiDuKien;

    private StringFilter noiDungTongQuan;

    private FloatFilter kinhPhiHoanThanh;

    private FloatFilter tongDiem;

    private LongFilter nckhNhanSuId;

    private LongFilter nckhTiendoId;

    private LongFilter nckhUpfileId;

    private LongFilter nckhDanhmucId;

    public NckhDetaiCriteria(){
    }

    public NckhDetaiCriteria(NckhDetaiCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maDetai = other.maDetai == null ? null : other.maDetai.copy();
        this.tenDetai = other.tenDetai == null ? null : other.tenDetai.copy();
        this.muctieu = other.muctieu == null ? null : other.muctieu.copy();
        this.ngayBdDuKien = other.ngayBdDuKien == null ? null : other.ngayBdDuKien.copy();
        this.ngayKtDuKien = other.ngayKtDuKien == null ? null : other.ngayKtDuKien.copy();
        this.kinhPhiDuKien = other.kinhPhiDuKien == null ? null : other.kinhPhiDuKien.copy();
        this.noiDungTongQuan = other.noiDungTongQuan == null ? null : other.noiDungTongQuan.copy();
        this.kinhPhiHoanThanh = other.kinhPhiHoanThanh == null ? null : other.kinhPhiHoanThanh.copy();
        this.tongDiem = other.tongDiem == null ? null : other.tongDiem.copy();
        this.nckhNhanSuId = other.nckhNhanSuId == null ? null : other.nckhNhanSuId.copy();
        this.nckhTiendoId = other.nckhTiendoId == null ? null : other.nckhTiendoId.copy();
        this.nckhUpfileId = other.nckhUpfileId == null ? null : other.nckhUpfileId.copy();
        this.nckhDanhmucId = other.nckhDanhmucId == null ? null : other.nckhDanhmucId.copy();
    }

    @Override
    public NckhDetaiCriteria copy() {
        return new NckhDetaiCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaDetai() {
        return maDetai;
    }

    public void setMaDetai(StringFilter maDetai) {
        this.maDetai = maDetai;
    }

    public StringFilter getTenDetai() {
        return tenDetai;
    }

    public void setTenDetai(StringFilter tenDetai) {
        this.tenDetai = tenDetai;
    }

    public StringFilter getMuctieu() {
        return muctieu;
    }

    public void setMuctieu(StringFilter muctieu) {
        this.muctieu = muctieu;
    }

    public InstantFilter getNgayBdDuKien() {
        return ngayBdDuKien;
    }

    public void setNgayBdDuKien(InstantFilter ngayBdDuKien) {
        this.ngayBdDuKien = ngayBdDuKien;
    }

    public InstantFilter getNgayKtDuKien() {
        return ngayKtDuKien;
    }

    public void setNgayKtDuKien(InstantFilter ngayKtDuKien) {
        this.ngayKtDuKien = ngayKtDuKien;
    }

    public FloatFilter getKinhPhiDuKien() {
        return kinhPhiDuKien;
    }

    public void setKinhPhiDuKien(FloatFilter kinhPhiDuKien) {
        this.kinhPhiDuKien = kinhPhiDuKien;
    }

    public StringFilter getNoiDungTongQuan() {
        return noiDungTongQuan;
    }

    public void setNoiDungTongQuan(StringFilter noiDungTongQuan) {
        this.noiDungTongQuan = noiDungTongQuan;
    }

    public FloatFilter getKinhPhiHoanThanh() {
        return kinhPhiHoanThanh;
    }

    public void setKinhPhiHoanThanh(FloatFilter kinhPhiHoanThanh) {
        this.kinhPhiHoanThanh = kinhPhiHoanThanh;
    }

    public FloatFilter getTongDiem() {
        return tongDiem;
    }

    public void setTongDiem(FloatFilter tongDiem) {
        this.tongDiem = tongDiem;
    }

    public LongFilter getNckhNhanSuId() {
        return nckhNhanSuId;
    }

    public void setNckhNhanSuId(LongFilter nckhNhanSuId) {
        this.nckhNhanSuId = nckhNhanSuId;
    }

    public LongFilter getNckhTiendoId() {
        return nckhTiendoId;
    }

    public void setNckhTiendoId(LongFilter nckhTiendoId) {
        this.nckhTiendoId = nckhTiendoId;
    }

    public LongFilter getNckhUpfileId() {
        return nckhUpfileId;
    }

    public void setNckhUpfileId(LongFilter nckhUpfileId) {
        this.nckhUpfileId = nckhUpfileId;
    }

    public LongFilter getNckhDanhmucId() {
        return nckhDanhmucId;
    }

    public void setNckhDanhmucId(LongFilter nckhDanhmucId) {
        this.nckhDanhmucId = nckhDanhmucId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NckhDetaiCriteria that = (NckhDetaiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maDetai, that.maDetai) &&
            Objects.equals(tenDetai, that.tenDetai) &&
            Objects.equals(muctieu, that.muctieu) &&
            Objects.equals(ngayBdDuKien, that.ngayBdDuKien) &&
            Objects.equals(ngayKtDuKien, that.ngayKtDuKien) &&
            Objects.equals(kinhPhiDuKien, that.kinhPhiDuKien) &&
            Objects.equals(noiDungTongQuan, that.noiDungTongQuan) &&
            Objects.equals(kinhPhiHoanThanh, that.kinhPhiHoanThanh) &&
            Objects.equals(tongDiem, that.tongDiem) &&
            Objects.equals(nckhNhanSuId, that.nckhNhanSuId) &&
            Objects.equals(nckhTiendoId, that.nckhTiendoId) &&
            Objects.equals(nckhUpfileId, that.nckhUpfileId) &&
            Objects.equals(nckhDanhmucId, that.nckhDanhmucId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maDetai,
        tenDetai,
        muctieu,
        ngayBdDuKien,
        ngayKtDuKien,
        kinhPhiDuKien,
        noiDungTongQuan,
        kinhPhiHoanThanh,
        tongDiem,
        nckhNhanSuId,
        nckhTiendoId,
        nckhUpfileId,
        nckhDanhmucId
        );
    }

    @Override
    public String toString() {
        return "NckhDetaiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maDetai != null ? "maDetai=" + maDetai + ", " : "") +
                (tenDetai != null ? "tenDetai=" + tenDetai + ", " : "") +
                (muctieu != null ? "muctieu=" + muctieu + ", " : "") +
                (ngayBdDuKien != null ? "ngayBdDuKien=" + ngayBdDuKien + ", " : "") +
                (ngayKtDuKien != null ? "ngayKtDuKien=" + ngayKtDuKien + ", " : "") +
                (kinhPhiDuKien != null ? "kinhPhiDuKien=" + kinhPhiDuKien + ", " : "") +
                (noiDungTongQuan != null ? "noiDungTongQuan=" + noiDungTongQuan + ", " : "") +
                (kinhPhiHoanThanh != null ? "kinhPhiHoanThanh=" + kinhPhiHoanThanh + ", " : "") +
                (tongDiem != null ? "tongDiem=" + tongDiem + ", " : "") +
                (nckhNhanSuId != null ? "nckhNhanSuId=" + nckhNhanSuId + ", " : "") +
                (nckhTiendoId != null ? "nckhTiendoId=" + nckhTiendoId + ", " : "") +
                (nckhUpfileId != null ? "nckhUpfileId=" + nckhUpfileId + ", " : "") +
                (nckhDanhmucId != null ? "nckhDanhmucId=" + nckhDanhmucId + ", " : "") +
            "}";
    }

}
