package com.vnptit.vnpthis.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A LoaiDanhMuc.
 */
@Entity
@Table(name = "NCKH_LOAIDM")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LoaiDanhMuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ten", nullable = false)
    private String ten;

    @NotNull
    @Column(name = "sudung", nullable = false)
    private Integer sudung;

    @OneToMany(mappedBy = "loaiDanhMuc", cascade = CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DanhMuc> danhMucs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public LoaiDanhMuc ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getSudung() {
        return sudung;
    }

    public LoaiDanhMuc sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Set<DanhMuc> getDanhMucs() {
        return danhMucs;
    }

    public LoaiDanhMuc danhMucs(Set<DanhMuc> danhMucs) {
        this.danhMucs = danhMucs;
        return this;
    }

    public LoaiDanhMuc addDanhMuc(DanhMuc danhMuc) {
        this.danhMucs.add(danhMuc);
        danhMuc.setLoaiDanhMuc(this);
        return this;
    }

    public LoaiDanhMuc removeDanhMuc(DanhMuc danhMuc) {
        this.danhMucs.remove(danhMuc);
        danhMuc.setLoaiDanhMuc(null);
        return this;
    }

    public void setDanhMucs(Set<DanhMuc> danhMucs) {
        this.danhMucs = danhMucs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoaiDanhMuc)) {
            return false;
        }
        return id != null && id.equals(((LoaiDanhMuc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LoaiDanhMuc{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
