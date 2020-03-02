package com.vnptit.vnpthis.domain.nckh;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A UpFile.
 */
@Entity
@Table(name = "NCKH_UPFILE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UpFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "fileid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "mota", nullable = false)
    private String mota;


    @Lob
    @Column(name = "noidung", nullable = false)
    private byte[] noidung;

//    @Column(name = "noidung_content_type", nullable = false)
//    private String noidungContentType;

    @NotNull
    @Column(name = "ngay_cn", nullable = false)
    private LocalDate ngayCn;

    @NotNull
    @Column(name = "nguoi_cn", nullable = false)
    private Integer nguoiCn;

    @ManyToOne
    @JoinColumn(name = "detaiid", insertable = false, updatable = false)
    @JsonIgnoreProperties("upFiles")
    private DeTai deTai;

    @ManyToOne
    @JoinColumn(name = "tiendoid", insertable = false, updatable = false)
    @JsonIgnoreProperties("upFiles")
    private TienDo tienDo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMota() {
        return mota;
    }

    public UpFile mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public byte[] getNoidung() {
        return noidung;
    }

    public UpFile noidung(byte[] noidung) {
        this.noidung = noidung;
        return this;
    }

    public void setNoidung(byte[] noidung) {
        this.noidung = noidung;
    }

//    public String getNoidungContentType() {
//        return noidungContentType;
//    }
//
//    public UpFile noidungContentType(String noidungContentType) {
//        this.noidungContentType = noidungContentType;
//        return this;
//    }

//    public void setNoidungContentType(String noidungContentType) {
//        this.noidungContentType = noidungContentType;
//    }

    public LocalDate getNgayCn() {
        return ngayCn;
    }

    public UpFile ngayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
        return this;
    }

    public void setNgayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
    }

    public Integer getNguoiCn() {
        return nguoiCn;
    }

    public UpFile nguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
        return this;
    }

    public void setNguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public UpFile deTai(DeTai deTai) {
        this.deTai = deTai;
        return this;
    }

    public void setDeTai(DeTai deTai) {
        this.deTai = deTai;
    }

    public TienDo getTienDo() {
        return tienDo;
    }

    public UpFile tienDo(TienDo tienDo) {
        this.tienDo = tienDo;
        return this;
    }

    public void setTienDo(TienDo tienDo) {
        this.tienDo = tienDo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpFile)) {
            return false;
        }
        return id != null && id.equals(((UpFile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UpFile{" +
            "id=" + getId() +
            ", mota='" + getMota() + "'" +
            ", noidung='" + getNoidung() + "'" +
//            ", noidungContentType='" + getNoidungContentType() + "'" +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            "}";
    }
}
