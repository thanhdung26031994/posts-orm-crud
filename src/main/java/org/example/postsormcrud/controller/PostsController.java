package org.example.postsormcrud.controller;

import org.example.postsormcrud.model.Posts;
import org.example.postsormcrud.model.PostsForm;
import org.example.postsormcrud.service.IPostsService;
import org.example.postsormcrud.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/posts")
@PropertySource("classpath:upload_file.properties")
public class PostsController {
    @Value("${upload}")
    private String upload;

    private IPostsService postsService = new PostsService();
    @GetMapping("")
    public String index(Model model) {
        List<Posts> postsList = postsService.findAll();
        model.addAttribute("posts", postsList);
        return "/index";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("posts", new PostsForm());
        return "/create";
    }
    @PostMapping("/save")
    public String save(PostsForm postsForm) throws IOException {
        MultipartFile file = postsForm.getImg();
        String nameImg = file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File(upload + nameImg));
        Posts posts = new Posts();
        posts.setCode(postsForm.getCode());
        posts.setTitle(postsForm.getTitle());
        posts.setContent(postsForm.getContent());
        posts.setDescription(postsForm.getDescription());
        posts.setImg(nameImg);
        postsService.save(posts);
        return "redirect:/posts";
    }
}
