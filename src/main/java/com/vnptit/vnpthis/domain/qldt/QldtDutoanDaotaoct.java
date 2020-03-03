package com.vnptit.vnpthis.domain.qldt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A QldtDutoanDaotaoct.
 */
@Entity
@Table(name = "QLDT_DUTOAN_DAOTAOCT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtDutoanDaotaoct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dutoandtctid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dutoandtid", insertable = false, updatable = false)
    @JsonIgnoreProperties("duToanDaotaoCts")
    private QldtDutoanDaotao qldtDutoanDaotao;
    
    @ManyToOne
    @JoinColumn(name = "noidungid", insertable = false, updatable = false)
    @JsonIgnoreProperties("duToandaotaoCts")
    private QldtDmNoidung qldtDmNoidung;
    @Column(name = "soluong")
    private Integer soluong;

    @Column(name = "mucchi")
    private Integer mucchi;

    @Column(name = "thanhtien")
    private Integer thanhtien;

    @Column(name = "noidung")
    private String noidung;

    @Column(name = "trangthaict")
    private Integer trangthaict;

    @Column(name = "dathanhtoan")
    private Integer dathanhtoan;

    @Column(name = "sudung")
    private Integer sudung;

  

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public QldtDutoanDaotaoct soluong(Integer soluong) {
        this.soluong = soluong;
        return this;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Integer getMucchi() {
        return mucchi;
    }

    public QldtDutoanDaotaoct mucchi(Integer mucchi) {
        this.mucchi = mucchi;
        return this;
    }

    public void setMucchi(Integer mucchi) {
        this.mucchi = mucchi;
    }

    public Integer getThanhtien() {
        return thanhtien;
    }

    public QldtDutoanDaotaoct thanhtien(Integer thanhtien) {
        this.thanhtien = thanhtien;
        return this;
    }

    public void setThanhtien(Integer thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getNoidung() {
        return noidung;
    }

    public QldtDutoanDaotaoct noidung(String noidung) {
        this.noidung = noidung;
        return this;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Integer getTrangthaict() {
        return trangthaict;
    }

    public QldtDutoanDaotaoct trangthaict(Integer trangthaict) {
        this.trangthaict = trangthaict;
        return this;
    }

    public void setTrangthaict(Integer trangthaict) {
        this.trangthaict = trangthaict;
    }

    public Integer getDathanhtoan() {
        return dathanhtoan;
    }

    public QldtDutoanDaotaoct dathanhtoan(Integer dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
        return this;
    }

    public void setDathanhtoan(Integer dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtDutoanDaotaoct sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public QldtDutoanDaotao getQldtDutoanDaotao() {
        return qldtDutoanDaotao;
    }

    public QldtDutoanDaotaoct qldtDutoanDaotao(QldtDutoanDaotao qldtDutoanDaotao) {
        this.qldtDutoanDaotao = qldtDutoanDaotao;
        return this;
    }

    public void setQldtDutoanDaotao(QldtDutoanDaotao qldtDutoanDaotao) {
        this.qldtDutoanDaotao = qldtDutoanDaotao;
    }

    public QldtDmNoidung getQldtDmNoidung() {
        return qldtDmNoidung;
    }

    public QldtDutoanDaotaoct qldtDmNoidung(QldtDmNoidung qldtDmNoidung) {
        this.qldtDmNoidung = qldtDmNoidung;
        return this;
    }

    public void setQldtDmNoidung(QldtDmNoidung qldtDmNoidung) {
        this.qldtDmNoidung = qldtDmNoidung;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtDutoanDaotaoct)) {
            return false;
        }
        return id != null && id.equals(((QldtDutoanDaotaoct) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtDutoanDaotaoct{" + "id=" + getId() + ", soluong=" + getSoluong() + ", mucchi=" + getMucchi()
                + ", thanhtien=" + getThanhtien() + ", noidung='" + getNoidung() + "'" + ", trangthaict="
                + getTrangthaict() + ", dathanhtoan=" + getDathanhtoan() + ", sudung=" + getSudung() + "}";
    }
}
