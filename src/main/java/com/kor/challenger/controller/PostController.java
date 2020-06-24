package com.kor.challenger.controller;

import com.kor.challenger.domain.Content;
import com.kor.challenger.domain.Post;
import com.kor.challenger.exeptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("post")
public class PostController {
    private int counter = 4;

    /*private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "First message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Second message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Third message");
        }});
    }};*/

    private List<String> pathImageName = new ArrayList<>();

    /*private List<Post> posts = new ArrayList<Post>() {{
        add(new Post(0L, "One"));
        add(new Post(1L, "Two"));
        add(new Post(2L, "Tree"));
    }};*/
    private List<Post> posts = new ArrayList<Post>();

    @Value("${upload.path}")
    private String uploadPath;

    int postCount = 0;

    @GetMapping
    public List<Post> getListPosts() {
        System.out.println("Get all success");

        return posts;
    }

    @GetMapping("{id}")
    public Post getOnePost(@PathVariable("id") Long id) {
        System.out.println("Get one success");
        System.out.println("id   =   " + id);
        return getPost(id);
    }

    private Post getPost(@PathVariable Long id) {
       /* return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);*/
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Post createPost(@RequestParam("text") String text, @RequestParam("file") MultipartFile file) throws IOException {

        System.out.println("Post success  " + postCount);
        postCount++;
        System.out.println(text);
        System.out.println(file.getOriginalFilename());

        long postId = posts.size();
        Post post = new Post(postId, text);

        Content[] postContents = post.getContents();
        String resultFilename = saveFile(file);
        postContents[0] = new Content(resultFilename);

        post.setContents(postContents);

        posts.add(post);

        return post;
    }

    private String saveFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists() && !file.getOriginalFilename().isEmpty()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();

            System.out.println(file.getSize());
            System.out.println(file.getContentType());

            int index = file.getOriginalFilename().lastIndexOf(".");
            String ext = file.getOriginalFilename().substring(index);
            String name = file.getOriginalFilename();

            if(".dat".equals(ext)){
                name = file.getOriginalFilename().substring(0,index)+".jpg";
            }

            String resultFilename = (uuidFile + "." + name).replace("\\s", "");
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            return resultFilename;
        }

        return null;
    }

    /*@PostMapping
    public Post create(@RequestBody Post post) {
        System.out.println("Post success");
        System.out.println(post.getId());
        System.out.println(post.getText());

        posts.add(post);

        return post;
    }*/

    @PutMapping("{id}")
    public Post editPost(@PathVariable Long id, @RequestBody Post post) {
        System.out.println("put success");
        System.out.println(post.getId());
        System.out.println(post.getText());

        Post postTemp = getPost(id);
        int indexPost = posts.indexOf(postTemp);
        post.setId(postTemp.getId());
        posts.set(indexPost, post);

        return post;
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id) {
        System.out.println("delete success");
        System.out.println(id);

        Post postTemp = getPost(id);
        int indexPost = posts.indexOf(postTemp);

        posts.remove(indexPost);
    }
}
