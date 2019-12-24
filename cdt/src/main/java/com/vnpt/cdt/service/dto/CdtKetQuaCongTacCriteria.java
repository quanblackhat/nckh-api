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
 * Criteria class for the {@link com.vnpt.cdt.domain.CdtKetQuaCongTac} entity. This class is used
 * in {@link com.vnpt.cdt.web.rest.CdtKetQuaCongTacResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cdt-ket-qua-cong-tacs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CdtKetQuaCongTacCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maKetQua;

    private StringFilter tenKetQua;

    private LongFilter thuTuSapXep;

    private LongFilter csytid;

    private LongFilter cdtChiDaoTuyenId;

    public CdtKetQuaCongTacCriteria(){
    }

    public CdtKetQuaCongTacCriteria(CdtKetQuaCongTacCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maKetQua = other.maKetQua == null ? null : other.maKetQua.copy();
        this.tenKetQua = other.tenKetQua == null ? null : other.tenKetQua.copy();
        this.thuTuSapXep = other.thuTuSapXep == null ? null : other.thuTuSapXep.copy();
        this.csytid = other.csytid == null ? null : other.csytid.copy();
        this.cdtChiDaoTuyenId = other.cdtChiDaoTuyenId == null ? null : other.cdtChiDaoTuyenId.copy();
    }

    @Override
    public CdtKetQuaCongTacCriteria copy() {
        return new CdtKetQuaCongTacCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaKetQua() {
        return maKetQua;
    }

    public void setMaKetQua(StringFilter maKetQua) {
        this.maKetQua = maKetQua;
    }

    public StringFilter getTenKetQua() {
        return tenKetQua;
    }

    public void setTenKetQua(StringFilter tenKetQua) {
        this.tenKetQua = tenKetQua;
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
        final CdtKetQuaCongTacCriteria that = (CdtKetQuaCongTacCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maKetQua, that.maKetQua) &&
            Objects.equals(tenKetQua, that.tenKetQua) &&
            Objects.equals(thuTuSapXep, that.thuTuSapXep) &&
            Objects.equals(csytid, that.csytid) &&
            Objects.equals(cdtChiDaoTuyenId, that.cdtChiDaoTuyenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maKetQua,
        tenKetQua,
        thuTuSapXep,
        csytid,
        cdtChiDaoTuyenId
        );
    }

    @Override
    public String toString() {
        return "CdtKetQuaCongTacCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maKetQua != null ? "maKetQua=" + maKetQua + ", " : "") +
                (tenKetQua != null ? "tenKetQua=" + tenKetQua + ", " : "") +
                (thuTuSapXep != null ? "thuTuSapXep=" + thuTuSapXep + ", " : "") +
                (csytid != null ? "csytid=" + csytid + ", " : "") +
                (cdtChiDaoTuyenId != null ? "cdtChiDaoTuyenId=" + cdtChiDaoTuyenId + ", " : "") +
            "}";
    }

}
