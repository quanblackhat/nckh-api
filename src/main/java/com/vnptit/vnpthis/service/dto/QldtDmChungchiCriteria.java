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
 * Criteria class for the {@link com.vnptit.vnpthis.domain.QldtDmChungchi} entity. This class is used
 * in {@link com.vnptit.vnpthis.web.rest.QldtDmChungchiResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /qldt-dm-chungchis?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QldtDmChungchiCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter machungchi;

    private StringFilter tenchungchi;

    private StringFilter mota;

    private IntegerFilter trangthai;

    private IntegerFilter sudung;

    private LongFilter chungChiId;

    private LongFilter qldtTochucCapId;

    public QldtDmChungchiCriteria() {
    }

    public QldtDmChungchiCriteria(QldtDmChungchiCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.machungchi = other.machungchi == null ? null : other.machungchi.copy();
        this.tenchungchi = other.tenchungchi == null ? null : other.tenchungchi.copy();
        this.mota = other.mota == null ? null : other.mota.copy();
        this.trangthai = other.trangthai == null ? null : other.trangthai.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.chungChiId = other.chungChiId == null ? null : other.chungChiId.copy();
        this.qldtTochucCapId = other.qldtTochucCapId == null ? null : other.qldtTochucCapId.copy();
    }

    @Override
    public QldtDmChungchiCriteria copy() {
        return new QldtDmChungchiCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMachungchi() {
        return machungchi;
    }

    public void setMachungchi(StringFilter machungchi) {
        this.machungchi = machungchi;
    }

    public StringFilter getTenchungchi() {
        return tenchungchi;
    }

    public void setTenchungchi(StringFilter tenchungchi) {
        this.tenchungchi = tenchungchi;
    }

    public StringFilter getMota() {
        return mota;
    }

    public void setMota(StringFilter mota) {
        this.mota = mota;
    }

    public IntegerFilter getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(IntegerFilter trangthai) {
        this.trangthai = trangthai;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getChungChiId() {
        return chungChiId;
    }

    public void setChungChiId(LongFilter chungChiId) {
        this.chungChiId = chungChiId;
    }

    public LongFilter getQldtTochucCapId() {
        return qldtTochucCapId;
    }

    public void setQldtTochucCapId(LongFilter qldtTochucCapId) {
        this.qldtTochucCapId = qldtTochucCapId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QldtDmChungchiCriteria that = (QldtDmChungchiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(machungchi, that.machungchi) &&
            Objects.equals(tenchungchi, that.tenchungchi) &&
            Objects.equals(mota, that.mota) &&
            Objects.equals(trangthai, that.trangthai) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(chungChiId, that.chungChiId) &&
            Objects.equals(qldtTochucCapId, that.qldtTochucCapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        machungchi,
        tenchungchi,
        mota,
        trangthai,
        sudung,
        chungChiId,
        qldtTochucCapId
        );
    }

    @Override
    public String toString() {
        return "QldtDmChungchiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (machungchi != null ? "machungchi=" + machungchi + ", " : "") +
                (tenchungchi != null ? "tenchungchi=" + tenchungchi + ", " : "") +
                (mota != null ? "mota=" + mota + ", " : "") +
                (trangthai != null ? "trangthai=" + trangthai + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (chungChiId != null ? "chungChiId=" + chungChiId + ", " : "") +
                (qldtTochucCapId != null ? "qldtTochucCapId=" + qldtTochucCapId + ", " : "") +
            "}";
    }

}
