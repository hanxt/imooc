package com.zimug.imooc.websky.common.model;

public class SysOrg {
    private Integer id;

    private Integer orgPid;

    private String orgPids;

    private Byte isLeaf;

    private String orgName;

    private String address;

    private String phone;

    private String email;

    private Byte sort;

    private Byte level;

    private Byte status;

    private String spareOne;

    private String spareTwo;

    private String spareThree;

    private String spareFour;

    private String spareFive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgPid() {
        return orgPid;
    }

    public void setOrgPid(Integer orgPid) {
        this.orgPid = orgPid;
    }

    public String getOrgPids() {
        return orgPids;
    }

    public void setOrgPids(String orgPids) {
        this.orgPids = orgPids;
    }

    public Byte getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Byte isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getSpareOne() {
        return this.spareOne;
    }

    public void setSpareOne(String spareOne) {
        this.spareOne = spareOne;
    }

    public String getSpareTwo() {
        return this.spareTwo;
    }

    public void setSpareTwo(String spareTwo) {
        this.spareTwo = spareTwo;
    }

    public String getSpareThree() {
        return this.spareThree;
    }

    public void setSpareThree(String spareThree) {
        this.spareThree = spareThree;
    }

    public String getSpareFour() {
        return this.spareFour;
    }

    public void setSpareFour(String spareFour) {
        this.spareFour = spareFour;
    }

    public String getSpareFive() {
        return this.spareFive;
    }

    public void setSpareFive(String spareFive) {
        this.spareFive = spareFive;
    }
}