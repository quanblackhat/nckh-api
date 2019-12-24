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
 * Criteria class for the {@link com.vnpt.cdt.domain.CdtVatTuHoTro} entity. This class is used
 * in {@link com.vnpt.cdt.web.rest.CdtVatTuHoTroResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cdt-vat-tu-ho-tros?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CdtVatTuHoTroCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maVatTu;

    private StringFilter tenVatTu;

    private LongFilter thuTuSapXep;

    private LongFilter csytid;

    private LongFilter vattuId;

    public CdtVatTuHoTroCriteria(){
    }

    public CdtVatTuHoTroCriteria(CdtVatTuHoTroCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maVatTu = other.maVatTu == null ? null : other.maVatTu.copy();
        this.tenVatTu = other.tenVatTu == null ? null : other.tenVatTu.copy();
        this.thuTuSapXep = other.thuTuSapXep == null ? null : other.thuTuSapXep.copy();
        this.csytid = other.csytid == null ? null : other.csytid.copy();
        this.vattuId = other.vattuId == null ? null : other.vattuId.copy();
    }

    @Override
    public CdtVatTuHoTroCriteria copy() {
        return new CdtVatTuHoTroCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(StringFilter maVatTu) {
        this.maVatTu = maVatTu;
    }

    public StringFilter getTenVatTu() {
        return tenVatTu;
    }

    public void setTenVatTu(StringFilter tenVatTu) {
        this.tenVatTu = tenVatTu;
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

    public LongFilter getVattuId() {
        return vattuId;
    }

    public void setVattuId(LongFilter vattuId) {
        this.vattuId = vattuId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CdtVatTuHoTroCriteria that = (CdtVatTuHoTroCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maVatTu, that.maVatTu) &&
            Objects.equals(tenVatTu, that.tenVatTu) &&
            Objects.equals(thuTuSapXep, that.thuTuSapXep) &&
            Objects.equals(csytid, that.csytid) &&
            Objects.equals(vattuId, that.vattuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maVatTu,
        tenVatTu,
        thuTuSapXep,
        csytid,
        vattuId
        );
    }

    @Override
    public String toString() {
        return "CdtVatTuHoTroCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maVatTu != null ? "maVatTu=" + maVatTu + ", " : "") +
                (tenVatTu != null ? "tenVatTu=" + tenVatTu + ", " : "") +
                (thuTuSapXep != null ? "thuTuSapXep=" + thuTuSapXep + ", " : "") +
                (csytid != null ? "csytid=" + csytid + ", " : "") +
                (vattuId != null ? "vattuId=" + vattuId + ", " : "") +
            "}";
    }

}
