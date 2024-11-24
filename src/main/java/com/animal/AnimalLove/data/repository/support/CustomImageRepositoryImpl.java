package com.animal.AnimalLove.data.repository.support;

import com.animal.AnimalLove.data.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.animal.AnimalLove.data.entity.QImage.image;

@RequiredArgsConstructor
public class CustomImageRepositoryImpl implements CustomImageRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int updateImage(Long id, String url, String publicId, Post post) {

        long updatedRows = jpaQueryFactory
                .update(image)
                .set(image.url, url)
                .set(image.publicId, publicId)
                .where(image.id.eq(id).and(image.post.eq(post)))
                .execute();

        // 업데이트가 성공하면 행 수에 상관없이 1을 반환하고, 실패하면 0 반환
        return (int) updatedRows > 0 ? 1 : 0;
    }
}
