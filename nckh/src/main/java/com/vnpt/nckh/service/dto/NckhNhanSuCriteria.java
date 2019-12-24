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
 * Criteria class for the {@link com.vnpt.nckh.domain.NckhNhanSu} entity. This class is used
 * in {@link com.vnpt.nckh.web.rest.NckhNhanSuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nckh-nhan-sus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NckhNhanSuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter orgOfficerId;

    private LongFilter nckhDetaiId;

    public NckhNhanSuCriteria(){
    }

    public NckhNhanSuCriteria(NckhNhanSuCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.orgOfficerId = other.orgOfficerId == null ? null : other.orgOfficerId.copy();
        this.nckhDetaiId = other.nckhDetaiId == null ? null : other.nckhDetaiId.copy();
    }

    @Override
    public NckhNhanSuCriteria copy() {
        return new NckhNhanSuCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getOrgOfficerId() {
        return orgOfficerId;
    }

    public void setOrgOfficerId(LongFilter orgOfficerId) {
        this.orgOfficerId = orgOfficerId;
    }

    public LongFilter getNckhDetaiId() {
        return nckhDetaiId;
    }

    public void setNckhDetaiId(LongFilter nckhDetaiId) {
        this.nckhDetaiId = nckhDetaiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NckhNhanSuCriteria that = (NckhNhanSuCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(orgOfficerId, that.orgOfficerId) &&
            Objects.equals(nckhDetaiId, that.nckhDetaiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        orgOfficerId,
        nckhDetaiId
        );
    }

    @Override
    public String toString() {
        return "NckhNhanSuCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (orgOfficerId != null ? "orgOfficerId=" + orgOfficerId + ", " : "") +
                (nckhDetaiId != null ? "nckhDetaiId=" + nckhDetaiId + ", " : "") +
            "}";
    }

}
