package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtQlHocvienCt} entity.
 */
public class QldtQlHocvienCtDTO implements Serializable {

    private Long id;

    private Integer diemdanh;

    private Integer diemthi;

    private String danhgia;

    private Integer sudung;


    private Long qldtDaotaoCtId;

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

    public Integer getDiemthi() {
        return diemthi;
    }

    public void setDiemthi(Integer diemthi) {
        this.diemthi = diemthi;
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

    public Long getQldtDaotaoCtId() {
        return qldtDaotaoCtId;
    }

    public void setQldtDaotaoCtId(Long qldtDaotaoCtId) {
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

        QldtQlHocvienCtDTO qldtQlHocvienCtDTO = (QldtQlHocvienCtDTO) o;
        if (qldtQlHocvienCtDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtQlHocvienCtDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtQlHocvienCtDTO{" +
            "id=" + getId() +
            ", diemdanh=" + getDiemdanh() +
            ", diemthi=" + getDiemthi() +
            ", danhgia='" + getDanhgia() + "'" +
            ", sudung=" + getSudung() +
            ", qldtDaotaoCtId=" + getQldtDaotaoCtId() +
            "}";
    }
}
