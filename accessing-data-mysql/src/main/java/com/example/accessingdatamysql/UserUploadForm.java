package com.example.accessingdatamysql;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class UserUploadForm {
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @NotNull
    private MultipartFile multipartFile;
    @NotNull
    private String name;
    @NotNull
    private String comments;
}
