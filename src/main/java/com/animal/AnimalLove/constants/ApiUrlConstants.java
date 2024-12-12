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
    public static final String LIKE = "/like";

    public static final String REGISTER = "/register";
    public static final String UPDATE= "/update";
    public static final String DELETE= "/delete";
    public static final String DETAIL= "/detail";
    public static final String LIST= "/list";


    public static final String API_V1 = API + V1;

    // 유저관련
    public static final String API_V1_USER = API_V1 + USER;
    public static final String API_V1_USER_REGISTER = API_V1_USER + REGISTER;
    // 게시물 관련
    public static final String API_V1_POST = API_V1 + POST;
    public static final String API_V1_POST_REGISTER = API_V1_POST + REGISTER;
    public static final String API_V1_POST_DETAIL = API_V1_POST + DETAIL;
    public static final String API_V1_POST_UPDATE = API_V1_POST + UPDATE;
    public static final String API_V1_POST_LIST = API + V1 + POST + LIST;
    // 코멘트 관련
    public static final String API_V1_COMMENT = API_V1 + COMMENT;
    // 북마크 관련
    public static final String API_V1_BOOKMARK = API_V1 + BOOKMARK;
    // 팔로우 관련
    public static final String API_V1_FOLLOW = API_V1 + FOLLOW;
    // 이미지 관련
    public static final String API_V1_IMAGE = API_V1 + IMAGE;
    public static final String API_V1_IMAGE_UPLOAD = API_V1_IMAGE + "/upload";
    // 좋아요
    public static final String API_V1_LIKE_REGISTER = API+V1+LIKE + REGISTER;
    public static final String API_V1_LIKE_DELETE = API+V1+LIKE + DELETE;

}
