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
 * Criteria class for the {@link com.vnpt.nckh.domain.OrgOfficer} entity. This class is used
 * in {@link com.vnpt.nckh.web.rest.OrgOfficerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /org-officers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrgOfficerCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter officerType;

    private StringFilter officerCode;

    private StringFilter officerName;

    private StringFilter email;

    private StringFilter note;

    private StringFilter trinhDo;

    private StringFilter chucDanh;

    private StringFilter maBacSi;

    private StringFilter hocHam;

    private StringFilter hocVi;

    private StringFilter phone;

    private StringFilter opt;

    private StringFilter diaChi;

    private InstantFilter ngaySinh;

    private StringFilter gioiTinh;

    private LongFilter deptId;

    private LongFilter subDeptId;

    private LongFilter nckhNhanSuId;

    private LongFilter orgOrganizationId;

    public OrgOfficerCriteria(){
    }

    public OrgOfficerCriteria(OrgOfficerCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.officerType = other.officerType == null ? null : other.officerType.copy();
        this.officerCode = other.officerCode == null ? null : other.officerCode.copy();
        this.officerName = other.officerName == null ? null : other.officerName.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.note = other.note == null ? null : other.note.copy();
        this.trinhDo = other.trinhDo == null ? null : other.trinhDo.copy();
        this.chucDanh = other.chucDanh == null ? null : other.chucDanh.copy();
        this.maBacSi = other.maBacSi == null ? null : other.maBacSi.copy();
        this.hocHam = other.hocHam == null ? null : other.hocHam.copy();
        this.hocVi = other.hocVi == null ? null : other.hocVi.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.opt = other.opt == null ? null : other.opt.copy();
        this.diaChi = other.diaChi == null ? null : other.diaChi.copy();
        this.ngaySinh = other.ngaySinh == null ? null : other.ngaySinh.copy();
        this.gioiTinh = other.gioiTinh == null ? null : other.gioiTinh.copy();
        this.deptId = other.deptId == null ? null : other.deptId.copy();
        this.subDeptId = other.subDeptId == null ? null : other.subDeptId.copy();
        this.nckhNhanSuId = other.nckhNhanSuId == null ? null : other.nckhNhanSuId.copy();
        this.orgOrganizationId = other.orgOrganizationId == null ? null : other.orgOrganizationId.copy();
    }

    @Override
    public OrgOfficerCriteria copy() {
        return new OrgOfficerCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getOfficerType() {
        return officerType;
    }

    public void setOfficerType(LongFilter officerType) {
        this.officerType = officerType;
    }

    public StringFilter getOfficerCode() {
        return officerCode;
    }

    public void setOfficerCode(StringFilter officerCode) {
        this.officerCode = officerCode;
    }

    public StringFilter getOfficerName() {
        return officerName;
    }

    public void setOfficerName(StringFilter officerName) {
        this.officerName = officerName;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getNote() {
        return note;
    }

    public void setNote(StringFilter note) {
        this.note = note;
    }

    public StringFilter getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(StringFilter trinhDo) {
        this.trinhDo = trinhDo;
    }

    public StringFilter getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(StringFilter chucDanh) {
        this.chucDanh = chucDanh;
    }

    public StringFilter getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(StringFilter maBacSi) {
        this.maBacSi = maBacSi;
    }

    public StringFilter getHocHam() {
        return hocHam;
    }

    public void setHocHam(StringFilter hocHam) {
        this.hocHam = hocHam;
    }

    public StringFilter getHocVi() {
        return hocVi;
    }

    public void setHocVi(StringFilter hocVi) {
        this.hocVi = hocVi;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public StringFilter getOpt() {
        return opt;
    }

    public void setOpt(StringFilter opt) {
        this.opt = opt;
    }

    public StringFilter getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(StringFilter diaChi) {
        this.diaChi = diaChi;
    }

    public InstantFilter getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(InstantFilter ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public StringFilter getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(StringFilter gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LongFilter getDeptId() {
        return deptId;
    }

    public void setDeptId(LongFilter deptId) {
        this.deptId = deptId;
    }

    public LongFilter getSubDeptId() {
        return subDeptId;
    }

    public void setSubDeptId(LongFilter subDeptId) {
        this.subDeptId = subDeptId;
    }

    public LongFilter getNckhNhanSuId() {
        return nckhNhanSuId;
    }

    public void setNckhNhanSuId(LongFilter nckhNhanSuId) {
        this.nckhNhanSuId = nckhNhanSuId;
    }

    public LongFilter getOrgOrganizationId() {
        return orgOrganizationId;
    }

    public void setOrgOrganizationId(LongFilter orgOrganizationId) {
        this.orgOrganizationId = orgOrganizationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrgOfficerCriteria that = (OrgOfficerCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(officerType, that.officerType) &&
            Objects.equals(officerCode, that.officerCode) &&
            Objects.equals(officerName, that.officerName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(note, that.note) &&
            Objects.equals(trinhDo, that.trinhDo) &&
            Objects.equals(chucDanh, that.chucDanh) &&
            Objects.equals(maBacSi, that.maBacSi) &&
            Objects.equals(hocHam, that.hocHam) &&
            Objects.equals(hocVi, that.hocVi) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(opt, that.opt) &&
            Objects.equals(diaChi, that.diaChi) &&
            Objects.equals(ngaySinh, that.ngaySinh) &&
            Objects.equals(gioiTinh, that.gioiTinh) &&
            Objects.equals(deptId, that.deptId) &&
            Objects.equals(subDeptId, that.subDeptId) &&
            Objects.equals(nckhNhanSuId, that.nckhNhanSuId) &&
            Objects.equals(orgOrganizationId, that.orgOrganizationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        officerType,
        officerCode,
        officerName,
        email,
        note,
        trinhDo,
        chucDanh,
        maBacSi,
        hocHam,
        hocVi,
        phone,
        opt,
        diaChi,
        ngaySinh,
        gioiTinh,
        deptId,
        subDeptId,
        nckhNhanSuId,
        orgOrganizationId
        );
    }

    @Override
    public String toString() {
        return "OrgOfficerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (officerType != null ? "officerType=" + officerType + ", " : "") +
                (officerCode != null ? "officerCode=" + officerCode + ", " : "") +
                (officerName != null ? "officerName=" + officerName + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (note != null ? "note=" + note + ", " : "") +
                (trinhDo != null ? "trinhDo=" + trinhDo + ", " : "") +
                (chucDanh != null ? "chucDanh=" + chucDanh + ", " : "") +
                (maBacSi != null ? "maBacSi=" + maBacSi + ", " : "") +
                (hocHam != null ? "hocHam=" + hocHam + ", " : "") +
                (hocVi != null ? "hocVi=" + hocVi + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (opt != null ? "opt=" + opt + ", " : "") +
                (diaChi != null ? "diaChi=" + diaChi + ", " : "") +
                (ngaySinh != null ? "ngaySinh=" + ngaySinh + ", " : "") +
                (gioiTinh != null ? "gioiTinh=" + gioiTinh + ", " : "") +
                (deptId != null ? "deptId=" + deptId + ", " : "") +
                (subDeptId != null ? "subDeptId=" + subDeptId + ", " : "") +
                (nckhNhanSuId != null ? "nckhNhanSuId=" + nckhNhanSuId + ", " : "") +
                (orgOrganizationId != null ? "orgOrganizationId=" + orgOrganizationId + ", " : "") +
            "}";
    }

}
