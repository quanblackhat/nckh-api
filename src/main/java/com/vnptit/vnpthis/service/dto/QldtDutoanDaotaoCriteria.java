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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtDutoanDaotao} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtDutoanDaotaoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-dutoan-daotaos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtDutoanDaotaoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter solop;

    private IntegerFilter sohocvien;

    private IntegerFilter dathanhtoan;

    private IntegerFilter madutoan;

    private StringFilter tendutoan;

    private IntegerFilter trangthai;

    private LocalDateFilter ngayBd;

    private LocalDateFilter ngayKt;

    private IntegerFilter sudung;

    private LongFilter duToanDaotaoCtId;

    public QldtDutoanDaotaoCriteria() {
    }

    public QldtDutoanDaotaoCriteria(QldtDutoanDaotaoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.solop = other.solop == null ? null : other.solop.copy();
        this.sohocvien = other.sohocvien == null ? null : other.sohocvien.copy();
        this.dathanhtoan = other.dathanhtoan == null ? null : other.dathanhtoan.copy();
        this.madutoan = other.madutoan == null ? null : other.madutoan.copy();
        this.tendutoan = other.tendutoan == null ? null : other.tendutoan.copy();
        this.trangthai = other.trangthai == null ? null : other.trangthai.copy();
        this.ngayBd = other.ngayBd == null ? null : other.ngayBd.copy();
        this.ngayKt = other.ngayKt == null ? null : other.ngayKt.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.duToanDaotaoCtId = other.duToanDaotaoCtId == null ? null : other.duToanDaotaoCtId.copy();
    }

    @Override
    public QldtDutoanDaotaoCriteria copy() {
        return new QldtDutoanDaotaoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSolop() {
        return solop;
    }

    public void setSolop(IntegerFilter solop) {
        this.solop = solop;
    }

    public IntegerFilter getSohocvien() {
        return sohocvien;
    }

    public void setSohocvien(IntegerFilter sohocvien) {
        this.sohocvien = sohocvien;
    }

    public IntegerFilter getDathanhtoan() {
        return dathanhtoan;
    }

    public void setDathanhtoan(IntegerFilter dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
    }

    public IntegerFilter getMadutoan() {
        return madutoan;
    }

    public void setMadutoan(IntegerFilter madutoan) {
        this.madutoan = madutoan;
    }

    public StringFilter getTendutoan() {
        return tendutoan;
    }

    public void setTendutoan(StringFilter tendutoan) {
        this.tendutoan = tendutoan;
    }

    public IntegerFilter getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(IntegerFilter trangthai) {
        this.trangthai = trangthai;
    }

    public LocalDateFilter getNgayBd() {
        return ngayBd;
    }

    public void setNgayBd(LocalDateFilter ngayBd) {
        this.ngayBd = ngayBd;
    }

    public LocalDateFilter getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(LocalDateFilter ngayKt) {
        this.ngayKt = ngayKt;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getDuToanDaotaoCtId() {
        return duToanDaotaoCtId;
    }

    public void setDuToanDaotaoCtId(LongFilter duToanDaotaoCtId) {
        this.duToanDaotaoCtId = duToanDaotaoCtId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QldtDutoanDaotaoCriteria that = (QldtDutoanDaotaoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(solop, that.solop) &&
            Objects.equals(sohocvien, that.sohocvien) &&
            Objects.equals(dathanhtoan, that.dathanhtoan) &&
            Objects.equals(madutoan, that.madutoan) &&
            Objects.equals(tendutoan, that.tendutoan) &&
            Objects.equals(trangthai, that.trangthai) &&
            Objects.equals(ngayBd, that.ngayBd) &&
            Objects.equals(ngayKt, that.ngayKt) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(duToanDaotaoCtId, that.duToanDaotaoCtId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        solop,
        sohocvien,
        dathanhtoan,
        madutoan,
        tendutoan,
        trangthai,
        ngayBd,
        ngayKt,
        sudung,
        duToanDaotaoCtId
        );
    }

    @Override
    public String toString() {
        return "QldtDutoanDaotaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (solop != null ? "solop=" + solop + ", " : "") +
                (sohocvien != null ? "sohocvien=" + sohocvien + ", " : "") +
                (dathanhtoan != null ? "dathanhtoan=" + dathanhtoan + ", " : "") +
                (madutoan != null ? "madutoan=" + madutoan + ", " : "") +
                (tendutoan != null ? "tendutoan=" + tendutoan + ", " : "") +
                (trangthai != null ? "trangthai=" + trangthai + ", " : "") +
                (ngayBd != null ? "ngayBd=" + ngayBd + ", " : "") +
                (ngayKt != null ? "ngayKt=" + ngayKt + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (duToanDaotaoCtId != null ? "duToanDaotaoCtId=" + duToanDaotaoCtId + ", " : "") +
            "}";
    }

}
