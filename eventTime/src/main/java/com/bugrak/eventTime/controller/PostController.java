package com.bugrak.eventTime.controller;

import com.bugrak.eventTime.dto.PostDto;
import com.bugrak.eventTime.model.City;
import com.bugrak.eventTime.model.Post;
import com.bugrak.eventTime.request.PostRequest;
import com.bugrak.eventTime.service.CityService;
import com.bugrak.eventTime.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CityService cityService;

    @PostMapping("/addPost")
    public ResponseEntity<?> savePost(@RequestBody PostRequest postRequest){
        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setEventTime(postRequest.getEventTime());
        post.setImage(postRequest.getImage());

        // City nesnesini bul
        City city = cityService.findById(postRequest.getCityId());  // CityService'iniz olduğunu varsayıyorum
        if (city == null) {
            return ResponseEntity.badRequest().body("Geçersiz şehir ID'si");
        }
        post.setCity(city);  // Şehri post nesnesine ata

        Post savedPost = postService.savePost(post, postRequest.getUserId());
        if (savedPost != null){
            return ResponseEntity.ok(savedPost);
        } else {
            return ResponseEntity.badRequest().body("Kategori bulunamadı");
        }
    }


    @GetMapping("/getAllPosts")
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/getPostsByEventTime/{eventTime}")
    public List<PostDto> getPostsByEventTime(@PathVariable String eventTime) {
        LocalDate date = LocalDate.parse(eventTime); // Tarih formatına dönüştür
        return postService.getPostsByEventTime(date);
    }

    @GetMapping("/getPostsByCityId/{cityId}")
    public List<PostDto> getPostsByCityId(@PathVariable Integer cityId) {
        return postService.getPostsByCityId(cityId);
    }


}
