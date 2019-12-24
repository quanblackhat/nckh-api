package com.vnpt.nckh.domain;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * NckhDanhmuc entity.\n@author VINHHC.
 */
@ApiModel(description = "NckhDanhmuc entity.\n@author VINHHC.")
@Entity
@Table(name = "nckh_danhmuc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NckhDanhmuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 255)
    @Column(name = "ma", length = 255)
    private String ma;

    @NotNull
    @Size(max = 255)
    @Column(name = "ten", length = 255, nullable = false)
    private String ten;

    @Column(name = "ngaytao")
    private Instant ngaytao;

    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "csytid")
    private Long csytid;

    @Min(value = 1)
    @Max(value = 2)
    @Column(name = "sudung")
    private Integer sudung;

    @Column(name = "thutu")
    private Long thutu;

    @OneToMany(mappedBy = "nckhDanhmuc")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NckhDetai> nckhDetais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public NckhDanhmuc ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public NckhDanhmuc ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Instant getNgaytao() {
        return ngaytao;
    }

    public NckhDanhmuc ngaytao(Instant ngaytao) {
        this.ngaytao = ngaytao;
        return this;
    }

    public void setNgaytao(Instant ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Long getCsytid() {
        return csytid;
    }

    public NckhDanhmuc csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public Integer getSudung() {
        return sudung;
    }

    public NckhDanhmuc sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Long getThutu() {
        return thutu;
    }

    public NckhDanhmuc thutu(Long thutu) {
        this.thutu = thutu;
        return this;
    }

    public void setThutu(Long thutu) {
        this.thutu = thutu;
    }

    public Set<NckhDetai> getNckhDetais() {
        return nckhDetais;
    }

    public NckhDanhmuc nckhDetais(Set<NckhDetai> nckhDetais) {
        this.nckhDetais = nckhDetais;
        return this;
    }

    public NckhDanhmuc addNckhDetai(NckhDetai nckhDetai) {
        this.nckhDetais.add(nckhDetai);
        nckhDetai.setNckhDanhmuc(this);
        return this;
    }

    public NckhDanhmuc removeNckhDetai(NckhDetai nckhDetai) {
        this.nckhDetais.remove(nckhDetai);
        nckhDetai.setNckhDanhmuc(null);
        return this;
    }

    public void setNckhDetais(Set<NckhDetai> nckhDetais) {
        this.nckhDetais = nckhDetais;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NckhDanhmuc)) {
            return false;
        }
        return id != null && id.equals(((NckhDanhmuc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NckhDanhmuc{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", ngaytao='" + getNgaytao() + "'" +
            ", csytid=" + getCsytid() +
            ", sudung=" + getSudung() +
            ", thutu=" + getThutu() +
            "}";
    }
}
