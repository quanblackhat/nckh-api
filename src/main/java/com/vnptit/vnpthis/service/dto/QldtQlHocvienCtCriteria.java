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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtQlHocvienCt} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtQlHocvienCtResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-ql-hocvien-cts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtQlHocvienCtCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter diemdanh;

    private IntegerFilter diemthi;

    private StringFilter danhgia;

    private IntegerFilter sudung;

    private LongFilter qldtDaotaoCtId;

    public QldtQlHocvienCtCriteria() {
    }

    public QldtQlHocvienCtCriteria(QldtQlHocvienCtCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.diemdanh = other.diemdanh == null ? null : other.diemdanh.copy();
        this.diemthi = other.diemthi == null ? null : other.diemthi.copy();
        this.danhgia = other.danhgia == null ? null : other.danhgia.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.qldtDaotaoCtId = other.qldtDaotaoCtId == null ? null : other.qldtDaotaoCtId.copy();
    }

    @Override
    public QldtQlHocvienCtCriteria copy() {
        return new QldtQlHocvienCtCriteria(this);
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

    public IntegerFilter getDiemthi() {
        return diemthi;
    }

    public void setDiemthi(IntegerFilter diemthi) {
        this.diemthi = diemthi;
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

    public LongFilter getQldtDaotaoCtId() {
        return qldtDaotaoCtId;
    }

    public void setQldtDaotaoCtId(LongFilter qldtDaotaoCtId) {
        this.qldtDaotaoCtId = qldtDaotaoCtId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QldtQlHocvienCtCriteria that = (QldtQlHocvienCtCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(diemdanh, that.diemdanh) &&
            Objects.equals(diemthi, that.diemthi) &&
            Objects.equals(danhgia, that.danhgia) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(qldtDaotaoCtId, that.qldtDaotaoCtId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        diemdanh,
        diemthi,
        danhgia,
        sudung,
        qldtDaotaoCtId
        );
    }

    @Override
    public String toString() {
        return "QldtQlHocvienCtCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (diemdanh != null ? "diemdanh=" + diemdanh + ", " : "") +
                (diemthi != null ? "diemthi=" + diemthi + ", " : "") +
                (danhgia != null ? "danhgia=" + danhgia + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (qldtDaotaoCtId != null ? "qldtDaotaoCtId=" + qldtDaotaoCtId + ", " : "") +
            "}";
    }

}
