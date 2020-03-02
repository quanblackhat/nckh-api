package com.vnptit.vnpthis.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.TienDo} entity.
 */
public class TienDoDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer tyleHoanthanh;

    @NotNull
    private String mota;

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

    public Integer getTyleHoanthanh() {
        return tyleHoanthanh;
    }

    public void setTyleHoanthanh(Integer tyleHoanthanh) {
        this.tyleHoanthanh = tyleHoanthanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
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

        TienDoDTO tienDoDTO = (TienDoDTO) o;
        if (tienDoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tienDoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TienDoDTO{" +
            "id=" + getId() +
            ", tyleHoanthanh=" + getTyleHoanthanh() +
            ", mota='" + getMota() + "'" +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            ", deTaiId=" + getDeTaiId() +
            "}";
    }
}
