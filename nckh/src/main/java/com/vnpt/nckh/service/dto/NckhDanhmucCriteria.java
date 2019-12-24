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
 * Criteria class for the {@link com.vnpt.nckh.domain.NckhDanhmuc} entity. This class is used
 * in {@link com.vnpt.nckh.web.rest.NckhDanhmucResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nckh-danhmucs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NckhDanhmucCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ma;

    private StringFilter ten;

    private InstantFilter ngaytao;

    private LongFilter csytid;

    private IntegerFilter sudung;

    private LongFilter thutu;

    private LongFilter nckhDetaiId;

    public NckhDanhmucCriteria(){
    }

    public NckhDanhmucCriteria(NckhDanhmucCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ma = other.ma == null ? null : other.ma.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.ngaytao = other.ngaytao == null ? null : other.ngaytao.copy();
        this.csytid = other.csytid == null ? null : other.csytid.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.thutu = other.thutu == null ? null : other.thutu.copy();
        this.nckhDetaiId = other.nckhDetaiId == null ? null : other.nckhDetaiId.copy();
    }

    @Override
    public NckhDanhmucCriteria copy() {
        return new NckhDanhmucCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMa() {
        return ma;
    }

    public void setMa(StringFilter ma) {
        this.ma = ma;
    }

    public StringFilter getTen() {
        return ten;
    }

    public void setTen(StringFilter ten) {
        this.ten = ten;
    }

    public InstantFilter getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(InstantFilter ngaytao) {
        this.ngaytao = ngaytao;
    }

    public LongFilter getCsytid() {
        return csytid;
    }

    public void setCsytid(LongFilter csytid) {
        this.csytid = csytid;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getThutu() {
        return thutu;
    }

    public void setThutu(LongFilter thutu) {
        this.thutu = thutu;
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
        final NckhDanhmucCriteria that = (NckhDanhmucCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ma, that.ma) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(ngaytao, that.ngaytao) &&
            Objects.equals(csytid, that.csytid) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(thutu, that.thutu) &&
            Objects.equals(nckhDetaiId, that.nckhDetaiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ma,
        ten,
        ngaytao,
        csytid,
        sudung,
        thutu,
        nckhDetaiId
        );
    }

    @Override
    public String toString() {
        return "NckhDanhmucCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ma != null ? "ma=" + ma + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (ngaytao != null ? "ngaytao=" + ngaytao + ", " : "") +
                (csytid != null ? "csytid=" + csytid + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (thutu != null ? "thutu=" + thutu + ", " : "") +
                (nckhDetaiId != null ? "nckhDetaiId=" + nckhDetaiId + ", " : "") +
            "}";
    }

}
