package com.jacky.r2dbc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("api_user_app")
public class ApiUserApp {

    private static final long serialVersionUID = -558043294043707772L;

    @Id
    private Long id;

    private String appId;
    private String status;
    private String checkIpStatus;
    private String checkMethodStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckIpStatus() {
        return checkIpStatus;
    }

    public void setCheckIpStatus(String checkIpStatus) {
        this.checkIpStatus = checkIpStatus;
    }

    public String getCheckMethodStatus() {
        return checkMethodStatus;
    }

    public void setCheckMethodStatus(String checkMethodStatus) {
        this.checkMethodStatus = checkMethodStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ApiUserApp{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", status='" + status + '\'' +
                ", checkIpStatus='" + checkIpStatus + '\'' +
                ", checkMethodStatus='" + checkMethodStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
