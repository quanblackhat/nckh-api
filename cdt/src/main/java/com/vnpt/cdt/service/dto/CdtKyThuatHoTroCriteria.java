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
 * Criteria class for the {@link com.vnpt.cdt.domain.CdtKyThuatHoTro} entity. This class is used
 * in {@link com.vnpt.cdt.web.rest.CdtKyThuatHoTroResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cdt-ky-thuat-ho-tros?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CdtKyThuatHoTroCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maNoiDen;

    private StringFilter tenNoiDen;

    private LongFilter thuTuSapXep;

    private LongFilter csytid;

    private LongFilter kythuatId;

    public CdtKyThuatHoTroCriteria(){
    }

    public CdtKyThuatHoTroCriteria(CdtKyThuatHoTroCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maNoiDen = other.maNoiDen == null ? null : other.maNoiDen.copy();
        this.tenNoiDen = other.tenNoiDen == null ? null : other.tenNoiDen.copy();
        this.thuTuSapXep = other.thuTuSapXep == null ? null : other.thuTuSapXep.copy();
        this.csytid = other.csytid == null ? null : other.csytid.copy();
        this.kythuatId = other.kythuatId == null ? null : other.kythuatId.copy();
    }

    @Override
    public CdtKyThuatHoTroCriteria copy() {
        return new CdtKyThuatHoTroCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaNoiDen() {
        return maNoiDen;
    }

    public void setMaNoiDen(StringFilter maNoiDen) {
        this.maNoiDen = maNoiDen;
    }

    public StringFilter getTenNoiDen() {
        return tenNoiDen;
    }

    public void setTenNoiDen(StringFilter tenNoiDen) {
        this.tenNoiDen = tenNoiDen;
    }

    public LongFilter getThuTuSapXep() {
        return thuTuSapXep;
    }

    public void setThuTuSapXep(LongFilter thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
    }

    public LongFilter getCsytid() {
        return csytid;
    }

    public void setCsytid(LongFilter csytid) {
        this.csytid = csytid;
    }

    public LongFilter getKythuatId() {
        return kythuatId;
    }

    public void setKythuatId(LongFilter kythuatId) {
        this.kythuatId = kythuatId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CdtKyThuatHoTroCriteria that = (CdtKyThuatHoTroCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maNoiDen, that.maNoiDen) &&
            Objects.equals(tenNoiDen, that.tenNoiDen) &&
            Objects.equals(thuTuSapXep, that.thuTuSapXep) &&
            Objects.equals(csytid, that.csytid) &&
            Objects.equals(kythuatId, that.kythuatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maNoiDen,
        tenNoiDen,
        thuTuSapXep,
        csytid,
        kythuatId
        );
    }

    @Override
    public String toString() {
        return "CdtKyThuatHoTroCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maNoiDen != null ? "maNoiDen=" + maNoiDen + ", " : "") +
                (tenNoiDen != null ? "tenNoiDen=" + tenNoiDen + ", " : "") +
                (thuTuSapXep != null ? "thuTuSapXep=" + thuTuSapXep + ", " : "") +
                (csytid != null ? "csytid=" + csytid + ", " : "") +
                (kythuatId != null ? "kythuatId=" + kythuatId + ", " : "") +
            "}";
    }

}
