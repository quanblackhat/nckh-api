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
 * Criteria class for the {@link com.vnpt.cdt.domain.CdtKyThuat} entity. This class is used
 * in {@link com.vnpt.cdt.web.rest.CdtKyThuatResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cdt-ky-thuats?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CdtKyThuatCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter csytid;

    private LongFilter cdtKyThuatHoTroId;

    private LongFilter cdtChiDaoTuyenId;

    public CdtKyThuatCriteria(){
    }

    public CdtKyThuatCriteria(CdtKyThuatCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.csytid = other.csytid == null ? null : other.csytid.copy();
        this.cdtKyThuatHoTroId = other.cdtKyThuatHoTroId == null ? null : other.cdtKyThuatHoTroId.copy();
        this.cdtChiDaoTuyenId = other.cdtChiDaoTuyenId == null ? null : other.cdtChiDaoTuyenId.copy();
    }

    @Override
    public CdtKyThuatCriteria copy() {
        return new CdtKyThuatCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getCsytid() {
        return csytid;
    }

    public void setCsytid(LongFilter csytid) {
        this.csytid = csytid;
    }

    public LongFilter getCdtKyThuatHoTroId() {
        return cdtKyThuatHoTroId;
    }

    public void setCdtKyThuatHoTroId(LongFilter cdtKyThuatHoTroId) {
        this.cdtKyThuatHoTroId = cdtKyThuatHoTroId;
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
        final CdtKyThuatCriteria that = (CdtKyThuatCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(csytid, that.csytid) &&
            Objects.equals(cdtKyThuatHoTroId, that.cdtKyThuatHoTroId) &&
            Objects.equals(cdtChiDaoTuyenId, that.cdtChiDaoTuyenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        csytid,
        cdtKyThuatHoTroId,
        cdtChiDaoTuyenId
        );
    }

    @Override
    public String toString() {
        return "CdtKyThuatCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (csytid != null ? "csytid=" + csytid + ", " : "") +
                (cdtKyThuatHoTroId != null ? "cdtKyThuatHoTroId=" + cdtKyThuatHoTroId + ", " : "") +
                (cdtChiDaoTuyenId != null ? "cdtChiDaoTuyenId=" + cdtChiDaoTuyenId + ", " : "") +
            "}";
    }

}
