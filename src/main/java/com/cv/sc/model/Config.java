package com.cv.sc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class Config implements SCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String configName;

    private String[] codeSearchKeywords;

    private String[] userSearchKeywords;

    private String[] repositoryNames;

    private String[] fileNames;

    private String customerName;

    private String modifiedBy;

    @NotNull
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    private Boolean isScheduled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String congigName) {
        this.configName = congigName;
    }

    public String[] getCodeSearchKeywords() {
        return codeSearchKeywords;
    }

    public void setCodeSearchKeywords(String[] codeSearchKeywords) {
        this.codeSearchKeywords = codeSearchKeywords;
    }

    public String[] getUserSearchKeywords() {
        return userSearchKeywords;
    }

    public void setUserSearchKeywords(String[] userSearchKeywords) {
        this.userSearchKeywords = userSearchKeywords;
    }

    public String[] getRepositoryNames() {
        return repositoryNames;
    }

    public void setRepositoryNames(String[] repositoryNames) {
        this.repositoryNames = repositoryNames;
    }

    public String[] getFileNames() {
        return fileNames;
    }

    public void setFileNames(String[] fileNames) {
        this.fileNames = fileNames;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", configName='" + configName + '\'' +
                ", codeSearchKeywords='" + Arrays.toString(codeSearchKeywords) + '\'' +
                ", userSearchKeywords='" + Arrays.toString(userSearchKeywords) + '\'' +
                ", repositoryNames='" + Arrays.toString(repositoryNames) + '\'' +
                ", classNames='" + Arrays.toString(fileNames) + '\'' +
                ", customerName='" + customerName + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", isScheduled=" + isScheduled +
                '}';
    }


    public Boolean getScheduled() {
        return isScheduled;
    }

    public void setScheduled(Boolean scheduled) {
        isScheduled = scheduled;
    }
}
