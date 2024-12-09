package com.animal.AnimalLove.data.repository.support;

import com.animal.AnimalLove.data.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.animal.AnimalLove.data.entity.QPost.post;

@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int updatePost(Long postId, String content, User user) {

        long updatedRows = jpaQueryFactory
                .update(post)
                .set(post.content, content)
                .where(post.postId.eq(postId).and(post.user.eq(user)))
                .execute();

        // 업데이트가 성공하면 행 수에 상관없이 1을 반환하고, 실패하면 0 반환
        return (int) updatedRows > 0 ? 1 : 0;
    }
}
