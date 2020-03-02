package com.vnptit.vnpthis.service.dto;
import com.vnptit.vnpthis.domain.nckh.UpFile;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link UpFile} entity.
 */
public class UpFileDTO implements Serializable {

    private Long id;

    @NotNull
    private String mota;


    @Lob
    private byte[] noidung;

    private String noidungContentType;
    @NotNull
    private LocalDate ngayCn;

    @NotNull
    private Integer nguoiCn;


    private Long deTaiId;

    private Long tienDoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public byte[] getNoidung() {
        return noidung;
    }

    public void setNoidung(byte[] noidung) {
        this.noidung = noidung;
    }

    public String getNoidungContentType() {
        return noidungContentType;
    }

    public void setNoidungContentType(String noidungContentType) {
        this.noidungContentType = noidungContentType;
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

    public Long getTienDoId() {
        return tienDoId;
    }

    public void setTienDoId(Long tienDoId) {
        this.tienDoId = tienDoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UpFileDTO upFileDTO = (UpFileDTO) o;
        if (upFileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), upFileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UpFileDTO{" +
            "id=" + getId() +
            ", mota='" + getMota() + "'" +
            ", noidung='" + getNoidung() + "'" +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            ", deTaiId=" + getDeTaiId() +
            ", tienDoId=" + getTienDoId() +
            "}";
    }
}
