package com.cv.sc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class ResultModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String queryUrl;

    @NotNull
    @Temporal(TIMESTAMP)
    private Date dateExecuted;

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

    public Date getDateExecuted() {
        return dateExecuted;
    }

    public void setDateExecuted(Date dateExecuted) {
        this.dateExecuted = dateExecuted;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "id=" + id +
                ", queryUrl='" + queryUrl + '\'' +
                ", dateExecuted=" + dateExecuted +
                ", jsonResult='" + jsonResult + '\'' +
                '}';
    }
}
