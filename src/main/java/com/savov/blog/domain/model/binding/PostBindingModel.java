package com.savov.blog.domain.model.binding;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PostBindingModel {

    private String title;

    private String body;

    private String category;

    private MultipartFile[] files;



    public PostBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
