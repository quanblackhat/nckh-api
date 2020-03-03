package com.vnptit.vnpthis.domain.qldt;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A QldtDmNoidung.
 */
@Entity
@Table(name = "QLDT_DM_NOIDUNG")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtDmNoidung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "noidungid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "noidung")
    private String noidung;

    @Column(name = "sudung")
    private Integer sudung;

    @OneToMany(mappedBy = "qldtDmNoidung")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QldtDutoanDaotaoct> duToandaotaoCts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public QldtDmNoidung noidung(String noidung) {
        this.noidung = noidung;
        return this;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtDmNoidung sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Set<QldtDutoanDaotaoct> getDuToandaotaoCts() {
        return duToandaotaoCts;
    }

    public QldtDmNoidung duToandaotaoCts(Set<QldtDutoanDaotaoct> qldtDutoanDaotaocts) {
        this.duToandaotaoCts = qldtDutoanDaotaocts;
        return this;
    }

    public QldtDmNoidung addDuToandaotaoCt(QldtDutoanDaotaoct qldtDutoanDaotaoct) {
        this.duToandaotaoCts.add(qldtDutoanDaotaoct);
        qldtDutoanDaotaoct.setQldtDmNoidung(this);
        return this;
    }

    public QldtDmNoidung removeDuToandaotaoCt(QldtDutoanDaotaoct qldtDutoanDaotaoct) {
        this.duToandaotaoCts.remove(qldtDutoanDaotaoct);
        qldtDutoanDaotaoct.setQldtDmNoidung(null);
        return this;
    }

    public void setDuToandaotaoCts(Set<QldtDutoanDaotaoct> qldtDutoanDaotaocts) {
        this.duToandaotaoCts = qldtDutoanDaotaocts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtDmNoidung)) {
            return false;
        }
        return id != null && id.equals(((QldtDmNoidung) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtDmNoidung{" +
            "id=" + getId() +
            ", noidung='" + getNoidung() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
