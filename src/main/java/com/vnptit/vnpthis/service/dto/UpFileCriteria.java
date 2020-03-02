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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.UpFile} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.UpFileResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /up-files?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UpFileCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter mota;

    private LocalDateFilter ngayCn;

    private IntegerFilter nguoiCn;

    private LongFilter deTaiId;

    private LongFilter tienDoId;

    public UpFileCriteria(){
    }

    public UpFileCriteria(UpFileCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.mota = other.mota == null ? null : other.mota.copy();
        this.ngayCn = other.ngayCn == null ? null : other.ngayCn.copy();
        this.nguoiCn = other.nguoiCn == null ? null : other.nguoiCn.copy();
        this.deTaiId = other.deTaiId == null ? null : other.deTaiId.copy();
        this.tienDoId = other.tienDoId == null ? null : other.tienDoId.copy();
    }

    @Override
    public UpFileCriteria copy() {
        return new UpFileCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public LongFilter getDeTaiId() {
        return deTaiId;
    }

    public void setDeTaiId(LongFilter deTaiId) {
        this.deTaiId = deTaiId;
    }

    public LongFilter getTienDoId() {
        return tienDoId;
    }

    public void setTienDoId(LongFilter tienDoId) {
        this.tienDoId = tienDoId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UpFileCriteria that = (UpFileCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mota, that.mota) &&
            Objects.equals(ngayCn, that.ngayCn) &&
            Objects.equals(nguoiCn, that.nguoiCn) &&
            Objects.equals(deTaiId, that.deTaiId) &&
            Objects.equals(tienDoId, that.tienDoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mota,
        ngayCn,
        nguoiCn,
        deTaiId,
        tienDoId
        );
    }

    @Override
    public String toString() {
        return "UpFileCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mota != null ? "mota=" + mota + ", " : "") +
                (ngayCn != null ? "ngayCn=" + ngayCn + ", " : "") +
                (nguoiCn != null ? "nguoiCn=" + nguoiCn + ", " : "") +
                (deTaiId != null ? "deTaiId=" + deTaiId + ", " : "") +
                (tienDoId != null ? "tienDoId=" + tienDoId + ", " : "") +
            "}";
    }

}
