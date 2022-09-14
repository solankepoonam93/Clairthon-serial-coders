package com.cv.sc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class SearchResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String queryUrl;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private SearchParent searchParent;

    private String jsonResult;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
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
                ", jsonResult='" + jsonResult + '\'' +
                '}';
    }
}
