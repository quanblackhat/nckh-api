package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.vnptit.vnpthis.domain.nckh.NhanSu;
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
 * Criteria class for the {@link NhanSu} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.NhanSuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nhan-sus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NhanSuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter chunhiemdetai;

    private LocalDateFilter ngayCn;

    private IntegerFilter nguoiCn;

    private LongFilter deTaiId;

    public NhanSuCriteria(){
    }

    public NhanSuCriteria(NhanSuCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.chunhiemdetai = other.chunhiemdetai == null ? null : other.chunhiemdetai.copy();
        this.ngayCn = other.ngayCn == null ? null : other.ngayCn.copy();
        this.nguoiCn = other.nguoiCn == null ? null : other.nguoiCn.copy();
        this.deTaiId = other.deTaiId == null ? null : other.deTaiId.copy();
    }

    @Override
    public NhanSuCriteria copy() {
        return new NhanSuCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getChunhiemdetai() {
        return chunhiemdetai;
    }

    public void setChunhiemdetai(IntegerFilter chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
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
        final NhanSuCriteria that = (NhanSuCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(chunhiemdetai, that.chunhiemdetai) &&
            Objects.equals(ngayCn, that.ngayCn) &&
            Objects.equals(nguoiCn, that.nguoiCn) &&
            Objects.equals(deTaiId, that.deTaiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        chunhiemdetai,
        ngayCn,
        nguoiCn,
        deTaiId
        );
    }

    @Override
    public String toString() {
        return "NhanSuCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (chunhiemdetai != null ? "chunhiemdetai=" + chunhiemdetai + ", " : "") +
                (ngayCn != null ? "ngayCn=" + ngayCn + ", " : "") +
                (nguoiCn != null ? "nguoiCn=" + nguoiCn + ", " : "") +
                (deTaiId != null ? "deTaiId=" + deTaiId + ", " : "") +
            "}";
    }

}
