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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtDmNguoidung} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtDmNguoidungResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-dm-nguoidungs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtDmNguoidungCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ten;

    private LocalDateFilter ngaysinh;

    private StringFilter mabenhvien;

    private IntegerFilter sudung;

    private LongFilter daoTaoCtId;

    private LongFilter hocVienId;

    public QldtDmNguoidungCriteria() {
    }

    public QldtDmNguoidungCriteria(QldtDmNguoidungCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.ngaysinh = other.ngaysinh == null ? null : other.ngaysinh.copy();
        this.mabenhvien = other.mabenhvien == null ? null : other.mabenhvien.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.daoTaoCtId = other.daoTaoCtId == null ? null : other.daoTaoCtId.copy();
        this.hocVienId = other.hocVienId == null ? null : other.hocVienId.copy();
    }

    @Override
    public QldtDmNguoidungCriteria copy() {
        return new QldtDmNguoidungCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTen() {
        return ten;
    }

    public void setTen(StringFilter ten) {
        this.ten = ten;
    }

    public LocalDateFilter getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(LocalDateFilter ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public StringFilter getMabenhvien() {
        return mabenhvien;
    }

    public void setMabenhvien(StringFilter mabenhvien) {
        this.mabenhvien = mabenhvien;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getDaoTaoCtId() {
        return daoTaoCtId;
    }

    public void setDaoTaoCtId(LongFilter daoTaoCtId) {
        this.daoTaoCtId = daoTaoCtId;
    }

    public LongFilter getHocVienId() {
        return hocVienId;
    }

    public void setHocVienId(LongFilter hocVienId) {
        this.hocVienId = hocVienId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QldtDmNguoidungCriteria that = (QldtDmNguoidungCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(ngaysinh, that.ngaysinh) &&
            Objects.equals(mabenhvien, that.mabenhvien) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(daoTaoCtId, that.daoTaoCtId) &&
            Objects.equals(hocVienId, that.hocVienId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ten,
        ngaysinh,
        mabenhvien,
        sudung,
        daoTaoCtId,
        hocVienId
        );
    }

    @Override
    public String toString() {
        return "QldtDmNguoidungCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (ngaysinh != null ? "ngaysinh=" + ngaysinh + ", " : "") +
                (mabenhvien != null ? "mabenhvien=" + mabenhvien + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (daoTaoCtId != null ? "daoTaoCtId=" + daoTaoCtId + ", " : "") +
                (hocVienId != null ? "hocVienId=" + hocVienId + ", " : "") +
            "}";
    }

}
