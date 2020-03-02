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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.DanhMuc} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.DanhMucResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-mucs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DanhMucCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ma;

    private LocalDateFilter ngaytao;

    private IntegerFilter sudung;

    private StringFilter ten;

    private IntegerFilter thutu;

    private LongFilter loaiDanhMucId;

    public DanhMucCriteria(){
    }

    public DanhMucCriteria(DanhMucCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ma = other.ma == null ? null : other.ma.copy();
        this.ngaytao = other.ngaytao == null ? null : other.ngaytao.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.thutu = other.thutu == null ? null : other.thutu.copy();
        this.loaiDanhMucId = other.loaiDanhMucId == null ? null : other.loaiDanhMucId.copy();
    }

    @Override
    public DanhMucCriteria copy() {
        return new DanhMucCriteria(this);
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

    public LocalDateFilter getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateFilter ngaytao) {
        this.ngaytao = ngaytao;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public StringFilter getTen() {
        return ten;
    }

    public void setTen(StringFilter ten) {
        this.ten = ten;
    }

    public IntegerFilter getThutu() {
        return thutu;
    }

    public void setThutu(IntegerFilter thutu) {
        this.thutu = thutu;
    }

    public LongFilter getLoaiDanhMucId() {
        return loaiDanhMucId;
    }

    public void setLoaiDanhMucId(LongFilter loaiDanhMucId) {
        this.loaiDanhMucId = loaiDanhMucId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DanhMucCriteria that = (DanhMucCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ma, that.ma) &&
            Objects.equals(ngaytao, that.ngaytao) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(thutu, that.thutu) &&
            Objects.equals(loaiDanhMucId, that.loaiDanhMucId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ma,
        ngaytao,
        sudung,
        ten,
        thutu,
        loaiDanhMucId
        );
    }

    @Override
    public String toString() {
        return "DanhMucCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ma != null ? "ma=" + ma + ", " : "") +
                (ngaytao != null ? "ngaytao=" + ngaytao + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (thutu != null ? "thutu=" + thutu + ", " : "") +
                (loaiDanhMucId != null ? "loaiDanhMucId=" + loaiDanhMucId + ", " : "") +
            "}";
    }

}
