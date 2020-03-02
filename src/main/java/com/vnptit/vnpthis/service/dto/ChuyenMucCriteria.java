package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.vnptit.vnpthis.domain.nckh.ChuyenMuc;
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
 * Criteria class for the {@link ChuyenMuc} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.ChuyenMucResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /chuyen-mucs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ChuyenMucCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter ngaytao;

    private IntegerFilter sott;

    private StringFilter tenchuyenmuc;

    private LongFilter deTaiId;

    public ChuyenMucCriteria(){
    }

    public ChuyenMucCriteria(ChuyenMucCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ngaytao = other.ngaytao == null ? null : other.ngaytao.copy();
        this.sott = other.sott == null ? null : other.sott.copy();
        this.tenchuyenmuc = other.tenchuyenmuc == null ? null : other.tenchuyenmuc.copy();
        this.deTaiId = other.deTaiId == null ? null : other.deTaiId.copy();
    }

    @Override
    public ChuyenMucCriteria copy() {
        return new ChuyenMucCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateFilter ngaytao) {
        this.ngaytao = ngaytao;
    }

    public IntegerFilter getSott() {
        return sott;
    }

    public void setSott(IntegerFilter sott) {
        this.sott = sott;
    }

    public StringFilter getTenchuyenmuc() {
        return tenchuyenmuc;
    }

    public void setTenchuyenmuc(StringFilter tenchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
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
        final ChuyenMucCriteria that = (ChuyenMucCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ngaytao, that.ngaytao) &&
            Objects.equals(sott, that.sott) &&
            Objects.equals(tenchuyenmuc, that.tenchuyenmuc) &&
            Objects.equals(deTaiId, that.deTaiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ngaytao,
        sott,
        tenchuyenmuc,
        deTaiId
        );
    }

    @Override
    public String toString() {
        return "ChuyenMucCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ngaytao != null ? "ngaytao=" + ngaytao + ", " : "") +
                (sott != null ? "sott=" + sott + ", " : "") +
                (tenchuyenmuc != null ? "tenchuyenmuc=" + tenchuyenmuc + ", " : "") +
                (deTaiId != null ? "deTaiId=" + deTaiId + ", " : "") +
            "}";
    }

}
