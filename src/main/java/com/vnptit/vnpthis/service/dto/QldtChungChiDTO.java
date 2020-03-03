package com.vnptit.vnpthis.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtChungChi} entity.
 */
public class QldtChungChiDTO implements Serializable {

    private Long id;

    private Integer han;

    private LocalDate ngaycap;

    private LocalDate ngayhethan;

    private String urlchungchi;

    private Integer sudung;


    private Long qldtDmChungchiId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHan() {
        return han;
    }

    public void setHan(Integer han) {
        this.han = han;
    }

    public LocalDate getNgaycap() {
        return ngaycap;
    }

    public void setNgaycap(LocalDate ngaycap) {
        this.ngaycap = ngaycap;
    }

    public LocalDate getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(LocalDate ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public String getUrlchungchi() {
        return urlchungchi;
    }

    public void setUrlchungchi(String urlchungchi) {
        this.urlchungchi = urlchungchi;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Long getQldtDmChungchiId() {
        return qldtDmChungchiId;
    }

    public void setQldtDmChungchiId(Long qldtDmChungchiId) {
        this.qldtDmChungchiId = qldtDmChungchiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QldtChungChiDTO qldtChungChiDTO = (QldtChungChiDTO) o;
        if (qldtChungChiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtChungChiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtChungChiDTO{" +
            "id=" + getId() +
            ", han=" + getHan() +
            ", ngaycap='" + getNgaycap() + "'" +
            ", ngayhethan='" + getNgayhethan() + "'" +
            ", urlchungchi='" + getUrlchungchi() + "'" +
            ", sudung=" + getSudung() +
            ", qldtDmChungchiId=" + getQldtDmChungchiId() +
            "}";
    }
}
