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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtDutoanDaotaoct} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtDutoanDaotaoctResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-dutoan-daotaocts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtDutoanDaotaoctCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter soluong;

    private IntegerFilter mucchi;

    private IntegerFilter thanhtien;

    private StringFilter noidung;

    private IntegerFilter trangthaict;

    private IntegerFilter dathanhtoan;

    private IntegerFilter sudung;

    private LongFilter qldtDutoanDaotaoId;

    private LongFilter qldtDmNoidungId;

    public QldtDutoanDaotaoctCriteria() {
    }

    public QldtDutoanDaotaoctCriteria(QldtDutoanDaotaoctCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.soluong = other.soluong == null ? null : other.soluong.copy();
        this.mucchi = other.mucchi == null ? null : other.mucchi.copy();
        this.thanhtien = other.thanhtien == null ? null : other.thanhtien.copy();
        this.noidung = other.noidung == null ? null : other.noidung.copy();
        this.trangthaict = other.trangthaict == null ? null : other.trangthaict.copy();
        this.dathanhtoan = other.dathanhtoan == null ? null : other.dathanhtoan.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.qldtDutoanDaotaoId = other.qldtDutoanDaotaoId == null ? null : other.qldtDutoanDaotaoId.copy();
        this.qldtDmNoidungId = other.qldtDmNoidungId == null ? null : other.qldtDmNoidungId.copy();
    }

    @Override
    public QldtDutoanDaotaoctCriteria copy() {
        return new QldtDutoanDaotaoctCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSoluong() {
        return soluong;
    }

    public void setSoluong(IntegerFilter soluong) {
        this.soluong = soluong;
    }

    public IntegerFilter getMucchi() {
        return mucchi;
    }

    public void setMucchi(IntegerFilter mucchi) {
        this.mucchi = mucchi;
    }

    public IntegerFilter getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(IntegerFilter thanhtien) {
        this.thanhtien = thanhtien;
    }

    public StringFilter getNoidung() {
        return noidung;
    }

    public void setNoidung(StringFilter noidung) {
        this.noidung = noidung;
    }

    public IntegerFilter getTrangthaict() {
        return trangthaict;
    }

    public void setTrangthaict(IntegerFilter trangthaict) {
        this.trangthaict = trangthaict;
    }

    public IntegerFilter getDathanhtoan() {
        return dathanhtoan;
    }

    public void setDathanhtoan(IntegerFilter dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getQldtDutoanDaotaoId() {
        return qldtDutoanDaotaoId;
    }

    public void setQldtDutoanDaotaoId(LongFilter qldtDutoanDaotaoId) {
        this.qldtDutoanDaotaoId = qldtDutoanDaotaoId;
    }

    public LongFilter getQldtDmNoidungId() {
        return qldtDmNoidungId;
    }

    public void setQldtDmNoidungId(LongFilter qldtDmNoidungId) {
        this.qldtDmNoidungId = qldtDmNoidungId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QldtDutoanDaotaoctCriteria that = (QldtDutoanDaotaoctCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(soluong, that.soluong) &&
            Objects.equals(mucchi, that.mucchi) &&
            Objects.equals(thanhtien, that.thanhtien) &&
            Objects.equals(noidung, that.noidung) &&
            Objects.equals(trangthaict, that.trangthaict) &&
            Objects.equals(dathanhtoan, that.dathanhtoan) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(qldtDutoanDaotaoId, that.qldtDutoanDaotaoId) &&
            Objects.equals(qldtDmNoidungId, that.qldtDmNoidungId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        soluong,
        mucchi,
        thanhtien,
        noidung,
        trangthaict,
        dathanhtoan,
        sudung,
        qldtDutoanDaotaoId,
        qldtDmNoidungId
        );
    }

    @Override
    public String toString() {
        return "QldtDutoanDaotaoctCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (soluong != null ? "soluong=" + soluong + ", " : "") +
                (mucchi != null ? "mucchi=" + mucchi + ", " : "") +
                (thanhtien != null ? "thanhtien=" + thanhtien + ", " : "") +
                (noidung != null ? "noidung=" + noidung + ", " : "") +
                (trangthaict != null ? "trangthaict=" + trangthaict + ", " : "") +
                (dathanhtoan != null ? "dathanhtoan=" + dathanhtoan + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (qldtDutoanDaotaoId != null ? "qldtDutoanDaotaoId=" + qldtDutoanDaotaoId + ", " : "") +
                (qldtDmNoidungId != null ? "qldtDmNoidungId=" + qldtDmNoidungId + ", " : "") +
            "}";
    }

}
