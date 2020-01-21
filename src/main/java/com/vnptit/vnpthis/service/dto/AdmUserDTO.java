package com.vnptit.vnpthis.service.dto;

import com.vnptit.vnpthis.domain.AdmUser;
import com.vnptit.vnpthis.security.AuthoritiesConstants;

import java.util.List;

import static java.util.Arrays.asList;

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
    private String login = AuthoritiesConstants.ADMIN;
    private boolean activated = true;
    private String langKey = "en";
    private String createdBy = "system";
    private List<String> authorities = asList(AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN);

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

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
