package com.vnptit.vnpthis.domain;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "ADM_USER", schema = "PM2_FRESHER", catalog = "")
public class AdmUser {
    @Id
    private Long userId;
    private String userName;
    private String userPwd;
    private String fullName;
    private Long userGroupId;
    private Short userLevel;
    private Long officerId;
    private Short encryptCheck;
    private String note;
    private Long companyId;
    private Boolean status;
    private Time dateSetPass;
    private String invoicesUser;
    private String invoicesPass;
    private String userPwdBk;
    private Boolean isLock;
    private Long countLogin;
    private String email;

    @Basic
    @Column(name = "USER_ID", nullable = true, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = true, length = 200)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "USER_PWD", nullable = true, length = 200)
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Basic
    @Column(name = "FULL_NAME", nullable = true, length = 200)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "USER_GROUP_ID", nullable = true, precision = 0)
    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Basic
    @Column(name = "USER_LEVEL", nullable = true, precision = 0)
    public Short getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Short userLevel) {
        this.userLevel = userLevel;
    }

    @Basic
    @Column(name = "OFFICER_ID", nullable = true, precision = 0)
    public Long getOfficerId() {
        return officerId;
    }

    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    @Basic
    @Column(name = "ENCRYPT_CHECK", nullable = true, precision = 0)
    public Short getEncryptCheck() {
        return encryptCheck;
    }

    public void setEncryptCheck(Short encryptCheck) {
        this.encryptCheck = encryptCheck;
    }

    @Basic
    @Column(name = "NOTE", nullable = true, length = 100)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "COMPANY_ID", nullable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, precision = 0)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "DATE_SET_PASS", nullable = true)
    public Time getDateSetPass() {
        return dateSetPass;
    }

    public void setDateSetPass(Time dateSetPass) {
        this.dateSetPass = dateSetPass;
    }

    @Basic
    @Column(name = "INVOICES_USER", nullable = true, length = 30)
    public String getInvoicesUser() {
        return invoicesUser;
    }

    public void setInvoicesUser(String invoicesUser) {
        this.invoicesUser = invoicesUser;
    }

    @Basic
    @Column(name = "INVOICES_PASS", nullable = true, length = 30)
    public String getInvoicesPass() {
        return invoicesPass;
    }

    public void setInvoicesPass(String invoicesPass) {
        this.invoicesPass = invoicesPass;
    }

    @Basic
    @Column(name = "USER_PWD_BK", nullable = true, length = 200)
    public String getUserPwdBk() {
        return userPwdBk;
    }

    public void setUserPwdBk(String userPwdBk) {
        this.userPwdBk = userPwdBk;
    }

    @Basic
    @Column(name = "IS_LOCK", nullable = true, precision = 0)
    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean lock) {
        isLock = lock;
    }

    @Basic
    @Column(name = "COUNT_LOGIN", nullable = true, precision = 0)
    public Long getCountLogin() {
        return countLogin;
    }

    public void setCountLogin(Long countLogin) {
        this.countLogin = countLogin;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdmUser admUser = (AdmUser) o;

        if (userId != null ? !userId.equals(admUser.userId) : admUser.userId != null) return false;
        if (userName != null ? !userName.equals(admUser.userName) : admUser.userName != null) return false;
        if (userPwd != null ? !userPwd.equals(admUser.userPwd) : admUser.userPwd != null) return false;
        if (fullName != null ? !fullName.equals(admUser.fullName) : admUser.fullName != null) return false;
        if (userGroupId != null ? !userGroupId.equals(admUser.userGroupId) : admUser.userGroupId != null) return false;
        if (userLevel != null ? !userLevel.equals(admUser.userLevel) : admUser.userLevel != null) return false;
        if (officerId != null ? !officerId.equals(admUser.officerId) : admUser.officerId != null) return false;
        if (encryptCheck != null ? !encryptCheck.equals(admUser.encryptCheck) : admUser.encryptCheck != null)
            return false;
        if (note != null ? !note.equals(admUser.note) : admUser.note != null) return false;
        if (companyId != null ? !companyId.equals(admUser.companyId) : admUser.companyId != null) return false;
        if (status != null ? !status.equals(admUser.status) : admUser.status != null) return false;
        if (dateSetPass != null ? !dateSetPass.equals(admUser.dateSetPass) : admUser.dateSetPass != null) return false;
        if (invoicesUser != null ? !invoicesUser.equals(admUser.invoicesUser) : admUser.invoicesUser != null)
            return false;
        if (invoicesPass != null ? !invoicesPass.equals(admUser.invoicesPass) : admUser.invoicesPass != null)
            return false;
        if (userPwdBk != null ? !userPwdBk.equals(admUser.userPwdBk) : admUser.userPwdBk != null) return false;
        if (isLock != null ? !isLock.equals(admUser.isLock) : admUser.isLock != null) return false;
        if (countLogin != null ? !countLogin.equals(admUser.countLogin) : admUser.countLogin != null) return false;
        if (email != null ? !email.equals(admUser.email) : admUser.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPwd != null ? userPwd.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (userGroupId != null ? userGroupId.hashCode() : 0);
        result = 31 * result + (userLevel != null ? userLevel.hashCode() : 0);
        result = 31 * result + (officerId != null ? officerId.hashCode() : 0);
        result = 31 * result + (encryptCheck != null ? encryptCheck.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dateSetPass != null ? dateSetPass.hashCode() : 0);
        result = 31 * result + (invoicesUser != null ? invoicesUser.hashCode() : 0);
        result = 31 * result + (invoicesPass != null ? invoicesPass.hashCode() : 0);
        result = 31 * result + (userPwdBk != null ? userPwdBk.hashCode() : 0);
        result = 31 * result + (isLock != null ? isLock.hashCode() : 0);
        result = 31 * result + (countLogin != null ? countLogin.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
