package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.vnptit.vnpthis.domain.nckh.LoaiDanhMuc;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link LoaiDanhMuc} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.LoaiDanhMucResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /loai-danh-mucs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LoaiDanhMucCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ten;

    private IntegerFilter sudung;

    private LongFilter danhMucId;

    public LoaiDanhMucCriteria(){
    }

    public LoaiDanhMucCriteria(LoaiDanhMucCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.danhMucId = other.danhMucId == null ? null : other.danhMucId.copy();
    }

    @Override
    public LoaiDanhMucCriteria copy() {
        return new LoaiDanhMucCriteria(this);
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

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getDanhMucId() {
        return danhMucId;
    }

    public void setDanhMucId(LongFilter danhMucId) {
        this.danhMucId = danhMucId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LoaiDanhMucCriteria that = (LoaiDanhMucCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(danhMucId, that.danhMucId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ten,
        sudung,
        danhMucId
        );
    }

    @Override
    public String toString() {
        return "LoaiDanhMucCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (danhMucId != null ? "danhMucId=" + danhMucId + ", " : "") +
            "}";
    }

}
