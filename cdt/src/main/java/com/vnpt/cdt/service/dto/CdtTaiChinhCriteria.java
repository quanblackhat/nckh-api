package com.vnpt.cdt.service.dto;

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

/**
 * Criteria class for the {@link com.vnpt.cdt.domain.CdtTaiChinh} entity. This class is used
 * in {@link com.vnpt.cdt.web.rest.CdtTaiChinhResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cdt-tai-chinhs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CdtTaiChinhCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter loai;

    private LongFilter soTien;

    private LongFilter csytid;

    private LongFilter cdtChiDaoTuyenId;

    public CdtTaiChinhCriteria(){
    }

    public CdtTaiChinhCriteria(CdtTaiChinhCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.loai = other.loai == null ? null : other.loai.copy();
        this.soTien = other.soTien == null ? null : other.soTien.copy();
        this.csytid = other.csytid == null ? null : other.csytid.copy();
        this.cdtChiDaoTuyenId = other.cdtChiDaoTuyenId == null ? null : other.cdtChiDaoTuyenId.copy();
    }

    @Override
    public CdtTaiChinhCriteria copy() {
        return new CdtTaiChinhCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLoai() {
        return loai;
    }

    public void setLoai(StringFilter loai) {
        this.loai = loai;
    }

    public LongFilter getSoTien() {
        return soTien;
    }

    public void setSoTien(LongFilter soTien) {
        this.soTien = soTien;
    }

    public LongFilter getCsytid() {
        return csytid;
    }

    public void setCsytid(LongFilter csytid) {
        this.csytid = csytid;
    }

    public LongFilter getCdtChiDaoTuyenId() {
        return cdtChiDaoTuyenId;
    }

    public void setCdtChiDaoTuyenId(LongFilter cdtChiDaoTuyenId) {
        this.cdtChiDaoTuyenId = cdtChiDaoTuyenId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CdtTaiChinhCriteria that = (CdtTaiChinhCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(loai, that.loai) &&
            Objects.equals(soTien, that.soTien) &&
            Objects.equals(csytid, that.csytid) &&
            Objects.equals(cdtChiDaoTuyenId, that.cdtChiDaoTuyenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        loai,
        soTien,
        csytid,
        cdtChiDaoTuyenId
        );
    }

    @Override
    public String toString() {
        return "CdtTaiChinhCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (loai != null ? "loai=" + loai + ", " : "") +
                (soTien != null ? "soTien=" + soTien + ", " : "") +
                (csytid != null ? "csytid=" + csytid + ", " : "") +
                (cdtChiDaoTuyenId != null ? "cdtChiDaoTuyenId=" + cdtChiDaoTuyenId + ", " : "") +
            "}";
    }

}
