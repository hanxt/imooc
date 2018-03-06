package com.zimug.imooc.websky.common.model;

public class SysDb {
    private Integer id;

    private String dbFlag;

    private String dbName;

    private String dbType;

    private String dbRw;

    private String conUrl;

    private String conIp;

    private Integer conPort;

    private String conUsername;

    private String conPassword;

    private Integer conTimeout;

    private Integer poolMaxtotal;

    private Integer poolMaxidle;

    private Integer poolMinidle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDbFlag() {
        return dbFlag;
    }

    public void setDbFlag(String dbFlag) {
        this.dbFlag = dbFlag;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbRw() {
        return dbRw;
    }

    public void setDbRw(String dbRw) {
        this.dbRw = dbRw;
    }

    public String getConUrl() {
        return conUrl;
    }

    public void setConUrl(String conUrl) {
        this.conUrl = conUrl;
    }

    public String getConIp() {
        return conIp;
    }

    public void setConIp(String conIp) {
        this.conIp = conIp;
    }

    public Integer getConPort() {
        return conPort;
    }

    public void setConPort(Integer conPort) {
        this.conPort = conPort;
    }

    public String getConUsername() {
        return conUsername;
    }

    public void setConUsername(String conUsername) {
        this.conUsername = conUsername;
    }

    public String getConPassword() {
        return conPassword;
    }

    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }

    public Integer getConTimeout() {
        return conTimeout;
    }

    public void setConTimeout(Integer conTimeout) {
        this.conTimeout = conTimeout;
    }

    public Integer getPoolMaxtotal() {
        return poolMaxtotal;
    }

    public void setPoolMaxtotal(Integer poolMaxtotal) {
        this.poolMaxtotal = poolMaxtotal;
    }

    public Integer getPoolMaxidle() {
        return poolMaxidle;
    }

    public void setPoolMaxidle(Integer poolMaxidle) {
        this.poolMaxidle = poolMaxidle;
    }

    public Integer getPoolMinidle() {
        return poolMinidle;
    }

    public void setPoolMinidle(Integer poolMinidle) {
        this.poolMinidle = poolMinidle;
    }
}