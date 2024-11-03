package com.animal.AnimalLove.data.request;

import com.animal.AnimalLove.data.dto.PostDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostCreationRequest {

    private Long userId;

    private PostDto postDto;

}
