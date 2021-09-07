package com.template.freelance.Beans;

import lombok.Data;

@Data
public class DropDownBean<T> {

    private T id;
    private String code;
    private String code2;
    private String desc;
    private String itemName;

    public DropDownBean() {
    }

    public DropDownBean(T id, String code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
        this.itemName = desc;
    }

    public DropDownBean(T id, String code, String code2, String desc) {
        this.id = id;
        this.code = code;
        this.code2 = code2;
        this.desc = desc;
    }

}