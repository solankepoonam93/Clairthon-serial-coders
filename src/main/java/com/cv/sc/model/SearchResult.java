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

    @Column(columnDefinition = "MEDIUMTEXT")
    private String jsonResult;

    public Long getId() {
        return id;
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

    public SearchParent getSearchParent() {
        return searchParent;
    }

    public void setSearchParent(SearchParent searchParent) {
        this.searchParent = searchParent;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id=" + id +
                ", queryUrl='" + queryUrl + '\'' +
                ", searchParent=" + searchParent +
                ", jsonResult='" + jsonResult + '\'' +
                '}';
    }
}
