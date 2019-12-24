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
 * NckhTiendo entity.\n@author VINHHC.
 */
@ApiModel(description = "NckhTiendo entity.\n@author VINHHC.")
@Entity
@Table(name = "nckh_tiendo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NckhTiendo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tien_do_hoan_thanh")
    private Long tienDoHoanThanh;

    @Size(max = 4000)
    @Column(name = "mo_ta", length = 4000)
    private String moTa;

    @Column(name = "ngay_cap_nhat")
    private Instant ngayCapNhat;

    @ManyToOne
    @JsonIgnoreProperties("nckhTiendos")
    private NckhDetai nckhDetai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTienDoHoanThanh() {
        return tienDoHoanThanh;
    }

    public NckhTiendo tienDoHoanThanh(Long tienDoHoanThanh) {
        this.tienDoHoanThanh = tienDoHoanThanh;
        return this;
    }

    public void setTienDoHoanThanh(Long tienDoHoanThanh) {
        this.tienDoHoanThanh = tienDoHoanThanh;
    }

    public String getMoTa() {
        return moTa;
    }

    public NckhTiendo moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Instant getNgayCapNhat() {
        return ngayCapNhat;
    }

    public NckhTiendo ngayCapNhat(Instant ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
        return this;
    }

    public void setNgayCapNhat(Instant ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public NckhDetai getNckhDetai() {
        return nckhDetai;
    }

    public NckhTiendo nckhDetai(NckhDetai nckhDetai) {
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
        if (!(o instanceof NckhTiendo)) {
            return false;
        }
        return id != null && id.equals(((NckhTiendo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NckhTiendo{" +
            "id=" + getId() +
            ", tienDoHoanThanh=" + getTienDoHoanThanh() +
            ", moTa='" + getMoTa() + "'" +
            ", ngayCapNhat='" + getNgayCapNhat() + "'" +
            "}";
    }
}
