package com.animal.AnimalLove.constants;

public class ApiUrlConstants {

    public static final String API = "/api";
    public static final String V1 = "/v1";
    public static final String USER = "/user";
    public static final String POST = "/post";
    public static final String COMMENT = "/comment";
    public static final String BOOKMARK = "/bookmark";
    public static final String FOLLOW = "/follow";
    public static final String IMAGE = "/image";

    public static final String API_V1 = API + V1;

    // 유저관련
    public static final String API_V1_USER = API + V1 + USER;
    public static final String API_V1_USER_REGISTER = API + V1 + USER + "/register";
    // 게시물 관련
    public static final String API_V1_POST = API + V1 + POST;
    public static final String API_V1_POST_REGISTER = API + V1 + POST + "/register";
    // 코멘트 관련
    public static final String API_V1_COMMENT = API + V1 + COMMENT;
    // 북마크 관련
    public static final String API_V1_BOOKMARK = API + V1 + BOOKMARK;
    // 팔로우 관련
    public static final String API_V1_FOLLOW = API + V1 + FOLLOW;
    // 이미지 관련
    public static final String API_V1_IMAGE = API + V1 + IMAGE;
    public static final String API_V1_IMAGE_UPLOAD = API + V1 + IMAGE + "/upload";

}
