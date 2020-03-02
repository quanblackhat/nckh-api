package com.vnptit.vnpthis.domain.nckh;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A ChuyenMuc.
 */
@Entity
@Table(name = "NCKH_CHUYENMUC")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChuyenMuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "chuyenmucid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ngaytao", nullable = false)
    private LocalDate ngaytao;

    @NotNull
    @Column(name = "sott", nullable = false)
    private Integer sott;

    @NotNull
    @Column(name = "tenchuyenmuc", nullable = false)
    private String tenchuyenmuc;

    @OneToMany(mappedBy = "chuyenMuc", cascade = CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DeTai> deTais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgaytao() {
        return ngaytao;
    }

    public ChuyenMuc ngaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
        return this;
    }

    public void setNgaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Integer getSott() {
        return sott;
    }

    public ChuyenMuc sott(Integer sott) {
        this.sott = sott;
        return this;
    }

    public void setSott(Integer sott) {
        this.sott = sott;
    }

    public String getTenchuyenmuc() {
        return tenchuyenmuc;
    }

    public ChuyenMuc tenchuyenmuc(String tenchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
        return this;
    }

    public void setTenchuyenmuc(String tenchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
    }

    public Set<DeTai> getDeTais() {
        return deTais;
    }

    public ChuyenMuc deTais(Set<DeTai> deTais) {
        this.deTais = deTais;
        return this;
    }

    public ChuyenMuc addDeTai(DeTai deTai) {
        this.deTais.add(deTai);
        deTai.setChuyenMuc(this);
        return this;
    }

    public ChuyenMuc removeDeTai(DeTai deTai) {
        this.deTais.remove(deTai);
        deTai.setChuyenMuc(null);
        return this;
    }

    public void setDeTais(Set<DeTai> deTais) {
        this.deTais = deTais;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChuyenMuc)) {
            return false;
        }
        return id != null && id.equals(((ChuyenMuc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ChuyenMuc{" +
            "id=" + getId() +
            ", ngaytao='" + getNgaytao() + "'" +
            ", sott=" + getSott() +
            ", tenchuyenmuc='" + getTenchuyenmuc() + "'" +
            "}";
    }
}
