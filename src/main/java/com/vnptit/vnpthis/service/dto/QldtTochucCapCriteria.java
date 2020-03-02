package com.vnptit.vnpthis.service.dto;

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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtTochucCap} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtTochucCapResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-tochuc-caps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtTochucCapCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter matochuc;

    private StringFilter tentochuc;

    private StringFilter noidung;

    private IntegerFilter sudung;

    private LongFilter dmChungchiId;

    public QldtTochucCapCriteria() {
    }

    public QldtTochucCapCriteria(QldtTochucCapCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.matochuc = other.matochuc == null ? null : other.matochuc.copy();
        this.tentochuc = other.tentochuc == null ? null : other.tentochuc.copy();
        this.noidung = other.noidung == null ? null : other.noidung.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.dmChungchiId = other.dmChungchiId == null ? null : other.dmChungchiId.copy();
    }

    @Override
    public QldtTochucCapCriteria copy() {
        return new QldtTochucCapCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMatochuc() {
        return matochuc;
    }

    public void setMatochuc(StringFilter matochuc) {
        this.matochuc = matochuc;
    }

    public StringFilter getTentochuc() {
        return tentochuc;
    }

    public void setTentochuc(StringFilter tentochuc) {
        this.tentochuc = tentochuc;
    }

    public StringFilter getNoidung() {
        return noidung;
    }

    public void setNoidung(StringFilter noidung) {
        this.noidung = noidung;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getDmChungchiId() {
        return dmChungchiId;
    }

    public void setDmChungchiId(LongFilter dmChungchiId) {
        this.dmChungchiId = dmChungchiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QldtTochucCapCriteria that = (QldtTochucCapCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(matochuc, that.matochuc) &&
            Objects.equals(tentochuc, that.tentochuc) &&
            Objects.equals(noidung, that.noidung) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(dmChungchiId, that.dmChungchiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        matochuc,
        tentochuc,
        noidung,
        sudung,
        dmChungchiId
        );
    }

    @Override
    public String toString() {
        return "QldtTochucCapCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (matochuc != null ? "matochuc=" + matochuc + ", " : "") +
                (tentochuc != null ? "tentochuc=" + tentochuc + ", " : "") +
                (noidung != null ? "noidung=" + noidung + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (dmChungchiId != null ? "dmChungchiId=" + dmChungchiId + ", " : "") +
            "}";
    }

}
