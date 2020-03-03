package com.vnptit.vnpthis.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtDmNguoidung} entity.
 */
public class QldtDmNguoidungDTO implements Serializable {

    private Long id;

    private String ten;

    private LocalDate ngaysinh;

    private String mabenhvien;

    private Integer sudung;


    private Long daoTaoCtId;

    private Long hocVienId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getMabenhvien() {
        return mabenhvien;
    }

    public void setMabenhvien(String mabenhvien) {
        this.mabenhvien = mabenhvien;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Long getDaoTaoCtId() {
        return daoTaoCtId;
    }

    public void setDaoTaoCtId(Long qldtDaotaoCtId) {
        this.daoTaoCtId = qldtDaotaoCtId;
    }

    public Long getHocVienId() {
        return hocVienId;
    }

    public void setHocVienId(Long qldtQlHocvienId) {
        this.hocVienId = qldtQlHocvienId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QldtDmNguoidungDTO qldtDmNguoidungDTO = (QldtDmNguoidungDTO) o;
        if (qldtDmNguoidungDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtDmNguoidungDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtDmNguoidungDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", ngaysinh='" + getNgaysinh() + "'" +
            ", mabenhvien='" + getMabenhvien() + "'" +
            ", sudung=" + getSudung() +
            ", daoTaoCtId=" + getDaoTaoCtId() +
            ", hocVienId=" + getHocVienId() +
            "}";
    }
}
