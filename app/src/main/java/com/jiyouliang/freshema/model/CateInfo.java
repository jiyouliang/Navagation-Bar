package com.jiyouliang.freshema.model;

/**
 * 分类信息：
 * Created by JiYouLiang on 2018/10/25.
 */

public class CateInfo {
    private int resId;
    private String title;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CateInfo{" +
                "resId=" + resId +
                ", title='" + title + '\'' +
                '}';
    }
}
