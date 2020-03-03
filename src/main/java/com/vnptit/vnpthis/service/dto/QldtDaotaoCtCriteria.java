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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtDaotaoCt} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtDaotaoCtResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-daotao-cts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtDaotaoCtCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter madaotaochitiet;

    private StringFilter tendaotaochitiet;

    private LocalDateFilter ngayBd;

    private LocalDateFilter ngayKt;

    private IntegerFilter thoigiandaotaoct;

    private StringFilter noidungdaotaoct;

    private IntegerFilter trangthaidaotaoct;

    private IntegerFilter sudung;

    private LongFilter hocVienCtId;

    private LongFilter qldtDaotaoId;

    public QldtDaotaoCtCriteria() {
    }

    public QldtDaotaoCtCriteria(QldtDaotaoCtCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.madaotaochitiet = other.madaotaochitiet == null ? null : other.madaotaochitiet.copy();
        this.tendaotaochitiet = other.tendaotaochitiet == null ? null : other.tendaotaochitiet.copy();
        this.ngayBd = other.ngayBd == null ? null : other.ngayBd.copy();
        this.ngayKt = other.ngayKt == null ? null : other.ngayKt.copy();
        this.thoigiandaotaoct = other.thoigiandaotaoct == null ? null : other.thoigiandaotaoct.copy();
        this.noidungdaotaoct = other.noidungdaotaoct == null ? null : other.noidungdaotaoct.copy();
        this.trangthaidaotaoct = other.trangthaidaotaoct == null ? null : other.trangthaidaotaoct.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.hocVienCtId = other.hocVienCtId == null ? null : other.hocVienCtId.copy();
        this.qldtDaotaoId = other.qldtDaotaoId == null ? null : other.qldtDaotaoId.copy();
    }

    @Override
    public QldtDaotaoCtCriteria copy() {
        return new QldtDaotaoCtCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMadaotaochitiet() {
        return madaotaochitiet;
    }

    public void setMadaotaochitiet(StringFilter madaotaochitiet) {
        this.madaotaochitiet = madaotaochitiet;
    }

    public StringFilter getTendaotaochitiet() {
        return tendaotaochitiet;
    }

    public void setTendaotaochitiet(StringFilter tendaotaochitiet) {
        this.tendaotaochitiet = tendaotaochitiet;
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

    public IntegerFilter getThoigiandaotaoct() {
        return thoigiandaotaoct;
    }

    public void setThoigiandaotaoct(IntegerFilter thoigiandaotaoct) {
        this.thoigiandaotaoct = thoigiandaotaoct;
    }

    public StringFilter getNoidungdaotaoct() {
        return noidungdaotaoct;
    }

    public void setNoidungdaotaoct(StringFilter noidungdaotaoct) {
        this.noidungdaotaoct = noidungdaotaoct;
    }

    public IntegerFilter getTrangthaidaotaoct() {
        return trangthaidaotaoct;
    }

    public void setTrangthaidaotaoct(IntegerFilter trangthaidaotaoct) {
        this.trangthaidaotaoct = trangthaidaotaoct;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getHocVienCtId() {
        return hocVienCtId;
    }

    public void setHocVienCtId(LongFilter hocVienCtId) {
        this.hocVienCtId = hocVienCtId;
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
        final QldtDaotaoCtCriteria that = (QldtDaotaoCtCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(madaotaochitiet, that.madaotaochitiet) &&
            Objects.equals(tendaotaochitiet, that.tendaotaochitiet) &&
            Objects.equals(ngayBd, that.ngayBd) &&
            Objects.equals(ngayKt, that.ngayKt) &&
            Objects.equals(thoigiandaotaoct, that.thoigiandaotaoct) &&
            Objects.equals(noidungdaotaoct, that.noidungdaotaoct) &&
            Objects.equals(trangthaidaotaoct, that.trangthaidaotaoct) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(hocVienCtId, that.hocVienCtId) &&
            Objects.equals(qldtDaotaoId, that.qldtDaotaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        madaotaochitiet,
        tendaotaochitiet,
        ngayBd,
        ngayKt,
        thoigiandaotaoct,
        noidungdaotaoct,
        trangthaidaotaoct,
        sudung,
        hocVienCtId,
        qldtDaotaoId
        );
    }

    @Override
    public String toString() {
        return "QldtDaotaoCtCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (madaotaochitiet != null ? "madaotaochitiet=" + madaotaochitiet + ", " : "") +
                (tendaotaochitiet != null ? "tendaotaochitiet=" + tendaotaochitiet + ", " : "") +
                (ngayBd != null ? "ngayBd=" + ngayBd + ", " : "") +
                (ngayKt != null ? "ngayKt=" + ngayKt + ", " : "") +
                (thoigiandaotaoct != null ? "thoigiandaotaoct=" + thoigiandaotaoct + ", " : "") +
                (noidungdaotaoct != null ? "noidungdaotaoct=" + noidungdaotaoct + ", " : "") +
                (trangthaidaotaoct != null ? "trangthaidaotaoct=" + trangthaidaotaoct + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (hocVienCtId != null ? "hocVienCtId=" + hocVienCtId + ", " : "") +
                (qldtDaotaoId != null ? "qldtDaotaoId=" + qldtDaotaoId + ", " : "") +
            "}";
    }

}
