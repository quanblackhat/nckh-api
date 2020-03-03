package com.vnptit.vnpthis.domain.qldt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A QldtDmChungchi.
 */
@Entity
@Table(name = "QLDT_DM_CHUNGCHI")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtDmChungchi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "machungchi")
    private String machungchi;

    @Column(name = "tenchungchi")
    private String tenchungchi;

    @Column(name = "mota")
    private String mota;

    @Column(name = "trangthai")
    private Integer trangthai;

    @Column(name = "sudung")
    private Integer sudung;

    @OneToMany(mappedBy = "qldtDmChungchi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QldtChungChi> chungChis = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("dmChungchis")
    private QldtTochucCap qldtTochucCap;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachungchi() {
        return machungchi;
    }

    public QldtDmChungchi machungchi(String machungchi) {
        this.machungchi = machungchi;
        return this;
    }

    public void setMachungchi(String machungchi) {
        this.machungchi = machungchi;
    }

    public String getTenchungchi() {
        return tenchungchi;
    }

    public QldtDmChungchi tenchungchi(String tenchungchi) {
        this.tenchungchi = tenchungchi;
        return this;
    }

    public void setTenchungchi(String tenchungchi) {
        this.tenchungchi = tenchungchi;
    }

    public String getMota() {
        return mota;
    }

    public QldtDmChungchi mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public QldtDmChungchi trangthai(Integer trangthai) {
        this.trangthai = trangthai;
        return this;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtDmChungchi sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Set<QldtChungChi> getChungChis() {
        return chungChis;
    }

    public QldtDmChungchi chungChis(Set<QldtChungChi> qldtChungChis) {
        this.chungChis = qldtChungChis;
        return this;
    }

    public QldtDmChungchi addChungChi(QldtChungChi qldtChungChi) {
        this.chungChis.add(qldtChungChi);
        qldtChungChi.setQldtDmChungchi(this);
        return this;
    }

    public QldtDmChungchi removeChungChi(QldtChungChi qldtChungChi) {
        this.chungChis.remove(qldtChungChi);
        qldtChungChi.setQldtDmChungchi(null);
        return this;
    }

    public void setChungChis(Set<QldtChungChi> qldtChungChis) {
        this.chungChis = qldtChungChis;
    }

    public QldtTochucCap getQldtTochucCap() {
        return qldtTochucCap;
    }

    public QldtDmChungchi qldtTochucCap(QldtTochucCap qldtTochucCap) {
        this.qldtTochucCap = qldtTochucCap;
        return this;
    }

    public void setQldtTochucCap(QldtTochucCap qldtTochucCap) {
        this.qldtTochucCap = qldtTochucCap;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtDmChungchi)) {
            return false;
        }
        return id != null && id.equals(((QldtDmChungchi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtDmChungchi{" +
            "id=" + getId() +
            ", machungchi='" + getMachungchi() + "'" +
            ", tenchungchi='" + getTenchungchi() + "'" +
            ", mota='" + getMota() + "'" +
            ", trangthai=" + getTrangthai() +
            ", sudung=" + getSudung() +
            "}";
    }
}
