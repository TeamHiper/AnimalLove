package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Image;
import com.animal.AnimalLove.data.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public record ImageDto(
        String url,
        String publicId,
        Post post
) {


    public Image toEntity(){
            return Image.builder()
                    .url(url)
                    .publicId(publicId)
                    .post(post)
                    .build();

        }

    public static ImageDto of(String url, Post post, String publicId){
        return new ImageDto(url, publicId, post);
    }

    public static ImageDto from(Image image){
            return new ImageDto(
                    image.getUrl(),
                    image.getPublicId(),
                    image.getPost()
            );
    }

}
