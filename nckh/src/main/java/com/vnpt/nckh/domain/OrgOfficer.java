package com.vnpt.nckh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * OrgOfficer entity.\n@author VINHHC.
 */
@ApiModel(description = "OrgOfficer entity.\n@author VINHHC.")
@Entity
@Table(name = "org_officer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrgOfficer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "officer_type")
    private Long officerType;

    @Size(max = 255)
    @Column(name = "officer_code", length = 255)
    private String officerCode;

    @Size(max = 255)
    @Column(name = "officer_name", length = 255)
    private String officerName;

    @Size(max = 500)
    @Column(name = "email", length = 500)
    private String email;

    @Size(max = 4000)
    @Column(name = "note", length = 4000)
    private String note;

    @Column(name = "trinh_do")
    private String trinhDo;

    @Column(name = "chuc_danh")
    private String chucDanh;

    @Column(name = "ma_bac_si")
    private String maBacSi;

    @Column(name = "hoc_ham")
    private String hocHam;

    @Column(name = "hoc_vi")
    private String hocVi;

    @Column(name = "phone")
    private String phone;

    @Column(name = "opt")
    private String opt;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "ngay_sinh")
    private Instant ngaySinh;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "sub_dept_id")
    private Long subDeptId;

    @OneToMany(mappedBy = "orgOfficer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NckhNhanSu> nckhNhanSus = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("orgOfficers")
    private OrgOrganization orgOrganization;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfficerType() {
        return officerType;
    }

    public OrgOfficer officerType(Long officerType) {
        this.officerType = officerType;
        return this;
    }

    public void setOfficerType(Long officerType) {
        this.officerType = officerType;
    }

    public String getOfficerCode() {
        return officerCode;
    }

    public OrgOfficer officerCode(String officerCode) {
        this.officerCode = officerCode;
        return this;
    }

    public void setOfficerCode(String officerCode) {
        this.officerCode = officerCode;
    }

    public String getOfficerName() {
        return officerName;
    }

    public OrgOfficer officerName(String officerName) {
        this.officerName = officerName;
        return this;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getEmail() {
        return email;
    }

    public OrgOfficer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public OrgOfficer note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public OrgOfficer trinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
        return this;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getChucDanh() {
        return chucDanh;
    }

    public OrgOfficer chucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
        return this;
    }

    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }

    public String getMaBacSi() {
        return maBacSi;
    }

    public OrgOfficer maBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
        return this;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getHocHam() {
        return hocHam;
    }

    public OrgOfficer hocHam(String hocHam) {
        this.hocHam = hocHam;
        return this;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    public String getHocVi() {
        return hocVi;
    }

    public OrgOfficer hocVi(String hocVi) {
        this.hocVi = hocVi;
        return this;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public String getPhone() {
        return phone;
    }

    public OrgOfficer phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpt() {
        return opt;
    }

    public OrgOfficer opt(String opt) {
        this.opt = opt;
        return this;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public OrgOfficer diaChi(String diaChi) {
        this.diaChi = diaChi;
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Instant getNgaySinh() {
        return ngaySinh;
    }

    public OrgOfficer ngaySinh(Instant ngaySinh) {
        this.ngaySinh = ngaySinh;
        return this;
    }

    public void setNgaySinh(Instant ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public OrgOfficer gioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Long getDeptId() {
        return deptId;
    }

    public OrgOfficer deptId(Long deptId) {
        this.deptId = deptId;
        return this;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getSubDeptId() {
        return subDeptId;
    }

    public OrgOfficer subDeptId(Long subDeptId) {
        this.subDeptId = subDeptId;
        return this;
    }

    public void setSubDeptId(Long subDeptId) {
        this.subDeptId = subDeptId;
    }

    public Set<NckhNhanSu> getNckhNhanSus() {
        return nckhNhanSus;
    }

    public OrgOfficer nckhNhanSus(Set<NckhNhanSu> nckhNhanSus) {
        this.nckhNhanSus = nckhNhanSus;
        return this;
    }

    public OrgOfficer addNckhNhanSu(NckhNhanSu nckhNhanSu) {
        this.nckhNhanSus.add(nckhNhanSu);
        nckhNhanSu.setOrgOfficer(this);
        return this;
    }

    public OrgOfficer removeNckhNhanSu(NckhNhanSu nckhNhanSu) {
        this.nckhNhanSus.remove(nckhNhanSu);
        nckhNhanSu.setOrgOfficer(null);
        return this;
    }

    public void setNckhNhanSus(Set<NckhNhanSu> nckhNhanSus) {
        this.nckhNhanSus = nckhNhanSus;
    }

    public OrgOrganization getOrgOrganization() {
        return orgOrganization;
    }

    public OrgOfficer orgOrganization(OrgOrganization orgOrganization) {
        this.orgOrganization = orgOrganization;
        return this;
    }

    public void setOrgOrganization(OrgOrganization orgOrganization) {
        this.orgOrganization = orgOrganization;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrgOfficer)) {
            return false;
        }
        return id != null && id.equals(((OrgOfficer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrgOfficer{" +
            "id=" + getId() +
            ", officerType=" + getOfficerType() +
            ", officerCode='" + getOfficerCode() + "'" +
            ", officerName='" + getOfficerName() + "'" +
            ", email='" + getEmail() + "'" +
            ", note='" + getNote() + "'" +
            ", trinhDo='" + getTrinhDo() + "'" +
            ", chucDanh='" + getChucDanh() + "'" +
            ", maBacSi='" + getMaBacSi() + "'" +
            ", hocHam='" + getHocHam() + "'" +
            ", hocVi='" + getHocVi() + "'" +
            ", phone='" + getPhone() + "'" +
            ", opt='" + getOpt() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", ngaySinh='" + getNgaySinh() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", deptId=" + getDeptId() +
            ", subDeptId=" + getSubDeptId() +
            "}";
    }
}
