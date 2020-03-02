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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtQlHocvien} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtQlHocvienResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-ql-hocviens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtQlHocvienCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter diemdanh;

    private IntegerFilter diem;

    private StringFilter danhgia;

    private IntegerFilter sudung;

    private IntegerFilter trangthaithanhtoan;

    private LocalDateFilter ngaythanhtoan;

    private StringFilter mathanhtoan;

    private LongFilter qldtDaotaoId;

    public QldtQlHocvienCriteria() {
    }

    public QldtQlHocvienCriteria(QldtQlHocvienCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.diemdanh = other.diemdanh == null ? null : other.diemdanh.copy();
        this.diem = other.diem == null ? null : other.diem.copy();
        this.danhgia = other.danhgia == null ? null : other.danhgia.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.trangthaithanhtoan = other.trangthaithanhtoan == null ? null : other.trangthaithanhtoan.copy();
        this.ngaythanhtoan = other.ngaythanhtoan == null ? null : other.ngaythanhtoan.copy();
        this.mathanhtoan = other.mathanhtoan == null ? null : other.mathanhtoan.copy();
        this.qldtDaotaoId = other.qldtDaotaoId == null ? null : other.qldtDaotaoId.copy();
    }

    @Override
    public QldtQlHocvienCriteria copy() {
        return new QldtQlHocvienCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getDiemdanh() {
        return diemdanh;
    }

    public void setDiemdanh(IntegerFilter diemdanh) {
        this.diemdanh = diemdanh;
    }

    public IntegerFilter getDiem() {
        return diem;
    }

    public void setDiem(IntegerFilter diem) {
        this.diem = diem;
    }

    public StringFilter getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(StringFilter danhgia) {
        this.danhgia = danhgia;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public IntegerFilter getTrangthaithanhtoan() {
        return trangthaithanhtoan;
    }

    public void setTrangthaithanhtoan(IntegerFilter trangthaithanhtoan) {
        this.trangthaithanhtoan = trangthaithanhtoan;
    }

    public LocalDateFilter getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(LocalDateFilter ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public StringFilter getMathanhtoan() {
        return mathanhtoan;
    }

    public void setMathanhtoan(StringFilter mathanhtoan) {
        this.mathanhtoan = mathanhtoan;
    }

    public LongFilter getQldtDaotaoId() {
        return qldtDaotaoId;
    }

    public void setQldtDaotaoId(LongFilter qldtDaotaoId) {
        this.qldtDaotaoId = qldtDaotaoId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QldtQlHocvienCriteria that = (QldtQlHocvienCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(diemdanh, that.diemdanh) &&
            Objects.equals(diem, that.diem) &&
            Objects.equals(danhgia, that.danhgia) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(trangthaithanhtoan, that.trangthaithanhtoan) &&
            Objects.equals(ngaythanhtoan, that.ngaythanhtoan) &&
            Objects.equals(mathanhtoan, that.mathanhtoan) &&
            Objects.equals(qldtDaotaoId, that.qldtDaotaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        diemdanh,
        diem,
        danhgia,
        sudung,
        trangthaithanhtoan,
        ngaythanhtoan,
        mathanhtoan,
        qldtDaotaoId
        );
    }

    @Override
    public String toString() {
        return "QldtQlHocvienCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (diemdanh != null ? "diemdanh=" + diemdanh + ", " : "") +
                (diem != null ? "diem=" + diem + ", " : "") +
                (danhgia != null ? "danhgia=" + danhgia + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (trangthaithanhtoan != null ? "trangthaithanhtoan=" + trangthaithanhtoan + ", " : "") +
                (ngaythanhtoan != null ? "ngaythanhtoan=" + ngaythanhtoan + ", " : "") +
                (mathanhtoan != null ? "mathanhtoan=" + mathanhtoan + ", " : "") +
                (qldtDaotaoId != null ? "qldtDaotaoId=" + qldtDaotaoId + ", " : "") +
            "}";
    }

}
