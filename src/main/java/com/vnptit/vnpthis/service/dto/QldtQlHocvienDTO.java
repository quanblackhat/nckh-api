package com.vnptit.vnpthis.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtQlHocvien} entity.
 */
public class QldtQlHocvienDTO implements Serializable {

    private Long id;

    private Integer diemdanh;

    private Integer diem;

    private String danhgia;

    private Integer sudung;

    private Integer trangthaithanhtoan;

    private LocalDate ngaythanhtoan;

    private String mathanhtoan;


    private Long qldtDaotaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiemdanh() {
        return diemdanh;
    }

    public void setDiemdanh(Integer diemdanh) {
        this.diemdanh = diemdanh;
    }

    public Integer getDiem() {
        return diem;
    }

    public void setDiem(Integer diem) {
        this.diem = diem;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Integer getTrangthaithanhtoan() {
        return trangthaithanhtoan;
    }

    public void setTrangthaithanhtoan(Integer trangthaithanhtoan) {
        this.trangthaithanhtoan = trangthaithanhtoan;
    }

    public LocalDate getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(LocalDate ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public String getMathanhtoan() {
        return mathanhtoan;
    }

    public void setMathanhtoan(String mathanhtoan) {
        this.mathanhtoan = mathanhtoan;
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

        QldtQlHocvienDTO qldtQlHocvienDTO = (QldtQlHocvienDTO) o;
        if (qldtQlHocvienDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtQlHocvienDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtQlHocvienDTO{" +
            "id=" + getId() +
            ", diemdanh=" + getDiemdanh() +
            ", diem=" + getDiem() +
            ", danhgia='" + getDanhgia() + "'" +
            ", sudung=" + getSudung() +
            ", trangthaithanhtoan=" + getTrangthaithanhtoan() +
            ", ngaythanhtoan='" + getNgaythanhtoan() + "'" +
            ", mathanhtoan='" + getMathanhtoan() + "'" +
            ", qldtDaotaoId=" + getQldtDaotaoId() +
            "}";
    }
}
