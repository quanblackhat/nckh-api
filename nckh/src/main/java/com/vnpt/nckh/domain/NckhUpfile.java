package com.vnpt.nckh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * NckhUpfile entity.\n@author VINHHC.
 */
@ApiModel(description = "NckhUpfile entity.\n@author VINHHC.")
@Entity
@Table(name = "nckh_upfile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NckhUpfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4000)
    @Column(name = "mo_ta", length = 4000)
    private String moTa;

    @Lob
    @Column(name = "noi_dung")
    private byte[] noiDung;

    @Column(name = "noi_dung_content_type")
    private String noiDungContentType;

    @Column(name = "nguoi_cn")
    private Long nguoiCN;

    @Column(name = "ngay_cn")
    private Instant ngayCN;

    @ManyToOne
    @JsonIgnoreProperties("nckhUpfiles")
    private NckhDetai nckhDetai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoTa() {
        return moTa;
    }

    public NckhUpfile moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public byte[] getNoiDung() {
        return noiDung;
    }

    public NckhUpfile noiDung(byte[] noiDung) {
        this.noiDung = noiDung;
        return this;
    }

    public void setNoiDung(byte[] noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiDungContentType() {
        return noiDungContentType;
    }

    public NckhUpfile noiDungContentType(String noiDungContentType) {
        this.noiDungContentType = noiDungContentType;
        return this;
    }

    public void setNoiDungContentType(String noiDungContentType) {
        this.noiDungContentType = noiDungContentType;
    }

    public Long getNguoiCN() {
        return nguoiCN;
    }

    public NckhUpfile nguoiCN(Long nguoiCN) {
        this.nguoiCN = nguoiCN;
        return this;
    }

    public void setNguoiCN(Long nguoiCN) {
        this.nguoiCN = nguoiCN;
    }

    public Instant getNgayCN() {
        return ngayCN;
    }

    public NckhUpfile ngayCN(Instant ngayCN) {
        this.ngayCN = ngayCN;
        return this;
    }

    public void setNgayCN(Instant ngayCN) {
        this.ngayCN = ngayCN;
    }

    public NckhDetai getNckhDetai() {
        return nckhDetai;
    }

    public NckhUpfile nckhDetai(NckhDetai nckhDetai) {
        this.nckhDetai = nckhDetai;
        return this;
    }

    public void setNckhDetai(NckhDetai nckhDetai) {
        this.nckhDetai = nckhDetai;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NckhUpfile)) {
            return false;
        }
        return id != null && id.equals(((NckhUpfile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NckhUpfile{" +
            "id=" + getId() +
            ", moTa='" + getMoTa() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", noiDungContentType='" + getNoiDungContentType() + "'" +
            ", nguoiCN=" + getNguoiCN() +
            ", ngayCN='" + getNgayCN() + "'" +
            "}";
    }
}
