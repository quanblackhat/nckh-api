package com.vnptit.vnpthis.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.DanhGia} entity.
 */
public class DanhGiaDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer diemdanhgia;

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

    public Integer getDiemdanhgia() {
        return diemdanhgia;
    }

    public void setDiemdanhgia(Integer diemdanhgia) {
        this.diemdanhgia = diemdanhgia;
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

        DanhGiaDTO danhGiaDTO = (DanhGiaDTO) o;
        if (danhGiaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhGiaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhGiaDTO{" +
            "id=" + getId() +
            ", diemdanhgia=" + getDiemdanhgia() +
            ", ghichu='" + getGhichu() + "'" +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            ", deTaiId=" + getDeTaiId() +
            "}";
    }
}
