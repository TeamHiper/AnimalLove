package com.animal.AnimalLove.data.dto;


import com.animal.AnimalLove.data.entity.Like;

import java.time.LocalDateTime;

public record LikeResponseDto(
            Long likeId,
            Long postId,
            LocalDateTime updatedDate
    ) {
        // Like 엔티티를 LikeDto로 변환
        public static LikeResponseDto from(Like like) {
            return new LikeResponseDto(
                    like.getLikeId(),                    // Like의 ID
                    like.getPost().getPostId(),          // Post의 ID
                    like.getUpdatedDttm()            // Like의 마지막 수정 날짜
            );
        }
    }

