package com.cv.sc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class SearchParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String user;

    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    private String status;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Config config;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "SearchParent{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", status='" + status + '\'' +
                ", config=" + config +
                '}';
    }
}
