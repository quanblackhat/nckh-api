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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtChungChi} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtChungChiResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-chung-chis?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtChungChiCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter han;

    private LocalDateFilter ngaycap;

    private LocalDateFilter ngayhethan;

    private StringFilter urlchungchi;

    private IntegerFilter sudung;

    private LongFilter qldtDmChungchiId;

    public QldtChungChiCriteria() {
    }

    public QldtChungChiCriteria(QldtChungChiCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.han = other.han == null ? null : other.han.copy();
        this.ngaycap = other.ngaycap == null ? null : other.ngaycap.copy();
        this.ngayhethan = other.ngayhethan == null ? null : other.ngayhethan.copy();
        this.urlchungchi = other.urlchungchi == null ? null : other.urlchungchi.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.qldtDmChungchiId = other.qldtDmChungchiId == null ? null : other.qldtDmChungchiId.copy();
    }

    @Override
    public QldtChungChiCriteria copy() {
        return new QldtChungChiCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getHan() {
        return han;
    }

    public void setHan(IntegerFilter han) {
        this.han = han;
    }

    public LocalDateFilter getNgaycap() {
        return ngaycap;
    }

    public void setNgaycap(LocalDateFilter ngaycap) {
        this.ngaycap = ngaycap;
    }

    public LocalDateFilter getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(LocalDateFilter ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public StringFilter getUrlchungchi() {
        return urlchungchi;
    }

    public void setUrlchungchi(StringFilter urlchungchi) {
        this.urlchungchi = urlchungchi;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getQldtDmChungchiId() {
        return qldtDmChungchiId;
    }

    public void setQldtDmChungchiId(LongFilter qldtDmChungchiId) {
        this.qldtDmChungchiId = qldtDmChungchiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QldtChungChiCriteria that = (QldtChungChiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(han, that.han) &&
            Objects.equals(ngaycap, that.ngaycap) &&
            Objects.equals(ngayhethan, that.ngayhethan) &&
            Objects.equals(urlchungchi, that.urlchungchi) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(qldtDmChungchiId, that.qldtDmChungchiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        han,
        ngaycap,
        ngayhethan,
        urlchungchi,
        sudung,
        qldtDmChungchiId
        );
    }

    @Override
    public String toString() {
        return "QldtChungChiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (han != null ? "han=" + han + ", " : "") +
                (ngaycap != null ? "ngaycap=" + ngaycap + ", " : "") +
                (ngayhethan != null ? "ngayhethan=" + ngayhethan + ", " : "") +
                (urlchungchi != null ? "urlchungchi=" + urlchungchi + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (qldtDmChungchiId != null ? "qldtDmChungchiId=" + qldtDmChungchiId + ", " : "") +
            "}";
    }

}
