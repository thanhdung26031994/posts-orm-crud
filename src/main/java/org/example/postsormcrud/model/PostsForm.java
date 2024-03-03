package org.example.postsormcrud.model;

import org.springframework.web.multipart.MultipartFile;

public class PostsForm {
    private Integer id;
    private String code;
    private String title;
    private String content;
    private String description;
    private MultipartFile img;

    public PostsForm() {
    }

    public PostsForm(Integer id, String code, String title, String content, String description, MultipartFile img) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.content = content;
        this.description = description;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
