package com.vnptit.vnpthis.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtDutoanDaotao} entity.
 */
public class QldtDutoanDaotaoDTO implements Serializable {

    private Long id;

    private Integer solop;

    private Integer sohocvien;

    private Integer dathanhtoan;

    private Integer madutoan;

    private String tendutoan;

    private Integer trangthai;

    private LocalDate ngayBd;

    private LocalDate ngayKt;

    private Integer sudung;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSolop() {
        return solop;
    }

    public void setSolop(Integer solop) {
        this.solop = solop;
    }

    public Integer getSohocvien() {
        return sohocvien;
    }

    public void setSohocvien(Integer sohocvien) {
        this.sohocvien = sohocvien;
    }

    public Integer getDathanhtoan() {
        return dathanhtoan;
    }

    public void setDathanhtoan(Integer dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
    }

    public Integer getMadutoan() {
        return madutoan;
    }

    public void setMadutoan(Integer madutoan) {
        this.madutoan = madutoan;
    }

    public String getTendutoan() {
        return tendutoan;
    }

    public void setTendutoan(String tendutoan) {
        this.tendutoan = tendutoan;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
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

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO = (QldtDutoanDaotaoDTO) o;
        if (qldtDutoanDaotaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtDutoanDaotaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtDutoanDaotaoDTO{" +
            "id=" + getId() +
            ", solop=" + getSolop() +
            ", sohocvien=" + getSohocvien() +
            ", dathanhtoan=" + getDathanhtoan() +
            ", madutoan=" + getMadutoan() +
            ", tendutoan='" + getTendutoan() + "'" +
            ", trangthai=" + getTrangthai() +
            ", ngayBd='" + getNgayBd() + "'" +
            ", ngayKt='" + getNgayKt() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
