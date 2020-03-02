package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.vnptit.vnpthis.domain.nckh.DuToan;
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
 * Criteria class for the {@link DuToan} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.DuToanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /du-toans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DuToanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter tienDukien;

    private IntegerFilter tienHoanthanh;

    private StringFilter ghichu;

    private LocalDateFilter ngayCn;

    private IntegerFilter nguoiCn;

    private LongFilter deTaiId;

    public DuToanCriteria(){
    }

    public DuToanCriteria(DuToanCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.tienDukien = other.tienDukien == null ? null : other.tienDukien.copy();
        this.tienHoanthanh = other.tienHoanthanh == null ? null : other.tienHoanthanh.copy();
        this.ghichu = other.ghichu == null ? null : other.ghichu.copy();
        this.ngayCn = other.ngayCn == null ? null : other.ngayCn.copy();
        this.nguoiCn = other.nguoiCn == null ? null : other.nguoiCn.copy();
        this.deTaiId = other.deTaiId == null ? null : other.deTaiId.copy();
    }

    @Override
    public DuToanCriteria copy() {
        return new DuToanCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTienDukien() {
        return tienDukien;
    }

    public void setTienDukien(IntegerFilter tienDukien) {
        this.tienDukien = tienDukien;
    }

    public IntegerFilter getTienHoanthanh() {
        return tienHoanthanh;
    }

    public void setTienHoanthanh(IntegerFilter tienHoanthanh) {
        this.tienHoanthanh = tienHoanthanh;
    }

    public StringFilter getGhichu() {
        return ghichu;
    }

    public void setGhichu(StringFilter ghichu) {
        this.ghichu = ghichu;
    }

    public LocalDateFilter getNgayCn() {
        return ngayCn;
    }

    public void setNgayCn(LocalDateFilter ngayCn) {
        this.ngayCn = ngayCn;
    }

    public IntegerFilter getNguoiCn() {
        return nguoiCn;
    }

    public void setNguoiCn(IntegerFilter nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public LongFilter getDeTaiId() {
        return deTaiId;
    }

    public void setDeTaiId(LongFilter deTaiId) {
        this.deTaiId = deTaiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DuToanCriteria that = (DuToanCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tienDukien, that.tienDukien) &&
            Objects.equals(tienHoanthanh, that.tienHoanthanh) &&
            Objects.equals(ghichu, that.ghichu) &&
            Objects.equals(ngayCn, that.ngayCn) &&
            Objects.equals(nguoiCn, that.nguoiCn) &&
            Objects.equals(deTaiId, that.deTaiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tienDukien,
        tienHoanthanh,
        ghichu,
        ngayCn,
        nguoiCn,
        deTaiId
        );
    }

    @Override
    public String toString() {
        return "DuToanCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tienDukien != null ? "tienDukien=" + tienDukien + ", " : "") +
                (tienHoanthanh != null ? "tienHoanthanh=" + tienHoanthanh + ", " : "") +
                (ghichu != null ? "ghichu=" + ghichu + ", " : "") +
                (ngayCn != null ? "ngayCn=" + ngayCn + ", " : "") +
                (nguoiCn != null ? "nguoiCn=" + nguoiCn + ", " : "") +
                (deTaiId != null ? "deTaiId=" + deTaiId + ", " : "") +
            "}";
    }

}
