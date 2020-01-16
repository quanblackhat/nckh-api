package com.vnptit.vnpthis.service.dto;

import com.vnptit.vnpthis.domain.AdmUser;
import com.vnptit.vnpthis.security.AuthoritiesConstants;

public class AdmUserDTO {
    private String userName;
    private String fullName;
    private Long userGroupId;
    private Short userLevel;
    private Long officerId;
    private String note;
    private Long companyId;
    private String email;
    private String role;

    public AdmUserDTO(AdmUser admUser) {
        userName = admUser.getUserName();
        fullName = admUser.getFullName();
        userGroupId = admUser.getUserGroupId();
        userLevel = admUser.getUserLevel();
        officerId = admUser.getOfficerId();
        note = admUser.getNote();
        companyId = admUser.getCompanyId();
        email = admUser.getEmail();
        role = AuthoritiesConstants.ADMIN;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Short getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Short userLevel) {
        this.userLevel = userLevel;
    }

    public Long getOfficerId() {
        return officerId;
    }

    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
