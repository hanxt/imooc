package com.zimug.imooc.websky.common.model;

public class SysDictionary {
    private Integer id;

    private String dicCode;

    private String dicClass;

    private String dicProperty;

    private String dicValue;

    private String dicName;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicClass() {
        return dicClass;
    }

    public void setDicClass(String dicClass) {
        this.dicClass = dicClass;
    }

    public String getDicProperty() {
        return dicProperty;
    }

    public void setDicProperty(String dicProperty) {
        this.dicProperty = dicProperty;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}