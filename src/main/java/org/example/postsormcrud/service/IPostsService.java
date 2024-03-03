package org.example.postsormcrud.service;

import org.example.postsormcrud.model.Posts;

import java.util.List;

public interface IPostsService {
    List<Posts> findAll();

    void save(Posts posts);
    Posts findById(int id);
}
