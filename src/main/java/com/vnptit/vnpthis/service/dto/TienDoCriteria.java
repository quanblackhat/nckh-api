package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.vnptit.vnpthis.domain.nckh.TienDo;
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
 * Criteria class for the {@link TienDo} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.TienDoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tien-dos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TienDoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter tyleHoanthanh;

    private StringFilter mota;

    private LocalDateFilter ngayCn;

    private IntegerFilter nguoiCn;

    private LongFilter upFileId;

    private LongFilter deTaiId;

    public TienDoCriteria(){
    }

    public TienDoCriteria(TienDoCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.tyleHoanthanh = other.tyleHoanthanh == null ? null : other.tyleHoanthanh.copy();
        this.mota = other.mota == null ? null : other.mota.copy();
        this.ngayCn = other.ngayCn == null ? null : other.ngayCn.copy();
        this.nguoiCn = other.nguoiCn == null ? null : other.nguoiCn.copy();
        this.upFileId = other.upFileId == null ? null : other.upFileId.copy();
        this.deTaiId = other.deTaiId == null ? null : other.deTaiId.copy();
    }

    @Override
    public TienDoCriteria copy() {
        return new TienDoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTyleHoanthanh() {
        return tyleHoanthanh;
    }

    public void setTyleHoanthanh(IntegerFilter tyleHoanthanh) {
        this.tyleHoanthanh = tyleHoanthanh;
    }

    public StringFilter getMota() {
        return mota;
    }

    public void setMota(StringFilter mota) {
        this.mota = mota;
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

    public LongFilter getUpFileId() {
        return upFileId;
    }

    public void setUpFileId(LongFilter upFileId) {
        this.upFileId = upFileId;
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
        final TienDoCriteria that = (TienDoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tyleHoanthanh, that.tyleHoanthanh) &&
            Objects.equals(mota, that.mota) &&
            Objects.equals(ngayCn, that.ngayCn) &&
            Objects.equals(nguoiCn, that.nguoiCn) &&
            Objects.equals(upFileId, that.upFileId) &&
            Objects.equals(deTaiId, that.deTaiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tyleHoanthanh,
        mota,
        ngayCn,
        nguoiCn,
        upFileId,
        deTaiId
        );
    }

    @Override
    public String toString() {
        return "TienDoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tyleHoanthanh != null ? "tyleHoanthanh=" + tyleHoanthanh + ", " : "") +
                (mota != null ? "mota=" + mota + ", " : "") +
                (ngayCn != null ? "ngayCn=" + ngayCn + ", " : "") +
                (nguoiCn != null ? "nguoiCn=" + nguoiCn + ", " : "") +
                (upFileId != null ? "upFileId=" + upFileId + ", " : "") +
                (deTaiId != null ? "deTaiId=" + deTaiId + ", " : "") +
            "}";
    }

}
