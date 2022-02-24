package com.example.accessingdatamysql;

public class GithubUser {
    private String name;
    private String blog;

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }

    public GithubUser(String name, String blog) {
        this.name = name;
        this.blog = blog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
