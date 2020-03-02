package com.vnptit.vnpthis.service.dto;
import com.vnptit.vnpthis.domain.nckh.DuToan;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link DuToan} entity.
 */
public class DuToanDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer tienDukien;

    @NotNull
    private Integer tienHoanthanh;

    @NotNull
    private String ghichu;

    @NotNull
    private LocalDate ngayCn;

    @NotNull
    private Integer nguoiCn;


    private Long deTaiId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTienDukien() {
        return tienDukien;
    }

    public void setTienDukien(Integer tienDukien) {
        this.tienDukien = tienDukien;
    }

    public Integer getTienHoanthanh() {
        return tienHoanthanh;
    }

    public void setTienHoanthanh(Integer tienHoanthanh) {
        this.tienHoanthanh = tienHoanthanh;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public LocalDate getNgayCn() {
        return ngayCn;
    }

    public void setNgayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
    }

    public Integer getNguoiCn() {
        return nguoiCn;
    }

    public void setNguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public Long getDeTaiId() {
        return deTaiId;
    }

    public void setDeTaiId(Long deTaiId) {
        this.deTaiId = deTaiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DuToanDTO duToanDTO = (DuToanDTO) o;
        if (duToanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), duToanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DuToanDTO{" +
            "id=" + getId() +
            ", tienDukien=" + getTienDukien() +
            ", tienHoanthanh=" + getTienHoanthanh() +
            ", ghichu='" + getGhichu() + "'" +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            ", deTaiId=" + getDeTaiId() +
            "}";
    }
}
