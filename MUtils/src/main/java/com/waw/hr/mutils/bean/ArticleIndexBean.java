package com.waw.hr.mutils.bean;

import java.util.List;

public class ArticleIndexBean {
    /**
     * article_authoratize : 1
     * num : 10
     * current : 1
     * totalPage : 2
     * totalNum : 14
     */

    private int article_authoratize;
    private int num;
    private String current;
    private int totalPage;
    private int totalNum;
    private List<ArticleModel> list;

    public List<ArticleModel> getList() {
        return list;
    }

    public void setList(List<ArticleModel> list) {
        this.list = list;
    }

    public int getArticle_authoratize() {
        return article_authoratize;
    }

    public void setArticle_authoratize(int article_authoratize) {
        this.article_authoratize = article_authoratize;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
