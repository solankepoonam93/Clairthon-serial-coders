package com.cv.sc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class SearchResult implements SCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queryUrl;

    @NotNull
    @ManyToOne
    private SearchParent searchParent;

    @Column(length = 512)
    private String s3FileUrl;

    @Column
    private String fileName;

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getS3FileUrl() {
        return s3FileUrl;
    }

    public void setS3FileUrl(String s3FileUrl) {
        this.s3FileUrl = s3FileUrl;
    }

    public SearchParent getSearchParent() {
        return searchParent;
    }

    public void setSearchParent(SearchParent searchParent) {
        this.searchParent = searchParent;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id=" + id +
                ", queryUrl='" + queryUrl + '\'' +
                ", searchParent=" + searchParent +
                ", jsonResult='" + s3FileUrl + '\'' +
                '}';
    }
}
