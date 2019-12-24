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
 * Criteria class for the {@link com.vnpt.nckh.domain.NckhUpfile} entity. This class is used
 * in {@link com.vnpt.nckh.web.rest.NckhUpfileResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nckh-upfiles?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NckhUpfileCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter moTa;

    private LongFilter nguoiCN;

    private InstantFilter ngayCN;

    private LongFilter nckhDetaiId;

    public NckhUpfileCriteria(){
    }

    public NckhUpfileCriteria(NckhUpfileCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.moTa = other.moTa == null ? null : other.moTa.copy();
        this.nguoiCN = other.nguoiCN == null ? null : other.nguoiCN.copy();
        this.ngayCN = other.ngayCN == null ? null : other.ngayCN.copy();
        this.nckhDetaiId = other.nckhDetaiId == null ? null : other.nckhDetaiId.copy();
    }

    @Override
    public NckhUpfileCriteria copy() {
        return new NckhUpfileCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMoTa() {
        return moTa;
    }

    public void setMoTa(StringFilter moTa) {
        this.moTa = moTa;
    }

    public LongFilter getNguoiCN() {
        return nguoiCN;
    }

    public void setNguoiCN(LongFilter nguoiCN) {
        this.nguoiCN = nguoiCN;
    }

    public InstantFilter getNgayCN() {
        return ngayCN;
    }

    public void setNgayCN(InstantFilter ngayCN) {
        this.ngayCN = ngayCN;
    }

    public LongFilter getNckhDetaiId() {
        return nckhDetaiId;
    }

    public void setNckhDetaiId(LongFilter nckhDetaiId) {
        this.nckhDetaiId = nckhDetaiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NckhUpfileCriteria that = (NckhUpfileCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(moTa, that.moTa) &&
            Objects.equals(nguoiCN, that.nguoiCN) &&
            Objects.equals(ngayCN, that.ngayCN) &&
            Objects.equals(nckhDetaiId, that.nckhDetaiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        moTa,
        nguoiCN,
        ngayCN,
        nckhDetaiId
        );
    }

    @Override
    public String toString() {
        return "NckhUpfileCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (moTa != null ? "moTa=" + moTa + ", " : "") +
                (nguoiCN != null ? "nguoiCN=" + nguoiCN + ", " : "") +
                (ngayCN != null ? "ngayCN=" + ngayCN + ", " : "") +
                (nckhDetaiId != null ? "nckhDetaiId=" + nckhDetaiId + ", " : "") +
            "}";
    }

}
