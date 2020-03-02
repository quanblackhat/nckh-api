package com.vnptit.vnpthis.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.NhanSu} entity.
 */
public class NhanSuDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer chunhiemdetai;

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

    public Integer getChunhiemdetai() {
        return chunhiemdetai;
    }

    public void setChunhiemdetai(Integer chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
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

        NhanSuDTO nhanSuDTO = (NhanSuDTO) o;
        if (nhanSuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhanSuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhanSuDTO{" +
            "id=" + getId() +
            ", chunhiemdetai=" + getChunhiemdetai() +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            ", deTaiId=" + getDeTaiId() +
            "}";
    }
}
