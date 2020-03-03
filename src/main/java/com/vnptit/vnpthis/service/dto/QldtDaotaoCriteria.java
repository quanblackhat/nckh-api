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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtDaotao} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtDaotaoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-daotaos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtDaotaoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter madaotao;

    private StringFilter tendaotao;

    private LocalDateFilter ngayBd;

    private LocalDateFilter ngayKt;

    private StringFilter diachi;

    private StringFilter doituongchitiet;

    private StringFilter noidungdaotao;

    private IntegerFilter thoigiandaotao;

    private IntegerFilter sudung;

    private LongFilter daoTaoCtId;

    private LongFilter hocVienId;

    public QldtDaotaoCriteria() {
    }

    public QldtDaotaoCriteria(QldtDaotaoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.madaotao = other.madaotao == null ? null : other.madaotao.copy();
        this.tendaotao = other.tendaotao == null ? null : other.tendaotao.copy();
        this.ngayBd = other.ngayBd == null ? null : other.ngayBd.copy();
        this.ngayKt = other.ngayKt == null ? null : other.ngayKt.copy();
        this.diachi = other.diachi == null ? null : other.diachi.copy();
        this.doituongchitiet = other.doituongchitiet == null ? null : other.doituongchitiet.copy();
        this.noidungdaotao = other.noidungdaotao == null ? null : other.noidungdaotao.copy();
        this.thoigiandaotao = other.thoigiandaotao == null ? null : other.thoigiandaotao.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.daoTaoCtId = other.daoTaoCtId == null ? null : other.daoTaoCtId.copy();
        this.hocVienId = other.hocVienId == null ? null : other.hocVienId.copy();
    }

    @Override
    public QldtDaotaoCriteria copy() {
        return new QldtDaotaoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMadaotao() {
        return madaotao;
    }

    public void setMadaotao(StringFilter madaotao) {
        this.madaotao = madaotao;
    }

    public StringFilter getTendaotao() {
        return tendaotao;
    }

    public void setTendaotao(StringFilter tendaotao) {
        this.tendaotao = tendaotao;
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

    public StringFilter getDiachi() {
        return diachi;
    }

    public void setDiachi(StringFilter diachi) {
        this.diachi = diachi;
    }

    public StringFilter getDoituongchitiet() {
        return doituongchitiet;
    }

    public void setDoituongchitiet(StringFilter doituongchitiet) {
        this.doituongchitiet = doituongchitiet;
    }

    public StringFilter getNoidungdaotao() {
        return noidungdaotao;
    }

    public void setNoidungdaotao(StringFilter noidungdaotao) {
        this.noidungdaotao = noidungdaotao;
    }

    public IntegerFilter getThoigiandaotao() {
        return thoigiandaotao;
    }

    public void setThoigiandaotao(IntegerFilter thoigiandaotao) {
        this.thoigiandaotao = thoigiandaotao;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getDaoTaoCtId() {
        return daoTaoCtId;
    }

    public void setDaoTaoCtId(LongFilter daoTaoCtId) {
        this.daoTaoCtId = daoTaoCtId;
    }

    public LongFilter getHocVienId() {
        return hocVienId;
    }

    public void setHocVienId(LongFilter hocVienId) {
        this.hocVienId = hocVienId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QldtDaotaoCriteria that = (QldtDaotaoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(madaotao, that.madaotao) &&
            Objects.equals(tendaotao, that.tendaotao) &&
            Objects.equals(ngayBd, that.ngayBd) &&
            Objects.equals(ngayKt, that.ngayKt) &&
            Objects.equals(diachi, that.diachi) &&
            Objects.equals(doituongchitiet, that.doituongchitiet) &&
            Objects.equals(noidungdaotao, that.noidungdaotao) &&
            Objects.equals(thoigiandaotao, that.thoigiandaotao) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(daoTaoCtId, that.daoTaoCtId) &&
            Objects.equals(hocVienId, that.hocVienId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        madaotao,
        tendaotao,
        ngayBd,
        ngayKt,
        diachi,
        doituongchitiet,
        noidungdaotao,
        thoigiandaotao,
        sudung,
        daoTaoCtId,
        hocVienId
        );
    }

    @Override
    public String toString() {
        return "QldtDaotaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (madaotao != null ? "madaotao=" + madaotao + ", " : "") +
                (tendaotao != null ? "tendaotao=" + tendaotao + ", " : "") +
                (ngayBd != null ? "ngayBd=" + ngayBd + ", " : "") +
                (ngayKt != null ? "ngayKt=" + ngayKt + ", " : "") +
                (diachi != null ? "diachi=" + diachi + ", " : "") +
                (doituongchitiet != null ? "doituongchitiet=" + doituongchitiet + ", " : "") +
                (noidungdaotao != null ? "noidungdaotao=" + noidungdaotao + ", " : "") +
                (thoigiandaotao != null ? "thoigiandaotao=" + thoigiandaotao + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (daoTaoCtId != null ? "daoTaoCtId=" + daoTaoCtId + ", " : "") +
                (hocVienId != null ? "hocVienId=" + hocVienId + ", " : "") +
            "}";
    }

}
