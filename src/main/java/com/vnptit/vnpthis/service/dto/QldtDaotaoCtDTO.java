package com.vnptit.vnpthis.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtDaotaoCt} entity.
 */
public class QldtDaotaoCtDTO implements Serializable {

    private Long id;

    private String madaotaochitiet;

    private String tendaotaochitiet;

    private LocalDate ngayBd;

    private LocalDate ngayKt;

    private Integer thoigiandaotaoct;

    private String noidungdaotaoct;

    private Integer trangthaidaotaoct;

    private Integer sudung;


    private Long qldtDaotaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMadaotaochitiet() {
        return madaotaochitiet;
    }

    public void setMadaotaochitiet(String madaotaochitiet) {
        this.madaotaochitiet = madaotaochitiet;
    }

    public String getTendaotaochitiet() {
        return tendaotaochitiet;
    }

    public void setTendaotaochitiet(String tendaotaochitiet) {
        this.tendaotaochitiet = tendaotaochitiet;
    }

    public LocalDate getNgayBd() {
        return ngayBd;
    }

    public void setNgayBd(LocalDate ngayBd) {
        this.ngayBd = ngayBd;
    }

    public LocalDate getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(LocalDate ngayKt) {
        this.ngayKt = ngayKt;
    }

    public Integer getThoigiandaotaoct() {
        return thoigiandaotaoct;
    }

    public void setThoigiandaotaoct(Integer thoigiandaotaoct) {
        this.thoigiandaotaoct = thoigiandaotaoct;
    }

    public String getNoidungdaotaoct() {
        return noidungdaotaoct;
    }

    public void setNoidungdaotaoct(String noidungdaotaoct) {
        this.noidungdaotaoct = noidungdaotaoct;
    }

    public Integer getTrangthaidaotaoct() {
        return trangthaidaotaoct;
    }

    public void setTrangthaidaotaoct(Integer trangthaidaotaoct) {
        this.trangthaidaotaoct = trangthaidaotaoct;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Long getQldtDaotaoId() {
        return qldtDaotaoId;
    }

    public void setQldtDaotaoId(Long qldtDaotaoId) {
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

        QldtDaotaoCtDTO qldtDaotaoCtDTO = (QldtDaotaoCtDTO) o;
        if (qldtDaotaoCtDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtDaotaoCtDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtDaotaoCtDTO{" +
            "id=" + getId() +
            ", madaotaochitiet='" + getMadaotaochitiet() + "'" +
            ", tendaotaochitiet='" + getTendaotaochitiet() + "'" +
            ", ngayBd='" + getNgayBd() + "'" +
            ", ngayKt='" + getNgayKt() + "'" +
            ", thoigiandaotaoct=" + getThoigiandaotaoct() +
            ", noidungdaotaoct='" + getNoidungdaotaoct() + "'" +
            ", trangthaidaotaoct=" + getTrangthaidaotaoct() +
            ", sudung=" + getSudung() +
            ", qldtDaotaoId=" + getQldtDaotaoId() +
            "}";
    }
}
