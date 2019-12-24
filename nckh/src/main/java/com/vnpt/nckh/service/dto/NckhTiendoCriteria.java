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
 * Criteria class for the {@link com.vnpt.nckh.domain.NckhTiendo} entity. This class is used
 * in {@link com.vnpt.nckh.web.rest.NckhTiendoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nckh-tiendos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NckhTiendoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter tienDoHoanThanh;

    private StringFilter moTa;

    private InstantFilter ngayCapNhat;

    private LongFilter nckhDetaiId;

    public NckhTiendoCriteria(){
    }

    public NckhTiendoCriteria(NckhTiendoCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.tienDoHoanThanh = other.tienDoHoanThanh == null ? null : other.tienDoHoanThanh.copy();
        this.moTa = other.moTa == null ? null : other.moTa.copy();
        this.ngayCapNhat = other.ngayCapNhat == null ? null : other.ngayCapNhat.copy();
        this.nckhDetaiId = other.nckhDetaiId == null ? null : other.nckhDetaiId.copy();
    }

    @Override
    public NckhTiendoCriteria copy() {
        return new NckhTiendoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getTienDoHoanThanh() {
        return tienDoHoanThanh;
    }

    public void setTienDoHoanThanh(LongFilter tienDoHoanThanh) {
        this.tienDoHoanThanh = tienDoHoanThanh;
    }

    public StringFilter getMoTa() {
        return moTa;
    }

    public void setMoTa(StringFilter moTa) {
        this.moTa = moTa;
    }

    public InstantFilter getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(InstantFilter ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
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
        final NckhTiendoCriteria that = (NckhTiendoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tienDoHoanThanh, that.tienDoHoanThanh) &&
            Objects.equals(moTa, that.moTa) &&
            Objects.equals(ngayCapNhat, that.ngayCapNhat) &&
            Objects.equals(nckhDetaiId, that.nckhDetaiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tienDoHoanThanh,
        moTa,
        ngayCapNhat,
        nckhDetaiId
        );
    }

    @Override
    public String toString() {
        return "NckhTiendoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tienDoHoanThanh != null ? "tienDoHoanThanh=" + tienDoHoanThanh + ", " : "") +
                (moTa != null ? "moTa=" + moTa + ", " : "") +
                (ngayCapNhat != null ? "ngayCapNhat=" + ngayCapNhat + ", " : "") +
                (nckhDetaiId != null ? "nckhDetaiId=" + nckhDetaiId + ", " : "") +
            "}";
    }

}
