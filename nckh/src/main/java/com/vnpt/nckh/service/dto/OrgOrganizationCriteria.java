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

/**
 * Criteria class for the {@link com.vnpt.nckh.domain.OrgOrganization} entity. This class is used
 * in {@link com.vnpt.nckh.web.rest.OrgOrganizationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /org-organizations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrgOrganizationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter parentId;

    private LongFilter orgType;

    private StringFilter orgCode;

    private StringFilter orgName;

    private StringFilter orgLevel;

    private StringFilter dbName;

    private StringFilter dbSchema;

    private LongFilter provinceId;

    private LongFilter status;

    private StringFilter orgAddress;

    private StringFilter hospitalCode;

    private LongFilter orgOfficerId;

    public OrgOrganizationCriteria(){
    }

    public OrgOrganizationCriteria(OrgOrganizationCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.parentId = other.parentId == null ? null : other.parentId.copy();
        this.orgType = other.orgType == null ? null : other.orgType.copy();
        this.orgCode = other.orgCode == null ? null : other.orgCode.copy();
        this.orgName = other.orgName == null ? null : other.orgName.copy();
        this.orgLevel = other.orgLevel == null ? null : other.orgLevel.copy();
        this.dbName = other.dbName == null ? null : other.dbName.copy();
        this.dbSchema = other.dbSchema == null ? null : other.dbSchema.copy();
        this.provinceId = other.provinceId == null ? null : other.provinceId.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.orgAddress = other.orgAddress == null ? null : other.orgAddress.copy();
        this.hospitalCode = other.hospitalCode == null ? null : other.hospitalCode.copy();
        this.orgOfficerId = other.orgOfficerId == null ? null : other.orgOfficerId.copy();
    }

    @Override
    public OrgOrganizationCriteria copy() {
        return new OrgOrganizationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getParentId() {
        return parentId;
    }

    public void setParentId(LongFilter parentId) {
        this.parentId = parentId;
    }

    public LongFilter getOrgType() {
        return orgType;
    }

    public void setOrgType(LongFilter orgType) {
        this.orgType = orgType;
    }

    public StringFilter getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(StringFilter orgCode) {
        this.orgCode = orgCode;
    }

    public StringFilter getOrgName() {
        return orgName;
    }

    public void setOrgName(StringFilter orgName) {
        this.orgName = orgName;
    }

    public StringFilter getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(StringFilter orgLevel) {
        this.orgLevel = orgLevel;
    }

    public StringFilter getDbName() {
        return dbName;
    }

    public void setDbName(StringFilter dbName) {
        this.dbName = dbName;
    }

    public StringFilter getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(StringFilter dbSchema) {
        this.dbSchema = dbSchema;
    }

    public LongFilter getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(LongFilter provinceId) {
        this.provinceId = provinceId;
    }

    public LongFilter getStatus() {
        return status;
    }

    public void setStatus(LongFilter status) {
        this.status = status;
    }

    public StringFilter getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(StringFilter orgAddress) {
        this.orgAddress = orgAddress;
    }

    public StringFilter getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(StringFilter hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public LongFilter getOrgOfficerId() {
        return orgOfficerId;
    }

    public void setOrgOfficerId(LongFilter orgOfficerId) {
        this.orgOfficerId = orgOfficerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrgOrganizationCriteria that = (OrgOrganizationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(parentId, that.parentId) &&
            Objects.equals(orgType, that.orgType) &&
            Objects.equals(orgCode, that.orgCode) &&
            Objects.equals(orgName, that.orgName) &&
            Objects.equals(orgLevel, that.orgLevel) &&
            Objects.equals(dbName, that.dbName) &&
            Objects.equals(dbSchema, that.dbSchema) &&
            Objects.equals(provinceId, that.provinceId) &&
            Objects.equals(status, that.status) &&
            Objects.equals(orgAddress, that.orgAddress) &&
            Objects.equals(hospitalCode, that.hospitalCode) &&
            Objects.equals(orgOfficerId, that.orgOfficerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        parentId,
        orgType,
        orgCode,
        orgName,
        orgLevel,
        dbName,
        dbSchema,
        provinceId,
        status,
        orgAddress,
        hospitalCode,
        orgOfficerId
        );
    }

    @Override
    public String toString() {
        return "OrgOrganizationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (parentId != null ? "parentId=" + parentId + ", " : "") +
                (orgType != null ? "orgType=" + orgType + ", " : "") +
                (orgCode != null ? "orgCode=" + orgCode + ", " : "") +
                (orgName != null ? "orgName=" + orgName + ", " : "") +
                (orgLevel != null ? "orgLevel=" + orgLevel + ", " : "") +
                (dbName != null ? "dbName=" + dbName + ", " : "") +
                (dbSchema != null ? "dbSchema=" + dbSchema + ", " : "") +
                (provinceId != null ? "provinceId=" + provinceId + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (orgAddress != null ? "orgAddress=" + orgAddress + ", " : "") +
                (hospitalCode != null ? "hospitalCode=" + hospitalCode + ", " : "") +
                (orgOfficerId != null ? "orgOfficerId=" + orgOfficerId + ", " : "") +
            "}";
    }

}
