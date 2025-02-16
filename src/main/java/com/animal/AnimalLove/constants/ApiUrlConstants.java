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
    public static final String COUNT= "/count";


    public static final String API_V1 = API + V1;

    // 유저관련
    public static final String API_V1_USER = API_V1 + USER;
    public static final String API_V1_USER_REGISTER = API_V1_USER + REGISTER;
    public static final String API_V1_USER_GETUSER = API_V1_USER + "/getUser";

    // 게시물 관련
    public static final String API_V1_POST = API_V1 + POST;
    public static final String API_V1_POST_REGISTER = API_V1_POST + REGISTER;
    public static final String API_V1_POST_DETAIL = API_V1_POST + DETAIL;
    public static final String API_V1_POST_UPDATE = API_V1_POST + UPDATE;
    public static final String API_V1_POST_LIST = API_V1_POST + LIST;
    public static final String API_V1_POST_LIST_COUNT = API_V1_POST_LIST + COUNT;
    public static final String API_V1_POST_DELETE = API_V1_POST + DELETE;
    // 코멘트 관련
    public static final String API_V1_COMMENT = API_V1 + COMMENT;
    // 북마크 관련
    public static final String API_V1_BOOKMARK = API_V1 + BOOKMARK;
    // 팔로우 관련
    public static final String API_V1_FOLLOW = API_V1 + FOLLOW;
    // 이미지 관련
    public static final String API_V1_IMAGE = API_V1 + IMAGE;
    public static final String API_V1_IMAGE_UPLOAD = API_V1_IMAGE + "/upload";
    public static final String API_V1_IMAGE_UPLOAD_PROFILE = API_V1_IMAGE + "/upload/profile";

    // 좋아요
    public static final String API_V1_LIKE = API_V1 + LIKE;
    public static final String API_V1_LIKE_CHECK = API_V1_LIKE+"/check";
    public static final String API_V1_LIKE_COUNT = API_V1_LIKE+COUNT;
    public static final String API_V1_LIKE_WHO = API_V1_LIKE+"/who";
    public static final String API_V1_LIKE_LIST = API_V1_LIKE+LIST;

}
