package com.animal.AnimalLove.data.dto;

import com.animal.AnimalLove.data.entity.Image;
import com.animal.AnimalLove.data.entity.Post;

public record ImageDto(
        String url,
        String publicId,
        Long postId
) {


    public Image toEntity(Post post){
            return Image.builder()
                    .url(url)
                    .publicId(publicId)
                    .post(post)
                    .build();

        }

    public static ImageDto of(String url, String publicId, Long postId){

        return new ImageDto(url, publicId, postId);
    }


    public static ImageDto from(Image image){
            return new ImageDto(
                    image.getUrl(),
                    image.getPublicId(),
                    image.getPost().getPostId()
            );
    }

}
