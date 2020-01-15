package com.vnptit.vnpthis.domain;

import javax.persistence.*;

@Entity
@Table(name = "NCKH_LOAIDM", schema = "PM2_FRESHER", catalog = "")
public class NckhLoaidm {
    @Id
    private long id;
    private String ten;
    private Boolean sudung;

    @Basic
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TEN", nullable = false, length = 255)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "SUDUNG", nullable = true, precision = 0)
    public Boolean getSudung() {
        return sudung;
    }

    public void setSudung(Boolean sudung) {
        this.sudung = sudung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NckhLoaidm that = (NckhLoaidm) o;

        if (id != that.id) return false;
        if (ten != null ? !ten.equals(that.ten) : that.ten != null) return false;
        if (sudung != null ? !sudung.equals(that.sudung) : that.sudung != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (ten != null ? ten.hashCode() : 0);
        result = 31 * result + (sudung != null ? sudung.hashCode() : 0);
        return result;
    }
}
