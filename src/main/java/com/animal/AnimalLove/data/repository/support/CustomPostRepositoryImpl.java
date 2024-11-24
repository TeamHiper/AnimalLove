package com.animal.AnimalLove.data.repository.support;

import com.animal.AnimalLove.data.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.animal.AnimalLove.data.entity.QPost.post;

@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int updatePost(Long postId, String content, byte[] image, User user) {

        long updatedRows = jpaQueryFactory
                .update(post)
                .set(post.content, content)
                .set(post.image, image)
                .where(post.postId.eq(postId).and(post.user.eq(user)))
                .execute();

        return (int) updatedRows;
    }
}
