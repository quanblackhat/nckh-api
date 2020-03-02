package com.vnptit.vnpthis.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A TienDo.
 */
@Entity
@Table(name = "NCKH_TIENDO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TienDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "tiendoid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "tyle_hoanthanh", nullable = false)
    private Integer tyleHoanthanh;

    @NotNull
    @Column(name = "mota", nullable = false)
    private String mota;

    @NotNull
    @Column(name = "ngay_cn", nullable = false)
    private LocalDate ngayCn;

    @NotNull
    @Column(name = "nguoi_cn", nullable = false)
    private Integer nguoiCn;

    @OneToMany(mappedBy = "tienDo", cascade = CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UpFile> upFiles = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("tienDos")
    @JoinColumn(name = "detaiid", insertable = false, updatable = false)
    private DeTai deTai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTyleHoanthanh() {
        return tyleHoanthanh;
    }

    public TienDo tyleHoanthanh(Integer tyleHoanthanh) {
        this.tyleHoanthanh = tyleHoanthanh;
        return this;
    }

    public void setTyleHoanthanh(Integer tyleHoanthanh) {
        this.tyleHoanthanh = tyleHoanthanh;
    }

    public String getMota() {
        return mota;
    }

    public TienDo mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public LocalDate getNgayCn() {
        return ngayCn;
    }

    public TienDo ngayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
        return this;
    }

    public void setNgayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
    }

    public Integer getNguoiCn() {
        return nguoiCn;
    }

    public TienDo nguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
        return this;
    }

    public void setNguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public Set<UpFile> getUpFiles() {
        return upFiles;
    }

    public TienDo upFiles(Set<UpFile> upFiles) {
        this.upFiles = upFiles;
        return this;
    }

    public TienDo addUpFile(UpFile upFile) {
        this.upFiles.add(upFile);
        upFile.setTienDo(this);
        return this;
    }

    public TienDo removeUpFile(UpFile upFile) {
        this.upFiles.remove(upFile);
        upFile.setTienDo(null);
        return this;
    }

    public void setUpFiles(Set<UpFile> upFiles) {
        this.upFiles = upFiles;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public TienDo deTai(DeTai deTai) {
        this.deTai = deTai;
        return this;
    }

    public void setDeTai(DeTai deTai) {
        this.deTai = deTai;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TienDo)) {
            return false;
        }
        return id != null && id.equals(((TienDo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TienDo{" +
            "id=" + getId() +
            ", tyleHoanthanh=" + getTyleHoanthanh() +
            ", mota='" + getMota() + "'" +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            "}";
    }
}
