package com.example.newsclientdemo.data;

/**
 * Created by 雪无痕 on 2017/5/20.
 */

public class NovelBean {

    private String novel_name;//书名
    private String novel_image;//标题图
    private String  novel_author;//作者
    private String novel_type;//类型
    private String novel_path;//路径

    public String getNovel_name() {
        return novel_name;
    }

    public void setNovel_name(String novel_name) {
        this.novel_name = novel_name;
    }

    public String getNovel_image() {
        return novel_image;
    }

    public void setNovel_image(String novel_image) {
        this.novel_image = novel_image;
    }

    public String getNovel_author() {
        return novel_author;
    }

    public void setNovel_author(String novel_author) {
        this.novel_author = novel_author;
    }

    public String getNovel_type() {
        return novel_type;
    }

    public void setNovel_type(String novel_type) {
        this.novel_type = novel_type;
    }

    public String getNovel_path() {
        return novel_path;
    }

    public void setNovel_path(String novel_path) {
        this.novel_path = novel_path;
    }
    public NovelBean(){}
    public NovelBean(String novel_name, String novel_image, String novel_author, String novel_type, String novel_path) {
        this.novel_name = novel_name;
        this.novel_image = novel_image;
        this.novel_author = novel_author;
        this.novel_type = novel_type;
        this.novel_path = novel_path;
    }

    @Override
    public String toString() {
        return "NovelBean{" +
                "novel_autor='" + novel_author + '\'' +
                ", novel_name='" + novel_name + '\'' +
                ", novel_image='" + novel_image + '\'' +
                ", novel_type='" + novel_type + '\'' +
                ", novel_path='" + novel_path + '\'' +
                '}';
    }
}
