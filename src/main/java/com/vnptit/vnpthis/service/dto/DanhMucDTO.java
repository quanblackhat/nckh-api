package com.vnptit.vnpthis.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.DanhMuc} entity.
 */
public class DanhMucDTO implements Serializable {

    private Long id;

    @NotNull
    private String ma;

    @NotNull
    private LocalDate ngaytao;

    @NotNull
    private Integer sudung;

    @NotNull
    private String ten;

    @NotNull
    private Integer thutu;


    private Long loaiDanhMucId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public LocalDate getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getThutu() {
        return thutu;
    }

    public void setThutu(Integer thutu) {
        this.thutu = thutu;
    }

    public Long getLoaiDanhMucId() {
        return loaiDanhMucId;
    }

    public void setLoaiDanhMucId(Long loaiDanhMucId) {
        this.loaiDanhMucId = loaiDanhMucId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanhMucDTO danhMucDTO = (DanhMucDTO) o;
        if (danhMucDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhMucDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhMucDTO{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ngaytao='" + getNgaytao() + "'" +
            ", sudung=" + getSudung() +
            ", ten='" + getTen() + "'" +
            ", thutu=" + getThutu() +
            ", loaiDanhMucId=" + getLoaiDanhMucId() +
            "}";
    }
}
