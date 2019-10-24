package com.waw.hr.mutils.bean;

import java.util.List;

public class ArticleModel {
    /**
     * a_id : 54
     * a_image : ["https://zonghongkeji.cn/VillageTao/public/uploads/20191024/691074002616b2ccc35419f0e5e32a13.jpg"]
     * a_content : Qwewqieywquiehqwjewqewqrwqrwqrwqewqewqewqewqe
     * a_browse : 0
     * a_time : 2019-10-24 10:36:35
     * user_id : 95
     * u_name : 测试流程
     * u_avatar : https://zonghongkeji.cn/VillageTao/public/uploads/20191024/59100daa5b9dafc235ba66a194347b82.png
     */

    private int a_id;
    private String a_content;
    private int a_browse;
    private String a_time;
    private int user_id;
    private String u_name;
    private String u_avatar;
    private List<String> a_image;

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_content() {
        return a_content;
    }

    public void setA_content(String a_content) {
        this.a_content = a_content;
    }

    public int getA_browse() {
        return a_browse;
    }

    public void setA_browse(int a_browse) {
        this.a_browse = a_browse;
    }

    public String getA_time() {
        return a_time;
    }

    public void setA_time(String a_time) {
        this.a_time = a_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_avatar() {
        return u_avatar;
    }

    public void setU_avatar(String u_avatar) {
        this.u_avatar = u_avatar;
    }

    public List<String> getA_image() {
        return a_image;
    }

    public void setA_image(List<String> a_image) {
        this.a_image = a_image;
    }
}
