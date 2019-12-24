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
 * Criteria class for the {@link com.vnpt.cdt.domain.CdtLyDoCongTac} entity. This class is used
 * in {@link com.vnpt.cdt.web.rest.CdtLyDoCongTacResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cdt-ly-do-cong-tacs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CdtLyDoCongTacCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maLyDo;

    private StringFilter tenLyDo;

    private LongFilter thuTuSapXep;

    private LongFilter csytid;

    private LongFilter cdtChiDaoTuyenId;

    public CdtLyDoCongTacCriteria(){
    }

    public CdtLyDoCongTacCriteria(CdtLyDoCongTacCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maLyDo = other.maLyDo == null ? null : other.maLyDo.copy();
        this.tenLyDo = other.tenLyDo == null ? null : other.tenLyDo.copy();
        this.thuTuSapXep = other.thuTuSapXep == null ? null : other.thuTuSapXep.copy();
        this.csytid = other.csytid == null ? null : other.csytid.copy();
        this.cdtChiDaoTuyenId = other.cdtChiDaoTuyenId == null ? null : other.cdtChiDaoTuyenId.copy();
    }

    @Override
    public CdtLyDoCongTacCriteria copy() {
        return new CdtLyDoCongTacCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaLyDo() {
        return maLyDo;
    }

    public void setMaLyDo(StringFilter maLyDo) {
        this.maLyDo = maLyDo;
    }

    public StringFilter getTenLyDo() {
        return tenLyDo;
    }

    public void setTenLyDo(StringFilter tenLyDo) {
        this.tenLyDo = tenLyDo;
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
        final CdtLyDoCongTacCriteria that = (CdtLyDoCongTacCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maLyDo, that.maLyDo) &&
            Objects.equals(tenLyDo, that.tenLyDo) &&
            Objects.equals(thuTuSapXep, that.thuTuSapXep) &&
            Objects.equals(csytid, that.csytid) &&
            Objects.equals(cdtChiDaoTuyenId, that.cdtChiDaoTuyenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maLyDo,
        tenLyDo,
        thuTuSapXep,
        csytid,
        cdtChiDaoTuyenId
        );
    }

    @Override
    public String toString() {
        return "CdtLyDoCongTacCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maLyDo != null ? "maLyDo=" + maLyDo + ", " : "") +
                (tenLyDo != null ? "tenLyDo=" + tenLyDo + ", " : "") +
                (thuTuSapXep != null ? "thuTuSapXep=" + thuTuSapXep + ", " : "") +
                (csytid != null ? "csytid=" + csytid + ", " : "") +
                (cdtChiDaoTuyenId != null ? "cdtChiDaoTuyenId=" + cdtChiDaoTuyenId + ", " : "") +
            "}";
    }

}
