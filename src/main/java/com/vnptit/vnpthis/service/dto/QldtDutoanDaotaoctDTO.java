package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtDutoanDaotaoct} entity.
 */
public class QldtDutoanDaotaoctDTO implements Serializable {

    private Long id;

    private Integer soluong;

    private Integer mucchi;

    private Integer thanhtien;

    private String noidung;

    private Integer trangthaict;

    private Integer dathanhtoan;

    private Integer sudung;


    private Long qldtDutoanDaotaoId;

    private Long qldtDmNoidungId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Integer getMucchi() {
        return mucchi;
    }

    public void setMucchi(Integer mucchi) {
        this.mucchi = mucchi;
    }

    public Integer getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Integer thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Integer getTrangthaict() {
        return trangthaict;
    }

    public void setTrangthaict(Integer trangthaict) {
        this.trangthaict = trangthaict;
    }

    public Integer getDathanhtoan() {
        return dathanhtoan;
    }

    public void setDathanhtoan(Integer dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Long getQldtDutoanDaotaoId() {
        return qldtDutoanDaotaoId;
    }

    public void setQldtDutoanDaotaoId(Long qldtDutoanDaotaoId) {
        this.qldtDutoanDaotaoId = qldtDutoanDaotaoId;
    }

    public Long getQldtDmNoidungId() {
        return qldtDmNoidungId;
    }

    public void setQldtDmNoidungId(Long qldtDmNoidungId) {
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

        QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO = (QldtDutoanDaotaoctDTO) o;
        if (qldtDutoanDaotaoctDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtDutoanDaotaoctDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtDutoanDaotaoctDTO{" +
            "id=" + getId() +
            ", soluong=" + getSoluong() +
            ", mucchi=" + getMucchi() +
            ", thanhtien=" + getThanhtien() +
            ", noidung='" + getNoidung() + "'" +
            ", trangthaict=" + getTrangthaict() +
            ", dathanhtoan=" + getDathanhtoan() +
            ", sudung=" + getSudung() +
            ", qldtDutoanDaotaoId=" + getQldtDutoanDaotaoId() +
            ", qldtDmNoidungId=" + getQldtDmNoidungId() +
            "}";
    }
}
