package com.fh.cate.po;

import java.io.Serializable;

public class Category implements Serializable {

    private static final long serialVersionUID = 7312448130121696247L;

    private Integer id;

    private Integer fatherId;

    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
