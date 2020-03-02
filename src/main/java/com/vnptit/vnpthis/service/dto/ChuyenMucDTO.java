package com.vnptit.vnpthis.service.dto;
import com.vnptit.vnpthis.domain.nckh.ChuyenMuc;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ChuyenMuc} entity.
 */
public class ChuyenMucDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate ngaytao;

    @NotNull
    private Integer sott;

    @NotNull
    private String tenchuyenmuc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Integer getSott() {
        return sott;
    }

    public void setSott(Integer sott) {
        this.sott = sott;
    }

    public String getTenchuyenmuc() {
        return tenchuyenmuc;
    }

    public void setTenchuyenmuc(String tenchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChuyenMucDTO chuyenMucDTO = (ChuyenMucDTO) o;
        if (chuyenMucDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chuyenMucDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChuyenMucDTO{" +
            "id=" + getId() +
            ", ngaytao='" + getNgaytao() + "'" +
            ", sott=" + getSott() +
            ", tenchuyenmuc='" + getTenchuyenmuc() + "'" +
            "}";
    }
}
