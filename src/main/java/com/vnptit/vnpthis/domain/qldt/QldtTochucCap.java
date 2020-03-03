package com.vnptit.vnpthis.domain.qldt;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A QldtTochucCap.
 */
@Entity
@Table(name = "QLDT_TOCHUC_CAP")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtTochucCap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "matochuc")
    private String matochuc;

    @Column(name = "tentochuc")
    private String tentochuc;

    @Column(name = "noidung")
    private String noidung;

    @Column(name = "sudung")
    private Integer sudung;

    @OneToMany(mappedBy = "qldtTochucCap")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QldtDmChungchi> dmChungchis = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatochuc() {
        return matochuc;
    }

    public QldtTochucCap matochuc(String matochuc) {
        this.matochuc = matochuc;
        return this;
    }

    public void setMatochuc(String matochuc) {
        this.matochuc = matochuc;
    }

    public String getTentochuc() {
        return tentochuc;
    }

    public QldtTochucCap tentochuc(String tentochuc) {
        this.tentochuc = tentochuc;
        return this;
    }

    public void setTentochuc(String tentochuc) {
        this.tentochuc = tentochuc;
    }

    public String getNoidung() {
        return noidung;
    }

    public QldtTochucCap noidung(String noidung) {
        this.noidung = noidung;
        return this;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtTochucCap sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Set<QldtDmChungchi> getDmChungchis() {
        return dmChungchis;
    }

    public QldtTochucCap dmChungchis(Set<QldtDmChungchi> qldtDmChungchis) {
        this.dmChungchis = qldtDmChungchis;
        return this;
    }

    public QldtTochucCap addDmChungchi(QldtDmChungchi qldtDmChungchi) {
        this.dmChungchis.add(qldtDmChungchi);
        qldtDmChungchi.setQldtTochucCap(this);
        return this;
    }

    public QldtTochucCap removeDmChungchi(QldtDmChungchi qldtDmChungchi) {
        this.dmChungchis.remove(qldtDmChungchi);
        qldtDmChungchi.setQldtTochucCap(null);
        return this;
    }

    public void setDmChungchis(Set<QldtDmChungchi> qldtDmChungchis) {
        this.dmChungchis = qldtDmChungchis;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtTochucCap)) {
            return false;
        }
        return id != null && id.equals(((QldtTochucCap) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtTochucCap{" +
            "id=" + getId() +
            ", matochuc='" + getMatochuc() + "'" +
            ", tentochuc='" + getTentochuc() + "'" +
            ", noidung='" + getNoidung() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
