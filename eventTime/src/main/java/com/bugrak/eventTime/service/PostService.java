package com.bugrak.eventTime.service;

import com.bugrak.eventTime.dto.PostDto;
import com.bugrak.eventTime.model.Post;
import com.bugrak.eventTime.model.User;
import com.bugrak.eventTime.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public Post savePost(Post post,Integer userId){
        Optional<User> userOptional = userService.findById(userId);

        if (userOptional.isPresent()){
            post.setUser(userOptional.get());
            return postRepository.save(post);
        }
        else {
            return null;
        }
    }

    public List<PostDto> getAllPosts(){
        return postRepository.findAll().stream()
                .map(post -> new PostDto(
                        post.getContent(),
                        post.getEventTime(),
                        post.getImage(),
                        post.getUser().getName(),
                        post.getCity().getName()
                ))
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsByEventTime(LocalDate eventTime) {
        return postRepository.findAll().stream()
                .filter(post -> post.getEventTime().equals(eventTime)) // Belirli bir tarih ile eşleşenleri filtrele
                .map(post -> new PostDto(
                        post.getContent(),
                        post.getEventTime(),
                        post.getImage(),
                        post.getUser().getName(),
                        post.getCity().getName()
                ))
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsByCityId(Integer cityId) {
        return postRepository.findAll().stream()
                .filter(post -> post.getCity().getId().equals(cityId)) // Belirli bir şehir ID'si ile eşleşenleri filtrele
                .map(post -> new PostDto(
                        post.getContent(),
                        post.getEventTime(),
                        post.getImage(),
                        post.getUser().getName(),
                        post.getCity().getName()
                ))
                .collect(Collectors.toList());
    }


}
