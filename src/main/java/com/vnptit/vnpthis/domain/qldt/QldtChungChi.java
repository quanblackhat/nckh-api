package com.vnptit.vnpthis.domain.qldt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A QldtChungChi.
 */
@Entity
@Table(name = "QLDT_CHUNGCHI")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtChungChi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "han")
    private Integer han;

    @Column(name = "ngaycap")
    private LocalDate ngaycap;

    @Column(name = "ngayhethan")
    private LocalDate ngayhethan;

    @Column(name = "urlchungchi")
    private String urlchungchi;

    @Column(name = "sudung")
    private Integer sudung;

    @ManyToOne
    @JsonIgnoreProperties("chungChis")
    private QldtDmChungchi qldtDmChungchi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHan() {
        return han;
    }

    public QldtChungChi han(Integer han) {
        this.han = han;
        return this;
    }

    public void setHan(Integer han) {
        this.han = han;
    }

    public LocalDate getNgaycap() {
        return ngaycap;
    }

    public QldtChungChi ngaycap(LocalDate ngaycap) {
        this.ngaycap = ngaycap;
        return this;
    }

    public void setNgaycap(LocalDate ngaycap) {
        this.ngaycap = ngaycap;
    }

    public LocalDate getNgayhethan() {
        return ngayhethan;
    }

    public QldtChungChi ngayhethan(LocalDate ngayhethan) {
        this.ngayhethan = ngayhethan;
        return this;
    }

    public void setNgayhethan(LocalDate ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public String getUrlchungchi() {
        return urlchungchi;
    }

    public QldtChungChi urlchungchi(String urlchungchi) {
        this.urlchungchi = urlchungchi;
        return this;
    }

    public void setUrlchungchi(String urlchungchi) {
        this.urlchungchi = urlchungchi;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtChungChi sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public QldtDmChungchi getQldtDmChungchi() {
        return qldtDmChungchi;
    }

    public QldtChungChi qldtDmChungchi(QldtDmChungchi qldtDmChungchi) {
        this.qldtDmChungchi = qldtDmChungchi;
        return this;
    }

    public void setQldtDmChungchi(QldtDmChungchi qldtDmChungchi) {
        this.qldtDmChungchi = qldtDmChungchi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtChungChi)) {
            return false;
        }
        return id != null && id.equals(((QldtChungChi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtChungChi{" +
            "id=" + getId() +
            ", han=" + getHan() +
            ", ngaycap='" + getNgaycap() + "'" +
            ", ngayhethan='" + getNgayhethan() + "'" +
            ", urlchungchi='" + getUrlchungchi() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
