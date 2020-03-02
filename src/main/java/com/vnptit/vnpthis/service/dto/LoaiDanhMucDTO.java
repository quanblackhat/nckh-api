package com.vnptit.vnpthis.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.LoaiDanhMuc} entity.
 */
public class LoaiDanhMucDTO implements Serializable {

    private Long id;

    @NotNull
    private String ten;

    @NotNull
    private Integer sudung;


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

        LoaiDanhMucDTO loaiDanhMucDTO = (LoaiDanhMucDTO) o;
        if (loaiDanhMucDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loaiDanhMucDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoaiDanhMucDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
